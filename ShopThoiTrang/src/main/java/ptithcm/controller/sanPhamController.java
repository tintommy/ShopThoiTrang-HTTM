package ptithcm.controller;

import java.util.Collections;
import java.util.Comparator;
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
import ptithcm.entity.YeuThichEntity;
import ptithcm.service.DanhGiaService;
import ptithcm.service.SanPhamService;
import ptithcm.service.gioHangService;
import ptithcm.service.yeuThichService;

import ptithcm.designpattern.FacadePattern.SanPhamFacade;

@Transactional
@Controller
@RequestMapping()
//public class sanPhamController {
//	
//	@Autowired
//	SanPhamService sanPhamService;
//	@Autowired
//	DanhGiaService danhGiaService;
//	@Autowired
//	gioHangService gioHangService;
//	@Autowired
//	yeuThichService yeuThichService;
//	@RequestMapping("/product/{maSp}")
//	public String sanPham(@PathVariable("maSp") String maSp, ModelMap model,HttpServletRequest request) {
//		SanPhamEntity sanPham=sanPhamService.laySanPham(maSp);
//		
//		List<String> sizes = sanPhamService.laySizeTheoTenSanPham(maSp);
//		Collections.sort(sizes, new SizeComparator());
//		model.addAttribute("sizes", sizes);
//		
//		List<SanPhamEntity> sanPhamCungKieu = sanPhamService.laySanPhamCungKieu(maSp);
//		sanPhamCungKieu = sanPhamService.locSanPhamTrung(sanPhamCungKieu);
//		model.addAttribute("sanPhamCungKieu", sanPhamCungKieu);
//		
//		List<DanhGiaEntity> listDanhGia = danhGiaService.layDanhGiaSanPham(maSp);
//		int count = listDanhGia.size();
//		
//		model.addAttribute("sanPham", sanPham);
//		model.addAttribute("count",count);
//		model.addAttribute("danhGiaList",listDanhGia);
//
//		return "/sanPham/sanPham";
//	}
//	@RequestMapping(value="themVaoGio/{maSp}")
//	public String addToCart(@PathVariable("maSp") String maSp, ModelMap model,HttpServletRequest request) {
//		SanPhamEntity sanPham = sanPhamService.laySanPham(maSp);
//		HttpSession session0= request.getSession();
//		int amount = Integer.parseInt(request.getParameter("soLuong"));
//		NguoiDungEntity user =  (NguoiDungEntity) session0.getAttribute("USER");
//		if(user == null) {
//			model.addAttribute("user", new NguoiDungEntity());
//			System.out.println("Nguoi dung moi");
//			return "user/login";
//		}
//		List<GioHangEntity> productsListInCart = gioHangService.layGioHangCuaUser(user.getMaNd());
//		boolean alreadyInCart = false;
//		for(int i =0; i< productsListInCart.size(); i++) {
//			if(productsListInCart.get(i).getSanPham() ==sanPham) {
//				alreadyInCart = true;
//				break;
//			}
//		}
//		if(alreadyInCart) {
//			GioHangEntity gioHang = gioHangService.layGioHangTheoMaNdVaSanPham(user.getMaNd(), sanPham.getMaSP());
//			gioHang.setSoLuong(gioHang.getSoLuong()+amount);
//			gioHangService.updateGioHang(gioHang);
//			System.out.println("San pham da ton tai trong gio hang");
//		}
//		else {
//			
//			GioHangEntity gioHang = new GioHangEntity();
//			gioHang.setNguoiDung(user);
//			gioHang.setSanPham(sanPham);
//			gioHang.setSoLuong(amount);
//			gioHangService.addGioHang(gioHang);
//			System.out.println("San pham chua ton tại trong gio hang");
//		}
//		  
//		System.out.println("Dang them vao gio");
//		model.addAttribute("sanPham", sanPham);
//		return "sanPham/sanPham";
//	}
//	@RequestMapping("themVaoYT/{maSP}")
//	public String addYeuThich(@PathVariable("maSP") String maSp, ModelMap model, HttpServletRequest request) {
//		SanPhamEntity sanPham = sanPhamService.laySanPham(maSp);
//		HttpSession session0= request.getSession();
//		NguoiDungEntity user =  (NguoiDungEntity) session0.getAttribute("USER");
//		if(user == null) {
//			model.addAttribute("user", new NguoiDungEntity());
//			System.out.println("Nguoi dung moi");
//			return "user/login"; 
//		}
//		System.out.println("them vao YT");
//		List<YeuThichEntity> yeuThichList = yeuThichService.layDSYeuThichCuaUser(user.getMaNd());
//		boolean already = false;
//		for(int i = 0; i < yeuThichList.size(); i++) {
//			if(yeuThichList.get(i).getSanPham() == sanPham) {
//				already = true;
//				break;
//			}
//		}
//		if(already ==false) {
//			YeuThichEntity yeuThich = new YeuThichEntity();
//			yeuThich.setNguoiDung(user);
//			yeuThich.setSanPham(sanPham);
//			yeuThichService.addYeuThich(yeuThich);
//		}
//		List<String> sizes = sanPhamService.laySizeTheoTenSanPham(maSp);
//		model.addAttribute("sizes", sizes);
//		
//		List<SanPhamEntity> sanPhamCungKieu = sanPhamService.laySanPhamCungKieu(maSp);
//		model.addAttribute("sanPhamCungKieu", sanPhamCungKieu);
//		model.addAttribute("sanPham",sanPham);
//		model.addAttribute(yeuThichList);
//		return "sanPham/sanPham";
//	}
//	
//	public class SizeComparator implements Comparator<String> {
//	    @Override
//	    public int compare(String size1, String size2) {
//	        // Xác định thứ tự ưu tiên của các size
//	        List<String> sizeOrder = List.of("S", "M", "L", "XL", "XXL");
//
//	        int index1 = sizeOrder.indexOf(size1);
//	        int index2 = sizeOrder.indexOf(size2);
//
//	        // So sánh dựa trên thứ tự ưu tiên
//	        return Integer.compare(index1, index2);
//	    }
//	}
//}








