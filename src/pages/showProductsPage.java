package pages;

import Database.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;


public class showProductsPage extends JPanel{

    int numberOfPages = 0;
    int currentPageNumber = 1;
    int numOfRows = 0, length = 0;

    JFrame frame = new JFrame();
    Font labelFont = new Font("Arial",Font.PLAIN,15);
    Font headFont = new Font("Arial",Font.BOLD,12);

    JTextField[][] productsArr = new JTextField[databaseOperations.data.toArray().length][5];
    JTextField[] headsTF = new JTextField[5];
    JButton backBtn = new JButton("Back");
    JButton nextBtn = new JButton("->");
    JButton previousBtn = new JButton("<-");
    JTextField pageNumberTF = new JTextField();


    public showProductsPage(){
        length = databaseOperations.data.toArray().length;

        numberOfPages = length / 10;
        if(length%10 != 0){
            numberOfPages++;
        }


        dataTableDesign();
        dataTable(1);   //initial page

        Design();
        buttonsAction();
        frameSettings();
    }

    private void dataTableDesign(){

        for(int i = 0; i < 10;i++){
            for(int j = 0; j < 5;j++) {
                productsArr[i][j] = new JTextField("");
                switch (j) {
                    case 0:
                        productsArr[i][j].setBounds(0, 51 * (i + 1), 149, 48);
                        productsArr[i][j].setHorizontalAlignment(0);
                        productsArr[i][j].setEditable(false);
                        //Fonts
                        productsArr[i][j].setFont(labelFont);
                        break;

                    case 1:
                        productsArr[i][j].setBounds(150, 51 * (i + 1), 92, 48);
                        productsArr[i][j].setHorizontalAlignment(0);
                        productsArr[i][j].setEditable(false);
                        //Fonts
                        productsArr[i][j].setFont(labelFont);
                        break;

                    case 2:
                        productsArr[i][j].setBounds(240, 51 * (i + 1), 92, 48);
                        productsArr[i][j].setHorizontalAlignment(0);
                        productsArr[i][j].setEditable(false);
                        //Fonts
                        productsArr[i][j].setFont(labelFont);
                        break;

                    case 3:
                        productsArr[i][j].setBounds(330, 51 * (i + 1), 92, 48);
                        productsArr[i][j].setHorizontalAlignment(0);
                        productsArr[i][j].setEditable(false);
                        //Fonts
                        productsArr[i][j].setFont(labelFont);
                        break;

                    case 4:
                        productsArr[i][j].setBounds(420, 51 * (i + 1), 165, 48);
                        productsArr[i][j].setHorizontalAlignment(0);
                        productsArr[i][j].setEditable(false);
                        //Fonts
                        productsArr[i][j].setFont(labelFont);
                        break;
                }

            }

        }


    }

    private void dataTable(int currentPageNumber){

        //currentPageNumber--;
        int productNumber = (currentPageNumber -1) *10 - 1;

        if(length >= currentPageNumber*10){
            numOfRows = 10;
        }else{
            numOfRows = length - (currentPageNumber - 1) * 10;
        }
        System.out.println(numOfRows);

        for(int i = 0; i < numOfRows;i++){
            productNumber++;
            for(int j = 0; j < 5;j++) {

                switch (j) {
                    case 0:
                        productsArr[i][j].setText(databaseOperations.data.get(productNumber).getName());
                        break;

                    case 1:
                        productsArr[i][j].setText(String.valueOf(databaseOperations.data.get(productNumber).getBuyPrice()));
                        break;


                    case 2:
                        productsArr[i][j].setText(String.valueOf(databaseOperations.data.get(productNumber).getSoldPrice()));
                        break;

                    case 3:
                        productsArr[i][j].setText(String.valueOf(databaseOperations.data.get(productNumber).getQuantity()));
                        break;

                    case 4:
                        productsArr[i][j].setText(String.valueOf(databaseOperations.data.get(productNumber).getAddingToSystemDate()));
                        break;
                }
            }

        }
        if(numOfRows < 10){
            deleteOverData(numOfRows);
        }


    }

    private void deleteOverData(int numOfRows){      //when its less than 10 rows
        for(int i = numOfRows; i < 10;i++){
            for(int j = 0; j < 5;j++) {
                productsArr[i][j].setText("");
            }

        }
    }

    private void Design(){

        backBtn.setBounds(30,572,80,20);
        nextBtn.setBounds(300,572,50,20);
        previousBtn.setBounds(200,572,50,20);
        pageNumberTF.setBounds(255,572,40,20);
        pageNumberTF.setText(currentPageNumber + " / " + numberOfPages);
        pageNumberTF.setEditable(false);
        pageNumberTF.setHorizontalAlignment(0);

        int border = 0;
        nextBtn.setBorder(BorderFactory.createEmptyBorder(border,border,border,border));
        previousBtn.setBorder(BorderFactory.createEmptyBorder(border,border,border,border));
        //backBtn.setForeground(Color.BLUE);

        for(int i = 0; i < 5; i++) {
            switch (i) {
                case 0 -> {
                    headsTF[i] = new JTextField("Name");
                    headsTF[i].setBounds(0, 0, 151, 50);
                }
                case 1 -> {
                    headsTF[i] = new JTextField("Original Price");
                    headsTF[i].setBounds(150, 0, 92, 50);
                }
                case 2 -> {
                    headsTF[i] = new JTextField("Selling Price");
                    headsTF[i].setBounds(240, 0, 92, 50);
                }
                case 3 -> {
                    headsTF[i] = new JTextField("Quantity");
                    headsTF[i].setBounds(330, 0, 92, 50);
                }
                case 4 -> {
                    headsTF[i] = new JTextField("Date");
                    headsTF[i].setBounds(420, 0, 165, 50);
                }
            }
            headsTF[i].setBackground(Color.gray);
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
            nextPageOfData();
        });
        previousBtn.addActionListener((ActionEvent ae)->{
            previousPageOfData();
        });
    }

    private void nextPageOfData(){

        if(currentPageNumber < numberOfPages){
            currentPageNumber++;
            pageNumberTF.setText(currentPageNumber + " / " + numberOfPages);
        }

        dataTable(currentPageNumber);

    }

    private void previousPageOfData(){
        if(currentPageNumber > 1){
            currentPageNumber--;
            pageNumberTF.setText(currentPageNumber + " / " + numberOfPages);
        }
        dataTable(currentPageNumber);

    }

    private void frameSettings(){

        frame.add(backBtn);
        frame.add(nextBtn);
        frame.add(previousBtn);
        frame.add(pageNumberTF);
        for(int i = 0; i < 5;i++){
            frame.add(headsTF[i]);
        }
        for(int i = 0; i < 10;i++){
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
