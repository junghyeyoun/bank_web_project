package pack.controller.product;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	public String insertProcess(@ModelAttribute("bean") ProductBean bean, @RequestParam("pimage") MultipartFile pimage, @RequestParam("pimagePath") String pimagePath) throws Exception{

		// 판매자만 가능 추가해야함
	
	   productDao.insert(bean, pimage);
	
		   return "redirect:/productlist";
	 
	}
}
