package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.sql.DataSource;
import model.dto.UserDTO;
import model.standard.Userable;

public class UserDAO implements Userable {

	DataSource ds;

	// 데이터 소스 의존성 주입
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	// 유저 데이터 가져오기
	@Override
	public UserDTO selectOne(String userID) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		UserDTO userDTO = new UserDTO();

		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement("select * from user where userid = ?");
			stmt.setString(1, userID);
			rs = stmt.executeQuery();
			rs.next();

			userDTO.setUserID(rs.getString("userID")).setUserAddress(rs.getString("userAddress"))
					.setUserRating(rs.getInt("userRating")).setUserPassword(rs.getString("userPassword"))
					.setUserPasswordHash(rs.getString("userPasswordHash")).setUserName(rs.getString("userName"))
					.setUserPhone(rs.getString("userPhone")).setUserSex(rs.getString("userSex"));

			return userDTO;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// 유저 데이터 작성
	@Override
	public int insert(UserDTO userDTO) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement("insert into user values(?, ?, ?, ?, ?, ?, ? ,?)");

			stmt.setString(1, userDTO.getUserID());
			stmt.setInt(2, userDTO.getUserRating());
			stmt.setString(3, userDTO.getUserPassword());
			stmt.setString(4, userDTO.getUserPasswordHash());
			stmt.setString(5, userDTO.getUserName());
			stmt.setString(6, userDTO.getUserAddress());
			stmt.setString(7, userDTO.getUserPhone());
			stmt.setString(8, userDTO.getUserSex());

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

	// 유저 데이터 삭제
	@Override
	public int delete(String userID) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement("delete from user where userID = ?");
			stmt.setString(1, userID);

			return stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {

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

		return -1;
	}

	// 유저 데이터 업데이트
	@Override
	public int update(UserDTO userDTO) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(
					"update user set userRating = ?, userPassword = ?, userPasswordHash = ?, userName = ?,"
							+ " userAddress = ?, userPhone = ?, userSex = ? where userID = ?");

			stmt.setInt(1, userDTO.getUserRating());
			stmt.setString(2, userDTO.getUserPassword());
			stmt.setString(3, userDTO.getUserPasswordHash());
			stmt.setString(4, userDTO.getUserName());
			stmt.setString(5, userDTO.getUserAddress());
			stmt.setString(6, userDTO.getUserPhone());
			stmt.setString(7, userDTO.getUserSex());
			stmt.setString(8, userDTO.getUserID());
			return stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("UserDAO Update Error!");
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e2) {

			}
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
		return -1;
	}

	// 로그인 기능 메소드
	@Override
	public int login(String userID, String userPassword) {
		Connection conn; // DB에 접근하는 객체
		PreparedStatement stmt; // SQL문을 실행하는 객체를 반환
		ResultSet rs; // DB의 SQL결과를 담을 수 있는 객체
		String SQL = "SELECT userPassword FROM USER WHERE userID = ?";

		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, userID);
			rs = stmt.executeQuery();
			if (rs.next()) {
				if (rs.getString(1).contentEquals(userPassword))
					return 1; // 로그인 성공
				else
					return 0; // 비밀번호 불일치
			}
			return -1; // 아이디가 없음
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -2; // 데이터 베이스 오류
	}

}
