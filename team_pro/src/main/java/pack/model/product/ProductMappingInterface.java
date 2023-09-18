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
	@Select("select * from products")
	List<ProductDto> selectAll();
	
	// 해당 상품 상세 보기 
	@Select("select * from products where product_id=#{product_id}")
	ProductDto selectOne(String product_id);
	
	// 상품 등록
	@Insert("INSERT INTO products (category_id, brand, model, price, stock_quantity, description, \r\n"
			+ "release_date, specifications, warranty_period, weight, dimensions, pimage)\r\n"
			+ "VALUES (#{category_id}, #{brand}, #{model}, #{price}, #{stock_quantity}, #{description}, \r\n"
			+ "#{release_date}, #{specifications}, #{warranty_period}, #{weight}, #{dimensions}, #{pimage})")
	int insertProduct(ProductBean bean);
	
	// 총 상품 수 구하기
	@Select("select count(*) from products")
	int totalCnt();
	
	// 상품 찾기
	@Select("select * from products where ${searchName} like concat('%', #{searchValue}, '%')")
	List<ProductDto> searchList(ProductBean bean);
	
	// 상품 수정
	@Update("update products set brand=#{brand}, model=#{model}, price=#{price}, stock_quantity=#{stock_quantity}, description=#{description}, release_date=#{release_date}, specifications=#{specifications}, warranty_period=#{warranty_period}, weight=#{weight}, dimensions=#{dimensions} where product_id=#{product_id}")
	int updateProduct(ProductBean bean);
	
	// 상품 삭제
	@Delete("delete from products where product_id=#{product_id}")
	int deleteProduct(String product_id);
	
}
