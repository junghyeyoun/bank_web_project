package pack.contoller.review;

import lombok.Data;

@Data
public class ReviewBean {
	private String review_id, customer_id, product_id, rating, comment, review_date;
}
