package pack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pack.model.ProductDao;
import pack.model.ProductDto;

@Controller
public class ProductController {
	@Autowired ProductDao productDao;
	
	@GetMapping("productlist")
	public String showProductList(Model model) {
		List<ProductDto> plist = productDao.selectAll();
		model.addAttribute("products",plist);
		return "productlist";
	}
	
	@GetMapping("showproduct")
	public String showproduct(@RequestParam("product_id") String product_id, Model model) {
		model.addAttribute("data", productDao.detail(product_id));
		return "productdetail";
	}
}