public class sanPhamController {

    @Autowired
    private SanPhamFacade sanPhamFacade;

    @RequestMapping("/product/{maSp}")
    public String sanPham(@PathVariable("maSp") String maSp, ModelMap model, HttpServletRequest request) {
        SanPhamEntity sanPham = sanPhamFacade.laySanPham(maSp);

        List<String> sizes = sanPhamFacade.laySizeTheoTenSanPham(maSp);
        Collections.sort(sizes, new SizeComparator());
        model.addAttribute("sizes", sizes);

        List<SanPhamEntity> sanPhamCungKieu = sanPhamFacade.laySanPhamCungKieu(maSp);
        sanPhamCungKieu = sanPhamFacade.locSanPhamTrung(sanPhamCungKieu);
        model.addAttribute("sanPhamCungKieu", sanPhamCungKieu);

        List<DanhGiaEntity> listDanhGia = sanPhamFacade.layDanhGiaSanPham(maSp);
        int count = listDanhGia.size();

        model.addAttribute("sanPham", sanPham);
        model.addAttribute("count", count);
        model.addAttribute("danhGiaList", listDanhGia);

        return "/sanPham/sanPham";
    }

    @RequestMapping(value = "themVaoGio/{maSp}")
    public String addToCart(@PathVariable("maSp") String maSp, ModelMap model, HttpServletRequest request) {
        SanPhamEntity sanPham = sanPhamFacade.laySanPham(maSp);
        HttpSession session0 = request.getSession();
        int amount = Integer.parseInt(request.getParameter("soLuong"));
        NguoiDungEntity user = (NguoiDungEntity) session0.getAttribute("USER");
        if (user == null) {
            model.addAttribute("user", new NguoiDungEntity());
            return "user/login";
        }
        List<GioHangEntity> productsListInCart = sanPhamFacade.layGioHangCuaUser(user.getMaNd());
        boolean alreadyInCart = false;
        for (GioHangEntity gioHangEntity : productsListInCart) {
            if (gioHangEntity.getSanPham().equals(sanPham)) {
                alreadyInCart = true;
                break;
            }
        }
        if (alreadyInCart) {
            GioHangEntity gioHang = sanPhamFacade.layGioHangTheoMaNdVaSanPham(user.getMaNd(), sanPham.getMaSP());
            gioHang.setSoLuong(gioHang.getSoLuong() + amount);
            sanPhamFacade.updateGioHang(gioHang);
        } else {
            GioHangEntity gioHang = new GioHangEntity();
            gioHang.setNguoiDung(user);
            gioHang.setSanPham(sanPham);
            gioHang.setSoLuong(amount);
            sanPhamFacade.addGioHang(gioHang);
        }

        model.addAttribute("sanPham", sanPham);
        return "sanPham/sanPham";
    }

    @RequestMapping("themVaoYT/{maSP}")
    public String addYeuThich(@PathVariable("maSP") String maSp, ModelMap model, HttpServletRequest request) {
        SanPhamEntity sanPham = sanPhamFacade.laySanPham(maSp);
        HttpSession session0 = request.getSession();
        NguoiDungEntity user = (NguoiDungEntity) session0.getAttribute("USER");
        if (user == null) {
            model.addAttribute("user", new NguoiDungEntity());
            return "user/login";
        }
        List<YeuThichEntity> yeuThichList = sanPhamFacade.layDSYeuThichCuaUser(user.getMaNd());
        boolean already = false;
        for (YeuThichEntity yeuThichEntity : yeuThichList) {
            if (yeuThichEntity.getSanPham().equals(sanPham)) {
                already = true;
                break;
            }
        }
        if (!already) {
            YeuThichEntity yeuThich = new YeuThichEntity();
            yeuThich.setNguoiDung(user);
            yeuThich.setSanPham(sanPham);
            sanPhamFacade.addYeuThich(yeuThich);
        }
        List<String> sizes = sanPhamFacade.laySizeTheoTenSanPham(maSp);
        model.addAttribute("sizes", sizes);

        List<SanPhamEntity> sanPhamCungKieu = sanPhamFacade.laySanPhamCungKieu(maSp);
        model.addAttribute("sanPhamCungKieu", sanPhamCungKieu);
        model.addAttribute("sanPham", sanPham);
        model.addAttribute("yeuThichList", yeuThichList);
        return "sanPham/sanPham";
    }

    public class SizeComparator implements Comparator<String> {
        @Override
        public int compare(String size1, String size2) {
            // Xác định thứ tự ưu tiên của các size
            List<String> sizeOrder = List.of("S", "M", "L", "XL", "XXL");

            int index1 = sizeOrder.indexOf(size1);
            int index2 = sizeOrder.indexOf(size2);

            // So sánh dựa trên thứ tự ưu tiên
            return Integer.compare(index1, index2);
        }
    }
}
