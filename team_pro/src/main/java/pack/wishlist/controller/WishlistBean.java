package pack.wishlist.controller;

import lombok.Data;

@Data
public class WishlistBean {
	private String brand, model, pimage;
	private int wishlistid, productid;
}
