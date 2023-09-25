package ptithcm.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ptithcm.entity.KieuSanPhamEntity;
import ptithcm.entity.LoaiSanPhamEntity;
import ptithcm.entity.SanPhamEntity;
import ptithcm.service.KieuSanPhamService;
import ptithcm.service.SanPhamService;
import ptithcm.service.loaiSanPhamService;


@Transactional
@Controller
@RequestMapping("shop")
public class loaiSanPhamController {

	@Autowired
	SanPhamService sanPhamService;
	@Autowired
	loaiSanPhamService loaiService;
	@Autowired
	KieuSanPhamService kieuSanPhamService;
	

	@RequestMapping()
	public String main(HttpServletRequest request, ModelMap model,
			@RequestParam(defaultValue = "0") int page) {
//		List<LoaiSanPhamEntity> loaiSPNam = loaiService.layLoaiTheoGioiTinh("nam");
//		List<LoaiSanPhamEntity> loaiSPNu = loaiService.layLoaiTheoGioiTinh("nữ");		
//		model.addAttribute("loaiSPNam", loaiSPNam);
//		model.addAttribute("loaiSPNu", loaiSPNu);
		
		List<LoaiSanPhamEntity> dsLoai = loaiService.layLoai();
		model.addAttribute("dsLoai", dsLoai);
	
		List<SanPhamEntity> dsSP = sanPhamService.layAllSanPham();
		model.addAttribute("dsSP", dsSP);			
		List<KieuSanPhamEntity>dsKieu= kieuSanPhamService.layKieu();
		model.addAttribute("dsKieu",dsKieu);
		int pageSize = 6;
		int totalLoaiSanPham = dsSP.size();
		int totalPages = (int) Math.ceil((double) totalLoaiSanPham / pageSize);
		if (totalPages==0) return "shop/loai";
		int startPage = Math.max(0, page - 1);
		int endPage = Math.min(totalPages - 1, page + 1);

		model.addAttribute("currentPage", page);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalLoaiSanPham", totalLoaiSanPham);
		model.addAttribute("totalPages", totalPages);
		List<SanPhamEntity> listSPTrenTrang = sanPhamService.LaySanPhamMotTrang( page, pageSize);
		model.addAttribute("listSPTrenTrang", listSPTrenTrang);
		
		return "shop/tatCa";
	}
	
	@RequestMapping(value = "/{loaiSp}")
	public String shopTheoLoai(@PathVariable("loaiSp") String loaiSp, ModelMap model,
			@RequestParam(defaultValue = "0") int page) {
			
		List<LoaiSanPhamEntity> dsLoai = loaiService.layLoai();
		model.addAttribute("dsLoai", dsLoai);
	
		List<SanPhamEntity> dsSP = sanPhamService.layAllSanPhamTheoLoai(loaiSp);
		model.addAttribute("dsSP", dsSP);			
		List<KieuSanPhamEntity>dsKieu= kieuSanPhamService.layKieu();
		model.addAttribute("dsKieu",dsKieu);
		int pageSize = 6;
		int totalLoaiSanPham = dsSP.size();
		int totalPages = (int) Math.ceil((double) totalLoaiSanPham / pageSize);		
		int startPage = Math.max(0, page - 1);
		int endPage = Math.min(totalPages - 1, page + 1);
		model.addAttribute("currentPage", page);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalLoaiSanPham", totalLoaiSanPham);
		model.addAttribute("totalPages", totalPages);
		List<SanPhamEntity> listSPTrenTrang = sanPhamService.LaySanPhamMotTrangTheoLoai(loaiSp, page, pageSize);
		model.addAttribute("listSPTrenTrang", listSPTrenTrang);
		
		return "shop/loai";
	}
	
