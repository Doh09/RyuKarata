/*
 * This TableModel class is used to display Abstract_Tournaments when managing
 * them through a SuperTournament editing window.
 */
package GUI.TableModels_And_Tables;

import BE.Tournaments.Abstract_Tournament;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class GroupTournamentsTableModel extends AbstractTableModel
{

    private static final String[] HEADER
            =
            {
                "Navn", "ID", "Antal kæmpere"
            };

    private static final Class[] COLUMN_TYPE
            =
            {
                String.class, Integer.class, Integer.class
            };

    private ArrayList<Abstract_Tournament> tournamentList;

    public GroupTournamentsTableModel(ArrayList<Abstract_Tournament> tournamentList)
    {
        this.tournamentList = tournamentList;
    }

    @Override
    public int getRowCount()
    {
        return tournamentList.size();
    }

    @Override
    public int getColumnCount()
    {
        return HEADER.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Abstract_Tournament tournament = tournamentList.get(rowIndex);

        switch (columnIndex)
        {
            case 0:
                return tournament.getName();
            case 1:
                return tournament.getId();
            case 2:
                return tournament.getFighterManager().getAllAsArrayList().size();
           
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

    public int getTournamentIndex(Abstract_Tournament tournament)
    {
        return tournamentList.indexOf(tournament);
    }

    public Abstract_Tournament getTournament(int index)
    {
        return tournamentList.get(index);
    }

    public void updateTournamentList(ArrayList<Abstract_Tournament> tournamentList)
    {
        this.tournamentList = tournamentList;
        fireTableDataChanged();
    }

    public ArrayList<Abstract_Tournament> getTournamentList()
    {
        return tournamentList;
    }

}
