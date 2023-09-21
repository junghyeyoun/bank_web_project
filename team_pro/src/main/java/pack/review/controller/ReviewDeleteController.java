package pack.review.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pack.review.model.ReviewDao;


@Controller
public class ReviewDeleteController {
	 private Logger logger = LoggerFactory.getLogger(this.getClass());

	    @Autowired
	    private ReviewDao reviewDao;

	    @GetMapping("/reviewdelete")
	    public String delete(@RequestParam("reviewId") int reviewId, @RequestParam("page") String page, RedirectAttributes redirectAttributes) {
	        try {
	            if (!confirmDeletion()) {
	                // 사용자가 삭제를 취소한 경우
	                return "redirect:/productlist?page=" + page;
	            }

	            boolean result = reviewDao.delete(reviewId);
	            if (result) {
	                return "redirect:/productlist?page=" + page;
	            } else {
	                redirectAttributes.addFlashAttribute("errorMessage", "상품 삭제에 실패했습니다.");
	                return "redirect:/error";
	            }
	        } catch (Exception e) {
	            logger.error("상품 삭제 중 오류 발생: " + e.getMessage());
	            redirectAttributes.addFlashAttribute("errorMessage", "상품 삭제 중 오류 발생했습니다.");
	            return "redirect:/error";
	        }
	    }

	    private boolean confirmDeletion() {
	        // 여기에서 사용자에게 정말 삭제할 것인지 물어보는 로직을 추가
	        // 사용자가 확인한 경우 true를 반환하고, 취소한 경우 false를 반환
	        // 예를 들어, JavaScript를 사용하여 브라우저에서 확인 대화상자를 표시할 수 있습니다.
	        
	        // 여기서는 간단하게 true로 가정
	        return true;
	    }
}
