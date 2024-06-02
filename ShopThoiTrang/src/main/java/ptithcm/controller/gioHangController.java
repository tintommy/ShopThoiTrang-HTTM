package ptithcm.controller;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ptithcm.entity.GioHangEntity;
import ptithcm.entity.LoaiSanPhamEntity;
import ptithcm.entity.NguoiDungEntity;
import ptithcm.entity.SanPhamEntity;
import ptithcm.service.gioHangService;

import ptithcm.designpattern.FacadePattern.GioHangFacade;

//@Transactional
//@Controller
//
//public class gioHangController {
//	@Autowired
//	SessionFactory factory;
//
//	@Autowired
//	gioHangService gioHangService;
//
//	
//	@RequestMapping("gioHang")
//	public String gioHang(HttpServletRequest request, ModelMap model) {
//
//		HttpSession session0 = request.getSession();
//
//		NguoiDungEntity user = (NguoiDungEntity) session0.getAttribute("USER");
//
//		if (user == null) {
//			model.addAttribute("user", new NguoiDungEntity());
//
//			return "/user/login";
//		}
//
//	
//		List<GioHangEntity> gioHangList = gioHangService.layGioHangCuaUser(user.getMaNd());
//
//		model.addAttribute("gioHangList", gioHangList);
//		model.addAttribute("user", user);
//		return "/gioHang/gioHang";
//	}
//	
//	
//	@RequestMapping(value = "/gioHang/{maGh}", params = "update", method = RequestMethod.POST)
//	public String update(@PathVariable("maGh") int maGh, HttpServletRequest request, ModelMap model) {
//		System.out.println("gbhjnh");
//		HttpSession session0 = request.getSession();
//		NguoiDungEntity user = (NguoiDungEntity) session0.getAttribute("USER");
//
//		int soLuong = Integer.parseInt(request.getParameter("soLuong"));
//System.out.println(soLuong);
//		gioHangService.updateSoLuong(soLuong, maGh);
//
//	
//
//		return "redirect:/gioHang.htm";
//	}
//
//	@RequestMapping(value = "/gioHang/{maGh}", params = "xoa", method = RequestMethod.POST)
//	public String xoa(@PathVariable("maGh") int maGh, HttpServletRequest request, ModelMap model) {
//		HttpSession session0 = request.getSession();
//		NguoiDungEntity user = (NguoiDungEntity) session0.getAttribute("USER");
//
//		gioHangService.deleteGioHang(maGh);
//		
//		return "redirect:/gioHang.htm";
//
//	}
//
//
//	public SanPhamEntity laySanPhamTheoMa(String maSp) {
//		Session session = factory.getCurrentSession();
//		String hql = "FROM SanPhamEntity WHERE id = :maSp";
//		Query query = session.createQuery(hql);
//		query.setParameter("maSp", maSp);
//		SanPhamEntity sp = (SanPhamEntity) query.uniqueResult();
//		Hibernate.initialize(sp.getHinhAnh());
//		return sp;
//	}
//
//	public List<SanPhamEntity> laySanPhamCungLoai(LoaiSanPhamEntity loaiSanPham, String maSp) {
//		Session session = factory.getCurrentSession();
//		String hql = "FROM SanPhamEntity WHERE loaiSanPham = :loaiSanPham AND id != :maSp";
//		Query query = session.createQuery(hql);
//		query.setParameter("loaiSanPham", loaiSanPham);
//		query.setParameter("maSp", maSp);
//		query.setMaxResults(3);
//		List<SanPhamEntity> sanPhamCungLoai = query.list();
//		return sanPhamCungLoai;
//	}
//}






@Transactional
@Controller
public class gioHangController {

    @Autowired
    private GioHangFacade gioHangFacade;

    @RequestMapping("gioHang")
    public String gioHang(HttpServletRequest request, ModelMap model) {
        HttpSession session0 = request.getSession();
        NguoiDungEntity user = (NguoiDungEntity) session0.getAttribute("USER");

        if (user == null) {
            model.addAttribute("user", new NguoiDungEntity());
            return "/user/login";
        }

        List<GioHangEntity> gioHangList = gioHangFacade.layGioHangCuaUser(user.getMaNd());
        model.addAttribute("gioHangList", gioHangList);
        model.addAttribute("user", user);
        return "/gioHang/gioHang";
    }

    @RequestMapping(value = "/gioHang/{maGh}", params = "update", method = RequestMethod.POST)
    public String update(@PathVariable("maGh") int maGh, HttpServletRequest request, ModelMap model) {
        HttpSession session0 = request.getSession();
        NguoiDungEntity user = (NguoiDungEntity) session0.getAttribute("USER");

        int soLuong = Integer.parseInt(request.getParameter("soLuong"));
        gioHangFacade.updateSoLuong(soLuong, maGh);

        return "redirect:/gioHang.htm";
    }

    @RequestMapping(value = "/gioHang/{maGh}", params = "xoa", method = RequestMethod.POST)
    public String xoa(@PathVariable("maGh") int maGh, HttpServletRequest request, ModelMap model) {
        HttpSession session0 = request.getSession();
        NguoiDungEntity user = (NguoiDungEntity) session0.getAttribute("USER");

        gioHangFacade.deleteGioHang(maGh);
        return "redirect:/gioHang.htm";
    }

}