/*
 * This TableModel class is used to display the battles of a GroupTournament.
 */
package GUI.TableModels_And_Tables;

import BE.Tournaments.Battles.Battle;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class GroupTournamentBattlesModel extends AbstractTableModel {

    private static final String[] HEADER
            = {
                "Red", "Blue", "Vinder af kampen"
            };

    private static final Class[] COLUMN_TYPE
            = {
                String.class, String.class, String.class
            };

    private ArrayList<Battle> battleList;

    public GroupTournamentBattlesModel(ArrayList<Battle> battleList) {
        this.battleList = battleList;
    }

    @Override
    public int getRowCount() {
        if (battleList != null) {
            return battleList.size();
        } else {
            return 0;
        }
    }

    @Override
    public int getColumnCount() {
        return HEADER.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Battle battle = battleList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                if (battle.getFighter1Red() == null) {
                    return "Ingen kæmper";
                }
                return battle.getFighter1Red().getName();
            case 1:
                if (battle.getFighter2Blue() == null) {
                    return "Ingen kæmper";
                }
                return battle.getFighter2Blue().getName();
            case 2:
                if (battle.getWinner() == null) {
                    return "Ikke afgjort";
                }
                return battle.getWinner().getName();
            case 3:
                return battle.getId();
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

    public int getBattleIndex(Battle battle) {
        return battleList.indexOf(battle);
    }

    public Battle getBattle(int index) {
        return battleList.get(index);
    }

    public void updateBattleList(ArrayList<Battle> battleList) {
        this.battleList = battleList;
        fireTableDataChanged();
    }

    public ArrayList<Battle> getBattleList() {
        return battleList;
    }

}
