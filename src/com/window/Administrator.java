package com.window;

import com.mysql.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class Administrator extends JFrame{
	static final int Width = 900;
	static final int Height = 500;
	
	JLabel title;
	JLabel user_id;//账号
	JLabel user_name;//姓名
	JLabel user_password;//密码
	JLabel user_identity;//身份
	JLabel user_apply;//会员申请情况
	
	public static JTextField user_idField;
	public static JTextField user_nameField;
	public static JTextField user_passwordField;
	public static JTextField user_identityField;
	public static JTextField user_applyField;
	
	JButton bt_select;
	JButton bt_add;
	JButton bt_modify;
	JButton bt_show;
	
	JPanel p1;
	JPanel p2;
	JPanel p3;
	JPanel p4;
	JPanel p5;
	
	Action1 action1 = new Action1();
	
	//表格数据
	Object cName[] = {"姓名","账号","密码","身份","会员申请情况"};
	static JTable userJTable = null;
	JScrollPane jScrollPane;
	static Vector rwo;
	static Object data[][];
	public static DefaultTableModel model;
	static TableColumnModel columnModel;
	
	public Administrator() {
		init();
		setTitle("用户管理");
		setSize(Width,Height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		validate();//让组件生效
		setResizable(false);
	}
	void init() {
		p1 = new JPanel();
		p1.setBounds(0, 0, Width, Height);
		p1.setLayout(null);
		
		p2 = new JPanel();
		p2.setBounds(0, 10, Width, 60);
		p2.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		title = new JLabel("用户信息管理");
		title.setFont(new Font("宋体",Font.BOLD,40));
		title.setForeground(Color.PINK);
		
		p3 = new JPanel();
		p3.setBounds(30, 80, Width-300, Height-200);
		p3.setLayout(new FlowLayout(FlowLayout.CENTER));
		Border border1 = BorderFactory.createTitledBorder("<信息显示>");
		p3.setBorder(border1);
		
		table();
		
		p4 = new JPanel();
		p4.setLayout(new FlowLayout(FlowLayout.CENTER));
		p4.setBounds(85, 400, Width-390, 60);
		bt_show = new JButton("用户信息全览");
		bt_show.addActionListener(action1);
		bt_select = new JButton("用户信息搜索");
		bt_select.addActionListener(action1);
		bt_add = new JButton("用户信息增加");
		bt_add.addActionListener(action1);
		bt_modify = new JButton("用户信息修改");
		bt_modify.addActionListener(action1);
		
		
		p5 = new JPanel();
		p5.setBounds(660, 90, Width-700, Height-200);
		p5.setLayout(new FlowLayout(FlowLayout.CENTER));
		Border border2 = BorderFactory.createTitledBorder("<信息输入>");
		
		p5.setBorder(border2);
		user_name = new JLabel("姓名：");
		user_nameField = new JTextField(15);
		user_id = new JLabel("账号：");
		user_idField = new JTextField(15);
		user_password = new JLabel("密码：");
		user_passwordField = new JTextField(15);
		user_identity = new JLabel("身份：");
		user_identityField = new JTextField(15);
		user_apply = new JLabel("会员申请情况：");
		user_applyField = new JTextField(15);
		
		p3.add(jScrollPane);
		p4.add(bt_show);
		p4.add(bt_add);
		p4.add(bt_select);
		p4.add(bt_modify);
		p5.add(user_name);
		p5.add(user_nameField);
		p5.add(user_id);
		p5.add(user_idField);
		p5.add(user_password);
		p5.add(user_passwordField);
		p5.add(user_identity);
		p5.add(user_identityField);
		p5.add(user_apply);
		p5.add(user_applyField);
		p2.add(title);
		p1.add(p2);
		p1.add(p3);
		p1.add(p4);
		p1.add(p5);
		
		this.add(p1);
	}
	void table() {
		userJTable = getBookJTable();
		jScrollPane = new JScrollPane(userJTable);//浏览窗格
		jScrollPane.setPreferredSize(new Dimension(570,260));//设置大小
		userJTable.setPreferredSize(new Dimension(200,500));//设置表格大小
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);//将滑动组件显示在窗口中
		
	}
	JTable getBookJTable() {
		if(userJTable==null) {
			userJTable = new JTable();
			int[] columnWidth = {50,65,30,30,35};
			model = new DefaultTableModel() {
				public boolean isCellEditable(int row, int column) {
					return false;
				};
			};//表格宽度行数，不可编辑
			model.setColumnIdentifiers(cName);
			userJTable.setModel(model);//设置为表格模式
			columnModel = userJTable.getColumnModel();//获取表格的控制
			userJTable.getTableHeader().setReorderingAllowed(false);//表格不可拖动
			userJTable.getTableHeader().setResizingAllowed(false);//同上
			int count = columnModel.getColumnCount();//行数列数
			for(int i=0;i<count;i++) {
				TableColumn column = columnModel.getColumn(i);
				column.setPreferredWidth(columnWidth[i]);
			}
			rwo = new Vector(10);
			
			
		}
		return userJTable;
	}
	class Action1 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==bt_show) {
				Administrator_sql.show();
			}
			else if(e.getSource()==bt_add) {
				Administrator_sql.add();
			}
			else if(e.getSource()==bt_modify) {
				Administrator_sql.modify();
			}
			else if(e.getSource()==bt_select) {
				Administrator_sql.select();
			}
		}
		
	}
}

