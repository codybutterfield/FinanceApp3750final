<%@ page import="FinanceApp.FinanceApp" %>
<%@ page import="DatePrice.DatePrice" %>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException" %>
<%@page import="java.io.IOException" %>
<%@page import="java.util.List" %>
<style><%@include file="/WEB-INF/css/finance.css"%></style>

<script type="text/javascript">
	window.onload = function() {
		<%
			String stock1 = "TWTR";
			String stock2 = "TSLA";
			String stock3 = "MSFT";
			int stYear = 2020, stMonth = 1, stDay = 1;
			String interval = "daily";
		%>
		
		var datePoints1= []
		<%
			FinanceApp fApp = new FinanceApp();
			List<DatePrice> historyInfo = fApp.getHistoryDatePrice(stock1, stYear, stMonth, stDay, interval);
			int price, year, month, day;
			for(DatePrice h:historyInfo){
				price = h.getPriceInt();
				year = h.getYear();
				month = h.getMonth();
				day = h.getDay();
				%>
				datePoints1.push({x: new Date(<%=year%>,<%=month%>,<%=day%>), y: <%=price%>})
				<%
			}
		
		%>
		var datePoints2= []
		<%
			historyInfo = fApp.getHistoryDatePrice(stock2, stYear, stMonth, stDay, interval);
			for(DatePrice h:historyInfo){
				price = h.getPriceInt();
				year = h.getYear();
				month = h.getMonth();
				day = h.getDay();
				%>
				datePoints2.push({x: new Date(<%=year%>,<%=month%>,<%=day%>), y: <%=price%>})
				<%
			}
		
		%>
		var datePoints3= 	[]
		<%
			historyInfo = fApp.getHistoryDatePrice(stock3, stYear, stMonth, stDay, interval);
			for(DatePrice h:historyInfo){
				price = h.getPriceInt();
				year = h.getYear();
				month = h.getMonth();
				day = h.getDay();
				%>
				datePoints3.push({x: new Date(<%=year%>,<%=month%>,<%=day%>), y: <%=price%>})
				<%
			}
		
		%>
		
	
	    var chart = new CanvasJS.Chart("chartContainer", {
	        backgroundColor: 'transparent',
	        animationEnabled: true,
	        theme: "light2",
	        title: {
	            text: "Stock History",
	            fontColor: 'white'
	        },
	        axisX: [{
	            title: "Date",
	            titleFontColor: "white",
	            lineColor: "white",
	            tickColor: "white",
	            labelFontColor: "white"
	
	        }],
	        axisY: [{
	            title: "Stock Price",
	            titleFontColor: "white",
	            tickColor: "white",
	            labelFontColor: "white"
	        }],
	        legend:{
	        fontSize: 20,
	        fontFamily: "tamoha",
	        fontColor: "White"      
	         },
	        data: [{
	                type: "line",
	                name: "<%=stock1%>",
	                showInLegend: true,
	                markerSize: 0,
	                yValueFormatString: "$#,###k",
	                dataPoints: datePoints1
	            },
	            {
	                type: "line",
	                name: "<%=stock2%>",
	                labelFontColor: "white",
	                showInLegend: true,
	                markerSize: 0,
	                yValueFormatString: "$#,###k",
	                dataPoints: datePoints2
	            },
	            {
	                type: "line",
	                name: "<%=stock3%>",
	                showInLegend: true,
	                markerSize: 0,
	                yValueFormatString: "$#,###k",
	                dataPoints: datePoints3
	            }
	        ]
	    });
	    chart.render();
	
	}
</script>
<script type="text/javascript" src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>

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
        <div id="chartContainer" style="height: 300px; width: 100%;"></div>
    </body>
    </div>
</html>

