package dl;

import bl.beens.Customer;

public class CustomerJSON {

    private String email;
    private Customer customer;


    public CustomerJSON() {

    }


    public CustomerJSON(String email, Customer customer) {
        this.email = email;
        this.customer = customer;
    }

    public String getEmail() {
        return email;

    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
