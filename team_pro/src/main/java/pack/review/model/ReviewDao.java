package pack.review.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pack.product.controller.ProductBean;
import pack.product.model.ProductDto;
import pack.review.controller.ReviewBean;

@Repository
public class ReviewDao {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ReviewMappingInterface reviewMappingInterface;
	
	// 리뷰 등록
	public boolean reviewinsert(ReviewBean bean) {
		boolean b = false;
		int re = reviewMappingInterface.insertReview(bean);
		if(re>0) b = true;
		return b;
	}
	
	// 전체 리뷰 읽기
	public List<ReviewDto> selectAll() {
		List<ReviewDto> list = reviewMappingInterface.selectAll();
		logger.info("읽은 리뷰 수 : " + list.size());
		return list;
	}
	
	// 해당 리뷰 상세 보기
	public ReviewDto detail(int reviewId) {
		ReviewDto dto = reviewMappingInterface.selectOne(reviewId);
		return dto;
	}

	public int totalCnt() {
		return reviewMappingInterface.totalCnt();
	}
	
	// 리뷰 수정
	public boolean update(ReviewBean bean) {
		boolean b = false;
		int re = reviewMappingInterface.updateReview(bean); 
		if (re > 0)
			b = true;
		return b;
	}
	
	// 리뷰 삭제
	public boolean delete(int reviewId) {
		boolean b = false;
		int re = reviewMappingInterface.deleteReview(reviewId);
		if (re > 0)
			b = true;
		return b;
	}
	
	// rimage 삭제
	public boolean rimagedelete(int reviewId) {
		boolean b = false;
		int re = reviewMappingInterface.rimagedelete(reviewId);
		if (re > 0)
			b = true;
		return b;
	}
}
