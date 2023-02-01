package com.window;

import com.event.LoginEvent;
import com.mysql.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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

public class Storekeeper extends JFrame{
	static final int Width = 900;
	static final int Height = 500;
	
	JLabel title;
	JLabel toy_id;//编号
	JLabel toy_name;//名称
	JLabel toy_type;//类别
	JLabel toy_price;//原价
	JLabel toy_count;//库存
	JLabel toy_return;//注销
	
	public static JTextField toy_idField;
	public static JTextField toy_nameField;
	public static JTextField toy_typeField;
	public static JTextField toy_priceField;
	public static JTextField toy_countField;
	
	JButton bt_show;
	JButton bt_add;
	JButton bt_delete;
	JButton bt_modify;
	JButton bt_select1;
	JButton bt_select2;
	JPanel p1;
	JPanel p2;
	JPanel p3;
	JPanel p4;
	JPanel p5;
	
	Action1 action1 = new Action1();
	
	//表格数据
	Object cName[] = {"编号","名称","类别","原价","会员价","库存量"};
	static JTable toyJTable = null;
	JScrollPane jScrollPane;
	static Vector rwo;
	static Object data[][];
	public static DefaultTableModel model;
	static TableColumnModel columnModel;
	public static  LoginStart loginStart1;
	
	public Storekeeper() {
		init();
		setTitle("仓库管理");
		setSize(Width,Height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		validate();//让组件生效
		setResizable(false);
		Storekeeper_Sql.show();
	}
	void init() {
		p1 = new JPanel();
		p1.setBounds(0, 0, Width, Height);
		p1.setLayout(null);
		
		p2 = new JPanel();
		p2.setBounds(0, 10, Width, 60);
		p2.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		title = new JLabel("库存信息管理");
		title.setFont(new Font("宋体",Font.BOLD,40));
		title.setForeground(Color.CYAN);
		
		p3 = new JPanel();
		p3.setBounds(30, 80, Width-300, Height-200);
		p3.setLayout(new FlowLayout(FlowLayout.CENTER));
		Border border1 = BorderFactory.createTitledBorder("<信息显示>");
		p3.setBorder(border1);
		
		table();
		
		p4 = new JPanel();
		p4.setLayout(new FlowLayout(FlowLayout.CENTER));
		p4.setBounds(30, 400, Width-300, 60);
		bt_show = new JButton("玩具全览");
		bt_show.addActionListener(action1);
		bt_add = new JButton("玩具增添");
		bt_add.addActionListener(action1);
		bt_delete = new JButton("玩具删除");
		bt_delete.addActionListener(action1);
		bt_modify = new JButton("价格修改");
		bt_modify.addActionListener(action1);
		bt_select1 = new JButton("价格搜索");
		bt_select1.addActionListener(action1);
		bt_select2 = new JButton("类别搜索");
		bt_select2.addActionListener(action1);
		
		p5 = new JPanel();
		p5.setBounds(660, 90, Width-700, Height-210);
		p5.setLayout(new FlowLayout(FlowLayout.CENTER));
		Border border2 = BorderFactory.createTitledBorder("<信息输入>");
		p5.setBorder(border2);
		toy_id = new JLabel("编号：");
		toy_idField = new JTextField(15);
		toy_name = new JLabel("名称：");
		toy_nameField = new JTextField(15);
		toy_type = new JLabel("类别：");
		toy_typeField = new JTextField(15);
		toy_price = new JLabel("原价：");
		toy_priceField = new JTextField(15);
		toy_count = new JLabel("库存量：");
		toy_countField = new JTextField(15);
		
		toy_return = new JLabel("注销登录");
		toy_return.setBounds(820, 430, 100, 50);
		toy_return.setFont(new Font("宋体",Font.BOLD,15));
		toy_return.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				LoginEvent.storekeeper.dispose();
				loginStart1 = new LoginStart();
				
			}
		});
		
		p3.add(jScrollPane);
		p4.add(bt_show);
		p4.add(bt_add);
		p4.add(bt_delete);
		p4.add(bt_modify);
		p4.add(bt_select1);
		p4.add(bt_select2);
		p5.add(toy_id);
		p5.add(toy_idField);
		p5.add(toy_name);
		p5.add(toy_nameField);
		p5.add(toy_type);
		p5.add(toy_typeField);
		p5.add(toy_price);
		p5.add(toy_priceField);
		p5.add(toy_count);
		p5.add(toy_countField);
		p2.add(title);
		p1.add(p2);
		p1.add(p3);
		p1.add(p4);
		p1.add(p5);
		p1.add(toy_return);
		this.add(p1);
	}
	void table() {
		toyJTable = getBookJTable();
		jScrollPane = new JScrollPane(toyJTable);//浏览窗格
		jScrollPane.setPreferredSize(new Dimension(570,260));//设置大小
		toyJTable.setPreferredSize(new Dimension(200,500));//设置表格大小
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);//将滑动组件显示在窗口中
		
	}
	JTable getBookJTable() {
		if(toyJTable==null) {
			toyJTable = new JTable();
			int[] columnWidth = {50,65,30,30,30,35};
			model = new DefaultTableModel() {
				public boolean isCellEditable(int row, int column) {
					return false;
				};
			};//表格宽度行数，不可编辑
			model.setColumnIdentifiers(cName);
			toyJTable.setModel(model);//设置为表格模式
			columnModel = toyJTable.getColumnModel();//获取表格的控制
			toyJTable.getTableHeader().setReorderingAllowed(false);//表格不可拖动
			toyJTable.getTableHeader().setResizingAllowed(false);//同上
			int count = columnModel.getColumnCount();//行数列数
			for(int i=0;i<count;i++) {
				TableColumn column = columnModel.getColumn(i);
				column.setPreferredWidth(columnWidth[i]);
			}
			rwo = new Vector(10);
			
			
		}
		return toyJTable;
	}
	class Action1 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==bt_show) {
				Storekeeper_Sql.show();
			}
			else if(e.getSource()==bt_add) {
				Storekeeper_Sql.add();
			}
			else if(e.getSource()==bt_delete) {
				Storekeeper_Sql.delete();
			}
			else if(e.getSource()==bt_modify) {
				Storekeeper_Sql.modify();
			}
			else if(e.getSource()==bt_select1) {
				Storekeeper_Sql.select1();
			}
			else if(e.getSource()==bt_select2) {
				Storekeeper_Sql.select2();
			}
		}
		
	}
	
}
