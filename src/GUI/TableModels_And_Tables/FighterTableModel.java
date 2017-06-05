/*
 * This class is a TableModel that shows details about fighters.
 * This TableModel only shows the name and club of the fighters.
 */
package GUI.TableModels_And_Tables;

import BE.Fighter;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class FighterTableModel extends AbstractTableModel {

    private static final String[] HEADER
            = {
                "Navn", "Klub"
            };

    private static final Class[] COLUMN_TYPE
            = {
                String.class, String.class
            };

    private ArrayList<Fighter> fighterList;

    public FighterTableModel(ArrayList<Fighter> fighterList) {
        this.fighterList = fighterList;
    }

    @Override
    public int getRowCount() {
        return fighterList.size();
    }

    @Override
    public int getColumnCount() {
        return HEADER.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Fighter fighter = fighterList.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return fighter.getName();
            case 1:
                return fighter.getClubName();
            case 2:
                return fighter.getFighterId();
        }
        return null;
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

    public int getFighterIndex(Fighter fighter) {
        return fighterList.indexOf(fighter);
    }

    public Fighter getFighter(int index) {
        return fighterList.get(index);
    }

    public void updateFighterList(ArrayList<Fighter> fighterList) {
        this.fighterList = fighterList;
        fireTableDataChanged();
    }

    public ArrayList<Fighter> getFighterList() {
        return fighterList;
    }

}
