package com.crud.sql.service;

import java.util.List;

import com.crud.sql.dto.Cash;

public interface ICashService {
	//CRUD methods signatures
	public List<Cash> listCashes();	
	public Cash saveCash(Cash cash);
	public Cash cashXID(int id);
	public Cash updateCash(Cash cash);
	public void eliminateCash(int id);

}
