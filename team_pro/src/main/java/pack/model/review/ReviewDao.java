package pack.model.review;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pack.contoller.review.ReviewBean;
import pack.model.product.ProductDto;

@Repository
public class ReviewDao {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ReviewMappingInterface reviewMappingInterface;
	
	// 리뷰 등록
	public void reviewinsert(ReviewBean bean) {
		reviewMappingInterface.insertReview(bean);
	}
	
	// 전체 리뷰 읽기
	public List<ReviewDto> selectAll() {
		List<ReviewDto> list = reviewMappingInterface.selectAll();
		logger.info("읽은 상품 수 : " + list.size());
		return list;
	}
	
	// 해당 리뷰 상세 보기
	public ReviewDto detail(String review_id) {
		ReviewDto dto = reviewMappingInterface.selectOne(review_id);
		return dto;
	}

	public int totalCnt() {
		return reviewMappingInterface.totalCnt();
	}
}
