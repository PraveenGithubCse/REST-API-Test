package com.crud.sql.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.sql.dto.Sale;

public interface ISaleDAO extends JpaRepository<Sale, Integer>{

}
