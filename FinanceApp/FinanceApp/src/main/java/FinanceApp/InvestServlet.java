package FinanceApp;

import java.io.IOException;
import java.math.BigDecimal;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class InvestServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		FinanceApp fa = new FinanceApp();
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		FinanceApp fa = new FinanceApp();
		String queryString = "";
		queryString += "transactions.jsp";
		
		if (request.getParameter("search") != null) {
			String ticker = (request.getParameter("stockSearch")).toUpperCase();
			
			if (YahooFinance.get(ticker).isValid()) {
	            Stock stock = YahooFinance.get(ticker);
	            String name = stock.getName();
	            BigDecimal price = stock.getQuote().getPrice();
	            BigDecimal change = stock.getQuote().getChangeInPercent();
	            queryString += "?price=" + price + "&name=" + name + "&change=" + change;    
	        }	
			response.sendRedirect(queryString);
		} else {
			String ticker1 = (request.getParameter("ticker1")).toUpperCase();
			int ticker1pct = Integer.parseInt(request.getParameter("ticker1pct"));
			String ticker2 = (request.getParameter("ticker2")).toUpperCase();
			int ticker2pct = Integer.parseInt(request.getParameter("ticker2pct"));
			String ticker3 = (request.getParameter("ticker3")).toUpperCase();
			int ticker3pct = Integer.parseInt(request.getParameter("ticker3pct"));
			fa.threeTicker(ticker1, ticker2, ticker3, ticker1pct, ticker2pct, ticker3pct);
			response.sendRedirect(queryString);
		}
		
	}
}
