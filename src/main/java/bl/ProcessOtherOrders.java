package bl;

import bl.beens.Customer;
import bl.beens.FilmProcessingOrder;
import bl.beens.Order;
import dl.CustomerInventory;
import dl.CustomerInventoryImpl;

public class ProcessOtherOrders {


    CustomerInventory customerInventory = new CustomerInventoryImpl();



    public void saveOrder(Order order, Customer customer){


    }



    public Order  getCustomerOrder(String customerEmail){
        Order order = new Order();

        return order;

    }

}
