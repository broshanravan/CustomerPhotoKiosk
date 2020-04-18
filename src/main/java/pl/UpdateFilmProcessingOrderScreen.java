package pl;

import bl.ProcessFilmDevelopmentOrder;
import bl.beens.Customer;
import bl.beens.FilmProcessingOrder;
import bl.enums.FilmType;
import dl.CustomerInventory;
import dl.CustomerInventoryImpl;
import dl.FilmProceccingOrderInventory;
import dl.FilmProceccingOrderInventoryImpl;
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

public class UpdateFilmProcessingOrderScreen extends JDialog {

    OrderSearchScreen orderSearchScreen;
    long orderId;
    long customerId = 0;
    boolean color =false;
    private int width = 900;
    private int hight = 900;

    double totalPrice =0;
    double deposit = 0;
    double balance = 0;
    double  vat = 0;
    double grandTotal =0;
    String printSize ;
    String borderType ;
    String printTime ;
    String numberOfCopiesStr ;
    String filmSize;
    int numberOfCopies;

    Customer customer = null;
    FilmProcessingOrder filmProcessingOrder = null;


    DateFormat dateFormat = new SimpleDateFormat("dd/MMM/YYYY");


    JLabel nameLbl = new JLabel("NAME:");
    JLabel emailLbl = new JLabel("Email:");
    JLabel telLbl = new JLabel("TEL:");
    JLabel orderNumberLbl = new JLabel("Order Number:");

    JLabel dateLbl = new JLabel(" Order Date:");
    JLabel dateValueLbl = new JLabel(dateFormat.format(new Date()));

    JLabel closedLbl = new JLabel("<html>This order has been<br/>closed</html>", SwingConstants.CENTER);
    JLabel generalErrorLbl = new JLabel("<html>There was an error in provided order details</html>", SwingConstants.CENTER);

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

    JTextField orderNumberFld = new JTextField();

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

    String[] filmList = {"Please Select", "FUJI", "KODAK", "KONIKA", "AGFA", "BOOTS"};
    JComboBox filmTypeCombo = new JComboBox(filmList);

    JButton closeBtn = new JButton("Complete & Close");
    JButton cancelBtn = new JButton("Cancel");
    JButton printBtn = new JButton("update & Print");

    ButtonGroup filmSizeGroup = new ButtonGroup();
    ButtonGroup colorCroup = new ButtonGroup();
    ButtonGroup printSizeGroup = new ButtonGroup();
    ButtonGroup borderCroup = new ButtonGroup();
    ButtonGroup printTimeCroup = new ButtonGroup();
    ButtonGroup copiesNumCroup = new ButtonGroup();



    ProcessFilmDevelopmentOrder processFilmDevelopmentOrder = new ProcessFilmDevelopmentOrder();

    CustomerInventory customerInventory = new CustomerInventoryImpl();


    String fileName = "./logos/KodakLogo.jpg";
    //ImageIcon KodakLogo = new ImageIcon("C:\\Users\\Behrooz\\Mainworkspace\\CustomerPhotoKiosk\\logos\\KodakLogo.jpg");

    JDatePickerImpl datePicker = null;

    public UpdateFilmProcessingOrderScreen(Customer customerIn, FilmProcessingOrder filmProcessingOrderIn ,OrderSearchScreen orderSearchScreenIn){
        orderId = filmProcessingOrderIn.getOrderNum();
        customer = customerIn;
        filmProcessingOrder = filmProcessingOrderIn;
        orderSearchScreen = orderSearchScreenIn;
    }

