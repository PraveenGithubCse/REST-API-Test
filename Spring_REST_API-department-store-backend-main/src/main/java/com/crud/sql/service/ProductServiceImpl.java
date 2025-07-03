package com.crud.sql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.sql.dao.IProductDAO;
import com.crud.sql.dto.Product;

@Service
public class ProductServiceImpl implements IProductService{
	
	@Autowired
	IProductDAO iProductDAO;

	@Override
	public List<Product> listProducts() {
		// TODO Auto-generated method stub
		return iProductDAO.findAll();
	}

	@Override
	public Product saveProduct(Product product) {
		// TODO Auto-generated method stub
		return iProductDAO.save(product);
	}

	@Override
	public Product productXID(int id) {
		// TODO Auto-generated method stub
		return iProductDAO.findById(id).get();
	}

	@Override
	public Product updateProduct(Product product) {
		// TODO Auto-generated method stub
		return iProductDAO.save(product);
	}

	@Override
	public void eliminateProduct(int id) {
		// TODO Auto-generated method stub
		iProductDAO.deleteById(id);
	}



}
