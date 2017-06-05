/*
 * This class represents a staff member of a karate club.
 */
package BE;

import BE.Users.Clubs.Abstract_ClubMember;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class StaffPerson extends Abstract_ClubMember
{
    private int id;
    private String name;
    private String username;
    private String password;
    private StaffPosition position; //The position the staff member holds at the tournament he is in.
    private boolean communalMeal;

    public StaffPerson(int id, String name, StaffPosition position, boolean communalMeal,
            int clubID, String clubName)
    {
        super(clubID, id, clubName);
        this.id = id;
        this.name = name;
        this.username = "";
        this.password = "";
        this.position = position;
        this.communalMeal = communalMeal;
    }    
    
    public int getId()
    {
        return id;
    }
    
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public boolean isCommunalMeal()
    {
        return communalMeal;
    }

    public void setCommunalMeal(boolean communalMeal)
    {
        this.communalMeal = communalMeal;
    }

    public StaffPosition getPosition()
    {
        return position;
    }

    public void setPosition(StaffPosition position)
    {
        this.position = position;
    }
    
}
