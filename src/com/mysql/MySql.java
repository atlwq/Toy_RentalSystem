package com.mysql;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import com.event.ConsumerEvent;
import com.event.LoginEvent;
import com.window.Consumer;
import com.window.MyFrame;



public class MySql {
	public static Connection con;
	
	public MySql(String account, String password){
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    	}catch(Exception e){
    		System.out.println(e);
    	}
    	String url ="jdbc:mysql://localhost:3306/toy_manage?useSSL=false";
    	try {
    		con = DriverManager.getConnection(url,account,password);
    		
    	}catch(SQLException e) {
    		System.out.println(e);
    	}
    }
	
	 public static boolean loginAccount(String account,String password){
	    	PreparedStatement sql;
	    	
			ResultSet rs;
			MySql mySql=new MySql("root","lwq788795");
	    	String url="select account,password from user where account=? and password=? ";
	    	try {
				sql=mySql.con.prepareStatement(url);
				sql.setString(1,account);
				sql.setString(2,password);
				rs=sql.executeQuery();
				if (rs.next()){
					
					return true;
				}else{
					return false;
				}
			}catch (SQLException e){
				e.printStackTrace();
				return false;
			}
		}
	 
	 
	 public static int identity(String account) throws SQLException{
	    	PreparedStatement sql;
			ResultSet rs;
			MySql mySql=new MySql("root","lwq788795");
	    	String url1="select identity from user where account=?";
			sql=mySql.con.prepareStatement(url1);
			sql.setString(1,account);
			rs=sql.executeQuery();
			while(rs.next()) {
				String iden=rs.getString("identity");
				if(iden.equals("A")) {
					return 1;
				}else if(iden.equals("B")) {
					return 2;
				}else if(iden.equals("C")) {
					return 3;
				}else if(iden.equals("D")) {
					return 4;
				}
			}
			return 0;	
	 }

	    public static void soToy() throws SQLException {
	    	String id=Consumer.idText.getText();
	    	
	    	MySql mySql=new MySql("root","lwq788795");
			PreparedStatement sql;
			Consumer.model.setRowCount(0);
			ResultSet rs;
			String url="select name from toy where id={id}";
			url=url.replace("{id}", id);
			sql=mySql.con.prepareStatement(url);
			rs=sql.executeQuery();
			if(rs.next()) {
				ResultSet rs1;
				PreparedStatement sql1;
				String url1="select * from toy where id = ?";
				sql1=mySql.con.prepareStatement(url1);
	    		sql1.setString(1,id);
				rs1=sql1.executeQuery();
				ConsumerEvent.addTable(rs1,1);
			}else {
				JOptionPane.showMessageDialog(null,"请输入正确信息！","警告",JOptionPane.WARNING_MESSAGE);
				searchAll();
			}
	    }
	    
	    public static void leaseToy(String id) throws SQLException{
	    	MySql mySql=new MySql("root","lwq788795");
			PreparedStatement sql;
			ResultSet rs;
			String url="select name from toy where id={id}";
			url=url.replace("{id}", id);
			sql=mySql.con.prepareStatement(url);
			rs=sql.executeQuery();
			if(rs.next()) {
				PreparedStatement sql1;
				String url1="update toy set stock=stock-1 where id=?";
				sql1=mySql.con.prepareStatement(url1);
				sql1.setString(1,id);
				sql1.executeUpdate();
				JOptionPane.showMessageDialog(null,"租赁成功！","恭喜",JOptionPane.WARNING_MESSAGE);
				getData(id);
				searchAll();
			}else {
				JOptionPane.showMessageDialog(null,"请输入正确信息！","警告",JOptionPane.WARNING_MESSAGE);
			}

		}
	    
	    public static void revertToy(String id) throws HeadlessException, SQLException{
	    	
	    	MySql mySql=new MySql("root","lwq788795");
			PreparedStatement sql;
			ResultSet rs;
			String url="select name from toy where id={id}";
			url=url.replace("{id}", id);
			sql=mySql.con.prepareStatement(url);
			rs=sql.executeQuery();
			if(rs.next()) {
				PreparedStatement sql1;
				String url1="update toy set stock=stock+1 where id=?";
				sql1=mySql.con.prepareStatement(url1);
				sql1.setString(1,id);
				sql1.executeUpdate();
				getData_1(id);
			}else {
				JOptionPane.showMessageDialog(null,"请输入正确信息！","警告",JOptionPane.WARNING_MESSAGE);
			}
		}
	    
	    public static void applyy(String id_d){
	    	MySql mySql=new MySql("root","lwq788795");
			PreparedStatement sql;
			ResultSet rs;
			String url="update user set apply=1 where account=?";
			try {
				sql=mySql.con.prepareStatement(url);
				sql.setString(1,id_d);
				sql.executeUpdate();
				JOptionPane.showMessageDialog(null,"申请成功！","恭喜",JOptionPane.WARNING_MESSAGE);
			}catch (SQLException e){

			}
		}

	    public static void searchAll(){
			MySql mySql=new MySql("root","lwq788795");
			PreparedStatement sql;
			Consumer.model.setRowCount(0);
			ResultSet rs;
			String url="select * from toy";
			try {
				sql=mySql.con.prepareStatement(url);
				rs=sql.executeQuery();
				ConsumerEvent.addTable(rs,1);
			}catch (SQLException e){
	
			}
		}

	    public static void MysearchAll(){
	    	
			MySql mySql=new MySql("root","lwq788795");
			PreparedStatement sql;
			Consumer.model.setRowCount(0);
			ResultSet rs;
			String url="select * from {name}";
			url=url.replace("{name}", "user_"+LoginEvent.account);
			
			try {
				sql=mySql.con.prepareStatement(url);
				rs=sql.executeQuery();
				ConsumerEvent.addTable(rs,2);
			}catch (SQLException e){
	
			}
		}
	    
	    
	    public static void getData(String id) throws SQLException {
	    	Date date = new Date();
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
			String time1=dateFormat.format(date);
	        long time = date.getTime();
	        Date newDate = new Date();
	        newDate.setTime(time + 1000*60*60*24*7);
	        String time2=dateFormat.format(newDate);
	        MySql mySql=new MySql("root","lwq788795");
	        //获取玩具名字,种类
			PreparedStatement sql;
			ResultSet rs1;
			String url="select name,type from toy where id=?";
			sql=mySql.con.prepareStatement(url);
			sql.setString(1, id);
			rs1=sql.executeQuery();
			String name=null;
			String type=null;
			while(rs1.next()) {
				name=rs1.getString("name");
				type=rs1.getString("type");
			}

			PreparedStatement sql3;
			ResultSet rs3;
			String url3="select name,num from {account} where id=?";
			url3=url3.replace("{account}", "user_"+LoginEvent.account);
			sql3=mySql.con.prepareStatement(url3);
			sql3.setString(1, id);
			rs3=sql3.executeQuery();
			if(rs3.next()) {
				PreparedStatement sql6;
				int num=rs3.getInt("num");
				num=num+1;	
				String url6="update {account} set num={num} ,time=?,retime=? where id={id}";
				url6=url6.replace("{account}", "user_"+LoginEvent.account);
				url6=url6.replace("{num}", num+"");
				url6=url6.replace("{id}", id);
				sql6=mySql.con.prepareStatement(url6);
				sql6.setString(1, time1);
				sql6.setString(2, time2);
				sql6.executeUpdate();
			}else {		
				PreparedStatement sql5;
				ResultSet rs5;
				String url5="INSERT INTO {account} VALUES(?,?,?,?,?,?)";
				url5=url5.replace("{account}", "user_"+LoginEvent.account);
				sql5=mySql.con.prepareStatement(url5);
				
				sql5.setString(1, id);
				sql5.setString(2, name);
				sql5.setString(3, type);
				sql5.setInt(4, 1);
				sql5.setString(5, time1);
				sql5.setString(6, time2);
				sql5.executeUpdate();
				sql.close();
			}
	    }
	    
	    public static void getData_1(String id) throws SQLException {
	        MySql mySql=new MySql("root","lwq788795");
			PreparedStatement sql;
			ResultSet rs;
			String url="select name from {account} where id=?";
			url=url.replace("{account}", "user_"+LoginEvent.account);
			sql=mySql.con.prepareStatement(url);
			sql.setString(1, id);
			rs=sql.executeQuery();
			if(rs.next()) {
				PreparedStatement sql1;
				ResultSet rs1;
				String url1="update {account} set num=num-1 where id={id}";
				url1=url1.replace("{account}", "user_"+LoginEvent.account);
				url1=url1.replace("{id}", id);
				sql1=mySql.con.prepareStatement(url1);
				sql1.executeUpdate();
				getData_1_num(id);
				JOptionPane.showMessageDialog(null,"归还成功！","谢谢",JOptionPane.WARNING_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null,"请确认归还信息！","警告",JOptionPane.WARNING_MESSAGE);
			}
			
	    }
	    public static void getData_1_num(String id) throws SQLException{
	    	 MySql mySql=new MySql("root","lwq788795");
	    	//判断是否为0
			PreparedStatement sql2;
			ResultSet rs2;
			String url2="select num from {account} where id={id}";
			url2=url2.replace("{account}", "user_"+LoginEvent.account);
			url2=url2.replace("{id}", id);
			
			sql2=mySql.con.prepareStatement(url2);
			rs2=sql2.executeQuery();
			while(rs2.next()) {
				String numm=rs2.getString("num");
				if(numm.equals("0")) {
					PreparedStatement sql1;
					ResultSet rs1;
					String url1="delete from {account} where id={id}";
					url1=url1.replace("{account}", "user_"+LoginEvent.account);
					url1=url1.replace("{id}", id);
					sql1=mySql.con.prepareStatement(url1);
					sql1.executeUpdate();
				}
				
			}
	    }
}
