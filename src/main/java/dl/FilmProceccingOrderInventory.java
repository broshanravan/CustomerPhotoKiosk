package dl;

import bl.beens.FilmProcessingOrder;

public interface FilmProceccingOrderInventory {

    public FilmProcessingOrder retrieveProcessingOrder(long orderNumber);

    public long saveProcessingOrder(FilmProcessingOrder order);
}
