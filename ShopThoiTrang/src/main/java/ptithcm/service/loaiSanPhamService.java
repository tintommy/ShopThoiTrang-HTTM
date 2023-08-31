package ptithcm.service;

import java.util.List;

import ptithcm.entity.LoaiSanPhamEntity;

public interface loaiSanPhamService {
	public LoaiSanPhamEntity layLoaiTheoMa(String maLoai);
	public List<LoaiSanPhamEntity> layLoai();
}
