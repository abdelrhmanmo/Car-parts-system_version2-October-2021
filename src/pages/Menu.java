package pages;

import javax.swing.*;
import java.awt.event.ActionEvent;

//Edit
public class Menu extends JPanel {
    JFrame frame = new JFrame();
    JButton addBtn = new JButton("Add Products"),
            sellBtn = new JButton("Sell Products"),
            wantedBtn = new JButton("Wanted Products"),
            showBtn = new JButton("Show Products"),
            updateBtn = new JButton("Update Data"),
            reportsBtn = new JButton("Sales Reports");

    public Menu (){
        buttonsActions();
        Design();
        frameSettings();
    }
    private void buttonsActions(){
        sellBtn.addActionListener((ActionEvent ae) ->{
            new SellPage();
            frame.dispose();
        });
        addBtn.addActionListener((ActionEvent ae) ->{
            new AddProductPage();
            frame.dispose();
        });
        wantedBtn.addActionListener((ActionEvent ae) ->{
            System.out.println("Wanted Button !!");
        });
        showBtn.addActionListener((ActionEvent ae) ->{
            System.out.println("SHOW Button !!");
        });
        updateBtn.addActionListener((ActionEvent ae) ->{
            new UpdateProductPage();
            frame.dispose();
        });
        reportsBtn.addActionListener((ActionEvent ae) ->{
            System.out.println("Report Button !!");
        });
    }
    private void Design(){

        int xPositionForBtns = 50, yPositionForBtns = 50;
        final int widthForBtns = 170, heightForBtns = 30;

        sellBtn.setBounds(xPositionForBtns,yPositionForBtns,widthForBtns,heightForBtns);
        addBtn.setBounds(xPositionForBtns,yPositionForBtns*3,widthForBtns,heightForBtns);
        wantedBtn.setBounds(xPositionForBtns,yPositionForBtns*5,widthForBtns,heightForBtns);

        showBtn.setBounds(xPositionForBtns*7,yPositionForBtns,widthForBtns,heightForBtns);
        updateBtn.setBounds(xPositionForBtns*7,yPositionForBtns*3,widthForBtns,heightForBtns);
        reportsBtn.setBounds(xPositionForBtns*7,yPositionForBtns*5,widthForBtns,heightForBtns);

    }
    private void frameSettings(){

        frame.add(sellBtn);
        frame.add(addBtn);
        frame.add(wantedBtn);
        frame.add(showBtn);
        frame.add(updateBtn);
        frame.add(reportsBtn);
        frame.add(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("System");
        frame.setSize(595,400);
        frame.setLocation(500,50);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}