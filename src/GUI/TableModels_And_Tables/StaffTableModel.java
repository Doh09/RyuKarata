/*
 * This TableModel displays the name and position of the StaffPersons it holds.
 */
package GUI.TableModels_And_Tables;

import BE.StaffPerson;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class StaffTableModel extends AbstractTableModel
{

    private static final String[] HEADER
            =
            {
                "Navn", "Stilling"
            };

    private static final Class[] COLUMN_TYPE
            =
            {
                String.class, String.class
            };

    private ArrayList<StaffPerson> staffMemberList;

    public StaffTableModel(ArrayList<StaffPerson> fighterList)
    {
        this.staffMemberList = fighterList;
    }

    @Override
    public int getRowCount()
    {
        return staffMemberList.size();
    }

    @Override
    public int getColumnCount()
    {
        return HEADER.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        StaffPerson staffMember = staffMemberList.get(rowIndex);

        switch (columnIndex)
        {
            case 0:
                return staffMember.getName();
            case 1:
                return staffMember.getPosition() == null ? "Ingen Position"
                        : staffMember.getPosition().toString();
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

    public int getStaffMemberIndex(StaffPerson staffMember)
    {
        return staffMemberList.indexOf(staffMember);
    }

    public StaffPerson getStaffMember(int index)
    {
        return staffMemberList.get(index);
    }

    public void updateStaffMemberList(ArrayList<StaffPerson> staffMemberList)
    {
        this.staffMemberList = staffMemberList;
        fireTableDataChanged();
    }

    public ArrayList<StaffPerson> getStaffList()
    {
        return staffMemberList;
    }

}
