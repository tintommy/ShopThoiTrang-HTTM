package ptithcm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.entity.DanhGiaEntity;
import ptithcm.entity.SanPhamEntity;
import ptithcm.service.DanhGiaService;
import ptithcm.service.SanPhamService;

@Transactional
@Controller
@RequestMapping()
public class sanPhamController {
	
	@Autowired
	SanPhamService sanPhamService;
	@Autowired
	DanhGiaService danhGiaService;
	
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
	
		
}
