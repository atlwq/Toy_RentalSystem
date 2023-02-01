package com.mysql;

import com.window.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Storekeeper_Sql {
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
			String sql_s = "select * from toy";
			ResultSet r = sta.executeQuery(sql_s);
			Storekeeper.model.setNumRows(0);//将表格数据置为0
			String[] data = new String[6];
			while(r.next()) {
				data[0] = r.getString(1);
				data[1] = r.getString(2);
				data[2] = r.getString(3);
				data[3] = r.getString(4);
				data[4] = r.getString(5);
				data[5] = r.getString(6);
				Storekeeper.model.addRow(data);
			}
		}
		catch (SQLException e) {
			// TODO: handle exception
		}
		
	}
	
	public static void add() {
		con();
		try {
			String toyId = Storekeeper.toy_idField.getText();
			String toyName = Storekeeper.toy_nameField.getText();
			String toyType = Storekeeper.toy_typeField.getText();
			String st = Storekeeper.toy_priceField.getText();
			double st1 = Double.parseDouble(st);
			String toyPrice = st1+"";
			double st2 = Double.parseDouble(st)*0.8;
			String toyVipPrice = st2+"";
			String toyCount = Storekeeper.toy_countField.getText();
			if(toyId.length()!=4) {
				JOptionPane.showMessageDialog(null, "玩具编号只能是4位字符！！！", "温馨提示", JOptionPane.WARNING_MESSAGE);
				Storekeeper.toy_idField.setText("");
			}
			else {
				String sql_A = "INSERT INTO toy VALUES('"+toyId+"', '"+toyName+"', '"+toyType+"', '"+toyPrice+"', '"+toyVipPrice+"', '"+toyCount+"')";
				sta.executeUpdate(sql_A);
				setEmpty();
				JOptionPane.showMessageDialog(null, "玩具信息增添成功！！！", "温馨提示", JOptionPane.WARNING_MESSAGE);
				show();
			}
			
		}
		catch(SQLException i) {
			setEmpty();
			JOptionPane.showMessageDialog(null, "库存中已有该编号玩具信息！！！", "温馨提示", JOptionPane.WARNING_MESSAGE);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "信息输入不能为空！！！", "温馨提示", JOptionPane.WARNING_MESSAGE);
		}
		
	}
	
	public static void delete() {
		con();
		try {
			String toyId = Storekeeper.toy_idField.getText();
			String sql_D = "delete from toy where id = '"+toyId+"'";
			if(toyId.equals("")) {
				JOptionPane.showMessageDialog(null, "玩具编号不能为空！！！", "温馨提示", JOptionPane.WARNING_MESSAGE);
			}
			else {
				sta.executeUpdate(sql_D);
				setEmpty();
				JOptionPane.showMessageDialog(null, "玩具信息删除成功！！！", "温馨提示", JOptionPane.WARNING_MESSAGE);
				show();
			}
		}
		catch (SQLException e) {
			
		}
	
	}
	
	public static void modify() {
		con();
		try {
			String toyId = Storekeeper.toy_idField.getText();
			String st = Storekeeper.toy_priceField.getText();
			double st1 = Double.parseDouble(st);
			String toyPrice = st1+"";
			double st2 = Double.parseDouble(st)*0.8;
			String toyVipPrice = st2+"";
			String sql_M1 = "update toy set price = '"+toyPrice+"' where id = '"+toyId+"'";
			sta.executeUpdate(sql_M1);
			String sql_M2 = "update toy set vip = '"+toyVipPrice+"' where id = '"+toyId+"'";
			sta.executeUpdate(sql_M2);
			setEmpty();
			JOptionPane.showMessageDialog(null, "玩具价格修改成功！！！", "温馨提示", JOptionPane.WARNING_MESSAGE);
			show();
			
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "玩具价格或修改价格不能为空！！！", "温馨提示", JOptionPane.WARNING_MESSAGE);
		}
		
	}
	public static void select1() {
		con();
		try {
			String st = Storekeeper.toy_priceField.getText();
			double st1 = Double.parseDouble(st);
			String toyPrice = st1+"";
			String sql_s = "select * from toy where price= '"+toyPrice+"'";
			ResultSet r = sta.executeQuery(sql_s);
			if(r.isBeforeFirst()) {
				Storekeeper.model.setNumRows(0);//将表格数据置为0
				String[] data = new String[6];
				while(r.next()) {
					data[0] = r.getString(1);
					data[1] = r.getString(2);
					data[2] = r.getString(3);
					data[3] = r.getString(4);
					data[4] = r.getString(5);
					data[5] = r.getString(6);
					Storekeeper.model.addRow(data);
				}
				setEmpty();
				JOptionPane.showMessageDialog(null, "玩具价格搜索成功！！！", "温馨提示", JOptionPane.WARNING_MESSAGE);
			}
			else {
				setEmpty();
				JOptionPane.showMessageDialog(null, "库存中暂无该价格玩具！！！", "温馨提示", JOptionPane.WARNING_MESSAGE);
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "玩具价格不能为空！！！", "温馨提示", JOptionPane.WARNING_MESSAGE);
		}
	}
	public static void select2() {
		con();
		try {
			String toyType = Storekeeper.toy_typeField.getText();
			if(toyType.equals("")) {
				JOptionPane.showMessageDialog(null, "玩具类别不能为空！！！", "温馨提示", JOptionPane.WARNING_MESSAGE);
			}
			else {
				String sql_s = "select * from toy where type= '"+toyType+"'";
				ResultSet r = sta.executeQuery(sql_s);
				if(r.isBeforeFirst()) {
					Storekeeper.model.setNumRows(0);//将表格数据置为0
					String[] data = new String[6];
					while(r.next()) {
						data[0] = r.getString(1);
						data[1] = r.getString(2);
						data[2] = r.getString(3);
						data[3] = r.getString(4);
						data[4] = r.getString(5);
						data[5] = r.getString(6);
						Storekeeper.model.addRow(data);
					}
					setEmpty();
					JOptionPane.showMessageDialog(null, "玩具类别搜索成功！！！", "温馨提示", JOptionPane.WARNING_MESSAGE);
				}
				else {
					setEmpty();
					JOptionPane.showMessageDialog(null, "库存中暂无该类别玩具！！！", "温馨提示", JOptionPane.WARNING_MESSAGE);
				}

			}
						
		}catch (SQLException e) {
			
		}
	}
	public static void setEmpty() {
		Storekeeper.toy_idField.setText("");
		Storekeeper.toy_nameField.setText("");
		Storekeeper.toy_typeField.setText("");
		Storekeeper.toy_priceField.setText("");
		Storekeeper.toy_countField.setText("");
	}
}
