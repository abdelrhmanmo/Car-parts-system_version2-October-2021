package pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import Database.*;

public class SellPage extends JPanel {
    JFrame frame = new JFrame();

    Font typingFont = new Font("Arial",Font.PLAIN,14);
    Font labelFont = new Font("Arial",Font.BOLD,14);

    JTextField searchField = new JTextField();
    JButton aboutBtn = new JButton("About");

    JButton backBtn = new JButton("Back");

    JLabel originalPriceLbl = new JLabel("-   Original Price : ");
    JLabel originalPriceValueLbl = new JLabel("00");

    JLabel sellingPriceLbl = new JLabel("-   Selling Price : ");
    JLabel sellingPriceValueLbl = new JLabel("00");

    JLabel quantityLbl = new JLabel("-   Quantity : ");
    JLabel quantityValueLbl = new JLabel("00");

    JLabel howManyLbl = new JLabel("How Many ?");
    JTextField howManyValueTextField = new JTextField();

    JLabel totalLbl = new JLabel("Total = ");
    JLabel totalValueLbl = new JLabel("00");
    JLabel LE = new JLabel("L.E");

    JButton submitBtn = new JButton("Submit");
    JLabel soldLbl = new JLabel();

    public SellPage(){
        typingRules();
        Design();
        frameSettings();
        buttonsAction();

    }


    private void buttonsAction(){
        submitBtn.addActionListener((ActionEvent ae) ->{
            if(searchField.getText().isEmpty() || howManyValueTextField.getText().isEmpty()) {

                soldLbl.setBounds(255,370,200,20);
                soldLbl.setText("Fill All Fields !");
            }else {
                sellingProcess();
            }
        });
        aboutBtn.addActionListener((ActionEvent ae) ->{
            if(searchField.getText().isEmpty()) {

                soldLbl.setBounds(235,370,200,20);
                soldLbl.setText("Enter Product Name !");
            }else {
                aboutProcess();
            }
        });

        backBtn.addActionListener((ActionEvent ae)->{
            new Menu();
            frame.dispose();
        });
    }


    private void Design(){

        searchField.setBounds(175,50,250,30);
        searchField.setMargin(new Insets(2,8,2,2));

        aboutBtn.setBounds(260,100,80,20);
        backBtn.setBounds(35,50,80,20);
        originalPriceLbl.setBounds(45,150,140,15);
        originalPriceValueLbl.setBounds(190,150,50,15);

        sellingPriceLbl.setBounds(45,190,120,15);
        sellingPriceValueLbl.setBounds(190,190,50,15);

        quantityLbl.setBounds(45,230,120,15);
        quantityValueLbl.setBounds(190,230,50,15);

        howManyLbl.setBounds(60,275,120,15);
        howManyValueTextField.setBounds(172,270,60,25);

        totalLbl.setBounds(360,272,120,15);
        totalValueLbl.setBounds(420,266,60,25);
        LE.setBounds(485,266,60,25);

        submitBtn.setBounds(260,330,80,20);
        soldLbl.setBounds(240,370,150,20);


        //Fonts
        searchField.setFont(typingFont);
        originalPriceLbl.setFont(labelFont);
        originalPriceValueLbl.setFont(labelFont);
        sellingPriceLbl.setFont(labelFont);
        sellingPriceValueLbl.setFont(labelFont);
        quantityLbl.setFont(labelFont);
        quantityValueLbl.setFont(labelFont);
        howManyLbl.setFont(labelFont);
        howManyValueTextField.setFont(typingFont);
        totalLbl.setFont(labelFont);
        totalValueLbl.setFont(labelFont);
        LE.setFont(labelFont);
        soldLbl.setFont(labelFont);

        //Text Color
        soldLbl.setForeground(Color.red);

    }

