package bl.beens;

public class Customer {

    private String name;
    private String mobileNum;



    private String email;



    public Customer(){

    }

    public Customer(String name, String mobileNum, String email) {
        this.name = name;
        this.mobileNum = mobileNum;
        this.email = email;
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

    public void setEmail(String email) {
        this.email = email;
    }

}
