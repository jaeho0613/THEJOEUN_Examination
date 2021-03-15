package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ProductDAO;
import model.dto.ProductDTO;

@SuppressWarnings("serial")
@WebServlet("/product")
public class ProductServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ServletContext sc = this.getServletContext();

		ProductDAO productDAO = (ProductDAO) sc.getAttribute("productDAO");
		int pdID = Integer.parseInt(req.getParameter("pdID"));

		ProductDTO productDTO = productDAO.getSelectOneProduct(pdID);

		resp.setContentType("text/html; charset=utf-8");

		req.setAttribute("product", productDTO);

		RequestDispatcher rd = req.getRequestDispatcher("/jsp/form/ProductForm.jsp");
		rd.include(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);

	}

}
