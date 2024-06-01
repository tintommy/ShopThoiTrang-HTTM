package ptithcm.DesignPattern.Strategy.Statistics;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.entity.NguoiDungEntity;
import ptithcm.service.nguoiDungService;

@Transactional
public class UserStatistics implements StatisticsInterface {

	@Autowired
	private nguoiDungService nguoiDungService;

	private int tongSoNguoiDung;

	@Override
	public Object collectStatistics() {
		List<NguoiDungEntity> listNguoiDung = nguoiDungService.getAllUserByRole(0);
		tongSoNguoiDung = listNguoiDung.size();
		return tongSoNguoiDung;
	}

	
}
