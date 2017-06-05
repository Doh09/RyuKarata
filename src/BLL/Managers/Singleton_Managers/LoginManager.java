/*
 * This manager class holds all user logins in the system.
 */
package BLL.Managers.Singleton_Managers;

import BE.Arranger;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class LoginManager
{

    private static LoginManager instance;
    private ArrangerManager arrangerManager;
    private Arranger currentUser;

    private LoginManager()
    {
        arrangerManager = ArrangerManager.getInstance();
    }
    
    public static LoginManager instance()
    {
        if (instance == null)
        {
            instance = new LoginManager();
        }
        return instance;
    }

    public boolean login(String userName, String password)
    {
        Arranger temp = arrangerManager.getEmployeeFromUsername(userName);
        if (temp == null)
        {
            return false;
        }
        if (!temp.getPassword().equals(password))
        {
            return false;
        }
        currentUser=temp;
        return true;
    }

  public Arranger getUser()
  {
      return currentUser;
  }
}
