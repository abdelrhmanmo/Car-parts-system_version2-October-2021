package pages;

import Database.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;


public class showProductsPage extends JPanel{

    int numOfPages = 0;
    JFrame frame = new JFrame();
    Font labelFont = new Font("Arial",Font.PLAIN,15);
    Font headFont = new Font("Arial",Font.BOLD,12);

    int numOfRows;

    JTextField[][] productsArr = new JTextField[databaseOperations.data.toArray().length][5];
    JTextField[] headsTF = new JTextField[5];
    JButton backBtn = new JButton("Back");
    JButton nextBtn = new JButton("->");
    JButton previousBtn = new JButton("<-");
    JLabel numberOfPagesLbl = new JLabel("1/55");


    public showProductsPage(){

        if(databaseOperations.data.toArray().length < 10){
            numOfRows = databaseOperations.data.toArray().length;
        }else{
            numOfRows = 10;
        }

        for(int i = 0*10; i < numOfRows; i++){
            for(int j = 0; j < 5;j++) {
                productsArr[i][j] = new JTextField("");
            }
        }
        for(int i = 0; i < 5;i++){
            headsTF[i] = new JTextField("");
        }
        dataTable(numOfPages);
        Design();
        buttonsAction();
        frameSettings();
    }

    private void dataTable(int numOfPage){

        for(int i = numOfPage; i < numOfRows;i++){

            int j = 0;
            productsArr[i][j].setText(databaseOperations.data.get(i).getName());
            productsArr[i][j].setBounds(0, 51 * (i + 1), 149, 48);
            productsArr[i][j].setHorizontalAlignment(0);
            productsArr[i][j].setEditable(false);
            //Fonts
            productsArr[i][j].setFont(labelFont);

            j = 1;
            productsArr[i][j].setText(String.valueOf(databaseOperations.data.get(i).getBuyPrice()));
            productsArr[i][j].setBounds(150, 51 * (i + 1), 92, 48);
            productsArr[i][j].setHorizontalAlignment(0);
            productsArr[i][j].setEditable(false);
            //Fonts
            productsArr[i][j].setFont(labelFont);

            j = 2;
            productsArr[i][j].setText(String.valueOf(databaseOperations.data.get(i).getSoldPrice()));
            productsArr[i][j].setBounds(240, 51 * (i + 1), 92, 48);
            productsArr[i][j].setHorizontalAlignment(0);
            productsArr[i][j].setEditable(false);
            //Fonts
            productsArr[i][j].setFont(labelFont);

            j = 3;
            productsArr[i][j].setText(String.valueOf(databaseOperations.data.get(i).getQuantity()));
            productsArr[i][j].setBounds(330, 51 * (i + 1), 92, 48);
            productsArr[i][j].setHorizontalAlignment(0);
            productsArr[i][j].setEditable(false);
            //Fonts
            productsArr[i][j].setFont(labelFont);

            j = 4;
            productsArr[i][j].setText(String.valueOf(databaseOperations.data.get(i).getAddingToSystemDate()));
            productsArr[i][j].setBounds(420, 51 * (i + 1), 165, 48);
            productsArr[i][j].setHorizontalAlignment(0);
            productsArr[i][j].setEditable(false);
            //Fonts
            productsArr[i][j].setFont(labelFont);

        }


    }

    private void Design(){

        backBtn.setBounds(30,572,80,20);
        nextBtn.setBounds(300,572,50,20);
        previousBtn.setBounds(200,572,50,20);
        numberOfPagesLbl.setBounds(260,572,50,20);

        int border = 0;
        nextBtn.setBorder(BorderFactory.createEmptyBorder(border,border,border,border));
        previousBtn.setBorder(BorderFactory.createEmptyBorder(border,border,border,border));
        //backBtn.setForeground(Color.BLUE);

        for(int i = 0; i < 5; i++) {
            headsTF[i].setBackground(Color.gray);
            switch (i) {
                case 0 -> {
                    headsTF[i].setText("Name");
                    headsTF[i].setBounds(0, 0, 151, 50);
                }
                case 1 -> {
                    headsTF[i].setText("Original Price");
                    headsTF[i].setBounds(150, 0, 92, 50);
                }
                case 2 -> {
                    headsTF[i].setText("Selling Price");
                    headsTF[i].setBounds(240, 0, 92, 50);
                }
                case 3 -> {
                    headsTF[i].setText("Quantity");
                    headsTF[i].setBounds(330, 0, 92, 50);
                }
                case 4 -> {
                    headsTF[i].setText("Date");
                    headsTF[i].setBounds(420, 0, 165, 50);
                }
            }

            headsTF[i].setHorizontalAlignment(0);
            headsTF[i].setEditable(false);
            //Fonts
            if(i != 1 && i != 2 && i != 3) {
                headsTF[i].setFont(labelFont);
            }else {
                headsTF[i].setFont(headFont);
            }
        }

        //Fonts

    }

    private void buttonsAction(){
        backBtn.addActionListener((ActionEvent ae)->{
            new Menu();
            frame.dispose();
        });
        nextBtn.addActionListener((ActionEvent ae)->{
            System.out.println("Next");
            numOfPages++;
            dataTable(numOfPages);
        });
        previousBtn.addActionListener((ActionEvent ae)->{
            System.out.println("Previous");
        });
    }

    private void frameSettings(){

        //frame.add(dataTableLbl);
        frame.add(backBtn);
        frame.add(nextBtn);
        frame.add(previousBtn);
        frame.add(numberOfPagesLbl);
        for(int i = 0; i < 5;i++){
            frame.add(headsTF[i]);
        }
        for(int i = 0; i < numOfRows;i++){
            for(int j = 0; j < 5;j++) {
                frame.add(productsArr[i][j]);
            }
        }
        frame.add(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Products Page");
        frame.setSize(600,650);
        frame.setLocation(400,60);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public void paint(Graphics g){

        for(int i = 1; i < 4; i++) {
            switch (i){
                case 1:
                    g.drawLine(150, 0, 150, 550);
                case 2:
                    g.drawLine(240, 0, 240, 550);
                case 3:
                    g.drawLine(330, 0, 330, 550);
                case 4:
                    g.drawLine(420, 0, 420, 550);
            }
        }

    }


}
