package ptithcm.service;

import java.util.List;

import ptithcm.entity.DanhGiaEntity;

public interface DanhGiaService {
	public List<DanhGiaEntity> layDanhGiaSanPham(String maSp);
	public void saveDanhGia(DanhGiaEntity danhGia);
	
}
