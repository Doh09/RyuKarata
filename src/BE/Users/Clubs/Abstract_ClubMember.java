/*
 * This abstract class holds the information of a club member.
 */
package BE.Users.Clubs;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public abstract class Abstract_ClubMember{
    /**
     * Variables
     */
    protected final int clubId;
    protected final int clubMemberId;
    protected String clubName; 

    /**
     * Constructor
     * @param clubId
     * @param iD
     * @param clubName 
     */
    public Abstract_ClubMember(int clubId, int clubMemberId, String clubName) {
        this.clubId = clubId;
        this.clubMemberId = clubMemberId;
        this.clubName = clubName;
    }

    //Getters & Setters
    public int getClubId() {
        return clubId;
    }

    public String getClubName()
    {
        return clubName;
    }

  

    public int getClubMemberId() {
        return clubMemberId;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }
    
    
    
    
    
}
