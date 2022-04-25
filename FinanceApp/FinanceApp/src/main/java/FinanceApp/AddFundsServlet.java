package FinanceApp;

import java.io.IOException;
import java.math.BigDecimal;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddFundsServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		FinanceApp fa = new FinanceApp();
		BigDecimal currentWallet = new BigDecimal(fa.getCurrentWallet());
		currentWallet = currentWallet.divide(BigDecimal.valueOf(100));
		String queryString = "wallet.jsp?currWalletDisplay=" + currentWallet;
		response.sendRedirect(queryString);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		BigDecimal funds = new BigDecimal(request.getParameter("addFunds"));
		FinanceApp fa = new FinanceApp();
		fa.addFunds(funds);
		doGet(request, response); 
	}
}
