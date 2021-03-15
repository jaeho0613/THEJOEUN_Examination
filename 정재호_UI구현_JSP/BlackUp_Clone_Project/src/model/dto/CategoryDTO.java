package model.dto;

public class CategoryDTO {

	int pdID;
	String cgName;
	String cgType;

	public int getPdID() {
		return pdID;
	}

	public CategoryDTO setPdID(int pdID) {
		this.pdID = pdID;
		return this;
	}

	public String getCgName() {
		return cgName;
	}

	public CategoryDTO setCgName(String cgName) {
		this.cgName = cgName;
		return this;
	}

	public String getCgType() {
		return cgType;
	}

	public CategoryDTO setCgType(String cgType) {
		this.cgType = cgType;
		return this;
	}
}
