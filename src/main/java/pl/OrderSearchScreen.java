package pl;

import bl.ProcessFilmDevelopmentOrder;
import bl.ProcessOtherOrders;
import bl.beens.Customer;
import bl.beens.FilmProcessingOrder;
import bl.beens.OtherOrderTypes;
import bl.enums.OrderType;
import dl.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OrderSearchScreen extends JFrame {

    private int width = 600;
    private int hight = 250;

    private Customer customer;
    private FilmProcessingOrder filmProcessingOrder = null;

    OtherOrdersInventory otherOrdersInventory = new OtherOrdersInventoryImpl();
    ProcessOtherOrders processOtherOrders = new ProcessOtherOrders();
    ProcessFilmDevelopmentOrder rocessFilmDevelopmentOrder = new  ProcessFilmDevelopmentOrder ();
    CustomerInventory customerInventory = new CustomerInventoryImpl();
    FilmProceccingOrderInventory filmProceccingOrderInventory = new FilmProceccingOrderInventoryImpl();




    String fileName = "./logos/KodakLogo.jpg";

    JLabel orderNumLbl = new JLabel("Order Number");
    JLabel orderTypeLbl = new JLabel("Order Type");
    JLabel orderErrorLbl = new JLabel("Order Does not exist, Please try again");

    JTextField orderNumFld = new JTextField();
    String[] orderTypeList = { "Please Select", "Film Processing", "Photo Gift", "Engraving"};
    JComboBox jobTypeCombo = new JComboBox(orderTypeList);

    JButton searchBtn = new JButton("Find Order");
    JButton cancelBtn = new JButton("Cancel");

    public void setupScreen(){

        setTitle("Order Search");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        BufferedImage KodakLogo = null;
        try {
            KodakLogo = ImageIO.read(new File(fileName));
            Image dimg = KodakLogo.getScaledInstance(500, 90, Image.SCALE_SMOOTH);
            ImageIcon LogoIcon = new ImageIcon(dimg);

            JLabel logoLabel = new JLabel(LogoIcon);
            getContentPane().add(logoLabel);
            logoLabel.setBounds(100,10,400,110);

        } catch (IOException e) {
            e.printStackTrace();
        }


        setAlwaysOnTop(true);
        getContentPane().setBackground(new Color(255, 255, 242));
        this.setLayout(null);
        Dimension d = new Dimension(width, hight);
        this.setSize(width, hight);


        getContentPane().add(orderNumLbl);
        orderNumLbl.setBounds(30, 120, 150, 25);

        getContentPane().add(orderNumFld);
        orderNumFld.setBounds(120, 120, 150, 25);

        getContentPane().add(orderTypeLbl);
        orderTypeLbl.setBounds(320, 120, 150, 25);

        getContentPane().add(jobTypeCombo);
        jobTypeCombo.setBounds(395, 120, 180, 25);

        getContentPane().add(searchBtn);
        searchBtn.setBounds(20, 170, 150, 25);

        getContentPane().add(cancelBtn);
        cancelBtn.setBounds(350, 170, 150, 25);

        ButtonListener buttonListener = new ButtonListener();
        searchBtn.addActionListener(buttonListener);
        cancelBtn.addActionListener(buttonListener);

    }

/*
    private OtherOrderTypes findOtherOrder(long orderID ,OrderType orderType){

        OtherOrderTypes otherOrder = null;

        if(isFormValid()) {


            otherOrder = otherOrdersInventory.retrieveOtherOrder(orderID, orderType.toString());

            if (otherOrder.getJobNumber() == 0 || otherOrder.getEmail() == null){

                orderErrorLbl.setVisible(true);
                orderNumFld.setBorder(BorderFactory.createLineBorder(Color.RED));
                jobTypeCombo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                JOptionPane.showMessageDialog(this, " Order Number : " + orderID +" of type" +  orderType.toString() + " does not exist" +
                        ". Please try again");
               }
            }else {
                orderErrorLbl.setVisible(false);
                orderNumFld.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                jobTypeCombo.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                if (orderType.toString().equalsIgnoreCase("Engraving")){
                    UpdateEngravingScreen updateEngravingScreen = new UpdateEngravingScreen(otherOrder, customer);
                    updateEngravingScreen.setupScreen();
                    updateEngravingScreen.setVisible(true);

                } else if (orderType.toString().equalsIgnoreCase("PhotoGiit")){
                    UpdatePhotoGiftScreen updatePhotoGiftScreen = new UpdatePhotoGiftScreen(otherOrder, customer);
                    updatePhotoGiftScreen.setupScreen();
                    updatePhotoGiftScreen.setVisible(true);

                }

                setVisible(false);
            }


        return otherOrder;
    }

*/
    private boolean isFormValid(){
        boolean isvalid =true;
        String orderNumberStr = orderNumFld.getText();
        String orderType = jobTypeCombo.getSelectedItem().toString();

        if (orderNumberStr == null ||"".equalsIgnoreCase(orderNumberStr.trim())) {
            orderErrorLbl.setVisible(true);
            isvalid =false;
            orderNumFld.setBorder(BorderFactory.createLineBorder(Color.RED));
        }

        if(orderType.equalsIgnoreCase("Please Select")){
            isvalid =false;
            jobTypeCombo.setBorder(BorderFactory.createLineBorder(Color.RED));
        }

        if(isvalid){
            orderErrorLbl.setVisible(false);
            orderNumFld.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            jobTypeCombo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }

        return isvalid;

    }



    private FilmProcessingOrder findFilmProcessingOrder(long orderId){

        if(isFormValid()) {
            if (filmProceccingOrderInventory.filmProcessingOrderExists(orderId)){
                //filmProcessingOrder = filmProceccingOrderInventory.retrieveProcessingOrder(orderId);
                filmProcessingOrder = rocessFilmDevelopmentOrder.getFilmProcessingOrder(orderId);
            }

            return filmProcessingOrder;
        }

        return filmProcessingOrder;

    }

    private Customer getCustomerByEmail(String customerEmail) {

        customer = customerInventory.findCustomer(customerEmail);

        return customer;
    }

    public void selectOrderTypeError(){
        JOptionPane.showMessageDialog(this, "please select your order type");
    }

    private void findOrder(long orderId, OrderType orderType){

        if(orderType.equals(OrderType.FilmProcessing)){
            if(rocessFilmDevelopmentOrder.doesFilmProcessingOrderExist(orderId)) {
                FilmProcessingOrder filmProcessingOrder = findFilmProcessingOrder(orderId);
                customer = getCustomerByEmail (filmProcessingOrder.getCustomerEmail()) ;
                customer.displayCustomerDetails();
                UpdateFilmProcessingOrderScreen updateFilmProcessingOrderScreen = new UpdateFilmProcessingOrderScreen(customer, filmProcessingOrder, this);
                updateFilmProcessingOrderScreen.setupScreen();
                updateFilmProcessingOrderScreen.setVisible(true);
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Film Processing orderNumber " + orderId + " does exist, Please try again");
            }

      } else if(orderType.equals(OrderType.Engraving)){

            if(processOtherOrders.doesEngravingOrderExist(orderId)) {
                OtherOrderTypes engravingOrder = processOtherOrders.getEhgravingOrder(orderId);
                customer = getCustomerByEmail (engravingOrder.getEmail()) ;
                customer.displayCustomerDetails();
                UpdateEngravingScreen updateEngravingScreen = new UpdateEngravingScreen(engravingOrder, customer);
                updateEngravingScreen.setupScreen();
                updateEngravingScreen.setOrderSearchScreen(this);
                updateEngravingScreen.setVisible(true);
                setVisible(false);

            } else {
                JOptionPane.showMessageDialog(this, "Engraving orderNumber " + orderId + " does exist, Please try again");
            }


      } else if(orderType.equals(OrderType.PhotoGift)){

            if(processOtherOrders.doesPhotoGiftOrderExist(orderId)) {
                OtherOrderTypes photoGiftOrder = processOtherOrders.getPhotoGiftOrder(orderId);
                customer = getCustomerByEmail (photoGiftOrder.getEmail()) ;
                UpdatePhotoGiftScreen updatePhotoGiftScreen = new UpdatePhotoGiftScreen(photoGiftOrder, customer);
                updatePhotoGiftScreen.setupScreen();
                updatePhotoGiftScreen.setVisible(true);
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Photo Gift orderNumber " + orderId + " does exist, Please try again");
            }

     }





    }


    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Find Order")) {
            findOrder(Long.parseLong(orderNumFld.getText()),(OrderType)jobTypeCombo.getSelectedItem());
        } else if (e.getActionCommand().equals("Cancel")) {
            setVisible(false);
        }
    }

    public static void main(String[] args){
        OrderSearchScreen orderSearchScreen = new  OrderSearchScreen();
        orderSearchScreen.setupScreen();
        orderSearchScreen.setVisible(true);
    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Find Order")) {
                String orderTypeStr =  jobTypeCombo.getSelectedItem().toString();
                OrderType ordetType =null;

                if(orderTypeStr.equalsIgnoreCase("Film Processing")){
                    ordetType = OrderType.FilmProcessing;
                    findOrder(Long.parseLong(orderNumFld.getText()),ordetType);
                }else if (orderTypeStr.equalsIgnoreCase("Photo Gift")){
                    ordetType = OrderType.PhotoGift;
                    findOrder(Long.parseLong(orderNumFld.getText()),ordetType);
                }else if (orderTypeStr.equalsIgnoreCase("Engraving")){
                    ordetType = OrderType.Engraving;
                    findOrder(Long.parseLong(orderNumFld.getText()),ordetType);
                } else{
                    selectOrderTypeError();
                }


            } else if (e.getActionCommand().equals("Cancel")) {
                setVisible(false);
            }
        }

    }


}
