package ptithcm.controller;

import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ptithcm.designpattern.FacadePattern.NguoiDungFacade;
import ptithcm.designpattern.SingletonPattern.ConstraintSingleton;
import ptithcm.entity.NguoiDungEntity;
import ptithcm.service.nguoiDungService;

//@Transactional
//@Controller
//@RequestMapping("admin/")
//public class quanLyTaiKhoanController {
//	@Autowired
//	nguoiDungService nguoiDungService;
//	
//	@RequestMapping("adminAccount")
//	public String account(HttpServletRequest request, ModelMap model) {
//		HttpSession session0 = request.getSession();
//		List<NguoiDungEntity> adminList = nguoiDungService.getAllUserByRole(1);
//
//		Collections.sort(adminList, new Comparator<NguoiDungEntity>() {
//			@Override
//			public int compare(NguoiDungEntity nguoi1, NguoiDungEntity nguoi2) {
//				boolean trangThai1 = nguoi1.isTrangThai();
//				boolean trangThai2 = nguoi2.isTrangThai();
//
//				// Sắp xếp theo thứ tự giảm dần (true trước, false sau)
//				return Boolean.compare(trangThai2, trangThai1);
//			}
//		});
//
//		model.addAttribute("adminList", adminList);
//		return "admin/adminAccount";
//	}
//
//	@RequestMapping("inforAcc/{maNd}")
//	public String inforAcc(@PathVariable("maNd") int maNd, HttpServletRequest request, ModelMap model) {
//		HttpSession session0 = request.getSession();
//		NguoiDungEntity user = nguoiDungService.findUserById(maNd);
//		model.addAttribute("user", user);
//
//		return "admin/inforAcc";
//	}
//
//	@RequestMapping(value = "changeInfo", params = "save", method = RequestMethod.POST)
//	public String infoUpdate(HttpServletRequest request, ModelMap model, @ModelAttribute("admin") NguoiDungEntity admin,
//			BindingResult errors) {
//		Boolean loi = Boolean.TRUE;
//		HttpSession session = request.getSession();
//		NguoiDungEntity adminSave = (NguoiDungEntity) session.getAttribute("USER");
//		admin.setUserName(adminSave.getUserName());
//
//		String NGAYSINH = request.getParameter("ngaySinh");
//		java.sql.Date ns = java.sql.Date.valueOf(NGAYSINH);
//		// Kiểm tra họ tên sử dụng Singleton
//		if (!ConstraintSingleton.getInstance().checkHoTen(admin.getHoTen(), model, "loiHoTen")) {
//			loi = Boolean.FALSE;
//		}
//		// Kiểm tra ngày sinh sử dụng Singleton
//		if (!ConstraintSingleton.getInstance().checkNgaySinh(admin.getNgaySinh(), model, "loiNgaySinh")) {
//			loi = Boolean.FALSE;
//		}
//		// Kiểm tra địa chỉ
//		if (!ConstraintSingleton.getInstance().checkDiaChi(admin.getDiaChi(), model, "loiDiaChi")) {
//			loi = Boolean.FALSE;
//		}
//
//		// Kiểm tra số điện thoại
//		if (!ConstraintSingleton.getInstance().checkPhoneNumber(admin.getSdt(), model, "loiSdt")) {
//
//			loi = Boolean.FALSE;
//		}
//		
//		
//		// Kiểm tra email
//		if (!ConstraintSingleton.getInstance().checkEmail(admin.getEmail(), model, "loiEmail")) {
//			loi = Boolean.FALSE;
//		}
//
//		if (loi == Boolean.FALSE) {
//			model.addAttribute("admin", adminSave);
//			return "/admin/me";
//		}
//		adminSave.setHoTen(admin.getHoTen());
//		adminSave.setGioiTinh(admin.isGioiTinh());
//		adminSave.setNgaySinh(ns);
//		adminSave.setDiaChi(admin.getDiaChi());
//		adminSave.setEmail(admin.getEmail());
//		adminSave.setSdt(admin.getSdt());
//		session.setAttribute("USER", adminSave);
//
//		nguoiDungService.updateUser(adminSave);
//
//		model.addAttribute("admin", adminSave);
//		model.addAttribute("successMessage", "Cập nhật thông tin thành công");
//
//		return "/admin/me";
//	}
//
//	@RequestMapping("changePass")
//	public String changPass() {
//		return "/admin/changePass";
//
//	}
//
//	@RequestMapping(value = "changePass.htm", params = "save", method = RequestMethod.POST)
//	public String changePass(HttpServletRequest request, ModelMap model) {
//		Boolean loi = Boolean.TRUE;
//		HttpSession session = request.getSession();
//		NguoiDungEntity user = (NguoiDungEntity) session.getAttribute("USER");
//		String pass = request.getParameter("pass");
//		String newPass = request.getParameter("newPass");
//		String reNewPass = request.getParameter("renewPass");
//
//		if (pass.isEmpty()) {
//			model.addAttribute("loiPass", "Hãy nhập mật khẩu cũ !!!");
//			loi = Boolean.FALSE;
//
//		} else if (!nguoiDungService.kiemTraMatKhau(pass, user.getPassWord())) {
//			model.addAttribute("loiPass", "Mật khẩu cũ không đúng !!!");
//			return "/admin/changePass";
//		}
//		
//		
//		// Check new password Singleton
//		if (!ConstraintSingleton.getInstance().checkNewPassword(newPass, reNewPass, model, "loiNewPass", "loiRePass")) {
//			loi = Boolean.FALSE;
//		}
//
//
//		if (loi == Boolean.TRUE) {
//			user.setPassWord(nguoiDungService.maHoaMatKhau(newPass));
//
//			nguoiDungService.updateUser(user);
//			model.addAttribute("successMessage", "Đổi mật khẩu thành công !!!");
//
//		}
//
//		return "/admin/changePass";
//	}
//
//	@RequestMapping("createAcc")
//	public String createAcc(HttpServletRequest request, ModelMap model) {
//		NguoiDungEntity admin = new NguoiDungEntity();
//		model.addAttribute("admin", admin);
//		return "admin/createAcc";
//	}
//
//	@RequestMapping(value = "/form/addAcc", params = "add", method = RequestMethod.POST)
//	public String addAcc(HttpServletRequest request, ModelMap model, @ModelAttribute("admin") NguoiDungEntity admin,
//			BindingResult errors) throws ParseException {
//
//		Boolean loi = Boolean.TRUE;
//		String rePassword = request.getParameter("re-passWord");
//		NguoiDungEntity userCheck;
//
//		
//		// Kiểm tra email
//		if (!ConstraintSingleton.getInstance().checkEmail(admin.getEmail(), errors, "email", "user")) {
//			loi = Boolean.FALSE;
//		}
//			
//		// Kiểm tra username
//		if (!ConstraintSingleton.getInstance().checkUsername(admin.getUserName(), errors, "userName", "user")) {
//			loi = Boolean.FALSE;
//		}
//
//
//		userCheck = nguoiDungService.findUserByNameAndEmail(admin.getUserName(), admin.getEmail());
//		if (userCheck != null) {
//			if (userCheck.getEmail().equals(admin.getEmail())) {
//				errors.rejectValue("email", "user", "Email đã được sử dụng !!!");
//				loi = Boolean.FALSE;
//			}
//			if (userCheck.getUserName().equals(admin.getUserName())) {
//				errors.rejectValue("userName", "user", "Username đã được sử dụng !!!");
//				loi = Boolean.FALSE;
//			}
//
//		}
//
//		// Kiểm tra mật khẩu
//		if (!ConstraintSingleton.getInstance().checkPasword(admin.getPassWord(), rePassword, errors, "passWord", "user")) {
//			loi = Boolean.FALSE;
//		}
//		// Kiểm tra họ tên
//		if (!ConstraintSingleton.getInstance().checkHoTen(admin.getHoTen(), errors, "hoTen", "user")) {
//			loi = Boolean.FALSE;
//		}
//		
//		
//		// Kiểm tra ngày sinh
//		if (!ConstraintSingleton.getInstance().checkNgaySinh(admin.getNgaySinh(), errors, "ngaySinh", "user")) {
//			loi = Boolean.FALSE;
//		}
//		// Kiểm tra số điện thoại
//		if (!ConstraintSingleton.getInstance().checkPhoneNumber(admin.getSdt(), errors, "sdt", "user")) {
//			loi = Boolean.FALSE;
//		}
//
//
//		if (loi == Boolean.FALSE) {
//			model.addAttribute("errorMessage", "Lỗi thêm Admin");
//
//			return "/admin/createAcc";
//		}
//
//
//		String NGAYSINH = request.getParameter("ngaySinh");
//		java.sql.Date ns = java.sql.Date.valueOf(NGAYSINH);
//		admin.setPassWord(nguoiDungService.maHoaMatKhau(admin.getPassWord()));
//		admin.setNgaySinh(ns);
//		admin.setHoTen(ConstraintSingleton.getInstance().capitalizeString(admin.getHoTen()));
//		admin.setTrangThai(true);
//		admin.setQuyen(1);
//		nguoiDungService.addUser(admin);
//		model.addAttribute("successMessage", "Thêm Admin mới thành công");
//		return "/admin/createAcc";
//	}
//
//	@RequestMapping("on/{maNd}")
//	public String on(@PathVariable("maNd") int maNd, HttpServletRequest request, ModelMap model) {
//		HttpSession session0 = request.getSession();
//		NguoiDungEntity user = nguoiDungService.findUserById(maNd);
//		user.setTrangThai(true);
//		nguoiDungService.updateUser(user);
//
//		if (user.getQuyen() == 0)
//			return "redirect:/admin/customerAccount.htm";
//
//		return "redirect:/admin/adminAccount.htm";
//	}
//
//	@RequestMapping("off/{maNd}")
//	public String off(@PathVariable("maNd") int maNd, HttpServletRequest request, ModelMap model) {
//		HttpSession session0 = request.getSession();
//		NguoiDungEntity user = nguoiDungService.findUserById(maNd);
//		user.setTrangThai(false);
//		nguoiDungService.updateUser(user);
//		if (user.getQuyen() == 0)
//			return "redirect:/admin/customerAccount.htm";
//
//		return "redirect:/admin/adminAccount.htm";
//	}
//
//	@RequestMapping("customerAccount")
//	public String cusAccount(HttpServletRequest request, ModelMap model) {
//		HttpSession session0 = request.getSession();
//		List<NguoiDungEntity> cusList = nguoiDungService.getAllUserByRole(0);
//
//		Collections.sort(cusList, new Comparator<NguoiDungEntity>() {
//			@Override
//			public int compare(NguoiDungEntity nguoi1, NguoiDungEntity nguoi2) {
//				boolean trangThai1 = nguoi1.isTrangThai();
//				boolean trangThai2 = nguoi2.isTrangThai();
//
//				// Sắp xếp theo thứ tự giảm dần (true trước, false sau)
//				return Boolean.compare(trangThai2, trangThai1);
//			}
//		});
//
//		model.addAttribute("userList", cusList);
//		return "admin/customerAccount";
//	}
//
//	@RequestMapping("me")
//	public String me(HttpServletRequest request, ModelMap model) {
//		HttpSession session0 = request.getSession();
//		NguoiDungEntity admin = (NguoiDungEntity) session0.getAttribute("USER");
//		model.addAttribute("admin", admin);
//		return "admin/me";
//	}
//}










