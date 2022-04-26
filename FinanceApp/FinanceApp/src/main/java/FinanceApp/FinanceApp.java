package FinanceApp;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
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
	
	public int getCurrentWallet() {
		int currentWallet = 0;
		Connection conn = null;
		try {
		    String url       = "jdbc:mysql://localhost:3306/mysql";
		    String user      = "root";
		    String password  = "root";

		    Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		    conn = DriverManager.getConnection(url, user, password);
		} catch(SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
		   System.out.println(e.getMessage());
		}
		
		String currWalletQuery = "select currentWallet from finance.wallet where walletID = 1";
	    try (Statement stmt = conn.createStatement()) {
	      ResultSet rs = stmt.executeQuery(currWalletQuery);
	      rs.next();
	      currentWallet = rs.getInt("currentWallet");
	    } catch (SQLException e) {
		  System.out.println(e);
		}
	
		try {
	    	conn.close();}
	    catch(Exception e) {
	    	System.out.println(e);
	    }
		
		return currentWallet;
    }
	
	public void addFunds(int funds) {
		Connection conn = null;
		try {
		    String url       = "jdbc:mysql://localhost:3306/mysql";
		    String user      = "root";
		    String password  = "root";

		    Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		    conn = DriverManager.getConnection(url, user, password);
		} catch(SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
		   System.out.println(e.getMessage());
		}
		
		int currentWallet = getCurrentWallet();
	    currentWallet += funds;
		
		String addFundsQuery = "update finance.wallet "
	      		+ "set currentWallet = " + currentWallet + ", netWorth = " + currentWallet + " where walletID = 1";
	    try (Statement stmt = conn.createStatement()) {
	      stmt.executeUpdate(addFundsQuery);
	    } catch (SQLException e) {
	      System.out.println(e);
	    }
	    
	    try {
	    	conn.close();}
	    catch(Exception e) {
	    	System.out.println(e);
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

	public void threeTicker(String ticker1, String ticker2, String ticker3, int perc1, int perc2, int perc3) throws IOException
	{
		int startDec = getCurrentWallet();

		
		int ticker1Dec = (startDec*perc1)/100;
		int ticker2Dec = (startDec*perc2)/100;
		int ticker3Dec = (startDec*perc3)/100;	
		
//		BigDecimal ticker2Dec = startDec.subtract(ticker1Dec);
//		ticker2Dec = ticker2Dec.multiply(bdPerc2.divide(bdPerc2.add(bdPerc3)));
//		ticker2Dec = ticker2Dec.setScale(2, RoundingMode.HALF_DOWN);
//		BigDecimal ticker3Dec;
//		if((perc1 + perc2 + perc3) < 100)
//		{
//			ticker3Dec = startDec.multiply(bdPerc3.divide(BigDecimal.valueOf(100)));
//			ticker3Dec = ticker3Dec.setScale(2, RoundingMode.HALF_DOWN);
//		}
//		else
//		{
//			ticker3Dec = startDec.subtract(ticker1Dec);
//			ticker3Dec = ticker3Dec.subtract(ticker2Dec);
//		}
		
		int subtractDec = ticker1Dec + ticker2Dec + ticker3Dec;
		/* Check for not enough money */
		subtractDec = 0 - subtractDec;
		//invest ticker1Dec, ticker2Dec, and ticker3Dec, subtract subtractDec from wallet
		
		
		int ticker1shares = ticker1Dec / getPrice(ticker1);
		int ticker2shares = ticker2Dec / getPrice(ticker2);
		int ticker3shares = ticker3Dec / getPrice(ticker3);
		
		if (startDec + subtractDec >= 0) {
			insertInvest(ticker1, ticker2, ticker3, getPrice(ticker1), getPrice(ticker2), getPrice(ticker3), ticker1shares, ticker2shares, ticker3shares);
			addFunds(subtractDec);
		}
	}
	
	//symbol, price, date, shares
	
	public void insertInvest(String ticker1, String ticker2, String ticker3, int price1, int price2, int price3, int ticker1shares, int ticker2shares, int ticker3shares) {
		Connection conn = null;
		try {
		    String url       = "jdbc:mysql://localhost:3306/mysql";
		    String user      = "root";
		    String password  = "root";

		    Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		    conn = DriverManager.getConnection(url, user, password);
		} catch(SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
		   System.out.println(e.getMessage());
		}
		
		String investQuery1 = "insert into finance.stocks(tickerSymbol, priceBoughtAt, dateBought, shares) values ('" + ticker1 + "', " + price1 + ", curdate(), " + ticker1shares + ")";
		String investQuery2 = "insert into finance.stocks(tickerSymbol, priceBoughtAt, dateBought, shares) values ('" + ticker2 + "', " + price2 + ", curdate(), " + ticker2shares + ")";
		String investQuery3 = "insert into finance.stocks(tickerSymbol, priceBoughtAt, dateBought, shares) values ('" + ticker3 + "', " + price3 + ", curdate(), " + ticker3shares + ")";
	    try (Statement stmt = conn.createStatement()) {
	      stmt.executeUpdate(investQuery1);
	      stmt.executeUpdate(investQuery2);
	      stmt.executeUpdate(investQuery3);
	    } catch (SQLException e) {
	      System.out.println(e);
	    }
	    
	    try {
	    	conn.close();
	    } catch(Exception e) {
	    	System.out.println(e);
	    }
	}
	
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
    
    public int getPrice(String stockName) throws IOException {
    	Stock stock = YahooFinance.get(stockName);
    	BigDecimal price = stock.getQuote().getPrice();
    	price.multiply(BigDecimal.valueOf(100));
    	return price.intValue();
    }
}
