package ptithcm.DesignPattern.Strategy.Statistics;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.entity.DonHangEntity;
import ptithcm.service.DonHangService;

@Transactional
public class TotalSuccessOrder implements StatisticsInterface {
	 
		@Autowired
	    private DonHangService donHangService;

	     long totalRevenue;

	    @Override
	    public Object collectStatistics() {
	        List<DonHangEntity> donThanhCong = donHangService.layDonHangTheoTrangThai(3);
	        totalRevenue = 0;
	        for (DonHangEntity donHang : donThanhCong) {
	            totalRevenue += donHang.getTongTien();
	        }
	        
	        return totalRevenue;
	    }

	   
}
