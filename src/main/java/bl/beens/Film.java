package bl.beens;

public class Film {
    private String type;
    private int size;
    private int identifier;

    public Film(){

    }
    
    public Film(String type, int size, int identifier) {
        this.type = type;
        this.size = size;
        this.identifier = identifier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }
}
