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
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsersDAO UsersDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrationServlet() {
		this.UsersDAO = new UsersDAO();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			registerUser(request, response);
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

//	insert user
	private void registerUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		User newUser = new User(username, email, password);
		boolean status = UsersDAO.saveUser(newUser);
		if (status) {
//			out.println("Registered successfully");
			response.sendRedirect("login.jsp");
		} else {
			String errorMessage = "User already exist!";
			HttpSession regSession = request.getSession();
			regSession.setAttribute("RegError", errorMessage);
			response.sendRedirect("registration.jsp");

		}

	}

}
