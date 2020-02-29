package pl;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainScreen extends JFrame {

    private int width = 500;
    private int hight = 300;

    JButton FilmProcBtn = new JButton("Film Processing");
    JButton PhotoGiftBtn = new JButton("PhotoGift");
    JButton engravingBtn = new JButton("Engraving");
    JPanel backPanel = new JPanel();
    JPanel buttonPanel= new JPanel();

    ButtonListener buttonListener = new ButtonListener();

    public void setupScreen() {

        setSize(width, hight);
        getContentPane().setLayout(new BorderLayout());

        backPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 20));
        backPanel.setLayout(new BorderLayout());


        Dimension d = new Dimension(150, 150);
        FilmProcBtn.setPreferredSize(d);
        PhotoGiftBtn.setPreferredSize(d);
        engravingBtn.setPreferredSize(d);


        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        buttonPanel.add(FilmProcBtn, BorderLayout.WEST);
        ;
        buttonPanel.add(PhotoGiftBtn, BorderLayout.EAST);
        buttonPanel.add(engravingBtn, BorderLayout.CENTER);


        //buttonPanel.setPreferredSize(new Dimension(1000, 25));

        backPanel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(backPanel);

    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Exit")) {
                System.exit(0);
            } else if (e.getActionCommand().equals("Refresh")) {

            }

        }
    }

}
