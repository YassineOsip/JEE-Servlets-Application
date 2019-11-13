

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

@WebServlet("/AddPerson")
public class AddPerson extends HttpServlet {
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
	
	public  int  addPerson(String fname, String lname,String age,String cin) {
		  
		  int a = 0;
		   try {
			   
			 String statement = " INSERT INTO  persons ( fname, lname, age,cin)VALUES ('"+fname+"','"+lname+"'," + age + ",'" + cin + "'"+ ")"; 
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
	
    public AddPerson() {
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
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String age = request.getParameter("age");
		String cin = request.getParameter("cin");
		addPerson(fname, lname, age,cin);
		boolean stat = Close(conn);

		if(!stat && fname.isEmpty() && lname.isEmpty() && age.isEmpty() && cin.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/addPersonn.html");
		}
		else{
		    response.sendRedirect(request.getContextPath() + "/addPersonp.html");
		}

	}

}
