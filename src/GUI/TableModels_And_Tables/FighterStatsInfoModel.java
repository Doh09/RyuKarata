/*
 * This class is a TableModel that shows details about fighters.
 * It is more detailed than the other TableModel class that displays fighters.
 */
package GUI.TableModels_And_Tables;

import BE.Fighter;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class FighterStatsInfoModel extends AbstractTableModel {

    private static final String[] HEADER
            = {
                "Navn", "Grad", "Alder", "Vægt", "Højde", "Kata", "Kumite", "Kobudo", "Ledig"
            };

    private static final Class[] COLUMN_TYPE
            = {
                String.class, String.class, Integer.class, Integer.class, Integer.class, Integer.class, String.class, String.class, String.class, String.class
            };

    private ArrayList<Fighter> fighterList;

    public FighterStatsInfoModel(ArrayList<Fighter> fighterList) {
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
                return fighter.getGrade();
            case 2:
                return fighter.getAge();
            case 3:
                return fighter.getWeight();
            case 4:
                return fighter.getHeight();
            case 5:
                if (fighter.isKata()) {
                    return "Ja";
                } else {
                    return "Nej";
                }
            case 6:
                if (fighter.isKumite()) {
                    return "Ja";
                } else {
                    return "Nej";
                }
            case 7:
                if (fighter.isKobudo()) {
                    return "Ja";
                } else {
                    return "Nej";
                }
            case 8:
                if (fighter.isInGroup()) {
                    return "Nej";
                } else {
                    return "Ja";
                }
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