    public void setupScreen() {

        setTitle("Update Film Processing");
        setAlwaysOnTop(true);
        getContentPane().setBackground(new Color(255, 255, 242));
        this.setLayout(null);
        Dimension d = new Dimension(width, hight);
        this.setSize(width, hight);
        setResizable(false);

        getContentPane().add(closedLbl);


        /*********************************************** transaction completed  **************************************************************/
        closedLbl.setFont(new Font("Serif", Font.BOLD, 80));
        closedLbl.setBounds(30,350,800,200);
        closedLbl.setForeground(Color.red);
        closedLbl.setBorder(BorderFactory.createLineBorder(Color.red));
        closedLbl.setVisible(false);

        /*********************************************** Adding Kodak logo to Panel **************************************************************/


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





        getContentPane().add(orderNumberFld);
        getContentPane().add(orderNumberLbl);
        orderNumberLbl.setBounds(20, 120, 220, 25);
        orderNumberFld.setBounds(110, 120, 220, 25);

        orderNumberFld.setEditable(false);
        orderNumberFld.setForeground(Color.black);
        orderNumberFld.setBackground(Color.white);


        getContentPane().add(emailFld);
        emailFld.setBounds(110, 150, 220, 25);

        getContentPane().add(closeBtn);
        closeBtn.setBounds(370, 150, 150, 25);


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
        ;
        datePicker.setBounds(670, 210, 200, 25);




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
        //withBorderRadio.setSelected(true);
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
        //hundred10Radio.setSelected(true);
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
        //colorRadio.setSelected(true);
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
        totalLbl.setBounds(450, 670, 100, 25);

        getContentPane().add(totalFld);
        totalFld.setBounds(550, 670, 100, 25);


        getContentPane().add(vatlLbl);
        vatlLbl.setBounds(450, 700, 100, 25);
        vatFld.setEditable(false);

        getContentPane().add(vatFld);
        vatFld.setBounds(550, 700, 100, 25);

        vatFld.setForeground(Color.black);
        vatFld.setBackground(Color.white);


        getContentPane().add(depositLbl);
        depositLbl.setBounds(450, 730, 100, 25);

        getContentPane().add(depositFld);
        depositFld.setBounds(550, 730, 100, 25);


        getContentPane().add(toPayLbl);
        toPayLbl.setBounds(450, 760, 100, 25);

        getContentPane().add(toPayFld);
        toPayFld.setBounds(550, 760, 100, 25);
        toPayFld.setEditable(false);
        toPayFld.setForeground(Color.black);
        toPayFld.setBackground(Color.white);


        getContentPane().add(printBtn);
        printBtn.setBounds(50, 810, 150, 25);

        getContentPane().add(closeBtn);
        closeBtn.setBounds(370, 810, 150, 25);

        getContentPane().add(cancelBtn);
        cancelBtn.setBounds(700, 810, 150, 25);

        getContentPane().add(generalErrorLbl);
        generalErrorLbl.setBounds(240, 830, 400, 35);
        generalErrorLbl.setForeground(Color.red);
        generalErrorLbl.setFont(new Font("Serif", Font.BOLD, 18));
        generalErrorLbl.setVisible(false);

        ButtonListener buttonListener = new ButtonListener();
        printBtn.addActionListener(buttonListener);
        cancelBtn.addActionListener(buttonListener);
        closeBtn.addActionListener(buttonListener);


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
        populateForm();


    }

