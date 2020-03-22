package dl;

import bl.beens.FilmProcessingOrder;
import bl.enums.FilmType;

import java.sql.*;

public class FilmProceccingOrderInventoryImpl implements FilmProceccingOrderInventory{
    Connection con = null;
    private boolean hasData = false;

    public FilmProcessingOrder getProcessingOrder(long orderNumber){
        FilmProcessingOrder filmProcessingOrder = new FilmProcessingOrder();
        if (con == null){
            getConnection();
        }
        try {

            String retrieveOrer ="SELECT * FROM FILM_PRECESS_ORDERS WHERE ORDER_NUMBER = " + orderNumber;
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(retrieveOrer);
            while (rs.next()) {
                filmProcessingOrder.setOrderNum(rs.getLong(1));
                filmProcessingOrder.setCustomerEmail(rs.getString(2));
                filmProcessingOrder.setOrderDate(rs.getDate(3));
                filmProcessingOrder.setCollectionDate(rs.getDate(4));
                filmProcessingOrder.setPrintSize(rs.getString(5));
                filmProcessingOrder.setBorderType(rs.getString(6));
                filmProcessingOrder.setPrintTime(rs.getString(7));
                filmProcessingOrder.setFilmType(FilmType.valueOf(rs.getString(8)));

                String color = rs.getString(9);

                boolean isColor =true;
                if("N".equalsIgnoreCase(color)){
                    isColor= false;
                }
                filmProcessingOrder.setColor(isColor);

                filmProcessingOrder.setNumberOfCopies(rs.getInt(10));
                filmProcessingOrder.setFilmSize(rs.getString(11));
                filmProcessingOrder.setTotalPrice(rs.getDouble(12));
                filmProcessingOrder.setDeposit(rs.getDouble(13));
                filmProcessingOrder.setBalance(rs.getDouble(14));

            }
        }catch(java.sql.SQLException jse){
            jse.printStackTrace();
        }

        return filmProcessingOrder;

    }

    private long getLatestOrderNum(){
        long orderNum = 0;
        if (con == null){
            getConnection();
        }
        try {
            String grtMaxQuery = "SELECT MAX(ORDER_NUMBER) from FILM_PRECESS_ORDERS ";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(grtMaxQuery);
            orderNum =  rs.getLong(1);

        } catch(java.sql.SQLException jse){
            jse.printStackTrace();
        }

        return orderNum;
    }

    public long saveProcessingOrder(FilmProcessingOrder order){
        long orderNum = 0;
        if (con == null){
            getConnection();
        }
        try {
            String saveQuery ="Insert into FILM_PRECESS_ORDERS " +
                    "(" +
                        " CUSTOMER_EMAIL," +
                        " ORDER_DATE," +
                        " COLLECTION_DATE," +
                        " PRINT_SIZE ," +
                        " BORDER_TYPE," +
                        " PRINT_TIME," +
                        " FILM_TYPE," +
                        " COLOR," +
                        " NUMBER_OF_COPIES," +
                        " FILM_SIZE," +
                        " TOTAL_PRICE," +
                        " DEPOSIT," +
                        " BALANCE" +
                    ")" +
                    " values(?, ?, ?, ?, ?, ?, ? ,? ,?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = con.prepareStatement(saveQuery);


            preparedStatement.setString(1, order.getCustomerEmail() );
            preparedStatement.setDate(2,  new java.sql.Date(order.getOrderDate().getTime()));
            preparedStatement.setDate(3,  new java.sql.Date(order.getCollectionDate().getTime()));
            preparedStatement.setString(4, order.getPrintSize() );
            preparedStatement.setString(5, order.getBorderType() );
            preparedStatement.setString(6, order.getPrintTime() );
            preparedStatement.setString(7, order.getFilmType().toString() );

            /**COLOR    9*/
            String isColor = "Y";
            if(!order.isColor()){
                isColor = "N";
            }
            preparedStatement.setString(8, isColor);

            preparedStatement.setInt(9, order.getNumberOfCopies() );
            preparedStatement.setString(10, order.getFilmSize() );
            preparedStatement.setDouble(11, order.getTotalPrice() );
            preparedStatement.setDouble(12, order.getDeposit() );
            preparedStatement.setDouble(13, order.getBalance() );

            preparedStatement.execute();

            orderNum = this.getLatestOrderNum();

        }catch(java.sql.SQLException jse){
            jse.printStackTrace();
        }

        return orderNum;

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
                String filmProcessingOrderInitQuery = "SELECT name FROM sqlite_master WHERE type ='table' and NAME = 'FILM_PRECESS_ORDERS'";
                Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery(filmProcessingOrderInitQuery);
                if (!rs.next()) {
                    String createCustomerTableSQL = "CREATE TABLE FILM_PRECESS_ORDERS (" +
                            " ORDER_NUMBER INTEGER," +
                            " CUSTOMER_EMAIL varchar2(20)," +
                            " ORDER_DATE  DATETIME," +
                            " COLLECTION_DATE DATETIME," +
                            " PRINT_SIZE varchar2(20)," +
                            " BORDER_TYPE varchar2(20)," +
                            " PRINT_TIME varchar2(20)," +
                            " FILM_TYPE varchar2(20)," +
                            " COLOR varchar2(20)," +
                            " NUMBER_OF_COPIES number," +
                            " FILM_SIZE varchar2(20)," +
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
