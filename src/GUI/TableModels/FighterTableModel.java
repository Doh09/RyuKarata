/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.TableModels;

import BE.Fighter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class FighterTableModel extends AbstractTableModel
{

    /**
     * Language strings
     */
    private static String nameHeader = "Navn";
    //
    private static final String[] HEADER =
    {
        nameHeader,"Klub"
    };

    private static final Class[] COLUMN_TYPE =
    {
        String.class,String.class
    };

    private List<Fighter> fighterList;

    public FighterTableModel(List<Fighter> fighterList)
    {
        this.fighterList = fighterList;
    }

    @Override
    public int getRowCount()
    {
        return fighterList.size();
    }

    @Override
    public int getColumnCount()
    {
        return HEADER.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Fighter fighter = fighterList.get(rowIndex);

        switch (columnIndex)
        {
            case 0:
                return fighter.getName();
            case 1:
                return fighter.getClubName();
        }
        return null;
    }

    @Override
    public String getColumnName(int column)
    {
        return HEADER[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return COLUMN_TYPE[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return false;
    }

    public int getFighterIndex(Fighter fighter)
    {
        return fighterList.indexOf(fighter);
    }

    public Fighter getFighter(int index)
    {
        return fighterList.get(index);
    }

    public void updateFighterList(List<Fighter> fighterList)
    {
        this.fighterList = fighterList;
        fireTableDataChanged();
    }

    public List<Fighter> getFighterList()
    {
        return fighterList;
    }

    //------------------- Language related -------------------
    /**
     * Language strings, written in Danish as standard, changed to their English
     * counterparts if language is English.
     */
    public void setTextsEnglish()
    {
        nameHeader = "Name";
        refreshTexts();
    }

    private void refreshTexts()
    {
        HEADER[0] = nameHeader;
        fireTableStructureChanged();
    }

}
