package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ProductDAO;
import model.dto.CategoryDTO;
import model.dto.ColorSetDTO;
import model.dto.ImagePathDTO;
import model.dto.ProductDTO;
import model.dto.SizeSetDTO;

@SuppressWarnings("serial")
@WebServlet("/category")
public class CategoryServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			// DataSource를 가져오기 위해 ServletContext 변수 생성
			ServletContext sc = this.getServletContext();

			// 카테고리 이름 (대문자로 변경)
			String cgName = req.getParameter("name").toUpperCase();
			String cgType = req.getParameter("type");

			// ContextLoaderListener에서 생성한 DAO 가져오기
			ProductDAO productDAO = (ProductDAO) sc.getAttribute("productDAO");
			ArrayList<ProductDTO> productList = productDAO.getCategoryTypeByProductList(cgType);
			ArrayList<String> cgTypeList = productDAO.getCategoryTypeList(cgName);

			// 가져온 ProductList 출력
			for (ProductDTO productDTO : productList) {
				System.out.println();
				System.out.println("-------------------------------------");
				System.out.println("------------product------------------");
				System.out.println("pdID : " + productDTO.getPdID());
				System.out.println("pdPrice : " + productDTO.getPdPrice());
				System.out.println("pdName : " + productDTO.getPdName());
				System.out.println("pdGPA1 : " + productDTO.getPdGPA1());
				System.out.println("pdGPA2 : " + productDTO.getPdGPA2());
				System.out.println("pdGPA3 : " + productDTO.getPdGPA3());
				System.out.println("pdGPA4 : " + productDTO.getPdGPA4());
				System.out.println("pdGPA5 : " + productDTO.getPdGPA5());
				System.out.println("-------------------------------------");
				System.out.println("------------colorSet------------------");
				for (ColorSetDTO colorSet : productDTO.getColorSetList()) {
					System.out.println("pdID : " + colorSet.getPdID());
					System.out.println("color : " + colorSet.getColor());
				}
				System.out.println("-------------------------------------");
				System.out.println("------------imagepath------------------");
				for (ImagePathDTO imagePath : productDTO.getImagePathList()) {
					System.out.println("pdID : " + imagePath.getPdID());
					System.out.println("imagePath : " + imagePath.getImgName());
					System.out.println("imageName : " + imagePath.getImgPath());
				}
				System.out.println("-------------------------------------");
				System.out.println("------------category------------------");
				for (CategoryDTO category : productDTO.getCategoryList()) {
					System.out.println("pdID : " + category.getPdID());
					System.out.println("cgType : " + category.getCgType());
					System.out.println("cgName : " + category.getCgName());
				}
				System.out.println("-------------------------------------");
				System.out.println("------------sizeSet------------------");
				for (SizeSetDTO sizeSet : productDTO.getSizeSetList()) {
					System.out.println("pdID : " + sizeSet.getPdID());
					System.out.println("size : " + sizeSet.getSize());
				}
				System.out.println("-------------------------------------");
				System.out.println();
			}

			// JSP에서 사용하기 위해 request 저장소에 저장
			req.setAttribute("productList", productList);
			req.setAttribute("cgName", cgName);
			req.setAttribute("cgType", cgType);
			req.setAttribute("cgTypeList", cgTypeList);

			resp.setContentType("text/html; charset=utf-8");

			// View (화면에 보이는 디자인)은 JSP에게 위임
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/form/CategoryForm.jsp");

			// include : View 출력후 제어권이 다시 돌아옴.
			rd.include(req, resp);

			// forward : View 출력후 제어권이 다시 돌아오지 않음.
			// rd.forward(req, resp);

		} catch (Exception e) {
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
