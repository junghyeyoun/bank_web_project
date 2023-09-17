package pack.model.review;

import lombok.Data;

@Data
public class ReviewDto {
	private String review_id, customer_id, product_id, rating, review_date;
}
