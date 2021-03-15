package model.dto;

public class SizeSetDTO {

	int pdID;
	String size;

	public int getPdID() {
		return pdID;
	}

	public SizeSetDTO setPdID(int pdID) {
		this.pdID = pdID;
		return this;
	}

	public String getSize() {
		return size;
	}

	public SizeSetDTO setSize(String size) {
		this.size = size;
		return this;
	}
}
