package dl;

import bl.beens.Customer;
import bl.beens.Order;

public interface CustomerInventory {

    public Customer findCustomer(String email);

    public boolean customerExists(String email);

    public void saveCustomer(Customer customer);

    class OdrerJSON {
        long orderId;
        Order order;

        public OdrerJSON(long orderId, Order order) {
            this.orderId = orderId;
            this.order = order;
        }

        public long getOrderId() {
            return orderId;
        }

        public void setOrderId(long orderId) {
            this.orderId = orderId;
        }

        public Order getOrder() {
            return order;
        }

        public void setOrder(Order order) {
            this.order = order;
        }
    }
}
