package FinanceApp;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import DatePrice.DatePrice;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

public class FinanceApp {
	
	public void testResult() throws IOException
    {
//    	System.out.println(investTool.getStock("GOOG"));

		Connection conn = null;
		try {
		    // db parameters
		    String url       = "jdbc:mysql://localhost:3306/mysql";
		    String user      = "root";
		    String password  = "root";

		    Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		    // create a connection to the database
		    conn = DriverManager.getConnection(url, user, password);
		    // more processing here
		    // ...	
		} catch(SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
		   System.out.println("error: " +e.getMessage());
		}
				
		 String query = "select * from finance.test";
		    try (Statement stmt = conn.createStatement()) {
		      ResultSet rs = stmt.executeQuery(query);
		      while (rs.next()) {
		        int id = rs.getInt("id");
		        String testCol = rs.getString("testCol");
		        System.out.println(id);
			    System.out.println(testCol);
		      }
		      
		    } catch (SQLException e) {
		      System.out.println(e);
		    }
		    
		    try {
		    	conn.close();}
		    catch(Exception e) {
		    	System.out.println("oops");
		    }

    }
	
	
	public void oneTicker(String ticker, int perc) {
		BigDecimal startDec = new BigDecimal(0);
		BigDecimal newDec = startDec.multiply(BigDecimal.valueOf(perc / 100));
		newDec = newDec.setScale(2, RoundingMode.HALF_DOWN);
		//invest newDec into chosen ticker, subtract newDec from wallet
		//System.out.println(newDec);
		return;
	}

	public void twoTicker(String ticker1, String ticker2, int perc1, int perc2)
	{
		BigDecimal startDec = new BigDecimal(0);
		BigDecimal ticker1Dec = startDec.multiply(BigDecimal.valueOf(perc1 / 100));
		ticker1Dec = ticker1Dec.setScale(2, RoundingMode.HALF_DOWN);
		BigDecimal ticker2Dec;
		if((perc1 + perc2) < 100)
		{
			ticker2Dec = startDec.multiply(BigDecimal.valueOf(perc2 / 100));
			ticker2Dec = ticker2Dec.setScale(2, RoundingMode.HALF_DOWN);
		}
		else
		{
			ticker2Dec = startDec.subtract(ticker1Dec);
		}
		BigDecimal subtractDec = ticker1Dec.add(ticker2Dec);
		//invest ticker1Dec and ticker2Dec, subtract subtractDec from wallet
		
	}

	public void threeTicker(String ticker1, String ticker2, String ticker3, int perc1, int perc2, int perc3)
	{
		BigDecimal startDec = new BigDecimal(0);
		BigDecimal ticker1Dec = startDec.multiply(BigDecimal.valueOf(perc1 / 100));
		ticker1Dec = ticker1Dec.setScale(2, RoundingMode.HALF_DOWN);
		BigDecimal ticker2Dec = startDec.subtract(ticker1Dec);
		ticker2Dec = ticker2Dec.multiply(BigDecimal.valueOf(perc2/(perc2 + perc3)));
		BigDecimal ticker3Dec;
		if((perc1 + perc2 + perc3) < 100)
		{
			ticker3Dec = startDec.multiply(BigDecimal.valueOf(perc3 / 100));
			ticker3Dec = ticker3Dec.setScale(2, RoundingMode.HALF_DOWN);
		}
		else
		{
			ticker3Dec = startDec.subtract(ticker1Dec);
			ticker3Dec = ticker3Dec.subtract(ticker2Dec);
		}
		BigDecimal subtractDec = ticker1Dec.add(ticker2Dec);
		subtractDec = subtractDec.add(ticker3Dec);
		//invest ticker1Dec, ticker2Dec, and ticker3Dec, subtract subtractDec from wallet
	}
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	private String convertDate(Calendar cal) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String formatDate = format.format(cal.getTime());
        return formatDate;
    }
    
    private Interval getInterval(String searchType) {
        Interval interval = null;
        switch (searchType.toUpperCase()) {
        case "MONTHLY":
            interval = Interval.MONTHLY;
            break;
        case "WEEKLY":
            interval = Interval.WEEKLY;
            break;
        case "DAILY":
            interval = Interval.DAILY;
            break;
        }
        return interval;
    }
    
    public List<BigDecimal> getHistoryPrice(String stockName, int year, int month, int day, String searchType) throws IOException {
        List<BigDecimal> historyList = new ArrayList<BigDecimal>();
        
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        from.set(year, month, day);
        
        Stock stock = YahooFinance.get(stockName);
        List<HistoricalQuote> history = stock.getHistory(from, to, getInterval(searchType));
        
        for(HistoricalQuote quote:history) {
            historyList.add(quote.getClose());
        }
        
        System.out.println("getHistoryPrice(): " + historyList);
        
        return historyList;
    }
    
    public List<DatePrice> getHistoryDatePrice(String stockName, int year, int month, int day, String searchType) throws IOException {
        List<DatePrice> historyList = new ArrayList<DatePrice>();
        
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        from.set(year, month, day);
        
        Stock stock = YahooFinance.get(stockName);
        List<HistoricalQuote> history = stock.getHistory(from, to, getInterval(searchType));
        
        for(HistoricalQuote quote:history) {
            DatePrice temp = new DatePrice(quote.getDate(), quote.getClose());
            historyList.add(temp);
        }
        
        System.out.println("getHistoryDatePrice(): " + historyList);
        
        return historyList;
    }
    
    public Map<String, Stock> getStock(String[] stockNames) throws IOException {
        Map<String, Stock> stock = YahooFinance.get(stockNames);
        
        return stock;
    }
    
    public Stock getPrice(String stockName) throws IOException {
    	Stock stock = YahooFinance.get(stockName);
    	System.out.println(stock);
		return stock;
    }
}
