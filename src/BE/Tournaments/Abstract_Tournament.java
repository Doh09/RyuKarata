/*
 * This class is an abstract class that holds the core values and 
 * methods shared among the 2 tournament types.
 * The class has the following managers:
 * - BattleManager
 * - ColorManager
 * - FighterManager
 * - StaffManager
 * Each of these managers are local to the tournament they belong to.
 * The class also holds 2 TreeMaps that together administrate the fighter requirements for this tournament.
 * The fighter requirements are used in the auto-distributing the system supports.
 */
package BE.Tournaments;

import BE.Tournaments.Battles.Battle;
import BLL.Managers.TournamentSpecific_Managers.FighterManager;
import BLL.Managers.TournamentSpecific_Managers.BattleManager;
import BLL.Managers.TournamentSpecific_Managers.ColorManager;
import BLL.Managers.TournamentSpecific_Managers.StaffManager;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public abstract class Abstract_Tournament extends Abstract_TournamentPart {

    protected final BattleManager battleManager = new BattleManager(getId());
    protected final ColorManager colorManager = new ColorManager(getId());
    protected final FighterManager fighterManager = new FighterManager(getId());
    protected final StaffManager staffManager = new StaffManager(getId());
    protected String type = "";
    protected String partOfLargerTournament = "";
    protected ArrayList<ArrayList<Battle>> battleLayers = new ArrayList<>(); //list of the heights/layers in the tournament, where 0 is the initial layer.
    protected boolean tournamentHasInitialized = false;
    protected TreeMap<String, Double> requirementsValue = new TreeMap<>();
    protected TreeMap<String, Boolean> requirementsBooleans = new TreeMap<>();

    public Abstract_Tournament(int iD) {
        super(iD);
        description = name + " - Turneringsbeskrivelse -";
    }

    /**
     *
     * @return the name of the SuperTournament this tournament is part of.
     */
    public String getPartOfLargerTournament() {
        return partOfLargerTournament;
    }

    /**
     * Set the name of the SuperTournament this tournament is part of.
     *
     * @param partOfLargerTournament
     */
    public void setPartOfLargerTournament(String partOfLargerTournament) {
        this.partOfLargerTournament = partOfLargerTournament;
    }

    /**
     *
     * @return a BattleManager holding all batles in this tournament.
     */
    public BattleManager getBattleManager() {
        return battleManager;
    }

    /**
     *
     * @return the tournament type.
     */
    public String getType() {
        return type;
    }

    /**
     * Set the tournament type.
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return a FighterManager holding all fighters in this tournament.
     */
    public FighterManager getFighterManager() {
        return fighterManager;
    }

    /**
     *
     * @return a StaffManager holding all StaffPersons in this tournament.
     */
    public StaffManager getStaffManager() {
        return staffManager;
    }

    /**
     *
     * @return a ColorManager holding all colors in this tournaments GUI.
     */
    public ColorManager getColorManager() {
        return colorManager;
    }

    /**
     *
     * @return an ArrayList with all battle layers in this tournament.
     */
    public ArrayList<ArrayList<Battle>> getBattleLayers() {
        return battleLayers;
    }

    /**
     * Set an ArrayList with all battle layers in this tournament to the given
     * list.
     *
     * @param battleLayers
     */
    public void setBattleLayers(ArrayList<ArrayList<Battle>> battleLayers) {
        this.battleLayers = battleLayers;
    }

    /**
     * 
     * @return whether or not the tournament has been initialized.
     */
    public boolean hasInitialized() {
        return tournamentHasInitialized;
    }

    /**
     * Set whether or not the tournament has been initialized.
     * @param tournamentHasInitialized 
     */
    public void setTournamentHasInitialized(boolean tournamentHasInitialized) {
        this.tournamentHasInitialized = tournamentHasInitialized;
    }

    /**
     * 
     * @return a TreeMap<String, Double> with the requirement values for the fighters in this tournament.
     */
    public TreeMap<String, Double> getRequirementsValue() {
        return requirementsValue;
    }

    /**
     * Set a TreeMap<String, Double> with the requirement values for the fighters in this tournament.
     * @param requirements 
     */
    public void setRequirementsValue(TreeMap<String, Double> requirements) {
        this.requirementsValue = requirements;
    }

    /**
     * 
     * @return a TreeMap<String, Boolean> with the requirement booleans for the fighters in this tournament.
     */
    public TreeMap<String, Boolean> getRequirementsBooleans() {
        return requirementsBooleans;
    }

    /**
     * Set a TreeMap<String, Boolean> with the requirement booleans for the fighters in this tournament.
     * @param requirementsBooleans 
     */
    public void setRequirementsBooleans(TreeMap<String, Boolean> requirementsBooleans) {
        this.requirementsBooleans = requirementsBooleans;
    }
   

}
