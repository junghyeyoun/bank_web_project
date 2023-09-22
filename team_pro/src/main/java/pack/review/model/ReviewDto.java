package pack.review.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ReviewDto {
	private String rating, comment, reviewdate, rimage, model, brand;
	private MultipartFile rimagePath;
	private int productid , reviewid;
}
