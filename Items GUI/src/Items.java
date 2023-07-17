/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

/**
 *
 * @author dim
 */

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Items extends JFrame implements ActionListener{
    Connection conn;
    Statement stmt;
    ResultSet rs;
    JButton save,delete,update,newrec,back,next,exit;
    JTextField itemID,item,locationFound,dateFound,color,additionalInfo;
    JComboBox type;
    JLabel students,surnamelbl,othernamelbl,nationalidlbl,emaillbl,phonelbl,genderlbl,studentidlbl;

    public void DoConnect(){
        try{
//STEP 1: Load the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");//JDBC driver name

            //Database Credentials
            String host = "jdbc:mysql://localhost/student"; //Database URL
            String uName = "root";
            String uPass = "";

//STEP 2: Create a connection
            conn = DriverManager.getConnection(host,uName,uPass);

//STEP 3: Create a statement object

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

//Step 4: Execute query

//            String SQL ="CREATE TABLE IF NOT EXISTS `students` (`student_id` int(10) NOT NULL AUTO_INCREMENT, `surname` varchar(50) NOT NULL, `othername` varchar(50) NOT NULL, `national_id` int(10) NOT NULL, `email` varchar(50) NOT NULL, `phone` int(10) NOT NULL, `gender` varchar(10) NOT NULL, PRIMARY KEY (`student_id`)) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;";
//            stmt.executeUpdate(SQL);
//            System.out.println("Items table created successfully!");
            String SQL = "Select * from students";
            rs = stmt.executeQuery(SQL);

//Step 5: Extract data from the result set/Process the results
            rs.next();
            itemID.setText(rs.getString("Item-ID"));
            item.setText(rs.getString("Item"));
            locationFound.setText(Integer.toString(rs.getInt("Location Found")));
            dateFound.setText(rs.getString("Date Found"));
            color.setText(Integer.toString(rs.getInt("Color")));
            type.setSelectedItem(rs.getString("Type"));
            additionalInfo.setText(rs.getString("Additional info"));
        }
        catch (SQLException | ClassNotFoundException err){
            JOptionPane.showMessageDialog(Items.this,err.getMessage());
        }
    }

    Items(){
        setDefaultCloseOperation(Items.EXIT_ON_CLOSE);
        setSize(500,500);
        setLayout(null);
        Dimension dim=Toolkit.getDefaultToolkit().getScreenSize(); this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

//Labels
        students=new JLabel("ITEMS"); students.setBounds(200,0,100,25); add(students);
        surnamelbl=new JLabel("Item-ID"); surnamelbl.setBounds(10,50,100,25); add(surnamelbl);
        othernamelbl=new JLabel("Item"); othernamelbl.setBounds(10,100,100,25); add(othernamelbl);
        nationalidlbl=new JLabel("Location found"); nationalidlbl.setBounds(10,150,100,25); add(nationalidlbl);
        emaillbl=new JLabel("Date found"); emaillbl.setBounds(10,200,100,25); add(emaillbl);
        phonelbl=new JLabel("Color"); phonelbl.setBounds(10,250,100,25); add(phonelbl);
        genderlbl=new JLabel("Type"); genderlbl.setBounds(10,300,100,25); add(genderlbl);
        studentidlbl=new JLabel("Additional info"); studentidlbl.setBounds(10,350,150,25); add(studentidlbl);

//Text Fields
        itemID=new JTextField(); itemID.setBounds(151,50,100,25); add(itemID);
        item=new JTextField(); item.setBounds(151,100,100,25); add(item);
        locationFound=new JTextField(); locationFound.setBounds(151,150,100,25); add(locationFound);
        dateFound=new JTextField(); dateFound.setBounds(151,200,100,25); add(dateFound);
        color=new JTextField(); color.setBounds(151,250,100,25); add(color);
        additionalInfo=new JTextField(); additionalInfo.setBounds(151,350,100,25); additionalInfo.setEditable(false); add(additionalInfo);

//Combo box
        type=new JComboBox(); type.setBounds(151,300,100,25);add(type); type.addItem("Stationery"); type.addItem("Clothing"); type.addItem("Personal");

//Buttons
        save=new JButton("Save"); save.setBounds(300,50,100,25); add(save); save.addActionListener(this);
        delete=new JButton("Delete"); delete.setBounds(300,100,100,25); add(delete); delete.addActionListener(this);
        update=new JButton("Update"); update.setBounds(300,150,100,25); add(update); update.addActionListener(this);
        newrec=new JButton("New"); newrec.setBounds(300,200,100,25); add(newrec); newrec.addActionListener(this);
        back=new JButton("Back"); back.setBounds(300,250,100,25); add(back); back.addActionListener(this);
        next=new JButton("Next"); next.setBounds(300,300,100,25); add(next); next.addActionListener(this);
        exit=new JButton("Exit"); exit.setBounds(300,400,100,25); add(exit); exit.addActionListener(this);

        DoConnect();
    }

    public static void main (String[] args){
        Items sts= new Items();
        sts.setVisible(true);
    }

    public void actionPerformed(ActionEvent evt){

//Save Button
        if(evt.getSource()==save){
            try{
                int itemId = Integer.parseInt(itemID.getText());
                //int phoneno = Integer.parseInt(phone.getText());
                stmt.executeUpdate("INSERT INTO items ( item_id, item, location_found, date_found, color, type) VALUES ('"+itemID.getText()+"','"+item.getText()+"','"+locationFound+"','"+dateFound.getText()+"','"+color+"','"+type.getSelectedItem()+"')");
                JOptionPane.showMessageDialog(Items.this, "Record saved!");
                Statement stmtshow;
                stmtshow = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                rs=stmtshow.executeQuery("select student_id from items where item_id='"+itemId+"'");
                rs.next();
                itemID.setText(rs.getString("item_id"));
                save.setEnabled(false);
            }
            catch(SQLException err){
                JOptionPane.showMessageDialog(Items.this, err.getMessage());
                System.out.println(err.getMessage());
            }
            catch(NumberFormatException ev){
                JOptionPane.showMessageDialog(Items.this, "Check that the Item ID number consists of numbers only.");
                System.out.println(ev.getMessage());
            }
        }

//Delete Button
        if(evt.getSource()==delete){
            try{
                stmt.executeUpdate("delete from students where student_id='"+itemID.getText()+"'");
                JOptionPane.showMessageDialog(Items.this, "Record deleted!");

                //Execute query
                rs=stmt.executeQuery("select * from students");
                rs.next();

//STEP 5: Extract data from result set
                itemID.setText(rs.getString("item_id"));
                item.setText(rs.getString("item"));
                locationFound.setText(Integer.toString(rs.getInt("location_found")));
                dateFound.setText(rs.getString("date_found"));
                color.setText(Integer.toString(rs.getInt("color")));
                type.setSelectedItem(rs.getString("type"));
                additionalInfo.setText(rs.getString("additional_info"));
            }
            catch(SQLException err){
                JOptionPane.showMessageDialog(Items.this, err.getMessage());
                System.out.println(err.getMessage());
            }
        }

//Update Button
        if(evt.getSource()==update){
            try{
                stmt.executeUpdate("update students set item_id='"+itemID.getText()+"',item='"+item.getText()+"',location_found='"+locationFound.getText()+"',date_found='"+dateFound.getText()+"',color='"+color.getText()+"',type='"+type.getSelectedItem()+"' WHERE additional_info ='"+additionalInfo.getText()+"'");
                JOptionPane.showMessageDialog(Items.this, "Record updated!");
            }
            catch(SQLException err){
                JOptionPane.showMessageDialog(Items.this, err.getMessage());
                System.out.println(err.getMessage());
            }
        }

//New Button
        if(evt.getSource()==newrec){
            itemID.setText("");
            item.setText("");
            locationFound.setText("");
            dateFound.setText("");
            color.setText("");
            type.setSelectedItem("");
            additionalInfo.setText("");
            save.setEnabled(true);
        }

//Back Button
        if(evt.getSource()==back){
            try {
                if(rs.previous()){
                    itemID.setText(rs.getString("item_id"));
                    item.setText(rs.getString("item"));
                    locationFound.setText(Integer.toString(rs.getInt("location_found")));
                    dateFound.setText(rs.getString("date_found"));
                    color.setText(Integer.toString(rs.getInt("color")));
                    type.setSelectedItem(rs.getString("type"));
                    additionalInfo.setText(rs.getString("additional_info"));
                }
                else{
                    rs.next();
                    JOptionPane.showMessageDialog(Items.this,"Start of File.");
                }
            }
            catch (SQLException ex) {
                JOptionPane.showMessageDialog(Items.this, ex.getMessage());
            }
        }

//Next Button
        if(evt.getSource()==next){
            try {
                if(rs.next()){
                    itemID.setText(rs.getString("item_id"));
                    item.setText(rs.getString("item"));
                    locationFound.setText(Integer.toString(rs.getInt("location_found")));
                    dateFound.setText(rs.getString("date_found"));
                    color.setText(Integer.toString(rs.getInt("color")));
                    type.setSelectedItem(rs.getString("type"));
                    additionalInfo.setText(rs.getString("additional_info"));
                }
                else{
                    rs.previous();
                    JOptionPane.showMessageDialog(Items.this,"End of File.");
                }
            }
            catch (SQLException ex) {
                JOptionPane.showMessageDialog(Items.this, ex.getMessage());
            }
        }

//Exit Button
        if(evt.getSource()==exit){
            System.exit(0);
        }
    }
}