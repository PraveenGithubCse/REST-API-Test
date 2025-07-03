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
@Table(name="Cash")
public class Cash {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="floor")
	private int floor;
	
	@OneToMany
	@JoinColumn(name="id")
	private List<Sale> sale;

	/**
	 * void constructor
	 */
	public Cash() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param floor
	 * @param sale
	 */
	public Cash(int id, int floor, List<Sale> sale) {
		super();
		this.id = id;
		this.floor = floor;
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
	 * @return the floor
	 */
	public int getFloor() {
		return floor;
	}

	/**
	 * @param floor the floor to set
	 */
	public void setFloor(int floor) {
		this.floor = floor;
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
		return "Cash [id=" + id + ", floor=" + floor + "]";
	}
	
	
	
	

}
