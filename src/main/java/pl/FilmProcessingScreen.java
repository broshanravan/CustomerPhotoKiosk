package pl;

import bl.ProcessFilmDevelopmentOrder;
import bl.beens.Customer;
import bl.beens.FilmProcessingOrder;
import bl.enums.FilmType;
import dl.CustomerInventory;
import dl.CustomerInventoryImpl;
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

public class FilmProcessingScreen extends  JDialog{

    long customerId = 0;
    boolean color =false;
    private int width = 900;
    private int hight = 900;

    double totalPrice =0;
    double  vat = 0;
    double deposit = 0;
    double balance = 0;
    double grandTotal =0;

    String printSize ;
    String borderType ;
    String printTime ;
    String numberOfCopiesStr ;
    String filmSize;
    int numberOfCopies;

    DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");

    //("<html>There was an error in provided order details</html>", SwingConstants.CENTER)
    JLabel generalErrorLbl = new JLabel("<html>There was an error in provided order details</html>", SwingConstants.CENTER);

    JLabel nameLbl = new JLabel("NAME:");
    JLabel emailLbl = new JLabel("Email:");
    JLabel telLbl = new JLabel("TEL:");
    JLabel newCustomerLbl = new JLabel(" This is a New Customer!");

    JLabel dateLbl = new JLabel(" Order Date:");
    JLabel dateValueLbl = new JLabel(dateFormat.format(new Date()));

    JLabel collectionDateLbl = new JLabel("Collection Date:");
    JLabel printSizeLbl = new JLabel("Print Size:");
    JLabel filmTypeLbl = new JLabel("Film Type:");
    JLabel filmSizeLbl = new JLabel("Film size:");
    JLabel printTypeLbl = new JLabel("Print Type:");


    JLabel totalLbl = new JLabel("Total Price :     £");
    JLabel vatlLbl = new JLabel("VAT 17.5% :     £");
    JLabel depositLbl = new JLabel("Deposit :          £");
    JLabel toPayLbl = new JLabel("Balance :         £");

    JLabel borderLbl = new JLabel("Border");
    JLabel printtimeLbl = new JLabel("Print time");

    JLabel sizeLbl = new JLabel("Size:");
    JLabel copiesLbl = new JLabel("Copies:");

    JTextField otherFld = new JTextField();
    JTextField nameFld = new JTextField();
    JTextField emailFld = new JTextField();
    JTextField telFld = new JTextField();


    JTextField totalFld = new JTextField();
    JTextField vatFld = new JTextField();
    JTextField depositFld = new JTextField();
    JTextField toPayFld = new JTextField();


    JRadioButton film6x4Radio = new JRadioButton("6 x 4\" (10x15)");
    JRadioButton film5x7Radio = new JRadioButton("5 x 7\" (15x85)");
    JRadioButton film6x8Radio = new JRadioButton("6 x 8\" (15x120)");


    JRadioButton hundred10Radio = new JRadioButton("110");
    JRadioButton hundred35Radio = new JRadioButton("135");
    JRadioButton hundred20Radio = new JRadioButton("120");


    JRadioButton colorRadio = new JRadioButton("Color");
    JRadioButton bAwRadio = new JRadioButton("Black & White");


    JRadioButton withBorderRadio = new JRadioButton("With Border");
    JRadioButton noBorderRadio = new JRadioButton("No Border");
    JRadioButton glossyRadio = new JRadioButton("Glossy");

    JRadioButton oneRadio = new JRadioButton("1");
    JRadioButton twoRadio = new JRadioButton("2");
    JRadioButton threeRadio = new JRadioButton("3");
    JRadioButton fourRadio = new JRadioButton("4");
    JRadioButton otherRadio = new JRadioButton("Other");

    JRadioButton oneHRRadio = new JRadioButton("One Hour");
    JRadioButton twentyFourHr4Radio = new JRadioButton("Twenty Four Hours");

