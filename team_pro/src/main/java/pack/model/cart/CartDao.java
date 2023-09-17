package pack.model.cart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pack.controller.cart.CartBean;

@Repository
public class CartDao {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CartMappingInerface cartMappingInerface;
	
	public boolean insertcart(CartBean bean)  {
		boolean b = false;
		int re = cartMappingInerface.addCart(bean);
		if (re > 0)
			b = true;
		return b;
	}
}
