package ptithcm.designpattern.FacadePattern;

import java.io.File;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ptithcm.entity.SanPhamEntity;
import ptithcm.service.DonHangService;
import ptithcm.service.SanPhamService;

@Component
@Transactional
public class DeXuatFacade {
	@Autowired
	SanPhamService sanPhamService;
	@Autowired
	DonHangService donHangService;
	
	String filePath = "D:\\imgSearch\\";
	
	

	public List<SanPhamEntity> laySanPhamTheoListMaSP(List<String> imageNames) {
		return sanPhamService.laySanPhamTheoListMaSP(imageNames);
	}

	public List<String> layMaSanPhamTrongDonHangGanNhatCuaUser(int maNd) {
		return donHangService.layMaSanPhamTrongDonHangGanNhatCuaUser(maNd);
	}

	public List<SanPhamEntity> laySanPhamNgauNhien() {
		return sanPhamService.laySanPhamNgauNhien();
	}

	public List<SanPhamEntity> locSanPhamTrung(List<SanPhamEntity> listNgauNhien) {
		return sanPhamService.locSanPhamTrung(listNgauNhien);
	}
	
	
	
	public void xoaTepTinHinhAnh(String tenTep) {
	    String imgPath = filePath + tenTep; // Đường dẫn tới thư mục chứa hình ảnh
	    File tepTin = new File(imgPath);
	    
	    if (tepTin.exists()) {
	        tepTin.delete(); // Xóa tệp tin hình ảnh từ server
	    }
	}
}
