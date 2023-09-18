package pack.controller.product;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

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
	public String insertProcess(ProductBean bean, @RequestParam("pimage") MultipartFile pimage, @RequestParam("pimagePath") String pimagePath, Model model, BindingResult result) throws Exception{

	// 판매자만 가능 추가해야함
	
		InputStream inputStream = null;
		OutputStream outputStream = null;

		// 업로드될 파일 검사
		MultipartFile file = bean.getPimagePath();
		String filename = file.getOriginalFilename();
		if (result.hasErrors()) {
			return "err"; // 에러 발생 (파일을 선택하지 않은 경우)시 수행
		}
		
		try {
			inputStream = file.getInputStream();
			File newFile = new File("C:/work/sprsou/sprweb16fileupload/src/main/resources/static/upload/" + filename); // 절대경로로 찍기
			if(!newFile.exists()) {
				newFile.createNewFile();
			}
			outputStream = new FileOutputStream(newFile);
			int read = 0;
			byte[] bytes = new byte[1024];
			// -1 끝을 의미
			while((read = inputStream.read(bytes))!=-1) {
				outputStream.write(bytes,0,read);
			}
		} catch (Exception e) {
			System.out.println("file submit err : "+e);
			return "err";
		} finally {
			try {
				inputStream.close();
				outputStream.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		bean.setPimage(pimagePath);
		model.addAttribute("filename",filename);
		return "redirect:/productlist";
	}
}
