/*
 * This class handles the colors of a single tournament.
 * The colors are retrieved as strings.
 * Each color is defined by the hex value its string holds.
 */
package BLL.Managers.TournamentSpecific_Managers;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class ColorManager {

    /**
     * Variables
     */
    private final int tournamentId; //The id of the tournament this manager belongs to.
    private String cellColorNoFighters = "fillColor=#ffffcc";
    private String cellColorNoWinnerFound = "fillColor=#cce6ff";
    private String cellColorWinnerFound = "fillColor=#66ff33";

    public ColorManager(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    /**
     * @return the color used if the battle has no fighters.
     */
    public String getCellColorNoFighters() {
        return cellColorNoFighters;
    }

    /**
     * Set the color used if the battle has no fighters.
     *
     * @param cellColourNoFighters
     */
    public void setCellColorNoFighters(String cellColourNoFighters) {
        this.cellColorNoFighters = cellColourNoFighters;
    }

    /**
     * @return the color used if the battle has one or two fighters, but no
     * winner has been selected for it.
     */
    public String getCellColorNoWinnerFound() {
        return cellColorNoWinnerFound;
    }

    /**
     * Set the color used if the battle has one or two fighters, but no
     * winner has been selected for it.
     * @param cellColourNoWinnerFound 
     */
    public void setCellColorNoWinnerFound(String cellColourNoWinnerFound) {
        this.cellColorNoWinnerFound = cellColourNoWinnerFound;
    }

    /**
     * @return the color used if the battle winner has been decided/selected.
     */
    public String getCellColorWinnerFound() {
        return cellColorWinnerFound;
    }

    /**
     * Set the color used if the battle winner has been decided/selected.
     * @param cellColourWinnerFound 
     */
    public void setCellColorWinnerFound(String cellColourWinnerFound) {
        this.cellColorWinnerFound = cellColourWinnerFound;
    }

    /**
     * @return the id of the tournament this manager belongs to.
     */
    public int getTournamentId() {
        return tournamentId;
    }

}
