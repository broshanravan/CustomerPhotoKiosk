package bl;


import bl.beens.Customer;
import bl.beens.FilmProcessingOrder;
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

    public void updateFilmProcessingOrder(FilmProcessingOrder order, Customer customer){

        customerInventory.saveCustomer(customer);
        long orderId = filmProceccingOrderInventory.saveProcessingOrder(order);


    }


    public FilmProcessingOrder getFilmProcessingOrder(long OrderNumber){
        FilmProcessingOrder filmProcessingOrder = filmProceccingOrderInventory.retrieveProcessingOrder(OrderNumber);

        return filmProcessingOrder;

    }


}
