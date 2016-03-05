package com.stock.dao;

import java.util.List;

import com.stock.model.Company;

public interface CompanyDAO {
	public int saveOrUpdate(Company company);
    
    public int delete(String symbol);
     
    public Company get(String symbol);
     
    public List<Company> list();
}
