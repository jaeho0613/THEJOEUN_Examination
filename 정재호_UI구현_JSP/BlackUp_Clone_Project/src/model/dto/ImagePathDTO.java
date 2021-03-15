package model.dto;

public class ImagePathDTO {

	int pdID;
	String imgPath;
	String imgName;

	public int getPdID() {
		return pdID;
	}

	public ImagePathDTO setPdID(int pdID) {
		this.pdID = pdID;
		return this;
	}

	public String getImgPath() {
		return imgPath;
	}

	public ImagePathDTO setImgPath(String imgPath) {
		this.imgPath = imgPath;
		return this;
	}

	public String getImgName() {
		return imgName;
	}

	public ImagePathDTO setImgName(String imgName) {
		this.imgName = imgName;
		return this;
	}
}
