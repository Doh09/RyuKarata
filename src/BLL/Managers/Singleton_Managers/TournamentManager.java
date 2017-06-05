/*
 * This manager class holds all Abstract_Tournaments in the system.
 */
package BLL.Managers.Singleton_Managers;

import BE.Tournaments.Abstract_Tournament;
import BLL.TournamentTypes.CupTournament;
import BLL.TournamentTypes.GroupTournament;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class TournamentManager {

    private static TournamentManager instance;

    /**
     * Variabler
     */
    private Map<Integer, Abstract_Tournament> tournaments; //The Integer is the tournaments Id.

    /**
     * Constructor, uses Singleton.
     */
    private TournamentManager() {
        setTurneringer(new HashMap<>());
        
    }

    /**
     * Singleton pattern
     *
     * @return
     */
    public static TournamentManager getInstance() {
        if (instance == null) {
            instance = new TournamentManager();
        }
        return instance;
    }

    /**
     * Returns a Map with all tournaments.
     *
     * @return
     */
    public Map<Integer, Abstract_Tournament> getTournaments() {
        return tournaments;
    }

    /**
     * Replaces the whole map of all current tournaments, with the given one.
     *
     * @param tournaments
     */
    public void setTurneringer(HashMap<Integer, Abstract_Tournament> tournaments) {
        this.tournaments = Collections.synchronizedMap(tournaments);
    }

    public Abstract_Tournament getTournamentByIndex(int i) {
        return tournaments.get(i);
    }

    public void addOrUpdateTournamentToMap(Abstract_Tournament t) {
        tournaments.put(t.getId(), t);
    }

    public void removeTournamentFromMap(int i) {
        tournaments.remove(i);
    }

    /**
     * Converts the synchronized map to a list and returns it. The list returned
     * may not be synchronized, unless you make it so.
     *
     * @return
     */
    public List<Abstract_Tournament> getAllAsArrayList() {

        List<Abstract_Tournament> list = new ArrayList<>(tournaments.values());
        return list;
    }

    public CupTournament createCupTournament() {

        int id = 0;

        for (Abstract_Tournament tournament : getAllAsArrayList()) {
            if (tournament.getId() >= id) {
                id = tournament.getId() + 1;
            }
        }

        CupTournament at = new CupTournament(id);
        at.setType("Cup");
        addOrUpdateTournamentToMap(at);

        return at;
    }

    public GroupTournament createGroupTournament() {

        int id = 0;

        for (Abstract_Tournament tournament : getAllAsArrayList()) {
            if (tournament.getId() >= id) {
                id = tournament.getId() + 1;
            }
        }
        GroupTournament at = new GroupTournament(id);
        at.setType("Group");
        addOrUpdateTournamentToMap(at);

        return at;
    }

}
