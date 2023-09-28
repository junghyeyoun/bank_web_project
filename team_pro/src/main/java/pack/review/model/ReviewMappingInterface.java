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
	@Insert("INSERT INTO reviews (rproductid, nickname, title,rating, comment, reviewdate, rimage)\r\n"
			+ "VALUES (#{rproductid}, #{nickname}, #{title},#{rating}, #{comment}, #{reviewdate} ,#{rimage})")
	int insertReview(ReviewBean bean);

	// 리뷰 목록 보기 - 사용자별추가하는게 좋을 것 같음
	// products table의 model,brand를 가져오기 위해 join 사용
	@Select("select rproductid, reviewid, nickname, title ,model, brand, rating, comment, reviewdate from reviews left outer join products on rproductid = productid order by reviewid desc")
	List<ReviewDto> selectAll();

	// 해당 리뷰 자세히 보기
	@Select("select rproductid, reviewid, nickname, title, model, brand, rating, comment, reviewdate, rimage from reviews left outer join products on rproductid = productid where reviewid = #{reviewid}")
	ReviewDto selectOne(int reviewid);
	
	// 상품별 리뷰 보기
	@Select("select rproductid, reviewid, nickname, title, model, brand, rating, comment, reviewdate, rimage from reviews left outer join products on rproductid = productid where rproductid = #{rproductid} order by reviewid desc")
	List<ReviewDto> selectPart(int productid); 
	
	// 상품별 리뷰 평균 보기
	@Select("SELECT AVG(rating) AS average_rating\r\n"
			+ "FROM reviews\r\n"
			+ "WHERE rproductid = #{rpoductid};\r\n"
			+ "")
	int avgreviewstar(int rpoductid);
	
	// 총 리뷰 수 구하기 (페이징 처리를 위해)
	@Select("select count(*) from reviews")
	int totalCnt();

	// 리뷰 수정
	@Update("update reviews set nickname=#{nickname}, title=#{title}, rating=#{rating}, comment=#{comment}, reviewdate=#{reviewdate}, rimage=#{rimage} where reviewId=#{reviewid}")
	int updateReview(ReviewBean bean);

	// 리뷰 삭제
	@Delete("delete from reviews where reviewid=#{reviewid}")
	int deleteReview(int reviewid);

	// rimage 삭제(null 값으로 update 하기)
	@Update("update reviews set rimage=null where reviewid = #{reviewid}")
	int rimagedelete(int reviewid);
	
	
}
