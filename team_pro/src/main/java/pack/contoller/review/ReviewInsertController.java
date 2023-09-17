package pack.contoller.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import pack.model.review.ReviewDao;

@Controller
public class ReviewInsertController {
	@Autowired
	private ReviewDao reviewDao;
	
	@GetMapping("reviewinsert")
	public String insertform(ReviewBean bean) {
		return "reviewinsert";
	}
	
	@PostMapping("reviewinsert")
	public String insertProcess (ReviewBean bean) {
		boolean b = reviewDao.reviewinsert(bean);
		if(b) {
			return "redirect:/reviewlist?page=1";  // 추가 후 목록 보기
		}else {
			return "redirect:/error";
		}
	}
}
