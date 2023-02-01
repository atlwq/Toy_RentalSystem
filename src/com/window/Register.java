package com.window;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Register extends JFrame{
	static final int Width = 450;
	static final int Height = 430;
	public static LoginStart acLoginStart;
	FlowLayout flowLayout;
	
	JLabel title;
	JLabel name;//��������
	JLabel account;//�����˺�
	JLabel password;//��������
	JLabel r_password;//�ٴ���������
	JLabel identity;
	JLabel picture;
	
	ButtonGroup group;//���ѡ����
	JRadioButton r1_A;//��ͨ�û�
	JRadioButton r2_B;//��Ա�û�
	
	public static JTextField accountField;//�����˺��ı�
	public static JTextField nameField;//���������ı�
	public static JPasswordField passwordField;//���������ı�
	JPasswordField r_passwordField;//�ٴ����������ı�
	
	JButton bt1;
	
	JPanel p1;
	JPanel p2;
	JPanel p3;
	
	String st;
	
	Action2 action2 = new Action2();
	Action1 action1 = new Action1();
	Statement sta;
	public Register() {
		init();
		setTitle("�û�ע��");
		setSize(Width,Height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		validate();
		
	}
	public void init() {
		this.setLayout(null);
		flowLayout = new FlowLayout(FlowLayout.CENTER);
		
		p1 = new JPanel();
		p1.setBounds(0, 0, Width, Height);
		p1.setLayout(null);
		
		ImageIcon img = new ImageIcon("src/img/11.png");
		picture = new JLabel(img);
		picture.setBounds(0,0,img.getIconWidth(),img.getIconHeight());
		
		p2 = new JPanel();
		p2.setBounds(0,20,Width,50);
		p2.setLayout(new FlowLayout(FlowLayout.CENTER));
		p2.setOpaque(false);
		
		title = new JLabel("��߳���ע��");
		title.setFont(new Font("����",Font.BOLD,40));
		
		p3 = new JPanel();
		p3.setBounds(50, 90, Width-100, 420);
		p3.setLayout(flowLayout);
		p3.setOpaque(false);
		
		flowLayout.setVgap(15);
		account = new JLabel("�˺ţ�");
		account.setFont(new Font("����",Font.BOLD,20));
		accountField = new JTextField(20);
		accountField.setFont(new Font("����",Font.BOLD,20));
		name = new JLabel("������");
		name.setFont(new Font("����",Font.BOLD,20));
		nameField = new JTextField(20);
		nameField.setFont(new Font("����",Font.BOLD,20));
		password = new JLabel("���룺");
		password.setFont(new Font("����",Font.BOLD,20));
		passwordField = new JPasswordField(20);
		passwordField.setFont(new Font("����",Font.BOLD,20));
		r_password = new JLabel("ȷ�ϣ�");
		r_password.setFont(new Font("����",Font.BOLD,20));
		r_passwordField = new JPasswordField(20);
		r_passwordField.setFont(new Font("����",Font.BOLD,20));
		identity = new JLabel("���");
		identity.setFont(new Font("����",Font.BOLD,20));
		group = new ButtonGroup();
		r1_A = new JRadioButton("��ͨ�û�");
		r2_B = new JRadioButton("��Ա�û�");
		r1_A.setFont(new Font("����",Font.BOLD,21));
		r2_B.setFont(new Font("����",Font.BOLD,21));
		r1_A.addActionListener(action1);
		r2_B.addActionListener(action1);
		group.add(r1_A);
		group.add(r2_B);
		bt1 = new JButton("     ע        ��     ");
		bt1.setFont(new Font("�����п�",Font.BOLD,18));
		bt1.addActionListener(action2);
		p3.add(account);
		p3.add(accountField);
		p3.add(name);
		p3.add(nameField);
		p3.add(password);
		p3.add(passwordField);
		p3.add(r_password);
		p3.add(r_passwordField);
		p3.add(identity);
		p3.add(r1_A);
		p3.add(r2_B);
		p3.add(bt1);
		p2.add(title);
		p1.add(p2);
		p1.add(p3);
		p1.add(picture);
		this.add(p1);
		
	}
	
	class Action2 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==bt1) {
				char[] str = passwordField.getPassword();
				String password = new String(str);
				char[] r_str = r_passwordField.getPassword();
				String r_password = new String(r_str);
				if(accountField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "�˺Ų���Ϊ�գ�����", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
				}
				else if(accountField.getText().length()<6) {
					JOptionPane.showMessageDialog(null, "�˺�����Ϊ6λ�ַ�������", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
				}
				else if(nameField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "��������Ϊ�գ�����", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
				}
				else if(password.equals("")) {
					JOptionPane.showMessageDialog(null, "���벻��Ϊ�գ�����", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
				}
				else if(password.length()<6) {
					JOptionPane.showMessageDialog(null, "��������Ϊ6λ�ַ�������", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
				}
				else if(r_password.equals("")) {
					JOptionPane.showMessageDialog(null, "ȷ�����벻��Ϊ�գ�����", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
				}
				else if(password.equals(r_password)==false) {
					JOptionPane.showMessageDialog(null, "�������벻һ�£�����", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
				}
				else {
					con();
					in();
					
					//MySql.a();
					
					acLoginStart = new LoginStart();
					LoginStart.register1.dispose();
				}
				
			}
		}
		
	}
	class Action1 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==r1_A) {
				st = "A";
			}
			else if(e.getSource()==r2_B) {
				st = "B";
			}
		}
	}
	void con() {
		
		try {
			String sd = "com.mysql.cj.jdbc.Driver";
			Class.forName(sd);
			
			String url = "jdbc:mysql://localhost:3306/toy_manage";
			Connection con = DriverManager.getConnection(url,"root","lwq788795");
			//����������
			sta=con.createStatement();
		}catch (ClassNotFoundException e) {
			// TODO: handle exception
		}catch(SQLException e) {
			
		}
	}
	void in(){
		try {
			String account = Register.accountField.getText();
			String name = Register.nameField.getText();
			char[] str = Register.passwordField.getPassword();
			String password = new String(str);
			String identity = st;
			String apply = "0";
			String sql_R = "INSERT INTO user VALUES('"+name+"', '"+account+"', '"+password+"', '"+identity+"', '"+apply+"')";
			sta.executeUpdate(sql_R);
			
			String sql_createT = "CREATE TABLE {account}(id varchar(20) PRIMARY KEY,name varchar(20),type varchar(20),num varchar(20),time varchar(50),retime varchar(50))";
		    sql_createT=sql_createT.replace("{account}", "user_"+account);
		    sta.executeUpdate(sql_createT);
			
			JOptionPane.showMessageDialog(null, "ע��ɹ�������", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
			
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "��ǰ�˺��Ѿ����ڣ�����", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
			// TODO: handle exception
		}
	}

}
