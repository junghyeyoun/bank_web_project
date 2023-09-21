package pack.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pack.product.model.ProductDao;

@Controller
public class ProductDeleteController {
	@Autowired
	private ProductDao productDao;

	@PostMapping("productdelete")
	public String delete(RedirectAttributes rdat, @RequestParam("productId") int productId,
			@RequestParam("page") String page) {
		productDao.delete(productId);
		// Redirect로 페이지 이동 시 전송값을 숨겨서 보내는 역할을 하는 클래스 : RedirectAttributes
		rdat.addFlashAttribute("msg", "삭제");
		return "redirect:productlist?page=" + page;
	}

}
