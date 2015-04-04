sAimport java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Simple extends HttpServlet 
{
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
 {

	response.setContentType("text/html;charset=UTF-8");	
	PrintWriter out = response.getWriter();
	Connection con = null;
	ResultSet resultSet = null;      
	String uname = request.getParameter("uname");
	String pwd = request.getParameter("pwd");
	try
        {

		Class.forName("com.mysql.jdbc.Driver").newInstance();
		String url = "jdbc:mysql://localhost";
		final String USER = "root";
		final String PASS = "root";
		con = DriverManager.getConnection(url,USER,PASS);
		String query = null;
		query = "SELECT uname,pwd from table_name";
		PreparedStatement statement = con.prepareStatement(query);
		resultSet = statement.executeQuery();
		String db_uname="";
		String db_pwd="";           
		while (resultSet.next()) 
		{
			db_uname = resultSet.getString("uname")
			db_pwd = resultSet.getString("pwd");                                       
			if( (uname.equals(db_uname)) && (pwd.equals(db_pwd)))
			{
				out.write("SUCCESS");
			} 
		}

	}
	 catch (Exception e) 
	{
		e.printStackTrace();
	} 
	finally
	 {
		out.close();
	 } 
 }
}

