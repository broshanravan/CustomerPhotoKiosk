package bl.beens;

import bl.enums.OrderType;

import java.util.Date;

public class Order {

    private String email;
    private OrderType orderType;
    private long jobNumber;
    private String customerInstruction;
    private String adminInstruction;

    private Date collectionDate;

    private double totalPrice;
    private double deposit;
    private double balance;

    public Order(){

    }


    public Order( long jobNumber, String email, OrderType orderType, String customerInstruction,
               String adminInstruction,  Date collectionDate, double totalPrice, double deposit, double remainting) {
        this.email = email;
        this.orderType = orderType;
        this.jobNumber = jobNumber;
        this.customerInstruction = customerInstruction;
        this.adminInstruction = adminInstruction;
        this.collectionDate = collectionDate;
        this.totalPrice = totalPrice;
        this.deposit = deposit;
        this.balance = remainting;
    }

    public Order(String email, OrderType orderType, String customerInstruction,
                 String adminInstruction,  Date collectionDate, double totalPrice, double deposit, double remaining) {
        this.email = email;
        this.orderType = orderType;
        this.customerInstruction = customerInstruction;
        this.adminInstruction = adminInstruction;
        this.collectionDate = collectionDate;
        this.totalPrice = totalPrice;
        this.deposit = deposit;
        this.balance = remaining;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public long getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(long jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getCustomerInstruction() {
        return customerInstruction;
    }

    public void setCustomerInstruction(String customerInstruction) {
        this.customerInstruction = customerInstruction;
    }

    public String getAdminInstruction() {
        return adminInstruction;
    }

    public void setAdminInstruction(String adminInstruction) {
        this.adminInstruction = adminInstruction;
    }

   public Date getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(Date collectionDate) {
        this.collectionDate = collectionDate;
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

    public void displayOrder() {
        System.out.println("jobNumber = " + jobNumber);
        System.out.println("customerInstruction = " + customerInstruction);
        System.out.println("adminInstruction = " + adminInstruction);
        System.out.println("collectionDate = " + collectionDate);
        System.out.println("totalPrice = " + totalPrice);
        System.out.println("deposit = " + deposit);
        System.out.println("balance = " + balance);
    }

}
