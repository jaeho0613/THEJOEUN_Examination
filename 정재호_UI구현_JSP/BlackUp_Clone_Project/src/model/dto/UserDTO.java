package model.dto;

public class UserDTO {
	String userID;
	int userRating;
	String userPassword;
	String userPasswordHash;
	String userName;
	String userAddress;
	String userPhone;
	String userSex;

	public String getUserID() {
		return userID;
	}

	public UserDTO setUserID(String userID) {
		this.userID = userID;
		return this;
	}

	public int getUserRating() {
		return userRating;
	}

	public UserDTO setUserRating(int userRating) {
		this.userRating = userRating;
		return this;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public UserDTO setUserPassword(String userPassword) {
		this.userPassword = userPassword;
		return this;
	}

	public String getUserPasswordHash() {
		return userPasswordHash;
	}

	public UserDTO setUserPasswordHash(String userPasswordHash) {
		this.userPasswordHash = userPasswordHash;
		return this;
	}

	public String getUserName() {
		return userName;
	}

	public UserDTO setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public UserDTO setUserAddress(String userAddress) {
		this.userAddress = userAddress;
		return this;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public UserDTO setUserPhone(String userPhone) {
		this.userPhone = userPhone;
		return this;
	}

	public String getUserSex() {
		return userSex;
	}

	public UserDTO setUserSex(String userSex) {
		this.userSex = userSex;
		return this;
	}
}
