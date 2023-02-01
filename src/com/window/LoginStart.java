package com.window;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.event.LoginEvent;


public class LoginStart extends JFrame{
	
	FlowLayout flowLayout;
	
	final int WIDTH = 500;
	final int HEIGHT = 300;
	
	JPanel jPanel_1;//放背景图片
	JPanel jPanel_2;//放标题
	JPanel jPanel_3;//放组件
	JPanel jPanel_4;//放组件
	
	JLabel title;
	JLabel account;
	JLabel password;
	JLabel bgImg;
	JLabel register;
	public static JTextField accountText;
	public static JPasswordField passwordText;
	JButton ok;
	
	ActionListener lister_1;
	ActionListener lister_2;
	
	//public static Register aRegister1;
	public static  Register register1;
	
	public LoginStart() {
		initialization();
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		validate();
	}
	void initialization() {
		
		lister_1=new LoginEvent();
		flowLayout = new FlowLayout(FlowLayout.CENTER);
		
		
		
		jPanel_1 = new JPanel();
		jPanel_1.setBounds(0,0,WIDTH,HEIGHT);
		jPanel_1.setLayout(null);
		
		//jPanel_1.setBorder(BorderFactory.createTitledBorder("基本功能界面"));//确定位置
		ImageIcon img = new ImageIcon("src/img/10.jpg");
		bgImg = new JLabel(img);
		bgImg.setBounds(0,0,img.getIconWidth(),img.getIconHeight());
		
		jPanel_2 = new JPanel();
		jPanel_2.setBounds(0,30,WIDTH,50);
		//设置当前框口的大小
		jPanel_2.setLayout(flowLayout);
		jPanel_2.setOpaque(false);
		title = new JLabel("玩具租赁系统");
		title.setFont(new Font("宋体",Font.BOLD,28));
		
		jPanel_3 = new JPanel();
		jPanel_3.setLayout(flowLayout);
		jPanel_3.setBounds(110, 80, WIDTH-220, 150);
		jPanel_3.setOpaque(false);
		//jPanel_3.setBorder(BorderFactory.createTitledBorder("基本功能界面"));//确定位置
		account=new JLabel("账号");
		account.setFont(new Font("华文bai行楷",Font.BOLD,18));
		
		accountText=new JTextField(22); 
		accountText.setFont(new Font("宋体",Font.PLAIN,18));
		password=new JLabel("密码");
		password.setFont(new Font("华文bai行楷",Font.BOLD,18));
		passwordText=new JPasswordField(22); 
		passwordText.setFont(new Font("宋体",Font.PLAIN,18));
		
		jPanel_4 = new JPanel();
		jPanel_4.setLayout(flowLayout);
		jPanel_4.setBounds(120, 150, WIDTH-220, 150);
		jPanel_4.setOpaque(false);
		ok=new JButton("安全登录");
		ok.setFont(new Font("宋体",Font.BOLD,18));
		ok.addActionListener(lister_1);
		
		
		register = new JLabel("注册账号");
		register.setBounds(20, 220, 100, 45);
		register.setFont(new Font("宋体",Font.BOLD,15));
		register.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				register1 = new Register();
				aLoginStart_1.dispose();
			}
		} );

		add(register);
		jPanel_3.add(account);
		jPanel_3.add(accountText);
		jPanel_3.add(password);
		jPanel_3.add(passwordText);
		jPanel_4.add(ok);
		jPanel_2.add(title);
		jPanel_1.add(jPanel_2);
		jPanel_1.add(jPanel_3);
		jPanel_1.add(jPanel_4);
		jPanel_1.add(bgImg);
		this.add(jPanel_1);
		setName();
	}
	void setName() {
		ok.setName("ok");
	}

	public static LoginStart aLoginStart_1;
	public static void main(String[] args) {
		LoginStart aLoginStart=new LoginStart();
		aLoginStart_1=aLoginStart;
	}

}
