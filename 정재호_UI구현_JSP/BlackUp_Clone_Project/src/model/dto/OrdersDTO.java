package model.dto;

public class OrdersDTO {
	int odID;
	String userID;
	int pdID;
	String pdName;
	int pdPrice;
	String color;
	String size;

	public int getOdID() {
		return odID;
	}

	public OrdersDTO setOdID(int odID) {
		this.odID = odID;
		return this;
	}

	public String getUserID() {
		return userID;
	}

	public OrdersDTO setUserID(String userID) {
		this.userID = userID;
		return this;
	}

	public int getPdID() {
		return pdID;
	}

	public OrdersDTO setPdID(int pdID) {
		this.pdID = pdID;
		return this;
	}

	public String getPdName() {
		return pdName;
	}

	public OrdersDTO setPdName(String pdName) {
		this.pdName = pdName;
		return this;
	}

	public int getPdPrice() {
		return pdPrice;
	}

	public OrdersDTO setPdPrice(int pdPrice) {
		this.pdPrice = pdPrice;
		return this;
	}

	public String getColor() {
		return color;
	}

	public OrdersDTO setColor(String color) {
		this.color = color;
		return this;
	}

	public String getSize() {
		return size;
	}

	public OrdersDTO setSize(String size) {
		this.size = size;
		return this;
	}
}