    private void jTextFieldKeyTyped(java.awt.event.KeyEvent evt){
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)){
            evt.consume();
        }
    }

    private void frameSettings(){

        frame.add(searchField);
        frame.add(aboutBtn);
        frame.add(originalPriceLbl);
        frame.add(originalPriceValueLbl);
        frame.add(sellingPriceLbl);
        frame.add(sellingPriceValueLbl);
        frame.add(quantityLbl);
        frame.add(quantityValueLbl);
        frame.add(howManyLbl);
        frame.add(howManyValueTextField);
        frame.add(totalLbl);
        frame.add(totalValueLbl);
        frame.add(LE);
        frame.add(submitBtn);
        frame.add(soldLbl);
        frame.add(backBtn);
        frame.add(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Sell Page");
        frame.setSize(600,460);
        frame.setLocation(560,80);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void typingRules(){

        howManyValueTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                jTextFieldKeyTyped(e);
                if (e.getKeyChar() == KeyEvent.VK_ENTER ) {
                    if(!(searchField.getText().isEmpty() || howManyValueTextField.getText().isEmpty())) {
                        sellingProcess();
                    }else{
                        soldLbl.setBounds(255,370,200,20);
                        soldLbl.setText("Fill All Fields !");
                    }
                }
            }
        });

        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER && !searchField.getText().isEmpty()) {
                    aboutProcess();
                }
            }
        });

    }

    private void sellingProcess(){
        String searchWord = searchField.getText();
        int productNumber = databaseOperations.search(searchWord);
        ConfirmPopUp pop = new ConfirmPopUp();//// بص على ال class ده ولو مفهمتش حاجه قولي

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String date;
        double total = 0.0;
        if (productNumber != -1) {
                    String howManyStr = howManyValueTextField.getText();
                    if (Integer.parseInt(howManyStr) <= databaseOperations.data.get(productNumber).getQuantity()) {
                        total = Integer.parseInt(howManyStr) *
                                databaseOperations.data.get(productNumber).getSoldPrice();
                        totalValueLbl.setText(String.valueOf(total));
                        pop = new ConfirmPopUp(String.valueOf(total) ,databaseOperations.data.get(productNumber).getName(),howManyStr);
                    }
                    else{
                        soldLbl.setBounds(220, 370, 160, 20);
                        soldLbl.setText("Not Available Quantity");
                    }
                    /*هنا بنتأكد اذا كان اختار انه عاوز يبيع فعلا ولا لا*/
                    if(pop.getResult() == 0){
                        soldLbl.setBounds(240, 370, 150, 20);
                        soldLbl.setText("Sold Successfully !");
                        date = formatter.format(new Date()); /*بنجيب تاريخ النهارده اللي اتباع فيه المنتج ده*/
                        /*بنصفر هنا كل حاجه بعدها*/
                        DatabaseHelper.updateProductQuantity(databaseOperations.data.get(productNumber) , productNumber , (databaseOperations.data.get(productNumber).getQuantity() - Integer.parseInt(howManyStr))); /*بنعدل الداتا بتاعت المنج*/
                        DatabaseHelper.insertSoldProductDetails(databaseOperations.data.get(productNumber).getName(),Integer.parseInt(howManyStr),date,total); /*بندخلها ل table المبيعات*/
                        quantityValueLbl.setText(String.valueOf(databaseOperations.data.get(productNumber).getQuantity()));
                        howManyValueTextField.setText("");
                        totalValueLbl.setText("");
                    }
                    else{
                        soldLbl.setText("");
                    }
            }else {
                soldLbl.setBounds(260, 370, 150, 20);
                soldLbl.setText("Not Found !");
            }
    }

    private void aboutProcess(){

        String searchWord = searchField.getText();
        int productNumber = databaseOperations.search(searchWord);
        if(productNumber != -1){
            originalPriceValueLbl.setText(String.valueOf(databaseOperations.data.get(productNumber).getBuyPrice()));
            sellingPriceValueLbl.setText(String.valueOf(databaseOperations.data.get(productNumber).getSoldPrice()));
            quantityValueLbl.setText(String.valueOf(databaseOperations.data.get(productNumber).getQuantity()));

        }else{
            soldLbl.setBounds(260,370,150,20);
            soldLbl.setText("Not Found !");
        }
    }

}
