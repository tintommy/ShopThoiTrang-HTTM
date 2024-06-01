package ptithcm.DesignPattern.State;

import ptithcm.entity.DonHangEntity;

public interface OrderState {
	 void handleOrder(DonHangEntity order);
}

