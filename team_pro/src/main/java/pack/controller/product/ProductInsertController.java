package pack.controller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import pack.model.product.ProductDao;

@Controller
public class ProductInsertController {
	@Autowired
	private ProductDao productDao;
	
	@GetMapping("insert")
	public String insertform() {
		return "productinsert";
	}
	
	@PostMapping("insert")
	public String insertProcess(ProductBean bean) {
		boolean b = productDao.insert(bean);
		if(b) {
			return "redirect:/productlist";
		} else {
			return "redirect:/error";
		}
	}
}
