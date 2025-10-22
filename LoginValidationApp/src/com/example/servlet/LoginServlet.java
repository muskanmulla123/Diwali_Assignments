package com.example.servlet;
import javax.servlet.http.*;
import jakarta.servlet.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/userdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
    
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                 
    	 String username = request.getParameter("username");
         String password = request.getParameter("password");
         
         try {
             // Load JDBC driver (optional with modern drivers)
             Class.forName("com.mysql.cj.jdbc.Driver");

             try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                  PreparedStatement stmt = conn.prepareStatement(
                     "SELECT 1 FROM users WHERE username=? AND password=?")) {

                 stmt.setString(1, username);
                 stmt.setString(2, password);

                 try (ResultSet rs = stmt.executeQuery()) {
                     if (rs.next()) {
                         request.setAttribute("username", username);
                         request.getRequestDispatcher("success.jsp").forward(request, response);
                     } else {
                         request.getRequestDispatcher("error.jsp").forward(request, response);
                     }
                 }
             }catch(Exception e)
         {
        	 throw new ServletException(e);
         }
    	
    	
    }
}
