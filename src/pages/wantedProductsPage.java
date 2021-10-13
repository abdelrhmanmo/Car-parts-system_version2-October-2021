package pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import Database.*;

public class wantedProductsPage extends JPanel {

    JFrame frame = new JFrame();
    Font headFont = new Font("Arial",Font.BOLD,12);

    JTextField[] headFields = new JTextField[5];
    public static  JTextField[][] wantedProductsArr = new JTextField[10][6];
    JButton backBtn = new JButton("Back");
    JButton exportBtn = new JButton("Export");
    JButton nextBtn = new JButton("->");
    JButton previousBtn = new JButton("<-");
    JTextField pageNumberTF = new JTextField();

    int currentPageNumber = 1, numberOfPages;
    int length, numOfRows = 0;

    public wantedProductsPage(){

        DatabaseHelper.getWantedProducts();

        length = databaseOperations.wantedProducts.toArray().length;
        numberOfPages = length / 10;
        if(length%10 != 0){
            numberOfPages++;
        }

        mainDesign();
        buttonsAction();
        dataTable(currentPageNumber);
        frameSettings();

    }

    private void buttonsAction() {
        backBtn.addActionListener((ActionEvent ae) ->{
            new Menu();
            frame.dispose();
        });

        nextBtn.addActionListener((ActionEvent ae) -> nextPageOfData()
        );

        previousBtn.addActionListener((ActionEvent ae) -> previousPageOfData()
        );
        exportBtn.addActionListener((ActionEvent ae)-> {
            if(new ConfirmPopUp(frame, "", 4).getResult() == 0) DatabaseHelper.exportAllWantedData(getTodayDateString());
        });
    }

    private void dataTable(int currentPageNumber){

        int productNumber = (currentPageNumber -1) *10 - 1;

        if(length >= currentPageNumber*10){
            numOfRows = 10;
        }else{
            numOfRows = length - (currentPageNumber - 1) * 10;
        }

        deleteData();
        for(int i = 0; i < numOfRows;i++){
            productNumber++;
            for(int j = 0; j < 6;j++) {

                    switch (j) {
                        case 0 -> wantedProductsArr[i][j].setText(String.valueOf(databaseOperations.wantedProducts.get(productNumber).getQuantity()));
                        case 1 -> wantedProductsArr[i][j].setText(/*databaseOperations.wantedProducts.get(productNumber).getName().length() > 9 ?
                                databaseOperations.wantedProducts.get(productNumber).getName().substring(0,9).concat("...") :*/
                                databaseOperations.wantedProducts.get(productNumber).getName());
                        case 2 -> wantedProductsArr[i][j].setText(String.valueOf(databaseOperations.wantedProducts.get(productNumber).getCategory()));
                        case 5 -> wantedProductsArr[i][j].setText(String.valueOf(databaseOperations.wantedProducts.get(productNumber).getBuyPrice()));
                    }

            }

        }


    }

