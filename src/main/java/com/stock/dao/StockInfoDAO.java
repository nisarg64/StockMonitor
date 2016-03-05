package com.stock.dao;

import java.util.List;

import com.stock.model.StockInfo;

public interface StockInfoDAO {

	public void saveOrUpdate(StockInfo stock);

	public void delete(String symbol);

	public StockInfo get(String symbol);

	public List<StockInfo> list(String symbol);
}
