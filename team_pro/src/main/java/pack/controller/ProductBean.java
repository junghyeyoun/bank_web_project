package pack.controller;

import lombok.Data;

@Data
public class ProductBean {
	private String product_id, category_id, brand, model, price, stock_quantity,
	description, release_date, specifications, warranty_period, weight, dimensions;
}
