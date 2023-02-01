package com.window;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.mysql.MySql;

public class MyFrame extends JFrame{
	
	FlowLayout flowLayout;

	JPanel jPanel_1;
	JPanel jPanel_2;
	
	
	
	public static Object cName[] = {"���","����","����","����","���ʱ��","�黹ʱ��"};
	public static JTable tableL = null;
	public static JScrollPane jscrollpane;
	static Vector rwo;//��̬����
	static Object a[][];
	public static DefaultTableModel model;
	static TableColumnModel columnModel;
	
	final int WIDTH = 560;
	final int HEIGHT = 280;
	
	public MyFrame() {
		initialization();
		setSize(WIDTH,HEIGHT);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		validate();
		MySql.searchAll();
		tableL=null;
	}
	void initialization() {
		flowLayout=new FlowLayout(FlowLayout.CENTER);

		this.setLayout(null);
		this.setTitle("�ҵ�");

		jPanel_1 = new JPanel();
		jPanel_1.setLayout(flowLayout);
		jPanel_1.setBounds(0, 0, WIDTH-14, 365);

		table();

		jPanel_1.add(jscrollpane);
		this.add(jPanel_1);
		MySql.MysearchAll();
	}
	public static void table(){
		tableL = getTable();
		jscrollpane = new JScrollPane(tableL);
		jscrollpane.setPreferredSize(new Dimension(520,220));
		tableL.setPreferredSize(new Dimension(200,1000));
		jscrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);//�����������ʾ�ڴ�����
		
	}
	public static JTable getTable(){
		if(tableL == null) {
			tableL = new JTable();
			int[] cNameWidth= {40,40,40,40,40,40};
			model = new DefaultTableModel();
			model.setColumnIdentifiers(cName);//���ñ�ͷ
			tableL.setModel(model);
			columnModel = tableL.getColumnModel();
			int count = columnModel.getColumnCount();//getColumnCount ��������
			for(int i = 0;i < count;i ++) {
				//javax.swing.table.TableColumn column = columnModel.getColumn(i);
				javax.swing.table.TableColumn column = columnModel.getColumn(i);
				column.setPreferredWidth(cNameWidth[i]);//�����п�
			}
			rwo = new Vector(6);
		}return tableL;
	}
}
