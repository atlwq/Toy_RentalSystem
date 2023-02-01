package com.mysql;

import com.window.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Administrator_sql {
	static Statement sta;
	static void con() {
		try {
			String sd = "com.mysql.cj.jdbc.Driver";
			Class.forName(sd);
			
			String url = "jdbc:mysql://localhost:3306/toy_manage";
			Connection con = DriverManager.getConnection(url,"root","lwq788795");
			//创建语句对象
			sta=con.createStatement();
		}catch (ClassNotFoundException e) {
			// TODO: handle exception
		}catch(SQLException e) {
			
		}
	}
	public static void show() {
		con();
		try {
			String sql_s = "select * from user";
			ResultSet r2 = sta.executeQuery(sql_s);
			Administrator.model.setNumRows(0);//将表格数据置为0
			String[] data = new String[5];
			while(r2.next()) {
				data[0] = r2.getString(1);
				data[1] = r2.getString(2);
				data[2] = r2.getString(3);
				data[3] = r2.getString(4);
				data[4] = r2.getString(5);
				Administrator.model.addRow(data);
			}
		}
		catch (SQLException e) {
			// TODO: handle exception
		}
		
	}
	
	public static void add() {
		con();
		try {
			String userName = Administrator.user_nameField.getText();
			String userId = Administrator.user_idField.getText();
			String userPassword = Administrator.user_passwordField.getText();
			String userIdentity =Administrator.user_identityField.getText();
			String userApply =Administrator.user_applyField.getText();;
			String sql_A = "INSERT INTO user VALUES('"+userName+"', '"+userId+"', '"+userPassword+"','"+userIdentity+"','"+userApply+"')";
			sta.executeUpdate(sql_A);
			JOptionPane.showMessageDialog(null, "用户信息增加成功！！！", "温馨提示", JOptionPane.WARNING_MESSAGE);
		}
		catch (SQLException e) {
			// TODO: handle exception
		}
		
	}
	
	
	public static void modify() {
		con();
		try {
			
			String userName = Administrator.user_nameField.getText();
			String userId = Administrator.user_idField.getText();
			String userPassword = Administrator.user_passwordField.getText();
			String userIdentity =Administrator.user_identityField.getText();
			String userApply =Administrator.user_applyField.getText();
			
			
			
			if(userName.equals("")==false) {
				String sql_M1 = "update user set name = '"+userName+"' where account = '"+userId+"'";
				sta.executeUpdate(sql_M1);
			}
			else {
				
			}
			if(userPassword.equals("")==false) {
				String sql_M2 = "update user set password = '"+userPassword+"' where account = '"+userId+"'";
				sta.executeUpdate(sql_M2);
			}
			else {
				
			}
			if(userIdentity.equals("")==false) {
				String sql_M3 = "update user set identity = '"+userIdentity+"' where account = '"+userId+"'";
				sta.executeUpdate(sql_M3);
			}
			else {
				
			}
			if(userApply.equals("")==false) {
				String sql_M4 = "update user set apply = '"+userApply+"' where account = '"+userId+"'";
				sta.executeUpdate(sql_M4);
			}
			else {
				
			}
			JOptionPane.showMessageDialog(null, "用户信息修改成功！！！", "温馨提示", JOptionPane.WARNING_MESSAGE);
			show();
		}
		
		
		catch (SQLException e) {
			
		}
		
	}
	
	public static void select() {
		con();
		try {
			String userId = Administrator.user_idField.getText();
			String sql_s = "select * from user where account= '"+userId+"'";
			ResultSet r2 = sta.executeQuery(sql_s);
			Administrator.model.setNumRows(0);//将表格数据置为0
			String[] data = new String[5];
			while(r2.next()) {
				data[0] = r2.getString(1);
				data[1] = r2.getString(2);
				data[2] = r2.getString(3);
				data[3] = r2.getString(4);
				data[4] = r2.getString(5);
				Administrator.model.addRow(data);
		}
		}
		catch (SQLException e) {
			// TODO: handle exception
		}
		
		
	
	}
}

