package ptithcm.designpattern.FacadePattern;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ptithcm.entity.CTDonHangEntity;
import ptithcm.entity.DonHangEntity;
import ptithcm.entity.GioHangEntity;
import ptithcm.entity.NguoiDungEntity;
import ptithcm.entity.SanPhamEntity;
import ptithcm.service.CTDonHangService;
import ptithcm.service.DonHangService;
import ptithcm.service.SanPhamService;
import ptithcm.service.gioHangService;

@Component
public class DonHangFacade {

    @Autowired
    private DonHangService donHangService;

    @Autowired
    private gioHangService gioHangService;

    @Autowired
    private CTDonHangService ctDonHangService;

    @Autowired
    private SanPhamService sanPhamService;

    public List<GioHangEntity> layGioHangCuaUser(int maNd) {
        return gioHangService.layGioHangCuaUser(maNd);
    }

    public void luuDonHang(DonHangEntity donHang) {
        donHangService.luuDonHang(donHang);
    }

    public void luuCTDonHang(CTDonHangEntity ctdh) {
        ctDonHangService.luuCtdh(ctdh);
    }

    public SanPhamEntity laySanPham(String maSp) {
        return sanPhamService.laySanPham(maSp);
    }

    public void updateSanPham(SanPhamEntity sanPham) {
        sanPhamService.updateSanPham(sanPham);
    }

    public void deleteGioHang(int maGh) {
        gioHangService.deleteGioHang(maGh);
    }

    public DonHangEntity timDonHangTheoMa(int maDh) {
        return donHangService.timDonHangTheoMa(maDh);
    }

    public List<CTDonHangEntity> timCTDonHangTheoMaDh(int maDh) {
        return ctDonHangService.timctdhTheoMaDh(maDh);
    }

    public void updateDonHang(DonHangEntity donHang) {
        donHangService.updateDonHang(donHang);
    }
    
    
    public List<DonHangEntity> layDonHangCuaUserTheoTrangThai(int maNd, int trangThai) {
        return donHangService.timDonHangCuaUserTheoTrangThai(maNd, trangThai);
    }
}