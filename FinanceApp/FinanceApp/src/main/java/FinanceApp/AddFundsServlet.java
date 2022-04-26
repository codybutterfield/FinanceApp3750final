package FinanceApp;

import java.io.IOException;
import java.math.BigDecimal;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddFundsServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		FinanceApp fa = new FinanceApp();
		int currentWallet = fa.getCurrentWallet();
		currentWallet = currentWallet / 100;
		String queryString = "wallet.jsp?currWalletDisplay=" + currentWallet;
		response.sendRedirect(queryString);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int funds = Integer.parseInt(request.getParameter("addFunds"));
		FinanceApp fa = new FinanceApp();
		fa.addFunds(funds);
		doGet(request, response); 
	}
}