    public  void populateForm(){

        if(customer != null){

             nameFld.setText(customer.getName());
             emailFld.setText(customer.getEmail());
             telFld.setText(customer.getMobileNum());
             customerId = customer.getCustomerId();

        }



        if(filmProcessingOrder != null){
            orderNumberFld.setText(String.valueOf(filmProcessingOrder.getOrderNum()));

            deposit = filmProcessingOrder.getDeposit();
            String depositStr = String.valueOf(deposit);

            double total = filmProcessingOrder.getTotalPrice();
            String totalStr = String.valueOf(total);
            double vat = Math.floor(filmProcessingOrder.getTotalPrice() * 17.5)/100;
            String vatStr = String.valueOf(vat);

            double toPay = total + vat - deposit;
            String toPayStr = String.valueOf(Math.floor(toPay * 100) / 100);

            System.out.println("filmTypeCombo = " + filmProcessingOrder.getFilmType() );

            filmTypeCombo.setSelectedIndex(0);
            String fimlTypStr =filmProcessingOrder.getFilmType().toString();

            System.out.println("filmList.length = " + filmList.length);

            int index = 0;


            for (int i = 0; i < filmList.length; i++){
                System.out.println("Item at index " + i + " = " + filmTypeCombo.getItemAt(i).toString());
                if (filmTypeCombo.getItemAt(i).toString().contains(fimlTypStr)){
                    index = i;
                }
            }


            filmTypeCombo.setSelectedIndex(index);

            totalFld.setText(totalStr);
            vatFld.setText(vatStr);
            depositFld.setText(depositStr);
            toPayFld.setText(toPayStr);

            Date collectionDate = filmProcessingOrder.getCollectionDate();


            Calendar calendar = Calendar.getInstance();
            calendar.setTime(collectionDate);
            int day =calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year =calendar.get(Calendar.YEAR);


            datePicker.getModel().setDay(day);
            datePicker.getModel().setMonth(month);
            datePicker.getModel().setYear(year);
            datePicker.getModel().setSelected(true);

            orderId = filmProcessingOrder.getOrderNum();


            setSelectedRadioButtons(filmProcessingOrder);

            if(filmProcessingOrder.isCompleted()){
                closedLbl.setVisible(true);
                disableAllFields();
            }

        }

     }


    private void disableAllFields(){
         totalFld.setEnabled(false);
         vatFld.setEnabled(false);
         depositFld.setEnabled(false);
         toPayFld.setEnabled(false);

         film6x4Radio.setEnabled(false);
         film5x7Radio.setEnabled(false);
         film6x8Radio.setEnabled(false);


         hundred10Radio.setEnabled(false);
         hundred35Radio.setEnabled(false);
         hundred20Radio.setEnabled(false);

         colorRadio.setEnabled(false);
         bAwRadio.setEnabled(false);

         withBorderRadio.setEnabled(false);
         noBorderRadio.setEnabled(false);
         glossyRadio.setEnabled(false);

         oneRadio.setEnabled(false);
         twoRadio.setEnabled(false);
         threeRadio.setEnabled(false);
         fourRadio.setEnabled(false);
         otherRadio.setEnabled(false);

         oneHRRadio.setEnabled(false);
         twentyFourHr4Radio.setEnabled(false);

          mafiFourHr4Radio.setEnabled(false);


         filmTypeCombo.setEnabled(false);

          closeBtn.setEnabled(false);
          printBtn.setEnabled(false);

        nameFld.setEnabled(false);
        emailFld.setEnabled(false);
        telFld.setEnabled(false);

        datePicker.setEnabled(false);

     }

