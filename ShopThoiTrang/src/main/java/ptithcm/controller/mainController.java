package ptithcm.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ptithcm.entity.NguoiDungEntity;
import ptithcm.entity.SanPhamEntity;
import ptithcm.entity.LoaiSanPhamEntity;
import ptithcm.service.DonHangService;
import ptithcm.service.SanPhamService;
import ptithcm.service.loaiSanPhamService;

@Transactional
@Controller
public class mainController {

	@Autowired
	loaiSanPhamService loaiSanPhamService;

	@Autowired
	SanPhamService sanPhamService;
	@Autowired
	DonHangService DonHangService;

	@RequestMapping()
	public String main(HttpServletRequest request, ModelMap model) throws IOException {

		List<LoaiSanPhamEntity> loaiSPNam = loaiSanPhamService.layLoaiTheoGioiTinh("nam");
		model.addAttribute("loaiSPNam", loaiSPNam);
		List<LoaiSanPhamEntity> loaiSPNu = loaiSanPhamService.layLoaiTheoGioiTinh("nữ");
		model.addAttribute("loaiSPNu", loaiSPNu);
	
		List<SanPhamEntity> listSpNam = sanPhamService.laySanPhamTheogioiTinh("nam");
		listSpNam = sanPhamService.locSanPhamTrung(listSpNam);
		model.addAttribute("listSpNam", listSpNam);
		List<SanPhamEntity> listSpNu = sanPhamService.laySanPhamTheogioiTinh("nữ");
		listSpNu = sanPhamService.locSanPhamTrung(listSpNu);
		model.addAttribute("listSpNu", listSpNu);
		model.addAttribute("user", new NguoiDungEntity());
		
		List<SanPhamEntity> listMoi = sanPhamService.laySanPhamMoi();
		listMoi = sanPhamService.locSanPhamTrung(listMoi);
		model.addAttribute("sanPhamMoi", listMoi);
				
		List<SanPhamEntity> listNgauNhien = sanPhamService.laySanPhamNgauNhien();
		listNgauNhien = sanPhamService.locSanPhamTrung(listNgauNhien);
		model.addAttribute("listNgauNhien", listNgauNhien);
		 
//		 	HttpSession session0 = request.getSession();
//			NguoiDungEntity user = (NguoiDungEntity) session0.getAttribute("USER");
//			int maNd=user.getMaNd();
//			List<String> maSanPhamListDeXuat = DonHangService.layMaSanPhamTrongDonHangGanNhatCuaUser(maNd);
////			System.out.print(maSanPhamListDeXuat);
//			
//			List<String> param = maSanPhamListDeXuat;
//			
//			ProcessBuilder builder = new ProcessBuilder(
//	                "cmd.exe", "/c", "cd C:\\Users\\Administrator\\Documents\\ShopThoiTrang\\src\\main\\python & python recommend.py \"" + param + "\"");
//	        builder.redirectErrorStream(true);
//	        Process p = builder.start();
//	        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
//	        System.out.println("test de xuat");
//	         
//	     // Đọc đầu ra từ quy trình Python và lưu vào danh sách
//	        List<String> productNames = new ArrayList<>();
//	        String line;
//	        while ((line = r.readLine()) != null) {
//	        	System.out.println(line);
//	            // Kiểm tra xem dòng có chứa tên tệp ảnh không
//	            if (line.startsWith("name: ")) {
//	            	String productName = line.replace("name: ", "");
//	                productNames.add(productName);
//	            }
//	        }
//	        
//	        if (productNames.isEmpty()) {
//	            // Nếu danh sách đề xuất trống, xuất sp ngẫu nhiên
//	            model.addAttribute("listDeXuat", listNgauNhien);
//	        } else {
//	            // Nếu danh sách không rỗng, tiến hành tìm sản phẩm và truyền vào model
//	            List<SanPhamEntity> products = sanPhamService.laySanPhamTheoListMaSP(productNames);
//	            model.addAttribute("listDeXuat", products);
//	        }
//		
		
		return "main";
	}

	@RequestMapping("khongCoQuyen")
	public String khongCoQuyen() {
		return "redirect:/user/login.htm";
	}

//	public List<SanPhamEntity> locSanPhamTrung(List<SanPhamEntity> list) {
//		Set<String> uniqueSet = new HashSet<>();
//		List<SanPhamEntity> result = new ArrayList<>();
//		for (SanPhamEntity sanPham : list) {
//
//			if (!uniqueSet.contains(sanPham.getTenSanPham())) {
//				result.add(sanPham);
//				uniqueSet.add(sanPham.getTenSanPham());
//			}
//		}
//		return result;
//	}

}
