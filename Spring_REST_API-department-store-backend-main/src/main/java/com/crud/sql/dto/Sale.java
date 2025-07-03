package com.crud.sql.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Sales")
public class Sale {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="cashier_id")
	Cashier cashier;
	
	@ManyToOne
	@JoinColumn(name="cash_id")
	Cash cash;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	Product product;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date registeredAt;

	/**
	 * void constructor
	 */
	public Sale() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param cashier
	 * @param cash
	 * @param product
	 * @param registeredAt
	 */
	public Sale(int id, Cashier cashier, Cash cash, Product product, Date registeredAt) {
		super();
		this.id = id;
		this.cashier = cashier;
		this.cash = cash;
		this.product = product;
		this.registeredAt = registeredAt;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the cashier
	 */
	public Cashier getCashier() {
		return cashier;
	}

	/**
	 * @param cashier the cashier to set
	 */
	public void setCashier(Cashier cashier) {
		this.cashier = cashier;
	}

	/**
	 * @return the cash
	 */
	public Cash getCash() {
		return cash;
	}

	/**
	 * @param cash the cash to set
	 */
	public void setCash(Cash cash) {
		this.cash = cash;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the registeredAt
	 */
	public Date getRegisteredAt() {
		return registeredAt;
	}

	/**
	 * @param registeredAt the registeredAt to set
	 */
	public void setRegisteredAt(Date registeredAt) {
		this.registeredAt = registeredAt;
	}

	@Override
	public String toString() {
		return "Sale [id=" + id + ", cashier=" + cashier + ", cash=" + cash + ", product=" + product + ", registeredAt="
				+ registeredAt + "]";
	}
	
	
	
	

	
	

	
	
	

}
