/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import BLL.Managers.Singleton_Managers.ArrangerManager;
import BLL.Managers.Singleton_Managers.SQLManager;
import GUI.LoginUI;
import java.sql.SQLException;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class RyuKarate
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException
    {
        SQLManager.getInstance().importData();

        LoginUI login = new LoginUI();
        login.setLocationRelativeTo(null);
        login.setVisible(true);

    }

}
