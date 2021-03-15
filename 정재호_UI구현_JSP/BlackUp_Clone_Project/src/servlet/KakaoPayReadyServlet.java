package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.dto.KakaoPayReadDTO;
import model.dto.OrdersDTO;

@SuppressWarnings("serial")
@WebServlet("/payment/ready")
public class KakaoPayReadyServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		BufferedReader in = null;

		try {
			
			// 저장소 객체 생성
			ServletContext sc = this.getServletContext();
			
			// JSON 파싱 객체
			Gson gson = new Gson();

			// null 검사
			String pdName = req.getParameter("pdName") != null ? req.getParameter("pdName") : "null";
			String userID = req.getParameter("userID") != null ? req.getParameter("userID") : "null";
			String pdPrice = req.getParameter("pdPrice") != null ? req.getParameter("pdPrice") : "0";
			String pdId = req.getParameter("pdId") != null ? req.getParameter("pdId") : "0";
			String size = req.getParameter("size") != null ? req.getParameter("size") : "null";
			String color = req.getParameter("color") != null ? req.getParameter("color") : "null";

			// 파라미터 출력
			 System.out.println(pdName);
			 System.out.println(pdPrice);
			 System.out.println(pdId);
			 System.out.println(size);
			 System.out.println(color);
			 System.out.println(userID);

			// 주문 데이터 객체 생성
			OrdersDTO order = new OrdersDTO()
					.setPdName(pdName)
					.setPdPrice(Integer.parseInt(pdPrice))
					.setPdID(Integer.parseInt(pdId))
					.setSize(size)
					.setColor(color)
					.setUserID(userID);

			// API 통신 헤더 설정
			URL url = new URL("https://kapi.kakao.com/v1/payment/ready");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "KakaoAK 0508fa19018d6c40d663a44da80b9f3b");
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			conn.setDoInput(true);
			conn.setDoOutput(true);

			// API 통신에 필요한 Parameter
			Map<String, String> params = new HashMap<String, String>();
			params.put("cid", "TC0ONETIME"); // 가맹점 번호
			params.put("partner_order_id", "test"); // 주문 번호
			params.put("partner_user_id", userID); // 주문 유저 번호
			params.put("item_name", pdName); // 상품 이름
			params.put("quantity", "1"); // 수량
			params.put("total_amount", pdPrice); // 가격
			params.put("vat_amount", "0"); // 부과세
			params.put("tax_free_amount", "0"); // 비과세
			params.put("approval_url", "http://localhost:8080/BlackUp_Clone_Project/approval"); // 결제 성공시 반환 Url
			params.put("fail_url", "http://localhost:8080/BlackUp_Clone_Project/fail"); // 결제 실패시 반환 Url
			params.put("cancel_url", "http://localhost:8080/BlackUp_Clone_Project/cancel"); // 결제 취소시 반환 Url

			// 전달할 Parameter 객체 -> String형으로 수정 (통신 전달시 Bytes로 변환하기 위해서)
			String string_params = new String();
			for (Map.Entry<String, String> elem : params.entrySet()) {
				string_params += (elem.getKey() + "=" + elem.getValue() + "&");
			}

			// 전달 Parameter를 전송하여 API 통신
			conn.getOutputStream().write(string_params.getBytes());
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			// 결제 준비 정보 객체 생성
			KakaoPayReadDTO ready = gson.fromJson(in, KakaoPayReadDTO.class);

			// 최상위 저장소에 저장
			sc.setAttribute("ready", ready);
			sc.setAttribute("order", order);

			// 결제창으로 이동
			resp.sendRedirect(ready.getNext_redirect_pc_url());

		} catch (Exception e) {
			System.out.println("KakaoPayReadyServlet Error!");
			e.printStackTrace();
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}
}
