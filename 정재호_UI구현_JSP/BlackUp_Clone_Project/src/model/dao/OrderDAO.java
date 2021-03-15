package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import model.dto.OrdersDTO;
import model.standard.Orderable;

public class OrderDAO implements Orderable {

	DataSource ds;

	// 의존성 주입
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public int insert(OrdersDTO order) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement("insert into orders values(null, ?, ?, ?, ?, ?, ?)");

			stmt.setString(1, order.getUserID());
			stmt.setInt(2, order.getPdID());
			stmt.setString(3, order.getPdName());
			stmt.setInt(4, order.getPdPrice());
			stmt.setString(5, order.getColor());
			stmt.setString(6, order.getSize());

			return stmt.executeUpdate(); // 성공하면 1반환

		} catch (Exception e) {
			System.out.println("UserDAO insert 오류입니다.");
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e2) {

			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {

			}
		}

		return -1; // 실패하면 -1반환
	}

}
