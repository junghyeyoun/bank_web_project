package pack.product.model;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import pack.product.controller.ProductBean;

@Repository
public class ProductDao {
	// log로 확인
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProductMappingInterface mappingInterface;

	// 전체 상품 읽기
	public List<ProductDto> selectAll() {
		List<ProductDto> list = mappingInterface.selectAll();
		logger.info("읽은 상품 수 : " + list.size());
		return list;
	}

	// 해당 상품 상세 보기
	public ProductDto detail(int productid) {
		ProductDto dto = mappingInterface.selectOne(productid);
		return dto;
	}

	public int totalCnt() {
		return mappingInterface.totalCnt();
	}

	// 상품 찾기
	public List<ProductDto> search(ProductBean bean) {
		List<ProductDto> list = mappingInterface.searchList(bean);
		logger.info("검색 반환된 레코드 수 : " + list.size());
		return list;
	}

	// 상품 등록
	public void insert(ProductBean bean) {
	   
	    mappingInterface.insertProduct(bean);
	}

	// 상품 수정
	public boolean update(ProductBean bean) {
		boolean b = false;
		int re = mappingInterface.updateProduct(bean);
		if (re > 0)
			b = true;
		return b;
	}
	
	// 상품이미지 삭제
	public boolean pimagedelete(int productid) {
		boolean b = false;
		int re = mappingInterface.pimagedelete(productid);
		if (re > 0)
			b = true;
		return b;
	}
	// 상세이미지 삭제
	public boolean dimagedelete(int productid) {
		boolean b = false;
		int re = mappingInterface.dimagedelete(productid);
		if (re > 0)
			b = true;
		return b;
	}
	// 상품 삭제
	public boolean delete(int productid) {
		boolean b = false;
		int re = mappingInterface.deleteProduct(productid);
		if (re > 0)
			b = true;
		return b;
	}
	// 특정 상품에 관한 리뷰 전체 삭제
	public boolean deletereviewfromproduct(int productid) {
		boolean b = false;
		int re = mappingInterface.deletereviewfromproduct(productid);
		if (re > 0)
			b = true;
		return b;
	}
}
