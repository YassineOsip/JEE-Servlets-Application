

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Servlet implementation class ShowPersons
 */
@WebServlet("/ShowPersons")
public class ShowPersons extends HttpServlet {
	String url = "jdbc:mysql://localhost:3306/test";
	String user = "root";
	String password = "";
	static Connection conn = null;
	Statement st = null;
	public static Connection Connect(String url, String user,String passwd) {
	try {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		conn = DriverManager.getConnection(url,user,passwd);
		//System.out.println("conn OK");
	} catch (SQLException e) {
		e.printStackTrace();
		
	}
	return conn;
}

	public static boolean Close(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
}
	
	  public ArrayList<String> readPersons() {
		  Statement st = null;
		  ArrayList<String> perms = new ArrayList<String>();
		   try {
			   String statement = "SELECT * FROM persons" ;
			   st = conn.createStatement();
			  ResultSet a = st.executeQuery(statement);
			   
			  while(a.next()) {
				  perms.add(a.getString("id"));
				  perms.add(a.getString("fname"));
				  perms.add(a.getString("lname"));
				  perms.add(a.getString("age"));
				  perms.add(a.getString("cin"));
			  }
		   } catch (SQLException e )
		   {
			   e.getMessage();
		   }
		return perms;
	  }
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */

	  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Connection conn = Connect(url, user, password);
		ArrayList<String> persons = readPersons();
		out.println("<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<meta charset=\"ISO-8859-1\">\r\n" + 
				"<title>persons crud</title>\r\n" + 
				"<link href=\"https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.5/css/bulma.min.css\" rel=\"stylesheet\">\r\n" + 
				"<link href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css\" rel=\"stylesheet\">\r\n" + 
				"</head>\r\n" + 
				"<body>");
		out.println("	<section class=\"hero is-info\">\r\n" + 
				"	\r\n" + 
				"	<div class=\"hero-head\">\r\n" + 
				"	<div class=\"container\">\r\n" + 
				"    	<h1 class=\"title is-family-code is-capitalized has-text-dark\" style=\"margin-top:20px;\">PERSONS CRUD APPLICATION IN JEE SERVLETS <i class=\"fa fa-coffee\" aria-hidden=\"true\"></i> </h1>\r\n" + 
				"		<h3 class=\"subtitle has-text-dark is-italic is-family-code\">Show Persons</h3>\r\n" + 
				"		<a class=\"button is-dark is-small is-outlined is-pulled-right is-family-code\" href=\"http://localhost:8080/webo/index.html\">Back To The Main Menu<i style=\"margin-left:5px;\" class=\"fas fa-bars fa-spin \"></i></a>\r\n" + 
				"		<br><br>\r\n" + 
				"	</div>\r\n" + 
				"	</div>\r\n" + 
				"	</section>\r\n" + 
				"	<br>\r\n" + 
				"	<div class=\"hero-body\"> \r\n" + 
				"	<div class=\"columns is-centered\">\r\n" + 
				"	<div class=\"column is-5-tablet is-4-desktop is-3-widescreen\">\r\n" + 
				"        <h1 class=\"title is-family-code is-capitalized has-text-dark\" style=\"margin-top:20px;text-align: center;\">Persons List</h1>\r\n" + 
				"        <table class=\"table is-bordered is-striped is-narrow is-hoverable is-fullwidth\">\r\n" + 
				"            <thead class=\"is-family-code\">\r\n" + 
				"                <th>ID</th>\r\n" + 
				"                <th>FIRST NAME</th>\r\n" + 
				"                <th>LAST NAME</th>\r\n" + 
				"                <th>AGE</th>\r\n" + 
				"                <th>CIN</th>\r\n" + 
				"            </thead>\r\n");
		
		for(int i=0; i<persons.size();i+=5) {
			out.println("<tbody class=\"is-family-code \" >");
			out.println("<td>"+persons.get(i) + "</td>");
			out.println("<td>"+persons.get(i+1) +"</td>");
			out.println("<td>"+persons.get(i+2) +"</td>");
			out.println("<td>"+persons.get(i+3) +"</td>");
			out.println("<td>"+persons.get(i+4) +"</td>");
			out.print("</tbody>");
		}
		
		out.println("        </table>\r\n" + 
				"	</div>\r\n" + 
				"	</div>\r\n" + 
				"	</div>\r\n" + 
				"</body>\r\n" + 
				"</html>");
		//System.out.print(persons);
		Close(conn);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
