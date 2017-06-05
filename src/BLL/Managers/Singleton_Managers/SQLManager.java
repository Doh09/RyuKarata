/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL.Managers.Singleton_Managers;

import BE.Arranger;
import DAL.SQL.Connection.DatabaseConnector;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Martin
 */
public class SQLManager
{
    
    private DatabaseConnector conection;
    private static SQLManager instance;
    
    private SQLManager()
    {
        conection = DatabaseConnector.getInstance();
    }
    
    public static SQLManager getInstance()
    {
        if (instance == null)
        {
            instance = new SQLManager();
        }
        return instance;
    }

    /**
     * Sends information to a Database
     * @throws java.sql.SQLException
     */
    public void exportData(Arranger arranger) throws SQLException
    {

            conection.exportArranger(arranger);
        
    }

    /**
     * Recieves information to a Database
     * @throws java.sql.SQLException
     */
    public void importData() throws SQLException
    {
        for(Arranger arranger : conection.importArranger())
        {
            ArrangerManager.getInstance().createArranger(arranger);
        }
    }
}
