package pack.review.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pack.product.model.ProductDto;
import pack.review.model.ReviewDao;
import pack.review.model.ReviewDto;
import pack.reviewreply.model.ReviewReplyDao;
import pack.reviewreply.model.ReviewReplyDto;

@Controller
public class ReviewListController {
	@Autowired
	ReviewDao reviewDao;
	@Autowired
	private ReviewReplyDao replyDao;
	
	private int tot; // 전체 레코드 수
	private int plist = 10; // 페이지 당 행 수
	private int pagesu; // 전체 페이지 수
	
	public ArrayList<ReviewDto> getListdata(ArrayList<ReviewDto> list, int page) {
		ArrayList<ReviewDto> result = new ArrayList<ReviewDto>();

		int start = (page - 1) * plist; // 0, 10, 20, ...

		int size = plist <= list.size() - start ? plist : list.size() - start;

		for (int i = 0; i < size; i++) {
			result.add(i, list.get(start + i));
		}
		return result;
	}

	// 총 페이지 수 얻기
	public int getPageSu() {
		tot = reviewDao.totalCnt();
		pagesu = tot / plist;
		if (tot % plist > 0)
			pagesu += 1;
		return pagesu;
	}
	
	// 리뷰 리스트 보기
	@GetMapping("reviewlist")
	public String showReviewList(@RequestParam(name = "page", defaultValue = "1") int page, Model model) {
	    // paging 처리
	    int spage = 0;
	    try {
	        spage = page;
	    } catch (Exception e) {
	        spage = 1;
	    }
	    if (page <= 0)
	        spage = 1;

	    ArrayList<ReviewDto> list = (ArrayList<ReviewDto>) reviewDao.selectAll();
	    ArrayList<ReviewDto> result = getListdata(list, spage);

	    model.addAttribute("list", result); 
	    model.addAttribute("pagesu", getPageSu());
	    model.addAttribute("page", spage);
	    return "reviewlist";
	}
	
	// 선택한 리뷰 자세히 보기
	@GetMapping("showreview")
	public String showreview(@RequestParam("reviewid") int reviewid, @RequestParam("page") int page, Model model) {
		model.addAttribute("data", reviewDao.detail(reviewid));
		model.addAttribute("page", page);
		// 댓글 조회
		ArrayList<ReviewReplyDto> list = (ArrayList<ReviewReplyDto>) replyDao.selectAll(reviewid);
		model.addAttribute("list", list);
		
		return "reviewdetail";
	}
	
	// 상품별 리뷰 보기
	@GetMapping("selectpart")
	public String selectpart(@RequestParam(name = "page", defaultValue = "1") int page, @RequestParam("rproductid") int rproductid, Model model) {
	    // paging 처리
	    int spage = 0;
	    try {
	        spage = page;
	    } catch (Exception e) {
	        spage = 1;
	    }
	    if (page <= 0)
	        spage = 1;

	    ArrayList<ReviewDto> list = (ArrayList<ReviewDto>) reviewDao.selectAll();
	    ArrayList<ReviewDto> result = getListdata(list, spage);

	    model.addAttribute("list", result); 
	    model.addAttribute("pagesu", getPageSu());
	    model.addAttribute("page", spage);
	    return "reviewproductlist";
	}
	
}
