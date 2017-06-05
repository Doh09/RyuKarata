/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.TableModels;

import BE.StaffPerson;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class StaffTableModel extends AbstractTableModel {

    /**
     * Language strings
     */
    private static String nameHeader = "Navn";
    private static String posHeader = "Position";
    //
    private static final String[] HEADER
            = {
                nameHeader, posHeader
            };

    private static final Class[] COLUMN_TYPE
            = {
                String.class, String.class
            };

    private List<StaffPerson> staffMemberList;

    public StaffTableModel(List<StaffPerson> fighterList) {
        this.staffMemberList = fighterList;

    }

    @Override
    public int getRowCount() {
        return staffMemberList.size();
    }

    @Override
    public int getColumnCount() {
        return HEADER.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StaffPerson staffMember = staffMemberList.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return staffMember.getName();
            case 1:
                return staffMember.getPosition().toString();
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

    public int getStaffMemberIndex(StaffPerson staffMember) {
        return staffMemberList.indexOf(staffMember);
    }

    public StaffPerson getStaffMember(int index) {
        return staffMemberList.get(index);
    }

    public void updateStaffMemberList(List<StaffPerson> staffMemberList) {
        this.staffMemberList = staffMemberList;
        fireTableDataChanged();
    }

    public List<StaffPerson> getStaffList() {
        return staffMemberList;
    }

    //------------------- Language related -------------------
    /**
     * Language strings, written in Danish as standard, changed to their English
     * counterparts if language is English.
     */
    public void setTextsEnglish() {
        nameHeader = "Name";
        posHeader = "Position";
        refreshTexts();
    }

    private void refreshTexts() {
        HEADER[0] = nameHeader;
        HEADER[1] = posHeader;
        fireTableStructureChanged();
    }
}
