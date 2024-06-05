package ptithcm.designpattern.FacadePattern;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ptithcm.entity.NguoiDungEntity;
import ptithcm.entity.YeuThichEntity;
import ptithcm.service.nguoiDungService;
import ptithcm.service.yeuThichService;

@Component
@Transactional
public class NguoiDungFacade {
	
	@Autowired
	nguoiDungService userService;
	
	@Autowired
    private yeuThichService yeuThichService;

    public List<YeuThichEntity> layDSYeuThichCuaUser(int maNd) {
        return yeuThichService.layDSYeuThichCuaUser(maNd);
    }

    public void deleteYeuThich(int maYT) {
        yeuThichService.deleteYeuThich(maYT);
    }

	public NguoiDungEntity findUserByNameAndEmail(String userName, String userName2) {
		return userService.findUserByNameAndEmail(userName, userName2);
	}

	public boolean kiemTraMatKhau(String passWord, String passWord2) {
		return userService.kiemTraMatKhau(passWord, passWord2);
	}

	public String maHoaMatKhau(String passWord) {
		return userService.maHoaMatKhau(passWord);
	}

	public void addUser(NguoiDungEntity user) {
		userService.addUser(user);
	}

	public void updateUser(NguoiDungEntity user) {
		userService.updateUser(user);		
	}
	
	
	public boolean isValidAndOver18(Date ngaySinh) {
		LocalDate dob = ngaySinh.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate today = LocalDate.now();
		int age = Period.between(dob, today).getYears();
		Date currentDate = new Date();
		return !ngaySinh.after(currentDate) && age >= 18;
	}

	public static String capitalizeString(String str) {
		String[] words = str.split("\\s+");
		for (int i = 0; i < words.length; i++) {
			words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1);
		}
		return String.join(" ", words);
	}

	public String taoOTP() {
		String alphabelt = "0123456789qwertyuiopasdfghjkzxcvbnmQWERTYUOPLKJHGFDSAZXCVBNM";

		String otp = "";
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			otp += alphabelt.charAt(random.nextInt(60));
		}

		return otp;
	}

	public List<NguoiDungEntity> getAllUserByRole(int i) {
		return userService.getAllUserByRole(i);
	}

	public NguoiDungEntity findUserById(int maNd) {
		return userService.findUserById(maNd);
	}
    
    
}
