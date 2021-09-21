package pages;

import Database.DatabaseHelper;
import classes.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddProductPage extends JPanel{
    JFrame frame = new JFrame();

    Font typingFont = new Font("Arial",Font.PLAIN,14);
    Font labelFont = new Font("Arial",Font.BOLD,14);

    JButton backBtn = new JButton("Back");

    JTextField productName = new JTextField();
    JTextField sellingPrice = new JTextField();
    JTextField buyingPrice = new JTextField();
    JTextField quantity = new JTextField();

    JLabel productNameLbl = new JLabel("-   Product Name : ");

    JLabel sellingPriceLbl = new JLabel("-   Selling Price : ");

    JLabel buyingPriceLbl = new JLabel("-   Buying Price : ");

    JLabel quantityLbl = new JLabel("-   Quantity : ");

    JLabel dateLbl = new JLabel(" -     Date : ");
    JLabel dateLblValue = new JLabel(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));


    JButton insertBtn = new JButton("Enter Product");
    JLabel msg = new JLabel();

    public AddProductPage(){
        typingRules();
        Design();
        frameSettings();
        buttonsAction();

    }


    private void Design(){


        backBtn.setBounds(35,50,80,20);
        productNameLbl.setBounds(45,95,140,15);
        productName.setBounds(190,90,190,25);

        sellingPriceLbl.setBounds(45,135,120,15);
        sellingPrice.setBounds(190,130,190,25);

        buyingPriceLbl.setBounds(45,175,120,15);
        buyingPrice.setBounds(190,170,190,25);


        quantityLbl.setBounds(45,215,120,15);
        quantity.setBounds(190,210,70,25);

        dateLbl.setBounds(45,255,120,15);
        dateLblValue.setBounds(172,250,160,25);


        insertBtn.setBounds(175,330,220,35);
        msg.setBounds(190,370,160,20);


        //Fonts
        productNameLbl.setFont(labelFont);
        sellingPriceLbl.setFont(labelFont);
        quantityLbl.setFont(labelFont);
        dateLbl.setFont(labelFont);
        dateLblValue.setFont(typingFont);
        buyingPriceLbl.setFont(labelFont);

        //Text Color
        msg.setForeground(Color.red);

    }

    private void buttonsAction(){
        insertBtn.addActionListener((ActionEvent ae) ->{
            if(productName.getText().isEmpty() || sellingPrice.getText().isEmpty() || buyingPrice.getText().isEmpty() || quantity.getText().isEmpty()) {

                msg.setBounds(255,370,200,20);
                msg.setText("Fill All Fields !");
            }else {
                try {
                    double d1 = Double.parseDouble(sellingPrice.getText());
                    double d2 = Double.parseDouble(buyingPrice.getText());
                    int i = Integer.parseInt(quantity.getText());
                    if (d1 < 0){
                        msg.setBounds(175, 370, 330, 20);
                        msg.setText("You Enter Selling Price with Negative Value");
                    }
                    else if (d2 < 0){
                        msg.setBounds(175, 370, 330, 20);
                        msg.setText("You Enter Buying Price with Negative Value");
                    }
                    else if (i < 0){
                        msg.setBounds(175, 370, 330, 20);
                        msg.setText("You Enter Product Quantity with Negative Value");
                    }
                    else if(i==0){
                        msg.setBounds(175, 370, 330, 20);
                        msg.setText("You Enter Product Quantity :  0");
                    }
                    else {
                        ConfirmPopUp pop = new ConfirmPopUp(productName.getText());
                        if (pop.getResult() == 0) {
                            msg.setBounds(210, 370, 200, 20);
                            msg.setText(insertProcess());
                        } else {
                            dateLblValue.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
                        }
                    }
                }
                catch (NumberFormatException nfe){
                    msg.setBounds(210, 370, 210, 20);
                    msg.setText("Check Your Data Again Please!!!");
                }
            }
        });

        backBtn.addActionListener((ActionEvent ae)->{
            new Menu();
            frame.dispose();
        });
    }

    private void frameSettings(){


        frame.add(productName);
        frame.add(productNameLbl);
        frame.add(sellingPrice);
        frame.add(sellingPriceLbl);
        frame.add(buyingPrice);
        frame.add(buyingPriceLbl);
        frame.add(quantity);
        frame.add(quantityLbl);
        frame.add(insertBtn);
        frame.add(dateLbl);
        frame.add(dateLblValue);
        frame.add(msg);
        frame.add(backBtn);
        frame.add(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Adding Product Page");
        frame.setSize(600,460);
        frame.setLocation(560,80);
        frame.setVisible(true);
        frame.setResizable(false);
    }
    private void typingRules(){

        insertBtn.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                jTextFieldKeyTyped(e);
                if (e.getKeyChar() == KeyEvent.VK_ENTER ) {
                    if(productName.getText().isEmpty() || sellingPrice.getText().isEmpty() || buyingPrice.getText().isEmpty() || quantity.getText().isEmpty()) {

                        msg.setBounds(210,370,200,20);
                        msg.setText("Fill All Fields !");
                    }else {
                        try {
                            double d1 = Double.parseDouble(sellingPrice.getText());
                            double d2 = Double.parseDouble(buyingPrice.getText());
                            int i = Integer.parseInt(quantity.getText());
                            if (d1 < 0){
                                msg.setBounds(175, 370, 330, 20);
                                msg.setText("You Enter Selling Price with Negative Value");
                            }
                            else if (d2 < 0){
                                msg.setBounds(175, 370, 330, 20);
                                msg.setText("You Enter Buying Price with Negative Value");
                            }
                            else if (i < 0){
                                msg.setBounds(175, 370, 330, 20);
                                msg.setText("You Enter Product Quantity with Negative Value");
                            }
                            else if(i==0){
                                msg.setBounds(175, 370, 330, 20);
                                msg.setText("You Enter Product Quantity :  0");
                            }
                            else {
                                ConfirmPopUp pop = new ConfirmPopUp(productName.getText());
                                if (pop.getResult() == 0) {
                                    msg.setBounds(210, 370, 200, 20);
                                    msg.setText(insertProcess());
                                } else {
                                    dateLblValue.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
                                }
                            }
                        }
                        catch (NumberFormatException nfe){
                            msg.setBounds(210, 370, 210, 20);
                            msg.setText("Check Your Data Again Please!!!");
                        }
                    }
                }
            }
        });
    }
    private void jTextFieldKeyTyped(java.awt.event.KeyEvent evt){
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)){
            evt.consume();
        }
    }

    private String insertProcess(){
        return DatabaseHelper.insertData(
                new Product(
                      productName.getText(),
                      Double.parseDouble(sellingPrice.getText()),
                      Double.parseDouble(buyingPrice.getText()),
                      Integer.parseInt(quantity.getText()),
                      new Date()
            )
        );
    }

}