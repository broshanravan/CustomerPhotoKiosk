package bl.beens;

import bl.enums.FilmType;

import java.util.Date;

public class FilmProcessingOrder {

    private String customerEmail;
    private long orderNum;
    private Date orderDate;
    private Date collectionDate;

    private String printSize;
    private String borderType;
    private String printTime;
    private FilmType filmType;
    private boolean color;

    private int numberOfCopies;

    private String filmSize;

    private double totalPrice;
    private double depoisite;
    private double balance;

    public FilmProcessingOrder(){

    }

    public FilmProcessingOrder(String customerEmail,
                               Date orderaAte,
                               Date collectionDate,
                               String printSise,
                               String borderType,
                               String printTime,
                               FilmType filmType,
                               boolean color,
                               int numberOfCopies,
                               String filmSize,
                               double totalPrice,
                               double depoisit,
                               double balance) {
        this.customerEmail = customerEmail;
        this.orderNum = orderNum;
        this.orderDate = orderaAte;
        this.collectionDate = collectionDate;
        this.printSize = printSise;
        this.borderType = borderType;
        this.printTime = printTime;
        this.filmType = filmType;
        this.color = color;
        this.numberOfCopies = numberOfCopies;
        this.filmSize = filmSize;
        this.totalPrice = totalPrice;
        this.depoisite = depoisit;
        this.balance = balance;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(long orderNum) {
        this.orderNum = orderNum;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(Date collectionDate) {
        this.collectionDate = collectionDate;
    }

    public String getPrintSize() {
        return printSize;
    }

    public void setPrintSize(String printSize) {
        this.printSize = printSize;
    }

    public String getBorderType() {
        return borderType;
    }

    public void setBorderType(String borderType) {
        this.borderType = borderType;
    }

    public String getPrintTime() {
        return printTime;
    }

    public void setPrintTime(String printTime) {
        this.printTime = printTime;
    }

    public FilmType getFilmType() {
        return filmType;
    }

    public void setFilmType(FilmType filmType) {
        this.filmType = filmType;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    public String getFilmSize() {
        return filmSize;
    }

    public void setFilmSize(String filmSize) {
        this.filmSize = filmSize;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getDepoisite() {
        return depoisite;
    }

    public void setDepoisite(double depoisite) {
        this.depoisite = depoisite;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
