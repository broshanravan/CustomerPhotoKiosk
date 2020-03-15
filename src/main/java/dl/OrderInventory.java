package dl;

import bl.beens.Order;

public interface OrderInventory {

    public Order getProcessionOrder(long orderNumber);

    public void saveProcessionOrder(Order order);
}
