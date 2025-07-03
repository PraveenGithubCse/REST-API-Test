package com.crud.sql.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Cashier")
public class Cashier {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="surname_name")
	private String surname_name;
	
	@OneToMany
	@JoinColumn(name="id")
	private List<Sale> sale;

	/**
	 * void constructor
	 */
	public Cashier() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param surname_name
	 * @param sale
	 */
	public Cashier(int id, String surname_name, List<Sale> sale) {
		super();
		this.id = id;
		this.surname_name = surname_name;
		this.sale = sale;
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
	 * @return the surname_name
	 */
	public String getSurname_name() {
		return surname_name;
	}

	/**
	 * @param surname_name the surname_name to set
	 */
	public void setSurname_name(String surname_name) {
		this.surname_name = surname_name;
	}

	/**
	 * @return the sale
	 */
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "Sale")
	public List<Sale> getSale() {
		return sale;
	}

	/**
	 * @param sale the sale to set
	 */
	public void setSale(List<Sale> sale) {
		this.sale = sale;
	}

	@Override
	public String toString() {
		return "Cashier [id=" + id + ", surname_name=" + surname_name + "]";
	}
	
	
	

}
