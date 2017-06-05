/*
 * This class represents the TableModel part of the TreeTable.
 */
package GUI.TableModels_And_Tables.TreeTable;

import BE.Tournaments.Abstract_Tournament;
import BE.Tournaments.Abstract_TournamentPart;
import BLL.TournamentTypes.SuperTournament;
import java.util.Date;
import javax.swing.tree.TreeNode;
import org.netbeans.swing.outline.RowModel;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class TreeNodeRowModel implements RowModel {

    Class[] headerClasses = {String.class, Date.class, Date.class, Integer.class};
    String[] headerNames = {"Type", "Startdato", "Slutdato", "Turneringens ID"};

    public TreeNodeRowModel() {

    }

    @Override
    public Class getColumnClass(int column) {

        if (column >= 0 && column < headerClasses.length) {
            return headerClasses[column];
        }
        return null;
    }

    @Override
    public int getColumnCount() {
        return headerClasses.length;
    }

    @Override
    public String getColumnName(int column) {
        if (column >= 0 && column < headerNames.length) {
            return headerNames[column];
        }
        if (column == -1) {
            return "Turneringer";
        }
        return "Undefined";
    }

    @Override
    public Object getValueFor(Object node, int column) {
        TreeNode tn = (TreeNode) node;
        Abstract_Tournament at = null;
        SuperTournament st = null;
        if (tn.isLeaf()) {
            at = (Abstract_Tournament) tn;
        } else {
            st = (SuperTournament) tn;
        }
        Abstract_TournamentPart atp = (Abstract_TournamentPart) node;
        //"Navn", "Type", "Startdato", "Slutdato"
        switch (column) {
            case 0:
                if (at != null) { //If subTournament
                    return at.getType();
                } else if (st != null) { //else if superTournament
                    if (st.getId() != -1) { //If not root superTournament.
                        return "Overturnering";
                    }
                }
                return "";
            case 1:
                if (at != null) { //If subTournament
                    return at.getStartTime();
                } else if (st != null) { //else if superTournament
                    if (st.getId() != -1) { //If not root superTournament.
                        return st.getStartTime();
                    }
                }
                break;
            case 2:
                if (at != null) { //If subTournament
                    return at.getEndTime();
                } else if (st != null) { //else if superTournament
                    if (st.getId() != -1) { //If not root superTournament.
                        return st.getEndTime();
                    }
                }
                break;
            case 3:
                if (at != null) { //If subTournament
                    return at.getId();
                } else if (st != null) { //else if superTournament
                    if (st.getId() != -1) { //If not root superTournament.
                        return st.getId();
                    }
                }
                break;
            default:
                assert false;
        }
        return null;
    }

    @Override
    public boolean isCellEditable(Object node, int column) {
        return false;
    }

    @Override
    public void setValueFor(Object node, int column, Object value) {
        //do nothing, nothing is editable
    }
}
