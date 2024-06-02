package ptithcm.designpattern.Strategy.Statistics;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.entity.DonHangEntity;
import ptithcm.service.DonHangService;

@Service
public class TotalSuccessOrder implements StatisticsInterface {
	 
	
	    private DonHangService donHangService;

	     long totalRevenue;
	     
	     public TotalSuccessOrder() {
			// TODO Auto-generated constructor stub
		}
	     
	     public TotalSuccessOrder(DonHangService donHangService) {
			this.donHangService=donHangService;
		}

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
