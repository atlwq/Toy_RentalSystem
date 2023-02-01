package com.event;

import com.mysql.MySql;
import com.window.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginEvent implements ActionListener{
	JButton button;
	JMenuItem jMenuItem;
	String account01;
	String sex;
	public static String account;
	public static Consumer consumer;
	public static Administrator administrator;

	
	public static Storekeeper storekeeper;
	public static String idd;
	

	
	public void actionPerformed(ActionEvent e) {
		//System.out.println("LoginEvent");
		try {
			button =  (JButton) e.getSource();
			if (button.getName().equals("ok")){
				String account=LoginStart.accountText.getText();
				idd=account;
				String password=new String(LoginStart.passwordText.getPassword());
				
				//System.out.println(password);
				if (MySql.loginAccount(account,password)){
						JOptionPane.showMessageDialog(null,"用户已上线！","恭喜",JOptionPane.WARNING_MESSAGE);
						this.account=account;
						if(MySql.identity(account)==1) {
							consumer=new Consumer();
						}else if(MySql.identity(account)==2){
							consumer=new Consumer();
						}else if(MySql.identity(account)==3) {
							administrator=new Administrator();
						}
						else if(MySql.identity(account)==4) {
							storekeeper=new Storekeeper();	
						}
						LoginStart.aLoginStart_1.dispose();
						Register.acLoginStart.dispose();
						Consumer.aLoginStart.dispose();
						Storekeeper.loginStart1.dispose();
						
				}else {
					JOptionPane.showMessageDialog(null,"登录失败！检查账号密码是否正确！","警告",JOptionPane.WARNING_MESSAGE);
				}
			}	
		}catch(Exception e1) {
			
		}
	}
	
}
