package pl;

import bl.ProcessOtherOrders;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CustomerScreen extends JFrame{

    private int width = 520;
    private int hight = 320;

    JButton FilmProcBtn = new JButton("Film Processing");
    JButton PhotoGiftBtn = new JButton("Photo Gift");
    JButton engravingBtn = new JButton("Engraving");

    String fileName = "./logos/KodakLogo.jpg";

    CustomerScreen.ButtonListener buttonListener = new CustomerScreen.ButtonListener();
    ProcessOtherOrders processOtherOrders = new ProcessOtherOrders();


    public void setupScreen() {

        setTitle("Customer Photo Kiosk");
        setResizable(false);
        getContentPane().setBackground(new Color(255, 255, 242));
        this.setLayout(null);
        this.setSize(width, hight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        getContentPane().add(FilmProcBtn);
        FilmProcBtn.setBounds(10,120,150,150);


        getContentPane().add(PhotoGiftBtn);
        PhotoGiftBtn.setBounds(180,120,150,150);

        getContentPane().add(engravingBtn);
        engravingBtn.setBounds(350,120,150,150);


        /*************************************************************** Adding Kodak logo to Panel **********************************************************************************/

        BufferedImage KodakLogo = null;
        try {
            KodakLogo = ImageIO.read(new File(fileName));
            Image dimg = KodakLogo.getScaledInstance(500, 90, Image.SCALE_SMOOTH);
            ImageIcon LogoIcon = new ImageIcon(dimg);

            JLabel logoLabel = new JLabel(LogoIcon);
            getContentPane().add(logoLabel);
            logoLabel.setBounds(40,10,400,110);

        } catch (IOException e) {
            e.printStackTrace();
        }

        FilmProcBtn.addActionListener(buttonListener);
        PhotoGiftBtn.addActionListener(buttonListener);
        engravingBtn.addActionListener(buttonListener);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                System.exit(0);
            }
        });
    }

    private class ButtonListener implements ActionListener {



        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Film Processing")) {
                FilmProcessingScreen filmProcessingScreen = new FilmProcessingScreen();
                filmProcessingScreen.setupScreen();
                filmProcessingScreen.setVisible(true);
            } else if (e.getActionCommand().equals("Photo Gift")) {
                PhotoGiftScreen photoGiftScreen = new PhotoGiftScreen();
                photoGiftScreen.setupScreen();
                photoGiftScreen.setVisible(true);

            }  else if (e.getActionCommand().equals("Engraving")) {
                EngravingScreen engravingScreen = new EngravingScreen();
                engravingScreen.setupScreen();
                engravingScreen.setVisible(true);
            }
        }
    }

    public static void main(String[] args){
        CustomerScreen customerScreen = new  CustomerScreen();
        customerScreen.setupScreen();
        customerScreen.setVisible(true);
    }
}