@Transactional
@Controller
@RequestMapping("admin/")
public class quanLyTaiKhoanController {
	@Autowired
    private NguoiDungFacade nguoiDungFacade;
	
	@RequestMapping("adminAccount")
	public String account(HttpServletRequest request, ModelMap model) {
		HttpSession session0 = request.getSession();
		List<NguoiDungEntity> adminList = nguoiDungFacade.getAllUserByRole(1);

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
		NguoiDungEntity user = nguoiDungFacade.findUserById(maNd);
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

		nguoiDungFacade.updateUser(adminSave);

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

		} else if (!nguoiDungFacade.kiemTraMatKhau(pass, user.getPassWord())) {
			model.addAttribute("loiPass", "Mật khẩu cũ không đúng !!!");
			return "/admin/changePass";
		}
		
		
		// Check new password Singleton
		if (!ConstraintSingleton.getInstance().checkNewPassword(newPass, reNewPass, model, "loiNewPass", "loiRePass")) {
			loi = Boolean.FALSE;
		}


		if (loi == Boolean.TRUE) {
			user.setPassWord(nguoiDungFacade.maHoaMatKhau(newPass));

			nguoiDungFacade.updateUser(user);
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


		userCheck = nguoiDungFacade.findUserByNameAndEmail(admin.getUserName(), admin.getEmail());
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
		admin.setPassWord(nguoiDungFacade.maHoaMatKhau(admin.getPassWord()));
		admin.setNgaySinh(ns);
		admin.setHoTen(ConstraintSingleton.getInstance().capitalizeString(admin.getHoTen()));
		admin.setTrangThai(true);
		admin.setQuyen(1);
		nguoiDungFacade.addUser(admin);
		model.addAttribute("successMessage", "Thêm Admin mới thành công");
		return "/admin/createAcc";
	}

	@RequestMapping("on/{maNd}")
	public String on(@PathVariable("maNd") int maNd, HttpServletRequest request, ModelMap model) {
		HttpSession session0 = request.getSession();
		NguoiDungEntity user = nguoiDungFacade.findUserById(maNd);
		user.setTrangThai(true);
		nguoiDungFacade.updateUser(user);

		if (user.getQuyen() == 0)
			return "redirect:/admin/customerAccount.htm";

		return "redirect:/admin/adminAccount.htm";
	}

	@RequestMapping("off/{maNd}")
	public String off(@PathVariable("maNd") int maNd, HttpServletRequest request, ModelMap model) {
		HttpSession session0 = request.getSession();
		NguoiDungEntity user = nguoiDungFacade.findUserById(maNd);
		user.setTrangThai(false);
		nguoiDungFacade.updateUser(user);
		if (user.getQuyen() == 0)
			return "redirect:/admin/customerAccount.htm";

		return "redirect:/admin/adminAccount.htm";
	}

	@RequestMapping("customerAccount")
	public String cusAccount(HttpServletRequest request, ModelMap model) {
		HttpSession session0 = request.getSession();
		List<NguoiDungEntity> cusList = nguoiDungFacade.getAllUserByRole(0);

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
