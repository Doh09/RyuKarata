/*
 * This class acts as the facade from GUI to BLL and further down.
 */
package BLL.Facades;

import BE.Tournaments.Abstract_Tournament;
import BLL.TournamentTypes.SuperTournament;
import DAL.Facades.FacadeToDAL;
import BLL.Managers.Singleton_Managers.ClubManager;
import BLL.Managers.Singleton_Managers.LoginManager;
import BLL.Managers.Singleton_Managers.SuperTournamentManager;
import BLL.Managers.Singleton_Managers.TournamentManager;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class FacadeToBLL {

    private static FacadeToBLL instance;
    private static FacadeToDAL facadeToDAL = FacadeToDAL.getInstance();

    private FacadeToBLL() {
    }

    public static FacadeToBLL getInstance() {
        if (instance == null) {
            instance = new FacadeToBLL();
        }
        return instance;
    }
    /**
     * Variabler
     */
    ////Referencer
    //Singletons
    private ClubManager klubManager = ClubManager.getInstance();
    private TournamentManager tournamentManager = TournamentManager.getInstance();

    public List<Abstract_Tournament> getAllTournaments() {
        return TournamentManager.getInstance().getAllAsArrayList();
    }

    public Abstract_Tournament getTournament(int i) {
        return TournamentManager.getInstance().getTournamentByIndex(i);
    }

    public void removeTournament(int i) {
        tournamentManager.removeTournamentFromMap(i);
    }

    public Abstract_Tournament createTournament(String type) {
        if (type.equalsIgnoreCase("Cup")) {
            return TournamentManager.getInstance().createCupTournament();
        } else { //else type must be group
            return TournamentManager.getInstance().createGroupTournament();
        }
    }

    public void addOrUpdateTournament(Abstract_Tournament at) {
        TournamentManager.getInstance().addOrUpdateTournamentToMap(at);
    }

    public ArrayList<SuperTournament> getAllSuperTournaments() {

        return SuperTournamentManager.getInstance().getAllAsArrayList();
    }

    public ArrayList<SuperTournament> searchForTournamentsByName(String searchString, int searchMode, boolean searchBydate, Date startRange, Date endRange) {
        return SuperTournamentManager.getInstance().searchForTournamentsByName(searchString, searchMode, searchBydate, startRange, endRange);
    }

    public ArrayList<SuperTournament> searchForTournamentsByDateOnly(Date startRange, Date endRange) {
        return SuperTournamentManager.getInstance().searchForTournamentsByDateOnly(startRange, endRange);
    }

    public SuperTournament getSuperTournament(int i) {
        return SuperTournamentManager.getInstance().getTournamentByIndex(i);
    }

    public void addOrUpdateSuperTournament(SuperTournament st) {
        SuperTournamentManager.getInstance().addOrUpdateTournamentToMap(st);
    }

    public SuperTournament createSuperTournament() {
        return SuperTournamentManager.getInstance().createSuperTournament();
    }

    public void removeSuperTournament(int i) {
        SuperTournamentManager.getInstance().removeTournamentFromMap(i);
    }

    /**
     * Attempts to save the given JComponent on the given directory path.
     *
     * @param image - The image to be saved
     * @param path - The directory path to save the file to.
     * @return If successful, return true, if failed, return false.
     */
    public boolean savePanelAsImage(BufferedImage image, String path) {
        return facadeToDAL.savePanelAsImage(image, path);
    }

    public boolean login(String username, String password) {
        return LoginManager.instance().login(username, password);
    }

}
