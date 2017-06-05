/*
 * This abstract class holds the core values for objects that are part of the tournament.
 */
package BE.Tournaments;

import java.util.Date;
import java.util.Enumeration;
import javax.swing.tree.TreeNode;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public abstract class Abstract_TournamentPart implements TreeNode {

    protected final int id;
    protected String name = "Ikke navngivet";
    protected Date startTime;
    protected Date endTime;
    protected Abstract_TournamentPart tournamentPartParent = null;
    protected int TreeNodeID;
    protected String description = "";

    public Abstract_TournamentPart(int iD) {
        this.id = iD;
    }

    //Getters & setters
    public void setName(String name) {
        this.name = name;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public int getId() {
        return id;
    }

    public void setParentTournamentPart(Abstract_TournamentPart tournamentPartParent) {
        this.tournamentPartParent = tournamentPartParent;
    }

    public Abstract_TournamentPart getParentAsTournamentPart() {
        return tournamentPartParent;
    }

    public int getParentId() {
        return tournamentPartParent.getId();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    ////TreeNode methods from here on down.
    @Override
    public TreeNode getChildAt(int i) {
        return null;//can't have children.
    }

    @Override
    public int getChildCount() {
        return 0;//can't have children.
    }

    @Override
    public TreeNode getParent() {
        return (TreeNode) tournamentPartParent; //dunno how to get tournamentPartParent yet.
    }

    @Override
    public int getIndex(TreeNode tn) {
        return TreeNodeID;
    }

    @Override
    public boolean getAllowsChildren() {
        return false;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public Enumeration children() {
        return null;
    }
}
