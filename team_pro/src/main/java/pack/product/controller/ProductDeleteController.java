package pack.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pack.product.model.ProductDao;
import pack.review.model.ReviewDao;

@Controller
public class ProductDeleteController {
	@Autowired
	private ProductDao productDao;

	@PostMapping("/productdelete")
	public String delete(@RequestParam("productId") int productid) {
		// product를 참고하는 다른 테이블있다면 거기서도 특정 productId의 데이터들을 삭제한 뒤 진행해야함
		productDao.deletereviewfromproduct(productid); // reviews가 products의 외래키를 참조하기 때문에 특정 productId에 해당하는 reviews을 먼저 지우고 products을 지워야함
		productDao.delete(productid);
		return "redirect:/productlist";
	}

}
