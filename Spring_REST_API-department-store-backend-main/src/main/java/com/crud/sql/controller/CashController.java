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

import com.crud.sql.dto.Cash;
import com.crud.sql.service.CashServiceImpl;

@RestController
@RequestMapping("/api")
public class CashController {
	
	@Autowired
	CashServiceImpl cashServiceImpl;
	
	@GetMapping("/cash-registers")
	public List<Cash> listCashes(){
		return cashServiceImpl.listCashes();
	}
	
	
	@PostMapping("/cash-registers")
	public Cash saveCash(@RequestBody Cash cash) {
		
		return cashServiceImpl.saveCash(cash);
	}
	
	
	@GetMapping("/cash-registers/{id}")
	public Cash cashXID(@PathVariable(name="id") int id) {
		
		Cash cash_xid= new Cash();		
		cash_xid=cashServiceImpl.cashXID(id);
	
		return cash_xid;
	}
	
	@PutMapping("/cash-registers/{id}")
	public Cash updateCash(@PathVariable(name="id")int id,@RequestBody Cash cash) {
		
		Cash selected_cash= new Cash();
		Cash updated_cash= new Cash();
		
		selected_cash= cashServiceImpl.cashXID(id);
		
		selected_cash.setFloor(cash.getFloor());
		
		updated_cash = cashServiceImpl.updateCash(selected_cash);
		
		return updated_cash;
	}
	
	@DeleteMapping("/cash-registers/{id}")
	public void eliminateCash(@PathVariable(name="id")int id) {
		cashServiceImpl.eliminateCash(id);
	}

}
