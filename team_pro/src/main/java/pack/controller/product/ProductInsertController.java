package pack.controller.product;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import pack.model.product.ProductDao;

@Controller
public class ProductInsertController {
	@Autowired
	private ProductDao productDao;

	@GetMapping("insert")
	public String insertform() {
		return "productinsert";
	}

	@PostMapping("insert")
	public String insertProcess(ProductBean bean, MultipartFile pimage) throws Exception{

		// 판매자만 가능 추가해야함
		
		String oriPimge = pimage.getOriginalFilename();
		String pimgae = "";
		
		String projectPath = System.getProperty("C:\\Users\\윤정혜\\git\\team_project\\team_pro\\src\\main\\resources\\static\\upload");
		
		// UUID를 이용하여 파일명 새로 생성
		// UUID : 서로 다른 객체들을 구별하기 위한 클래스
		UUID uuid = UUID.randomUUID();
		
		String savedFileName = uuid + "_" + oriPimge;
		// pimage = savedFileName; 
		
		// File saveFile = new File(projectPath, pimage);
		
		// pimage.transferTo(saveFile);
		
		bean.setPimag(pimgae);
		
		
		
		
		
	   boolean b = productDao.insert(bean);
	   if(b) {
		   return "redirect:/productlist";
	   } else {
		   return "redirect:/error";
	   }
	}
}
