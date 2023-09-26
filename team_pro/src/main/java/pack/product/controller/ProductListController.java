package pack.product.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pack.product.model.ProductDao;
import pack.product.model.ProductDto;
import pack.review.model.ReviewDao;
import pack.review.model.ReviewDto;

@Controller
public class ProductListController {
	@Autowired
	ProductDao productDao;
	@Autowired
	ReviewDao reviewDao;

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
	public String showProductList(@RequestParam(name = "page", defaultValue = "1") int page, ProductBean bean,Model model) {
		
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
		
		// 상품별 리뷰 보기
		ArrayList<ReviewDto> list = (ArrayList<ReviewDto>) reviewDao.selectPart(productid);
		model.addAttribute("list", list); 
		
		 // 리뷰가 없을 경우 메시지 추가
	    if (list.isEmpty()) {
	        model.addAttribute("noReviews", "리뷰가 없습니다.");
	    }
		
		return "productdetail";
	}
	
	// 상품 검색하기
	@GetMapping("productsearch")
	public String searchProcess(ProductBean bean, Model model) {
	    System.out.println(bean.getSearchName() + " " + bean.getSearchValue()); // 검색 파라미터 확인용
	    
	    ArrayList<ProductDto> list = (ArrayList<ProductDto>) productDao.search(bean);
	    
	    model.addAttribute("list", list);
	    model.addAttribute("pagesu", getPageSu());
	    model.addAttribute("page", "1");
	    return "productlist";
	}
	
	// 카테고리별 상품 보기
	@GetMapping("productcategorylist")
	public String showProductCategoryList(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam("category") String category,Model model) {
	    // paging 처리
	    int spage = 0;
	    try {
	        spage = page;
	    } catch (Exception e) {
	        spage = 1;
	    }
	    if (page <= 0)
	        spage = 1;

	    ArrayList<ProductDto> list = (ArrayList<ProductDto>) productDao.selectCatogory(category);
	    ArrayList<ProductDto> result = getListdata(list, spage);

	    model.addAttribute("list", result); 
	    model.addAttribute("pagesu", getPageSu());
	    model.addAttribute("page", spage);
	    return "productlist";
	}
	

}
