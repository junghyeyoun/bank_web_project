package pack.contoller.review;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ReviewBean {
	private String review_id, customer_id, product_id, rating, comment, review_date, rimage;
	private MultipartFile rimagePath;
}
