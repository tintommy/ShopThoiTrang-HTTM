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

import ptithcm.DesignPattern.Strategy.Statistics.MonthlyRevenue;
import ptithcm.DesignPattern.Strategy.Statistics.OrderStatusStatistics;
import ptithcm.DesignPattern.Strategy.Statistics.StatisticsContext;
import ptithcm.DesignPattern.Strategy.Statistics.TotalSuccessOrder;
import ptithcm.DesignPattern.Strategy.Statistics.UserStatistics;

import ptithcm.entity.DonHangEntity;

import ptithcm.entity.NguoiDungEntity;

import ptithcm.service.DonHangService;

import ptithcm.service.SanPhamService;
import ptithcm.service.nguoiDungService;

@Transactional
@Controller
@RequestMapping("/admin/")
public class adminController {

	@Autowired
	SanPhamService sanPhamService;

	@Autowired
	DonHangService donHangService;

	@Autowired
	nguoiDungService nguoiDungService;

	@RequestMapping("index")
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

	@RequestMapping("adminAccount")
	public String account(HttpServletRequest request, ModelMap model) {
		HttpSession session0 = request.getSession();
		List<NguoiDungEntity> adminList = nguoiDungService.getAllUserByRole(1);

		Collections.sort(adminList, new Comparator<NguoiDungEntity>() {
			@Override
			public int compare(NguoiDungEntity nguoi1, NguoiDungEntity nguoi2) {
				boolean trangThai1 = nguoi1.isTrangThai();
				boolean trangThai2 = nguoi2.isTrangThai();

				// Sắp xếp theo thứ tự giảm dần (true trước, false sau)
				return Boolean.compare(trangThai2, trangThai1);
			}
		});

		model.addAttribute("adminList", adminList);
		return "admin/adminAccount";
	}

	@RequestMapping("inforAcc/{maNd}")
	public String inforAcc(@PathVariable("maNd") int maNd, HttpServletRequest request, ModelMap model) {
		HttpSession session0 = request.getSession();
		NguoiDungEntity user = nguoiDungService.findUserById(maNd);
		model.addAttribute("user", user);

		return "admin/inforAcc";
	}

	@RequestMapping(value = "changeInfo", params = "save", method = RequestMethod.POST)
	public String infoUpdate(HttpServletRequest request, ModelMap model, @ModelAttribute("admin") NguoiDungEntity admin,
			BindingResult errors) {
		Boolean loi = Boolean.TRUE;
		HttpSession session = request.getSession();
		NguoiDungEntity adminSave = (NguoiDungEntity) session.getAttribute("USER");
		admin.setUserName(adminSave.getUserName());

		String NGAYSINH = request.getParameter("ngaySinh");
		java.sql.Date ns = java.sql.Date.valueOf(NGAYSINH);
		// Kiểm tra họ tên sử dụng Singleton
		if (!ConstraintSingleton.getInstance().checkHoTen(admin.getHoTen(), model, "loiHoTen")) {
			loi = Boolean.FALSE;
		}
		// Kiểm tra ngày sinh sử dụng Singleton
		if (!ConstraintSingleton.getInstance().checkNgaySinh(admin.getNgaySinh(), model, "loiNgaySinh")) {
			loi = Boolean.FALSE;
		}
		// Kiểm tra địa chỉ
		if (!ConstraintSingleton.getInstance().checkDiaChi(admin.getDiaChi(), model, "loiDiaChi")) {
			loi = Boolean.FALSE;
		}

		// Kiểm tra số điện thoại
		if (!ConstraintSingleton.getInstance().checkPhoneNumber(admin.getSdt(), model, "loiSdt")) {

			loi = Boolean.FALSE;
		}
		
		
		// Kiểm tra email
		if (!ConstraintSingleton.getInstance().checkEmail(admin.getEmail(), model, "loiEmail")) {
			loi = Boolean.FALSE;
		}

		if (loi == Boolean.FALSE) {
			model.addAttribute("admin", adminSave);
			return "/admin/me";
		}
		adminSave.setHoTen(admin.getHoTen());
		adminSave.setGioiTinh(admin.isGioiTinh());
		adminSave.setNgaySinh(ns);
		adminSave.setDiaChi(admin.getDiaChi());
		adminSave.setEmail(admin.getEmail());
		adminSave.setSdt(admin.getSdt());
		session.setAttribute("USER", adminSave);

		nguoiDungService.updateUser(adminSave);

		model.addAttribute("admin", adminSave);
		model.addAttribute("successMessage", "Cập nhật thông tin thành công");

		return "/admin/me";
	}

	@RequestMapping("changePass")
	public String changPass() {
		return "/admin/changePass";

	}

	@RequestMapping(value = "changePass.htm", params = "save", method = RequestMethod.POST)
	public String changePass(HttpServletRequest request, ModelMap model) {
		Boolean loi = Boolean.TRUE;
		HttpSession session = request.getSession();
		NguoiDungEntity user = (NguoiDungEntity) session.getAttribute("USER");
		String pass = request.getParameter("pass");
		String newPass = request.getParameter("newPass");
		String reNewPass = request.getParameter("renewPass");

		if (pass.isEmpty()) {
			model.addAttribute("loiPass", "Hãy nhập mật khẩu cũ !!!");
			loi = Boolean.FALSE;

		} else if (!nguoiDungService.kiemTraMatKhau(pass, user.getPassWord())) {
			model.addAttribute("loiPass", "Mật khẩu cũ không đúng !!!");
			return "/admin/changePass";
		}
		
		
		// Check new password Singleton
		if (!ConstraintSingleton.getInstance().checkNewPassword(newPass, reNewPass, model, "loiNewPass", "loiRePass")) {
			loi = Boolean.FALSE;
		}


		if (loi == Boolean.TRUE) {
			user.setPassWord(nguoiDungService.maHoaMatKhau(newPass));

			nguoiDungService.updateUser(user);
			model.addAttribute("successMessage", "Đổi mật khẩu thành công !!!");

		}

		return "/admin/changePass";
	}

