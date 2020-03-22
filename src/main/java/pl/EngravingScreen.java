package pl;


import bl.beens.Customer;
import bl.beens.Order;
import bl.enums.OrderType;
import dl.CustomerInventory;
import dl.CustomerInventoryImpl;
import dl.OrderInventory;
import dl.OrderInventoryImpl;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class EngravingScreen extends JFrame {

    private int width = 800;
    private int hight = 930;


    JLabel custFillLbl = new JLabel("Customer Fill:");
    JLabel adminFillLbl = new JLabel("Admin Fill:");
    JLabel jobNoLbl = new JLabel("Job No:");
    JLabel jobNoValueLbl = new JLabel("...");

    JLabel nameLbl = new JLabel("NAME:");
    JLabel emailLbl = new JLabel("Email:");
    JLabel telLbl = new JLabel("Mobile No:");
    JLabel jobTypeLbl = new JLabel("Job Type:");
    String[] jobTypeList = { "Please Select", "Engraving", "Photo Mug", "Film Dev Only", "Film Printing", "Film to CD"};
    JComboBox jobTypeCombo = new JComboBox(jobTypeList);


    JTextField nameFld = new JTextField();
    JTextField emailFld = new JTextField();
    JTextField telFld = new JTextField();


    JLabel custInstructionLbl = new JLabel("Special Instruction:");
    JTextArea custInstructionArea = new JTextArea();


    JLabel adminInstructionLbl = new JLabel("Special Instruction:");
    JTextArea adminInstructionArea = new JTextArea();

    JLabel totaLbl = new JLabel("Total Price : £");
    JLabel depositeLbl = new JLabel("Deposit :      £");
    JLabel toPayLbl = new JLabel("Left to Pay : £");

    JTextField totalFld = new JTextField();
    JTextField depositFld = new JTextField();
    JTextField toPayFld = new JTextField();

    JLabel collectionDateLbl = new JLabel("Collection Date:");

    JButton cancelBtn =new JButton("Cancel");
    JButton printBtn =new JButton("Print");
    JButton printLblBtn =new JButton("Print Label");

    String fileName = "C:\\Users\\Behrooz\\Mainworkspace\\CustomerPhotoKiosk\\logos\\KodakLogo.jpg";

    JDatePickerImpl datePicker = null;

    OrderInventory orderInventory = new OrderInventoryImpl();
    CustomerInventory customerInventory = new CustomerInventoryImpl();

    public void setupScreen() {
        setTitle("Engraving");
        setResizable(false);
        getContentPane().setBackground(new Color(255, 255, 242));
        this.setLayout(null);
        Dimension d = new Dimension(width, hight);
        this.setSize(width, hight);

        /*************************************************************** Adding Kodak logo to Panel **********************************************************************************/


        BufferedImage KodakLogo = null;
        try {
            KodakLogo = ImageIO.read(new File(fileName));
            Image dimg = KodakLogo.getScaledInstance(500, 90, Image.SCALE_SMOOTH);
            ImageIcon LogoIcon = new ImageIcon(dimg);

            JLabel logoLabel = new JLabel(LogoIcon);
            getContentPane().add(logoLabel);
            logoLabel.setBounds(60,10,700,110);

        } catch (IOException e) {
            e.printStackTrace();
        }







        getContentPane().add(custFillLbl);
        custFillLbl.setOpaque(false);
        //Font labelFont = custFillLbl.getFont();

        custFillLbl.setFont(new Font("Serif", Font.BOLD, 18));
        custFillLbl.setBounds(20,120,200,25);


        getContentPane().add(jobNoLbl);
        jobNoLbl.setOpaque(false);
        jobNoLbl.setBounds(20,160,100,25);

        getContentPane().add(jobNoValueLbl);
        jobNoValueLbl.setOpaque(false);
        jobNoValueLbl.setBounds(150,160,100,25);

        getContentPane().add(nameLbl);
        nameLbl.setOpaque(false);
        nameLbl.setBounds(20,200,100,25);

        getContentPane().add(nameFld);
        nameFld.setBounds(120,200,150,25);

        getContentPane().add(telLbl);
        telLbl.setOpaque(false);
        telLbl.setBounds(400,200,100,25);

        getContentPane().add(telFld);
        telFld.setBounds(500,200,150,25);

        getContentPane().add(emailLbl);
        emailLbl.setOpaque(false);
        emailLbl.setBounds(20,240,100,25);

        getContentPane().add(emailFld);
        emailFld.setBounds(120,240,150,25);



        getContentPane().add(custInstructionLbl);
        custInstructionLbl.setOpaque(false);
        custInstructionLbl.setBounds(20,290,120,25);

        getContentPane().add(custInstructionArea);
        custInstructionArea.setBorder( BorderFactory.createLineBorder(Color.BLACK));
        custInstructionArea.setBounds(60,330,660,100);

        getContentPane().add(printLblBtn);
        printLblBtn.setBounds(620,440,100,25);


        getContentPane().add(adminFillLbl);
        adminFillLbl.setFont(new Font("Serif", Font.BOLD, 18));
        adminFillLbl.setOpaque(false);
        adminFillLbl.setBounds(20,460,100,25);


        getContentPane().add(collectionDateLbl);
        collectionDateLbl.setOpaque(false);
        collectionDateLbl.setBounds(20,500,100,25);

        Properties p = new Properties();
        p.put("text.today", "today");
        p.put("text.month", "month");
        p.put("text.year", "year");

        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());

        getContentPane().add(datePicker);
        datePicker.setOpaque(false);
        datePicker.setBounds(120,500,200,25);

        getContentPane().add(jobTypeLbl);
        jobTypeLbl.setBounds(20,530,120,25);


        getContentPane().add(jobTypeCombo);
        jobTypeCombo.setOpaque(false);
        jobTypeCombo.setBounds(120,530,200,25);


        getContentPane().add(totaLbl);
        totaLbl.setOpaque(false);
        totaLbl.setBounds(500,500,100,25);

        getContentPane().add(totalFld);
        totalFld.setBounds(600,500,120,25);

        getContentPane().add(depositeLbl);
        depositeLbl.setOpaque(false);
        depositeLbl.setBounds(500,530,100,25);

        getContentPane().add(depositFld);
        depositFld.setBounds(600,530,120,25);

        getContentPane().add(toPayLbl);
        toPayLbl.setOpaque(false);
        toPayLbl.setBounds(500,560,100,25);

        getContentPane().add(toPayFld);
        toPayFld.setBounds(600,560,120,25);



        getContentPane().add(adminInstructionLbl);
        adminInstructionLbl.setOpaque(false);
        adminInstructionLbl.setBounds(20,600,120,25);

        getContentPane().add(adminInstructionArea);
        adminInstructionArea.setBorder( BorderFactory.createLineBorder(Color.BLACK));
        adminInstructionArea.setBounds(60,650,660,100);

        getContentPane().add(cancelBtn);
        cancelBtn.setBounds(60,820,100,40);

        getContentPane().add(printBtn);
        printBtn.setBounds(620,820,100,40);

       ButtonListener buttonListener = new ButtonListener();
        printBtn.addActionListener(buttonListener);
        cancelBtn.addActionListener(buttonListener);

    }

    private class ButtonListener implements ActionListener {
       double totalPrice = 0;
       double deposit = 0;
       double balance = 0;

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Submit")) {
                saveOrder();
            } else if (e.getActionCommand().equals("Search Details")) {
                findCustomer();
            } else if (e.getActionCommand().equals("Cancel")) {
                setVisible(false);
            }
        }

        private void findCustomer(){

            CustomerInventory customerInventoryImpl = new CustomerInventoryImpl();
            String customerEmail = emailFld.getText();
            if(customerEmail != null && !"".equalsIgnoreCase(customerEmail)){
                Customer customer = customerInventoryImpl.findCustomer(customerEmail);

                if(customer.getMobileNum() !=null &&  !"".equalsIgnoreCase(customer.getMobileNum())){
                    telFld.setText(customer.getMobileNum());
                }

                if(customer.getName() !=null &&  !"".equalsIgnoreCase(customer.getName())){
                    nameFld.setText(customer.getName());
                }
            }

        }

        private boolean isOrderValid( Order order, Customer customer){
            boolean isValid = true;
            if (totalFld.getText() == null || "".equalsIgnoreCase(totalFld.getText().trim())){
                isValid = false;
            }else{
                try{
                    totalPrice =  Integer.parseInt(totalFld.getText());
                    order.setTotalPrice(totalPrice);

                } catch(NumberFormatException nfe){
                    isValid = false;
                }

            }

            if (depositFld.getText() != null && !"".equalsIgnoreCase(depositFld.getText().trim())){

                try{
                    deposit =  Integer.parseInt(totalFld.getText());
                    order.setDeposit(deposit);

                } catch(NumberFormatException nfe){
                    isValid = false;
                }

            }

            if (toPayFld.getText() == null || "".equalsIgnoreCase(toPayFld.getText().trim())){
                isValid = false;
            }else{
                try{
                    balance =  Integer.parseInt(toPayFld.getText());
                    order.setBalance(balance);

                } catch(NumberFormatException nfe){
                    isValid = false;
                }

            }

            return isValid;
        }

        private void saveOrder(){
            Order order =new Order();

            String customerInstruction = custInstructionArea.getText();
            String aminInstruction = adminInstructionArea.getText();

            String jobType = jobTypeCombo.getSelectedItem().toString();
            String customerName =  nameFld.getName();
            String customerEmail = emailFld.getText();
            String telephone = telFld.getToolTipText();

            Date collectionDate  =  (Date) datePicker.getModel().getValue();

            Order enngravingOrder = new Order(
                                                    customerEmail,
                                                    OrderType.Engraving,
                                                    customerInstruction,
                                                    aminInstruction,
                                                    collectionDate,
                                                    totalPrice,
                                                    deposit,
                                                    balance
                                            );


            Customer customer = new Customer(customerName,  telephone, customerEmail) ;

            if(isValid()){
                if(isOrderValid(enngravingOrder, customer)){
                    orderInventory.saveOrder(enngravingOrder);
                    customerInventory.saveCustomer(customer);
                }
            }
        }
    }

}
