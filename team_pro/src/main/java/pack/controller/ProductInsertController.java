package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import pack.model.ProductDao;

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
		// Spring 에서 client ip 가져오는 법
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		String ip = req.getHeader("X-FORWARDED-FOR");
		if (ip == null)
			ip = req.getRemoteAddr();
		// ------------------------------------------------	
		boolean b = productDao.insert(bean);
		if(b) {
			return "redirect:/productlist";
		} else {
			return "redirect:/error";
		}
	}
}
