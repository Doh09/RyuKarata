/*
 * This manager class holds all clubs in the system.
 */
package BLL.Managers.Singleton_Managers;

import BE.Users.Clubs.Club;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.    util.Map;
import java.util.TreeMap;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class ClubManager
{

    private static ClubManager instance;

    /**
     * Variabler
     */
    private Map<Integer, Club> clubs; //The Integer is the clubs Id.

    /**
     * Constructor, uses Singleton.
     */
    private ClubManager()
    {
        clubs =  Collections.synchronizedMap(new TreeMap<Integer, Club>());
    }

    /**
     * Singleton pattern
     *
     * @return
     */
    public static ClubManager getInstance()
    {
        if (instance == null)
        {
            instance = new ClubManager();
        }
        return instance;
    }

    /**
     * Returns a map with all clubs.
     *
     * @return
     */
    public Map<Integer, Club> getClubs()
    {
        return clubs;
    }

    /**
     * Replace the clubs map with the given map.
     *
     * @param clubs
     */
    public void setClub(HashMap<Integer, Club> clubs)
    {
        this.clubs = Collections.synchronizedMap(clubs);
    }

    public Club getClubByIndex(int i)
    {
        return clubs.get(i);
    }

    public void addOrUpdateClubToMap(Club c)
    {
        clubs.put(c.getId(), c);
    }

    public void removeClubFromMap(int i)
    {
        clubs.remove(i);
    }

    /**
     * Converts the synchronized map to a list and returns it. The list returned
     * may not be synchronized, unless you make it so.
     *
     * @return
     */
    public List<Club> getAllAsArrayList()
    {
        return Collections.synchronizedList(
                (ArrayList<Club>) Arrays.asList((Club[]) clubs.values().toArray()));
    }

    public Club createClub(String clubName)
    {

        //Make sure two clubs with the same name doesn't occor
        
        for(Club club: clubs.values())
        {
            if(club.getClubName().trim().toLowerCase().equals(clubName.trim().toLowerCase()))
            {
                return club;
            }
        }
                
        
        int id = 0;

        for (Club club : clubs.values())
        {
            if (club.getId() >= id)
            {
                id = club.getId() + 1;
            }
        }

        Club c = new Club(id, clubName);
        addOrUpdateClubToMap(c);
        return c;
    }

}
