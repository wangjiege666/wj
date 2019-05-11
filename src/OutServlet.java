import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;




public class OutServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	
	  String yhname; 
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");// 设置字符编码
		response.setContentType("text/html");// 设置响应类型
		 String userName=request.getParameter("userName");//获取用户名		 
         String password=request.getParameter("password");//获取密码
         
         try {
 			Class.forName("com.mysql.jdbc.Driver");
 		} catch (ClassNotFoundException e) {
 			e.printStackTrace();
 		}

 		try {
 			Connection con = (Connection) DriverManager.getConnection(
 					"jdbc:mysql://127.0.0.1:3306/yhdb", "root", "1");
 			Statement sta = (Statement) con.createStatement();
 			sta.executeUpdate("insert yh values('"+userName+"','"+password+"')");
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
 		

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.println("注测成功！请<a href='dl'>点击此处</a>进入登录页面<br/>");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.setCharacterEncoding("UTF-8");// 设置字符编码
//		response.setContentType("text/html");// 设置响应类型
//
//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
//		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
//		out.println("<HTML>");
//		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
//		out.println("  <BODY>");
//		out.println("注测成功！请  <a href='dl'>点击此处</a><br/>");
//		out.println("  </BODY>");
//		out.println("</HTML>");
//		out.flush();
//		out.close();
	}

}
