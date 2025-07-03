package com.crud.sql.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.sql.dto.Product;

public interface IProductDAO extends JpaRepository<Product, Integer>{

}
