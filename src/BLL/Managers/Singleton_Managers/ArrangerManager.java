/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL.Managers.Singleton_Managers;

import BE.Arranger;
import java.util.ArrayList;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class ArrangerManager
{

    private static ArrangerManager instance;

    private ArrayList<Arranger> arrangerList;

    private ArrangerManager()
    {
        arrangerList = new ArrayList<>();
    }

    public static ArrangerManager getInstance()
    {
        if (instance == null)
        {
            instance = new ArrangerManager();
        }
        return instance;
    }

    public Arranger createArranger(Arranger arranger)
    {
        if (arranger == null)
        {
            throw new IllegalArgumentException("! Arranger can not be null!");
        }

        int id = 0;

        if (arrangerList.size() == 0)
        {
            id = arrangerList.size();
        }
        else
        {
            id = arrangerList.get(arrangerList.size() - 1).getId() + 1;
        }

        Arranger newArranger = new Arranger(arranger.getId(), arranger.getName(), arranger.getUserName(), arranger.getPassword());
        arrangerList.add(newArranger);
        return newArranger;
    }

    public Arranger createArranger(String name, String userName, String password)
    {
        if (name.isEmpty() || name == null)
        {
            throw new IllegalArgumentException("!Name can not be null or empty!");
        }
        if (userName.isEmpty() || userName == null)
        {
            throw new IllegalArgumentException("!eMail can not be null or empty!");
        }
        if (password.isEmpty() || password == null)
        {
            throw new IllegalArgumentException("!Password can not be empty or null!");
        }
        
        //Checks if username is in use
        for (Arranger u : arrangerList)
        {
            if (u.getUserName().equals(userName))
            {
                throw new IllegalArgumentException("The userName is already in use");
            }
        }
        
        int id;

        if (arrangerList.size() == 0)
        {
            id = arrangerList.size();
        }
        else
        {
            id = arrangerList.get(arrangerList.size() - 1).getId() + 1;
        }

        Arranger arranger = new Arranger(id, name, userName, password);
        arrangerList.add(arranger);
        return arranger;
    }

    Arranger getEmployeeFromUsername(String userName)
    {
        //Check if username not is null.
        if (userName == null || userName.isEmpty())
        {
            throw new IllegalArgumentException("Email can not be null.");
        }

        //Loop through list.
        for (Arranger user : arrangerList)
        {
            if (user.getUserName().equals(userName))
            {
                return user;
            }
        }

        //Return null, if user do not exist with the given username.
        return null;
    }

    public ArrayList<Arranger> getAll()
    {
        return arrangerList;
    }
    
    public Arranger getArranger(int index)
    {
       return  arrangerList.get(index);
    }
    
    
    public void remove(Arranger arranger)
    {
        if (!arrangerList.contains(arranger))
        {
            throw new IllegalArgumentException("Does not exist in the system");
        }
        arrangerList.remove(arranger);
    }
}
