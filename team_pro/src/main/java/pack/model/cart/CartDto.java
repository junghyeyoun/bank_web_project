package pack.model.cart;

import lombok.Data;

@Data
public class CartDto {
	// cart 테이블의 칼럼
	private int cart_id, product_id, quantity;
	
	// products 테이블의 칼럼
	private String model, brand;
	private int price;
	
}
