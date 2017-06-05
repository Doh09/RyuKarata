/*
 * This class represents a battle between 2 fighters.
 * The class holds 2 fighters, their points, who won the battle and any potential battle time measured.
 */
package BE.Tournaments.Battles;

import BE.Fighter;
import BE.StopWatch;
import BE.Tournaments.Abstract_TournamentPart;
import java.util.ArrayList;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class Battle extends Abstract_TournamentPart {

    private Fighter red = null;
    private Fighter blue = null;
    private Fighter winner = null;
    private boolean pause = true;
    private boolean beingEdited = false;
    private Battle previousBattle1 = null;
    private Battle previousBattle2 = null;
    private Battle nextBattle = null;
    private StopWatch time;
    private int redPoint;
    private int bluePoint;
    private ArrayList<String> redWarnings = new ArrayList<>();
    private ArrayList<String> blueWarnings = new ArrayList<>();

    /**
     * Constructor with only ID.
     *
     * @param iD
     */
    public Battle(int iD) {
        super(iD);
    }

    /**
     * Constructor where 2 fighters are assigned.
     *
     * @param iD
     * @param fighter1
     * @param fighter2
     */
    public Battle(int iD, Fighter fighter1, Fighter fighter2) {
        super(iD);

        this.red = fighter1;
        this.blue = fighter2;
        this.time = new StopWatch();
    }

    public void setFighter1Red(Fighter red) {
        this.red = red;
    }

    public void setFighter2Blue(Fighter blue) {
        this.blue = blue;
    }

    public void setPreviousBattle1(Battle previousBattle1) {
        this.previousBattle1 = previousBattle1;
    }

    public void setPreviousBattle2(Battle previousBattle2) {
        this.previousBattle2 = previousBattle2;
    }

    public void setNextBattle(Battle nextBattle) {
        this.nextBattle = nextBattle;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public boolean isPause() {
        return pause;
    }

    public Fighter getFighter1Red() {
        return red;
    }

    public Fighter getFighter2Blue() {
        return blue;
    }

    public Battle getPreviousBattle1() {
        return previousBattle1;
    }

    public Battle getNextBattle() {
        return nextBattle;
    }

    public Battle getPreviousBattle2() {
        return previousBattle2;
    }

    /**
     * Checks if this battle is the finale of the tournament.
     *
     * @return true if is the finale.
     */
    public boolean isFinale() {
        return nextBattle == null;
    }

    /**
     * Checks if this battle is one of the initial ones.
     *
     * @return true if this battle is one of the initial battles in the
     * tournament.
     */
    public boolean isOneOfFirstMatches() {
        return (previousBattle1 == null && previousBattle2 == null);
    }

    public Fighter getWinner() {
        return winner;
    }

    public void setWinner(Fighter winner) {
        this.winner = winner;
    }

    /**
     * Check if the given fighter is to be found in this battle.
     *
     * @param f
     * @return true if the fighter is found, otherwise return false.
     */
    public boolean battleContainsFighter(Fighter f) {
        if (red != null && f != null) {
            if (red.getFighterId() == f.getFighterId()) {
                return true;
            }
        }
        if (blue != null && f != null) {
            if (blue.getFighterId() == f.getFighterId()) {
                return true;
            }
        }
        return false;
    }

    public StopWatch getStopWatch() {
        return time;
    }

    @Override
    /**
     * Makes a string of the battle in the format: "Fighter1.name \nVS\n
     * Fighter2.name"
     */
    public String toString() {
        String f1Name = "Ingen kæmper";
        String f2Name = "Ingen kæmper";
        if (red != null) {
            f1Name = red.getName();
        }
        if (blue != null) {
            f2Name = blue.getName();
        }

        String stringToReturn
                = getId() + "."
                + "\n" + f1Name
                + "\n VS "
                + "\n" + f2Name;

        return stringToReturn;
    }

    public int getRedPoint() {
        return redPoint;
    }

    public void setRedPoint(int redPoint) {
        this.redPoint = redPoint;
    }

    public int getBluePoint() {
        return bluePoint;
    }

    public void setBluePoint(int bluePoint) {
        this.bluePoint = bluePoint;
    }

    /**
     * If a winner has been found, returns the fighter who didn't win. Otherwise
     * return null.
     *
     * @return the fighter who didn't win, or null.
     */
    public Fighter getLoser() {
        if (red != null && blue != null) {
            if (red.getFighterId() == winner.getFighterId()) {
                return blue;
            }
            if (blue.getFighterId() == winner.getFighterId()) {
                return red;
            }
        }

        return null;
    }

    public boolean isBeingEdited() {
        return beingEdited;
    }

    public void setBeingEdited(boolean beingEdited) {
        this.beingEdited = beingEdited;
    }

    public ArrayList<String> getRedWarnings() {
        return redWarnings;
    }

    public void setRedWarnings(ArrayList<String> redWarnings) {
        this.redWarnings = redWarnings;
    }

    public ArrayList<String> getBlueWarnings() {
        return blueWarnings;
    }

    public void setBlueWarnings(ArrayList<String> blueWarnings) {
        this.blueWarnings = blueWarnings;
    }

}
