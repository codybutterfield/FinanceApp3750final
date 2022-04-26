<%@ page import="FinanceApp.FinanceApp" %>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException" %>
<%@page import="java.io.IOException" %>
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
            <li><a class="active" href="history.jsp">History</a></li>
            <li><a href="transactions.jsp">Transactions</a></li>
            <li><a href="wallet.jsp">Wallet</a></li>
        </ul>
    </nav>
    <body>
        <h1>History</h1>
        <select name=” history ” onChange=”combo(this, ’history’)”>
        <option>option 1</option>
        <option>option 2</option>
        <option>option 3</option>
        <option> option 4 </option>
        <option> option 5 </option>
        </select>
    </body>
    </div>
</html>

