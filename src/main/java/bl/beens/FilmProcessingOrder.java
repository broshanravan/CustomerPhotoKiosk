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
    private double deposit;
    private double balance;

    private boolean completed;

    public FilmProcessingOrder(){

    }

    public void displayFilmProcessingOrder(){
        System.out.println("orderNum = " + orderNum);
        System.out.println("customerEmail = " + customerEmail);
        System.out.println("orderDate = " + orderDate);
        System.out.println("collectionDate = " + collectionDate);
        System.out.println("printSize = " + printSize);
        System.out.println("borderType = " + borderType);
        System.out.println("printTime = " + printTime);
        System.out.println("filmType = " + filmType);
        System.out.println("color = " + color);
        System.out.println("numberOfCopies = " + numberOfCopies);
        System.out.println("filmSize = " + filmSize);
        System.out.println("totalPrice = " + totalPrice);
        System.out.println("depoisit = " + deposit);
        System.out.println("balance = " + balance);
    }

    public FilmProcessingOrder(String customerEmail,
                               Date orderDate,
                               Date collectionDate,
                               String printSize,
                               String borderType,
                               String printTime,
                               FilmType filmType,
                               boolean color,
                               int numberOfCopies,
                               String filmSize,
                               double totalPrice,
                               double deposit,
                               double balance) {
        this.customerEmail = customerEmail;
        this.orderDate = orderDate;
        this.collectionDate = collectionDate;
        this.printSize = printSize;
        this.borderType = borderType;
        this.printTime = printTime;
        this.filmType = filmType;
        this.color = color;
        this.numberOfCopies = numberOfCopies;
        this.filmSize = filmSize;
        this.totalPrice = totalPrice;
        this.deposit = deposit;
        this.balance = balance;
    }
    public FilmProcessingOrder(long orderNum,
                               String customerEmail,
                               Date orderDate,
                               Date collectionDate,
                               String printSize,
                               String borderType,
                               String printTime,
                               FilmType filmType,
                               boolean color,
                               int numberOfCopies,
                               String filmSize,
                               double totalPrice,
                               double deposit,
                               double balance) {
        this.orderNum = orderNum;
        this.customerEmail = customerEmail;
        this.orderDate = orderDate;
        this.collectionDate = collectionDate;
        this.printSize = printSize;
        this.borderType = borderType;
        this.printTime = printTime;
        this.filmType = filmType;
        this.color = color;
        this.numberOfCopies = numberOfCopies;
        this.filmSize = filmSize;
        this.totalPrice = totalPrice;
        this.deposit = deposit;
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

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
