package dl;

import bl.beens.Order;

public interface OrderInventory {

    public Order retrieveOrder(long orderNumber);

    public void closeOrder(long orderId);

    public long saveOrder(Order order);

    public void updateOrder(Order order);

}
