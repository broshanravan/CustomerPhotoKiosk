package pl;

import bl.ProcessFilmDevelopmentOrder;
import bl.ProcessOtherOrders;
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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class FilmPrcessingScreen extends JFrame {

    private int width = 900;
    private int hight = 900;

    JLabel nameLbl = new JLabel("NAME:");
    JLabel emailLbl = new JLabel("Email:");
    JLabel telLbl = new JLabel("TEL:");
    JLabel jobNoLbl = new JLabel("Job No:");
    JLabel orderDateLbl = new JLabel("Order Date:");
    JLabel collectionDateLbl = new JLabel("Collection Date:");
    JLabel printSizeLbl = new JLabel("Print Size:");
    JLabel filmTypeLbl = new JLabel("Film Type:");
    JLabel filmSizeLbl = new JLabel("Film size:");
    JLabel printTypeLbl = new JLabel("Print Type:");


    JLabel totalLbl = new JLabel("Total Price : £");
    JLabel depositLbl = new JLabel("Deposit :      £");
    JLabel toPayLbl = new JLabel("Left to Pay : £");

    JLabel borderLbl = new JLabel("Border");
    JLabel printtimeLbl = new JLabel("Print time");

    JLabel sizeLbl = new JLabel("Size:");
    JLabel copiesLbl = new JLabel("Copies:");

    JTextField otherFld = new JTextField();
    JTextField nameFld = new JTextField();
    JTextField emailFld = new JTextField();
    JTextField telFld = new JTextField();
    JTextField jobNoFld = new JTextField();
    JTextField orderDateFld = new JTextField();

    JTextField totalFld = new JTextField();
    JTextField depositFld = new JTextField();
    JTextField toPayFld = new JTextField();

    JRadioButton film6x4Radio = new JRadioButton("6 x 4\" (10x15)");
    JRadioButton film5x74Radio = new JRadioButton("5 x 7\" (15x85)");
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

    String[] fimlList = { "Please Select", "FUJI", "KODAK", "KONIKA", "AGFA", "BOOTS"};
    JComboBox filmTypeCombo = new JComboBox(fimlList);

    JButton searchBtn =new JButton("Search Details");
    JButton cancelBtn =new JButton("Cancel");
    JButton submitBtn =new JButton("Submit");

    ButtonGroup filmSizeGroup = new ButtonGroup();
    ButtonGroup colorCroup = new ButtonGroup();
    ButtonGroup printSizeCroup = new ButtonGroup();
    ButtonGroup borderCroup = new ButtonGroup();
    ButtonGroup printTimeCroup = new ButtonGroup();
    ButtonGroup copiesNumCroup = new ButtonGroup();

    ProcessFilmDevelopmentOrder processOtherOrders = new ProcessFilmDevelopmentOrder();

    CustomerInventory customerInventory = new CustomerInventoryImpl();


    String fileName = "C:\\Users\\Behrooz\\Mainworkspace\\CustomerPhotoKiosk\\logos\\KodakLogo.jpg";
    //ImageIcon KodakLogo = new ImageIcon("C:\\Users\\Behrooz\\Mainworkspace\\CustomerPhotoKiosk\\logos\\KodakLogo.jpg");

    JDatePickerImpl datePicker = null;
    public void setupScreen() {

        setTitle("Film Processing");
        setAlwaysOnTop( true );
        getContentPane().setBackground(new Color(255,255,242));
        this.setLayout(null);
        Dimension d = new Dimension(width, hight);
        this.setSize(width, hight);
        setResizable(false);


        /*************************************************************** Adding Kodak logo to Panel **********************************************************************************/


        BufferedImage KodakLogo = null;
        try {
            KodakLogo = ImageIO.read(new File(fileName));
            Image dimg = KodakLogo.getScaledInstance(500, 90, Image.SCALE_SMOOTH);
            ImageIcon LogoIcon = new ImageIcon(dimg);

            JLabel logoLabel = new JLabel(LogoIcon);
            getContentPane().add(logoLabel);
            logoLabel.setBounds(100,10,700,110);

        } catch (IOException e) {
            e.printStackTrace();
        }








        /** Adding Labels and Fields*/

        getContentPane().add(emailLbl);
        emailLbl.setBounds(20,150,100,25);


        getContentPane().add(emailFld);
        emailFld.setBounds(120,150,200,25);

        getContentPane().add(searchBtn);
        searchBtn.setBounds(350,150,150,25);


        getContentPane().add(nameLbl);
        nameLbl.setBounds(20,180,100,25);

        getContentPane().add(nameFld);
        nameFld.setBounds(120,180,200,25);


        getContentPane().add(telLbl);
        telLbl.setBounds(20,210,100,25);

        getContentPane().add(telFld);
        telFld.setBounds(120,210,200,25);



        getContentPane().add(orderDateLbl);
        orderDateLbl.setBounds(550,150,100,25);

        getContentPane().add(orderDateFld);
        orderDateFld.setBounds(670,150,200,25);

        getContentPane().add(collectionDateLbl);
        collectionDateLbl.setBounds(550,180,100,25);


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
        datePicker.setBounds(670,180,200,25);



        getContentPane().add(jobNoLbl);
        jobNoLbl.setBounds(550,210,100,25);

        getContentPane().add(jobNoFld);
        jobNoFld.setBounds(670,210,200,25);




        /** Second Section */


        /** PrintSize radio group*/
        getContentPane().add(printSizeLbl);
        printSizeLbl.setBounds(25,280,100,25);

        getContentPane().add(film6x8Radio);
        film6x8Radio.setBounds(20,330,120,25);
        film6x8Radio.setOpaque(false);

        getContentPane().add(film6x4Radio);
        film6x4Radio.setBounds(20,380,120,25);
        film6x4Radio.setOpaque(false);

        getContentPane().add(film5x74Radio);
        film5x74Radio.setBounds(20,430,120,25);
        film5x74Radio.setOpaque(false);



        printSizeCroup.add(film6x8Radio);
        printSizeCroup.add(film6x4Radio);
        printSizeCroup.add(film5x74Radio);

        /** Border radio group*/
        getContentPane().add(borderLbl);
        borderLbl.setBounds(405,280,100,25);

        getContentPane().add(withBorderRadio);
        withBorderRadio.setBounds(400,330,120,25);
        withBorderRadio.setOpaque(false);

        getContentPane().add(noBorderRadio);
        noBorderRadio.setBounds(400,380,120,25);
        noBorderRadio.setOpaque(false);

        getContentPane().add(glossyRadio);
        glossyRadio.setBounds(400,430,120,25);
        glossyRadio.setOpaque(false);



        borderCroup.add(withBorderRadio);
        borderCroup.add(noBorderRadio);
        borderCroup.add(glossyRadio);

        /** Print time radio group*/
        getContentPane().add(printtimeLbl);
        printtimeLbl.setBounds(655,280,100,25);

        getContentPane().add(oneHRRadio);
        oneHRRadio.setBounds(650,330,120,25);
        oneHRRadio.setOpaque(false);

        getContentPane().add(twentyFourHr4Radio);
        twentyFourHr4Radio.setBounds(650,380,140,25);
        twentyFourHr4Radio.setOpaque(false);

        getContentPane().add(mafiFourHr4Radio);
        mafiFourHr4Radio.setBounds(650,430,120,25);
        mafiFourHr4Radio.setOpaque(false);

        printTimeCroup.add(oneHRRadio);
        printTimeCroup.add(twentyFourHr4Radio);
        printTimeCroup.add(mafiFourHr4Radio);


        /** Third Section */

        getContentPane().add(filmTypeLbl);
        filmTypeLbl.setBounds(20,490,100,25);

        /** Adding Combo Box */
        getContentPane().add(filmTypeCombo);
        filmTypeCombo.setBounds(100,490,200,25);
        filmTypeCombo.setOpaque(false);



        /** Film Type radio group*/
        getContentPane().add(filmSizeLbl);
        filmSizeLbl.setBounds(400,490,100,25);



        getContentPane().add(hundred10Radio);
        hundred10Radio.setBounds(400,520,50,25);
        hundred10Radio.setOpaque(false);

        getContentPane().add(hundred20Radio);
        hundred20Radio.setBounds(400,550,50,25);
        hundred20Radio.setOpaque(false);

        getContentPane().add(hundred35Radio);
        hundred35Radio.setBounds(400,580,50,25);
        hundred35Radio.setOpaque(false);




        filmSizeGroup.add(hundred10Radio);
        filmSizeGroup.add(hundred35Radio);
        filmSizeGroup.add(hundred20Radio);



        /** Color radio group*/

        getContentPane().add(printTypeLbl);
        printTypeLbl.setBounds(650,490,60,25);

        getContentPane().add(colorRadio);
        colorRadio.setBounds(650,520,60,25);
        colorRadio.setOpaque(false);

        getContentPane().add(bAwRadio);
        bAwRadio.setBounds(650,550,150,25);
        bAwRadio.setOpaque(false);



        colorCroup.add(colorRadio);
        colorCroup.add(bAwRadio);

        /** Copies Number radio group*/
        getContentPane().add(copiesLbl);
        copiesLbl.setBounds(20,640,60,25);

        getContentPane().add(oneRadio);
        oneRadio.setBounds(20,670,60,25);
        oneRadio.setOpaque(false);

        getContentPane().add(twoRadio);
        twoRadio.setBounds(120,670,60,25);
        twoRadio.setOpaque(false);


        getContentPane().add(threeRadio);
        threeRadio.setBounds(20,700,60,25);
        threeRadio.setOpaque(false);


        getContentPane().add(fourRadio);
        fourRadio.setBounds(120,700,60,25);
        fourRadio.setOpaque(false);


        getContentPane().add(otherRadio);
        otherRadio.setBounds(20,730,60,25);
        otherRadio.setOpaque(false);

        getContentPane().add(otherFld);
        otherFld.setBounds(80,730,60,25);




        copiesNumCroup.add(oneRadio);
        copiesNumCroup.add(twoRadio);
        copiesNumCroup.add(threeRadio);
        copiesNumCroup.add(fourRadio);
        copiesNumCroup.add(otherRadio);



        getContentPane().add(totalLbl);
        totalLbl.setBounds(450,670,100,25);

        getContentPane().add(totalFld);
        totalFld.setBounds(550,670,100,25);

        getContentPane().add(depositLbl);
        depositLbl.setBounds(450,700,100,25);

        getContentPane().add(depositFld);
        depositFld.setBounds(550,700,100,25);


        getContentPane().add(toPayLbl);
        toPayLbl.setBounds(450,730,100,25);

        getContentPane().add(toPayFld);
        toPayFld.setBounds(550,730,100,25);



        getContentPane().add(submitBtn);
        submitBtn.setBounds(200,800,100,25);

        getContentPane().add(cancelBtn);
        cancelBtn.setBounds(600,800,100,25);

        ButtonListener buttonListener = new ButtonListener();
        submitBtn.addActionListener(buttonListener);
        cancelBtn.addActionListener(buttonListener);




        String border = "";
        for (int i=0 ;i<245 ; i++){
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


        line_1.setBounds(1,250,1500,10);
        line_2.setBounds(1,470,1500,10);
        line_3.setBounds(1,620,1000,10);

    }


    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Submit")) {
                saveOrder();
            } else if (e.getActionCommand().equals("Search Details")) {
                findCustomer();
            } else if (e.getActionCommand().equals("Cancel")) {
                setVisible(false);
            }

        }

        double totalPrice =0;
        double depoisit =0;
        double balance = 0;


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


        private boolean isFormDataValid(FilmProcessingOrder filmProcessingOrder, Customer customer){
            boolean valid = true;


            if(customer.getName() == null || "".equalsIgnoreCase(customer.getName())){
                valid = false;
            }
            if(customer.getEmail()== null || "".equalsIgnoreCase(customer.getEmail())){
                valid = false;
            }

            if(customer.getMobileNum() == null || "".equalsIgnoreCase(customer.getMobileNum())){
                valid = false;
            }

            if(filmProcessingOrder.getCustomerEmail() ==null || "".equalsIgnoreCase(filmProcessingOrder.getCustomerEmail())){
                valid = false;
            }



             if (totalFld.getText() == null || "".equalsIgnoreCase(totalFld.getText().trim())){
                valid = false;
             }else{
                 try{
                     totalPrice =  Integer.parseInt(totalFld.getText());
                     filmProcessingOrder.setTotalPrice(totalPrice);

                 } catch(NumberFormatException nfe){
                     valid = false;
                 }

            }

            if (depositFld.getText() != null && !"".equalsIgnoreCase(depositFld.getText().trim())){

                try{
                    depoisit =  Integer.parseInt(totalFld.getText());
                    filmProcessingOrder.setDepoisite(depoisit);

                } catch(NumberFormatException nfe){
                    valid = false;
                }

            }

            if (toPayFld.getText() == null || "".equalsIgnoreCase(toPayFld.getText().trim())){
                valid = false;
            }else{
                try{
                    balance =  Integer.parseInt(toPayFld.getText());
                    filmProcessingOrder.setBalance(balance);

                } catch(NumberFormatException nfe){
                    valid = false;
                }

            }

            return valid;
        }

        private void saveOrder(){




            String filmTypeStr = filmTypeCombo.getSelectedItem().toString();

            FilmType filmType = FilmType.valueOf(filmTypeStr);

            String customerName =  nameFld.getName();
            String customerEmail = emailFld.getText();
            String telephone = telFld.getToolTipText();

            Date collectionDate  =  (Date) datePicker.getModel().getValue();
            boolean color = "Color".equalsIgnoreCase(colorCroup.getSelection().getActionCommand());

            String printSize = printSizeCroup.getSelection().getActionCommand();
            String borderType = borderCroup.getSelection().getActionCommand();
            String printTime = printTimeCroup.getSelection().getActionCommand();
            String numberOfCopiesStr = copiesNumCroup.getSelection().getActionCommand();
            String filmSize = filmSizeGroup.getSelection().getActionCommand();

            if("Oher".equalsIgnoreCase(numberOfCopiesStr)){
                numberOfCopiesStr = otherFld.getText();
            }
            int numberOfCopies = Integer.parseInt(numberOfCopiesStr);
            Customer customer = new Customer(customerName,  telephone, customerEmail) ;



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
                                                                    depoisit,
                                                                    balance
                                                                 );


            if(isFormDataValid(filmProcessingOrder,customer)){
                processOtherOrders.saveFilmProcessingOrder(filmProcessingOrder,customer);
                customerInventory.saveCustomer(customer);
            }

        }



    }

}