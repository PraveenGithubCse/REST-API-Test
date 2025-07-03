package com.crud.sql.service;

import java.util.List;

import com.crud.sql.dto.Sale;

public interface ISaleService {
	//CRUD methods signatures
	public List<Sale> listSales();	
	public Sale saveSale(Sale sale);
	public Sale saleXID(int id);
	public Sale updateSale(Sale sale);
	public void eliminateSale(int id);

}
