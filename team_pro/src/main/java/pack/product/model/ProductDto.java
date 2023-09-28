package pack.product.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductDto {
	private int productid, reviewid, customernum;
	private String category, brand, model, price, stockquantity, pimage, dimage, state;
// pimage, dimge는 이미지 파일명

 private MultipartFile pimagePath, dimagePath;
// 이미지 조회경로
}
