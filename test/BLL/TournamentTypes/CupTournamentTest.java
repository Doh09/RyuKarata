/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL.TournamentTypes;

import BE.Fighter;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class CupTournamentTest {

    public CupTournamentTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setUpTournament method, of class CupTournament.
     */
    @Test
    public void testSetUpTournament() {
        System.out.println("setUpTournament");
        List<Fighter> fighterList = new ArrayList<>();
        fighterList.add(new Fighter(0, "name", Fighter.Gender.Male, 0, 0, 0, 0, true, true, true, true, 0, "clubName"));
        fighterList.add(new Fighter(1, "name", Fighter.Gender.Male, 1, 1, 1, 1, true, true, true, true, 1, "clubName"));
        fighterList.add(new Fighter(2, "name", Fighter.Gender.Male, 2, 2, 2, 2, true, true, true, true, 2, "clubName"));
        fighterList.add(new Fighter(3, "name", Fighter.Gender.Male, 3, 3, 3, 3, true, true, true, true, 3, "clubName"));
        fighterList.add(new Fighter(4, "name", Fighter.Gender.Male, 4, 4, 4, 4, true, true, true, true, 4, "clubName"));
        fighterList.add(new Fighter(5, "name", Fighter.Gender.Male, 5, 5, 5, 5, true, true, true, true, 5, "clubName"));

        CupTournament instance = new CupTournament(-1);
        instance.setUpTournament(fighterList);
        int expResult = 3;
        int result = instance.getBattleLayers().size(); //test battle layer amount.
        assertEquals(expResult, result);
        int expResult2 = 7;
        int result2 = instance.getBattleManager().getAllAsArrayList().size(); //test that tree is binary.
        assertEquals(expResult2, result2);
    }

    /**
     * Test of getTournamentTreeHeight method, of class CupTournament.
     */
    @Test
    public void testGetTournamentTreeHeight() {
        List<Fighter> fighterList = new ArrayList<>();
        fighterList.add(new Fighter(0, "name", Fighter.Gender.Male, 0, 0, 0, 0, true, true, true, true, 0, "clubName"));
        fighterList.add(new Fighter(1, "name", Fighter.Gender.Male, 1, 1, 1, 1, true, true, true, true, 1, "clubName"));
        fighterList.add(new Fighter(2, "name", Fighter.Gender.Male, 2, 2, 2, 2, true, true, true, true, 2, "clubName"));
        fighterList.add(new Fighter(3, "name", Fighter.Gender.Male, 3, 3, 3, 3, true, true, true, true, 3, "clubName"));
        fighterList.add(new Fighter(4, "name", Fighter.Gender.Male, 4, 4, 4, 4, true, true, true, true, 4, "clubName"));
        fighterList.add(new Fighter(5, "name", Fighter.Gender.Male, 5, 5, 5, 5, true, true, true, true, 5, "clubName"));

        CupTournament instance = new CupTournament(-1);
        instance.setUpTournament(fighterList);
        double expResult = 3.0;
        double result = instance.getTournamentTreeHeight();
        assertEquals(expResult, result, 0.9);

    }

}
