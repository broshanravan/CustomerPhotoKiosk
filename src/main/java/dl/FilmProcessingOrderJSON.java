package dl;

import bl.beens.FilmProcessingOrder;

public class FilmProcessingOrderJSON {

    long id;
    FilmProcessingOrder eilmProcessingOrder;

    public FilmProcessingOrderJSON(long id, FilmProcessingOrder eilmProcessingOrder) {
        this.id = id;
        this.eilmProcessingOrder = eilmProcessingOrder;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public FilmProcessingOrder getEilmProcessingOrder() {
        return eilmProcessingOrder;
    }

    public void setEilmProcessingOrder(FilmProcessingOrder eilmProcessingOrder) {
        this.eilmProcessingOrder = eilmProcessingOrder;
    }
}
