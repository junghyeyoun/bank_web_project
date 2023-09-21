package pack.review.model;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pack.review.controller.ReviewBean;

@Mapper
public interface ReviewMappingInterface {
	// 리뷰 등록
	@Insert("INSERT INTO reviews (productId, rating, comment, reviewDate, rimage)\r\n"
			+ "VALUES (#{productId}, #{rating}, #{comment}, #{reviewDate} ,#{rimage})")
	int insertReview(ReviewBean bean);
	
	// 리뷰 목록 보기
	// products table의 model,brand를 가져오기 위해 join 사용
	@Select("select a.productId, reviewId, model, brand, rating, comment, reviewDate from reviews a left outer join products b on a.productId = b.productId  order by reviewId desc")
	List<ReviewDto> selectAll();
	
	// 해당 리뷰 자세히 보기
	@Select("select a.productId, reviewId, model, brand, rating, comment, reviewDate, rimage from reviews a left outer join products b on a.productId = b.productId where reviewId = #{reviewId}")
	ReviewDto selectOne(int reviewId);
	
	// 총 리뷰 수 구하기 (페이징 처리를 위해)
	@Select("select count(*) from reviews")
	int totalCnt();
	
	// 리뷰 수정
	@Update("update reviews set rating=#{rating}, comment=#{comment}, reviewDate=#{reviewDate}, rimage=#{rimage} where reviewId=#{reviewId}")
	int updateReview(ReviewBean bean);
	
	// 리뷰 삭제
	@Delete("delete from reviews where reviewId=#{reviewId}")
	int deleteReview(int reviewId);
	
	// rimage 삭제(null 값으로 update 하기)
	@Update("update reviews set rimage=null where reviewId = #{reviewId}")
	int rimagedelete(int reviewId);
	
}