    JRadioButton mafiFourHr4Radio = new JRadioButton("MAFI");

    String[] fimlList = {"Please Select", "FUJI", "KODAK", "KONIKA", "AGFA", "BOOTS"};
    JComboBox filmTypeCombo = new JComboBox(fimlList);

    JButton searchBtn = new JButton("Search Details");
    JButton cancelBtn = new JButton("Cancel");
    JButton printBtn = new JButton("Print");

    ButtonGroup filmSizeGroup = new ButtonGroup();
    ButtonGroup colorCroup = new ButtonGroup();
    ButtonGroup printSizeGroup = new ButtonGroup();
    ButtonGroup borderCroup = new ButtonGroup();
    ButtonGroup printTimeCroup = new ButtonGroup();
    ButtonGroup copiesNumCroup = new ButtonGroup();

    ProcessFilmDevelopmentOrder processOtherOrders = new ProcessFilmDevelopmentOrder();

    CustomerInventory customerInventory = new CustomerInventoryImpl();


    String fileName = "./logos/KodakLogo.jpg";
    //ImageIcon KodakLogo = new ImageIcon("C:\\Users\\Behrooz\\Mainworkspace\\CustomerPhotoKiosk\\logos\\KodakLogo.jpg");

    JDatePickerImpl datePicker = null;

    public void setupScreen() {

        setTitle("Film Processing");
        setAlwaysOnTop(true);
        getContentPane().setBackground(new Color(255, 255, 242));
        this.setLayout(null);
        //Dimension d = new Dimension(width, hight);
        this.setSize(width, hight);
        setResizable(false);

        BufferedImage KodakLogo = null;
        try {
            KodakLogo = ImageIO.read(new File(fileName));
            Image dimg = KodakLogo.getScaledInstance(500, 90, Image.SCALE_SMOOTH);
            ImageIcon LogoIcon = new ImageIcon(dimg);

            JLabel logoLabel = new JLabel(LogoIcon);
            getContentPane().add(logoLabel);
            logoLabel.setBounds(100, 10, 700, 110);

        } catch (IOException e) {
            e.printStackTrace();
        }


        /** Adding Labels and Fields*/

        getContentPane().add(emailLbl);
        emailLbl.setBounds(20, 150, 100, 25);

        getContentPane().add(newCustomerLbl);
        newCustomerLbl.setBounds(110, 120, 220, 25);
        newCustomerLbl.setForeground(Color.blue);
        newCustomerLbl.setVisible(false);
        getContentPane().add(emailFld);
        emailFld.setBounds(110, 150, 220, 25);

        getContentPane().add(searchBtn);
        searchBtn.setBounds(370, 150, 150, 25);


        getContentPane().add(nameLbl);
        nameLbl.setBounds(20, 180, 100, 25);

        getContentPane().add(nameFld);
        nameFld.setBounds(110, 180, 220, 25);


        getContentPane().add(telLbl);
        telLbl.setBounds(20, 210, 100, 25);

        getContentPane().add(telFld);
        telFld.setBounds(110, 210, 220, 25);



        getContentPane().add(dateLbl);
        dateLbl.setBounds(550, 180, 100, 25);

        getContentPane().add(dateValueLbl);
        dateValueLbl.setBounds(670, 180, 200, 25);


        getContentPane().add(collectionDateLbl);
        collectionDateLbl.setBounds(550, 210, 100, 25);


        Properties p = new Properties();
        p.put("text.today", "today");
        p.put("text.month", "month");
        p.put("text.year", "year");
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());

        getContentPane().add(datePicker);
        datePicker.setOpaque(false);


        //getContentPane().add(CollectionDateFld);
        datePicker.setBounds(670, 210, 200, 25);


        /*
        getContentPane().add(jobNoLbl);
        jobNoLbl.setBounds(550, 210, 100, 25);

        getContentPane().add(jobNoFld);
        jobNoFld.setBounds(670, 210, 200, 25);
        */

