package com.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.core.Cashier;
import com.core.Customer;
import com.core.Item;
import com.core.Payment;
import com.core.Sale;

/**
* SaleLadger DataAccessObject for SQLite.
* @author Krittayot Techasombooranakit 5510545976
*/
public class SaleLadgerDB extends GenericDao implements SaleLadgerDao{
	
	/**
	 * Constructor.
	 * @param context of application.
	 */
	public SaleLadgerDB(Context context){
		super(context, GenericDao.dName, Sale.TABLE_CREATE, Sale.DATABASE_TABLE, Sale.DATABASE_VERSION);
	}
	
	/**
	 * @see com.database.SaleLadgerDao#insert(com.core.Sale)
	 */
	@Override
	public Sale insert(Sale sale) {
		PaymentBookDB paymentBookDB = new PaymentBookDB(getContext());
        sale.setPayment(paymentBookDB.insert(sale.getPayment()));
        paymentBookDB.close();
		
		ContentValues cv = new ContentValues();
		if(sale.getCustomer().getID() == -1){
			CustomerBookDB customerBookDB = new CustomerBookDB(getContext());
			sale.setCustomer( customerBookDB.insert(new Customer(-1, "none", sale.getCustomer().getRegisterDate(), sale.getCustomer().getEmail()))  );
		}
		cv.put(Sale.COL_CUSTOMER_ID , sale.getCustomer().getID());
        cv.put(Sale.COL_DATE , sale.getDate().getTime());  
        cv.put(Sale.COL_PAYMENT_ID , sale.getPayment().getID());
        cv.put(Sale.COL_CASHIER_ID , sale.getCashier().getId());
        
        sale = new Sale((int)super.insert(Sale.DATABASE_TABLE, cv) ,sale.getItems(), sale.getCashier() , sale.getCustomer() ,sale.getPayment() , sale.getDate());
        List<Item> items = sale.getItems();
        InventoryDB inventoryDB = new InventoryDB(getContext());
        for(int i = 0 ; i < items.size() ; i++){
        	inventoryDB.updateSaleID(sale.getID(), items.get(i));
        }
        inventoryDB.close();
        return sale;
	}

	/**
	 * @see com.database.SaleLadgerDao#delete(com.core.Sale)
	 */
	@Override
	public int delete(Sale sale) {
		PaymentBookDB paymentBookDB = new PaymentBookDB(getContext());
		paymentBookDB.deleteByID(sale.getPayment().getID());
		paymentBookDB.close();
		
		InventoryDB inventoryDB = new InventoryDB(getContext());
		inventoryDB.moveToStockBySaleID(sale.getID());
		inventoryDB.close();
		
		return (int)super.delete(Sale.DATABASE_TABLE , sale.getID());
		
	}

	/**
	 * @see com.database.SaleLadgerDao#update(com.core.Sale)
	 */
	@Override
	public Sale update(Sale sale) {
		delete(sale);
		sale = insert(sale);
		return sale;
	}

	/**
	 * @see com.database.SaleLadgerDao#findAll()
	 */
	@Override
	public ArrayList<Sale> findAll() {
		String[] columns = new String[]{GenericDao.KEY_ID, Sale.COL_CUSTOMER_ID ,  Sale.COL_DATE , Sale.COL_PAYMENT_ID , Sale.COL_CASHIER_ID};
		Cursor cursor = super.get(Sale.DATABASE_TABLE, columns);
		ArrayList<Sale> sales = null;
		if(cursor != null){
			if(cursor.moveToFirst()){
				int _id = cursor.getColumnIndex(GenericDao.KEY_ID);
				int cusId = cursor.getColumnIndex(Sale.COL_CUSTOMER_ID );
				int date = cursor.getColumnIndex(Sale.COL_DATE);
				int pay = cursor.getColumnIndex(Sale.COL_PAYMENT_ID);
				int _cash = cursor.getColumnIndex(Sale.COL_PAYMENT_ID);
				sales = new ArrayList<Sale>();
				CustomerBookDB customerBookDB = new CustomerBookDB(getContext());
				PaymentBookDB paymentBookDB = new PaymentBookDB(getContext());
				InventoryDB inventoryDB = new InventoryDB(getContext());
				HashMap<Integer, Cashier> cashMap = new HashMap<Integer, Cashier>();
				for(int i = 0 ; i < cursor.getCount() ; i++) {
					Customer customer = customerBookDB.findBy(cursor.getInt(cusId));
					Payment payment = paymentBookDB.findByID(cursor.getInt(pay));
					List<Item> items = inventoryDB.findBySaleID(cursor.getInt(_id));
					Cashier cashier;
					if(cashMap.containsKey(cursor.getInt(_cash))) cashier = cashMap.get(cursor.getInt(_cash));
					else{
						CashierBookDB cashDB = new CashierBookDB(getContext());
						cashier = cashDB.findBy(cursor.getInt(_cash));
						cashMap.put(cursor.getInt(_cash), cashier);
						cashDB.close();
					}
					
					sales.add( new Sale(cursor.getInt(_id), items, cashier , customer, payment , new Date(cursor.getLong(date))) );
					cursor.moveToNext();
				}
				customerBookDB.close();
				paymentBookDB.close();
				inventoryDB.close();
			}
		} 
		return sales;
	}

	/**
	 * @see com.database.SaleLadgerDao#findByID(int)
	 */
	@Override
	public Sale findByID(int id) {
		String[] columns = new String[]{GenericDao.KEY_ID, Sale.COL_CUSTOMER_ID ,  Sale.COL_DATE , Sale.COL_PAYMENT_ID , Sale.COL_CASHIER_ID};
		Cursor cursor = super.get(Sale.DATABASE_TABLE, columns , GenericDao.KEY_ID + "=" + id);
		Sale sale = null;
		if(cursor != null){
			if(cursor.moveToFirst()){
				int _id = cursor.getColumnIndex(GenericDao.KEY_ID);
				int cusId = cursor.getColumnIndex(Sale.COL_CUSTOMER_ID );
				int date = cursor.getColumnIndex(Sale.COL_DATE);
				int pay = cursor.getColumnIndex(Sale.COL_PAYMENT_ID);
				int _cash = cursor.getColumnIndex(Sale.COL_CASHIER_ID);
				
				CustomerBookDB customerBookDB = new CustomerBookDB(getContext());
				Customer customer = customerBookDB.findBy(cursor.getInt(cusId));
				customerBookDB.close();
				
				PaymentBookDB paymentBookDB = new PaymentBookDB(getContext());
				Payment payment = paymentBookDB.findByID(cursor.getInt(pay));
				paymentBookDB.close();
				
				InventoryDB inventoryDB = new InventoryDB(getContext());
				List<Item> items = inventoryDB.findBySaleID(id);
				inventoryDB.close();
				
				CashierBookDB cashDB = new CashierBookDB(getContext());
				Cashier cashier = cashDB.findBy(cursor.getInt(_cash));
				cashDB.close();
				
				sale = new Sale( cursor.getInt(_id) , items , cashier, customer, payment , new Date(cursor.getLong(date)));
			}
		} 
		return sale;
	}

}
