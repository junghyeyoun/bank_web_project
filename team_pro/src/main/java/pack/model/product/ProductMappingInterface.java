package pack.model.product;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pack.controller.product.ProductBean;


@Mapper
public interface ProductMappingInterface {
	// 전체 상품 읽기
	@Select("SELECT * FROM products ORDER BY product_id DESC")
	List<ProductDto> selectAll();
	
	// 해당 상품 상세 보기 
	@Select("select * from products where product_id=#{product_id}")
	ProductDto selectOne(int product_id);
	
	// 상품 등록
	@Insert("INSERT INTO products (category_id, brand, model, price, stock_quantity,pimage, dimage)\r\n"
			+ "VALUES (#{category_id}, #{brand}, #{model}, #{price}, #{stock_quantity}, #{pimage},#{dimage})")
	int insertProduct(ProductBean bean);
	
	// 총 상품 수 구하기
	@Select("select count(*) from products")
	int totalCnt();
	
	// 상품 찾기
	@Select("select * from products where ${searchName} like concat('%', #{searchValue}, '%')")
	List<ProductDto> searchList(ProductBean bean);
	
	// 상품 수정
	@Update("update products set brand=#{brand}, model=#{model}, price=#{price}, stock_quantity=#{stock_quantity}, pimage=#{pimage}, dimage=#{dimage}  where product_id=#{product_id}")
	int updateProduct(ProductBean bean);
	
	// 상품 삭제
	@Delete("delete from products where product_id=#{product_id}")
	int deleteProduct(int product_id);
	
}
