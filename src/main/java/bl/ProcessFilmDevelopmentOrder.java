package bl;


import bl.beens.Customer;
import bl.beens.FilmProcessingOrder;
import bl.beens.OtherOrderTypes;
import dl.CustomerInventory;
import dl.CustomerInventoryImpl;
import dl.FilmProceccingOrderInventory;
import dl.FilmProceccingOrderInventoryImpl;

public class ProcessFilmDevelopmentOrder {


    CustomerInventory customerInventory = new CustomerInventoryImpl();

    FilmProceccingOrderInventory filmProceccingOrderInventory= new FilmProceccingOrderInventoryImpl();

    public long saveFilmProcessingOrder(FilmProcessingOrder order, Customer customer){
        customerInventory.saveCustomer(customer);
        long orderId = filmProceccingOrderInventory.saveProcessingOrder(order);
        return orderId;
    }

    public boolean doesFilmProcessingOrderExist(long orderId){
        return filmProceccingOrderInventory.filmProcessingOrderExists(orderId);
    }

    public void closeFilmProcessingOrderExist(long orderId){
         filmProceccingOrderInventory.closeFilmProcessingOrder(orderId);
    }

    public FilmProcessingOrder getFilmProcessingOrder(long OrderNumber){
        FilmProcessingOrder filmProcessingOrder = filmProceccingOrderInventory.retrieveProcessingOrder(OrderNumber);
        return filmProcessingOrder;

    }


}
