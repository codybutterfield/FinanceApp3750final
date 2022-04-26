<%@ page import="FinanceApp.FinanceApp" %>
<%@ page import="DatePrice.DatePrice" %>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException" %>
<%@page import="java.io.IOException" %>

<style><%@include file="/WEB-INF/css/finance.css"%></style>

<script type="text/javascript">
        window.onload = function () {
       var chart = new CanvasJS.Chart("chartContainer",
       {  
        backgroundColor: 'transparent',
        theme: "dark2",
        title:{
            text: "Personal Stock Distribution"
        },        
        data: [
        {       
            type: "pie",
            showInLegend: true,
            toolTipContent: "{y} - #percent %",
            yValueFormatString: "#,##0,,.## Million",
            legendText: "{indexLabel}",
            dataPoints: [
            	
           			<%
                    	FinanceApp fa = new FinanceApp();
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
        				
        				String chartQuery = "SELECT tickerSymbol, ROUND(shares, 2) as shares FROM finance.stocks";
        				try (Statement stmt = conn.createStatement()) {
        					ResultSet rs = stmt.executeQuery(chartQuery);
        				  	while(rs.next()){
        			%> 
        					
        					{  y: <%= fa.getPrice(rs.getString("tickerSymbol"))*rs.getDouble("shares") %>, indexLabel: "<%= rs.getString("tickerSymbol")%>" },
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
            ]
        }
        ]
       });
       chart.render();
        }
</script>
<script type="text/javascript" src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>



<html>
<body>
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
            <li><a class="active" href="index.jsp">Portfolio</a></li>
            <li><a href="history.jsp">History</a></li>
            <li><a href="transactions.jsp">Transactions</a></li>
            <li><a href="wallet.jsp">Wallet</a></li>
        </ul>
    </nav>
    <body>
        <h1>Stock Portfolio</h1>
        <div id="chartContainer" style="height: 300px; width: 100%;"></div>
        <table>
            <thead>
            <tr>
                <td>Ticker Symbol</td>
                <td>Current Price</td>
                <td>Change In Price</td>
                <td>Shares</td>
                <td>Current Value</td>
                <td>Date Bought</td>
            </tr>
            </thead>
        <tbody>
            <%
				try {
				    String url       = "jdbc:mysql://localhost:3306/mysql";
				    String user      = "root";
				    String password  = "root";
				
				    Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				    conn = DriverManager.getConnection(url, user, password);
				} catch(SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				   	System.out.println(e.getMessage());
				}
				
				String portfolioQuery = "SELECT tickerSymbol, ROUND(priceBoughtAt, 2) as price, ROUND(shares, 2) as shares, dateBought FROM finance.stocks";
				try (Statement stmt = conn.createStatement()) {
					ResultSet rs = stmt.executeQuery(portfolioQuery);
				  	while(rs.next()){
			%> 
					<tr>
						<td><%= rs.getString("tickerSymbol")%></td>
						<td><%= fa.getPrice(rs.getString("tickerSymbol")) %>
			            <td><%= fa.getPrice(rs.getString("tickerSymbol")) - rs.getDouble("price")%></td>
			            <td><%= rs.getDouble("shares")%></td>
			            <td><%= fa.getPrice(rs.getString("tickerSymbol"))*rs.getDouble("shares") %></td>
			            <td><%= rs.getDate("dateBought")%></td>
			        </tr>
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

        </tbody>
        </table>
    </body>
    </div>
</html>
