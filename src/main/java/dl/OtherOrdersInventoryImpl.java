package dl;


import bl.beens.OtherOrderTypes;
import bl.enums.OrderType;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class OtherOrdersInventoryImpl implements OtherOrdersInventory {

    Connection con = null;

    private boolean hasData = false;

    public OtherOrderTypes retrieveOtherOrder(long orderNumber , String orderType){

        if (!isConnectionValid()){
            getConnection();
        }
        OtherOrderTypes otherOrderTypes = new OtherOrderTypes();

        try {

            String retrieveOrer ="SELECT * FROM OTHER_ORDERS  o WHERE o.ORDER_NUMBER = " + orderNumber + " and o.ORDER_TYPE = " + "'" +orderType + "'";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(retrieveOrer);
            while (rs.next()) {
                String orderTyprstr = rs.getString("ORDER_TYPE");
                otherOrderTypes = new OtherOrderTypes(rs.getLong("ORDER_NUMBER"),
                        rs.getString("CUSTOMER_EMAIL"),
                        OrderType.valueOf(orderTyprstr),
                        rs.getString("CUSTOMER_INSTRUCTION") ,
                        rs.getString("ADMIN_INSTRUCTION"),
                        rs.getDate("COLLECTION_DATE"),
                        rs.getDouble("TOTAL_PRICE"),
                        rs.getDouble("DEPOSIT"),
                        rs.getDouble("BALANCE"),
                        rs.getString("JOB_TYPE"));



                String isClosed = rs.getString("COMPLETED_CLOSED");

                if("Y".equalsIgnoreCase(isClosed)) {
                    otherOrderTypes.setClosed(true);
                } else {
                    otherOrderTypes.setClosed(false);
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
        return otherOrderTypes;

    }

    public boolean orderExists(long orderNumber , String orderType){

        boolean orderExists = false;
        if (!isConnectionValid()){
            getConnection();
        }

        try {

            String retrieveOdrer ="SELECT * FROM OTHER_ORDERS o WHERE o.ORDER_NUMBER = " + orderNumber + " and o.ORDER_TYPE = '" + orderType +"'";
            System.out.println(retrieveOdrer);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(retrieveOdrer);
            if (rs.next()) {
                orderExists = true;

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
        return orderExists;
    }

    public List<OtherOrderTypes> retrieveAllOrders(){

        List<OtherOrderTypes> orderList = new LinkedList<OtherOrderTypes>();
        if (!isConnectionValid()){
            getConnection();
        }



        try {

            String retrieveOrer ="SELECT * FROM OTHER_ORDERS" ;
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(retrieveOrer);
            while (rs.next()) {
                OtherOrderTypes otherOrderType = new OtherOrderTypes();
                otherOrderType.setJobNumber(rs.getLong(1));
                otherOrderType.setOrderType(OrderType.valueOf(rs.getString(2)));
                otherOrderType.setEmail(rs.getString(3));

                otherOrderType.setCollectionDate(rs.getDate(4));
                otherOrderType.setCustomerInstruction(rs.getString(5));
                otherOrderType.setAdminInstruction(rs.getString(6));

                otherOrderType.setTotalPrice(rs.getDouble(7));
                otherOrderType.setDeposit(rs.getDouble(8));
                otherOrderType.setBalance(rs.getDouble(9));
                orderList.add(otherOrderType);

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
        return orderList;

    }

    public void closeOrder(long orderId){
        if (!isConnectionValid()){
            getConnection();
        }
        try {
            System.err.print("Closing order Number" + orderId);
            String updateQuery = "UPDATE OTHER_ORDERS SET" +
                    " COMPLETED_CLOSED = 'Y'" +
                    " WHERE ORDER_NUMBER = " + orderId;

            Statement statement = con.createStatement();

            statement.execute(updateQuery);




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

    public void updateOrder(OtherOrderTypes otherorderTypes){
        if (!isConnectionValid()){
            getConnection();
        }
        try {
            String updateQuery = "UPDATE OTHER_ORDERS SET" +
                    " ORDER_TYPE = ?," +
                    " JOB_TYPE = ?," +
                    " CUSTOMER_EMAIL = ?," +
                    " COLLECTION_DATE = ?," +
                    " CUSTOMER_INSTRUCTION = ?," +
                    " ADMIN_INSTRUCTION = ?," +
                    " TOTAL_PRICE = ?," +
                    " DEPOSIT = ?," +
                    " BALANCE = ?" +
                    " WHERE ORDER_NUMBER = " + otherorderTypes.getJobNumber();

            PreparedStatement preparedStatement = con.prepareStatement(updateQuery);

            preparedStatement.setString(1, otherorderTypes.getOrderType().toString() );
            preparedStatement.setString(2, otherorderTypes.getJobType() );


            preparedStatement.setString(3, otherorderTypes.getEmail() );
            preparedStatement.setDate(4,  new java.sql.Date(otherorderTypes.getCollectionDate().getTime()));

            preparedStatement.setString(5, otherorderTypes.getCustomerInstruction() );
            preparedStatement.setString(6, otherorderTypes.getAdminInstruction() );
            preparedStatement.setDouble(7, otherorderTypes.getTotalPrice() );
            preparedStatement.setDouble(8, otherorderTypes.getDeposit() );
            preparedStatement.setDouble(9, otherorderTypes.getBalance() );


            preparedStatement.executeUpdate();




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

    public long getHighestOrderId(){

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

    private long  insertOrder(OtherOrderTypes otherOrderTypes){

        long userId = 0 ;
        if (!isConnectionValid()){
            getConnection();
        }
        try {
                String saveQuery = "INSERT INTO OTHER_ORDERS (" +
                        " ORDER_TYPE," +
                        " JOB_TYPE," +
                        " CUSTOMER_EMAIL," +
                        " COLLECTION_DATE," +
                        " CUSTOMER_INSTRUCTION," +
                        " ADMIN_INSTRUCTION," +
                        " TOTAL_PRICE," +
                        " DEPOSIT," +
                        " BALANCE" +
                        " )" +
                        " values(?, ?, ?, ?, ?, ?, ?, ?, ?);";

                PreparedStatement preparedStatement = con.prepareStatement(saveQuery,Statement.RETURN_GENERATED_KEYS);

                preparedStatement.setString(1, otherOrderTypes.getOrderType().toString() );
                preparedStatement.setString(2, otherOrderTypes.getJobType() );
                preparedStatement.setString(3, otherOrderTypes.getEmail() );

                preparedStatement.setDate(4,  new Date(otherOrderTypes.getCollectionDate().getTime()));

                preparedStatement.setString(5, otherOrderTypes.getCustomerInstruction() );
                preparedStatement.setString(6, otherOrderTypes.getAdminInstruction() );
                preparedStatement.setDouble(7, otherOrderTypes.getTotalPrice() );
                preparedStatement.setDouble(8, otherOrderTypes.getDeposit() );
                preparedStatement.setDouble(9, otherOrderTypes.getBalance() );



                preparedStatement.execute();
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        userId = generatedKeys.getLong(1);
                    }
                    else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }



        }catch(SQLException jse){
            jse.printStackTrace();
        }finally {
            try {
                con.close();
            } catch(SQLException jse){
                jse.printStackTrace();
            }
        }

        return userId;

    }

    public long saveOrder(OtherOrderTypes otherOrdertype){
        long orderNumber = 0;
        if (otherOrdertype.getJobNumber()!= 0) {
            updateOrder(otherOrdertype);
            orderNumber =  otherOrdertype.getJobNumber();
        } else {
            insertOrder(otherOrdertype);
            orderNumber = getMaxOrderNumber();
        }


        return orderNumber;

    }

    public OtherOrderTypes getPhotoGiftOrder(long orderId){
       OtherOrderTypes otherOrderTypes = null;
        String selectOQuery = "Select * from OTHER_ORDERS  where" +
                "ORDER_NUMBER  =" +  orderId  +
               "and ORDER_TYPE = " + OrderType.PhotoGift;

        try{

            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(selectOQuery);
            if(rs.next()){
                String orderTyprstr = rs.getString("ORDER_TYPE");

                otherOrderTypes = new OtherOrderTypes(rs.getLong("ORDER_NUMBER"),
                                                rs.getString("CUSTOMER_EMAIL"),
                                                OrderType.valueOf(orderTyprstr),
                                                rs.getString("CUSTOMER_INSTRUCTION") ,
                                                rs.getString("ADMIN_INSTRUCTIO"),
                                                rs.getDate("COLLECTION_DATE "),
                                                rs.getDouble("TOTAL_PRICE"),
                                                rs.getDouble("DEPOSIT"),
                                                rs.getDouble("BALANCE"),
                                                rs.getString("JOB_TYPE"));
            }

        }catch(java.sql.SQLException jse){
        jse.printStackTrace();
    }



       return otherOrderTypes;
   }

    public OtherOrderTypes getEngravingOrder(Long orderId){
        OtherOrderTypes otherOrders = null;

        String selectOQuery = "Select * from OTHER_ORDERS  where" +
                "ORDER_NUMBER  =" +  orderId  +
                "and ORDER_TYPE = " + OrderType.Engraving;

        try{

            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(selectOQuery);
            if(rs.next()){
                String orderTyprstr = rs.getString("ORDER_TYPE");

                otherOrders = new OtherOrderTypes(rs.getLong("ORDER_NUMBER"),
                        rs.getString("CUSTOMER_EMAIL"),
                        OrderType.valueOf(orderTyprstr),
                        rs.getString("CUSTOMER_INSTRUCTION") ,
                        rs.getString("ADMIN_INSTRUCTIO"),
                        rs.getDate("COLLECTION_DATE "),
                        rs.getDouble("TOTAL_PRICE"),
                        rs.getDouble("DEPOSIT"),
                        rs.getDouble("BALANCE"),
                        rs.getString("JOB_TYPE"));

                if("Y".equalsIgnoreCase(rs.getString("COMPLETED_CLOSED"))){
                    otherOrders.setClosed(true);
                } else{
                    otherOrders.setClosed(false);
                }

            }

        }catch(java.sql.SQLException jse){
            jse.printStackTrace();
        }



        return otherOrders;
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
                            " JOB_TYPE varchar2(20)," +
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
        OtherOrdersInventoryImpl orderInventoryImpl =  new OtherOrdersInventoryImpl();
        long maxOrderId = orderInventoryImpl.getMaxOrderNumber();
        System.out.println("maxOrderId is : " + maxOrderId);
    }


}
