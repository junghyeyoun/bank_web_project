package pack.controller.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pack.model.review.ReviewDao;
import pack.model.review.ReviewDto;

@Controller
public class ReviewUpdateController {
	@Autowired
	private ReviewDao reviewDao;
	
	@GetMapping("reviewupdate")
	public String edit(@RequestParam("review_id") int reveiw_id, @RequestParam("page")String page, Model model) {
		// 수정 대상 자료 읽기
		ReviewDto dto = reviewDao.detail(reveiw_id);
		
		model.addAttribute("data", dto);
		model.addAttribute("page", page);
		
		return "reviewupdate";
	}
	
	@PostMapping("reviewupdate")
	public String editProcess(@RequestParam("review_id") int review_id, @RequestParam("page")String page, ReviewBean bean, Model model) {
			boolean b = reviewDao.update(bean);
			if(b) {
				return "redirect:reviewlist";
			}else {
				return "redirect:error";
			}
	}
}
