package model.standard;

import java.util.ArrayList;
import java.util.List;

import model.dto.CategoryDTO;
import model.dto.ColorSetDTO;
import model.dto.ImagePathDTO;
import model.dto.ProductDTO;
import model.dto.SizeSetDTO;

public interface Productable {
	
	// 특정 카테고리 타입으로 상품 리스트 가져오기
	ArrayList<ProductDTO> getCategoryTypeByProductList(String cgType);

	// 특정 상품 하나 가져오기
	ProductDTO getSelectOneProduct(int pdID);

	// 특정 카테고리 타입 리스트 가져오기
	ArrayList<String> getCategoryTypeList(String cgName);

	// 상품 전체 가져오기
	ArrayList<ProductDTO> getProductList();

	// 특정 카테고리 이름 상품 리스트 가져오기
	ArrayList<ProductDTO> getCategoryByProductList(String cgName);

	// 특정 상품 가져오기 - (상품 테이블만)
	int getProductID(String pdName);

	// 특정 상품 가져오기 - (상품 테이블만)
	String getProductName(int pdId);

	// 특정 상품의 정보 하나를 가져오기 - pdID로
	Object selectOne(Set set, int pdID);

	// 특정 상품의 정보 하나를 가져오기 - pdName
	Object selectOne(Set set, String pdName);

	// 특정 상품 삭제
	int delete(int pdID);

	// 어떤 셋을 가져올지
	public enum Set {
		product, colorset, sizeset, category, imagepath
	}

	// 특정 상품의 사이즈셋 가져오기 - 아이디로
	// SizeSetDTO selectOneProductSizeSet(int pdID) throws Exception;

	// 특정 상품의 컬러셋 가져오기 - 아이디로
	// ColorSetDTO selectOneProductColorSet(int pdID) throws Exception;

	// 특정 상품의 이미지 경로 가져오기 - 아이디로
	// ImagePathDTO selectOneProductImagePath(int pdID) throws Exception;

	// 특정 상품의 카테고리 가져오기 - 아이디로
	// CategoryDTO selectOneProductCategory(int pdID) throws Exception;

	//////////////////////////////////////////////////////////////////

	// 특정 상품의 사이즈셋 가져오기 - 상품명으로
	// SizeSetDTO selectOneProductSizeSet(String pdName) throws Exception;

	// 특정 상품의 컬러셋 가져오기 - 상품명으로
	// ColorSetDTO selectOneProductColorSet(String pdName) throws Exception;

	// 특정 상품의 이미지 경로 가져오기 - 상품명으로
	// ImagePathDTO selectOneProductImagePath(String pdName) throws Exception;

	// 특정 상품의 카테고리 가져오기 - 상품명으로
	// CategoryDTO selectOneProductCategory(String pdName) throws Exception;

	// 특정 상품 수정 *(상품, 컬러, 사이즈, 이미지, 카테고리의 변동 또한 생각해야함.)
	// int update(int pdID) throws Exception;
}
