package model.dto;

import java.util.ArrayList;

public class ProductDTO {

	int pdID;
	int pdPrice;
	String pdName;
	int pdGPA5;
	int pdGPA4;
	int pdGPA3;
	int pdGPA2;
	int pdGPA1;

	ArrayList<ColorSetDTO> colorSetList;
	ArrayList<ImagePathDTO> imagePathList;
	ArrayList<CategoryDTO> categoryList;
	ArrayList<SizeSetDTO> sizeSetList;

	public ArrayList<ColorSetDTO> getColorSetList() {
		return colorSetList;
	}

	public ProductDTO setColorSetList(ArrayList<ColorSetDTO> colorSetList) {
		this.colorSetList = colorSetList;
		return this;
	}

	public ArrayList<ImagePathDTO> getImagePathList() {
		return imagePathList;
	}

	public ProductDTO setImagePathList(ArrayList<ImagePathDTO> imagePathList) {
		this.imagePathList = imagePathList;
		return this;
	}

	public ArrayList<CategoryDTO> getCategoryList() {
		return categoryList;
	}

	public ProductDTO setCategoryList(ArrayList<CategoryDTO> categoryList) {
		this.categoryList = categoryList;
		return this;
	}

	public ArrayList<SizeSetDTO> getSizeSetList() {
		return sizeSetList;
	}

	public ProductDTO setSizeSetList(ArrayList<SizeSetDTO> sizeSetList) {
		this.sizeSetList = sizeSetList;
		return this;
	}

	public int getPdID() {
		return pdID;
	}

	public ProductDTO setPdID(int pdID) {
		this.pdID = pdID;
		return this;
	}

	public int getPdPrice() {
		return pdPrice;
	}

	public ProductDTO setPdPrice(int pdPrice) {
		this.pdPrice = pdPrice;
		return this;
	}

	public String getPdName() {
		return pdName;
	}

	public ProductDTO setPdName(String pdName) {
		this.pdName = pdName;
		return this;
	}

	public int getPdGPA5() {
		return pdGPA5;
	}

	public ProductDTO setPdGPA5(int pdGPA5) {
		this.pdGPA5 = pdGPA5;
		return this;
	}

	public int getPdGPA4() {
		return pdGPA4;
	}

	public ProductDTO setPdGPA4(int pdGPA4) {
		this.pdGPA4 = pdGPA4;
		return this;
	}

	public int getPdGPA3() {
		return pdGPA3;
	}

	public ProductDTO setPdGPA3(int pdGPA3) {
		this.pdGPA3 = pdGPA3;
		return this;
	}

	public int getPdGPA2() {
		return pdGPA2;
	}

	public ProductDTO setPdGPA2(int pdGPA2) {
		this.pdGPA2 = pdGPA2;
		return this;
	}

	public int getPdGPA1() {
		return pdGPA1;
	}

	public ProductDTO setPdGPA1(int pdGPA1) {
		this.pdGPA1 = pdGPA1;
		return this;
	}
}
