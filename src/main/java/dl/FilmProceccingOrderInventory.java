package dl;

import bl.beens.FilmProcessingOrder;

public interface FilmProceccingOrderInventory {

    public FilmProcessingOrder getProcessionOrder(long orderNumber);

    public void saveProcessionOrder(FilmProcessingOrder order);
}
