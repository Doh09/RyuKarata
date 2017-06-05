/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL.Excel;

import BE.Tournaments.Battles.Battle;
import java.io.IOException;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class BattleExcel
{
      public void writeFighter(String fileName, Battle battle, boolean xls) throws IOException
    {
        if (xls)
        {
            oldWriteExcel(fileName, battle);
        }
        else
        {
            newWriteExcel(fileName, battle);
        }
    }

    private void oldWriteExcel(String fileName, Battle battle)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void newWriteExcel(String fileName, Battle battle)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
