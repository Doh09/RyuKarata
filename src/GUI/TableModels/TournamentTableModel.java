/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.TableModels;

import BE.Tournaments.Abstract_Tournament;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class TournamentTableModel extends AbstractTableModel {

    /**
     * Language strings
     */
    private static String nameHeader = "Navn";
    private static String typeHeader = "Type";
    private static String startDateHeader = "Startdato";
    private static String endDateHeader = "Slutdato";
    private static String partOfHeader = "Del af";
    //

    private static final String[] HEADER
            = {
                nameHeader, typeHeader, startDateHeader, endDateHeader, partOfHeader
            };

    private static final Class[] COLUMN_TYPE
            = {
                String.class, String.class, Date.class, Date.class, String.class
            };

    private List<Abstract_Tournament> tournamentList;

    public TournamentTableModel(List<Abstract_Tournament> tournamentList) {
        this.tournamentList = tournamentList;

    }

    @Override
    public int getRowCount() {
        return tournamentList.size();
    }

    @Override
    public int getColumnCount() {
        return HEADER.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Abstract_Tournament tournament = tournamentList.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return tournament.getName();
            case 1:
                return tournament.getType();
            case 2:
                return tournament.getStartTime();
            case 3:
                return tournament.getEndTime();
            case 4:
                return tournament.getPartOfLargerTournament();
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

    public int getTournamentIndex(Abstract_Tournament tournament) {
        return tournamentList.indexOf(tournament);
    }

    public Abstract_Tournament getTournament(int index) {
        return tournamentList.get(index);
    }

    public void updateTournamentListList(List<Abstract_Tournament> tournamentList) {
        this.tournamentList = tournamentList;
        fireTableDataChanged();
    }

    public List<Abstract_Tournament> getTournamentList() {
        return tournamentList;
    }

    //------------------- Language related -------------------
    /**
     * Language strings, written in Danish as standard, changed to their English
     * counterparts if language is English.
     */
    public void setTextsEnglish() {
        nameHeader = "Name";
        typeHeader = "Position";
        startDateHeader = "Start date";
        endDateHeader = "End date";
        partOfHeader = "Part of";
        refreshTexts();
    }

    private void refreshTexts() {
        HEADER[0] = nameHeader;
        HEADER[1] = typeHeader;
        HEADER[2] = startDateHeader;
        HEADER[3] = endDateHeader;
        HEADER[4] = partOfHeader;
        fireTableStructureChanged();
    }

}
