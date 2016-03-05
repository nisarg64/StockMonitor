package com.stock.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.stock.model.StockInfo;

public class StockInfoDAOImpl implements StockInfoDAO {

	private JdbcTemplate jdbcTemplate;

	public StockInfoDAOImpl() {

	}

	public StockInfoDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveOrUpdate(StockInfo stock) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO company (symbol, price)"
                + " VALUES (?, ?)";
	    try {
	    	jdbcTemplate.update(sql, stock.getSymbol(), stock.getPrice());
	    } catch (Exception e) {
			
		}
	}

	@Override
	public void delete(String symbol) {
		// TODO Auto-generated method stub

	}

	@Override
	public StockInfo get(String symbol) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StockInfo> list(String symbol) {
		// TODO Auto-generated method stub
		return null;
	}

}
