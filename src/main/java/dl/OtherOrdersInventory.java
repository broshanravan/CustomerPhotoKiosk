package dl;

import bl.beens.OtherOrderTypes;
import java.util.List;

public interface OtherOrdersInventory {

    //public OtherOrders retrieveOrder(long orderNumber, String orderType);

    public OtherOrderTypes retrieveOtherOrder(long orderNumber , String orderType);

    public void closeOrder(long orderId);

    public long saveOrder(OtherOrderTypes order);

    public void updateOrder(OtherOrderTypes order);

    public List<OtherOrderTypes> retrieveAllOrders();

    public OtherOrderTypes getPhotoGiftOrder(long orderId);

    public long getHighestOrderId();

    public boolean orderExists(long orderNumber , String orderType);

}
