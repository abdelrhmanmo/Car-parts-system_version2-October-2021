package pages;

import Database.databaseOperations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoiceFramePage extends JPanel {

    int productNumber;

    JFrame frame = new JFrame();
    JButton updateProductData = new JButton("Update Product Data"),
            sellProduct = new JButton("Sell Product"),
            cancel = new JButton("Cancel");
    JLabel productName = new JLabel();

    public ChoiceFramePage(int productNumber){
        this.productNumber = productNumber;
        buttonsActions();
        Design();
        frameSettings();
    }

    private void buttonsActions() {
        updateProductData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new UpdateProductPage(productNumber);
            }
        });
        sellProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new SellPage(productNumber);
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new showProductsPage(400,60);
            }
        });

    }
    private void Design(){

        productName.setText("Product name: "+databaseOperations.data.get(productNumber).getName());
        productName.setForeground(Color.BLACK);
        productName.setBounds(50,20,750,35);
        updateProductData.setBounds(300,90,160,35);
        sellProduct.setBounds(300,180,160,35);
        cancel.setBounds(300,270,160,35);

    }
    private void frameSettings(){

        frame.add(sellProduct);
        frame.add(updateProductData);
        frame.add(cancel);
        frame.add(productName);
        frame.add(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Choice");
        frame.setSize(800,420);
        frame.setLocation(500,50);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}