package dl;

import bl.beens.Customer;

public interface CustomerInventory {

    public Customer findCustomer(String email);

    public boolean customerExists(String email);

    public void insertCustomer(Customer customer);

    public void updateCustomer(Customer customer);

    public void saveCustomer(Customer customer);


}
