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
	@Select("SELECT * FROM products ORDER BY productId DESC")
	List<ProductDto> selectAll();
	
	// 해당 상품 상세 보기 
	@Select("select * from products where productId=#{productId}")
	ProductDto selectOne(int product_id);
	
	// 상품 등록
	@Insert("INSERT INTO products (category, brand, model, price, stockQuantity,pimage, dimage)\r\n"
			+ "VALUES (#{category}, #{brand}, #{model}, #{price}, #{stockQuantity}, #{pimage},#{dimage})")
	int insertProduct(ProductBean bean);
	
	// 총 상품 수 구하기
	@Select("select count(*) from products")
	int totalCnt();
	
	// 상품 찾기
	@Select("select * from products where ${searchName} like concat('%', #{searchValue}, '%')")
	List<ProductDto> searchList(ProductBean bean);
	
	// 상품 수정
	@Update("update products set brand=#{brand}, model=#{model}, price=#{price}, stockQuantity=#{stockQuantity}, pimage=#{pimage}, dimage=#{dimage}  where productId=#{productId}")
	int updateProduct(ProductBean bean);
	
	// pimage(상품이미지) 삭제
	@Update("update products set pimage=null where productId = #{productId}")
	int pimagedelete(int productId);
	
	// dimage(상세이미지) 삭제
	@Update("update products set dimage=null where productId = #{productId}")
	int dimagedelete(int productId);
	
	// 상품 삭제
	@Delete("delete from products where productId=#{productId}")
	int deleteProduct(int product_id);
}
