package com.database;

import java.util.Date;
import java.util.List;

import com.core.Sale;

public interface SaleLadgerDao {
	public Sale insert(Sale sale);
	public int delete(Sale sale);
	public Sale update(Sale item);
	public List<Sale> findAll();
	public Sale findByID(int id);
	public List<Sale> findByDate(Date from , Date to);
}
