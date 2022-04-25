import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddFundsServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse resp) {
		System.out.println("Hey this kinda works");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse resp) {
		System.out.println("Hey this works");
	}
}
