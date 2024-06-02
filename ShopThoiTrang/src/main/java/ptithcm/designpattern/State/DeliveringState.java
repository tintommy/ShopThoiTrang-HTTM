package ptithcm.designpattern.State;

import ptithcm.entity.DonHangEntity;

public class DeliveringState implements OrderState{
	public void handleOrder(DonHangEntity order) {
        order.setTrangThai(2); 
    }
}
