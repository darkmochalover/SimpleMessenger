package domain.util;

import java.awt.Component;
import java.awt.Font;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer{

	JButton jb;
	JTable table;
	
	public TableCell() {
		// TODO Auto-generated constructor stub
		jb = new JButton("Follow");
//		jb.addActionListener((ActionListener) this);
		jb.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jb.setVisible(true);
		
		jb.addActionListener(e -> {
			System.out.println(table.getValueAt(table.getSelectedRow(), 1));
		});

//		table.getValueAt(table.getSelectedRow(), 1);
	
	}
	
	@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// TODO Auto-generated method stub
		return jb;
	}
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
			int column) {
		// TODO Auto-generated method stub
		return jb;
	}
	
}