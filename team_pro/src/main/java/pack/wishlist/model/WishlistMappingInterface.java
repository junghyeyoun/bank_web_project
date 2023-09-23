package pack.wishlist.model;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import pack.wishlist.controller.WishlistBean;

@Mapper
public interface WishlistMappingInterface {
	// 찜 등록
	@Insert("insert into wishlist (productid) values (#{productid})")
	int insertWishlist(WishlistBean bean);
	
	// 찜목록 보기
	@Select("select * from wishlist a inner join products b on a.productid = b.productid order by wishlistid")
	List<WishlistDto> selectAll();
	
	// 총 찜 수 구하기 (페이징 처리를 위해)
	@Select("select count(*) from wishlist")
	int totalCnt();
	
	// 찜 삭제
	@Delete("delete from wishlist where wishlistid = ${wishlistid}")
	int deletewishlist(int wishlistid);
}
