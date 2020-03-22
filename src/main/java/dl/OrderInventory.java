package dl;

import bl.beens.Order;

public interface OrderInventory {

    public Order retrieveOrder(long orderNumber);

    public long saveOrder(Order order);
}