    private void setSelectedRadioButtons(FilmProcessingOrder filmProcessingOrder) {

        if (filmProcessingOrder.getPrintSize().equalsIgnoreCase("6 x 4\"")){
            film6x4Radio.setSelected(true);
        } else if(filmProcessingOrder.getPrintSize().equalsIgnoreCase("5 x 7\"")){
            film5x7Radio.setSelected(true);
        } else if(filmProcessingOrder.getPrintSize().equalsIgnoreCase("6 x 8\"")){
            film6x8Radio.setSelected(true);
        }


        if (filmProcessingOrder.isColor()){
            colorRadio.setSelected(true);
        } else {
            bAwRadio.setSelected(true);
        }



        if (filmProcessingOrder.getBorderType().equalsIgnoreCase("With Border")){
            withBorderRadio.setSelected(true);
        } else if (filmProcessingOrder.getBorderType().equalsIgnoreCase("No Border")) {
            noBorderRadio.setSelected(true);
        }else{
            glossyRadio.setSelected(true);
        }


        if (filmProcessingOrder.getNumberOfCopies() ==1 ){
            oneRadio.setSelected(true);
            otherFld.setEditable(false);
            otherFld.setText("");
        } else if (filmProcessingOrder.getNumberOfCopies() == 2 ){
            twoRadio.setSelected(true);
            otherFld.setEditable(false);
            otherFld.setText("");
        } else if (filmProcessingOrder.getNumberOfCopies() == 3 ){
            threeRadio.setSelected(true);
            otherFld.setEditable(false);
            otherFld.setText("");
        } else if (filmProcessingOrder.getNumberOfCopies() == 4 ){
            fourRadio.setSelected(true);
            otherFld.setEditable(false);
            otherFld.setText("");
        }
        else {
            otherRadio.setSelected(true);
            otherFld.setText(String.valueOf(filmProcessingOrder.getNumberOfCopies()));
            otherFld.setEnabled(true);
            otherFld.setEditable(true);

        }

        if (filmProcessingOrder.getPrintTime().equalsIgnoreCase("One Hour") ){
            oneHRRadio.setSelected(true);
        } else if (filmProcessingOrder.getPrintTime().equalsIgnoreCase("24 Hours") ){
            twentyFourHr4Radio.setSelected(true);
        } else if (filmProcessingOrder.getPrintTime().equalsIgnoreCase("MAFI") ){
            mafiFourHr4Radio.setSelected(true);
        }


        if(filmProcessingOrder.getFilmSize().equalsIgnoreCase("110")){
            hundred10Radio.setSelected(true);
        } else if(filmProcessingOrder.getFilmSize().equalsIgnoreCase("135")){
            hundred35Radio.setSelected(true);
        }else if(filmProcessingOrder.getFilmSize().equalsIgnoreCase("120")){
            hundred20Radio.setSelected(true);
        }


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

                        balance = grandTotal - deposit;
;

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

        if (film6x8Radio.isSelected()){
            printSize = "6 x 8\"";
        } else if (film6x4Radio.isSelected()){
            printSize = "6 x 4\"";
        } else if (film5x7Radio.isSelected()) {
            printSize = "5 x 7\"";
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



        //hundred10Radio = new JRadioButton("110");
        //JRadioButton  = new JRadioButton("135");
        //JRadioButton hundred20Radio

        if (hundred10Radio.isSelected()){
            filmSize= "110";
        } else if (hundred20Radio.isSelected()){
            filmSize= "120";;
        } else if (hundred35Radio.isSelected()){
            filmSize= "135";
        }


    }

    private void getColor(){

        if (colorRadio.isSelected()){
            color = true;
        } else {
            color =false;
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

            filmProcessingOrder.setOrderNum(orderId);



            processFilmDevelopmentOrder.saveFilmProcessingOrder(filmProcessingOrder, customer);
            customerInventory.saveCustomer(customer);
            JOptionPane.showMessageDialog(this, "Order number " + orderId + " has been updated successfully");
            this.setVisible(false);
            orderSearchScreen.setVisible(false);

        }else {
            generalErrorLbl.setVisible(true);
        }

    }

    private void closeOrder(){


        int answer = JOptionPane.showConfirmDialog(this ,"Are you sure you would like to close Film Processing order number " + orderId +"?");

        System.out.println(answer);

        if(answer == 0) {
            System.out.println("being Closed");
            processFilmDevelopmentOrder.closeFilmProcessingOrderExist(orderId);
            setVisible(false);
        }else if(answer == 2) {
            System.out.println("Closing canceled");
            setVisible(false);
        }else {
            System.out.println("Not being Closed");
        }
    }


    class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("update & Print")) {
                saveOrder();

            }  else if (e.getActionCommand().equals("Cancel")) {
                setVisible(false);
            } else if (e.getActionCommand().equals("Complete & Close")) {
                closeOrder();
            }

        }

    }

    class RadioListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (otherRadio.isSelected()) {
                otherFld.setEditable(true);
            } else{
                otherFld.setEditable(false);
                otherFld.setText("");
            }

        }

    }





}
