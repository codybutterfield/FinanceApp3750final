<%@ page import="FinanceApp.FinanceApp" %>
<style><%@include file="/WEB-INF/css/finance.css"%></style>

<html>
    <div class="shadow">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>FinanceApp</title>
        <meta name="description" content="A finance app that works by calling tickers">
        <link rel="stylesheet" href="finance.css">
    </head>
    <nav>
        <ul>
            <li><a href="index.jsp">Portfolio</a></li>
            <li><a class="active" href="history.jsp">History</a></li>
            <li><a href="transactions.jsp">Transactions</a></li>
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

