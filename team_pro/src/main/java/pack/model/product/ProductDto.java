package pack.model.product;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductDto {
	private String product_id, category_id, brand, model, price, stock_quantity, description, release_date,
	specifications, warranty_period, weight, dimensions, pimage, dimage;
// pimage, dimge는 이미지 파일명

 private MultipartFile pimagePath, dimagePath;
// 이미지 조회경로
}
