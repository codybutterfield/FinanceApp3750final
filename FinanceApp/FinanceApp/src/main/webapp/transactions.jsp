<%@ page import="FinanceApp.FinanceApp" %>
<%@ page import="DatePrice.DatePrice" %>
<%@ page import="DatePrice.DatePrice" %>
<%@ page import="yahoofinance.Stock" %>
<%@ page import="yahoofinance.YahooFinance" %>
<%@ page import="yahoofinance.histquotes.HistoricalQuote" %>
<%@ page import="yahoofinance.histquotes.Interval" %>


<style><%@include file="/WEB-INF/css/finance.css"%></style>

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
            <li><a class="active" href="transactions.jsp">Transactions</a></li>
            <li><a href="wallet.jsp">Wallet</a></li>
        </ul>
    </nav>
    <body>
        <h1>Transactions</h1>
        <form method="post" action="testResult">
            <input type="text" id="stockSearch" name="stockSearch"/>
            <input type="text" id="search" name="search" value="search" hidden/>
            <button type="submit">Search</button>
        </form>
        
        <h2><%= request.getParameter("price") %></h2>
        <h2><%= request.getParameter("name") %></h2>
        <h2><%= request.getParameter("change") %></h2>
        
        <form method="post" action="threeTicker">
        	<input type="text" id="ticker1" name="ticker1"/><br/>
        	<input type="number" id="ticker1pct" name="ticker1pct"/><br/>
        	<input type="text" id="ticker2" name="ticker2"/><br/>
        	<input type="number" id="ticker2pct" name="ticker2pct"/><br/>
        	<input type="text" id="ticker3" name="ticker3"/><br/>
        	<input type="number" id="ticker3pct" name="ticker3pct"/><br/>
        	<button type="submit">Add Investment</button>
        </form>

    </body>
    </div>
</html>
