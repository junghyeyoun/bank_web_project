package pack.controller.cart;

import lombok.Data;

@Data
public class CartBean {
	// cart 테이블의 칼럼
		private int cart_id, product_id, quantity;
		
		// products 테이블의 칼럼
		private String model, brand;
		private int price;
		
		// 추가
		private int totalPrice; // 전체가격을 나타내기 위함
		
		
		// totalPrice의 변수 값을 초기화 해주는 메서드
			public void initSaleTotal() {
				this.totalPrice = this.price*this.quantity;
			}
			
		// CartDto 객체를 문자열로 표현하기 위해 toString 메서드를 재정의
		@Override
		public String toString() {
		    return "CartDto [cart_id=" + cart_id + ", product_id=" + product_id + ", quantity=" + quantity +
		           ", model=" + model + ", brand=" + brand + ", price=" + price + ", totalPrice=" + totalPrice + "]";
		}
}
