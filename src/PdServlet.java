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
    public static Map<String,String> userMap=new HashMap<String, String>();//����û���Ϣ
    /**
      * ��ʼ��Map����
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
		 response.setCharacterEncoding("UTF-8");//�����ַ�����
	        response.setContentType("text/html");//������Ӧ����
	        String userName=request.getParameter("userName");//��ȡ�û���
            String password=request.getParameter("password");//��ȡ����
                 
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
	         HttpSession session=request.getSession();//��ȡsession����
	        /**
	          * ҵ���߼������ж�session����û�е�¼��Ϣ,�������ֱ�ӷ��سɹ�ҳ�棬
	         * ���û�У�������ʾҳ�棬��ʾ�����¼ҳ���¼��
	         */
	         String uName=(String) session.getAttribute("userName");//��session��ȡ���û���
	         
	         String pass=(String) session.getAttribute("pass");
	           
	        if(yhname.equals(request.getParameter("userName"))){
	        	 session.setAttribute("userName", userName);//�����û���Ϣ��session��
                 //����ɹ�ҳ��
                  request.getRequestDispatcher("cg").forward(request, response);
	             //����ɹ�ҳ��
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
