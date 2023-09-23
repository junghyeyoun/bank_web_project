package pack.wishlist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pack.wishlist.model.WishlistDao;

@Controller
public class WishlistInsertController {
	@Autowired
	private WishlistDao dao;
	
	@GetMapping("wishlistinsert")
	public String insert(WishlistBean bean, @RequestParam("productid") int productid) {
		boolean b = dao.wishinsert(bean);
		if(b) {
			return "redirect:wishlist";
		} else {
			return "redirect:error";
		}
	}
}
