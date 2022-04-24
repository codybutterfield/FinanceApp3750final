<%@ page import="FinanceApp.FinanceApp" %>
<%@ page import="DatePrice.DatePrice" %>
<style><%@include file="/WEB-INF/css/finance.css"%></style>

<html>
<body>
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
            <li><a class="active" href="index.jsp">Portfolio</a></li>
            <li><a href="history.jsp">History</a></li>
            <li><a href="transactions.jsp">Transactions</a></li>
        </ul>
    </nav>
    <body>
        <h1>Stock Portfolio</h1>
        <table>
            <thead>
            <tr>
                <td>Ticker Symbol</td>
                <td>Price</td>
                <td>Shares</td>
                <td>Value</td>
            </tr>
            </thead>
        <tbody>
            <tr>
                <td>First Ticker</td>
                <td>First Price</td>
                <td>Number of Shares</td>
                <td>Value</td>
            </tr>
            <tr>
                <td>First Ticker</td>
                <td>First Price</td>
                <td>Number of Shares</td>
                <td>Value</td>
            </tr>
            <tr>
                <td>First Ticker</td>
                <td>First Price</td>
                <td>Number of Shares</td>
                <td>Value</td>
            </tr>
        </tbody>
        </table>
    </body>
    </div>
</html>
<%
	FinanceApp fa = new FinanceApp();
	fa.testResult();
	fa.getHistoryPrice("INTC", 2022, 4, 10, "daily");
	fa.getHistoryDatePrice("INTC", 2022, 4, 10, "daily");
%>
