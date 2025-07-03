package com.crud.sql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.sql.dao.ISaleDAO;
import com.crud.sql.dto.Sale;

@Service
public class SaleServiceImpl implements ISaleService{
	
	@Autowired
	ISaleDAO iSaleDAO;

	@Override
	public List<Sale> listSales() {
		// TODO Auto-generated method stub
		return iSaleDAO.findAll();
	}

	@Override
	public Sale saveSale(Sale sale) {
		// TODO Auto-generated method stub
		return iSaleDAO.save(sale);
	}

	@Override
	public Sale saleXID(int id) {
		// TODO Auto-generated method stub
		return iSaleDAO.findById(id).get();
	}

	@Override
	public Sale updateSale(Sale sale) {
		// TODO Auto-generated method stub
		return iSaleDAO.save(sale);
	}

	@Override
	public void eliminateSale(int id) {
		// TODO Auto-generated method stub
		iSaleDAO.deleteById(id);
	}
	
	

}
