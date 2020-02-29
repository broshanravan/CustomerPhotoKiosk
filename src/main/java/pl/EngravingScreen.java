package pl;

import javafx.scene.control.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class EngravingScreen extends JFrame {

    private int width = 900;
    private int hight = 900;

    JLabel orderNoLbl = new JLabel("Job No:");
    JLabel orderNoValueLbl = new JLabel("Job No:");

    JLabel nameLbl = new JLabel("NAME:");
    JLabel emailLbl = new JLabel("Email:");
    JLabel telLbl = new JLabel("TEL:");


    JTextField nameFld = new JTextField();
    JTextField emailFld = new JTextField();
    JTextField telFld = new JTextField();


    JLabel instructionLbl = new JLabel("Special Instruction:");
    JTextArea instructionArea = new JTextArea();

    JLabel totaLbl = new JLabel("Total Price : £");
    JLabel depositeLbl = new JLabel("Deposit :      £");
    JLabel toPayLbl = new JLabel("Left to Pay : £");

    JTextField totalFld = new JTextField();
    JTextField depositFld = new JTextField();
    JTextField toPayFld = new JTextField();

    JLabel collectionDateLbl = new JLabel("Collection Date:");
    DatePicker readonlyDatePicker = new DatePicker();

    /*
    readonlyDatePicker.setLabel("Read-only");
    readonlyDatePicker.setValue(LocalDate.now());
    readonlyDatePicker.setReadOnly(true);
    */

    JLabel orderDateLbl = new JLabel("Order Date:");
    public void setupScreen() {
        getContentPane().setBackground(new Color(255, 255, 242));
        this.setLayout(null);
        Dimension d = new Dimension(width, hight);
        this.setSize(width, hight);
    }
}
