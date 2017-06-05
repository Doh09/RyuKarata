/*
 * This class acts as TableModel for the warning lists that are used when editing a single battle through the tournament GUI.
 */
package GUI.TableModels_And_Tables;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class BattleWarningsTableModel extends AbstractTableModel {

    private static final String[] HEADER
            = {
                "Advarselstype"
            };

    private static final Class[] COLUMN_TYPE
            = {
                String.class
            };

    private ArrayList<String> warningList;

    public BattleWarningsTableModel(ArrayList<String> warningList) {
        this.warningList = warningList;
    }

    @Override
    public int getRowCount() {
        return warningList.size();
    }

    @Override
    public int getColumnCount() {
        return HEADER.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String warning = warningList.get(rowIndex);

        return warning;
    }

    @Override
    public String getColumnName(int column) {
        return HEADER[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return COLUMN_TYPE[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public int getWarningIndex(String warning) {
        return warningList.indexOf(warning);
    }

    public String getWarning(int index) {
        return warningList.get(index);
    }

    public void updateWarningList(ArrayList<String> warningList) {
        this.warningList = warningList;
        fireTableDataChanged();
    }

    public ArrayList<String> getWarningList() {
        return warningList;
    }

}
