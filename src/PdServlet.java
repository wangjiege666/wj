import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class PdServlet extends HttpServlet {	
    public static Map<String,String> userMap=new HashMap<String, String>();//存放用户信息
    /**
      * 初始化Map数据
     */
     @Override
     public void init() throws ServletException {
         userMap.put("mashuai", "mashuai");
         userMap.put("fanliyang","fanliyang");
         userMap.put("yangyijiang", "yangyijiang");
     }    
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
		 response.setCharacterEncoding("UTF-8");//设置字符编码
	        response.setContentType("text/html");//设置响应类型
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
    			ResultSet rs = (ResultSet) sta.executeQuery("select * from yh");
    			while (rs.next()) {
    				yhname = rs.getString(1);
    			}
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		
	        PrintWriter out = response.getWriter();
	         HttpSession session=request.getSession();//获取session对象
	        /**
	          * 业务逻辑：先判断session中有没有登录信息,如果有则直接返回成功页面，
	         * 如果没有，返回提示页面，提示进入登录页面登录。
	         */
	         String uName=(String) session.getAttribute("userName");//从session中取出用户名
	         
	         String pass=(String) session.getAttribute("pass");
	           
	        if(yhname.equals(request.getParameter("userName"))){
	        	 session.setAttribute("userName", userName);//保存用户信息到session中
                 //输出成功页面
                  request.getRequestDispatcher("cg").forward(request, response);
	             //输出成功页面
	         }else{
	                    request.getRequestDispatcher("out").forward(request, response);
	                 }
	                    
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}