        /** Second Section */


        /** PrintSize radio group*/
        getContentPane().add(printSizeLbl);
        printSizeLbl.setBounds(25, 280, 100, 25);

        getContentPane().add(film6x8Radio);
        film6x8Radio.setBounds(20, 330, 120, 25);
        film6x8Radio.setOpaque(false);

        getContentPane().add(film6x4Radio);
        film6x4Radio.setBounds(20, 380, 120, 25);
        film6x4Radio.setOpaque(false);

        getContentPane().add(film5x7Radio);
        film5x7Radio.setBounds(20, 430, 120, 25);
        film5x7Radio.setOpaque(false);


        printSizeGroup.add(film6x8Radio);
        film6x8Radio.setSelected(true);
        printSizeGroup.add(film6x4Radio);
        printSizeGroup.add(film5x7Radio);

        /** Border radio group*/
        getContentPane().add(borderLbl);
        borderLbl.setBounds(405, 280, 100, 25);

        getContentPane().add(withBorderRadio);
        withBorderRadio.setBounds(400, 330, 120, 25);
        withBorderRadio.setOpaque(false);

        getContentPane().add(noBorderRadio);
        noBorderRadio.setBounds(400, 380, 120, 25);
        noBorderRadio.setOpaque(false);

        getContentPane().add(glossyRadio);
        glossyRadio.setBounds(400, 430, 120, 25);
        glossyRadio.setOpaque(false);


        borderCroup.add(withBorderRadio);
        withBorderRadio.setSelected(true);
        borderCroup.add(noBorderRadio);
        borderCroup.add(glossyRadio);

        /** Print time radio group*/
        getContentPane().add(printtimeLbl);
        printtimeLbl.setBounds(655, 280, 100, 25);

        getContentPane().add(oneHRRadio);
        oneHRRadio.setBounds(650, 330, 120, 25);
        oneHRRadio.setOpaque(false);

        getContentPane().add(twentyFourHr4Radio);
        twentyFourHr4Radio.setBounds(650, 380, 140, 25);
        twentyFourHr4Radio.setOpaque(false);

        getContentPane().add(mafiFourHr4Radio);
        mafiFourHr4Radio.setBounds(650, 430, 120, 25);
        mafiFourHr4Radio.setOpaque(false);

        printTimeCroup.add(oneHRRadio);
        oneHRRadio.setSelected(true);
        printTimeCroup.add(twentyFourHr4Radio);
        printTimeCroup.add(mafiFourHr4Radio);


        /** Third Section */

        getContentPane().add(filmTypeLbl);
        filmTypeLbl.setBounds(20, 490, 100, 25);

        /** Adding Combo Box */
        getContentPane().add(filmTypeCombo);
        filmTypeCombo.setBounds(100, 490, 200, 25);
        filmTypeCombo.setOpaque(false);


        /** Film Type radio group*/
        getContentPane().add(filmSizeLbl);
        filmSizeLbl.setBounds(400, 490, 100, 25);


        getContentPane().add(hundred10Radio);
        hundred10Radio.setBounds(400, 520, 50, 25);
        hundred10Radio.setOpaque(false);

        getContentPane().add(hundred20Radio);
        hundred20Radio.setBounds(400, 550, 50, 25);
        hundred20Radio.setOpaque(false);

        getContentPane().add(hundred35Radio);
        hundred35Radio.setBounds(400, 580, 50, 25);
        hundred35Radio.setOpaque(false);


        filmSizeGroup.add(hundred10Radio);
        hundred10Radio.setSelected(true);
        filmSizeGroup.add(hundred35Radio);
        filmSizeGroup.add(hundred20Radio);


        /** Color radio group*/

        getContentPane().add(printTypeLbl);
        printTypeLbl.setBounds(650, 490, 60, 25);

        getContentPane().add(colorRadio);
        colorRadio.setBounds(650, 520, 60, 25);
        colorRadio.setOpaque(false);

