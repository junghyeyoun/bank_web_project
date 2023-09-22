package pack.review.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pack.review.model.ReviewDao;


@Controller
public class ReviewDeleteController {
	 private Logger logger = LoggerFactory.getLogger(this.getClass());

	    @Autowired
	    private ReviewDao reviewDao;
	    
	    @PostMapping("/reviewdelete")
	    public String delete(@RequestParam("reviewid") int reviewid) {
			// review를 참고하는 다른 테이블있다면 거기서도 특정 reveiwid의 데이터들을 삭제한 뒤 진행해야함
	    	reviewDao.delete(reviewid);
			return "redirect:/reviewlist";
		}
}
