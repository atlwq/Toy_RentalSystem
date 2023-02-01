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
	JLabel user_id;//�˺�
	JLabel user_name;//����
	JLabel user_password;//����
	JLabel user_identity;//���
	JLabel user_apply;//��Ա�������
	
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
	
	//�������
	Object cName[] = {"����","�˺�","����","���","��Ա�������"};
	static JTable userJTable = null;
	JScrollPane jScrollPane;
	static Vector rwo;
	static Object data[][];
	public static DefaultTableModel model;
	static TableColumnModel columnModel;
	
	public Administrator() {
		init();
		setTitle("�û�����");
		setSize(Width,Height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		validate();//�������Ч
		setResizable(false);
	}
	void init() {
		p1 = new JPanel();
		p1.setBounds(0, 0, Width, Height);
		p1.setLayout(null);
		
		p2 = new JPanel();
		p2.setBounds(0, 10, Width, 60);
		p2.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		title = new JLabel("�û���Ϣ����");
		title.setFont(new Font("����",Font.BOLD,40));
		title.setForeground(Color.PINK);
		
		p3 = new JPanel();
		p3.setBounds(30, 80, Width-300, Height-200);
		p3.setLayout(new FlowLayout(FlowLayout.CENTER));
		Border border1 = BorderFactory.createTitledBorder("<��Ϣ��ʾ>");
		p3.setBorder(border1);
		
		table();
		
		p4 = new JPanel();
		p4.setLayout(new FlowLayout(FlowLayout.CENTER));
		p4.setBounds(85, 400, Width-390, 60);
		bt_show = new JButton("�û���Ϣȫ��");
		bt_show.addActionListener(action1);
		bt_select = new JButton("�û���Ϣ����");
		bt_select.addActionListener(action1);
		bt_add = new JButton("�û���Ϣ����");
		bt_add.addActionListener(action1);
		bt_modify = new JButton("�û���Ϣ�޸�");
		bt_modify.addActionListener(action1);
		
		
		p5 = new JPanel();
		p5.setBounds(660, 90, Width-700, Height-200);
		p5.setLayout(new FlowLayout(FlowLayout.CENTER));
		Border border2 = BorderFactory.createTitledBorder("<��Ϣ����>");
		
		p5.setBorder(border2);
		user_name = new JLabel("������");
		user_nameField = new JTextField(15);
		user_id = new JLabel("�˺ţ�");
		user_idField = new JTextField(15);
		user_password = new JLabel("���룺");
		user_passwordField = new JTextField(15);
		user_identity = new JLabel("��ݣ�");
		user_identityField = new JTextField(15);
		user_apply = new JLabel("��Ա���������");
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
		jScrollPane = new JScrollPane(userJTable);//�������
		jScrollPane.setPreferredSize(new Dimension(570,260));//���ô�С
		userJTable.setPreferredSize(new Dimension(200,500));//���ñ���С
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);//�����������ʾ�ڴ�����
		
	}
	JTable getBookJTable() {
		if(userJTable==null) {
			userJTable = new JTable();
			int[] columnWidth = {50,65,30,30,35};
			model = new DefaultTableModel() {
				public boolean isCellEditable(int row, int column) {
					return false;
				};
			};//��������������ɱ༭
			model.setColumnIdentifiers(cName);
			userJTable.setModel(model);//����Ϊ���ģʽ
			columnModel = userJTable.getColumnModel();//��ȡ���Ŀ���
			userJTable.getTableHeader().setReorderingAllowed(false);//��񲻿��϶�
			userJTable.getTableHeader().setResizingAllowed(false);//ͬ��
			int count = columnModel.getColumnCount();//��������
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

