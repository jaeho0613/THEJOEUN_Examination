package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import model.dao.UserDAO;

@SuppressWarnings("serial")
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	HttpSession session;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 로그인 하는 뷰로 이동
		resp.setContentType("text/html; charset=utf-8");
		RequestDispatcher rd = req.getRequestDispatcher("/jsp/form/LogInForm.jsp");
		rd.include(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		// DataSource를 가져오기 위해 ServletContext 변수 생성
		ServletContext sc = req.getServletContext();

		// req.getAttribute로 사용자의 입력값 받아오기.
		// UserDAO를 이용하여 로그인 검사
		UserDAO userDAO = (UserDAO) sc.getAttribute("userDAO");

		resp.setContentType("text/html; charset=utf-8");
		int result = userDAO.login(req.getParameter("userID"), req.getParameter("userPassword"));

		if (result == 1) { // 로그인 성공 시
			session = req.getSession();
			session.setAttribute("userID", (req.getParameter("userID"))); // 로그인 성공한 회원에게 세션 부여 구문
			PrintWriter wr = resp.getWriter();
			wr.println("<script>");
			wr.println("alert('로그인 성공하였습니다.');");
			wr.println("location.href = 'main'");
			wr.println("</script>");
			wr.close();

		} else if (result == 0) { // 비밀번호가 틀렸을 경우
			PrintWriter wr = resp.getWriter();
			wr.println("<script>");
			wr.println("alert('비밀번호가 틀렸습니다.');");
			wr.println("location.href = 'login'");
			wr.println("</script>");
			wr.close();
		} else if (result == -1) { // 아이디가 존재하지 않은 경우
			PrintWriter wr = resp.getWriter();
			wr.println("<script>");
			wr.println("alert('존재하지 않은 아이디입니다.');");
			wr.println("location.href = 'login'");
			wr.println("</script>");
			wr.close();
		} else if (result == -2) { // DB 오류
			PrintWriter wr = resp.getWriter();
			wr.println("<script>");
			wr.println("alert('데이터베이스 오류가 발생했습니다.');");
			wr.println("location.href = 'login'");
			wr.println("</script>");
			wr.close();
		} else {
			PrintWriter wr = resp.getWriter();
			wr.println("<script>");
			wr.println("alert('오류.');");
			wr.println("location.href = 'login'");
			wr.println("</script>");
			wr.close();
		}
	}
}
