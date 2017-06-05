/*
 * This class handles the fighters of a single tournament.
 * The class has a TreeMap with the fighters.
 * CRUD Functionality is implemented.
 * The TreeMap with all the fighters can be retrieved as an ArrayList
 * for iteration purposes.
 */
package BLL.Managers.TournamentSpecific_Managers;

import BE.Fighter;
import BE.Users.Clubs.Club;
import BLL.Managers.Singleton_Managers.ClubManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class FighterManager {

    /**
     * Variables
     */
    private Map<Integer, Fighter> fighters; //A map of all fighters held by this manager.

    private final int tournamentId; //The id of the tournament this manager belongs to.

    /**
     * Constructor, is purposely not made Singleton, as the program potentially
     * runs more than one tournament at a time.
     *
     * @param tournamentId
     */
    public FighterManager(int tournamentId) {
        this.tournamentId = tournamentId;
        fighters = Collections.synchronizedMap(new TreeMap<Integer, Fighter>());

    }

    /**
     * Returns a map with all fighters in the tournament.
     *
     * @return
     */
    public Map<Integer, Fighter> getFighters() {
        return fighters;
    }

    /**
     * Replace all fighters in this fightermanagers Map, with the given Map.
     *
     * @param fighters
     */
    public void setFighters(TreeMap<Integer, Fighter> fighters) {
        this.fighters = Collections.synchronizedMap(fighters);
    }

    /**
     * Method that merges a list of fighters into the Map of the manager. This
     * means that each fighter from the list given is added to the map, if
     * another fighter with the same ID is not already in the map.
     * This is to prevent overriding existing fighters and their stats.
     *
     * @param fighterList
     * @return a list of the fighters added.
     */
    public List<Fighter> mergeFighterCollections(List<Fighter> fighterList) {
        List<Fighter> tempList = new ArrayList<>();

        for (Fighter f : fighterList) //add new
        {
            if (!fighters.containsKey(f.getFighterId())) {
                fighters.put(f.getFighterId(), f);
                tempList.add(f);
            }
        }
        return tempList;
    }

    /**
     * Replaces all fighters in the fightermanagers map with the given list of
     * fighters.
     *
     * @param fighters
     */
    public void setFighters(ArrayList<Fighter> fighters) {
        TreeMap<Integer, Fighter> fighterMap = new TreeMap();
        for (Fighter f : fighters) {
            fighterMap.put(f.getFighterId(), f);
        }
        this.fighters = Collections.synchronizedMap(fighterMap);
    }

    /**
     * @param i, the index used to find the fighter, usually the fighterId.
     * @return a fighter based on his index/key.
     */
    public Fighter getFighterByIndex(int i) {
        return fighters.get(i);
    }

    /**
     * Add a fighter to the managers map. If the ID of the fighter is already in
     * the map, it is overwritten.
     *
     * @param f
     */
    public void addOrUpdateFighterToMap(Fighter f) {
        if (f != null) {
            fighters.put(f.getFighterId(), f);
        }
    }

    /**
     * Removes a fighter from the managers map.
     *
     * @param i, the index/key of the fighter to remove.
     */
    public void removeFighterFromMap(int i) {
        fighters.remove(i);
    }

    /**
     * Converts the synchronized map to a list and returns it. The list returned
     * may not be synchronized, unless you make it so.
     *
     * @return
     */
    public ArrayList<Fighter> getAllAsArrayList() {
        ArrayList<Fighter> list = new ArrayList<>(fighters.values());
        return list;
    }

    /**
     * Returns the index of the fighter given.
     *
     * @param fighter
     * @return
     */
    public int getIndex(Fighter fighter) {
        return getAllAsArrayList().indexOf(fighter);
    }

    /**
     * @return the id of the tournament this manager belongs to.
     */
    public int getTournamentId() {
        return tournamentId;
    }

    /**
     * Creates a fighter, the fighter receives a unique fighterID which is used
     * to store him in the managers map he is also put in.
     *
     * @param name
     * @param communalMeal, whether or not the fighter is signed up to eat at
     * the tournament.
     * @param gender
     * @param grade
     * @param age
     * @param weight
     * @param height
     * @param kata, whether or not the fighter participates in kata fighting.
     * @param kumite, whether or not the fighter participates in kumite
     * fighting.
     * @param kobudo, whether or not the fighter participates in kobudo
     * fighting.
     * @param clubName, the name of the club the fighter is a member of.
     * @return the fighter created.
     */
    public Fighter createFighter(String name, boolean communalMeal,
            Fighter.Gender gender, int grade, int age, double weight,
            double height, boolean kata, boolean kumite, boolean kobudo, String clubName) {

        if (name.isEmpty() || name == null) {
            throw new IllegalArgumentException("Name can not be nothing");
        }

        int id = 0;

        for (Fighter f : getAllAsArrayList()) {
            if (f.getFighterId() >= id) {
                id = f.getFighterId() + 1;
            }
        }

        Club club = ClubManager.getInstance().createClub(clubName);
        Fighter fighter = new Fighter(id, name, gender, grade, age, weight, height, kata, kumite, kobudo, communalMeal, club.getId(), club.getClubName());

        club.addOrUpdateClubMemberToMap(fighter);
        addOrUpdateFighterToMap(fighter);

        return fighter;
    }

    /**
     * Adds a club name to a fighter, then returns him. Is used instead of a
     * setter method to set the club, to ensure a fighter cannot have his club
     * changed without it being through the manager. Also used if a fighter
     * needs to change his club.
     *
     * @param fighter
     * @param clubName, the name of the club the fighter is a member of.
     * @return the fighter set up.
     */
    public Fighter createFighter(Fighter fighter, String clubName) {

        if (fighter == null) {
            throw new IllegalArgumentException("Fighter can not be nothing");
        }

        int id = 0;

        for (Fighter f : getAllAsArrayList()) {
            if (f.getFighterId() >= id) {
                id = f.getFighterId() + 1;
            }
        }

        Club club = ClubManager.getInstance().createClub(clubName);
        Fighter newFighter = new Fighter(id, fighter.getName(), fighter.getGender(), fighter.getGrade(), fighter.getAge(), fighter.getWeight(), fighter.getHeight(), fighter.isKata(), fighter.isKumite(), fighter.isKobudo(), fighter.isCommunalMeal(), club.getId(), club.getClubName());

        club.addOrUpdateClubMemberToMap(newFighter);
        addOrUpdateFighterToMap(newFighter);
        return newFighter;
    }

}
