package bl.beens;

import bl.enums.OrderType;

import java.util.Date;

public class OtherOrderTypes {
    private String email;
    private OrderType orderType;
    private String jobType;
    private long jobNumber;
    private String customerInstruction;
    private String adminInstruction;

    private Date collectionDate;
    private Date orderDate;

    private double totalPrice;
    private double deposit;
    private double balance;

    private boolean closed;

    public OtherOrderTypes(){

    }


    public OtherOrderTypes( long jobNumber, String email, OrderType orderType, String customerInstruction,
                        String adminInstruction,  Date collectionDate, double totalPrice, double deposit, double remainting,String jobTypeIn) {
        this.email = email;
        this.orderType = orderType;
        this.jobNumber = jobNumber;
        this.customerInstruction = customerInstruction;
        this.adminInstruction = adminInstruction;
        this.collectionDate = collectionDate;
        this.totalPrice = totalPrice;
        this.deposit = deposit;
        this.balance = remainting;
        this.jobType = jobTypeIn;
    }

    public OtherOrderTypes(String email, OrderType orderType, String customerInstruction,
                       String adminInstruction,  Date collectionDate, double totalPrice, double deposit, double remaining,
                           String jobTypeIn) {
        this.email = email;
        this.orderType = orderType;
        this.customerInstruction = customerInstruction;
        this.adminInstruction = adminInstruction;
        this.collectionDate = collectionDate;
        this.totalPrice = totalPrice;
        this.deposit = deposit;
        this.balance = remaining;
        this.jobType = jobTypeIn;
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

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public void displayOrder() {
        System.out.println("jobNumber = " + jobNumber);
        System.out.println("customerInstruction = " + customerInstruction);
        System.out.println("adminInstruction = " + adminInstruction);
        System.out.println("collectionDate = " + collectionDate);
        System.out.println("totalPrice = " + totalPrice);
        System.out.println("deposit = " + deposit);
        System.out.println("balance = " + balance);

        System.out.println("orderDate = " + orderDate);
    }
}
