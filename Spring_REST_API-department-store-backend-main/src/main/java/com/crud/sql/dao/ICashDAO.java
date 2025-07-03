package com.crud.sql.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.sql.dto.Cash;

public interface ICashDAO extends JpaRepository<Cash, Integer>{

}
