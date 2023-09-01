package ptithcm.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ptithcm.entity.LoaiSanPhamEntity;
@Transactional
@Service
public interface loaiSanPhamService {
	public LoaiSanPhamEntity layLoaiTheoMa(String maLoai);
	public List<LoaiSanPhamEntity> layLoai();
	public List<LoaiSanPhamEntity> layLoaiTheoGioiTinh(String gioiTinh);
}
