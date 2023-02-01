package com.event;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.window.Consumer;

public class MyFrameEvent {
	
	public static void addTable(ResultSet rs){
		String []data=new String[6];
		try {
			while (rs.next()){
				for (int i=1;i<=6;i++){
					data[i-1]=rs.getString(i);
				}
				Consumer.model.addRow(data);
			}

		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	
}
