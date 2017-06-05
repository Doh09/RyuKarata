/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL.Managers.TournamentSpecific_Managers;

import BE.Fighter;
import BE.Tournaments.Battles.Battle;
import java.util.TreeMap;
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
public class BattleManagerTest {

    public BattleManagerTest() {
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
     * Test of getMapOfBattles method, of class BattleManager.
     */
    @Test
    public void testGetMapOfBattles() {
        System.out.println("getMapOfBattles");
        BattleManager instance = new BattleManager(-1);
        assertTrue(instance.getMapOfBattles() instanceof TreeMap);
    }

    /**
     * Test of setBattles method, of class BattleManager.
     */
    @Test
    public void testSetBattles() {
        System.out.println("setBattles");
        TreeMap<Integer, Battle> battles = new TreeMap<>();
        battles.put(0, new Battle(0));
        battles.put(1, new Battle(1));
        battles.put(0, new Battle(0));
        BattleManager instance = new BattleManager(-1);
        instance.setBattles(battles);
        int expResult = 2;
        int result = instance.getMapOfBattles().size();
        assertEquals(expResult, result);

    }

    /**
     * Test of getBattleByIndex method, of class BattleManager.
     */
    @Test
    public void testGetBattleByIndex() {
        System.out.println("getBattleByIndex");
        int index = 1;
        BattleManager instance = new BattleManager(-1);
        Battle b = instance.createBattle();
        Battle b1 = instance.createBattle();

        Battle expResult = b1;
        Battle result = instance.getBattleByIndex(index);
        assertEquals(expResult, result);

    }

    /**
     * Test of addOrUpdateBattleToMap method, of class BattleManager.
     */
    @Test
    public void testAddOrUpdateBattleToMap() {
        System.out.println("addOrUpdateBattleToMap");
        Battle b = new Battle(0);
        Battle b1 = new Battle(0);
        Battle b2 = new Battle(1);
        BattleManager instance = new BattleManager(-1);
        instance.addOrUpdateBattleToMap(b);
        instance.addOrUpdateBattleToMap(b1);
        instance.addOrUpdateBattleToMap(b2);
        int expResult = 2;
        int result = instance.getMapOfBattles().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of removeBattleFromMap method, of class BattleManager.
     */
    @Test
    public void testRemoveBattleFromMap() {
        System.out.println("removeBattleFromMap");
        int index = 1;
        BattleManager instance = new BattleManager(-1);
        instance.createBattle();
        instance.createBattle();
        instance.createBattle();
        instance.removeBattleFromMap(index);
        int expResult = 2;
        int result = instance.getMapOfBattles().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllAsArrayList method, of class BattleManager.
     */
    @Test
    public void testGetAllAsArrayList() {
        System.out.println("getAllAsArrayList");
        BattleManager instance = new BattleManager(-1);
        instance.createBattle();
        instance.createBattle();
        instance.createBattle();

        assertEquals(instance.getAllAsArrayList().size(), 3);
    }

    /**
     * Test of getTournamentId method, of class BattleManager.
     */
    @Test
    public void testGetTournamentId() {
        System.out.println("getTournamentId");
        BattleManager instance = new BattleManager(-1);
        int expResult = -1;
        int result = instance.getTournamentId();
        assertEquals(expResult, result);
    }

    /**
     * Test of createBattle method, of class BattleManager.
     */
    @Test
    public void testCreateBattle_3args() {
        System.out.println("createBattle");
        Fighter fighter1 = new Fighter(0, "name", Fighter.Gender.Male, 0, 0, 0, 0, true, true, true, true, 0, "club");
        Fighter fighter2 = new Fighter(1, "name", Fighter.Gender.Male, 1, 1, 1, 1, true, true, true, true, 1, "club");
        BattleManager instance = new BattleManager(-1);
        Battle b = instance.createBattle(fighter1, fighter2);
        assertEquals(fighter1, b.getFighter1Red());
        assertEquals(fighter2, b.getFighter2Blue());
        assertEquals(0, b.getId());
        assertEquals(instance.getAllAsArrayList().size(), 1);
    }

    /**
     * Test of createBattle method, of class BattleManager.
     */
    @Test
    public void testCreateBattle_0args() {
        System.out.println("createBattle");
        BattleManager instance = new BattleManager(-1);
        Battle b = instance.createBattle();
        assertEquals(0, b.getId());
        assertEquals(instance.getAllAsArrayList().size(), 1);
    }

}
