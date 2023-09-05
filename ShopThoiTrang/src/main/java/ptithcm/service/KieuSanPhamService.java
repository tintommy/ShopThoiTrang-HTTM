package ptithcm.service;

import java.util.List;

import ptithcm.entity.KieuSanPhamEntity;

public interface KieuSanPhamService {
	public KieuSanPhamEntity layKieuTheoMa(String maKieu);
	public List<KieuSanPhamEntity> layKieu();
	public boolean kiemTraSanPhamTheoKieu(String kieu);
	
	public void themKieu(KieuSanPhamEntity kieu);
	public void updateKieu(KieuSanPhamEntity kieu);
	public void xoaKieu(KieuSanPhamEntity kieu);
}
