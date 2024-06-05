package ptithcm.designpattern.State;

import ptithcm.entity.DonHangEntity;

public class CancelledState implements OrderState{
	 @Override
	    public void handleOrder(DonHangEntity order) {
	        order.setTrangThai(0); 
	    }
}