        getContentPane().add(bAwRadio);
        bAwRadio.setBounds(650, 550, 150, 25);
        bAwRadio.setOpaque(false);


        colorCroup.add(colorRadio);
        colorRadio.setSelected(true);
        colorCroup.add(bAwRadio);

        /** Copies Number radio group*/
        getContentPane().add(copiesLbl);
        copiesLbl.setBounds(20, 640, 60, 25);

        getContentPane().add(oneRadio);
        oneRadio.setBounds(20, 670, 60, 25);
        oneRadio.setOpaque(false);

        getContentPane().add(twoRadio);
        twoRadio.setBounds(120, 670, 60, 25);
        twoRadio.setOpaque(false);


        getContentPane().add(threeRadio);
        threeRadio.setBounds(20, 700, 60, 25);
        threeRadio.setOpaque(false);


        getContentPane().add(fourRadio);
        fourRadio.setBounds(120, 700, 60, 25);
        fourRadio.setOpaque(false);


        getContentPane().add(otherRadio);
        otherRadio.setBounds(20, 730, 60, 25);
        otherRadio.setOpaque(false);

        getContentPane().add(otherFld);
        otherFld.setEditable(false);
        otherFld.setBounds(80, 730, 60, 25);


        copiesNumCroup.add(oneRadio);
        oneRadio.setSelected(true);
        copiesNumCroup.add(twoRadio);
        copiesNumCroup.add(threeRadio);
        copiesNumCroup.add(fourRadio);
        copiesNumCroup.add(otherRadio);


        otherRadio.addActionListener(new RadioListener());
        oneRadio.addActionListener(new RadioListener());
        twoRadio.addActionListener(new RadioListener());
        threeRadio.addActionListener(new RadioListener());
        fourRadio.addActionListener(new RadioListener());
        otherRadio.addActionListener(new RadioListener());





        getContentPane().add(totalLbl);
        totalLbl.setBounds(450, 640, 100, 25);

        getContentPane().add(totalFld);
        totalFld.setBounds(550, 640, 100, 25);

        getContentPane().add(vatlLbl);
        vatlLbl.setBounds(450, 670, 100, 25);
        vatFld.setEditable(false);

        getContentPane().add(vatFld);
        vatFld.setBounds(550, 670, 100, 25);

        vatFld.setForeground(Color.black);
        vatFld.setBackground(Color.white);

        getContentPane().add(depositLbl);
        depositLbl.setBounds(450, 700, 100, 25);

        getContentPane().add(depositFld);
        depositFld.setBounds(550, 700, 100, 25);


        getContentPane().add(toPayLbl);
        toPayLbl.setBounds(450, 730, 100, 25);

        getContentPane().add(toPayFld);
        toPayFld.setBounds(550, 730, 100, 25);
        toPayFld.setEditable(false);
        toPayFld.setForeground(Color.black);
        toPayFld.setBackground(Color.white);

        getContentPane().add(printBtn);
        printBtn.setBounds(150, 800, 100, 25);

        getContentPane().add(cancelBtn);
        cancelBtn.setBounds(650, 800, 100, 25);


        getContentPane().add(generalErrorLbl);
        generalErrorLbl.setBounds(240, 820, 400, 35);
        generalErrorLbl.setForeground(Color.red);
        generalErrorLbl.setFont(new Font("Serif", Font.BOLD, 18));
        generalErrorLbl.setVisible(false);

        ButtonListener buttonListener = new ButtonListener();
        printBtn.addActionListener(buttonListener);
        cancelBtn.addActionListener(buttonListener);
        searchBtn.addActionListener(buttonListener);


        String border = "";

        for (int i = 0; i < 245; i++) {
            border = border + "-";
        }

        JLabel line_1 = new JLabel(border);
        JLabel line_2 = new JLabel(border);
        JLabel line_3 = new JLabel(border);

