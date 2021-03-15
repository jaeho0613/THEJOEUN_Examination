package model.standard;

import model.dto.UserDTO;

public interface Userable {

	// 유저 추가 (회원가입)
	int insert(UserDTO userDTO);

	// 유저 삭제 (회원탈퇴)
	int delete(String userID);

	// 유저 선택 (회원 정보 확인)
	UserDTO selectOne(String userID);

	// 유저 수정 (회원 정보 수정)
	int update(UserDTO userDTO);

	// 유저 로그인
	int login(String userID, String userPassword);
}