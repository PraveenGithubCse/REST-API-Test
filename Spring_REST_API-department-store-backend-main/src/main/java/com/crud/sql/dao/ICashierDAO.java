package com.crud.sql.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.sql.dto.Cashier;

public interface ICashierDAO extends JpaRepository<Cashier, Integer>{

}
