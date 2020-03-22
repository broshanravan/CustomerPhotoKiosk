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

    public void saveFilmProcessingOrder(FilmProcessingOrder order, Customer customer){

        customerInventory.saveCustomer(customer);
        filmProceccingOrderInventory.saveProcessingOrder(order);

    }


    public FilmProcessingOrder getFilmProcessingOrder(long OrderNumber){
        FilmProcessingOrder filmProcessingOrder = filmProceccingOrderInventory.getProcessingOrder(OrderNumber);

        return filmProcessingOrder;

    }


}
