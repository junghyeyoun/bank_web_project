package pack.controller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pack.model.product.ProductDao;

@Controller
public class ProductDeleteController {
	@Autowired
	private ProductDao productDao;
	
	@GetMapping("delete")
	public String del(@RequestParam("product_id")String product_id) {
		// 비밀번호 비교 추가하기
		if(productDao.delete(product_id))
			return "redirect:productlist";
		else
			return "redirect:error";
	}
}
