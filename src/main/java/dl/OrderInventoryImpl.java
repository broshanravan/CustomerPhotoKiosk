package dl;

import bl.beens.Order;
import bl.enums.FilmType;
import bl.enums.OrderType;

import java.sql.*;

public class OrderInventoryImpl implements OrderInventory{

    Connection con = null;
    private boolean hasData = false;

    public Order retrieveOrder(long orderNumber){

        if (con == null){
            getConnection();
        }
         Order order = new Order();


        try {

            String retrieveOrer ="SELECT * FROM OTHER_ORDERS WHERE ORDER_NUMBER = " + orderNumber;
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(retrieveOrer);
            while (rs.next()) {
                order.setJobNumber(rs.getLong(1));
                //order.setOrderType(OrderType.valueOf(rs.getString(2)));
                order.setEmail(rs.getString(3));

                order.setCollectionDate(rs.getDate(4));
                order.setCustomerInstruction(rs.getString(5));
                order.setAdminInstruction(rs.getString(6));

                order.setTotalPrice(rs.getDouble(7));
                order.setDeposit(rs.getDouble(8));
                order.setBalance(rs.getDouble(9));

            }
        }catch(java.sql.SQLException jse){
            jse.printStackTrace();
        }
        return order;

    }


    private long getMaxOrderNumber(){
        long orderNum = 0;
        if (con == null){
            getConnection();
        }
        try {
            String grtMaxQuery = "SELECT MAX(ORDER_NUMBER) from OTHER_ORDERS ";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(grtMaxQuery);
            orderNum =  rs.getLong(1);

        } catch(java.sql.SQLException jse){
            jse.printStackTrace();
        }

        return orderNum;
    }

    private void insertOrder(Order order){

        if (con == null){
            getConnection();
        }
        try {
                String saveQuery = "INSERT INTO OTHER_ORDERS (" +
                        " ORDER_TYPE," +
                        " CUSTOMER_EMAIL," +
                        " COLLECTION_DATE," +
                        " CUSTOMER_INSTRUCTION," +
                        " ADMIN_INSTRUCTION," +
                        " TOTAL_PRICE," +
                        " DEPOSIT," +
                        " BALANCE" +
                        " )" +
                        " values(?, ?, ?, ?, ?, ?, ?, ?);";

                PreparedStatement preparedStatement = con.prepareStatement(saveQuery);

                preparedStatement.setString(1, order.getOrderType().toString() );
                preparedStatement.setString(2, order.getEmail() );
                preparedStatement.setDate(3,  new java.sql.Date(order.getCollectionDate().getTime()));

                preparedStatement.setString(4, order.getCustomerInstruction() );
                preparedStatement.setString(5, order.getAdminInstruction() );
                preparedStatement.setDouble(6, order.getTotalPrice() );
                preparedStatement.setDouble(7, order.getDeposit() );
                preparedStatement.setDouble(8, order.getBalance() );

                preparedStatement.execute();


        }catch(java.sql.SQLException jse){
            jse.printStackTrace();
        }


    }
    public long saveOrder(Order order){
        long orderNumber = 0;
        insertOrder(order);
        orderNumber = getMaxOrderNumber();
        return orderNumber;

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
                String orderInitQuery = "SELECT name FROM sqlite_master WHERE type ='table' and NAME = 'OTHER_ORDERS'";
                Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery(orderInitQuery);
                if (!rs.next()) {
                    String createCustomerTableSQL = "CREATE TABLE OTHER_ORDERS (" +
                            " ORDER_NUMBER INTEGER," +
                            " ORDER_TYPE varchar2(20)," +
                            " CUSTOMER_EMAIL varchar2(20)," +
                            " COLLECTION_DATE DATETIME," +
                            " CUSTOMER_INSTRUCTION varchar(120)," +
                            " ADMIN_INSTRUCTION varchar(120)," +
                            " TOTAL_PRICE double," +
                            " DEPOSIT double," +
                            " BALANCE double," +
                            " PRIMARY KEY (ORDER_NUMBER)" +
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
