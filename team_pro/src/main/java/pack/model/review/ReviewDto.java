package pack.model.review;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ReviewDto {
	private String review_id, customer_id, product_id, rating, comment, review_date, rimage;
	private MultipartFile rimagePath;
}
