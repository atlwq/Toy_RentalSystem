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
	
	public static Object cName[] = {"���","����","����","ԭ��","��Ա��","���"};
	public static JTable tableL = null;
	public static JScrollPane jscrollpane;
	static Vector rwo;//��̬����
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
		this.setTitle("�������ϵͳ");
		
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
		JMenu menu=new JMenu("������Ϣ");
		JMenuItem item1=new JMenuItem("��    ��");
		item1.addActionListener(a);
		cancellation=new JMenuItem("ע    ��");
		cancellation.addActionListener(b);
		menu.add(item1);
		menu.add(cancellation);
		menuBar.add(menu);


		jPanel_1 = new javax.swing.JPanel();
		jPanel_1.setLayout(flowLayout);
		//jPanel_1.setBorder(BorderFactory.createTitledBorder("������Ϣ����"));//����
		jPanel_1.setBounds(90, 125, WIDTH-180, 115);

		id = new JLabel("���");
		idText = new JTextField(20);
		name = new JLabel("����");
		nameText = new JTextField(20);
		type = new JLabel("����");
		typeText = new JTextField(20);
		price = new JLabel("ԭ��");
		priceText = new JTextField(20);

		search = new JButton("�顡��");
		search.addActionListener(listener_1);
		lease = new JButton("�⡡��");
		lease.addActionListener(listener_1);
		revert = new JButton("�顡��");
		revert.addActionListener(listener_1);
		apply = new JButton("�����Ա");
		apply.addActionListener(listener_1);
		all = new JButton("�������");
		all.addActionListener(listener_1);
	
		jPanel_2 = new JPanel();
		jPanel_2.setLayout(flowLayout);
		jPanel_2.setBounds(0, 235, WIDTH-14, 390);
		//jPanel_2.setBorder(BorderFactory.createTitledBorder("������Ϣ����"));//����
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
		jscrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);//�����������ʾ�ڴ�����
		
	}
	public static JTable getTable(){
		if(tableL == null) {
			tableL = new JTable();
			int[] cNameWidth= {70,70,70,70,70,70};
			model = new DefaultTableModel();
			model.setColumnIdentifiers(cName);//���ñ�ͷ
			tableL.setModel(model);
			columnModel = tableL.getColumnModel();
			int count = columnModel.getColumnCount();//getColumnCount ��������
			for(int i = 0;i < count;i ++) {
				javax.swing.table.TableColumn column = columnModel.getColumn(i);
				column.setPreferredWidth(cNameWidth[i]);//�����п�
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
