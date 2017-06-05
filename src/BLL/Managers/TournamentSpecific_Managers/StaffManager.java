/*
 * This class handles the staffmembers of a single tournament.
 * The class has a TreeMap with the staffmembers.
 * CRUD Functionality is implemented.
 * The TreeMap with all the staffmembers can be retrieved as an ArrayList
 * for iteration purposes.
 */
package BLL.Managers.TournamentSpecific_Managers;

import BE.StaffPerson;
import BE.StaffPosition;
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
public class StaffManager {

    /**
     * Variables
     */
    private Map<Integer, StaffPerson> staffPeople; //A map of all staff people held by this manager.
    private final int tournamentId; //The id of the tournament this manager belongs to.

    /**
     * Constructor, is purposely not made Singleton, as the program potentially
     * runs more than one tournament at a time.
     *
     * @param tournamentId
     */
    public StaffManager(int tournamentId) {
        this.tournamentId = tournamentId;
        staffPeople = Collections.synchronizedMap(new TreeMap<Integer, StaffPerson>());

    }

    /**
     * Returns a map with all staffmembers in the tournament.
     *
     * @return
     */
    public Map<Integer, StaffPerson> getStaffPeople() {
        return staffPeople;
    }

    /**
     * Replace all staffmembers in this staffmanagers Map, with the given Map.
     *
     * @param people
     */
    public void setPeople(TreeMap<Integer, StaffPerson> people) {
        this.staffPeople = Collections.synchronizedMap(people);
    }

    /**
     * Replace all staffmembers in this staffmanagers Map, with the given List.
     *
     * @param staffPersonList
     */
    public void setPeople(List<StaffPerson> staffPersonList) {
        TreeMap<Integer, StaffPerson> peopleMap = new TreeMap();
        for (StaffPerson sp : staffPersonList) {
            peopleMap.put(sp.getId(), sp);
        }
        this.staffPeople = Collections.synchronizedMap(peopleMap);
    }

    /**
     * @param i, the index/key of the staff to return, usually their id.
     * @return a StaffPerson based on his index/key.
     */
    public StaffPerson getStaffByIndex(int i) {
        return staffPeople.get(i);
    }

    /**
     * Add a StaffPerson to the managers map. If the ID of the StaffPerson is
     * already in the map, it is overwritten.
     *
     * @param p
     */
    public void addOrUpdatePersonToMap(StaffPerson p) {
        staffPeople.put(p.getId(), p);
    }

    /**
     * Method that removes a staff member based on his/her index/key.
     *
     * @param i, the index/key of the staff member to remove, usually their id.
     */
    public void removePersonFromMap(int i) {
        staffPeople.remove(i);
    }

    /**
     * Converts the synchronized map to a list and returns it. The list returned
     * may not be synchronized, unless you make it so.
     *
     * @return a list of all staffmembers in this manager.
     */
    public ArrayList<StaffPerson> getAllAsArrayList() {
        ArrayList<StaffPerson> list = new ArrayList<>(staffPeople.values());
        return list;

    }

    /**
     * @param person, the staff member to find the key/index of.
     * @return the key/index of the staff member given.
     */
    public int getIndex(StaffPerson person) {
        return getAllAsArrayList().indexOf(person);
    }

    /**
     * @return the id of the tournament this manager belongs to.
     */
    public int getTournamentId() {
        return tournamentId;
    }

    /**
     * Create a new StaffPerson, the StaffPerson receives a unique ID as
     * key/index.
     *
     * @param name
     * @param position
     * @param communalMeal, whether or not the StaffPerson is signed up to eat
     * at the tournament.
     * @param clubName, the name of the club the StaffPerson is a member of.
     * @return the StaffPerson created.
     */
    public StaffPerson createPerson(String name, StaffPosition position, boolean communalMeal, String clubName) {
        if (name.isEmpty() || name == null) {
            throw new IllegalArgumentException("Name can not be nothing");
        }

        int id = 0;

        for (StaffPerson p : getAllAsArrayList()) {
            if (p.getId() >= id) {
                id = p.getId() + 1;
            }
        }

        Club club = ClubManager.getInstance().createClub(clubName);
        StaffPerson staffmember = new StaffPerson(id, name, position, communalMeal, club.getId(), club.getClubName());

        club.addOrUpdateClubMemberToMap(staffmember);
        addOrUpdatePersonToMap(staffmember);

        return staffmember;
    }

    /**
     * | Adds a club name to a StaffPerson, then returns him. Is used instead of
     * a setter method to set the club, to ensure a StaffPerson cannot have his
     * club changed without it being through the manager. Also used if a
     * StaffPerson needs to change his club.
     *
     * @param staffmember to assign a club to.
     * @param clubName, the name of the club the StaffPerson is a member of.
     * @return the StaffPerson created.
     */
    public StaffPerson createPerson(StaffPerson staffmember, String clubName) {
        if (staffmember == null) {
            throw new IllegalArgumentException("Name can not be nothing");
        }

        int id = 0;

        for (StaffPerson p : getAllAsArrayList()) {
            if (p.getId() >= id) {
                id = p.getId() + 1;
            }
        }

        Club club = ClubManager.getInstance().createClub(clubName);
        StaffPerson newStaffMember = new StaffPerson(id, staffmember.getName(), staffmember.getPosition(), staffmember.isCommunalMeal(), club.getId(), club.getClubName());

        club.addOrUpdateClubMemberToMap(newStaffMember);
        addOrUpdatePersonToMap(newStaffMember);

        return newStaffMember;
    }
    
   
    /**
     * Method that merges a list of StaffPersons into the Map of the manager. This
     * means that each StaffPerson from the list given is added to the map, if
     * another StaffPerson with the same ID is not already in the map. This is to
     * prevent overriding existing StaffPersons.
     *
     * @param staffList
     * @return a list of the staff people added.
     */
    public List<StaffPerson> mergeStaffCollections(List<StaffPerson> staffList) {
        List<StaffPerson> tempList = new ArrayList<>();

        for (StaffPerson p : staffList) //add new
        {
            if (!staffPeople.containsKey(p.getId())) {
                staffPeople.put(p.getId(), p);
                tempList.add(p);
            }
        }
        return tempList;
    }
}
