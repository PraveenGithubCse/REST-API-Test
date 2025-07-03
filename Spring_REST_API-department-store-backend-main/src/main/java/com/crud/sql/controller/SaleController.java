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
import com.crud.sql.dto.Sale;
import com.crud.sql.service.ProductServiceImpl;
import com.crud.sql.service.SaleServiceImpl;

@RestController
@RequestMapping("/api")
public class SaleController {

	@Autowired
	SaleServiceImpl saleServiceImpl;

	@GetMapping("/sales")
	public List<Sale> listSales() {
		return saleServiceImpl.listSales();
	}

	@PostMapping("/sales")
	public Sale saveSale(@RequestBody Sale sale) {
		return saleServiceImpl.saveSale(sale);
	}

	@GetMapping("/sales/{id}")
	public Sale saleXID(@PathVariable(name = "id") int id) {

		Sale sale_xid = new Sale();
		sale_xid = saleServiceImpl.saleXID(id);

		return sale_xid;
	}

//	@PutMapping("/sales/{id}")
//	public Sale updateSale(@PathVariable(name = "id") int id, @RequestBody Sale sale) {
//
//		Sale selected_sale = new Sale();
//		Sale updated_sale = new Sale();
//
//		selected_sale = saleServiceImpl.saleXID(id);
//
//		selected_sale.setCashier(sale.getCashier());
//		selected_sale.setCash(sale.getCash());
//		selected_sale.setProduct(sale.getProduct());
//
//		updated_sale = saleServiceImpl.updateSale(selected_sale);
//
//		return updated_sale;
//	}

	@DeleteMapping("/sales/{id}")
	public void eliminateSale(@PathVariable(name = "id") int id) {
		saleServiceImpl.eliminateSale(id);
	}

}
