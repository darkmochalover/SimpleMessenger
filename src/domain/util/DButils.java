package domain.util;

import java.awt.Component;
import java.awt.Font;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.*;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import domain.PublicUserInfo;

public class DButils {
	public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

	    ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }
	    
	    /* 행 선택 추가 */
	    DefaultTableModel model = new DefaultTableModel(data, columnNames);
//	    JTable table = new JTable(model);
//	    table.getColumnModel().getColumn(columnCount).setPreferredWidth(columnCount);
//	    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    
	    JTable table = new JTable();
		table.setModel(new DefaultTableModel(data, columnNames) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, true
			};
			public boolean isCellEditable(int row, int column)
			{
				return columnEditables[column];
			}
		});

	    return model;

	}
	
	public static JTable buildJTable(ResultSet rs) throws SQLException {

	    ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }
	    
	    /* 행 선택 추가 */
	    DefaultTableModel model = new DefaultTableModel(data, columnNames);
	    JTable table = new JTable(model);
	    table.getColumnModel().getColumn(columnCount).setPreferredWidth(columnCount);
	    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    return table;

	}
	
	public static DefaultTableModel buildPublicProfileTable (ResultSet rs) throws SQLException
	{
		ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();
		
		String[] colNames = {"이름", "아이디", "한 줄 소개", "status"};
		Vector<String> columnNames = new Vector<String>();
		
		int columnCount = metaData.getColumnCount();
		for (int column = 0; column < colNames.length; column++) {
	        columnNames.add(colNames[column]);
	    }
		
		// data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    Object[][] rowDatas = new Object[columnCount][colNames.length];
	    
	    int i=0;
	    
	    while(rs.next())
	    {
	    	
//	    	for (int i = 0; i < columnCount; i++) {
//	        	Object obj = rs.getObject(columnIndex);
	        	
	        	rowDatas[i] = new Object[]
		            		{
		            				rs.getString(1),		 	// 이름 
									rs.getString(2),			// 아이디 
									rs.getString(5),			// 한 줄 소개 
									rs.getString(9) 			// 상태 );
		            		};
//		    }
	        	i++;
	    }
	    
            
//	    while (rs.next()) {
//	        Vector<Object> vector = new Vector<Object>();
//	        for (int columnIndex = 1; columnIndex < columnCount; columnIndex++) {
//	        	Object obj = rs.getObject(columnIndex);
//	        	
//	            vector.add(new PublicUserInfo
//		            		(
//		            				rs.getString(1),		 	// 이름 
//									rs.getString(2),			// 아이디 
//									rs.getString(4),			// 한 줄 소개 
//									rs.getString(8) 			// 상태 );
//		            		)
//	            );
//	        }
//	        data.add(vector);
//	    }
	    
	    DefaultTableModel model = new DefaultTableModel(rowDatas, colNames);

	    return model;
	}

}

