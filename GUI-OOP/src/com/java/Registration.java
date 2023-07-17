//151417-Gideon Kiprotich
//BICS 2.1D

package com.java;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
//import java.awt.*;
//import java.sql.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Registration extends JFrame implements ActionListener
{
    public JFrame frame;
    JLabel l1, l2, l3, l4, l5, l6, l7, l8,l9,l10;
    JTextField tf1, tf2, tf5, tf6, tf7;
    JButton btn1, btn2, btn3;
    JPasswordField p1, p2;
    // A group of radio buttons
    // necessary to only allow one radio button to be selected at the same time.
    CheckboxGroup radioGroup;
    // The radio buttons to be selected
    Checkbox radio1;
    Checkbox radio2;
    // An independant selection box
    //JCheckBox option1, option2, option3;

    public Registration()
    {
        //setVisible(true);
        setSize(700, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Registration Form ");

        l1 = new JLabel("Registration Form:");
        l1.setForeground(Color.blue);
        l1.setFont(new Font("Serif", Font.BOLD, 20));

        l2 = new JLabel("Name:");
        l3 = new JLabel("Email Address:");
        l4 = new JLabel("Create Password:");
        l5 = new JLabel("Confirm Password:");
        l6 = new JLabel("Nationality:");
        l7 = new JLabel("State:");
        l8 = new JLabel("Phone No:");
        l9 = new JLabel("Gender:");
        l10= new JLabel("Course:");
        tf1 = new JTextField();
        tf2 = new JTextField();
        p1 = new JPasswordField();
        p2 = new JPasswordField();
        tf5 = new JTextField();
        tf6 = new JTextField();
        tf7 = new JTextField();

        // initialize the radio buttons group
        radioGroup = new CheckboxGroup();
        // first radio button. Gives the label text, tells to which
        // group it belongs and sets the default state (unselected)
        radio1 = new Checkbox("Male", radioGroup, false);
        // same but selected
        radio2 = new Checkbox("Female", radioGroup,  false);

        /*Label and state of the checkbox

        option1=new JCheckBox("Course 1");
        option2=new JCheckBox("Course 2");
        option3=new JCheckBox("Course 3");*/

        btn1 = new JButton("Submit");
        btn2 = new JButton("Clear");
        btn3 = new JButton("Back");



        l1.setBounds(250, 30, 400, 30);
        l2.setBounds(80, 70, 200, 30);
        l3.setBounds(80, 110, 200, 30);
        l4.setBounds(80, 150, 200, 30);
        l5.setBounds(80, 190, 200, 30);
        l6.setBounds(80, 230, 200, 30);
        l7.setBounds(80, 270, 200, 30);
        l8.setBounds(80, 310, 200, 30);
        l9.setBounds(80, 350, 200, 30);
        l10.setBounds(80, 390, 200, 30);
        tf1.setBounds(300, 70, 200, 30);
        tf2.setBounds(300, 110, 200, 30);
        p1.setBounds(300, 150, 200, 30);
        p2.setBounds(300, 190, 200, 30);
        tf5.setBounds(300, 230, 200, 30);
        tf6.setBounds(300, 270, 200, 30);
        tf7.setBounds(300, 310, 200, 30);
        btn1.setBounds(100, 450, 100, 30);
        btn2.setBounds(250, 450, 100, 30);
        btn3.setBounds(400, 450, 100, 30);
        radio1.setBounds(300, 350, 100, 30);
        radio2.setBounds(400, 350, 100, 30);
        //option1.setBounds(300, 390, 100, 30);
        //option2.setBounds(400, 390, 100, 30);
        //option3.setBounds(500, 390, 100, 30);

        add(l1);
        add(l2);
        add(tf1);
        add(l3);
        add(tf2);
        add(l4);
        add(p1);
        add(l5);
        add(p2);
        add(l6);
        add(tf5);
        add(l7);
        add(tf6);
        add(l8);
        add(tf7);
        add(btn1);
        add(btn2);
        add(btn3);
        add(l9);
        add(radio1);
        add(radio2);
        add(l10);
        //add(option1);
        //add(option2);
        //add(option3);

        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
    }

    public void registerUser(){
        String Name = tf1.getText();
        String Email = tf2.getText();
        String Password = String.valueOf(p1.getPassword());
        String ConfirmPassword = String.valueOf(p2.getPassword());
        String Country = tf5.getText();
        String State = tf6.getText();

        if (Name.isEmpty() || Email.isEmpty() || Password.isEmpty() || ConfirmPassword.isEmpty() || Password.isEmpty() || ConfirmPassword.isEmpty() || Country.isEmpty() || State.isEmpty()){
            JOptionPane.showMessageDialog(null,"Please fill out all required info", "Error", JOptionPane.ERROR_MESSAGE, null);
        } else if (Password.equals(ConfirmPassword)) {
            JOptionPane.showMessageDialog(null,"Registration successfull","Success", JOptionPane.INFORMATION_MESSAGE);

            Login b = new Login();
            //b.frame.setVisible(true);
            frame.setVisible(false);
        }
        else {
            JOptionPane.showMessageDialog(null,"Password does not match", "Error", JOptionPane.ERROR_MESSAGE);

            p1.setText("");
            p2.setText("");
        }
    }
    public void getConnection() {
        final String JDBC_CONNECTOR = "com.mysql.cj.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost:3306/registration";
        final String USER = "root";
        final String PASSWORD = "";

        String Name = tf1.getText();
        String Email = tf2.getText();
        String Password = String.valueOf(p1.getPassword());
        String ConfirmPassword = String.valueOf(p2.getPassword());
        String Country = tf5.getText();
        String State = tf6.getText();
        String Phone = tf7.getText();
        String Gender = "";

        if(radio1.isEnabled()) {
            Gender = "Male";
        } else if (radio2.isEnabled()) {
          Gender = "Female";
        }else{
            Gender = "No gender has been selected";
        }
        /*String Course ="";
        if(option1.isSelected() && option2.isSelected() && option3.isSelected()) {
            Course ="ICS, BBIT and BCOM";
        } else if (option1.isSelected() && option2.isSelected()) {
            Course="ICS and BBIT";
        } else if (option1.isSelected() && option3.isSelected()){
            Course = "ICS and BCOM";
        } else if (option2.isSelected() && option3.isSelected()) {
            Course="BBIT and BCOM";
        }else if (option1.isSelected()){
            Course="ICS";
        } else if (option2.isSelected()) {
            Course="BBIT";
        } else if (option3.isSelected()) {
            Course="BCOM";
        }else{
            Course="No course has been selected";
        }*/


        Connection connection;
        try {

            Class.forName(JDBC_CONNECTOR);
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Connected");
            String sql = "INSERT INTO registration (Name, Email, Password, ConfirmPassword, Country, State, Phone, Gender, Course)VALUES (?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Name);
            preparedStatement.setString(2, Email);
            preparedStatement.setString(3,Password);
            preparedStatement.setString(4,ConfirmPassword);
            preparedStatement.setString(5,Country);
            preparedStatement.setString(6,State);
            preparedStatement.setString(7,Phone);
            preparedStatement.setString(8,Gender);
            //preparedStatement.setString(9,Course);


            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null,"Record added");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
   public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == btn1) {

            getConnection();
            Login b = new Login();
            b.setVisible(true);
            this.setVisible(false);
        }
        if (e.getSource() == btn2) {
            tf1.setText("");
            tf2.setText("");
            p1.setText("");
            p2.setText("");
            tf5.setText("");
            tf6.setText("");
            tf7.setText("");
            radioGroup.setSelectedCheckbox(null);
            //option1.setSelected(false);
            //option2.setSelected(false);
            //option3.setSelected(false);


        }

        if (e.getSource() == btn2) {
            tf2.setText("");
            radioGroup.setSelectedCheckbox(null);

        }

        if(e.getSource()==btn2){
            p1.setText("");
            radioGroup.setSelectedCheckbox(null);
        }

        if(e.getSource()==btn2){
            p2.setText("");
            radioGroup.setSelectedCheckbox(null);
        }

        if(e.getSource()==btn2){
            tf5.setText("");
            radioGroup.setSelectedCheckbox(null);
        }

        if(e.getSource()==btn2){
            tf6.setText("");
            radioGroup.setSelectedCheckbox(null);
            //option1.setSelected(false);
            //option2.setSelected(false);
            //option3.setSelected(false);
        }

        if(e.getSource()==btn2){
            tf7.setText("");
            radioGroup.setSelectedCheckbox(null);
        }

        if (e.getSource() == btn3) {

            getConnection();
            Login b = new Login();
            b.setVisible(true);
            this.setVisible(false);
        }



    }
}




