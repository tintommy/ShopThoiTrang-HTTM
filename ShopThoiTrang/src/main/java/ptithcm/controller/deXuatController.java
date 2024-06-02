package ptithcm.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.entity.NguoiDungEntity;
import ptithcm.entity.SanPhamEntity;
import ptithcm.service.DonHangService;
import ptithcm.service.SanPhamService;

@Transactional
@Controller
@RequestMapping()
public class deXuatController {
	
	@Autowired
	SanPhamService sanPhamService;
	@Autowired
	DonHangService DonHangService;
	
	
	@RequestMapping("/recommend")
	public String imageSearch(HttpServletRequest request, ModelMap model) throws IOException {
		
		HttpSession session0 = request.getSession();
		NguoiDungEntity user = (NguoiDungEntity) session0.getAttribute("USER");
		int maNd=user.getMaNd();				
		
		List<String> maSanPhamList = DonHangService.layMaSanPhamTrongDonHangGanNhatCuaUser(maNd);
		if(maSanPhamList.isEmpty()) {
			List<SanPhamEntity> listNgauNhien = sanPhamService.laySanPhamNgauNhien();
    		listNgauNhien = sanPhamService.locSanPhamTrung(listNgauNhien);
    		model.addAttribute("listDeXuat", listNgauNhien);
    		return "deXuat/deXuat";
		}
		
		ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "cd C:\\Users\\Administrator\\Documents\\ShopThoiTrang\\src\\main\\python & python recommend.py \"" + maSanPhamList + "\"");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        System.out.println("test de xuat");
         
     // Đọc đầu ra từ quy trình Python và lưu vào danh sách
        List<String> productNames = new ArrayList<>();
        String line;
        while ((line = r.readLine()) != null) {
        	System.out.println(line);
            // Kiểm tra xem dòng có chứa tên sp nào ko
            if (line.startsWith("name: ")) {
            	String productName = line.replace("name: ", "");
                productNames.add(productName);
            }
        }
        
        
        if (productNames.size()<6) {
        	List<SanPhamEntity> listNgauNhien = sanPhamService.laySanPhamNgauNhien();
    		listNgauNhien = sanPhamService.locSanPhamTrung(listNgauNhien);
    		List<SanPhamEntity> listDeXuat = sanPhamService.laySanPhamTheoListMaSP(productNames);
    		listDeXuat.addAll(listNgauNhien);
            // Nếu danh sách đề xuất trống, xuất sp ngẫu nhiên
            model.addAttribute("listDeXuat", listDeXuat);
        } else {
            // Nếu danh sách không rỗng, tiến hành tìm sản phẩm và truyền vào model
            List<SanPhamEntity> products = sanPhamService.laySanPhamTheoListMaSP(productNames);
            model.addAttribute("listDeXuat", products);
        }	
        
		return "deXuat/deXuat";
	}
}










//package ptithcm.controller;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import javax.transaction.Transactional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import ptithcm.designpattern.FacadePattern.DeXuatFacade;
//import ptithcm.entity.NguoiDungEntity;
//import ptithcm.entity.SanPhamEntity;
//import ptithcm.service.DonHangService;
//import ptithcm.service.SanPhamService;
//
//@Transactional
//@Controller
//@RequestMapping()
//public class deXuatController {
//	
//	
//	@Autowired
//	DeXuatFacade deXuatFacade;
//	
//	@RequestMapping("/recommend")
//	public String imageSearch(HttpServletRequest request, ModelMap model) throws IOException {
//		
//		HttpSession session0 = request.getSession();
//		NguoiDungEntity user = (NguoiDungEntity) session0.getAttribute("USER");
//		int maNd=user.getMaNd();				
//		
//		List<String> maSanPhamList = deXuatFacade.layMaSanPhamTrongDonHangGanNhatCuaUser(maNd);
//		if(maSanPhamList.isEmpty()) {
//			List<SanPhamEntity> listNgauNhien = deXuatFacade.laySanPhamNgauNhien();
//    		listNgauNhien = deXuatFacade.locSanPhamTrung(listNgauNhien);
//    		model.addAttribute("listDeXuat", listNgauNhien);
//    		return "deXuat/deXuat";
//		}
//		
//		ProcessBuilder builder = new ProcessBuilder(
//                "cmd.exe", "/c", "cd C:\\Users\\Administrator\\Documents\\ShopThoiTrang\\src\\main\\python & python recommend.py \"" + maSanPhamList + "\"");
//        builder.redirectErrorStream(true);
//        Process p = builder.start();
//        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
//        System.out.println("test de xuat");
//         
//     // Đọc đầu ra từ quy trình Python và lưu vào danh sách
//        List<String> productNames = new ArrayList<>();
//        String line;
//        while ((line = r.readLine()) != null) {
//        	System.out.println(line);
//            // Kiểm tra xem dòng có chứa tên sp nào ko
//            if (line.startsWith("name: ")) {
//            	String productName = line.replace("name: ", "");
//                productNames.add(productName);
//            }
//        }
//        
//        
//        if (productNames.size()<6) {
//        	List<SanPhamEntity> listNgauNhien = deXuatFacade.laySanPhamNgauNhien();
//    		listNgauNhien = deXuatFacade.locSanPhamTrung(listNgauNhien);
//    		List<SanPhamEntity> listDeXuat = deXuatFacade.laySanPhamTheoListMaSP(productNames);
//    		listDeXuat.addAll(listNgauNhien);
//            // Nếu danh sách đề xuất trống, xuất sp ngẫu nhiên
//            model.addAttribute("listDeXuat", listDeXuat);
//        } else {
//            // Nếu danh sách không rỗng, tiến hành tìm sản phẩm và truyền vào model
//            List<SanPhamEntity> products = deXuatFacade.laySanPhamTheoListMaSP(productNames);
//            model.addAttribute("listDeXuat", products);
//        }	
//        
//		return "deXuat/deXuat";
//	}
//}
