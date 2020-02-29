package pl;

import javax.swing.*;
import java.awt.*;

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

    JLabel totaLbl = new JLabel("Total Price : £");
    JLabel depositeLbl = new JLabel("Deposit :      £");
    JLabel toPayLbl = new JLabel("Left to Pay : £");

    JLabel borderLbl = new JLabel("Border");
    JLabel printtimeLbl = new JLabel("Print time");

    JLabel oneLbl = new JLabel("1");
    JLabel twoLbl = new JLabel("2");
    JLabel threeLbl = new JLabel("3");
    JLabel otherLbl = new JLabel("Other");
    JLabel sizeLbl = new JLabel("Size:");
    JLabel copiesLbl = new JLabel("Copies:");

    JTextField otherFld = new JTextField();
    JTextField nameFld = new JTextField();
    JTextField emailFld = new JTextField();
    JTextField telFld = new JTextField();
    JTextField jobNoFld = new JTextField();
    JTextField orderDateFld = new JTextField();
    JTextField CollectionDateFld = new JTextField();

    JTextField sizeFld = new JTextField();

    JTextField totalFld = new JTextField();
    JTextField depositFld = new JTextField();
    JTextField toPayFld = new JTextField();

    JRadioButton film6x4Radio = new JRadioButton("6 x 4\" (10x15)");
    JRadioButton film5x74Radio = new JRadioButton("5 x 7\" (15x85)");
    JRadioButton film6x8Radio = new JRadioButton("6 x 8\" (15x120)");

    JRadioButton size6x4Radio = new JRadioButton("6 x 4");
    JRadioButton size5x74Radio = new JRadioButton("5 x ");
    JRadioButton size6x8Radio = new JRadioButton("6 x 8)");



    JRadioButton hundred10Radio = new JRadioButton("110");
    JRadioButton hundred35Radio = new JRadioButton("135");
    JRadioButton hundred20Radio = new JRadioButton("120");


    JRadioButton colorRadio = new JRadioButton("Color");
    JRadioButton bpwRadio = new JRadioButton("BPW");



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




    public void setupScreen() {
        getContentPane().setBackground(new Color(255,255,242));
        this.setLayout(null);
        Dimension d = new Dimension(width, hight);
        this.setSize(width, hight);

        /** Adding Labels and Fields*/

        getContentPane().add(emailLbl);
        emailLbl.setBounds(20,120,100,25);


        getContentPane().add(emailFld);
        emailFld.setBounds(120,120,200,25);

        getContentPane().add(searchBtn);
        searchBtn.setBounds(350,120,150,25);


        getContentPane().add(nameLbl);
        nameLbl.setBounds(20,150,100,25);

        getContentPane().add(nameFld);
        nameFld.setBounds(120,150,200,25);




        getContentPane().add(telLbl);
        telLbl.setBounds(20,180,100,25);

        getContentPane().add(telFld);
        telFld.setBounds(120,180,200,25);



        getContentPane().add(orderDateLbl);
        orderDateLbl.setBounds(550,120,100,25);

        getContentPane().add(orderDateFld);
        orderDateFld.setBounds(670,120,200,25);

        getContentPane().add(collectionDateLbl);
        collectionDateLbl.setBounds(550,150,100,25);

        getContentPane().add(CollectionDateFld);
        CollectionDateFld.setBounds(670,150,200,25);

        getContentPane().add(jobNoLbl);
        jobNoLbl.setBounds(550,180,100,25);

        getContentPane().add(jobNoFld);
        jobNoFld.setBounds(670,180,200,25);




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

        ButtonGroup printSizeCroup = new ButtonGroup();

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

        ButtonGroup borderCroup = new ButtonGroup();

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

        ButtonGroup printTimeCroup = new ButtonGroup();

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
        getContentPane().add(hundred10Radio);
        hundred10Radio.setBounds(400,490,50,25);
        hundred10Radio.setOpaque(false);

        getContentPane().add(hundred20Radio);
        hundred20Radio.setBounds(400,540,50,25);
        hundred20Radio.setOpaque(false);

        getContentPane().add(hundred35Radio);
        hundred35Radio.setBounds(400,590,50,25);
        hundred35Radio.setOpaque(false);


        ButtonGroup filmTypeCroupG = new ButtonGroup();

        filmTypeCroupG.add(hundred10Radio);
        filmTypeCroupG.add(hundred35Radio);
        filmTypeCroupG.add(hundred20Radio);


        /** Color radio group*/
        getContentPane().add(colorRadio);
        colorRadio.setBounds(650,490,60,25);
        colorRadio.setOpaque(false);

        getContentPane().add(bpwRadio);
        bpwRadio.setBounds(650,540,60,25);
        bpwRadio.setOpaque(false);

        ButtonGroup colorCroup = new ButtonGroup();

        colorCroup.add(colorRadio);
        colorCroup.add(bpwRadio);

        /** Copies Number radio group*/
        getContentPane().add(copiesLbl);
        copiesLbl.setBounds(25,640,100,25);

        getContentPane().add(oneRadio);
        oneRadio.setBounds(100,640,40,25);
        oneRadio.setOpaque(false);

        getContentPane().add(twoRadio);
        twoRadio.setBounds(160,640,40,25);
        twoRadio.setOpaque(false);

        getContentPane().add(threeRadio);
        threeRadio.setBounds(220,640,40,25);
        threeRadio.setOpaque(false);

        getContentPane().add(fourRadio);
        fourRadio.setBounds(280,640,40,25);
        fourRadio.setOpaque(false);

        getContentPane().add(otherRadio);
        otherRadio.setBounds(340,640,60,25);
        otherRadio.setOpaque(false);

        ButtonGroup copiesNumCroup = new ButtonGroup();

        copiesNumCroup.add(oneRadio);
        copiesNumCroup.add(twoRadio);
        copiesNumCroup.add(threeRadio);
        copiesNumCroup.add(fourRadio);
        copiesNumCroup.add(otherRadio);

        /** Size radio group*/
        getContentPane().add(sizeLbl);
        sizeLbl.setBounds(655,640,60,25);


        getContentPane().add(size5x74Radio);
        size5x74Radio.setBounds(650,690,60,25);
        size5x74Radio.setOpaque(false);

        getContentPane().add(size6x4Radio);
        size6x4Radio.setBounds(650,720,60,25);
        size6x4Radio.setOpaque(false);

        getContentPane().add(size6x8Radio);
        size6x8Radio.setBounds(650,750,60,25);
        size6x8Radio.setOpaque(false);

        ButtonGroup sizeGroup = new ButtonGroup();

        sizeGroup.add(size6x4Radio);
        sizeGroup.add(size5x74Radio);
        sizeGroup.add(size6x8Radio);





        getContentPane().add(totaLbl);
        totaLbl.setBounds(20,690,100,25);

        getContentPane().add(totalFld);
        totalFld.setBounds(100,690,100,25);

        getContentPane().add(depositeLbl);
        depositeLbl.setBounds(20,720,100,25);

        getContentPane().add(depositFld);
        depositFld.setBounds(100,720,100,25);


        getContentPane().add(toPayLbl);
        toPayLbl.setBounds(20,750,100,25);

        getContentPane().add(toPayFld);
        toPayFld.setBounds(100,750,100,25);



        getContentPane().add(submitBtn);
        submitBtn.setBounds(200,800,100,25);

        getContentPane().add(cancelBtn);
        cancelBtn.setBounds(600,800,100,25);






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

        /*
        line_1.setBackground(new Color(100,100,100,100));
        line_2.setBackground(new Color(0,0,0,0));
        line_3.setBackground(new Color(0,0,0,0));
        */

        line_1.setBounds(1,250,1500,10);
        line_2.setBounds(1,470,1500,10);
        line_3.setBounds(1,620,1000,10);

    }


}