        line_1.setForeground(Color.BLUE);
        line_2.setForeground(Color.blue);
        line_3.setForeground(Color.BLUE);

        getContentPane().add(line_1);
        getContentPane().add(line_2);
        getContentPane().add(line_3);


        line_1.setBounds(1, 250, 1500, 10);
        line_2.setBounds(1, 470, 1500, 10);
        line_3.setBounds(1, 620, 1000, 10);

        addTotalPriceFieldsListeners();
        addDepositFieldsListeners();
        addOtherFieldListener();


    }


    public void addOtherFieldListener(){
        otherFld.getDocument().addDocumentListener(new DocumentListener() {
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
                if (!isOtherFieldValid()) {
                    otherFld.setBorder(BorderFactory.createLineBorder(Color.red));
                } else {
                    otherFld.setBorder(BorderFactory.createLineBorder(Color.black));
                }
            }
        });

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

    private boolean isOtherFieldValid(){
        boolean isValid =true;
        if (otherRadio.isSelected()){
            String otherString =otherFld.getText();
            if (otherString ==null|| "".equalsIgnoreCase(otherString.trim())){
                isValid = false;
                otherFld.setBorder(BorderFactory.createLineBorder(Color.red));
            } else {
                try{
                    Integer.parseInt(otherString);
                    otherFld.setBorder(BorderFactory.createLineBorder(Color.black));
                } catch (NumberFormatException e){
                    isValid = false;
                    otherFld.setBorder(BorderFactory.createLineBorder(Color.red));
                }
            }

        }
        return isValid;
    }


    /*
    public void addOtherFieldListener(){
        otherFld.getDocument().addDocumentListener(new DocumentListener() {
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
                if (!isOtherFieldValid()) {
                    otherFld.setBorder(BorderFactory.createLineBorder(Color.red));
                } else {
                    otherFld.setBorder(BorderFactory.createLineBorder(Color.black));
                }
            }
        });

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

    private boolean isOtherFieldValid(){
        boolean isValid =true;
        if (otherRadio.isSelected()){
            String otherString =otherFld.getText();
                if (otherString ==null|| "".equalsIgnoreCase(otherString.trim())){
                    isValid = false;
                    otherFld.setBorder(BorderFactory.createLineBorder(Color.red));
                } else {
                    try{
                        Integer.parseInt(otherString);
                        otherFld.setBorder(BorderFactory.createLineBorder(Color.black));
                    } catch (NumberFormatException e){
                        isValid = false;
                        otherFld.setBorder(BorderFactory.createLineBorder(Color.red));
                    }
                }

        }
        return isValid;
    }

    */


    private void findCustomer() {

        CustomerInventory customerInventoryImpl = new CustomerInventoryImpl();
        String customerEmail = emailFld.getText();


        if (customerEmail != null && !"".equalsIgnoreCase(customerEmail) && customerEmail.contains("@") && customerEmail.contains(".")
                && (customerEmail.indexOf("@")< customerEmail.indexOf(".") && !customerEmail.contains(".."))) {

            Customer customer = customerInventoryImpl.findCustomer(customerEmail);
            if (customer.getName() == null && customer.getMobileNum() == null) {
                newCustomerLbl.setVisible(true);
            }else{
                newCustomerLbl.setVisible(false);
                customerId = customer.getCustomerId();
            }
            emailFld.setBorder(BorderFactory.createLineBorder(Color.black));
            if (customer.getMobileNum() != null && !"".equalsIgnoreCase(customer.getMobileNum())) {
                telFld.setText(customer.getMobileNum());
                customerId = customer.getCustomerId();
            }

            if (customer.getName() != null && !"".equalsIgnoreCase(customer.getName())) {
                nameFld.setText(customer.getName());
            }
        } else {
            emailFld.setBorder(BorderFactory.createLineBorder(Color.red));
            newCustomerLbl.setVisible(false);
        }

    }

    private boolean isFormDataValid() {
        boolean valid = true;


        if (totalFld.getText() == null || "".equalsIgnoreCase(totalFld.getText().trim())) {
            valid = false;
            totalFld.setBorder(BorderFactory.createLineBorder(Color.red));
        } else {
            totalFld.setBorder(BorderFactory.createLineBorder(Color.black));
        }

        String customerName = nameFld.getText();

        if (customerName == null || "".equalsIgnoreCase(customerName.trim())) {
            valid = false;
            nameFld.setBorder(BorderFactory.createLineBorder(Color.red));
        } else {
            nameFld.setBorder(BorderFactory.createLineBorder(Color.black));
        }
        String customerEmail = emailFld.getText();
        if ( customerEmail== null || "".equalsIgnoreCase(customerEmail.trim())) {
            emailFld.setBorder(BorderFactory.createLineBorder(Color.red));
            valid = false;
        }  else {
            if(emailFld.getText().contains("@")){
                emailFld.setBorder(BorderFactory.createLineBorder(Color.black));
            }else {
                emailFld.setBorder(BorderFactory.createLineBorder(Color.red));
                valid = false;
            }

        }

        String filmTypeStr = filmTypeCombo.getSelectedItem().toString();
        if ( filmTypeStr== null || "Please Select".equalsIgnoreCase(filmTypeStr)) {
            filmTypeCombo.setBorder(BorderFactory.createLineBorder(Color.red));
            valid = false;
        }  else {
            filmTypeCombo
                    .setBorder(BorderFactory.createLineBorder(Color.black));
        }


        String customerMobile = telFld.getText();
        if (customerMobile == null || "".equalsIgnoreCase(customerMobile.trim())) {
            telFld.setBorder(BorderFactory.createLineBorder(Color.red));
            valid = false;
        } else {
            telFld.setBorder(BorderFactory.createLineBorder(Color.black));
        }

        if(datePicker.getModel().getValue() ==null){
            datePicker.setBorder(BorderFactory.createLineBorder(Color.red));
            valid = false;
        }else{
            Date collectionDate = (Date) datePicker.getModel().getValue();
            Date today = new Date();
            if(collectionDate.before(today)){
                datePicker.setBorder(BorderFactory.createLineBorder(Color.red));
                valid = false;
            } else {
                datePicker.setBorder(BorderFactory.createLineBorder(Color.black));

            }


        }

        if (!isTotalFieldsValid()) {
            valid = false;
        }

        if (!isDepositFieldsValid()) {
                valid = false;
        }

        if(!isOtherFieldValid()){
            valid = false;
        }


        return valid;
    }

    private void getPrintSize(){

        if (film6x4Radio.isSelected()){
            printSize = "6 x 4\"";
        } else if (film5x7Radio.isSelected()){
            printSize = "5 x 7\"";
        } else if (film6x8Radio.isSelected()) {
            printSize = "6 x 8\"";
        }

    }

    private void getBorder(){


        if (withBorderRadio.isSelected()){
            borderType = "With Border";
        } else if (noBorderRadio.isSelected()){
            borderType = "No Border";
        } else if (glossyRadio.isSelected()) {
            borderType = "Glossy";
        }

    }

    private void getNumberOfCopies(){


        if (oneRadio.isSelected()){
            numberOfCopies = 1;
        } else if (twoRadio.isSelected()){
            numberOfCopies = 2;
        } else if (threeRadio.isSelected()){
            numberOfCopies = 3;
        } else if (fourRadio.isSelected()){
            numberOfCopies = 4;
        } else{
            if(isOtherFieldValid()){
                numberOfCopies = Integer.parseInt(otherFld.getText().trim());
            }
        }


    }

    private void getPrintTime(){
        if (oneHRRadio.isSelected()){
            printTime = "One Hour";
        } else if (twentyFourHr4Radio.isSelected()){
            printTime= "24 Hours";;
        } else if (mafiFourHr4Radio.isSelected()){
            printTime= "MAFI";
        }
    }

    private void getFilmSize(){

        if (hundred10Radio.isSelected()){
            filmSize= "110";
        } else if (hundred35Radio.isSelected()){
            filmSize= "135";;
        } else if (hundred20Radio.isSelected()){
            filmSize= "120";
        }

    }

    private void getColor(){
        JRadioButton colorRadio = new JRadioButton("Color");
        JRadioButton bAwRadio = new JRadioButton("Black & White");

        if (colorRadio.isSelected()){
            color = true;
        }

    }

    private boolean arePaymentFieldsValid(){
        boolean arePaymentsValid = true;
        if (totalFld.getText() == null || "".equalsIgnoreCase(totalFld.getText().trim())) {
            arePaymentsValid = false;
        } else {
            try {
                totalPrice = Double.parseDouble(totalFld.getText());

            } catch (NumberFormatException nfe) {
                arePaymentsValid = false;
            }

        }

        if (depositFld.getText() != null && !"".equalsIgnoreCase(depositFld.getText().trim())) {

            try {
                deposit = Double.parseDouble(totalFld.getText());

            } catch (NumberFormatException nfe) {
                arePaymentsValid = false;
            }

        }

        return arePaymentsValid;
    }

    private void saveOrder() {

        generalErrorLbl.setVisible(false);
        if (isFormDataValid() && arePaymentFieldsValid()) {

            totalPrice =Double.parseDouble(totalFld.getText());
            vat = totalPrice * 0.175;
            vat = Math.floor(vat * 100) / 100;
            grandTotal = totalPrice + vat;
            deposit = Double.parseDouble(depositFld.getText());
            balance = grandTotal  - deposit;
            balance = Math.floor(balance * 100) / 100;

            String filmTypeStr = filmTypeCombo.getSelectedItem().toString();

            FilmType filmType = FilmType.valueOf(filmTypeStr);

            String customerName = nameFld.getText();
            String customerEmail = emailFld.getText();
            String telephone = telFld.getText();

            Date collectionDate = (Date) datePicker.getModel().getValue();




            if ("Other".equalsIgnoreCase(numberOfCopiesStr)) {
                numberOfCopiesStr = otherFld.getText();
            }

            Customer customer = new Customer(customerName, telephone, customerEmail,customerId);

            getPrintSize();
            getBorder();
            getPrintTime();
            getNumberOfCopies();
            getFilmSize();
            getColor();

            FilmProcessingOrder filmProcessingOrder = new FilmProcessingOrder(
                    customerEmail,
                    new Date(),
                    collectionDate,
                    printSize,
                    borderType,
                    printTime,
                    filmType,
                    color,
                    numberOfCopies,
                    filmSize,
                    totalPrice,
                    deposit,
                    balance
            );

            customer.displayCustomerDetails();
            //filmProcessingOrder.displayFilmProcessingOrder();


            long orderId = processOtherOrders.saveFilmProcessingOrder(filmProcessingOrder, customer);
            //customerInventory.saveCustomer(customer);
            JOptionPane.showMessageDialog(this, "Your orderId is: " + orderId);
            this.setVisible(false);

        } else {
            generalErrorLbl.setVisible(true);
        }

    }

    class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Print")) {
                saveOrder();
            } else if (e.getActionCommand().equals("Search Details")) {
                findCustomer();
            } else if (e.getActionCommand().equals("Cancel")) {
                setVisible(false);
            }

        }

    }

    class RadioListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (otherRadio.isSelected()) {
                otherFld.setEditable(true);
            } else{
                otherFld.setEditable(false);
            }

        }

    }

    public static void main(String[] args){
        FilmProcessingScreen filmProcessingScreen  = new FilmProcessingScreen();
        filmProcessingScreen.setupScreen();
        filmProcessingScreen.setVisible(true);
    }



}