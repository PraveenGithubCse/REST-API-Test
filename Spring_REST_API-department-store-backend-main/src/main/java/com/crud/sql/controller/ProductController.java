package com.crud.sql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.sql.dto.Product;
import com.crud.sql.service.ProductServiceImpl;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	ProductServiceImpl cashServiceImpl;
	
	@GetMapping("/products")
	public List<Product> listProducts(){
		return cashServiceImpl.listProducts();
	}
	
	
	@PostMapping("/products")
	public Product saveProduct(@RequestBody Product product) {
		
		return cashServiceImpl.saveProduct(product);
	}
	
	
	@GetMapping("/products/{id}")
	public Product cashXID(@PathVariable(name="id") int id) {
		
		Product product_xid= new Product();		
		product_xid=cashServiceImpl.productXID(id);
	
		return product_xid;
	}
	
	@PutMapping("/products/{id}")
	public Product updateProduct(@PathVariable(name="id")int id,@RequestBody Product product) {
		
		Product selected_product= new Product();
		Product updated_product= new Product();
		
		selected_product= cashServiceImpl.productXID(id);
		
		selected_product.setName(product.getName());
		selected_product.setPrice(product.getPrice());
		
		updated_product = cashServiceImpl.updateProduct(selected_product);
		
		return updated_product;
	}
	
	@DeleteMapping("/products/{id}")
	public void eliminateProduct(@PathVariable(name="id")int id) {
		cashServiceImpl.eliminateProduct(id);
	}

}
