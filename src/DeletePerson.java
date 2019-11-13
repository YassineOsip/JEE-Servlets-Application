

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;




/**
 * Servlet implementation class AddPerson
 */

@WebServlet("/DeletePerson")
public class DeletePerson extends HttpServlet {
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
	
	  public int delPerson(String cin) {

		  int a = 0;
		   Statement st = null;
		   try {
			   
			 String statement = " DELETE FROM  persons WHERE cin = '" + cin + "'";
			 System.out.println(statement);
				st = conn.createStatement();
				a = st.executeUpdate(statement);
				
		   }catch(SQLException e ) {
			   e.getMessage();
		   }
		   
		  return a;
	  }
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public DeletePerson() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = Connect(url, user, password);
		String cin = request.getParameter("cin");
		//addPerson(fname, lname, age,cin);
		delPerson(cin);
		//boolean stat = Close(conn);
		response.sendRedirect(request.getContextPath() + "/deletePerson.html");
		Close(conn);

		/*
		if(!stat || cin.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/deletePersonp.html");
		}
		else{
		    response.sendRedirect(request.getContextPath() + "/deletePersonn.html");
		}*/

	}

}
