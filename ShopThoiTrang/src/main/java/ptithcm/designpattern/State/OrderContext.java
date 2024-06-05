package ptithcm.designpattern.State;

import ptithcm.entity.DonHangEntity;

public class OrderContext {
    private OrderState state;

    public void setState(OrderState state) {
        this.state = state;
    }

    public void applyState(DonHangEntity order) {
        state.handleOrder(order);
    }
}