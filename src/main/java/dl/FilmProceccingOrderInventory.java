package dl;

import bl.beens.FilmProcessingOrder;

public interface FilmProceccingOrderInventory {

    public FilmProcessingOrder getProcessingOrder(long orderNumber);

    public long saveProcessingOrder(FilmProcessingOrder order);
}
