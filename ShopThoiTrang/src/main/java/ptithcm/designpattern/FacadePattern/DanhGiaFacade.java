package ptithcm.designpattern.FacadePattern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ptithcm.entity.CTDonHangEntity;
import ptithcm.entity.DanhGiaEntity;
import ptithcm.entity.DonHangEntity;
import ptithcm.entity.NguoiDungEntity;
import ptithcm.entity.SanPhamEntity;
import ptithcm.service.CTDonHangService;
import ptithcm.service.DanhGiaService;
import ptithcm.service.DonHangService;
import ptithcm.service.SanPhamService;

@Component
@Transactional
public class DanhGiaFacade {

	@Autowired
    private DonHangService donHangService;

    @Autowired
    private CTDonHangService ctDonHangService;

    @Autowired
    private SanPhamService sanPhamService;

    @Autowired
    private DanhGiaService danhGiaService;

    public DonHangEntity getDonHangById(int maDh) {
        return donHangService.timDonHangTheoMa(maDh);
    }

    public List<CTDonHangEntity> getCTDonHangByMaDh(int maDh) {
        return ctDonHangService.timctdhTheoMaDh(maDh);
    }

    public NguoiDungEntity getCurrentUser(HttpSession session) {
        return (NguoiDungEntity) session.getAttribute("USER");
    }

    public void saveDanhGia(DanhGiaEntity danhGia) {
        danhGiaService.saveDanhGia(danhGia);
    }

    public void updateCTDonHang(CTDonHangEntity ctdh) {
        ctDonHangService.updateCtdh(ctdh);
    }

    public void updateSanPham(SanPhamEntity sanpham) {
        sanPhamService.updateSanPham(sanpham);
    }

    public void runRecommendationTraining() throws IOException {
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "cd C:\\Users\\Administrator\\Documents\\ShopThoiTrang\\src\\main\\python & python recommendTrain.py");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = r.readLine()) != null) {
            System.out.println(line);
        }
    }

    public float tinhSoSaoTB(SanPhamEntity sanpham) {
        return sanPhamService.tinhSoSaoTB(sanpham);
    }

    public SanPhamEntity getSanPhamById(String maSp) {
        return sanPhamService.laySanPham(maSp);
    }

    public CTDonHangEntity getCTDonHangByMaDhMaSp(int maDh, String maSp) {
        return ctDonHangService.timCtdhTheoMaDhMaSP(maDh, maSp);
    }
}
