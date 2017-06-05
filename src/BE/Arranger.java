/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BE;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class Arranger
{

    private final int id;
    private String name;
    private String userName;
    private String password;

    public Arranger(int id, String name, String userName, String password)
    {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.password = password;
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

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

}
