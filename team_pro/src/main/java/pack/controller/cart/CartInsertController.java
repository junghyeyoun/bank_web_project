package pack.controller.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import pack.model.cart.CartDao;

@Controller
public class CartInsertController {
    @Autowired
    private CartDao cartDao;
    
    // 카트담기
    @PostMapping("insertcart")
    public String insertProcess(CartBean bean) {
        boolean b = cartDao.insertcart(bean);
        if (b) {
            return "redirect:/cartlist";  // 추가 후 목록 보기
        } else {
            return "redirect:/error";
        }
    }
}

