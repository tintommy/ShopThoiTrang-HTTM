package ptithcm.controller;

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
import ptithcm.service.SanPhamService;
import ptithcm.service.loaiSanPhamService;

@Transactional
@Controller
public class mainController {

	@Autowired
	loaiSanPhamService loaiSanPhamService;

	@Autowired
	SanPhamService sanPhamService;

	@RequestMapping()
	public String main(HttpServletRequest request, ModelMap model) {

		List<LoaiSanPhamEntity> loaiSPNam = loaiSanPhamService.layLoaiTheoGioiTinh("nam");
		model.addAttribute("loaiSPNam", loaiSPNam);
		List<LoaiSanPhamEntity> loaiSPNu = loaiSanPhamService.layLoaiTheoGioiTinh("nữ");
		model.addAttribute("loaiSPNu", loaiSPNu);
	

		List<SanPhamEntity> listSpNam = sanPhamService.laySanPhamTheogioiTinh("nam");
		listSpNam = sanPhamService.locSanPhamTrung(listSpNam);
//		listSpNam=locSanPhamTrung(listSpNam);
		model.addAttribute("listSpNam", listSpNam);
		List<SanPhamEntity> listSpNu = sanPhamService.laySanPhamTheogioiTinh("nữ");
		listSpNu = sanPhamService.locSanPhamTrung(listSpNu);
//		listSpNu=locSanPhamTrung(listSpNu);
		model.addAttribute("listSpNu", listSpNu);
		model.addAttribute("user", new NguoiDungEntity());
		return "main";
	}

	@RequestMapping("khongCoQuyen")
	public String khongCoQuyen() {
		return "error/403";
	}

	public List<SanPhamEntity> locSanPhamTrung(List<SanPhamEntity> list) {
		Set<String> uniqueSet = new HashSet<>();
		List<SanPhamEntity> result = new ArrayList<>();
		for (SanPhamEntity sanPham : list) {

			if (!uniqueSet.contains(sanPham.getTenSanPham())) {
				result.add(sanPham);
				uniqueSet.add(sanPham.getTenSanPham());
			}
		}
		return result;
	}

}
