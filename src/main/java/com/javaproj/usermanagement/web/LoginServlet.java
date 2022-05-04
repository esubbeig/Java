package com.javaproj.usermanagement.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaproj.usermanagement.dao.UsersDAO;
import com.javaproj.usermanagement.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsersDAO UsersDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
    	this.UsersDAO = new UsersDAO();
    }
    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			checkUser(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	fetch user
	private void checkUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		User existedUser = new User(email, password);
		boolean status = UsersDAO.fetchUser(existedUser);
		System.out.println(status);
		if (status) {
			HttpSession session = request.getSession(true);
			session.setAttribute("email", email);
			session.setAttribute("password", password);
			response.getWriter().append("Login successfull");
			response.sendRedirect("product-list.jsp");
		} else {
			String errorMessage = "Login Failed";
			HttpSession session = request.getSession(true);
			session.setAttribute("errorMessage", errorMessage);
			response.sendRedirect("login.jsp");
		}
	}

}
