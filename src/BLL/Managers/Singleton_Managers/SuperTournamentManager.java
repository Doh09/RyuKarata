/*
 * This manager class holds all SuperTournaments in the system.
 */
package BLL.Managers.Singleton_Managers;

import BE.Tournaments.Abstract_Tournament;
import BLL.TournamentTypes.SuperTournament;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class SuperTournamentManager {

    private static SuperTournamentManager instance;

    /**
     * Variabler
     */
    private TreeMap<Integer, SuperTournament> superTournaments; //The Integer is the tournaments Id.

    /**
     * Constructor, uses Singleton.
     */
    private SuperTournamentManager() {
        setTurneringer(new TreeMap<>());

    }

    /**
     * Singleton pattern
     *
     * @return
     */
    public static SuperTournamentManager getInstance() {
        if (instance == null) {
            instance = new SuperTournamentManager();
        }
        return instance;
    }

    /**
     * Returns a Map with all tournaments.
     *
     * @return
     */
    public TreeMap<Integer, SuperTournament> getTournaments() {
        return superTournaments;
    }

    /**
     * Replaces the whole map of all current tournaments, with the given one.
     *
     * @param tournaments
     */
    public void setTurneringer(TreeMap<Integer, SuperTournament> tournaments) {
        this.superTournaments = tournaments;
    }

    public SuperTournament getTournamentByIndex(int i) {
        return superTournaments.get(i);
    }

    public void addOrUpdateTournamentToMap(SuperTournament t) {
        superTournaments.put(t.getId(), t);
    }

    public void removeTournamentFromMap(int i) {
        superTournaments.remove(i);
    }

    public SuperTournament createSuperTournament() {

        int id = 0;

        for (SuperTournament tournament : getAllAsArrayList()) {
            if (tournament.getId() >= id) {
                id = tournament.getId() + 1;
            }
        }

        SuperTournament st = new SuperTournament(id);
        addOrUpdateTournamentToMap(st);

        return st;
    }

    /**
     * Converts the synchronized map to a list and returns it. The list returned
     * may not be synchronized, unless you make it so.
     *
     * @return
     */
    public ArrayList<SuperTournament> getAllAsArrayList() {

        ArrayList<SuperTournament> list = new ArrayList<>(superTournaments.values());
        return list;
    }

    public ArrayList<SuperTournament> searchForTournamentsByName(String searchString, int searchMode, boolean searchByDate, Date startRange, Date endRange) {
        ArrayList<SuperTournament> tempList = new ArrayList<>();
        for (SuperTournament st : getAllAsArrayList()) { //Search through each SuperTournament
            for (Abstract_Tournament at : st.getAllAsAbstract_TournamentList()) { //Look for match among the tournaments in the supertournament
                switch (searchMode) {
                    case 0:
                        if (at.getName().toLowerCase().contains(searchString.toLowerCase()) && !tempList.contains(st)) { //If a match is found and the list doesn't already contain this tournament.
                            if (searchByDate) { //If searching by date as well as name
                                if (isWithinDateRange(at, startRange, endRange)) {
                                    tempList.add(st); //add the tournament
                                }
                            } else {
                                tempList.add(st); //add the tournament
                            }
                        }
                        break;
                    case 1:
                        if (at.getName().toLowerCase().matches(searchString.toLowerCase()) && !tempList.contains(st)) { //If a match is found and the list doesn't already contain this tournament.
                            if (searchByDate) { //If searching by date as well as name
                                if (isWithinDateRange(at, startRange, endRange)) {
                                    tempList.add(st); //add the tournament
                                }
                            } else {
                                tempList.add(st); //add the tournament
                            }
                        }
                        break;
                    case 2:
                        if (at.getName().toLowerCase().startsWith(searchString.toLowerCase()) && !tempList.contains(st)) { //If a match is found and the list doesn't already contain this tournament.
                            if (searchByDate) { //If searching by date as well as name
                                if (isWithinDateRange(at, startRange, endRange)) {
                                    tempList.add(st); //add the tournament
                                }
                            } else {
                                tempList.add(st); //add the tournament
                            }
                        }
                        break;
                    case 3:
                        if (at.getName().toLowerCase().endsWith(searchString.toLowerCase()) && !tempList.contains(st)) { //If a match is found and the list doesn't already contain this tournament.
                            if (searchByDate) { //If searching by date as well as name
                                if (isWithinDateRange(at, startRange, endRange)) {
                                    tempList.add(st); //add the tournament
                                }
                            } else {
                                tempList.add(st); //add the tournament
                            }
                        }
                        break;
                }
            }

        }
        return tempList;
    }

    private boolean isWithinDateRange(Abstract_Tournament at, Date startRange, Date endRange) {
        if (at != null && startRange != null && endRange != null) {
            Date endDatePlusOne = new Date(endRange.getTime() + (1000 * 60 * 60 * 24));
            //Adjust dates by one to include the date displayed.
            if (at.getStartTime().after(startRange)
                    && (at.getEndTime().before(endDatePlusOne))) {
                return true;
            }
        }//Start range, atsr, ater, end range
        return false;
    }

    public ArrayList<SuperTournament> searchForTournamentsByDateOnly(Date startRange, Date endRange) {
        ArrayList<SuperTournament> tempList = new ArrayList<>();
        for (SuperTournament st : getAllAsArrayList()) { //Search through each SuperTournament
            for (Abstract_Tournament at : st.getAllAsAbstract_TournamentList()) { //Look for match among the tournaments in the supertournament
                if (isWithinDateRange(at, startRange, endRange)) {
                    tempList.add(st);
                }
                break;
            }
        }
        return tempList;
    }
}
