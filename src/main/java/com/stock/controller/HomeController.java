package com.stock.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.SocketUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.stock.dao.CompanyDAO;
import com.stock.dao.StockInfoDAO;
import com.stock.model.Company;
import com.stock.model.StockInfo;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@Controller
public class HomeController {

	@Autowired
	private CompanyDAO companyDAO;

	@Autowired
	private StockInfoDAO stockInfoDAO;

	@RequestMapping(value = "/")
	public ModelAndView test(HttpServletResponse response) throws ServletException, IOException {
		return new ModelAndView("home");
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public void getCompany(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		String company_symbol = request.getParameter("symbol");
		Stock stock = YahooFinance.get(company_symbol);
		if(!stock.getName().equals("N/A")){
		 
			BigDecimal price = stock.getQuote().getPrice();
			BigDecimal change = stock.getQuote().getChangeInPercent();
			BigDecimal peg = stock.getStats().getPeg();
			BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();
			 
			// Set response content type
		    response.setContentType("text/html");
		     
		    PrintWriter out = response.getWriter();
		      out.println("Company: "+stock.getName()+"\nTicker Symbol: "+company_symbol+"\nPrice: "+price+"\nCurrency: "+stock.getCurrency());
	
		}else{
			response.setContentType("text/html");
		     
		    PrintWriter out = response.getWriter();
		      out.println("Incorrent Company Symbol!");
		}
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void saveCompany(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException{
		String company_symbol = request.getParameter("symbol");
		Stock stock = YahooFinance.get(company_symbol);
		if(!stock.getName().equals("N/A")){
			String company_name = stock.getName();
			String type = stock.getStockExchange();
			Company new_company = new Company(company_name,company_symbol, type);
			int ret = companyDAO.saveOrUpdate(new_company);
			
			StockInfo stockInfo = new StockInfo(company_symbol, stock.getQuote().getPrice().floatValue());
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if(ret == 1){
			      out.println("Company "+company_name+" added to the database!");
			      stockInfoDAO.saveOrUpdate(stockInfo);
			}else
				out.println("Company "+company_name+ " is already present in the database!");
		}else{
			response.setContentType("text/html");
		     
		    PrintWriter out = response.getWriter();
		      out.println("Incorrent Company Symbol!");
		}
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void getCompanyList(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
					 
		// Set response content type
	    response.setContentType("text/html");
	     
	    PrintWriter out = response.getWriter();
	    List<Company> companies = companyDAO.list();
	
	    for(Company company: companies){
	    	Stock stock = YahooFinance.get(company.getSymbol());
	    	out.println("Company: "+company.getName()+", Stock Price: "+stock.getQuote().getPrice());
	    }
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public void deleteCompany(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		String company_symbol = request.getParameter("symbol");
		int ret = companyDAO.delete(company_symbol);
		response.setContentType("text/html");
	     
	    PrintWriter out = response.getWriter();
	    if(ret == 1)
	    	out.println("Company with symbol "+company_symbol+" deleted succesfully!");
	    else
	    	out.println("Company with symbol "+company_symbol+" not present in the database!");
	    
	}
}
