package ptithcm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.entity.DanhGiaEntity;
import ptithcm.entity.GioHangEntity;
import ptithcm.entity.NguoiDungEntity;
import ptithcm.entity.SanPhamEntity;
import ptithcm.service.DanhGiaService;
import ptithcm.service.SanPhamService;
import ptithcm.service.gioHangService;

@Transactional
@Controller
@RequestMapping()
public class sanPhamController {
	
	@Autowired
	SanPhamService sanPhamService;
	@Autowired
	DanhGiaService danhGiaService;
	@Autowired
	gioHangService gioHangService;
	@RequestMapping("/product/{maSp}")
	public String sanPham(@PathVariable("maSp") String maSp, ModelMap model,HttpServletRequest request) {
		SanPhamEntity sanPham=sanPhamService.laySanPham(maSp);
		
		List<String> sizes = sanPhamService.laySizeTheoTenSanPham(maSp);
		model.addAttribute("sizes", sizes);
		
		List<SanPhamEntity> sanPhamCungKieu = sanPhamService.laySanPhamCungKieu(maSp);
		model.addAttribute("sanPhamCungKieu", sanPhamCungKieu);
		
		List<DanhGiaEntity> listDanhGia = danhGiaService.layDanhGiaSanPham(maSp);
		int count = listDanhGia.size();
		
		model.addAttribute("sanPham", sanPham);
		model.addAttribute("count",count);
		model.addAttribute("danhGiaList",listDanhGia);

		return "/sanPham/sanPham";
	}
	@RequestMapping(value="themVaoGio/{maSp}")
	public String addToCart(@PathVariable("maSp") String maSp, ModelMap model,HttpServletRequest request) {
		SanPhamEntity sanPham = sanPhamService.laySanPham(maSp);
		HttpSession session0= request.getSession();
		int amount = Integer.parseInt(request.getParameter("soLuong"));
		NguoiDungEntity user =  (NguoiDungEntity) session0.getAttribute("USER");
		if(user == null) {
			model.addAttribute("user", new NguoiDungEntity());
			System.out.println("Nguoi dung moi");
			return "user/login";
		}
		List<GioHangEntity> productsListInCart = gioHangService.layGioHangCuaUser(user.getMaNd());
		boolean alreadyInCart = false;
		for(int i =0; i< productsListInCart.size(); i++) {
			if(productsListInCart.get(i).getSanPham() ==sanPham) {
				alreadyInCart = true;
				break;
			}
		}
		if(alreadyInCart) {
			GioHangEntity gioHang = gioHangService.layGioHangTheoMaNdVaSanPham(user.getMaNd(), sanPham.getMaSP());
			gioHang.setSoLuong(gioHang.getSoLuong()+amount);
			gioHangService.updateGioHang(gioHang);
			System.out.println("San pham da ton tai trong gio hang");
		}
		else {
			
			GioHangEntity gioHang = new GioHangEntity();
			gioHang.setNguoiDung(user);
			gioHang.setSanPham(sanPham);
			gioHang.setSoLuong(amount);
			gioHangService.addGioHang(gioHang);
			System.out.println("San pham chua ton tại trong gio hang");
		}
		  
		System.out.println("Dang them vao gio");
		model.addAttribute("sanPham", sanPham);
		return "sanPham/sanPham";
	}
	
		
}
