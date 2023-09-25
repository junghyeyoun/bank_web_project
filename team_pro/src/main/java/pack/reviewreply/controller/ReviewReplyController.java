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
	public String replyinsertProcess (@RequestParam("reviewno") int reviewno, ReviewReplyBean bean,
			@RequestParam("page") int page, Model model) {
		bean.setReplydate();
		 boolean b =  replyDao.replyinsert(bean);
	        if(b) {
	        	return "redirect:showreview?reviewid=" +reviewno +"&page="+page;
	        } else {
	        	return "redirect:error";
	        }
	}
	
	// 댓글 조회 controller는 reviewlistcontroller에 있음
	
	
	// 댓글 수정
	@PostMapping("/replyupdate")
	public String replyUpdateProcess(@RequestParam("reviewno") int reviewno, @RequestParam("replyid") int replyid,
			@RequestParam("page") int page, @RequestParam("comment") String comment,ReviewReplyBean bean) {
		bean.setComment(comment);
	    boolean updated = replyDao.update(bean);

	    if (updated) {
	        // 수정이 완료된 후, 리뷰 상세 페이지로 리다이렉트합니다.
	        return "redirect:showreview?reviewid=" +reviewno +"&page="+page;
	    } else {
	        return "redirect:/error"; // 수정 실패 시 에러 페이지로 리다이렉트합니다.
	    }
	}

	
	// 댓글 삭제
	@GetMapping("/replydelete")
	public String replydeleteprocess (@RequestParam("replyid") int replyid, @RequestParam("reviewno") int reviewno
			, @RequestParam("page")int page) {
		replyDao.delete(replyid);
		return "redirect:showreview?reviewid=" +reviewno +"&page="+page;
	}
	
}
