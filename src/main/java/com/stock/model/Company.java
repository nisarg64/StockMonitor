package com.stock.model;

public class Company {
	private String name;
	private String symbol;
	private String type;
	
	public Company(){
		
	}
	public Company(String name, String symbol, String type){
		this.name = name;
		this.symbol = symbol;
		this.type = type;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
