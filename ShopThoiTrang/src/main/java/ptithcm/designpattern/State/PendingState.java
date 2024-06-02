package ptithcm.designpattern.State;

import ptithcm.entity.DonHangEntity;

public class PendingState implements OrderState{
	@Override
    public void handleOrder(DonHangEntity order) {
        order.setTrangThai(1);
    }
}
