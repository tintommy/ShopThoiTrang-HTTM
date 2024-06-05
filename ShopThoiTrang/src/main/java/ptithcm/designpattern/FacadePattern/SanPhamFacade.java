package ptithcm.designpattern.FacadePattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ptithcm.entity.CTDonHangEntity;
import ptithcm.entity.DanhGiaEntity;
import ptithcm.entity.GioHangEntity;
import ptithcm.entity.KieuSanPhamEntity;
import ptithcm.entity.LoaiSanPhamEntity;
import ptithcm.entity.NguoiDungEntity;
import ptithcm.entity.SanPhamEntity;
import ptithcm.entity.YeuThichEntity;
import ptithcm.service.DanhGiaService;
import ptithcm.service.KieuSanPhamService;
import ptithcm.service.SanPhamService;
import ptithcm.service.gioHangService;
import ptithcm.service.loaiSanPhamService;
import ptithcm.service.yeuThichService;

@Component
@Transactional
public  class SanPhamFacade{
    @Autowired
    private SanPhamService sanPhamService;

    @Autowired
    private DanhGiaService danhGiaService;

    @Autowired
    private gioHangService gioHangService;

    @Autowired
    private yeuThichService yeuThichService;
    
    @Autowired
	loaiSanPhamService loaiService;
	@Autowired
	KieuSanPhamService kieuSanPhamService;

    public SanPhamEntity laySanPham(String maSp) {
        return sanPhamService.laySanPham(maSp);
    }

    public List<String> laySizeTheoTenSanPham(String maSp) {
        return sanPhamService.laySizeTheoTenSanPham(maSp);
    }

    public List<SanPhamEntity> laySanPhamCungKieu(String maSp) {
        return sanPhamService.laySanPhamCungKieu(maSp);
    }

    public List<SanPhamEntity> locSanPhamTrung(List<SanPhamEntity> sanPhamCungKieu) {
        return sanPhamService.locSanPhamTrung(sanPhamCungKieu);
    }

    public List<DanhGiaEntity> layDanhGiaSanPham(String maSp) {
        return danhGiaService.layDanhGiaSanPham(maSp);
    }

    public List<GioHangEntity> layGioHangCuaUser(int maNd) {
        return gioHangService.layGioHangCuaUser(maNd);
    }

    public void updateGioHang(GioHangEntity gioHang) {
        gioHangService.updateGioHang(gioHang);
    }

    public void addGioHang(GioHangEntity gioHang) {
        gioHangService.addGioHang(gioHang);
    }

    public List<YeuThichEntity> layDSYeuThichCuaUser(int maNd) {
        return yeuThichService.layDSYeuThichCuaUser(maNd);
    }

    public void addYeuThich(YeuThichEntity yeuThich) {
        yeuThichService.addYeuThich(yeuThich);
    }

    public GioHangEntity layGioHangTheoMaNdVaSanPham(int maNd, String maSp) {
        return gioHangService.layGioHangTheoMaNdVaSanPham(maNd, maSp);
    }

	public List<SanPhamEntity> layAllSanPham() {		
		return sanPhamService.layAllSanPham();
	}

	public List<LoaiSanPhamEntity> layLoai() {
		return loaiService.layLoai();
	}

	public List<KieuSanPhamEntity> layKieu() {
		return kieuSanPhamService.layKieu();
	}

	public List<SanPhamEntity> LaySanPhamMotTran(List<SanPhamEntity> dsSP, int page, int pageSize) {
		return sanPhamService.LaySanPhamMotTran(dsSP, page, pageSize);
	}

	public List<SanPhamEntity> layAllSanPhamTheoLoai(String loaiSp) {
		return sanPhamService.layAllSanPhamTheoLoai(loaiSp);
	}

	public List<SanPhamEntity> locSanPham(List<String> stylesList, int minPrice, int maxPrice) {
		return sanPhamService.locSanPham(stylesList, minPrice, maxPrice);
	}

	public List<SanPhamEntity> laySanPhamTheoMa(String key) {
		return sanPhamService.laySanPhamTheoMa(key);
	}

	public List<SanPhamEntity> layTatCaSanPhamCungKieu(String kieuSp) {
		return sanPhamService.layTatCaSanPhamCungKieu(kieuSp);

	}
    
    
	
	public List<LoaiSanPhamEntity> getLoaiSanPhamByGender(String gender) {
        return loaiService.layLoaiTheoGioiTinh(gender);
    }

    public List<SanPhamEntity> getSanPhamByGender(String gender) {
        List<SanPhamEntity> listSp = sanPhamService.laySanPhamTheogioiTinh(gender);
        return sanPhamService.locSanPhamTrung(listSp);
    }

    public List<SanPhamEntity> getNewSanPham() {
        List<SanPhamEntity> listMoi = sanPhamService.laySanPhamMoi();
        return sanPhamService.locSanPhamTrung(listMoi);
    }

    public List<CTDonHangEntity> getPopularSanPham(int limit) {
        List<CTDonHangEntity> listPhoBien = sanPhamService.laySanPhamPhoBien(limit);
        return sapXepVaTinhTong(listPhoBien);
    }

    private List<CTDonHangEntity> sapXepVaTinhTong(List<CTDonHangEntity> sanPhamList) {
        Map<String, Integer> tongSoLuongMap = new HashMap<>();

        // Lọc và tính tổng số lượng cho từng sản phẩm cùng maSP
        for (CTDonHangEntity ctdh : sanPhamList) {
            SanPhamEntity sanPham = ctdh.getSanPham();
            int soLuong = ctdh.getSoLuong();

            // Kiểm tra xem sản phẩm đã tồn tại trong Map chưa
            if (tongSoLuongMap.containsKey(sanPham.getMaSP())) {
                int tongSoLuongHienTai = tongSoLuongMap.get(sanPham.getMaSP());
                tongSoLuongMap.put(sanPham.getMaSP(), tongSoLuongHienTai + soLuong);
            } else {
                tongSoLuongMap.put(sanPham.getMaSP(), soLuong);
            }
        }

        // Tạo danh sách mới để chứa các đối tượng đã lọc
        List<CTDonHangEntity> sanPhamDaLoc = new ArrayList<>();

        // Duyệt qua danh sách gốc và thêm các đối tượng đã lọc vào danh sách mới
        for (CTDonHangEntity ctdh : sanPhamList) {
            SanPhamEntity sanPham = ctdh.getSanPham();
            boolean checkSPTrung = false;
            for (CTDonHangEntity sp : sanPhamDaLoc) {
                if (sp.getSanPham().getMaSP().equals(sanPham.getMaSP())) {
                    checkSPTrung = true;
                    break;
                }
            }
            if (checkSPTrung) {
                continue;
            }
            int soLuong = tongSoLuongMap.get(sanPham.getMaSP());

            // Tạo một đối tượng mới với tổng số lượng
            CTDonHangEntity ctdhMoi = new CTDonHangEntity();
            ctdhMoi.setSanPham(sanPham);
            ctdhMoi.setSoLuong(soLuong);

            sanPhamDaLoc.add(ctdhMoi);
        }

        // Sử dụng Collections.sort để sắp xếp danh sách theo số lượng giảm dần
        Collections.sort(sanPhamDaLoc, (ctdh2, ctdh1) -> Integer.compare(ctdh1.getSoLuong(), ctdh2.getSoLuong()));
        return sanPhamDaLoc;
    }
    
}
