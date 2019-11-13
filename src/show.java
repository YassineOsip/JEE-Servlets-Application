

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class show
 */
@WebServlet("/show")
public class show extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public show() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h1>Votre Nom:</h1><h1>Votre Prenom:</h1><h1>Votre Sex:</h1>");
		String ip = request.getRemoteAddr();
		String serverName = request.getServerName();
		int port = request.getServerPort();
		System.out.println(ip);
		System.out.println(serverName);
		System.out.println(port);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String gendre = request.getParameter("gend");
		String comment = request.getParameter("comment");
		String fonction = request.getParameter("fonction");
		String[] loisir = request.getParameterValues("loisir");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h1>Votre Nom:</h1>" + nom + "<h1>Votre Prenom:</h1>"+ prenom+ "<h1>Votre Sex:</h1>" +  gendre+ "<h1>Votre commentaire:</h1>" + comment +" <h1>Votre fonction:</h1>" + fonction+"<h1>Tes Loisires:</h1>");
		for(int i =0 ;i<loisir.length;i++ ) {
			out.println(loisir[i]);
			out.println("<br>");
		}
		
		
		
		
	}

}
