//151417-Gideon Kiprotich
//BICS 2.


package com.java;
import java.sql.*;
import com.java.Home;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame  {

    Connection connection;
    final String JDBC_CONNECTOR = "com.mysql.cj.jdbc.Driver";
    final String DB_URL = "jdbc:mysql://localhost:3306/registration";
    final String USER = "root";
    final String PASSWORD = "";

    JLabel l1, l2, l3;
    JTextField tf1;
    JButton btn1, btn2;


    JPasswordField p1;

    public JFrame frame;

    public Login() {
        try {
            Class.forName(JDBC_CONNECTOR);
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        }catch (Exception e) {
            e.printStackTrace();
        }

        //initialize container properties
        setSize(700, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Login Page");

        //initialize GUI components
        l1 = new JLabel("Login Page");
        l1.setForeground(Color.blue);
        l1.setFont(new Font("Serif", Font.BOLD, 20));
        l2 = new JLabel("Username:");
        l3 = new JLabel("Password:");
        tf1 = new JTextField();
        p1 = new JPasswordField();
        btn1 = new JButton("Login");
        btn2 = new JButton("Register");

        //deciding location for the components since we have no layout
        l1.setBounds(300, 110, 400, 30);
        l2.setBounds(80, 160, 200, 30);
        l3.setBounds(80, 210, 200, 30);
        tf1.setBounds(300, 160, 200, 30);
        p1.setBounds(300, 210, 200, 30);
        btn1.setBounds(250, 270, 100, 30);
        btn2.setBounds(370, 270, 100, 30);

        //add to container
        add(l1);
        add(l2);
        add(l3);
        add(tf1);
        add(p1);
        add(btn1);
        add(btn2);

        //actions
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                RegisterButtonAction(ev);
            }
        });
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginButtonAction(e);
            }
        });



    }

    void RegisterButtonAction(ActionEvent e){
        new Registration().setVisible(true);

    }
    void LoginButtonAction(ActionEvent e) {
        String username = tf1.getText();
        String pass = p1.getText();

        try{
                String sql = "SELECT * FROM registration WHERE Name= ? AND Password=?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,username);
                preparedStatement.setString(2,pass);
                ResultSet resultSet=preparedStatement.executeQuery();
                if (resultSet.next()){
                    String upassword=resultSet.getString("Password");
                    if (upassword.equals(pass)){
                        JOptionPane.showMessageDialog(null, "Login successful");
                        new Home();
                        frame.setVisible(false);

                    }else {
                        JOptionPane.showMessageDialog(null,"Invalid password");
                    }

                }else {
                    JOptionPane.showMessageDialog(null,"Invalid password");
                }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }



    public static void main(String args[]) {

        Login login = new Login();
        login.setVisible(true);



        {


        }
    }
}


