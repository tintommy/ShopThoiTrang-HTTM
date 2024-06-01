package ptithcm.DesignPattern.Strategy.Statistics;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.service.DonHangService;

@Transactional
public class MonthlyRevenue implements StatisticsInterface {

	@Autowired
	private DonHangService donHangService;

	private List<Long> monthlyRevenues;

	@Override
	public Object collectStatistics() {
		monthlyRevenues = new ArrayList<>();
		for (int i = 1; i <= 12; i++) {
			long totalRevenue = donHangService.tinhTongDoanhThuTheoThang(i);
			monthlyRevenues.add(totalRevenue);
		}
		
		return monthlyRevenues;

	}

}
