package pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class wantedProductsPage extends JPanel {

    JFrame frame = new JFrame();
    Font headFont = new Font("Arial",Font.BOLD,12);

    JTextField[] headFields = new JTextField[4];
    JTextField[][] wantedProductsArr = new JTextField[10][5];
    JButton backBtn = new JButton("Back");
    JButton nextBtn = new JButton("->");
    JButton previousBtn = new JButton("<-");
    JTextField pageNumberTF = new JTextField();

    int currentPageNumber = 1, numberOfPages = 1;

    public wantedProductsPage(){


        buttonsAction();
        mainDesign();
        frameSettings();

    }

    private void buttonsAction() {
        backBtn.addActionListener((ActionEvent ae) ->{
            new Menu();
            frame.dispose();
        });
    }

    private void mainDesign(){

        backBtn.setBounds(30,572,80,20);

        nextBtn.setBounds(300,572,50,20);

        pageNumberTF.setBounds(255,572,40,20); //-45
        pageNumberTF.setText(currentPageNumber + " / " + numberOfPages);
        pageNumberTF.setEditable(false);
        pageNumberTF.setHorizontalAlignment(0);

        previousBtn.setBounds(200,572,50,20);

        int border = 0;
        nextBtn.setBorder(BorderFactory.createEmptyBorder(border,border,border,border));
        previousBtn.setBorder(BorderFactory.createEmptyBorder(border,border,border,border));

        headsDesign();
        fieldsDesign();
    }

    private void headsDesign(){
        for(int i = 0; i < 4; i++) {
            switch (i) {
                case 0 -> {
                    headFields[i] = new JTextField("CHECK");
                    headFields[i].setBounds(0, 0, 91, 50);
                }
                case 1 -> {
                    headFields[i] = new JTextField("Name");
                    headFields[i].setBounds(90, 0, 151, 50);
                }
                case 2 -> {
                    headFields[i] = new JTextField("Category");
                    headFields[i].setBounds(240, 0, 122, 50);
                }
                case 3 -> {
                    headFields[i] = new JTextField("Operation");
                    headFields[i].setBounds(360, 0, 200, 50);
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
            for(int j = 0; j < 5;j++) {

                wantedProductsArr[i][j] = new JTextField("");
                wantedProductsArr[i][j].setHorizontalAlignment(0);
                wantedProductsArr[i][j].setEditable(false);

                switch (j) {
                    case 0 -> {
                        wantedProductsArr[i][j].setBounds(0, 51 * (i + 1), 91, 48);
                        wantedProductsArr[i][j].setText(" _ ");
                    }
                    case 1 -> wantedProductsArr[i][j].setBounds(90, 51 * (i + 1), 151, 48);
                    case 2 -> wantedProductsArr[i][j].setBounds(240, 51 * (i + 1), 122, 48);
                    case 3 -> {
                        wantedProductsArr[i][j].setBounds(385, 51 * (i + 1), 70, 48);
                        wantedProductsArr[i][j].setText("UPDATE");
                        wantedProductsArr[i][j].setFont(new Font("Arial",Font.BOLD,12));
                        wantedProductsArr[i][j].setForeground(Color.green);
                    }
                    case 4 -> {
                        wantedProductsArr[i][j].setBounds(465, 51 * (i + 1), 70, 48);
                        wantedProductsArr[i][j].setText(" X ");
                        wantedProductsArr[i][j].setForeground(Color.red);
                    }
                }

                int x = i, y = j;
                wantedProductsArr[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        String proName = wantedProductsArr[x][1].getText();

                        if(y == 3){
                            System.out.println("UPDATE");
                        }else if(y == 4){
                            System.out.println("DELETE");
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
        for(int i = 0; i < 4;i++){
            frame.add(headFields[i]);
        }
        for(int i = 0; i < 10;i++){
            for(int j = 0; j < 5;j++) {
                frame.add(wantedProductsArr[i][j]);
            }
        }
        frame.add(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Wanted Products");
        frame.setSize(575,650);
        frame.setLocation(400,60);
        frame.setVisible(true);
        frame.setResizable(false);
    }

}
