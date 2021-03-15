package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.OrderDAO;
import model.dto.KakaoPayReadDTO;
import model.dto.OrdersDTO;

@SuppressWarnings("serial")
@WebServlet("/approval")
public class KakaoPayApprovalServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");

		try {

			// 저장소 객체 생성
			ServletContext sc = this.getServletContext();

			// 저장소에서 결제 정보 가져오기
			KakaoPayReadDTO ready = (KakaoPayReadDTO) sc.getAttribute("ready");

			if (ready != null) {
				
				// 데이터 베이스에 저장할 DAO 객체
				OrderDAO orderDAO = (OrderDAO) sc.getAttribute("orderDAO");

				// 저장소에서 주문 정보 가져오기
				OrdersDTO order = (OrdersDTO) sc.getAttribute("order");

				// 결제에 필요한 pgToken값 가져오기
				String pgToken = req.getParameter("pg_token");

				// API 통신 헤더 설정
				URL url = new URL("https://kapi.kakao.com/v1/payment/approve");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Authorization", "KakaoAK 0508fa19018d6c40d663a44da80b9f3b");
				conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
				conn.setDoInput(true);
				conn.setDoOutput(true);

				// API 통신 Parameter 설정
				Map<String, String> params = new HashMap<String, String>();
				params.put("cid", "TC0ONETIME"); // 준비 단계에서 적은 가맹점 코드
				params.put("tid", ready.getTid()); // 준비 단계에서 얻은 Tid
				params.put("partner_order_id", "test"); // 준비 단계에서 적었던 주문 번호
				params.put("partner_user_id", order.getUserID()); // 준비 단계에서 적었던 유저 아이디
				params.put("pg_token", pgToken); // Parameter로 얻은 pgToken

				// 전달할 Parameter 객체 -> String형으로 수정 (통신 전달시 Bytes로 변환하기 위해서)
				String string_params = new String();
				for (Map.Entry<String, String> elem : params.entrySet()) {
					string_params += (elem.getKey() + "=" + elem.getValue() + "&");
				}

				// 전달 Parameter를 전송하여 API 통신
				conn.getOutputStream().write(string_params.getBytes());
				// BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

				// ORDER 데이터 베이스에 추가
				orderDAO.insert(order);

				// 결제 성공시 Popup창 닫기
				PrintWriter wr = resp.getWriter();
				wr.println("<script>");
				wr.println("alert('결제 성공');");
				wr.println("window.close();");
				wr.println("</script>");
				wr.close();
			}
		} catch (Exception e) {
			System.out.println("KakaoPayApprovalServlet Error!");
			e.printStackTrace();
		}
	}
}
