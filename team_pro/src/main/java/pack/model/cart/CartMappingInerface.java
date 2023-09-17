package pack.model.cart;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pack.controller.cart.CartBean;

@Mapper
public interface CartMappingInerface {
	
	// 카트 추가
	@Insert("INSERT INTO cart (product_id , quantity, customer_id) VALUES (#{product_id}, #{quantity}, #{customer_id})")
	int addCart(CartBean bean);
	
	// 카트 삭제
	@Delete("delete from cart where cart_id = #{cart_id}")
	int deleteCart(int cart_id);
	
	// 카트 수량 수정
	@Update("update cart set quantity=#{quantity}")
	int modifyCount(CartBean bean);
	
	// 카트 목록
	@Select("select a.cart_id, a.customer_id, a.product_id, b.model, b.brand, b.price, b.pimage from cart a left outer join products b on a.product_id = b.product_id where customer_id = #{customer_id}")
	List<CartDto> getCart(String customer_id);
	
	// 카트 확인
	@Select("select * from cart where customer_id = #{customer_id} and product_id=#{product_id}")
	CartDto checkCart(CartBean bean);
}
