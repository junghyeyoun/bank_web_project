package pack.controller.product;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
@Component
public class ProductBean {
	private int product_id;
	private String category_id, brand, model, price, stock_quantity, description, release_date,
			specifications, warranty_period, weight, dimensions, pimage, dimage;
	// pimage, dimge는 이미지 파일명
	

	private String searchName, searchValue;
	

	private MultipartFile pimagePath, dimagePath;
	// 이미지 조회경로

}
