package pack.model;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import pack.controller.ProductBean;

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
			+ "release_date, specifications, warranty_period, weight, dimensions)\r\n"
			+ "VALUES (#{category_id}, #{brand} #{model}, #{price}, #{stock_quantity}, #{description}, \r\n"
			+ "#{release_date}, #{specifications}, #{warranty_period}, #{weight}, #{dimensions})")
	int insertProduct(ProductBean bean);
}
