//package ptithcm.controller;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import ptithcm.entity.LoaiSanPhamEntity;
//import ptithcm.entity.NguoiDungEntity;
//import ptithcm.service.SanPhamService;
//import ptithcm.service.loaiSanPhamService;
//@Transactional
//@Controller
//
//public class headerController {
//	@Autowired
//	SanPhamService sanPhamService;
//	@Autowired
//	loaiSanPhamService loaiService;
//	@RequestMapping()
//	public String main(HttpServletRequest request, ModelMap model) {
//
//		List<LoaiSanPhamEntity> loaiSPNam = loaiService.layLoaiTheoGioiTinh("nam");
//		model.addAttribute("loaiSPNam", loaiSPNam);
//		List<LoaiSanPhamEntity> loaiSPNu = loaiService.layLoaiTheoGioiTinh("nữ");
//		model.addAttribute("loaiSPNu", loaiSPNu);
//		model.addAttribute("user", new NguoiDungEntity());
//		return "include/header";
//	}
//
//
//	
//
////	@RequestMapping(params = "btnSearch")
////	public String timSanPham(ModelMap model, @RequestParam(value = "key", required = false) String key,
////			HttpServletRequest request) {
////       List<SanPhamEntity>listSP=SanPhamService.laySanPhamTheoMa(key);
////       model.addAttribute("key", key);
////       model.addAttribute("listSP", listSP);
////       int so = listSP.size();
////       model.addAttribute("soLuong", so);
////       String mess =""; 
////       if (listSP.isEmpty()) mess = "Không có sản phẩm này";
////       else {
////    	    mess = "Tìm thấy "+so+" sản phẩm";
////    	   
////	}
////       model.addAttribute("message", mess);	
////        return "sanPham/timKiem";
////	}
//	
////	@RequestMapping(params = "voucher")
////	public String ToiTrangKM() {
////
////		
////
////		return "redirect:/KhuyenMai.htm";
////	}
////
////	@RequestMapping(params = "history")
////	public String lishSu() {
////	
////		
////
////		return "redirect:/lichSuDonHang.htm";
////	}
////
////	@RequestMapping(params = "cart")
////	public String gioHang() {
////
////
////		return "redirect:/gioHang.htm";
////	}
//
//	
//	
//}