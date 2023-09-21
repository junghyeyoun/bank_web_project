package pack.product.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pack.product.model.ProductDao;

@Controller
public class ProductDeleteController {
    @Autowired
    private ProductDao productDao;

    @PostMapping("productdelete")
    public String delete(@RequestParam("productId") int productId, @RequestParam("page") String page) {
    	if(productDao.delete(productId))
    		return "redirect:productlist?page="+page;
    	else
    		return "redirect:error";
    }

}
