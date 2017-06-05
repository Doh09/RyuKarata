/*
 * This class represents a karate club.
 */
package BE.Users.Clubs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class Club {

    /**
     * Variables
     */
    int iD;
    String clubName;
    private Map<Integer, Abstract_ClubMember> clubMembers;

    public Club(int iD, String clubName) {
        this.iD = iD;
        this.clubName = clubName;
        clubMembers =  Collections.synchronizedMap(new TreeMap<Integer, Abstract_ClubMember>());
    }

    //Getters & Setters
    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public int getId() {
        return iD;
    }

    /**
     * Returns a map with the members of the club.
     *
     * @return
     */
    public Map<Integer, Abstract_ClubMember> getClubMembers() {
        return clubMembers;
    }

    /**
     * Replaces the entire club member list with the given list.
     *
     * @param clubMembers
     */
    public void setClubMembers(HashMap<Integer, Abstract_ClubMember> clubMembers) {
        this.clubMembers = Collections.synchronizedMap(clubMembers);
    }

    public Abstract_ClubMember getClubMemberByIndex(int i) {
        return clubMembers.get(i);
    }

    public void addOrUpdateClubMemberToMap(Abstract_ClubMember c) {
        clubMembers.put(c.getClubMemberId(), c);
    }

    public void removeClubMemberFromMap(int i) {
        clubMembers.remove(i);
    }

    /**
     * Converts the synchronized map to a list and returns it. The list returned
     * may not be synchronized, unless you make it so.
     *
     * @return
     */
    public List<Abstract_ClubMember> getAllAsArrayList() {
        return Collections.synchronizedList((ArrayList<Abstract_ClubMember>) Arrays.asList((Abstract_ClubMember[]) clubMembers.values().toArray()));
    }

}
