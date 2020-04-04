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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class EngravingScreen extends JFrame {

    private long customerId;
    private int width = 800;
    private int hight = 930;


    private String customerEmail;
    private String customerName;
    private String customerPhoneNumber;
    private OrderType orderType;
    private long jobNumber;
    private String customerInstruction;
    private String adminInstruction;
    String joType;

    private Date collectionDate;

    OrderInventory orderInventory = new OrderInventoryImpl();

    DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");

    double totalPrice =0;
    double deposit = 0;
    double balance = 0;


    JLabel newCustomerLbl = new JLabel("This is a new customer");
    JLabel custFillLbl = new JLabel("Customer Fill:");
    JLabel adminFillLbl = new JLabel("Admin Fill:");
    JLabel dateLbl = new JLabel("Date:");
    JLabel dateValueLbl = new JLabel(dateFormat.format(new Date()));

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
    //JButton printLblBtn =new JButton("Print Label");
    JButton searchBtn = new JButton("Search Details");

    JDatePickerImpl datePicker = null;

    CustomerInventory customerInventory = new CustomerInventoryImpl();

    String fileName = "./logos/KodakLogo.jpg";

    public void setupScreen() {
        setTitle("engraving");
        setResizable(false);

        BufferedImage KodakLogo = null;
        try {
            KodakLogo = ImageIO.read(new File(fileName));
            Image dimg = KodakLogo.getScaledInstance(500, 90, Image.SCALE_SMOOTH);
            ImageIcon LogoIcon = new ImageIcon(dimg);

            JLabel logoLabel = new JLabel(LogoIcon);
            getContentPane().add(logoLabel);
            logoLabel.setBounds(180,10,400,110);

        } catch (IOException e) {
            e.printStackTrace();
        }


        setAlwaysOnTop(true);
        getContentPane().setBackground(new Color(255, 255, 242));
        this.setLayout(null);
        Dimension d = new Dimension(width, hight);
        this.setSize(width, hight);

        getContentPane().add(custFillLbl);
        custFillLbl.setOpaque(false);
        //Font labelFont = custFillLbl.getFont();

        custFillLbl.setFont(new Font("Serif", Font.BOLD, 18));
        custFillLbl.setBounds(20,120,200,25);


        getContentPane().add(dateLbl);
        dateLbl.setOpaque(false);
        dateLbl.setBounds(20,160,100,25);

        getContentPane().add(dateValueLbl);
        dateValueLbl.setOpaque(false);
        dateValueLbl.setBounds(150,160,100,25);


        getContentPane().add(emailLbl);
        emailLbl.setOpaque(false);
        emailLbl.setBounds(20,200,100,25);

        getContentPane().add(emailFld);
        emailFld.setBounds(120,200,220,25);

        getContentPane().add(newCustomerLbl);
        newCustomerLbl.setBounds(350, 200, 220, 25);
        newCustomerLbl.setForeground(Color.blue);
        newCustomerLbl.setVisible(false);



        getContentPane().add(searchBtn);
        searchBtn.setBounds(550, 200, 150, 25);


        getContentPane().add(nameLbl);
        nameLbl.setOpaque(false);
        nameLbl.setBounds(20,240,100,25);

        getContentPane().add(nameFld);
        nameFld.setBounds(120,240,220,25);

        getContentPane().add(telLbl);
        telLbl.setOpaque(false);
        telLbl.setBounds(20,280,100,25);

        getContentPane().add(telFld);
        telFld.setBounds(120,280,220,25);





        getContentPane().add(custInstructionLbl);
        custInstructionLbl.setOpaque(false);
        custInstructionLbl.setBounds(20,330,120,25);

        getContentPane().add(custInstructionArea);
        custInstructionArea.setBorder( BorderFactory.createLineBorder(Color.BLACK));
        custInstructionArea.setBounds(60,370,660,100);

        //getContentPane().add(printLblBtn);
        //printLblBtn.setBounds(620,480,100,25);


        getContentPane().add(adminFillLbl);
        adminFillLbl.setFont(new Font("Serif", Font.BOLD, 18));
        adminFillLbl.setOpaque(false);
        adminFillLbl.setBounds(20,500,100,25);


        getContentPane().add(collectionDateLbl);
        collectionDateLbl.setOpaque(false);
        collectionDateLbl.setBounds(20,540,100,25);

        Properties p = new Properties();
        p.put("text.today", "today");
        p.put("text.month", "month");
        p.put("text.year", "year");

        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());

        getContentPane().add(datePicker);
        datePicker.setOpaque(false);
        datePicker.setBounds(120,540,200,25);

        getContentPane().add(jobTypeLbl);
        jobTypeLbl.setBounds(20,600,120,25);


        getContentPane().add(jobTypeCombo);
        jobTypeCombo.setOpaque(false);
        jobTypeCombo.setBounds(120,600,200,25);


        getContentPane().add(totaLbl);
        totaLbl.setOpaque(false);
        totaLbl.setBounds(500,540,100,25);

        getContentPane().add(totalFld);
        totalFld.setBounds(600,540,120,25);

        getContentPane().add(depositeLbl);
        depositeLbl.setOpaque(false);
        depositeLbl.setBounds(500,570,100,25);

        getContentPane().add(depositFld);
        depositFld.setBounds(600,570,120,25);

        getContentPane().add(toPayLbl);
        toPayLbl.setOpaque(false);
        toPayLbl.setBounds(500,600,100,25);

        getContentPane().add(toPayFld);
        toPayFld.setEditable(false);
        toPayFld.setForeground(Color.black);
        toPayFld.setBackground(Color.white);
        toPayFld.setBounds(600,600,120,25);


        getContentPane().add(adminInstructionLbl);
        adminInstructionLbl.setOpaque(false);
        adminInstructionLbl.setBounds(20,660,120,25);

        getContentPane().add(adminInstructionArea);
        adminInstructionArea.setBorder( BorderFactory.createLineBorder(Color.BLACK));
        adminInstructionArea.setBounds(60,690,660,100);



        getContentPane().add(printBtn);
        printBtn.setBounds(60,820,100,40);

        getContentPane().add(cancelBtn);
        cancelBtn.setBounds(620,820,100,40);

        ButtonListener buttonListener = new ButtonListener();
        printBtn.addActionListener(buttonListener);
        cancelBtn.addActionListener(buttonListener);
        searchBtn.addActionListener(buttonListener);

        addDepositFieldsListeners();
        addTotalPriceFieldsListeners();

    }

    private void findCustomer(){

        CustomerInventory customerInventory = new CustomerInventoryImpl();
        String customerEmail = emailFld.getText();
        if(customerEmail != null && !"".equalsIgnoreCase(customerEmail)){
            Customer customer = customerInventory.findCustomer(customerEmail);
            customer.displayCustomerDetails();
            if(customer.getMobileNum() != null &&  !"".equalsIgnoreCase(customer.getMobileNum())){
                telFld.setText(customer.getMobileNum());
                customerId = customer.getCustomerId();
            }

            if(customer.getName() !=null &&  !"".equalsIgnoreCase(customer.getName())){
                nameFld.setText(customer.getName());
                newCustomerLbl.setVisible(false);
                customerId = customer.getCustomerId();
            } else {
                newCustomerLbl.setVisible(true);
            }
        }

    }

    private long savePhotoGiftOrder(){
        long orderNumber = 0;
        if(isFormDataValid()){
            String customerInstruction = custInstructionArea.getText();
            String aminInstruction = adminInstructionArea.getText();

            String jobType = jobTypeCombo.getSelectedItem().toString();
            String customerName =  nameFld.getName();
            String customerEmail = emailFld.getText();
            String telephone = telFld.getToolTipText();

            Date collectionDate  =  (Date) datePicker.getModel().getValue();

            Order photoGiftOrder = new Order(
                    customerEmail,
                    OrderType.PhotoGift,
                    customerInstruction,
                    aminInstruction,
                    collectionDate,
                    totalPrice,
                    deposit,
                    balance
            );

            Customer customer = new Customer(customerName,  telephone, customerEmail,customerId) ;

            orderNumber = orderInventory.saveOrder(photoGiftOrder);
            customerInventory.saveCustomer(customer);
            JOptionPane.showMessageDialog(this, "Your orderId is: " + orderNumber);
            this.setVisible(false);
        }


        return orderNumber;
    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Print")) {
                savePhotoGiftOrder();
            } else if (e.getActionCommand().equals("Search Details")) {
                findCustomer();
            } else if (e.getActionCommand().equals("Cancel")) {
                setVisible(false);
            }
        }

    }

    public void addTotalPriceFieldsListeners() {

        totalFld.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                warn();
            }

            public void removeUpdate(DocumentEvent e) {
                warn();
            }

            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            public void warn() {
                if (!isTotalFieldsValid()) {
                    totalFld.setBorder(BorderFactory.createLineBorder(Color.red));
                } else {
                    totalFld.setBorder(BorderFactory.createLineBorder(Color.black));
                    if(isDepositFieldsValid()){
                        if(deposit < totalPrice){
                            depositFld.setBorder(BorderFactory.createLineBorder(Color.black));
                        }
                        balance = totalPrice - deposit;
                        balance = Math.floor(balance * 100) / 100;
                        toPayFld.setText(String.valueOf(balance));
                    }
                }
            }
        });

    }

    public void addDepositFieldsListeners() {
        depositFld.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                warn();
            }

            public void removeUpdate(DocumentEvent e) {
                warn();
            }

            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            public void warn() {
                if (!isDepositFieldsValid()) {
                    depositFld.setBorder(BorderFactory.createLineBorder(Color.red));
                }else{
                    depositFld.setBorder(BorderFactory.createLineBorder(Color.black));
                    if(isTotalFieldsValid()){
                        balance = totalPrice - deposit;
                        balance = Math.floor(balance * 100) / 100;
                        if(deposit > totalPrice){
                            depositFld.setBorder(BorderFactory.createLineBorder(Color.red));
                        } else {
                            toPayFld.setText(String.valueOf(balance));
                        }
                    }
                }

            }
        });


    }

    private boolean isTotalFieldsValid() {
        boolean isValid = true;
        if (totalFld.getText() == null || "".equalsIgnoreCase(totalFld.getText().trim())) {
            isValid = false;
        } else {
            try {
                totalPrice = Double.parseDouble(totalFld.getText());
                if(deposit > totalPrice){
                    depositFld.setBorder(BorderFactory.createLineBorder(Color.red));
                    isValid = false;
                } else {
                    depositFld.setBorder(BorderFactory.createLineBorder(Color.black));
                }

            } catch (NumberFormatException nfe) {
                isValid = false;
                //totalFld.getHighlighter().addHighlight(1,1,   Highlighter.Highlight.getPainter());
            }


        }
        return isValid;
    }

    private boolean isDepositFieldsValid() {
        boolean isValid = true;
        if (depositFld.getText() != null && !"".equalsIgnoreCase(depositFld.getText().trim())) {

            try {
                deposit = Double.parseDouble(depositFld.getText());

            } catch (NumberFormatException nfe) {
                isValid = false;
            }

        }

        return isValid;
    }

    private boolean isFormDataValid(){
        boolean isValid = true;;

        customerName = nameFld.getText();
        customerEmail = emailFld.getText();
        customerPhoneNumber = telFld.getText();

        customerInstruction = custInstructionArea.getText();
        adminInstruction = adminInstructionArea.getText();

        orderType = OrderType.PhotoGift;

        if (customerName == null || "".equalsIgnoreCase(customerName.trim())) {
            isValid = false;
            nameFld.setBorder(BorderFactory.createLineBorder(Color.red));
        } else {
            nameFld.setBorder(BorderFactory.createLineBorder(Color.black));
        }

        if (customerPhoneNumber == null || "".equalsIgnoreCase(customerPhoneNumber.trim())) {
            isValid = false;
            telFld.setBorder(BorderFactory.createLineBorder(Color.red));
        } else {
            telFld.setBorder(BorderFactory.createLineBorder(Color.black));
        }


        if ( customerEmail== null || "".equalsIgnoreCase(customerEmail.trim())) {
            emailFld.setBorder(BorderFactory.createLineBorder(Color.red));
            isValid = false;
        }  else {
            if(emailFld.getText().contains("@")){
                emailFld.setBorder(BorderFactory.createLineBorder(Color.black));
            }else {
                emailFld.setBorder(BorderFactory.createLineBorder(Color.red));
                isValid = false;
            }

        }


        if (adminInstruction == null || "".equalsIgnoreCase(adminInstruction.trim())) {
            isValid = false;
            adminInstructionArea.setBorder(BorderFactory.createLineBorder(Color.red));
        } else {
            adminInstructionArea.setBorder(BorderFactory.createLineBorder(Color.black));
        }


        if (customerInstruction == null || "".equalsIgnoreCase(customerInstruction.trim())) {
            isValid = false;
            custInstructionArea.setBorder(BorderFactory.createLineBorder(Color.red));
        } else {
            custInstructionArea.setBorder(BorderFactory.createLineBorder(Color.black));
        }


        joType = jobTypeCombo.getSelectedItem().toString();


        if ( joType== null || "Please Select".equalsIgnoreCase(joType)) {
            jobTypeCombo.setBorder(BorderFactory.createLineBorder(Color.red));
            isValid = false;
        }  else {
            jobTypeCombo
                    .setBorder(BorderFactory.createLineBorder(Color.black));
        }

        if(datePicker.getModel().getValue() == null){
            datePicker.setBorder(BorderFactory.createLineBorder(Color.red));
            isValid = false;
        }else{
            Date collectionDate = (Date) datePicker.getModel().getValue();
            Date today = new Date();
            if(collectionDate.before(today)){
                datePicker.setBorder(BorderFactory.createLineBorder(Color.red));
                isValid = false;
            } else {
                datePicker.setBorder(BorderFactory.createLineBorder(Color.black));

            }

        }


        return isValid;
    }


}
