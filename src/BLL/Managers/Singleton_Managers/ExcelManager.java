/*
 * This manager class is used to import/export from/to Excel.
 */
package BLL.Managers.Singleton_Managers;

import BE.ColLooker;
import BE.Fighter;
import BE.StaffPerson;
import BE.Tournaments.Abstract_Tournament;
import BE.Tournaments.Battles.Battle;
import DAL.Excel.CreateExcelTemplate;
import DAL.Excel.FighterExcel;
import DAL.Excel.StaffExcel;
import BLL.TournamentTypes.SuperTournament;
import java.io.IOException;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class ExcelManager
{

    private static ExcelManager instance;
    private final CreateExcelTemplate createTemplate;
    private final FighterExcel fighterExcel;
    private final StaffExcel staffExcel;

    private int fighterRead;
    private int staffRead;

    private ExcelManager()
    {
        createTemplate = new CreateExcelTemplate();
        fighterExcel = new FighterExcel();
        staffExcel = new StaffExcel();
        staffRead = 0;
        fighterRead = 0;
    }

    public static ExcelManager instance()
    {
        if (instance == null)
        {
            instance = new ExcelManager();
        }
        return instance;
    }

    public void createTemplate(String name) throws IOException
    {

        createTemplate.create(name);
    }

    public void readFighter(String fileName, int lineStart, ColLooker colLooker, String clubName, SuperTournament st, Abstract_Tournament at) throws IOException
    {
        for (int i = lineStart; i < Integer.MAX_VALUE; i++)
        {
            Fighter fighter = fighterExcel.readFighter(fileName, i, colLooker);
            if (fighter != null)
            {
                fighterRead++;
                if (st != null)
                {
                    st.getFighterManager().createFighter(fighter, clubName);
                }
                else
                {
                    at.getFighterManager().createFighter(fighter, clubName);
                }
            }
            else
            {
                break;
            }
        }
    }

    public void readStaff(String fileName, int lineStart, ColLooker colLooker, String clubName, SuperTournament st, Abstract_Tournament at) throws IOException
    {
        for (int i = lineStart; i < Integer.MAX_VALUE; i++)
        {
            StaffPerson staff = staffExcel.readStaff(fileName, i, colLooker);
            if (staff != null)
            {
                staffRead++;
                if (st != null)
                {
                    st.getStaffManager().createPerson(staff, clubName);
                }
                else
                {
                    at.getStaffManager().createPerson(staff, clubName);
                }
            }
            else
            {
                break;
            }
        }
    }

    public void writeFighter(String fileName, Fighter fighter, Abstract_Tournament at) throws IOException
    {
        fighterExcel.writeFighter(fileName, fighter, at);
    }

    public void writeBatte(String fileName, Battle battle, boolean xls)
    {

    }

    public int getFighterTotal(int lineStart, String fileName, ColLooker colLooker) throws IOException
    {
        int num = 0;
        for (int i = lineStart; i < Integer.MAX_VALUE; i++)
        {
            Fighter fighter = fighterExcel.readFighter(fileName, i, colLooker);
            if (fighter != null)
            {
                num++;
            }
            else
            {
                return num;
            }
        }
        return num;
    }

    public int getFightersRead()
    {
        return fighterRead;
    }

    public int getStaffsRead()
    {
        return staffRead;
    }

    public int getStaffNum(int lineStart, String fileName, ColLooker colLooker) throws IOException
    {
        int num = 0;
        for (int i = lineStart; i < Integer.MAX_VALUE; i++)
        {
            StaffPerson staffPerson = staffExcel.readStaff(fileName, i, colLooker);
            if (staffPerson != null)
            {
                num++;
            }
            else
            {
                return num;
            }
        }
        return num;
    }

}
