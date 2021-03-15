package listener;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import model.dao.OrderDAO;
import model.dao.ProductDAO;
import model.dao.UserDAO;
import model.dto.UserDTO;

//서버 실행시 시작되는 초기화 클래스
@WebListener
public class ContextLoaderListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			ServletContext sc = sce.getServletContext();

			InitialContext initialContext = new InitialContext();
			DataSource ds = (DataSource) initialContext.lookup("java:comp/env/jdbc/blackup");

			ProductDAO productDAO = new ProductDAO();
			productDAO.setDataSource(ds);

			UserDAO userDAO = new UserDAO();
			userDAO.setDataSource(ds);

			OrderDAO orderDAO = new OrderDAO();
			orderDAO.setDataSource(ds);

			// 연결 테스트 출력
//			 UserDTO userDTO = userDAO.selectOne("wind2104");
//			 System.out.println(userDTO.getUserID());
//			 System.out.println(userDTO.getUserRating());
//			 System.out.println(userDTO.getUserPassword());
//			 System.out.println(userDTO.getUserPasswordHash());
//			 System.out.println(userDTO.getUserName());
//			 System.out.println(userDTO.getUserID());
//			 System.out.println(userDTO.getUserAddress());
//			 System.out.println(userDTO.getUserPhone());
//			 System.out.println(userDTO.getUserSex());

			// 서버 실행시 DAO 객체 저장
			sc.setAttribute("productDAO", productDAO);
			sc.setAttribute("userDAO", userDAO);
			sc.setAttribute("orderDAO", orderDAO);

		} catch (Exception e) {

		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
}
