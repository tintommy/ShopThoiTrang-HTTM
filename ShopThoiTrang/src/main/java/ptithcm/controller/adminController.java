package ptithcm.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.cfg.annotations.MapKeyColumnDelegator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import ptithcm.designpattern.SingletonPattern.ConstraintSingleton;

import ptithcm.designpattern.Strategy.Statistics.MonthlyRevenue;
import ptithcm.designpattern.Strategy.Statistics.OrderStatusStatistics;
import ptithcm.designpattern.Strategy.Statistics.StatisticsContext;
import ptithcm.designpattern.Strategy.Statistics.TotalSuccessOrder;
import ptithcm.designpattern.Strategy.Statistics.UserStatistics;

import ptithcm.entity.DonHangEntity;

import ptithcm.entity.NguoiDungEntity;

import ptithcm.service.DonHangService;

import ptithcm.service.SanPhamService;
import ptithcm.service.nguoiDungService;

@Transactional
@Controller

public class adminController {

	@Autowired
	SanPhamService sanPhamService;

	@Autowired
	DonHangService donHangService;

	@Autowired
	nguoiDungService nguoiDungService;

	@RequestMapping("admin/index")
	public String index(HttpServletRequest request, ModelMap model) {
		HttpSession session0 = request.getSession();
		NguoiDungEntity user = (NguoiDungEntity) session0.getAttribute("USER");


		StatisticsContext context = new StatisticsContext();

		// Thống kê đơn thành công
		context.setStrategy(new TotalSuccessOrder(donHangService));
		model.addAttribute("tongDoanhThu", context.executeStrategy());

//		// Tính tổng doanh thu theo từng tháng
		context.setStrategy(new MonthlyRevenue(donHangService));
		model.addAttribute("monthlyRevenues", context.executeStrategy());

		// Thống kê số người dùng

		context.setStrategy(new UserStatistics(nguoiDungService));
		model.addAttribute("tongSoNguoiDung", context.executeStrategy());

//	    // Thống kêsố đơn hàng
		context.setStrategy(new OrderStatusStatistics(donHangService));
		List<Integer> tongDon = (List<Integer>) context.executeStrategy();

		model.addAttribute("cancelledOrders", tongDon.get(0));
		model.addAttribute("pendingOrders", tongDon.get(1));
		model.addAttribute("deliveringOrders", tongDon.get(2));
		model.addAttribute("completedOrders", tongDon.get(3));

//
//		List<DonHangEntity> donThanhCong = donHangService.layDonHangTheoTrangThai(3);
//		int tongDoanhThu = 0;
//		for (DonHangEntity donHang : donThanhCong) {
//		    tongDoanhThu += donHang.getTongTien();
//		}
//	
//		model.addAttribute("tongDoanhThu", tongDoanhThu);
//		
//		// Tính tổng doanh thu theo từng tháng
//	    List<Long> monthlyRevenues = new ArrayList<>();
//	    for (int i = 1; i <= 12; i++) {
//	        long totalRevenue = donHangService.tinhTongDoanhThuTheoThang(i);
//	        monthlyRevenues.add(totalRevenue);
//	    }
//
//	    model.addAttribute("monthlyRevenues", monthlyRevenues);
//		
//		//Thống kê số người dùng
//		
//		List<NguoiDungEntity> listNguoiDung = nguoiDungService.getAllUserByRole(0);
//		int tongSoNguoiDung=listNguoiDung.size();
//		model.addAttribute("tongSoNguoiDung", tongSoNguoiDung);
//		
//	    // Thống kêsố đơn hàng
//		int tongDonChoXacNhan= donHangService.layDonHangTheoTrangThai(1).size();
//		int tongDonDangGiao= donHangService.layDonHangTheoTrangThai(2).size();
//		int tongDonThanhCong= donHangService.layDonHangTheoTrangThai(3).size();
//		int tongDonDaHuy= donHangService.layDonHangTheoTrangThai(0).size();
//		
//		model.addAttribute("pendingOrders", tongDonChoXacNhan);
//	    model.addAttribute("deliveringOrders", tongDonDangGiao);
//	    model.addAttribute("completedOrders", tongDonThanhCong);
//	    model.addAttribute("cancelledOrders", tongDonDaHuy);

		return "admin/index";
	}



}
