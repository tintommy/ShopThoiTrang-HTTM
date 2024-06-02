package ptithcm.designpattern.FacadePattern;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ptithcm.entity.GioHangEntity;
import ptithcm.entity.LoaiSanPhamEntity;
import ptithcm.entity.SanPhamEntity;
import ptithcm.service.SanPhamService;
import ptithcm.service.gioHangService;

@Component
@Transactional
public class GioHangFacade {

    @Autowired
    private gioHangService gioHangService;
    

    public List<GioHangEntity> layGioHangCuaUser(int maNd) {
        return gioHangService.layGioHangCuaUser(maNd);
    }

    public void updateSoLuong(int soLuong, int maGh) {
        gioHangService.updateSoLuong(soLuong, maGh);
    }

    public void deleteGioHang(int maGh) {
        gioHangService.deleteGioHang(maGh);
    }

}
