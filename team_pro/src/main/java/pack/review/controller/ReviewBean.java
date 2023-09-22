package pack.review.controller;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ReviewBean {
	private String rating, comment, reviewdate, rimage,  model, brand;
	private int productid , reviewid;
	private MultipartFile rimagePath;
	public void setReviewdate() {
		LocalDate localDate = LocalDate.now();
		int year = localDate.getYear();
		int month = localDate.getMonthValue();
		int day = localDate.getDayOfMonth();
		this.reviewdate = year + "-" + month + "-" + day;
	}
}
