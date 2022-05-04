package com.javaproj.usermanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaproj.usermanagement.dao.ProductsDAO;
import com.javaproj.usermanagement.model.Products;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProductsDAO ProductsDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
    	this.ProductsDAO = new ProductsDAO();
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
		String action = request.getServletPath();

		try {

			switch (action) {

			case "/new":
				showProductForm(request, response);
				break;

			case "/insert":
				insertProduct(request, response);
				break;

			case "/edit":
				showEditForm(request, response);
				break;

			case "/delete":
				deleteProduct(request, response);
				break;

			case "/update":
				updateProduct(request, response);
				break;

			default:
				listProducts(request, response);
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void showProductForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("product-form.jsp");
		dispatcher.forward(request, response);
	}
	
//	insert product
	private void insertProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String color = request.getParameter("color");
		String price = request.getParameter("price");
		Products newProduct = new Products(name, description, color, price);
		ProductsDAO.insertProduct(newProduct);
		response.sendRedirect("products_list");
	}

//	delete product
	private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		ProductsDAO.deleteProduct(id);
		response.sendRedirect("products_list");
	}
	
//	edit
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Products existingProduct = ProductsDAO.selectProduct(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("product-form.jsp");
		request.setAttribute("product", existingProduct);
		dispatcher.forward(request, response);

	}

//	update product
	private void updateProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String color = request.getParameter("color");
		String price = request.getParameter("price");

		Products product = new Products(id, name, description, color, price);
		ProductsDAO.updateProduct(product);
		response.sendRedirect("products_list");
	}

//	default
	private void listProducts(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {
		List<Products> listProducts = ProductsDAO.selectAllProducts();
		request.setAttribute("listProducts", listProducts);
		RequestDispatcher dispatcher = request.getRequestDispatcher("product-list.jsp");
		dispatcher.forward(request, response);
	}

}
