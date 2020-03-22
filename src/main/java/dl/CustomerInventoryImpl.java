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

    public void insertCustomer(Customer customer){
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

    public void updateCustomer(Customer customer){
        if (con == null){
            getConnection();
        }
        try {
            String saveQuery =  " UPDATE CUSTOMER_INVENTORY set" +
                                " CUSTOMER_NAME = ?," +
                                " CUSTOMER_PHONE_NUMBER = ?" +
                                " WHERE  CUSTOMER_EMAIL = ?";
            PreparedStatement preparedStatement = con.prepareStatement(saveQuery);

            preparedStatement.setString(1, customer.getName() );
            preparedStatement.setString(2, customer.getMobileNum());
            preparedStatement.setString(3, customer.getEmail());


            preparedStatement.executeUpdate();

        }catch(java.sql.SQLException jse){
            jse.printStackTrace();
        }

    }

    public void saveCustomer(Customer customer){
        if (customerExists(customer.getEmail())){
            System.out.println( "Customer email: " + customer.getEmail() + " exists!");
            updateCustomer(customer);

        } else {
            insertCustomer(customer);
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
    
}


