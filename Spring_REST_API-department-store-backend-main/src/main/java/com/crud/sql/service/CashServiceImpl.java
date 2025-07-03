package com.crud.sql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.sql.dao.ICashDAO;
import com.crud.sql.dto.Cash;

@Service
public class CashServiceImpl implements ICashService{
	
	@Autowired
	ICashDAO iCashDAO;

	@Override
	public List<Cash> listCashes() {
		// TODO Auto-generated method stub
		return iCashDAO.findAll();
	}

	@Override
	public Cash saveCash(Cash cash) {
		// TODO Auto-generated method stub
		return iCashDAO.save(cash);
	}

	@Override
	public Cash cashXID(int id) {
		// TODO Auto-generated method stub
		return iCashDAO.findById(id).get();
	}

	@Override
	public Cash updateCash(Cash cash) {
		// TODO Auto-generated method stub
		return iCashDAO.save(cash);
	}

	@Override
	public void eliminateCash(int id) {
		// TODO Auto-generated method stub
		iCashDAO.deleteById(id);
	}

}
