package com.crud.sql.service;

import java.util.List;

import com.crud.sql.dto.Product;

public interface IProductService {
	//CRUD methods signatures
	public List<Product> listProducts();	
	public Product saveProduct(Product product);
	public Product productXID(int id);
	public Product updateProduct(Product product);
	public void eliminateProduct(int id);

}
