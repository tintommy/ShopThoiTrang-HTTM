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
	
	@Autowired
	private SessionFactory sessionFactory;
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
		
		List<SanPhamEntity> listNgauNhien = sanPhamService.laySanPhamNgauNhien();
		listNgauNhien = sanPhamService.locSanPhamTrung(listNgauNhien);
		 model.addAttribute("sanPhamNgauNhien", listNgauNhien);
		List<SanPhamEntity> listMoi = sanPhamService.laySanPhamMoi();
		listMoi = sanPhamService.locSanPhamTrung(listMoi);
		model.addAttribute("sanPhamMoi", listMoi);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		List<String>dsLink = new ArrayList<>();		;
		dsLink.add("assets/img/product/ao/ao-polo-unique.jpg");
		dsLink.add("assets/img/product/ao/BODKEY.jpg");
		dsLink.add("assets/img/product/quan/quanjeannu.jpg");
		model.addAttribute("dsLink", dsLink);
		model.addAttribute("SP1",dsLink.get(0));
		List<SanPhamEntity> dsSPGY= dsSPGoiY(dsLink);
		dsSPGY = sanPhamService.locSanPhamTrung(dsSPGY);
		model.addAttribute("dsSPGY",dsSPGY);
		return "main";
	}

	@RequestMapping("khongCoQuyen")
	public String khongCoQuyen() {
		return "redirect:/user/login.htm";
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
	
	
	
	public List<SanPhamEntity> dsSPGoiY(List<String> listLink){
	
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM SanPhamEntity sp WHERE trangThai=True AND sp.hinhAnh.link IN :listLink";
//		if (stylesList != null && !stylesList.isEmpty()) {
//			hql += "AND sp.maKieu.tenKieu IN :stylesList ";
//		}
//		if (minPrice >= 0 && maxPrice >= 0) {
//			hql += "AND sp.donGia >= :minPrice AND sp.donGia <= :maxPrice ";
//		}
//
		Query query = session.createQuery(hql);
		query.setParameterList("listLink", listLink);
//		if (stylesList != null && !stylesList.isEmpty()) {
//			query.setParameterList("stylesList", stylesList);
//		}
//		if (minPrice >= 0 && maxPrice >= 0) {
//			query.setParameter("minPrice", minPrice).setParameter("maxPrice", maxPrice);
//		}
		List<SanPhamEntity> listSPGoiY = query.list();
		return listSPGoiY;
		
		
	}

}
