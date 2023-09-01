package ptithcm.controller;

import java.util.List;

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

import ptithcm.entity.LoaiSanPhamEntity;
import ptithcm.service.loaiSanPhamService;


@Transactional
@Controller
public class mainController {

	@Autowired
	loaiSanPhamService loaiSanPhamService;

	@RequestMapping()
	public String main(HttpServletRequest request, ModelMap model) {

		List<LoaiSanPhamEntity> loaiSPNam = loaiSanPhamService.layLoaiTheoGioiTinh("nam");
		model.addAttribute("loaiSPNam", loaiSPNam);
		List<LoaiSanPhamEntity> loaiSPNu = loaiSanPhamService.layLoaiTheoGioiTinh("nữ");
		model.addAttribute("loaiSPNu", loaiSPNu);
		return "main";
	}

	@RequestMapping("khongCoQuyen")
	public String khongCoQuyen() {
		return "error/403";
	}

}
