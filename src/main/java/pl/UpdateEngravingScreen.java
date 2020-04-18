package pl;

import bl.ProcessOtherOrders;
import bl.beens.Customer;
import bl.beens.OtherOrderTypes;
import bl.enums.OrderType;
import dl.CustomerInventory;
import dl.CustomerInventoryImpl;
import dl.OtherOrdersInventory;
import dl.OtherOrdersInventoryImpl;
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
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class UpdateEngravingScreen extends  JDialog{


    private int width = 800;
    private int hight = 930;


    private String customerEmail;
    private String customerName;
    private String customerPhoneNumber;

    private String customerInstruction;
    private String adminInstruction;
    private String joType;

    private OtherOrderTypes engravingOrder;
    private Customer customer;
    private long customerId;

    private long jobNumber;
    private Date collectionDate;


    public UpdateEngravingScreen(OtherOrderTypes engravingOrderIn,Customer customeIn){
        customer = customeIn;
        engravingOrder = engravingOrderIn;
    }

    ProcessOtherOrders processOtherOrders = new ProcessOtherOrders();

    DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");

    double totalPrice =0;
    double deposit = 0;
    double balance = 0;
    double  vat = 0;
    double grandTotal =0;
    long orderId=0;

    OrderSearchScreen orderSearchScreen;

    JLabel custFillLbl = new JLabel("Customer Fill:");
    JLabel adminFillLbl = new JLabel("Admin Fill:");
    JLabel orderNumberLbl = new JLabel("Order Number:");

    JLabel closedLbl = new JLabel("<html>This order has been<br/>closed</html>", SwingConstants.CENTER);
    JLabel generalErrorLbl = new JLabel("<html>There was an error in provided order details</html>", SwingConstants.CENTER);

    JLabel nameLbl = new JLabel("NAME:");
    JLabel emailLbl = new JLabel("Email:");
    JLabel telLbl = new JLabel("Mobile No:");
    JLabel jobTypeLbl = new JLabel("Job Type:");
    String[] jobTypeList = { "Please Select", "Engraving", "Photo Mug", "Film Dev Only", "Film Printing", "Film to CD"};
    JComboBox jobTypeCombo = new JComboBox(jobTypeList);


    JTextField nameFld = new JTextField();
    JTextField emailFld = new JTextField();
    JTextField telFld = new JTextField();
    JTextField orderNumberFld = new JTextField();


    JLabel custInstructionLbl = new JLabel("Special Instruction:");
    JTextArea custInstructionArea = new JTextArea();


    JLabel adminInstructionLbl = new JLabel("Special Instruction:");
    JTextArea adminInstructionArea = new JTextArea();

    JLabel dateLbl = new JLabel(" Order Date:");
    JLabel dateValueLbl = new JLabel(dateFormat.format(new Date()));


    JLabel totalLbl = new JLabel("Total Price :   £");
    JLabel vatLbl = new JLabel("VAT 17.5% :   £");
    JLabel depositeLbl = new JLabel("Deposit :        £");
    JLabel toPayLbl = new JLabel("Left to Pay :  £");

    JTextField totalFld = new JTextField();
    JTextField vatFld = new JTextField();
    JTextField depositFld = new JTextField();
    JTextField toPayFld = new JTextField();

    JLabel collectionDateLbl = new JLabel("Collection Date:");

    JButton cancelBtn =new JButton("Cancel");
    JButton printBtn =new JButton("Update & Print");
    JButton closeBtn = new JButton("Complete & Close");

    JDatePickerImpl collectionDatePicker = null;

    //CustomerInventory customerInventory = new CustomerInventoryImpl();



    String fileName = "./logos/KodakLogo.jpg";

    public void setupScreen() {
        setTitle(" Update Engraving oosers");
        setResizable(false);

        getContentPane().add(closedLbl);

        closedLbl.setFont(new Font("Serif", Font.BOLD, 80));
        closedLbl.setBounds(40,350,710,200);
        closedLbl.setForeground(Color.red);
        closedLbl.setBorder(BorderFactory.createLineBorder(Color.red));
        closedLbl.setVisible(false);


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


        getContentPane().add(orderNumberLbl);
        orderNumberLbl.setBounds(20,160,100,25);

        getContentPane().add(orderNumberFld);
        orderNumberFld.setBounds(120,160,100,25);
        orderNumberFld.setEditable(false);
        orderNumberFld.setForeground(Color.black);
        orderNumberFld.setBackground(Color.white);


        getContentPane().add(dateLbl);
        dateLbl.setBounds(300,160,100,25);

        getContentPane().add(dateValueLbl);
        dateValueLbl.setOpaque(false);
        dateValueLbl.setBounds(400,160,100,25);

        getContentPane().add(emailLbl);
        emailLbl.setOpaque(false);
        emailLbl.setBounds(20,200,100,25);

        getContentPane().add(emailFld);
        emailFld.setBounds(120,200,220,25);


        getContentPane().add(closeBtn);
        closeBtn.setBounds(550, 200, 150, 25);


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
        collectionDatePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());

        getContentPane().add(collectionDatePicker);
        collectionDatePicker.setOpaque(false);
        collectionDatePicker.setBounds(120,540,200,25);

        getContentPane().add(jobTypeLbl);
        jobTypeLbl.setBounds(20,600,120,25);


        getContentPane().add(jobTypeCombo);
        jobTypeCombo.setOpaque(false);
        jobTypeCombo.setBounds(120,600,200,25);


        getContentPane().add(totalLbl);
        totalLbl.setOpaque(false);
        totalLbl.setBounds(500,540,100,25);

        getContentPane().add(totalFld);
        totalFld.setBounds(600,540,120,25);

        getContentPane().add(vatLbl);
        vatLbl.setBounds(500, 570, 100, 25);
        vatLbl.setOpaque(false);

        getContentPane().add(vatFld);
        vatFld.setBounds(600, 570, 120, 25);
        vatFld.setEditable(false);

        vatFld.setForeground(Color.black);
        vatFld.setBackground(Color.white);

        getContentPane().add(depositeLbl);
        depositeLbl.setOpaque(false);
        depositeLbl.setBounds(500,600,100,25);

        getContentPane().add(depositFld);
        depositFld.setBounds(600,600,120,25);

        getContentPane().add(toPayLbl);
        toPayLbl.setOpaque(false);
        toPayLbl.setBounds(500,630,100,25);

        getContentPane().add(toPayFld);
        toPayFld.setEditable(false);
        toPayFld.setForeground(Color.black);
        toPayFld.setBackground(Color.white);
        toPayFld.setBounds(600,630,120,25);


        getContentPane().add(adminInstructionLbl);
        adminInstructionLbl.setOpaque(false);
        adminInstructionLbl.setBounds(20,660,120,25);

        getContentPane().add(adminInstructionArea);
        adminInstructionArea.setBorder( BorderFactory.createLineBorder(Color.BLACK));
        adminInstructionArea.setBounds(60,690,660,100);

        getContentPane().add(printBtn);
        printBtn.setBounds(50, 810, 150, 25);

        getContentPane().add(closeBtn);
        closeBtn.setBounds(300, 810, 150, 25);

        getContentPane().add(cancelBtn);
        cancelBtn.setBounds(600, 810, 150, 25);


        ButtonListener buttonListener = new ButtonListener();
        printBtn.addActionListener(buttonListener);
        cancelBtn.addActionListener(buttonListener);
        closeBtn.addActionListener(buttonListener);

        addDepositFieldsListeners();
        addTotalPriceFieldsListeners();

        getContentPane().add(generalErrorLbl);
        generalErrorLbl.setBounds(180, 850, 400, 35);
        generalErrorLbl.setForeground(Color.red);
        generalErrorLbl.setFont(new Font("Serif", Font.BOLD, 18));
        generalErrorLbl.setVisible(false);
        populateScreen();

    }

    public void populateScreen(){

        if(customer != null) {

            nameFld.setText(customer.getName());
            emailFld.setText(customer.getEmail());
            telFld.setText(customer.getMobileNum());
            customerId = customer.getCustomerId();

        }

        if(engravingOrder != null) {

            jobNumber = engravingOrder.getJobNumber();
            orderNumberFld.setText(String.valueOf(engravingOrder.getJobNumber()));

            custInstructionArea.setText(engravingOrder.getCustomerInstruction());
            adminInstructionArea.setText(engravingOrder.getAdminInstruction());

            collectionDate = engravingOrder.getCollectionDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(collectionDate);
            int day =calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year =calendar.get(Calendar.YEAR);

            jobTypeCombo.setSelectedItem(engravingOrder.getJobType());

            collectionDatePicker.getModel().setDay(day);
            collectionDatePicker.getModel().setMonth(month);
            collectionDatePicker.getModel().setYear(year);
            collectionDatePicker.getModel().setSelected(true);

            deposit = engravingOrder.getDeposit();
            String depositStr = String.valueOf(deposit);

            totalPrice = engravingOrder.getTotalPrice();
            String totalStr = String.valueOf(totalPrice);

            vat = Math.floor(engravingOrder.getTotalPrice() * 17.5) / 100;
            String vatStr = String.valueOf(vat);

            balance = totalPrice + vat - deposit;
            String toPayStr = String.valueOf(Math.floor(balance * 100) / 100);

            vatFld.setText(vatStr);
            totalFld.setText(totalStr);
            depositFld.setText(depositStr);
            toPayFld.setText(toPayStr);

            if(engravingOrder.isClosed()){
                disableFields();
                closedLbl.setVisible(true);
            }
        }

    }

    private void closeOrder(){
        int answer = JOptionPane.showConfirmDialog(this ,"Are you sure you would like to close Engraving order number " + jobNumber +"?");

        System.out.println(answer);

        if(answer == 0) {
            System.out.println("being Closed");
            processOtherOrders.closeOrder(jobNumber);
            setVisible(false);
        }else if(answer == 2) {
            System.out.println("Closing canceled");
            setVisible(false);
        }else {
            System.out.println("Not being Closed");
        }
    }

    private void disableFields(){
        nameFld.setEnabled(false);
        emailFld.setEnabled(false);
        telFld.setEnabled(false);
        orderNumberFld.setEnabled(false);
        custInstructionArea.setEnabled(false);
        adminInstructionArea.setEnabled(false);

        dateValueLbl.setEnabled(false);

         totalFld.setEnabled(false);
         vatFld.setEnabled(false);
         depositFld.setEnabled(false);
         toPayFld.setEnabled(false);

        closeBtn.setEnabled(false);
        printBtn.setEnabled(false);

        collectionDatePicker.setEnabled(false);
    }

    private long saveEngraingOrder(){
        generalErrorLbl.setVisible(false);
        long orderNumber = 0;
        if(isFormDataValid()){
            String customerInstruction = custInstructionArea.getText();
            String aminInstruction = adminInstructionArea.getText();
            String jobType = jobTypeCombo.getSelectedItem().toString();
            String customerName =  nameFld.getText();
            String customerEmail = emailFld.getText();
            String telephone = telFld.getText();

            Date collectionDate  =  (Date) collectionDatePicker.getModel().getValue();

            OtherOrderTypes engravingOrder = new OtherOrderTypes(
                    jobNumber,
                    customerEmail,
                    OrderType.Engraving,
                    customerInstruction,
                    aminInstruction,
                    collectionDate,
                    totalPrice,
                    deposit,
                    balance,
                    jobType
            );

            Customer customer = new Customer(customerName,  telephone, customerEmail,customerId) ;

            processOtherOrders.saveOrder(engravingOrder, customer);


            JOptionPane.showMessageDialog(this, "Your orderId is: " + orderNumber);
            this.setVisible(false);
        } else {
            generalErrorLbl.setVisible(true);
        }

        return orderNumber;
    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Update & Print")) {
                saveEngraingOrder();
            } else if (e.getActionCommand().equals("Complete & Close")) {
                closeOrder();

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

                        vat = totalPrice * 0.175;
                        vat = Math.floor(vat * 100) / 100;
                        grandTotal = totalPrice + vat;
                        if(deposit < grandTotal){
                            depositFld.setBorder(BorderFactory.createLineBorder(Color.black));
                        }

                        balance = grandTotal  - deposit;

                        balance = Math.floor(balance * 100) / 100;

                        vatFld.setText(String.valueOf(vat));
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
                    if("".equalsIgnoreCase(depositFld.getText().trim())){
                        deposit = 0;
                    }
                    if(isTotalFieldsValid()){
                        vat = Math.floor(vat * 100) / 100;
                        balance = totalPrice  + vat - deposit;
                        balance = Math.floor(balance * 100) / 100;
                        if(deposit > totalPrice * 1.175){
                            System.err.println("depost fieldd listener failed" );
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
                vat = totalPrice * 0.175;
                grandTotal =
                        vat = Math.floor(vat * 100) / 100;
                grandTotal = totalPrice + vat;
                System.out.println("grandTotal =" + grandTotal);
                System.out.println("deposit =" + deposit);
                if(deposit > grandTotal){
                    depositFld.setBorder(BorderFactory.createLineBorder(Color.red));
                    System.err.println("totalfieldvalid failed" );
                    isValid = false;
                } else {
                    depositFld.setBorder(BorderFactory.createLineBorder(Color.black));
                }
            } catch (NumberFormatException nfe) {
                isValid = false;

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

        if(collectionDatePicker.getModel().getValue() == null){
            collectionDatePicker.setBorder(BorderFactory.createLineBorder(Color.red));
            isValid = false;
        }else{
            Date collectionDate = (Date) collectionDatePicker.getModel().getValue();
            Date today = new Date();
            if(collectionDate.before(today)){
                collectionDatePicker.setBorder(BorderFactory.createLineBorder(Color.red));
                isValid = false;
            } else {
                collectionDatePicker.setBorder(BorderFactory.createLineBorder(Color.black));

            }

        }


        return isValid;
    }

    public OrderSearchScreen getOrderSearchScreen() {
        return orderSearchScreen;
    }

    public void setOrderSearchScreen(OrderSearchScreen orderSearchScreen) {
        this.orderSearchScreen = orderSearchScreen;
    }


}
