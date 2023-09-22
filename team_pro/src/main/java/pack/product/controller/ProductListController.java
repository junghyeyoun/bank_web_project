package pack.product.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pack.product.model.ProductDao;
import pack.product.model.ProductDto;

@Controller
public class ProductListController {
	@Autowired
	ProductDao productDao;

	private int tot; // 전체 레코드 수
	private int plist = 10; // 페이지 당 행 수
	private int pagesu; // 전체 페이지 수

	public ArrayList<ProductDto> getListdata(ArrayList<ProductDto> list, int page) {
		ArrayList<ProductDto> result = new ArrayList<ProductDto>();

		int start = (page - 1) * plist; // 0, 10, 20, ...

		int size = plist <= list.size() - start ? plist : list.size() - start;

		for (int i = 0; i < size; i++) {
			result.add(i, list.get(start + i));
		}
		return result;
	}

	// 총 페이지 수 얻기
	public int getPageSu() {
		tot = productDao.totalCnt();
		pagesu = tot / plist;
		if (tot % plist > 0)
			pagesu += 1;
		return pagesu;
	}

	// 상품 리스트보기
	@GetMapping("productlist")
	public String showProductList(@RequestParam(name = "page", defaultValue = "1") int page, Model model) {
	    // paging 처리
	    int spage = 0;
	    try {
	        spage = page;
	    } catch (Exception e) {
	        spage = 1;
	    }
	    if (page <= 0)
	        spage = 1;

	    ArrayList<ProductDto> list = (ArrayList<ProductDto>) productDao.selectAll();
	    ArrayList<ProductDto> result = getListdata(list, spage);

	    model.addAttribute("list", result); 
	    model.addAttribute("pagesu", getPageSu());
	    model.addAttribute("page", spage);
	    return "productlist";
	}


	// 선택한 상품 자세히 보기
	@GetMapping("showproduct")
	public String showproduct(@RequestParam("productid") int productid, @RequestParam("page") String page, Model model) {
		model.addAttribute("data", productDao.detail(productid));
		model.addAttribute("page", page);
		
		return "productdetail";
	}
	
	@GetMapping("productsearch")
	public String searchProcess(ProductBean bean, Model model) {
	    System.out.println(bean.getSearchName() + " " + bean.getSearchValue()); // 검색 파라미터 확인용
	    
	    ArrayList<ProductDto> list = (ArrayList<ProductDto>) productDao.search(bean);
	    
	    model.addAttribute("list", list);
	    model.addAttribute("pagesu", getPageSu());
	    model.addAttribute("page", "1");
	    return "productlist";
	}

}
