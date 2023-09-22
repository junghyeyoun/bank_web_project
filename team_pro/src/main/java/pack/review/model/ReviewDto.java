package pack.review.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ReviewDto {
	private String nickname, title, rating, comment, reviewdate, rimage, model, brand;
	private MultipartFile rimagePath;
	private int rproductid , reviewid;
	private int gnum, onum; // 답글을 위한 칼럼
}
