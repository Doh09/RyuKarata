/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL.SQL.Connection;

import BE.Arranger;
import BE.Fighter;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class DatabaseConnector
{

    SQLServerDataSource ssds;
    private final String serverIP = "127.0.0.1";
    private final int portNumber = 1433;
    private final String databaseName = "RyuKarateSQL";
    private final String password = "RyuKarateLogin";
    private final String userName = "RyuKarateLogin";
   
    
    
    private static DatabaseConnector instance;

    public DatabaseConnector()
    {
        ssds = new SQLServerDataSource();
        ssds.setServerName(serverIP);
       // ssds.setPortNumber(portNumber);
        ssds.setDatabaseName(databaseName);
        ssds.setUser(userName);
        ssds.setPassword(password);
    }

    public static DatabaseConnector getInstance()
    {
        if (instance == null)
        {
            instance = new DatabaseConnector();
        }
        return instance;
    }

    public ArrayList<Arranger> importArranger() throws SQLException
    {
        // Plain old SELECT:
        System.out.println("Plain old SELECT:");
        try (Connection con = ssds.getConnection())
        {

            String sql = "SELECT * FROM Arranger;";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            ArrayList<Arranger> arrangerList = new ArrayList<>();

            while (rs.next())
            {   
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String arrangerUserName = rs.getString(3);
                String ArrangerPassword = rs.getString(4);
                System.out.println(id);
                System.out.println(name);
                System.out.println(arrangerUserName);
                System.out.println(ArrangerPassword);
                arrangerList.add(new Arranger(id, name, arrangerUserName, ArrangerPassword));
                
            }
            rs.close();
            st.close();
            con.close();
            return arrangerList;
        }
        
    }

    public void exportArranger(Arranger arranger) throws SQLException
    {
        System.out.println("Plain old INSERT");
        try (Connection con = ssds.getConnection())
        {
            //Vi opretter et prepared statement i stedet for et statement:
            String sql = "INSERT INTO Arranger(id, name, userName, password) "
                    + "VALUES(?,?,?,?)"; // ? markerer en variabel.
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, arranger.getId());
            ps.setString(2, arranger.getName());
            ps.setString(3, arranger.getUserName());
            ps.setString(4, arranger.getPassword());
            ps.executeUpdate();
            con.close();
        }

    }

    public Fighter importFighter() throws SQLException
    {
        // Plain old SELECT:
        System.out.println("Plain old SELECT:");
        try (Connection con = ssds.getConnection())
        {

            String sql = "SELECT * FROM Fighter;";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            ArrayList<Fighter> fighterList = new ArrayList<>();

            while (rs.next())
            {

                int id = rs.getInt(1);

                fighterList.add(new Fighter(id, sql, Fighter.Gender.Male, id, id, id, id, true, true, true, true, id, userName));
            }
            rs.close();
            st.close();
        }
        return null;
    }

    public void exportFighter(Fighter fighter) throws SQLException
    {
        System.out.println("Plain old INSERT");
        try (Connection con = ssds.getConnection())
        {
            //Vi opretter et prepared statement i stedet for et statement:
            String sql = "INSERT INTO Fighter(id, Name, Gender, Grade, Age, Point, Weight, Height,Kata,Kumite,Kobudo,Eat,In Group,Battle Ability, Rule Understanding, Speed, Statue, Toughness) "
                    + "VALUES(?,?,?,?,"
                    + "?,?,?,?,"
                    + "?,?,?,?,"
                    + "?,?,?,?,"
                    + "?,?);"; // ? markerer en variabel.
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, fighter.getFighterId());
            ps.setString(2, fighter.getName());
            ps.setString(3, fighter.getGender().toString());
            ps.setInt(4, fighter.getGrade());
            ps.setInt(5, fighter.getAge());
            ps.setInt(6, fighter.getPoint());
            ps.setDouble(7, fighter.getWeight());
            ps.setDouble(8, fighter.getHeight());
            ps.setBoolean(9, fighter.isKata());
            ps.setBoolean(10, fighter.isKumite());
            ps.setBoolean(11, fighter.isKobudo());
            ps.setBoolean(12, fighter.isCommunalMeal());
            ps.setBoolean(13, fighter.isInGroup());
            ps.setString(14, fighter.getBattleAbility().toString());
            ps.setString(15, fighter.getRuleUnderstanding().toString());
            ps.setString(16, fighter.getSpeed().toString());
            ps.setString(17, fighter.getStatue().toString());
            ps.setString(18, fighter.getToughness().toString());
            ps.executeUpdate();
        }

    }
}
