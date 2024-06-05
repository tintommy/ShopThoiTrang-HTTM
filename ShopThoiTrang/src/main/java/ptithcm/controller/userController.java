package ptithcm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.entity.NguoiDungEntity;
import ptithcm.entity.SanPhamEntity;
import ptithcm.bean.Mailer;
import ptithcm.dao.nguoiDungDao;
import ptithcm.designpattern.FacadePattern.NguoiDungFacade;
import ptithcm.designpattern.SingletonPattern.ConstraintSingleton;
import ptithcm.service.nguoiDungService;

@Transactional
@Controller
public class userController {

	@Autowired
	SessionFactory factory;

	@Autowired
	Mailer mailer;

	@Autowired
    private NguoiDungFacade nguoiDungFacade;

	@RequestMapping("user/login")
	public String login(ModelMap model) {
		model.addAttribute("user", new NguoiDungEntity());
		System.out.println("hello");
		return "/user/login";

	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login0(ModelMap model) {
		model.addAttribute("user", new NguoiDungEntity());
		System.out.print("login");
		return "user/login";
	}

	@RequestMapping(value = "signup", method = RequestMethod.GET)
	public String signUp0(ModelMap model) {
		model.addAttribute("user", new NguoiDungEntity());
		System.out.print("signup");
		return "/user/signup";
	}

	@RequestMapping("forgotpass")
	public String forgotpass() {
		return "/user/forgotpass";
	}

	@RequestMapping("changePass.htm")
	public String changePass() {
		return "/user/changePass";
	}

	@RequestMapping("userInfo.htm")
	public String userInfo(HttpServletRequest request, ModelMap model) {
		HttpSession session = request.getSession();
		NguoiDungEntity user = (NguoiDungEntity) session.getAttribute("USER");

		model.addAttribute("user", user);

		return "/user/user-info";

	}

	@RequestMapping(value = "form/login", params = "login", method = RequestMethod.POST)
	public String login(ModelMap model, HttpServletRequest request, @ModelAttribute("user") NguoiDungEntity user,
			BindingResult errors) {

		Boolean loi = Boolean.TRUE;

		if (user.getUserName().isEmpty()) {
			errors.rejectValue("userName", "user", "Hãy nhập username hoặc email !!!");
			return "/user/login";
		} else if (user.getPassWord().isEmpty()) {
			errors.rejectValue("passWord", "user", "Hãy nhập mật khẩu !!!");
			return "/user/login";
		}

		NguoiDungEntity check = nguoiDungFacade.findUserByNameAndEmail(user.getUserName(), user.getUserName());

		if (check == null) {

			errors.rejectValue("userName", "user", "Tài khoản không tồn tại !!!");
			loi = Boolean.FALSE;
			
		} else if ((user.getUserName().equals(check.getUserName()) || user.getUserName().equals(check.getEmail()))
				&& !nguoiDungFacade.kiemTraMatKhau(user.getPassWord(), check.getPassWord())) {
			errors.rejectValue("passWord", "user", "Sai mật khẩu !!!");
			loi = Boolean.FALSE;
		}		
		else if (check.isTrangThai()==false) {
			errors.rejectValue("userName", "user", "Tài khoản của bạn đã bị khoá !!!");
			loi = Boolean.FALSE;
		}
		if (loi == Boolean.FALSE)
			return "user/login";
		Hibernate.initialize(check.getGioHangs());
		HttpSession session0 = request.getSession();
		session0.setAttribute("USER", check);		
		if (check.getQuyen()!=0) { // Kiểm tra quyền người dùng là admin
			return "redirect:/admin/index.htm"; // Trả về trang quản trị admin
		}		
		String maSp= (String) session0.getAttribute("SANPHAM");
		if(maSp!=null)
			{session0.removeAttribute("SANPHAM");
			return "redirect:/sanpham/"+maSp+".htm";}
		session0.removeAttribute("SANPHAM");
		return "redirect:/";
	}

	@RequestMapping(value = "form/signup", params = "signup", method = RequestMethod.POST)
	public String signUp(HttpServletRequest request, ModelMap model, @ModelAttribute("user") NguoiDungEntity user,
			BindingResult errors) throws ParseException {

		Boolean loi = Boolean.TRUE;
		String rePassword = request.getParameter("re-passWord");
		NguoiDungEntity userCheck;
		//Kiểm tra email
		if(!ConstraintSingleton.getInstance().checkEmail(user.getEmail(), errors, "email", "user")) {
			loi = Boolean.FALSE;
		}

		// Kiểm tra username
		if(!ConstraintSingleton.getInstance().checkUsername(user.getUserName(), errors, "userName", "user")) {
			loi = Boolean.FALSE;
		}

		

		userCheck = nguoiDungFacade.findUserByNameAndEmail(user.getUserName(), user.getEmail());
		if (userCheck != null) {
			if (userCheck.getEmail().equals(user.getEmail())) {
				errors.rejectValue("email", "user", "Email đã được sử dụng !!!");
				loi = Boolean.FALSE;
			}
			if (userCheck.getUserName().equals(user.getUserName())) {
				errors.rejectValue("userName", "user", "Username đã được sử dụng !!!");
				loi = Boolean.FALSE;
			}

		}
		// Kiểm tra password 
		if(!ConstraintSingleton.getInstance().checkPasword(user.getPassWord(), rePassword, errors, "passWord", "user")) {
			loi = Boolean.FALSE;
		}
		// Kiểm tra họ tên
		if(!ConstraintSingleton.getInstance().checkHoTen(user.getHoTen(), errors, "hoTen", "user")) {
			loi = Boolean.FALSE;
		}
		
		// Kiểm tra ngày sinh
		if(!ConstraintSingleton.getInstance().checkNgaySinh(user.getNgaySinh(), errors, "ngaySinh", "user")) {
			loi = Boolean.FALSE;
		}
		// Kiểm tra số điện thoại
		if(!ConstraintSingleton.getInstance().checkPhoneNumber(user.getSdt(), errors, "sdt", "user")) {
			loi = Boolean.FALSE;
		}
		// Kiểm tra địa chỉ
		if(!ConstraintSingleton.getInstance().checkDiaChi(user.getDiaChi(), errors, "diaChi", "user")) {
			loi = Boolean.FALSE;
		}


		if (loi == Boolean.FALSE)
			return "/user/signup";

		String NGAYSINH = request.getParameter("ngaySinh");
		java.sql.Date ns = java.sql.Date.valueOf(NGAYSINH);
		user.setPassWord(nguoiDungFacade.maHoaMatKhau(user.getPassWord()));
		user.setNgaySinh(ns);
		user.setHoTen(ConstraintSingleton.getInstance().capitalizeString(user.getHoTen()));
		user.setTrangThai(true);
		user.setQuyen(0);
		HttpSession session = request.getSession();
		session.setAttribute("USERSIGNUP", user);
		String otp = nguoiDungFacade.taoOTP();
		session.setAttribute("OTP", otp);
		mailer.sendMailAsync("SHOPTHOITRANG", user.getEmail(), "OTP", "Mã OTP của bạn là: " + otp); 
		return "/user/verify";
	}

	@RequestMapping(value = "form/forgotpass", params = "tieptheo", method = RequestMethod.POST)
	public String forgotPass(HttpServletRequest request, ModelMap model) {
		String userName = request.getParameter("username");
		System.out.print(userName);

		if (userName.equals(null)) {
			model.addAttribute("messenger", "Hãy nhập Username/Email của bạn !!!");
			return "/user/forgotpass";
		}
		NguoiDungEntity user = nguoiDungFacade.findUserByNameAndEmail(userName, userName);
		if (user == null) {
			model.addAttribute("messenger", "Không tìm thấy tài khoản !!!");
			return "/user/forgotpass";
		}
		HttpSession session = request.getSession();
		session.setAttribute("USERFORGOT", user);
		String otp = nguoiDungFacade.taoOTP();
		session.setAttribute("OTP", otp);
		mailer.sendMailAsync("DAILYSHOP", user.getEmail(), "OTP", "Mã OTP của bạn là: " + otp);
		model.addAttribute("email","****"+user.getEmail().substring(user.getEmail().length()-13));
		return "/user/verify2";
	}

	@RequestMapping(value = "form/verify", params = "verify", method = RequestMethod.POST)
	public String verify(HttpServletRequest request, ModelMap model) {
		HttpSession session = request.getSession();
		String a = request.getParameter("a");
		String b = request.getParameter("b");
		String c = request.getParameter("c");
		String d = request.getParameter("d");
		String e = request.getParameter("e");
		String f = request.getParameter("f");

		String otp = session.getAttribute("OTP").toString();
		String temp = a + b + c + d + e + f;

		if (otp.equals(temp)) {
			NguoiDungEntity user = (NguoiDungEntity) session.getAttribute("USERSIGNUP"); 
			nguoiDungFacade.addUser(user);
			model.addAttribute("user", new NguoiDungEntity());  //nếu nhập đúng OTP thì sẽ chuyển đến trang login
			return "/user/login";
		}
		NguoiDungEntity user = (NguoiDungEntity) session.getAttribute("USERSIGNUP");
		model.addAttribute("email","****"+user.getEmail().substring(user.getEmail().length()-13));

		model.addAttribute("messenger", "OTP bạn nhập không đúng !!!");
		return "/user/verify";
	}

	@RequestMapping(value = "form/verify2", params = "verify", method = RequestMethod.POST)
	public String verify2(HttpServletRequest request, ModelMap model) {
		HttpSession session = request.getSession();
		String a = request.getParameter("a");
		String b = request.getParameter("b");
		String c = request.getParameter("c");
		String d = request.getParameter("d");
		String e = request.getParameter("e");
		String f = request.getParameter("f");

		String otp = session.getAttribute("OTP").toString();
		String temp = a + b + c + d + e + f;

		if (otp.equals(temp)) {
			
			return "/user/newpass";
		}
		NguoiDungEntity user = (NguoiDungEntity) session.getAttribute("USERFORGOT");
		model.addAttribute("email","****"+user.getEmail().substring(user.getEmail().length()-13));
		model.addAttribute("messenger", "OTP bạn nhập không đúng !!!");
		return "/user/verify2";
	}

	@RequestMapping(value = "form/verify", params = "again", method = RequestMethod.POST)
	public String guiLaiMa(HttpServletRequest request, ModelMap model) {
		HttpSession session = request.getSession();
		String otp = nguoiDungFacade.taoOTP();
		session.setAttribute("OTP", otp);
		NguoiDungEntity user = (NguoiDungEntity) session.getAttribute("USERSIGNUP");
//		sendMail("THEGIOIDIENMAY", user.getEmail(), "OTP", "Mã OTP của bạn là: " + otp);
		mailer.sendMailAsync("THEGIOIDIENMAY", user.getEmail(), "OTP", "Mã OTP của bạn là: " + otp);
		model.addAttribute("email","****"+user.getEmail().substring(user.getEmail().length()-13));
		model.addAttribute("again", "OTP đã được gửi lại !!!");
		return "/user/verify";
	}

	@RequestMapping(value = "form/verify2", params = "again", method = RequestMethod.POST)
	public String guiLaiMa2(HttpServletRequest request, ModelMap model) {
		HttpSession session = request.getSession();
		String otp = nguoiDungFacade.taoOTP();
		session.setAttribute("OTP", otp);
		NguoiDungEntity user = (NguoiDungEntity) session.getAttribute("USERFORGOT");
//		sendMail("THEGIOIDIENMAY", user.getEmail(), "OTP", "Mã OTP của bạn là: " + otp);
		mailer.sendMailAsync("THEGIOIDIENMAY", user.getEmail(), "OTP", "Mã OTP của bạn là: " + otp);
		model.addAttribute("email","****"+user.getEmail().substring(user.getEmail().length()-13));
		model.addAttribute("again", "OTP đã được gửi lại !!!");
		return "/user/verify2";
	}

	@RequestMapping(value = "form/newpass", params = "hoanTat", method = RequestMethod.POST)
	public String newPass(HttpServletRequest request, ModelMap model) {
		HttpSession session = request.getSession();

		NguoiDungEntity user = (NguoiDungEntity) session.getAttribute("USERFORGOT");
		String pass = request.getParameter("password");
		String confirmPass = request.getParameter("confirmPass");
		Boolean loi = Boolean.TRUE;
		//Kiểm tra password 
		if(!ConstraintSingleton.getInstance().checkNewPassword(pass, confirmPass, model, "messenger", "messenger")) {
			loi = Boolean.FALSE;
		}
		if (loi == Boolean.FALSE)
			return "/user/newpass";
		
		if (pass.equals(confirmPass)) {

			user.setPassWord(nguoiDungFacade.maHoaMatKhau(pass));

			nguoiDungFacade.updateUser(user);

			model.addAttribute("user", new NguoiDungEntity());
			return "/user/login";
		}

		return "/user/newpass";
	}

	@RequestMapping("user/info")
	public String info(HttpServletRequest request, ModelMap model) {
		HttpSession session = request.getSession();
		NguoiDungEntity user = (NguoiDungEntity) session.getAttribute("USER");

		model.addAttribute("user", user);

		return "/user/user-info";
	}

	@RequestMapping(value = "form/info", params = "save", method = RequestMethod.POST)
	public String infoUpdate(HttpServletRequest request, ModelMap model, @ModelAttribute("user") NguoiDungEntity user,
			BindingResult errors) {
		Boolean loi = Boolean.TRUE;
		HttpSession session = request.getSession();
		NguoiDungEntity userSave = (NguoiDungEntity) session.getAttribute("USER");
		user.setUserName(userSave.getUserName());
		user.setEmail(userSave.getEmail());
		user.setSdt(userSave.getSdt());

		String NGAYSINH = request.getParameter("ngaySinh");
		java.sql.Date ns = java.sql.Date.valueOf(NGAYSINH);
		// Kiểm tra họ tên
		if(!ConstraintSingleton.getInstance().checkHoTen(user.getHoTen(), model, "loiHoTen")) {
			loi = Boolean.FALSE;
		}

		// Kiểm tra ngày sinh
		if(!ConstraintSingleton.getInstance().checkNgaySinh(user.getNgaySinh(), model, "loiNgaySinh")) {
			loi = Boolean.FALSE;
		}
		// Kiểm tra địa chỉ
		if(!ConstraintSingleton.getInstance().checkDiaChi(user.getDiaChi(), model, "loiDiaChi")) {
			loi = Boolean.FALSE;
		}
		

		if (loi == Boolean.FALSE) {
			model.addAttribute("user", userSave);
			return "/user/user-info";
		}
		userSave.setHoTen(user.getHoTen());
		userSave.setGioiTinh(user.isGioiTinh());
		userSave.setNgaySinh(ns);
		userSave.setDiaChi(user.getDiaChi());
		session.setAttribute("USER", userSave);

		nguoiDungFacade.updateUser(userSave);

		model.addAttribute("user", userSave);
		model.addAttribute("successMessage","Cập nhật thông tin thành công");

		return "/user/user-info";
	}

	@RequestMapping(value = "form/changePass.htm", params = "save", method = RequestMethod.POST)
	public String changePass(HttpServletRequest request, ModelMap model) {
		Boolean loi = Boolean.TRUE;
		HttpSession session = request.getSession();
		NguoiDungEntity user = (NguoiDungEntity) session.getAttribute("USER");
		String pass = request.getParameter("password");
		String newPass = request.getParameter("newPassword");
		String reNewPass = request.getParameter("reNewPassword");
		
		
		// Kiểm tra password cũ
		if (pass.isEmpty()) {
			model.addAttribute("loiPassword", "Hãy nhập mật khẩu cũ !!!");
			loi = Boolean.FALSE;

		} else if (!nguoiDungFacade.kiemTraMatKhau(pass,user.getPassWord())) {
			model.addAttribute("loiPassword", "Mật khẩu cũ không đúng !!!");
			model.addAttribute("user", user);
			return "/user/user-info";
		}
		
		// Kiểm tra password mới 
		if(!ConstraintSingleton.getInstance().checkNewPassword(newPass, reNewPass, model, "loiNewPassword", "loiRePassword")) {
			loi = Boolean.FALSE;
		}
		

		
		if (loi == Boolean.FALSE) {
            model.addAttribute("user", user);
            return "/user/user-info";
        }

		user.setPassWord(nguoiDungFacade.maHoaMatKhau(newPass));

		nguoiDungFacade.updateUser(user);
		model.addAttribute("thanhCong", "Đổi mật khẩu thành công !!!");
		
	

		model.addAttribute("user", user);

		return "/user/user-info";
	}

	@RequestMapping(value = "user/logout")
	public String logout(HttpServletRequest request, ModelMap model) {
		HttpSession session0 = request.getSession();
		session0.removeAttribute("USER");
		session0.removeAttribute("SANPHAM");
		session0.removeAttribute("NEWINFO");
		 return "redirect:/";

	}



}