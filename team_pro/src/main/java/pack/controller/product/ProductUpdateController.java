package pack.controller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pack.model.product.ProductDao;
import pack.model.product.ProductDto;

@Controller
public class ProductUpdateController {
	@Autowired
	private ProductDao productDao;
	
	@GetMapping("update")
	public String edit(@RequestParam("product_id")String product_id, Model model) {
		// 수정 대상 자료 읽기
		ProductDto dto = productDao.detail(product_id);
		
		model.addAttribute("data", dto);
		
		return "productupdate";
	}
	
	@PostMapping("update")
	public String editProcess(ProductBean bean, Model model) {
			boolean b = productDao.update(bean);
			if(b) {
				return "redirect:productlist";
			}else {
				return "redirect:error";
			}
	}
	
	
}
