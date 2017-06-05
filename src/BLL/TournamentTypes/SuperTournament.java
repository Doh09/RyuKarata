/*
 * This class is a SuperTournament class that can hold supertournaments or subtournaments (Abstract_Tournament).
 * Tecnhically it can hold all classes extending Abstract_TournamentPart, but only the 2 mentioned classes are used.
 * The root tournament is the only supertournament holding other supertournaments.
 * All objects held are kept in their respective managers, this class only has references to them.
 */
package BLL.TournamentTypes;

import BE.Tournaments.Abstract_Tournament;
import BE.Tournaments.Abstract_TournamentPart;
import BLL.Managers.Singleton_Managers.TournamentManager;
import BLL.Managers.TournamentSpecific_Managers.FighterManager;
import BLL.Managers.TournamentSpecific_Managers.StaffManager;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.tree.TreeNode;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class SuperTournament extends Abstract_TournamentPart implements TreeNode {

    protected final FighterManager fighterManager = new FighterManager(getId());
    protected final StaffManager staffManager = new StaffManager(getId());
    protected String type; //the tournament type
    private ArrayList<Abstract_TournamentPart> subTournaments = new ArrayList<>();

    public SuperTournament(int iD) {
        super(iD);
        description = name + " - Overturneringsbeskrivelse -";
    }

    public ArrayList<Abstract_TournamentPart> getSubTournaments() {
        return subTournaments;
    }

    public void setSubTournaments(ArrayList<Abstract_TournamentPart> subTournaments) {
        this.subTournaments = subTournaments;
    }

    public ArrayList<Abstract_Tournament> getAllAsAbstract_TournamentList() {
        ArrayList<Abstract_Tournament> tempList = new ArrayList<>();
        for (Abstract_TournamentPart atp : subTournaments) {
            tempList.add(((Abstract_Tournament) atp));
        }
        return tempList;
    }

    public void removeFromList(Abstract_TournamentPart atp) {
        subTournaments.remove(atp);
    }

    public void setSubTournamentsUsingSuperTournaments(ArrayList<SuperTournament> newSubTournaments) {
        ArrayList<Abstract_TournamentPart> tempList = new ArrayList<>();
        for (Abstract_TournamentPart atp : newSubTournaments) {
            tempList.add((Abstract_TournamentPart) atp);
        }
        this.subTournaments = tempList;
    }

    public FighterManager getFighterManager() {
        return fighterManager;
    }

    public StaffManager getStaffManager() {
        return staffManager;
    }

    public void removeAllSubtournaments() {
        ArrayList<Abstract_Tournament> tempList = new ArrayList<>();
        for (Abstract_TournamentPart atp : subTournaments) {
            if (atp instanceof Abstract_Tournament) {
                tempList.add((Abstract_Tournament) atp);
            }
            for (Abstract_Tournament st : tempList) {
                TournamentManager.getInstance().removeTournamentFromMap(st.getId()); //Remove from tournamentmanager
            }
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    ////Tree node implementation from here on down.
    @Override
    public TreeNode getChildAt(int i) {
//        subTournaments.add(new SuperTournament(0));
        return subTournaments.get(i);
    }

    @Override
    public int getChildCount() {
        return subTournaments.size();
    }

    @Override
    public TreeNode getParent() {
        return null; //return null, because superTournaments are the highest tier parents.
    }

    @Override
    public int getIndex(TreeNode tn) {
        Abstract_TournamentPart atp = (Abstract_TournamentPart) tn;
        for (int i = 0; i < subTournaments.size(); i++) {
            if (atp.getId() == subTournaments.get(i).getId()) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean getAllowsChildren() {
        return subTournaments.size() > 0;
    }

    @Override
    public boolean isLeaf() {
        return subTournaments.size() < 0;
    }

    @Override
    public Enumeration children() {
        return null; //unfinished.
    }

}
