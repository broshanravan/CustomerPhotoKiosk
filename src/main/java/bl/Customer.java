package bl;

public class Customer {

    private String firstName;
    private String surname;
    private String email;
    private String address1;
    private String address2;
    private String postCode;


    public Customer(){

    }


    public Customer(String firstName, String surname, String email, String address1, String address2, String postCode) {
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.address1 = address1;
        this.address2 = address2;
        this.postCode = postCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}
