package com.stock.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.stock.model.Company;

public class CompanyDAOImpl implements CompanyDAO{

	private JdbcTemplate jdbcTemplate;
	
	public CompanyDAOImpl(){
		
	}
	
    public CompanyDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	@Override
	public int saveOrUpdate(Company company) {
		// TODO Auto-generated method stub
        // insert
        String sql = "INSERT INTO company (symbol, name, type)"
                    + " VALUES (?, ?, ?)";
        try {
        	return jdbcTemplate.update(sql, company.getSymbol(), company.getName(), company.getType());
        } catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int delete(String symbol) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM company WHERE symbol=?";
	    return jdbcTemplate.update(sql, symbol);
	}

	@Override
	public Company get(String symbol) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Company> list() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM company";
	    List<Company> listCompany = jdbcTemplate.query(sql, new RowMapper<Company>() {
	 

			@Override
			public Company mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				Company aCompany = new Company();
				 
				aCompany.setName(rs.getString("name"));
				aCompany.setSymbol(rs.getString("symbol"));
				aCompany.setType(rs.getString("type"));
				
	 
	            return aCompany;
			}
	 
	    });
	 
	    return listCompany;
	}

}
