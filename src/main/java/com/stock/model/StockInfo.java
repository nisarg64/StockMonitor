package com.stock.model;

import java.sql.Timestamp;

public class StockInfo {

	private int id;
	private String symbol;
	private float price;
	private Timestamp utctime;
	
	public StockInfo(){
		
	}
	
	public StockInfo(String symbol, float price){
		this.symbol = symbol;
		this.price = price;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Timestamp getUtctime() {
		return utctime;
	}
	public void setUtctime(Timestamp utctime) {
		this.utctime = utctime;
	}
	
	
}
