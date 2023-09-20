package pack.controller.product;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public String insertProcess(ProductBean bean, Model model, BindingResult result) throws Exception{

	// 판매자만 가능 추가해야함
	
		InputStream inputStream1 = null;
		InputStream inputStream2 = null;
		OutputStream outputStream1 = null;
		OutputStream outputStream2 = null;

		// 업로드될 파일 검사
		MultipartFile file1 = bean.getPimagePath();
		MultipartFile file2 = bean.getDimagePath();
		String filename1 = file1.getOriginalFilename();
		String filename2 = file2.getOriginalFilename();
		if (result.hasErrors()) {
			return "err"; 
		}
		
		try {
			inputStream1 = file1.getInputStream();
			inputStream2 = file2.getInputStream();
			File newFile1 = new File("C:\\work\\sprsou\\team_project\\team_pro\\src\\main\\resources\\static\\upload\\" + filename1); // 절대경로로 찍기
			File newFile2 = new File("C:\\work\\sprsou\\team_project\\team_pro\\src\\main\\resources\\static\\upload\\" + filename2);
			if(!newFile1.exists()) {
				newFile1.createNewFile();
			}
			if(!newFile2.exists()) {
				newFile2.createNewFile();
			}
			outputStream1 = new FileOutputStream(newFile1);
			outputStream2 = new FileOutputStream(newFile2);
			int read = 0;
			byte[] bytes = new byte[1024];  // -1 끝을 의미
			while((read = inputStream1.read(bytes))!=-1) {
				outputStream1.write(bytes,0,read);
			}
			while((read = inputStream2.read(bytes))!=-1) {
				outputStream2.write(bytes,0,read);
			}
			bean.setPimage(filename1);
			bean.setDimage(filename2);
			productDao.insert(bean);
		} catch (Exception e) {
			System.out.println("file submit err : "+e);
		} finally {
			try {
				inputStream1.close();
				inputStream2.close();
				outputStream1.close();
				outputStream2.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		model.addAttribute("list",bean);
		return "redirect:/productlist";
	}
}
