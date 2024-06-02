package ptithcm.designpattern.State;

import ptithcm.entity.DonHangEntity;

public class CompletedState implements OrderState{
	@Override
    public void handleOrder(DonHangEntity order) {
        order.setTrangThai(3); 
    }
}