	@RequestMapping("createAcc")
	public String createAcc(HttpServletRequest request, ModelMap model) {
		NguoiDungEntity admin = new NguoiDungEntity();
		model.addAttribute("admin", admin);
		return "admin/createAcc";
	}

	@RequestMapping(value = "/form/addAcc", params = "add", method = RequestMethod.POST)
	public String addAcc(HttpServletRequest request, ModelMap model, @ModelAttribute("admin") NguoiDungEntity admin,
			BindingResult errors) throws ParseException {

		Boolean loi = Boolean.TRUE;
		String rePassword = request.getParameter("re-passWord");
		NguoiDungEntity userCheck;

		
		// Kiểm tra email
		if (!ConstraintSingleton.getInstance().checkEmail(admin.getEmail(), errors, "email", "user")) {
			loi = Boolean.FALSE;
		}
			
		// Kiểm tra username
		if (!ConstraintSingleton.getInstance().checkUsername(admin.getUserName(), errors, "userName", "user")) {
			loi = Boolean.FALSE;
		}


		userCheck = nguoiDungService.findUserByNameAndEmail(admin.getUserName(), admin.getEmail());
		if (userCheck != null) {
			if (userCheck.getEmail().equals(admin.getEmail())) {
				errors.rejectValue("email", "user", "Email đã được sử dụng !!!");
				loi = Boolean.FALSE;
			}
			if (userCheck.getUserName().equals(admin.getUserName())) {
				errors.rejectValue("userName", "user", "Username đã được sử dụng !!!");
				loi = Boolean.FALSE;
			}

		}

		// Kiểm tra mật khẩu
		if (!ConstraintSingleton.getInstance().checkPasword(admin.getPassWord(), rePassword, errors, "passWord", "user")) {
			loi = Boolean.FALSE;
		}
		// Kiểm tra họ tên
		if (!ConstraintSingleton.getInstance().checkHoTen(admin.getHoTen(), errors, "hoTen", "user")) {
			loi = Boolean.FALSE;
		}
		
		
		// Kiểm tra ngày sinh
		if (!ConstraintSingleton.getInstance().checkNgaySinh(admin.getNgaySinh(), errors, "ngaySinh", "user")) {
			loi = Boolean.FALSE;
		}
		// Kiểm tra số điện thoại
		if (!ConstraintSingleton.getInstance().checkPhoneNumber(admin.getSdt(), errors, "sdt", "user")) {
			loi = Boolean.FALSE;
		}


		if (loi == Boolean.FALSE) {
			model.addAttribute("errorMessage", "Lỗi thêm Admin");

			return "/admin/createAcc";
		}


		String NGAYSINH = request.getParameter("ngaySinh");
		java.sql.Date ns = java.sql.Date.valueOf(NGAYSINH);
		admin.setPassWord(nguoiDungService.maHoaMatKhau(admin.getPassWord()));
		admin.setNgaySinh(ns);
		admin.setHoTen(ConstraintSingleton.getInstance().capitalizeString(admin.getHoTen()));
		admin.setTrangThai(true);
		admin.setQuyen(1);
		nguoiDungService.addUser(admin);
		model.addAttribute("successMessage", "Thêm Admin mới thành công");
		return "/admin/createAcc";
	}

	@RequestMapping("on/{maNd}")
	public String on(@PathVariable("maNd") int maNd, HttpServletRequest request, ModelMap model) {
		HttpSession session0 = request.getSession();
		NguoiDungEntity user = nguoiDungService.findUserById(maNd);
		user.setTrangThai(true);
		nguoiDungService.updateUser(user);

		if (user.getQuyen() == 0)
			return "redirect:/admin/customerAccount.htm";

		return "redirect:/admin/adminAccount.htm";
	}

	@RequestMapping("off/{maNd}")
	public String off(@PathVariable("maNd") int maNd, HttpServletRequest request, ModelMap model) {
		HttpSession session0 = request.getSession();
		NguoiDungEntity user = nguoiDungService.findUserById(maNd);
		user.setTrangThai(false);
		nguoiDungService.updateUser(user);
		if (user.getQuyen() == 0)
			return "redirect:/admin/customerAccount.htm";

		return "redirect:/admin/adminAccount.htm";
	}

	@RequestMapping("customerAccount")
	public String cusAccount(HttpServletRequest request, ModelMap model) {
		HttpSession session0 = request.getSession();
		List<NguoiDungEntity> cusList = nguoiDungService.getAllUserByRole(0);

		Collections.sort(cusList, new Comparator<NguoiDungEntity>() {
			@Override
			public int compare(NguoiDungEntity nguoi1, NguoiDungEntity nguoi2) {
				boolean trangThai1 = nguoi1.isTrangThai();
				boolean trangThai2 = nguoi2.isTrangThai();

				// Sắp xếp theo thứ tự giảm dần (true trước, false sau)
				return Boolean.compare(trangThai2, trangThai1);
			}
		});

		model.addAttribute("userList", cusList);
		return "admin/customerAccount";
	}

	@RequestMapping("me")
	public String me(HttpServletRequest request, ModelMap model) {
		HttpSession session0 = request.getSession();
		NguoiDungEntity admin = (NguoiDungEntity) session0.getAttribute("USER");
		model.addAttribute("admin", admin);
		return "admin/me";
	}


}
