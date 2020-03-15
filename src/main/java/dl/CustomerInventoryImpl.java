package dl;

import bl.beens.Customer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class CustomerInventoryImpl implements CustomerInventory {

    private static Connection con;
    private boolean hasData = false;

    public CustomerInventoryImpl(){
        if(con == null){
            getConnection();
        }
    }


    public Customer findCustomer(String email){
        Customer customer = new Customer();
        try {

            String findQuery ="SELECT * FROM CUSTOMER_INVENTORY c WHERE c.CUSTOMER_EMAIL = '" + email + "'";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(findQuery);
            while (rs.next()) {
                customer.setCustomerId(rs.getInt(1));
                customer.setName(rs.getString(2));
                customer.setMobileNum(rs.getString(3));
                customer.setEmail(rs.getString(4));

            }
        }catch(java.sql.SQLException jse){
            jse.printStackTrace();
        }

        return customer;
    }

    public boolean customerExists(String email){
        if (con == null){
            getConnection();
        }
        boolean exists = false;
        try {
            String findQuery ="SELECT * FROM CUSTOMER_INVENTORY c WHERE c.CUSTOMER_EMAIL = '" + email + "'";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(findQuery);
            if (rs.next()) {
                exists = true;
            }
        }catch(java.sql.SQLException jse){
            jse.printStackTrace();
        }

        return exists;

    }

    public Customer getCustomer(String email){
        Customer customer = null;
        if (con == null){
            getConnection();
        }

        boolean exists = false;
        try {
            String findQuery ="SELECT * FROM CUSTOMER_INVENTORY c WHERE c.email = '" + email + "'";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(findQuery);
            customer = new Customer();
            while (rs.next()) {
                customer.setCustomerId(rs.getInt("CUSTOMER_ID"));
                customer.setName(rs.getString("CUSTOMER_NAME"));
                customer.setMobileNum(rs.getString("CUSTOMER_PHONE_NUMBER"));
                customer.setEmail(rs.getString("CUSTOMER_EMAIL"));
            }
        }catch(java.sql.SQLException jse){
            jse.printStackTrace();
        }

        return customer;

    }

    public void saveCustomer(Customer customer){
        if (con == null){
            getConnection();
        }
        try {
            String saveQuery ="Insert into CUSTOMER_INVENTORY values(?,?,?,?);";
            PreparedStatement preparedStatement = con.prepareStatement(saveQuery);

            preparedStatement.setString(2, customer.getName() );
            preparedStatement.setString(3, customer.getMobileNum() );
            preparedStatement.setString(4, customer.getEmail() );


            preparedStatement.execute();

        }catch(java.sql.SQLException jse){
            jse.printStackTrace();
        }


    }

    private void getConnection(){
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("JDBC:sqlite:PHOTO_KIOSK.db");
            initialize();
        }catch(ClassNotFoundException cnfx){
            cnfx.printStackTrace();
        }catch(java.sql.SQLException jse){
            jse.printStackTrace();
        }
    }

    private void initialize(){

        if (con == null){
            getConnection();
        }
        if(!hasData){

            System.out.println("First time initialization of DB");
            try {
                String customerInitQuery = "SELECT name FROM sqlite_master WHERE type ='table' and NAME = 'CUSTOMER_INVENTORY'";
                Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery(customerInitQuery);
                if (!rs.next()) {
                    String createCustomerTableSQL = "CREATE TABLE CUSTOMER_INVENTORY (" +
                                                                                        " CUSTOMER_ID INTEGER," +
                                                                                        " CUSTOMER_NAME varchar2(20)," +
                                                                                        " CUSTOMER_PHONE_NUMBER varchar2(20)," +
                                                                                        " CUSTOMER_EMAIL varchar2(20)," +
                                                                                        " PRIMARY KEY (CUSTOMER_ID)" +
                                                                                    ");";
                    Statement statement_2 = con.createStatement();
                    statement_2.execute(createCustomerTableSQL);

                    hasData = true;
                }

            }catch(java.sql.SQLException jse){
                jse.printStackTrace();
            }
        }

    }




    public static void main(String [] args) {
        CustomerInventory customerInventoryImpl = new CustomerInventoryImpl();
        Customer Bruce = new Customer("Bruce","07861671879", "broshanravan@hotmail.com");
        Customer Laleh = new Customer("Laleh","078422223", "lahehn@hotmail.com");
        Customer Areya = new Customer("Areya","0786467865", "aroshan@gmail.com");
        Customer Atoosa = new Customer("Atoosa","44445555", "atoohanravan@outlook.com");
        //customerInventoryImpl.saveCustomer(Bruce);
        //customerInventoryImpl.saveCustomer(Laleh);

        Customer customer = customerInventoryImpl.findCustomer("broshanravan@hotmail.com");
        customer.displayCustomerDetails();

        System.out.println(customerInventoryImpl.customerExists("lahehn@hotmail.com"));
        System.out.println(customerInventoryImpl.customerExists("atoohanravan@outlook.com"));


        //customerInventoryImpl.saveCustomer(Areya);
        //customerInventoryImpl.saveCustomer(Atoosa);





    }
}


