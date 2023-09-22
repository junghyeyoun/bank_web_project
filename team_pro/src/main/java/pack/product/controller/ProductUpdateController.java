package pack.product.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import pack.product.model.ProductDao;
import pack.product.model.ProductDto;

@Controller
public class ProductUpdateController {
	@Autowired
	private ProductDao productDao;

	@GetMapping("productupdate")
	public String edit(@RequestParam("productid") int productid, @RequestParam("page") String page,
			@RequestParam("pimage") String pimage, @RequestParam("dimage") String dimage, Model model) {
		// 수정 대상 자료 읽기
		ProductDto dto = productDao.detail(productid);

		model.addAttribute("data", dto);
		model.addAttribute("page", page);
		model.addAttribute("dimage", dimage);
		model.addAttribute("pimage", pimage);

		return "productupdate";
	}

	@PostMapping("productupdate")
	public String editProcess(@RequestParam("productid") int productid, @RequestParam("page") String page,
			@RequestParam("pimage") String pimage, @RequestParam("dimage") String dimage, ProductBean bean,
			BindingResult result, Model model) {

		InputStream inputStream1 = null;
		InputStream inputStream2 = null;
		OutputStream outputStream1 = null;
		OutputStream outputStream2 = null;

		MultipartFile file1 = bean.getPimagePath();
		MultipartFile file2 = bean.getDimagePath();
		String orifilename1 = file1.getOriginalFilename();
		String orifilename2 = file2.getOriginalFilename();
		if (orifilename1 != "") {
			String uuid1 = UUID.randomUUID().toString();
			String filename1 = uuid1 + file1.getOriginalFilename();
			if (result.hasErrors()) {
				return "err";
			}
			try {
				inputStream1 = file1.getInputStream();
				File newFile1 = new File(
						"C:\\work\\sprsou\\team_project\\team_pro\\src\\main\\resources\\static\\upload\\" + filename1); // 절대경로로
																															// 찍기
				if (!newFile1.exists()) {
					newFile1.createNewFile();
				}
				outputStream1 = new FileOutputStream(newFile1);
				int read = 0;
				byte[] bytes = new byte[1024]; // -1 끝을 의미
				while ((read = inputStream1.read(bytes)) != -1) {
					outputStream1.write(bytes, 0, read);
				}
				bean.setPimage(filename1);
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				try {
					inputStream1.close();
					outputStream1.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		if (orifilename2 != "") {
			String uuid2 = UUID.randomUUID().toString();
			String filename2 = uuid2 + file2.getOriginalFilename();
			if (result.hasErrors()) {
				return "err";
			}

			try {
				inputStream2 = file2.getInputStream();
				File newFile2 = new File(
						"C:\\work\\sprsou\\team_project\\team_pro\\src\\main\\resources\\static\\upload\\" + filename2);
				if (!newFile2.exists()) {
					newFile2.createNewFile();
				}
				outputStream2 = new FileOutputStream(newFile2);
				int read = 0;
				byte[] bytes = new byte[1024]; // -1 끝을 의미

				while ((read = inputStream2.read(bytes)) != -1) {
					outputStream2.write(bytes, 0, read);
				}

				bean.setDimage(filename2);
			} catch (Exception e) {
				System.out.println("file submit err : " + e);
			} finally {
				try {
					inputStream2.close();
					outputStream2.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		boolean b = productDao.update(bean);
		if (b) {
			return "redirect:productlist";
		} else {
			return "redirect:error";
		}
	}
	
	// 상품이미지 삭제(따로)
	@GetMapping("pimagedelete")
	public String pimagedelete(@RequestParam("productid") int productid, Model model) {
		productDao.pimagedelete(productid);
		return "redirect:productlist";
	}
	
	// 상세이미지 삭제(따로)
	@GetMapping("dimagedelete")
	public String dimagedelete(@RequestParam("productid") int productid, Model model) {
		productDao.dimagedelete(productid);
		return "redirect:productlist";
	}
	
}
