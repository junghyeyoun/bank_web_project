package pack.reviewreply.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pack.reviewreply.model.ReviewReplyDao;
import pack.reviewreply.model.ReviewReplyDto;

@Controller
public class ReviewReplyController {
	@Autowired
	private ReviewReplyDao replyDao;
	
	// 댓글등록
	@PostMapping("/reviewreplyinsert")
	public String replyiinsertProcess (@RequestParam("reviewno") int reviewno, ReviewReplyBean bean,
			@RequestParam("page") int page, Model model) {
		bean.setReplydate();
		 boolean b =  replyDao.replyinsert(bean);
	        if(b) {
	        	return "redirect:showreview?reviewid=" +reviewno +"&page="+page;
	        } else {
	        	return "redirect:error";
	        }
	}
	
	// 댓글 조회
	@GetMapping("reviewreplyshow")
	public String showReplyList (@RequestParam("reviewno") int reviewno ,Model model) {
		ArrayList<ReviewReplyDto> list = (ArrayList<ReviewReplyDto>) replyDao.selectAll(reviewno);
		model.addAttribute("list", list);
		return "reviewdetail";
	}
	
	// 댓글 수정
	
	
	// 댓글 삭제
}
