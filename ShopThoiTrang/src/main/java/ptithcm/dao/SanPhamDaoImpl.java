package ptithcm.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ptithcm.entity.KieuSanPhamEntity;
import ptithcm.entity.LoaiSanPhamEntity;
import ptithcm.entity.SanPhamEntity;

@Transactional
@Repository
public class SanPhamDaoImpl implements SanPhamDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public SanPhamEntity laySanPham(String maSp) {
		SanPhamEntity sanPham = (SanPhamEntity) sessionFactory.getCurrentSession().get(SanPhamEntity.class, maSp);
		return sanPham;
	}

	@Override
	public List<SanPhamEntity> laySanPhamTheoMa(String key) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM SanPhamEntity sp WHERE sp.maSP LIKE :key " + "OR sp.tenSanPham LIKE :key "
				+ "OR sp.maKieu.tenKieu LIKE :key " ;
//	                 "OR sp.loaiSanPham.tenLoai LIKE :key"+
//				"OR (sp.maKieu).loai.maLoai LIKE :key";
		Query query = session.createQuery(hql);
		query.setParameter("key", "%" + key + "%");
		return query.list();

	}
	
	@Override
	public List<SanPhamEntity> laySanPhamCungTen(String maSp){
		Session session = sessionFactory.getCurrentSession();
	    SanPhamEntity sp = (SanPhamEntity) session.get(SanPhamEntity.class, maSp);
	    String ten = sp.getTenSanPham();
	    String hql = "FROM SanPhamEntity WHERE tenSanPham = :ten AND trangThai = true";
	    Query query = session.createQuery(hql);
	    query.setParameter("ten", ten);
	    List<SanPhamEntity> spCungKieu = query.list();	    
	    return spCungKieu;
	}
	
	@Override
	public SanPhamEntity laySanPhamTheoMaVaSize(String maSp, String size){
		Session session = sessionFactory.getCurrentSession();
        String hql = "FROM SanPhamEntity sp WHERE sp.maSP = :maSP AND sp.size = :size";
        return (SanPhamEntity) session.createQuery(hql)
                .setParameter("maSP", maSp)
                .setParameter("size", size)
                .uniqueResult();
    }
	

	@Override
	public List<SanPhamEntity> LaySanPhamMotTrang(int page, int pageSize){
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM SanPhamEntity sp WHERE trangThai=True ";
		Query query = session.createQuery(hql);
		int offset = page * pageSize;
		List<SanPhamEntity> list = query.setFirstResult(offset).setMaxResults(pageSize).list();
		return list;
	}
	
	@Override
	public List<SanPhamEntity> LaySanPhamMotTrangTheoLoai(String loai, int page, int pageSize) {

		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM SanPhamEntity sp WHERE (sp.maKieu).loai.maLoai = :loai and trangThai=True ";
		Query query = session.createQuery(hql).setParameter("loai", loai);

		int offset = page * pageSize;
		List<SanPhamEntity> list = query.setFirstResult(offset).setMaxResults(pageSize).list();
		return list;
	}

	@Override
	public List<SanPhamEntity> laySanPhamTheoLoai(String loai) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM SanPhamEntity sp WHERE sp.loaiSanPham.maLoai = :loai and trangThai=True ";
		Query query = session.createQuery(hql).setParameter("loai", loai);
		query.setMaxResults(6);
		List<SanPhamEntity> list = query.list();
		return list;
	}
	
	@Override
	public List<SanPhamEntity> laySanPhamCungKieu(String maSp) {
	    Session session = sessionFactory.getCurrentSession();
	    SanPhamEntity sp = (SanPhamEntity) session.get(SanPhamEntity.class, maSp);
	    KieuSanPhamEntity kieu = sp.getMaKieu();
	    String ten = sp.getTenSanPham();
	    String hql = "FROM SanPhamEntity WHERE maKieu = :kieu AND tenSanPham != :ten AND trangThai = true";
	    Query query = session.createQuery(hql);
	    query.setParameter("kieu", kieu);
	    query.setParameter("ten", ten);
//	    query.setMaxResults(8);
	    List<SanPhamEntity> spCungKieu = query.list();
	    
	    return spCungKieu;
	}


	@Override
	public List<SanPhamEntity> laySanPhamCungLoai(String maSp) {
		/*
		 * Session session = sessionFactory.getCurrentSession(); SanPhamEntity sp =
		 * (SanPhamEntity) session.get(SanPhamEntity.class, maSp); LoaiSanPhamEntity
		 * loai = sp.getLoaiSanPham(); String hql =
		 * "FROM SanPhamEntity WHERE loaiSanPham = :loai AND maSP != :maSp and trangThai=True"
		 * ; Query query = session.createQuery(hql); query.setParameter("loai", loai);
		 * query.setParameter("maSp", maSp); query.setMaxResults(3);
		 */
		List<SanPhamEntity> sanPhamCungLoai = new ArrayList<>();
		return sanPhamCungLoai;
	}

	@Override
	public List<SanPhamEntity> laySanPhamNgauNhien() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM SanPhamEntity where trangThai=True ORDER BY NEWID()";
		Query query = session.createQuery(hql);
		query.setMaxResults(8);
		List<SanPhamEntity> listNgauNhien = query.list();
		return listNgauNhien;
	}

	@Override
	public List<SanPhamEntity> laySanPhamMoi() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM SanPhamEntity where trangThai=True ORDER BY NGAYTHEM DESC"; // String hql = "FROM
																						// SanPhamEntity ORDER BY id
																						// DESC";
		Query query = session.createQuery(hql);
		query.setMaxResults(6);
		List<SanPhamEntity> danhSachSanPham = query.list();
		return danhSachSanPham;
	}

	@Override
	public List<SanPhamEntity> locSanPham(List<String> stylesList, int minPrice,
			int maxPrice) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM SanPhamEntity sp WHERE trangThai=True ";
		if (stylesList != null && !stylesList.isEmpty()) {
			hql += "AND sp.maKieu.tenKieu IN :stylesList ";
		}
		if (minPrice >= 0 && maxPrice >= 0) {
			hql += "AND sp.donGia >= :minPrice AND sp.donGia <= :maxPrice ";
		}

		Query query = session.createQuery(hql);
		if (stylesList != null && !stylesList.isEmpty()) {
			query.setParameterList("stylesList", stylesList);
		}
		if (minPrice >= 0 && maxPrice >= 0) {
			query.setParameter("minPrice", minPrice).setParameter("maxPrice", maxPrice);
		}
		List<SanPhamEntity> dsKieu = query.list();
		return dsKieu;

	}

	@Override
	public float tinhSoSaoTB(SanPhamEntity sanPham) {
		Session session = sessionFactory.getCurrentSession();

		// Tính trung bình số sao đánh giá
		Query query = session.createQuery("SELECT AVG(soSao) FROM DanhGiaEntity WHERE sanPham = :sanPham");
		query.setParameter("sanPham", sanPham);
		Double avg = (Double) query.uniqueResult();
		if (avg == null) {
			return 0; // hoặc giá trị mặc định khác tùy ý
		}

		Float avgFloat = avg.floatValue();

		// Làm tròn đến 1 chữ số thập phân
		return (float) (Math.round(avgFloat * 10.0) / 10.0);
	}

	@Override
	public List<SanPhamEntity> layAllSanPham() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM SanPhamEntity where trangThai=True ORDER BY NGAYTHEM DESC";
		Query query = session.createQuery(hql);
		List<SanPhamEntity> danhSachSanPham = query.list();
		return danhSachSanPham;
	}

	@Override
	public List<SanPhamEntity> layAllSanPhamDaNgungBan() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM SanPhamEntity where trangThai=False ORDER BY NGAYTHEM DESC";
		Query query = session.createQuery(hql);
		List<SanPhamEntity> danhSachSanPham = query.list();
		return danhSachSanPham;
	}

	@Override
	public void themSanPham(SanPhamEntity sanPham) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(sanPham);
			t.commit();

		} catch (Exception ex) {
			t.rollback();
			System.out.print("loi");

		} finally {
			session.close();
		}
	}

	@Override
	public void updateSanPham(SanPhamEntity sanPham) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(sanPham);
			t.commit();

		} catch (Exception ex) {
			t.rollback();
			System.out.print("loi");

		} finally {
			session.close();
		}
	}

	@Override
	public void xoaSanPham(SanPhamEntity sanPham) {
		sessionFactory.getCurrentSession().delete(sanPham);
	}

	@Override
	public List<SanPhamEntity> layAllSanPhamTheoLoai(String loai) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM SanPhamEntity sp WHERE (sp.maKieu).loai.maLoai = :loai and trangThai=True";
		Query query = session.createQuery(hql).setParameter("loai", loai);
		List<SanPhamEntity> list = query.list();
		return list;
	}

}
