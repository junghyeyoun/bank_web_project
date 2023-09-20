package pack.model.review;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import pack.controller.review.ReviewBean;

@Mapper
public interface ReviewMappingInterface {
	// 리뷰 등록
	@Insert("INSERT INTO reviews (product_id, rating, comment, review_date, rimage)\r\n"
			+ "VALUES (#{product_id}, #{rating}, #{comment}, #{review_date} ,#{rimage})")
	int insertReview(ReviewBean bean);
	
	// 리뷰 목록 보기
	// products table의 model,brand를 가져오기 위해 join 사용
	@Select("select a.product_id, review_id, model, brand, rating, comment, review_date from reviews a left outer join products b on a.product_id = b.product_id  order by review_id desc")
	List<ReviewDto> selectAll();
	
	// 해당 리뷰 자세히 보기
	@Select("select a.product_id, review_id, model, brand, rating, comment, review_date, rimage from reviews a left outer join products b on a.product_id = b.product_id where review_id = #{review_id}")
	ReviewDto selectOne(String review_id);
	
	// 총 리뷰 수 구하기
	@Select("select count(*) from reviews")
	int totalCnt();
	
	// 리뷰 수정
	
	// 리뷰 삭제
	@Delete("delete from reviews where review_id=#{review_id}")
	int deleteReview(String review_id);
	
}
