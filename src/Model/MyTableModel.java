package Model;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {
    private final Object[][] data;

    public MyTableModel(Object[][] objects) {
        data = objects;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return data[0].length;
    }

    @Override
    public synchronized Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public synchronized void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data[rowIndex][columnIndex] = aValue;
        fireTableCellUpdated(rowIndex,columnIndex);
    }
}
