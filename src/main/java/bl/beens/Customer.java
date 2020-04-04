package bl.beens;

public class Customer {

    private long customerId;
    private String name;
    private String mobileNum;



    private String email;



    public Customer(){

    }

    public Customer(String name, String mobileNum, String email, long customerId) {
        this.customerId =customerId;
        this.name = name;
        this.mobileNum = mobileNum;
        this.email = email;
    }

    public Customer(String name, String mobileNum, String email) {
        this.name = name;
        this.mobileNum = mobileNum;
        this.email = email;
    }

    public void displayCustomerDetails(){

       System.out.println("customerId = " + customerId);
        System.out.println("name = " + name);
        System.out.println("mobileNum = " + mobileNum);
        System.out.println("email = " + email);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getEmail() {
        return email;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public void setEmail(String email) {
        this.email = email;

    }



}
