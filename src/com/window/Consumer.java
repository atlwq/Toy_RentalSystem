package com.window;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.event.ConsumerEvent;
import com.event.LoginEvent;
import com.mysql.MySql;


public class Consumer extends JFrame{
	FlowLayout flowLayout;

	public static LoginStart aLoginStart;
	
	JPanel jPanel_1;
	JPanel jPanel_2;

	JLabel bgImg;
	JLabel id;
	public static JTextField idText;
	JLabel name;
	public static JTextField nameText;
	JLabel type;
	public static JTextField typeText;
	JLabel price;
	public static JTextField priceText;
	
	
	JButton search,lease,revert,all,apply;

	JMenuItem cancellation;
	
	public static Object cName[] = {"编号","名称","种类","原价","会员价","库存"};
	public static JTable tableL = null;
	public static JScrollPane jscrollpane;
	static Vector rwo;//动态数组
	static Object a[][];
	public static DefaultTableModel model;
	static TableColumnModel columnModel;
	
	final int WIDTH = 680;
	final int HEIGHT = 700;
	
	ActionListener listener_1;
	
	public Consumer() throws SQLException  {
		initialization();
		setSize(WIDTH,HEIGHT);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		validate();
	}
	void initialization() throws SQLException{
		listener_1=new ConsumerEvent();
		
		flowLayout=new FlowLayout(FlowLayout.CENTER);
		
		
		this.setLayout(null);
		this.setTitle("玩具租赁系统");
		
		ImageIcon img = new ImageIcon("src/img/3.jpg");
		bgImg = new JLabel(img);
		bgImg.setBounds(0,0,img.getIconWidth(),120);
		
		ActionListener a =new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MyFrame a=new MyFrame();
			}
		};
		ActionListener b =new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginEvent.consumer.dispose();
				aLoginStart=new LoginStart();
			}
		};
		
		JMenuBar menuBar=new JMenuBar();
		JMenu menu=new JMenu("个人信息");
		JMenuItem item1=new JMenuItem("我    的");
		item1.addActionListener(a);
		cancellation=new JMenuItem("注    销");
		cancellation.addActionListener(b);
		menu.add(item1);
		menu.add(cancellation);
		menuBar.add(menu);


		jPanel_1 = new javax.swing.JPanel();
		jPanel_1.setLayout(flowLayout);
		//jPanel_1.setBorder(BorderFactory.createTitledBorder("基本信息处理"));//测试
		jPanel_1.setBounds(90, 125, WIDTH-180, 115);

		id = new JLabel("编号");
		idText = new JTextField(20);
		name = new JLabel("名称");
		nameText = new JTextField(20);
		type = new JLabel("种类");
		typeText = new JTextField(20);
		price = new JLabel("原价");
		priceText = new JTextField(20);

		search = new JButton("查　找");
		search.addActionListener(listener_1);
		lease = new JButton("租　赁");
		lease.addActionListener(listener_1);
		revert = new JButton("归　还");
		revert.addActionListener(listener_1);
		apply = new JButton("申请会员");
		apply.addActionListener(listener_1);
		all = new JButton("所有玩具");
		all.addActionListener(listener_1);
	
		jPanel_2 = new JPanel();
		jPanel_2.setLayout(flowLayout);
		jPanel_2.setBounds(0, 235, WIDTH-14, 390);
		//jPanel_2.setBorder(BorderFactory.createTitledBorder("基本信息处理"));//测试
		table();
		setName();
		jPanel_1.add(id);
		jPanel_1.add(idText);
		jPanel_1.add(name);
		jPanel_1.add(nameText);
		jPanel_1.add(type);
		jPanel_1.add(typeText);
		jPanel_1.add(price);
		jPanel_1.add(priceText);
		jPanel_1.add(search);
		jPanel_1.add(lease);
		jPanel_1.add(revert);
		jPanel_1.add(all);
		if(MySql.identity(LoginEvent.account)==1) {
			jPanel_1.add(apply);
		}
		jPanel_2.add(jscrollpane);
		this.add(jPanel_1);
		this.add(jPanel_2);
		this.add(bgImg);
		this.setJMenuBar(menuBar);
		MySql.searchAll();
	}
	
	public static void table(){
		tableL = getTable();
		jscrollpane = new JScrollPane(tableL);
		jscrollpane.setPreferredSize(new Dimension(650,365));
		tableL.setPreferredSize(new Dimension(200,1000));
		jscrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);//将滑动组件显示在窗口中
		
	}
	public static JTable getTable(){
		if(tableL == null) {
			tableL = new JTable();
			int[] cNameWidth= {70,70,70,70,70,70};
			model = new DefaultTableModel();
			model.setColumnIdentifiers(cName);//设置表头
			tableL.setModel(model);
			columnModel = tableL.getColumnModel();
			int count = columnModel.getColumnCount();//getColumnCount 返回列数
			for(int i = 0;i < count;i ++) {
				javax.swing.table.TableColumn column = columnModel.getColumn(i);
				column.setPreferredWidth(cNameWidth[i]);//设置列宽
			}
			rwo = new Vector(6);
		}return tableL;
	}
	
	void setName() {
		search.setName("search");
		lease.setName("lease");
		revert.setName("revert");
		apply.setName("apply");
		all.setName("all");
	}

}
