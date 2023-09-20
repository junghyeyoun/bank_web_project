package pack.contoller.review;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ch.qos.logback.core.model.Model;
import pack.model.review.ReviewDao;

@Controller
public class ReviewInsertController {
	@Autowired
	private ReviewDao reviewDao;
	
	@GetMapping("reviewinsert")
	public String insert () {	
		return "reviewinsert";
	}
	
	@PostMapping("reviewinsert")
	public String insertProcess (@RequestParam("product_id") String product_id, ReviewBean bean, Model model, BindingResult result) throws Exception{
		InputStream inputStream = null;
		OutputStream outputStream = null;
		
		MultipartFile file = bean.getRimagePath();
		String filename = file.getOriginalFilename();
		if (result.hasErrors()) {
			return "err";
		}
		
		try {
			inputStream = file.getInputStream();
			File newFile = new File("C:\\Users\\윤정혜\\git\\team_project\\team_pro\\src\\main\\resources\\static\\upload\\"+filename);
			if(!newFile.exists()) {
				newFile.createNewFile();
			}
			outputStream = new FileOutputStream(newFile);
			int read = 0;
			byte[] bytes = new byte[1024];  // -1 끝을 의미
			while((read = inputStream.read(bytes))!=-1) {
				outputStream.write(bytes,0,read);
			}
			bean.setProduct_id(product_id);
			bean.setRimage(filename);
			reviewDao.reviewinsert(bean);
		} catch (Exception e) {
			System.out.println("file submit err : "+e);
		} finally {
			try {
				inputStream.close();
				outputStream.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return "redirect:/reviewlist";
	}
}
