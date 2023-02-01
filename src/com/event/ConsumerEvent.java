package com.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.mysql.MySql;
import com.window.Consumer;
import com.window.LoginStart;
import com.window.MyFrame;

public class ConsumerEvent implements ActionListener{
	
	JButton button;
	String account01;
	String sex;
	static String account;
	static Consumer consumer;
	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("ConsumerEvent");
		try {
			button = (JButton) e.getSource();
			if (button.getName().equals("search")) {
				String id=Consumer.idText.getText();
				if(id.equals("")==false) {
					MySql.soToy();
				}else {
					JOptionPane.showMessageDialog(null,"请输入商品编号！","警告",JOptionPane.WARNING_MESSAGE);
				}
				setEmpty();
			}
			
			if (button.getName().equals("lease")) {
				String id=Consumer.idText.getText();
				if(id.equals("")==false) {
					MySql.leaseToy(id);	
					setEmpty();
				}else {
					JOptionPane.showMessageDialog(null,"请输入商品编号！","警告",JOptionPane.WARNING_MESSAGE);
				}
				MySql.searchAll();
			}
			
			if (button.getName().equals("revert")) {
				String id=Consumer.idText.getText();
				if(id.equals("")==false) {
					MySql.revertToy(id);
					setEmpty();
					
				}else {
					JOptionPane.showMessageDialog(null,"请输入商品编号！","警告",JOptionPane.WARNING_MESSAGE);
				}
				MySql.searchAll();	
				
			}
			
			if (button.getName().equals("apply")) {
				String id_d=LoginEvent.idd;
				
				MySql.applyy(id_d);	
				setEmpty();
				
			}
			
			if (button.getName().equals("all")) {
				MySql.searchAll();	
				
				
			}
			
			
		}catch(Exception e1) {
			
		}
		
	}
	
	
	public static void addTable(ResultSet rs,int n){
		String []data=new String[6];
		try {
			while (rs.next()){
				for (int i=1;i<=6;i++){
					data[i-1]=rs.getString(i);
				}
				if(n==1) {
					Consumer.model.addRow(data);
				}else if(n==2) {
					MyFrame.model.addRow(data);
				}
				
			}

		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	void setEmpty() {
		Consumer.idText.setText("");
		Consumer.nameText.setText("");
		Consumer.typeText.setText("");
		Consumer.priceText.setText("");
		
	}
		

}
