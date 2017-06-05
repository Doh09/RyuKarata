/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL.Managers.TournamentSpecific_Managers;

import BE.StaffPerson;
import BE.StaffPosition;
import java.util.ArrayList;
import java.util.List;
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
public class StaffManagerTest {

    public StaffManagerTest() {
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
     * Test of getStaffPeople method, of class StaffManager.
     */
    @Test
    public void testGetStaffPeople() {
        System.out.println("getStaffPeople");
        StaffManager instance = new StaffManager(-1);
        instance.addOrUpdatePersonToMap(new StaffPerson(0, "name", StaffPosition.Official, true, 0, "clubName"));
        boolean expResult = true;
        boolean result = instance.getStaffPeople().size() > 0;
        assertEquals(expResult, result);
    }

    /**
     * Test of setPeople method, of class StaffManager.
     */
    @Test
    public void testSetPeople_TreeMap() {
        System.out.println("setPeopleMap");
        TreeMap<Integer, StaffPerson> people = new TreeMap<>();
        StaffManager instance = new StaffManager(-1);
        people.put(0, new StaffPerson(0, "name", StaffPosition.Official, true, 0, "clubName"));
        people.put(1, new StaffPerson(1, "name", StaffPosition.Official, true, 1, "clubName"));
        instance.setPeople(people);
        assertEquals(instance.getStaffPeople().size(), 2);
    }

    /**
     * Test of setPeople method, of class StaffManager.
     */
    @Test
    public void testSetPeople_List() {
        System.out.println("setPeopleList");
        List<StaffPerson> staffPersonList = new ArrayList<>();
        StaffManager instance = new StaffManager(-1);
        staffPersonList.add(new StaffPerson(0, "name", StaffPosition.Official, true, 0, "clubName"));
        staffPersonList.add(new StaffPerson(1, "name", StaffPosition.Official, true, 1, "clubName"));
        instance.setPeople(staffPersonList);
        assertEquals(instance.getStaffPeople().size(), 2);
    }

    /**
     * Test of getStaffByIndex method, of class StaffManager.
     */
    @Test
    public void testGetStaffByIndex() {
        System.out.println("getStaffByIndex");
        int index = 1;
        StaffPerson p = new StaffPerson(1, "name", StaffPosition.Official, true, 1, "clubName");
        StaffManager instance = new StaffManager(-1);
        instance.addOrUpdatePersonToMap(p);
        StaffPerson expResult = p;
        StaffPerson result = instance.getStaffByIndex(index);
        assertEquals(expResult, result);
    }

    /**
     * Test of addOrUpdatePersonToMap method, of class StaffManager.
     */
    @Test
    public void testAddOrUpdatePersonToMap() {
        System.out.println("addOrUpdatePersonToMap");
        StaffPerson p = new StaffPerson(1, "name", StaffPosition.Official, true, 1, "clubName");
        StaffPerson p1 = new StaffPerson(2, "name", StaffPosition.Official, true, 1, "clubName");
        StaffManager instance = new StaffManager(-1);
        instance.addOrUpdatePersonToMap(p);
        instance.addOrUpdatePersonToMap(p1);
        assertEquals(instance.getStaffPeople().size(), 2);
    }

    /**
     * Test of removePersonFromMap method, of class StaffManager.
     */
    @Test
    public void testRemovePersonFromMap() {
        System.out.println("removePersonFromMap");
        StaffPerson p = new StaffPerson(1, "name", StaffPosition.Official, true, 1, "clubName");
        StaffPerson p1 = new StaffPerson(2, "name", StaffPosition.Official, true, 1, "clubName");
        StaffManager instance = new StaffManager(-1);
        instance.addOrUpdatePersonToMap(p);
        instance.addOrUpdatePersonToMap(p1);
        int index = 1;
        instance.removePersonFromMap(index);
        StaffPerson expResult = null;
        StaffPerson result = instance.getStaffByIndex(index);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllAsArrayList method, of class StaffManager.
     */
    @Test
    public void testGetAllAsArrayList() {
        System.out.println("getAllAsArrayList");
        StaffPerson p = new StaffPerson(1, "name", StaffPosition.Official, true, 1, "clubName");
        StaffPerson p1 = new StaffPerson(2, "name", StaffPosition.Official, true, 1, "clubName");
        StaffManager instance = new StaffManager(-1);
        instance.addOrUpdatePersonToMap(p);
        instance.addOrUpdatePersonToMap(p1);
        ArrayList<StaffPerson> expResult = instance.getAllAsArrayList();
        ArrayList<StaffPerson> result = instance.getAllAsArrayList();
        int expResult1 = 2;
        int result1 = instance.getAllAsArrayList().size();
        assertEquals(expResult, result);
        assertEquals(expResult1, result1);

    }

    /**
     * Test of getIndex method, of class StaffManager.
     */
    @Test
    public void testGetIndex() {
        System.out.println("getIndex");
        StaffPerson p1 = new StaffPerson(1, "name", StaffPosition.Official, true, 1, "clubName");
        StaffPerson p2 = new StaffPerson(2, "name", StaffPosition.Official, true, 2, "clubName");
        StaffManager instance = new StaffManager(-1);
        instance.addOrUpdatePersonToMap(p2);
        instance.addOrUpdatePersonToMap(p1);
        int expResult = 1;
        int result = instance.getIndex(p2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTournamentId method, of class StaffManager.
     */
    @Test
    public void testGetTournamentId() {
        System.out.println("getTournamentId");
        StaffManager instance = new StaffManager(-1);
        int expResult = -1;
        int result = instance.getTournamentId();
        assertEquals(expResult, result);
    }

    /**
     * Test of createPerson method, of class StaffManager.
     */
    @Test
    public void testCreatePerson_4args() {
        System.out.println("createPerson");
        String name = "name1";
        StaffPosition position = StaffPosition.Coach;
        boolean communalMeal = false;
        String clubName = "club1";
        StaffManager instance = new StaffManager(-1);
        StaffPosition expResult = StaffPosition.Coach; //test if returned
        StaffPerson p1 = instance.createPerson(name, position, communalMeal, clubName);
        StaffPosition result = p1.getPosition();
        assertEquals(expResult, result);
        int expResult2 = 1; //test if added
        int result2 = instance.getStaffPeople().size();
        assertEquals(expResult2, result2);
    }

    /**
     * Test of createPerson method, of class StaffManager.
     */
    @Test
    public void testCreatePerson_StaffPerson_String() {
        System.out.println("createPerson");
        String name = "name1";
        StaffPosition position = StaffPosition.Coach;
        boolean communalMeal = false;
        String clubName = "newClubName";
        StaffManager instance = new StaffManager(-1);
        String expResult = "newClubName"; //test if returned
        StaffPerson p1 = instance.createPerson(new StaffPerson(0, "name", position, communalMeal, 0, "clubName"), clubName);
        String result = p1.getClubName();
        assertEquals(expResult, result);
        int expResult2 = 1; //test if added
        int result2 = instance.getStaffPeople().size();
        assertEquals(expResult2, result2);

    }

    /**
     * Test of mergeStaffCollections method, of class StaffManager.
     */
    @Test
    public void testMergeStaffCollections() {
        System.out.println("mergeStaffCollections");
        StaffPerson p1 = new StaffPerson(1, "name", StaffPosition.Official, true, 1, "clubName");
        StaffPerson p2 = new StaffPerson(2, "name", StaffPosition.Official, true, 2, "clubName");
        StaffPerson p3 = new StaffPerson(3, "name", StaffPosition.Official, true, 3, "clubName");
        StaffPerson p4 = new StaffPerson(4, "name", StaffPosition.Official, true, 4, "clubName");
        StaffManager instance = new StaffManager(-1);
        List<StaffPerson> staffList = new ArrayList<>();
        staffList.add(p2);
        staffList.add(p1);
        staffList.add(p2);
        staffList.add(p1);
        staffList.add(p2);
        staffList.add(p1);
        staffList.add(p3);
        staffList.add(p4);

        List<StaffPerson> returnList = instance.mergeStaffCollections(staffList);
        int expResult = 4;
        int result = instance.getAllAsArrayList().size();
        assertEquals(expResult, result);
        int expResult2 = 4;
        int result2 = returnList.size();
        assertEquals(expResult2, result2);

    }

}
