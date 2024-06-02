package ptithcm.designpattern.Strategy.Statistics;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.entity.NguoiDungEntity;
import ptithcm.service.nguoiDungService;

@Service
public class UserStatistics implements StatisticsInterface {

	private nguoiDungService nguoiDungService;

	private int tongSoNguoiDung;

	public UserStatistics() {
		// TODO Auto-generated constructor stub
	}

	public UserStatistics(ptithcm.service.nguoiDungService nguoiDungService) {
		super();
		this.nguoiDungService = nguoiDungService;
	}

	@Override
	public Object collectStatistics() {
		List<NguoiDungEntity> listNguoiDung = nguoiDungService.getAllUserByRole(0);
		tongSoNguoiDung = listNguoiDung.size();
		return tongSoNguoiDung;
	}

}
