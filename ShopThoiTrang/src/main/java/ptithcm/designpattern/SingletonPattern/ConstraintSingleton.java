package ptithcm.designpattern.SingletonPattern;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import ptithcm.entity.DonHangEntity;

public class ConstraintSingleton {
	private static ConstraintSingleton INSTANCE = new ConstraintSingleton();
	private ConstraintSingleton() {
        
    }
	public static ConstraintSingleton getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new ConstraintSingleton();
		}
        return INSTANCE;
    }
	// Tính tổng doanh thu của 1 danh sách đơn hàng
	public int tinhTongDoanhThu(List<DonHangEntity> Orders) {
		int tongDoanhThu = 0;
		for (DonHangEntity donHang : Orders){
		    tongDoanhThu += donHang.getTongTien();
		}
		return tongDoanhThu;
	}
	// Check họ tên
	public boolean checkHoTen(String hoTen, ModelMap model, String parameterName) {
		if (hoTen.isEmpty()) {
			model.addAttribute(parameterName, "Họ tên không được để trống!");
            return false;
		} else if (hoTen.length() > 50) {
			model.addAttribute(parameterName, "Họ tên quá dài !!!");
			return false;
		} else if (!hoTen.matches("[\\p{L} ]+")) {
			model.addAttribute(parameterName, "Họ tên không được chứa số !!!");
			return false;
		}
		return true;
	}
	public boolean checkHoTen(String hoTen, BindingResult errors, String field, String errorCode) {
		if (hoTen.isEmpty()) {
			errors.rejectValue(field, errorCode, "Hãy nhập họ tên !!!");
			return false;
		} else if (hoTen.length() > 50) {
			errors.rejectValue(field, errorCode, "Họ tên quá dài !!!");
			return false;
		} else if (!hoTen.matches("[\\p{L} ]+")) {
			errors.rejectValue(field, errorCode, "Họ tên không được chứa số !!!");
			return false;
		}
		return true;
	}
	public boolean checkNgaySinh(Date birthday, BindingResult errors, String field, String errorCode) {
		if (birthday == null) {
			errors.rejectValue(field, errorCode, "Hãy nhập ngày sinh !!!");
			return false;
		} else if (!isValidAndOver18(birthday)) {
			errors.rejectValue(field, errorCode, "Admin cần lớn hơn 18 tuổi để tạo tài khoản !!!");
			return false;
		}
		return true;
	}
	// Check ngày sinh
	public boolean checkNgaySinh(Date birthday, ModelMap model, String parameterName) {
		if (birthday == null) {
			model.addAttribute("loiNgaySinh", "Hãy nhập ngày sinh !!!");
			return false;
		} else if (!isValidAndOver18(birthday)) {
			model.addAttribute("loiNgaySinh", "Bạn cần lớn hơn 18 tuổi để tạo tài khoản !!!");
			return false;
		} 
		return true;
	}
	// 
	public boolean isValidAndOver18(Date ngaySinh) {
		LocalDate dob = ngaySinh.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate today = LocalDate.now();
		int age = Period.between(dob, today).getYears();
		Date currentDate = new Date();
		return !ngaySinh.after(currentDate) && age >= 18;
	}
	//
	public boolean checkDiaChi(String address, ModelMap model, String parameter) {
		if (address.isEmpty()) {
			model.addAttribute(parameter, "Địa chỉ không được trống!!!");
			return false;
		}
		
		return true;
	}
	public boolean checkDiaChi(String address, BindingResult errors, String field, String errorCode) {
		if (address.isEmpty()) {
			errors.rejectValue(field, errorCode, "Hãy nhập địa chỉ !!!");
			return false;
		}
		
		return true;
	}
	public boolean checkPhoneNumber(String phoneNum, ModelMap model, String parameter) {
		if (phoneNum.isEmpty()) {
            model.addAttribute(parameter, "Hãy nhập sdt!!!");
            return false;
        } else if (!phoneNum.matches("[0-9]+")) {
            model.addAttribute(parameter, "SDT không hợp lệ!!!");
            return false;
        } else if (phoneNum.length() != 10 && phoneNum.length() != 11) {
			model.addAttribute(parameter, "SDT không hợp lệ !!!");
			return false;
		}
        return true;
	}
	public boolean checkPhoneNumber(String phoneNum, BindingResult errors, String field, String errorCode) {
		if (phoneNum.isEmpty()) {
			errors.rejectValue(field, errorCode, "Hãy nhập sdt !!!");
			return false;
		} else if (!phoneNum.matches("[0-9]+")) {
			errors.rejectValue(field, errorCode, "SDT không hợp lệ !!!");
			return false;
		} else if (phoneNum.length() != 10 && phoneNum.length() != 11) {
			errors.rejectValue(field, errorCode, "SDT không hợp lệ !!!");
			return false;
		}
		return true;
	}
	public boolean checkEmail(String email, ModelMap model, String parameter) {
		if (email.isEmpty()) {
			model.addAttribute(parameter, "Hãy nhập email !!!");
			return false;
		} else if (!email.endsWith("@gmail.com")) {
			model.addAttribute(parameter, "Hãy nhập email đúng định dạng !!!");
			return false;
		}
		return true;
	}
	public boolean checkEmail(String email, BindingResult errors, String field, String errorCode) {
		if (email.isEmpty()) {
			errors.rejectValue(field, errorCode, "Hãy nhập email !!!");
			return false;
		} else if (!email.endsWith("@gmail.com")) {
			errors.rejectValue(field, errorCode, "Hãy nhập email đúng định dạng !!!");
			return false;
		}
		return true;
	}
	public boolean checkNewPassword(String newPass, String reNewPass, ModelMap model, String parameter1, String parameter2) {
		if (newPass.isEmpty()) {
			model.addAttribute(parameter1, "Hãy nhập mật khẩu mới !!!");
			return false;
		} else if (newPass.length() < 8) {
			model.addAttribute(parameter1, "Mật khẩu tối thiểu 8 kí tự !!!");
			return false;
		} else if (newPass.contains(" ")) {
			model.addAttribute(parameter1, "Mật khẩu không được chứa khoảng trắng !!!");
			return false;
		} else if (reNewPass.isEmpty()) {
			model.addAttribute(parameter2, "Hãy nhập lại mật khẩu !!!");
			return false;
		} else if (!reNewPass.equals(newPass)) {
			model.addAttribute(parameter2, "Xác nhận mật khẩu không đúng !!!");
			return false;
		}
		return true;
	}
	public boolean checkPasword(String password, String rePassword, BindingResult errors, String field, String errorCode) {
		if (password.isEmpty()) {
			errors.rejectValue(field, errorCode, "Hãy nhập mật khẩu !!!");
			return false;
		} else if (password.length() < 8) {
			errors.rejectValue(field, errorCode, "Password tối thiểu 8 kí tự !!!");
			return false;
		} else if (password.contains(" ")) {
			errors.rejectValue(field, errorCode, "Password không được chứa khoảng trắng !!!");
			return false;
		} else if (rePassword.isEmpty()) {
			errors.rejectValue(field, errorCode, "Hãy nhập lại mật khẩu !!!");
			return false;
		} else if (!rePassword.equals(password)) {
			errors.rejectValue(field, errorCode, "Xác nhận mật khẩu không đúng !!!");
			return false;
		}
		return true;
	}
	public boolean checkUsername(String username, BindingResult errors, String field, String errorCode) {
		if (username.isEmpty()) {
			errors.rejectValue(field, errorCode, "Hãy nhập username !!!");
			return false;
		} else if (username.contains(" ")) {
			errors.rejectValue(field, errorCode, "UserName không được chứa khoảng trắng !!!");
			return false;
		} else if (username.length() > 30) {
			errors.rejectValue(field, errorCode, "UserName không được dài quá 30 kí tự !!!");
			return false;
		}
		return true;
	}
	public String capitalizeString(String str) {
		String[] words = str.split("\\s+");
		for (int i = 0; i < words.length; i++) {
			words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1);
		}
		return String.join(" ", words);
	}
	
	
//	
	
}
