package com.android.ui;

import java.util.List;

import com.android.softspectproject.R;
import com.core.ItemDescription;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StockViewAllCustomArrayAdapter extends ArrayAdapter<ItemDescription> {
	
	private List<ItemDescription> itemDescriptions;
	private Activity context;

	public StockViewAllCustomArrayAdapter(Activity context,List<ItemDescription> itemDescriptions) {
		super(context,R.layout.activity_viewall_itemdescription,itemDescriptions);
		this.itemDescriptions = itemDescriptions;
		this.context = context;
	}

	@Override
	public View getView(final int position, View view, ViewGroup parent) {
	LayoutInflater inflater = context.getLayoutInflater();
	View rowView= inflater.inflate(R.layout.activity_viewall_itemdescription, null, true);
	TextView txtTitle = (TextView) rowView.findViewById(R.id.txtViewAll);
	txtTitle.setText(itemDescriptions.get(position).toString());
	Button btEdit = (Button) rowView.findViewById(R.id.btViewAllEdit);
	 
	 btEdit.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			//Toast.makeText(getContext(), itemDescriptions.get(position).toString(),1).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            
            String s =	"Name : "+ itemDescriptions.get(position).getName() +
            			"\nBarcode : "+ itemDescriptions.get(position).getBarcode()+
            			"\nPrice : "+ itemDescriptions.get(position).getPrice()+ 
            			"\nDescription : " + itemDescriptions.get(position).getItemDescription();
            	
            builder.setTitle("Product Description");
            builder.setMessage(s);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show();
                	
                }
            });
            
            builder.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					 Toast.makeText(context, itemDescriptions.get(position).toString(), Toast.LENGTH_SHORT).show();	
					 Intent intent = new Intent(context, EditProductDescriptionActivity.class);
					 intent.putExtra("oldItemDescription", itemDescriptions.get(position));
					 context.startActivity(intent);
				}
			});
            
            builder.show(); //To show the AlertDialog

		}
	});
	return rowView;
	}
	

}
