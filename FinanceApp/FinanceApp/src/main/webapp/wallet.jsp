<%@ page import="FinanceApp.FinanceApp" %>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException" %>
<%@page import="java.io.IOException" %>

<style><%@include file="/WEB-INF/css/finance.css"%></style>
<%@ page language="java" contentType="text/html; charset=utf-8"%>

<html>
    <div class="shadow">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>FinanceApp</title>
        <meta name="description" content="A finance app that works by calling tickers">
        <link rel="stylesheet" href="css/finance.css">
        <!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script> -->
    </head>
    <nav>
        <ul>
            <li><a href="index.jsp">Portfolio</a></li>
            <li><a href="history.jsp">History</a></li>
            <li><a href="transactions.jsp">Transactions</a></li>
            <li><a class="active" href="wallet.jsp">Wallet</a></li>
        </ul>
    </nav>
    <body>
        <h1>Add Funds To Wallet</h1>
       
       <%
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
		
		String currWalletQuery = "select ROUND(currentWallet/100,2) as currentWallet from finance.wallet where walletID = 1";
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(currWalletQuery);
		  	while(rs.next()){
		%> 
			<h2>Current Wallet: <%= rs.getInt("currentWallet")%></h2>
		<%
		  }
		} catch (SQLException e) {
		  System.out.println(e);
		}
		
		try {
			conn.close();}
		catch(Exception e) {
			System.out.println(e);
		}
		%>
       
        <form method="post" action="addFunds">
            <input type="number" id="addFunds" name="addFunds"/>
            <button type="submit">Add Funds</button>
        </form>
    </body>
    </div>
</html>
