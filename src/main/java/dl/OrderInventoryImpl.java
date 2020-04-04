package dl;

import bl.beens.Order;
import bl.enums.FilmType;
import bl.enums.OrderType;

import java.sql.*;

public class OrderInventoryImpl implements OrderInventory{

    Connection con = null;

    private boolean hasData = false;

    public Order retrieveOrder(long orderNumber){

        if (!isConnectionValid()){
            getConnection();
        }
         Order order = new Order();


        try {

            String retrieveOrer ="SELECT * FROM OTHER_ORDERS WHERE ORDER_NUMBER = " + orderNumber;
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(retrieveOrer);
            while (rs.next()) {
                order.setJobNumber(rs.getLong(1));
                order.setOrderType(OrderType.valueOf(rs.getString(2)));
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
        }finally {
            try {
                con.close();
            } catch(java.sql.SQLException jse){
                jse.printStackTrace();
            }
        }
        return order;

    }

    public void closeOrder(long orderId){
        if (!isConnectionValid()){
            getConnection();
        }
        try {
            String updateQuery = "UPDATE OTHER_ORDERS SET" +
                    "COMPLETED_CLOSED = Y" +
                    " WHERE ORDER_ID =" + orderId;

            Statement statement = con.createStatement();

            statement.executeQuery(updateQuery);




        }catch(java.sql.SQLException jse){
            jse.printStackTrace();
        }finally {
            try {
                con.close();
            } catch(java.sql.SQLException jse){
                jse.printStackTrace();
            }
        }
    }

    public void updateOrder(Order order){
        if (!isConnectionValid()){
            getConnection();
        }
        try {
            String updateQuery = "UPDATE OTHER_ORDERS " +
                    " ORDER_TYPE = ?," +
                    " CUSTOMER_EMAIL = ?," +
                    " COLLECTION_DATE = ?," +
                    " CUSTOMER_INSTRUCTION = ?," +
                    " ADMIN_INSTRUCTION = ?," +
                    " TOTAL_PRICE = ?," +
                    " DEPOSIT = ?," +
                    " BALANCE = ?" +
                    " WHERE ORDER_ID =" + order.getJobNumber();

            PreparedStatement preparedStatement = con.prepareStatement(updateQuery);

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
        }finally {
            try {
                con.close();
            } catch(java.sql.SQLException jse){
                jse.printStackTrace();
            }
        }
    }

    private long getMaxOrderNumber(){
        long orderNum = 0;
        if (!isConnectionValid()){
            getConnection();
        }
        try {
            String grtMaxQuery = "SELECT MAX(ORDER_NUMBER) from OTHER_ORDERS ";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(grtMaxQuery);
            orderNum =  rs.getLong(1);

        } catch(java.sql.SQLException jse){
            jse.printStackTrace();
        }finally {
            try {
                con.close();
            } catch(java.sql.SQLException jse){
                jse.printStackTrace();
            }
        }

        return orderNum;
    }

    private long  insertOrder(Order order){

        long userId = 0 ;
        if (!isConnectionValid()){
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

                PreparedStatement preparedStatement = con.prepareStatement(saveQuery,Statement.RETURN_GENERATED_KEYS);

                preparedStatement.setString(1, order.getOrderType().toString() );
                preparedStatement.setString(2, order.getEmail() );
                preparedStatement.setDate(3,  new java.sql.Date(order.getCollectionDate().getTime()));

                preparedStatement.setString(4, order.getCustomerInstruction() );
                preparedStatement.setString(5, order.getAdminInstruction() );
                preparedStatement.setDouble(6, order.getTotalPrice() );
                preparedStatement.setDouble(7, order.getDeposit() );
                preparedStatement.setDouble(8, order.getBalance() );



                preparedStatement.execute();
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        userId = generatedKeys.getLong(1);
                    }
                    else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }



        }catch(java.sql.SQLException jse){
            jse.printStackTrace();
        }finally {
            try {
                con.close();
            } catch(java.sql.SQLException jse){
                jse.printStackTrace();
            }
        }

        return userId;

    }

    public long saveOrder(Order order){
        insertOrder(order);
        long orderNumber = getMaxOrderNumber();
        return orderNumber;

    }

    private boolean isConnectionValid() {
        if (con == null) {
            return false;
        } else {
            try {
                if (con.isClosed()) {
                    return false;
                }
            } catch (SQLException sqle) {
                return false;
            }
        }
        return true;
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

        if (!isConnectionValid()){
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
                            " COMPLETED_CLOSED varchar2(20)," +
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


    public static void main(String[] args) {
        OrderInventoryImpl orderInventoryImpl =  new OrderInventoryImpl();
        long maxOrderId = orderInventoryImpl.getMaxOrderNumber();
        System.out.println("maxOrderId: " + maxOrderId);
    }


}