	@RequestMapping(value = "/{loaiSp}/{kieuSp}")
	public String shopTheoKieu(@PathVariable("loaiSp") String loaiSp,
			@PathVariable("kieuSp") String kieuSp,
			ModelMap model,
			@RequestParam(defaultValue = "0") int page) {
			
		model.addAttribute("kieuSp", kieuSp);
		List<LoaiSanPhamEntity> dsLoai = loaiService.layLoai();
		model.addAttribute("dsLoai", dsLoai);
	
		List<SanPhamEntity> dsSP = sanPhamService.layAllSanPhamTheoLoai(loaiSp);
		model.addAttribute("dsSP", dsSP);			
		List<KieuSanPhamEntity>dsKieu= kieuSanPhamService.layKieu();
		model.addAttribute("dsKieu",dsKieu);
		int pageSize = 6;
		int totalLoaiSanPham = dsSP.size();
		int totalPages = (int) Math.ceil((double) totalLoaiSanPham / pageSize);		
		int startPage = Math.max(0, page - 1);
		int endPage = Math.min(totalPages - 1, page + 1);
		model.addAttribute("currentPage", page);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalLoaiSanPham", totalLoaiSanPham);
		model.addAttribute("totalPages", totalPages);
		List<SanPhamEntity> listSPTrenTrang = sanPhamService.LaySanPhamMotTrangTheoLoai(loaiSp, page, pageSize);
		model.addAttribute("listSPTrenTrang", listSPTrenTrang);
		
		return "shop/loai";
	}
	
	@RequestMapping(value = "/{loaiSp}", params = "btnApply", method = RequestMethod.POST)
	public String locSanPham(@PathVariable("loaiSp") String loaiSp, ModelMap model, HttpServletRequest request,
			@RequestParam(value = "style", required = false) List<String> stylesList,
			@RequestParam(value = "minPrice", defaultValue = "0") int minPrice,
			@RequestParam(value = "maxPrice", defaultValue = "999999999") int maxPrice,
			@RequestParam(defaultValue = "0") int page)
	// @RequestParam(value="rating", required=false) Integer rating)
	{
		
		List<LoaiSanPhamEntity> dsLoai = loaiService.layLoai();
		model.addAttribute("dsLoai", dsLoai);
	
		List<SanPhamEntity> dsSP = sanPhamService.layAllSanPhamTheoLoai(loaiSp);
		model.addAttribute("dsSP", dsSP);			
		
		List<SanPhamEntity> dsSPdaloc = sanPhamService.locSanPham(stylesList, minPrice, maxPrice);
		List<KieuSanPhamEntity>dsKieu = kieuSanPhamService.layKieu();
		model.addAttribute("dsKieu", dsKieu);
		model.addAttribute("selectedStyles", stylesList);
		model.addAttribute("minPrice", minPrice);
		model.addAttribute("maxPrice", maxPrice);
		model.addAttribute("dsSPdaloc", dsSPdaloc);
		// model.addAttribute("selectedRating", rating);
		if (dsSPdaloc.isEmpty()) {
			model.addAttribute("message", "Hiện không có sản phẩm cần tìm");
			return "shop/locSanPham";
		}
		return "shop/locSanPham";
	}
	
	@RequestMapping(params = "btnSearch")
	public String timSanPham(ModelMap model, @RequestParam(value = "key", required = false) String key,
			HttpServletRequest request) {
       List<SanPhamEntity>listSP=sanPhamService.laySanPhamTheoMa(key);
       model.addAttribute("key", key);
       model.addAttribute("listSP", listSP);
       int so = listSP.size();
       model.addAttribute("soLuong", so);
       String mess =""; 
       if (listSP.isEmpty()) mess = "Không có sản phẩm này";
       else {
    	    mess = "Tìm thấy "+so+" sản phẩm";
    	   
	}
       model.addAttribute("message", mess);	
        return "shop/timKiem";
	}

//	
//	@RequestMapping(value = "/{loaiSp}/{kieuSp}")
//	public String shopTheoKieu(@PathVariable("loaiSp") String loaiSp,@PathVariable("kieuSp") String kieuSp, ModelMap model) {
//		List<LoaiSanPhamEntity> dsLoai = loaiService.layLoai();
//		model.addAttribute("dsLoai", dsLoai);
//		List<SanPhamEntity> dsSP = sanPhamService.layAllSanPhamTheoLoai(loaiSp);
//		model.addAttribute("dsSP", dsSP);
//		List<KieuSanPhamEntity>dsKieu= kieuSanPhamService.layKieuTheoLoai(loaiSp);
//		model.addAttribute("dsKieu",dsKieu);
//		
//		return "shop/loai";
//	}
}