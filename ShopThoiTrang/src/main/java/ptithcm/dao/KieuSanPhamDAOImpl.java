package ptithcm.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ptithcm.entity.KieuSanPhamEntity;

@Transactional
@Repository
public class KieuSanPhamDAOImpl implements KieuSanPhamDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<KieuSanPhamEntity> layKieu() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM KieuSanPhamEntity";
		Query query = session.createQuery(hql);
	    List<KieuSanPhamEntity> listKieu = query.list();
	    return listKieu;
	}

	@Override
	public KieuSanPhamEntity layKieuTheoMa(String maKieu) {
		Session session = sessionFactory.getCurrentSession();
	    KieuSanPhamEntity Kieu = (KieuSanPhamEntity) session.get(KieuSanPhamEntity.class, maKieu);
	    return Kieu;
	}

	@Override
	public void themKieu(KieuSanPhamEntity kieu) {
		sessionFactory.getCurrentSession().save(kieu);
	}

	@Override
	public void updateKieu(KieuSanPhamEntity kieu) {
		sessionFactory.getCurrentSession().update(kieu);		
	}

	@Override
	public void xoaKieu(KieuSanPhamEntity kieu) {
		sessionFactory.getCurrentSession().delete(kieu);
	}

}
