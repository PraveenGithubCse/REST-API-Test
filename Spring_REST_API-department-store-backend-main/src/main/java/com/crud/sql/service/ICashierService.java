package com.crud.sql.service;

import java.util.List;

import com.crud.sql.dto.Cashier;

public interface ICashierService {
	//CRUD methods signatures
	public List<Cashier> listCashiers();	
	public Cashier saveCashier(Cashier cashier);
	public Cashier cashierXID(int id);
	public Cashier updateCashier(Cashier cashier);
	public void eliminateCashier(int id);

}
