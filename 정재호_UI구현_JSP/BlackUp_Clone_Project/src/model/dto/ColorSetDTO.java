package model.dto;

public class ColorSetDTO {

	int pdID;
	String color;
	String colorCode;

	public String getColorCode() {
		return colorCode;
	}

	public ColorSetDTO setColorCode(String colorCode) {
		this.colorCode = colorCode;
		return this;
	}

	public int getPdID() {
		return pdID;
	}

	public ColorSetDTO setPdID(int pdID) {
		this.pdID = pdID;
		return this;
	}

	public String getColor() {
		return color;
	}

	public ColorSetDTO setColor(String color) {
		this.color = color;
		return this;
	}
}