    private void deleteData(){      //when its less than 10 rows
        for(int i = 0; i < 10;i++){
            for(int j = 0; j < 6;j++) {
                switch (j) {
                    case 0 -> wantedProductsArr[i][j].setText(" _ ");
                    case 1 , 2 , 5 -> wantedProductsArr[i][j].setText(" ");
                }
            }

        }
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

    private void mainDesign(){

        backBtn.setBounds(30,572,80,20);

        nextBtn.setBounds(300 + 150,572,50,20);

        pageNumberTF.setBounds(255 + 150,572,40,20); //-45
        pageNumberTF.setText(currentPageNumber + " / " + numberOfPages);
        pageNumberTF.setEditable(false);
        pageNumberTF.setHorizontalAlignment(0);

        previousBtn.setBounds(200 + 150,572,50,20);

        exportBtn.setBounds(165,572,75,20);

        int border = 0;
        nextBtn.setBorder(BorderFactory.createEmptyBorder(border,border,border,border));
        previousBtn.setBorder(BorderFactory.createEmptyBorder(border,border,border,border));

        headsDesign();
        fieldsDesign();
    }

    private void headsDesign(){
        for(int i = 0; i < 5; i++) {
            switch (i) {
                case 0 -> {
                    headFields[i] = new JTextField("");
                    headFields[i].setBounds(0, 0, 91, 50);
                }
                case 1 -> {
                    headFields[i] = new JTextField("Name");
                    headFields[i].setBounds(90, 0, 151 + 250, 50);
                }
                case 2 -> {
                    headFields[i] = new JTextField("Category");
                    headFields[i].setBounds(240 + 250, 0, 122 + 50, 50);
                }
                case 3 -> {
                    headFields[i] = new JTextField("Operation");
                    headFields[i].setBounds(360 + 300, 0, 175, 50);
                }
                case 4 ->{
                    headFields[i] = new JTextField("        Price");
                    headFields[i].setBounds(360 + 460, 0, 96, 50);
                }
            }
            headFields[i].setBackground(Color.gray);
            headFields[i].setHorizontalAlignment(0);
            headFields[i].setEditable(false);
            headFields[i].setFont(headFont);

        }
    }

    private void fieldsDesign(){
        for(int i = 0; i < 10;i++){
            for(int j = 0; j < 6;j++) {

                wantedProductsArr[i][j] = new JTextField("");
                wantedProductsArr[i][j].setHorizontalAlignment(0);
                wantedProductsArr[i][j].setEditable(false);

                switch (j) {
                    case 0 -> {
                        wantedProductsArr[i][j].setBounds(0, 51 * (i + 1), 91, 48);
                        wantedProductsArr[i][j].setText(" _ ");
                    }
                    case 1 -> wantedProductsArr[i][j].setBounds(90, 51 * (i + 1), 151 + 250, 48);
                    case 2 -> wantedProductsArr[i][j].setBounds(240 + 250, 51 * (i + 1), 122 + 50, 48);
                    case 3 -> {
                        wantedProductsArr[i][j].setBounds(385 + 300, 51 * (i + 1), 70, 48);
                        wantedProductsArr[i][j].setText("UPDATE");
                        wantedProductsArr[i][j].setFont(new Font("Arial",Font.BOLD,12));
                        wantedProductsArr[i][j].setForeground(Color.green);
                    }
                    case 4 -> {
                        wantedProductsArr[i][j].setBounds(465 + 300, 51 * (i + 1), 70, 48);
                        wantedProductsArr[i][j].setText(" X ");
                        wantedProductsArr[i][j].setForeground(Color.red);
                    }
                    case 5->{
                        wantedProductsArr[i][j].setBounds(545 + 300, 51 * (i + 1), 70, 48);
                        wantedProductsArr[i][j].setText(" _ ");
                        wantedProductsArr[i][j].setForeground(Color.black);
                    }
                }

                int x = i, y = j;
                wantedProductsArr[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                            if(x < databaseOperations.wantedProducts.toArray().length){
                            String proName = wantedProductsArr[x][1].getText();
                            String proCategory = wantedProductsArr[x][2].getText();
                            int oldQuantity = Integer.parseInt(wantedProductsArr[x][0].getText());

                                if (y == 3) {
                                new increaseInQuantityFrame(proName, proCategory, oldQuantity, x);
                                frame.dispose();
                            } else if (y == 4) {
                                new ConfirmPopUp(frame, proName,proCategory,x,true);
                            }
                        }
                    }
                });

            }

        }
    }

    private void frameSettings(){

        frame.add(backBtn);
        frame.add(nextBtn);
        frame.add(previousBtn);
        frame.add(pageNumberTF);
        for(int i = 0; i < 5;i++){
            frame.add(headFields[i]);
        }
        for(int i = 0; i < 10;i++){
            for(int j = 0; j < 6;j++) {
                frame.add(wantedProductsArr[i][j]);
            }
        }
        frame.add(exportBtn);
        frame.add(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Wanted Products");
        frame.setSize(575 + 360,650);
        frame.setLocation(400,60);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private String getTodayDateString() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(new Date());
    }

}
