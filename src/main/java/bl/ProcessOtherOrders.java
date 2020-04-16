package bl;

import bl.beens.Customer;
import bl.beens.FilmProcessingOrder;
import bl.beens.OtherOrderTypes;
import bl.enums.OrderType;
import dl.*;

public class ProcessOtherOrders {


    CustomerInventory customerInventory = new CustomerInventoryImpl();
    OtherOrdersInventory otherOrdersInventory = new OtherOrdersInventoryImpl();
    FilmProceccingOrderInventory filmProcessingOrderInventory =  new FilmProceccingOrderInventoryImpl();



    public boolean doesEngravingOrderExist(long orderId){
       return  otherOrdersInventory.orderExists(orderId,String.valueOf(OrderType.Engraving));
    }

    public boolean doesPhotoGiftOrderExist(long orderId){
        return  otherOrdersInventory.orderExists(orderId,String.valueOf(OrderType.PhotoGift));
    }

    public boolean doesFilmProcessingOrderExist(long orderId){
        return filmProcessingOrderInventory.filmProcessingOrderExists(orderId);
    }

    public long saveOrder(OtherOrderTypes order, Customer customer){
        long orderId = 0;
        customerInventory.saveCustomer(customer);
        order.setEmail(customer.getEmail());
        orderId = otherOrdersInventory.saveOrder(order);

        return orderId;
    }



    public OtherOrderTypes getPhotoGiftOrder(Long orderNumber ){
        OtherOrderTypes photoGiftOrder = otherOrdersInventory.retrieveOtherOrder(orderNumber,OrderType.PhotoGift.toString());

        return photoGiftOrder;
    }

    public OtherOrderTypes  getEhgravingOrder(Long orderNumber ){
        OtherOrderTypes order = otherOrdersInventory.retrieveOtherOrder(orderNumber,OrderType.Engraving.toString());

        return order;
    }

}
