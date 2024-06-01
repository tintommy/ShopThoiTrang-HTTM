package ptithcm.DesignPattern.Strategy.Statistics;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import ptithcm.service.DonHangService;


@Transactional
public class OrderStatusStatistics implements StatisticsInterface {


    @Autowired
    private DonHangService donHangService;

    private int tongDonChoXacNhan;
    private int tongDonDangGiao;
    private int tongDonThanhCong;
    private int tongDonDaHuy;

    @Override
    public Object collectStatistics() {
    	List<Integer> tongDon= new ArrayList<>();
    	tongDonDaHuy = donHangService.layDonHangTheoTrangThai(0).size();
    	tongDon.add(tongDonDaHuy);
        tongDonChoXacNhan = donHangService.layDonHangTheoTrangThai(1).size();
        tongDon.add(tongDonChoXacNhan);
        tongDonDangGiao = donHangService.layDonHangTheoTrangThai(2).size();
        tongDon.add(tongDonDangGiao);
        tongDonThanhCong = donHangService.layDonHangTheoTrangThai(3).size();
        tongDon.add(tongDonThanhCong);
        return tongDon;
        
    }

}
