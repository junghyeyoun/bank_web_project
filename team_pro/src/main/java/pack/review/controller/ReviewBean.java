package pack.review.controller;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ReviewBean {
	private String rating, comment, reviewDate, rimage,  model, brand;
	private int productId , reviewId;
	private MultipartFile rimagePath;
	public void setReviewDate() {
		LocalDate localDate = LocalDate.now();
		int year = localDate.getYear();
		int month = localDate.getMonthValue();
		int day = localDate.getDayOfMonth();
		this.reviewDate = year + "-" + month + "-" + day;
	}
}
