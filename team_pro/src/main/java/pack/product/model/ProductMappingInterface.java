package pack.product.model;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pack.product.controller.ProductBean;

@Mapper
public interface ProductMappingInterface {
	// 전체 상품 읽기
	@Select("SELECT * FROM products ORDER BY productid DESC")
	List<ProductDto> selectAll();

	// 해당 상품 상세 보기
	@Select("select * from products where productid=#{productid}")
	ProductDto selectOne(int product_id);

	// 상품 등록
	@Insert("INSERT INTO products (category, brand, model, price, stockquantity,pimage, dimage)\r\n"
			+ "VALUES (#{category}, #{brand}, #{model}, #{price}, #{stockquantity}, #{pimage},#{dimage})")
	int insertProduct(ProductBean bean);

	// 총 상품 수 구하기
	@Select("select count(*) from products")
	int totalCnt();

	// 상품 찾기
	@Select("select * from products where ${searchName} like concat('%', #{searchValue}, '%')")
	List<ProductDto> searchList(ProductBean bean);

	// 상품 수정
	@Update("update products set brand=#{brand}, model=#{model}, price=#{price}, stockquantity=#{stockquantity}, pimage=#{pimage}, dimage=#{dimage}  where productid=#{productid}")
	int updateProduct(ProductBean bean);

	// pimage(상품이미지) 삭제
	@Update("update products set pimage=null where productid = #{productid}")
	int pimagedelete(int productid);

	// dimage(상세이미지) 삭제
	@Update("update products set dimage=null where productid = #{productid}")
	int dimagedelete(int productid);

	// 상품 삭제
	@Delete("delete from products where productid=#{productid}")
	int deleteProduct(int productid);

	// 특정 상품에 관한 리뷰 전체 삭제
	@Delete("delete from reviews where productid = #{productid}")
	int deletereviewfromproduct(int productid);
}
