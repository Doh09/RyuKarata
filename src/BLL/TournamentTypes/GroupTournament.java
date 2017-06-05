/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL.TournamentTypes;

import BE.Fighter;
import BE.Tournaments.Abstract_Tournament;
import BE.Tournaments.Battles.Battle;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class GroupTournament extends Abstract_Tournament {

    public GroupTournament(int iD) {
        super(iD);
    }

    public void setUpTournament() {
        if (!tournamentHasInitialized) {
            createBattleLayers();
            setUpBattles();
            tournamentHasInitialized = true;
        }
    }

    private void createBattleLayers() {
        for (Fighter f : fighterManager.getAllAsArrayList()) { //add 1 battle layer for each person.  Each person = one battle layer.
            battleLayers.add(new ArrayList<Battle>());
        }

    }

    private void setUpBattles() {
        for (int i = 0; i < fighterManager.getAllAsArrayList().size(); i++) { //add a fight against all other persons.
            //X-coordinate
            Fighter primaryFighter = fighterManager.getAllAsArrayList().get(i);
            ArrayList<Battle> battleLayer = battleLayers.get(i);
            for (int j = 0; j < fighterManager.getAllAsArrayList().size(); j++) {
                //Y-coordinate
                Fighter secondaryFighter = fighterManager.getAllAsArrayList().get(j);
                if (primaryFighter.getFighterId() != secondaryFighter.getFighterId()) {
                    {
                        Battle b = battleManager.createBattle(primaryFighter, secondaryFighter);
                        battleLayer.add(b);
                    }
                }
            }
        }
    }

    public void afterInitializationUpdate(List<Fighter> fighterList) {
        addNewFightersToOldLayers(fighterList);
        addNewLayers(fighterList);
    }

    private void addNewLayers(List<Fighter> fighterList) {
        List<Fighter> tempList = fighterManager.mergeFighterCollections(fighterList);
        for (Fighter f : tempList) {
            //X-coordinate
            ArrayList<Battle> tempBattleLayer = new ArrayList<>();
            battleLayers.add(tempBattleLayer);
            for (int j = 0; j < fighterManager.getAllAsArrayList().size(); j++) {
                //Y-coordinate
                Fighter secondaryFighter = fighterManager.getAllAsArrayList().get(j);
                if (f.getFighterId() != secondaryFighter.getFighterId()) {
                    {
                        Battle b = battleManager.createBattle(f, secondaryFighter);
                        tempBattleLayer.add(b);
                    }
                }
            }
        }
    }

    private void addNewFightersToOldLayers(List<Fighter> fighterList) {
        for (int i = 0; i < fighterManager.getAllAsArrayList().size(); i++) { //add a fight against all other persons.
            //X-coordinate
            Fighter primaryFighter = fighterManager.getAllAsArrayList().get(i);
            ArrayList<Battle> battleLayer = battleLayers.get(i);
            for (Fighter secondaryFighter : fighterList) {
                //Y-coordinate
                Battle b = battleManager.createBattle(primaryFighter, secondaryFighter);
                battleLayer.add(b);
            }
        }
    }

    public void setAllBattlesWithGivenFightersWon(Battle battleToCompareTo) {
        if (battleToCompareTo != null) {
            if (battleToCompareTo.getWinner() != null) {
                for (Battle b : battleManager.getAllAsArrayList()) {
                    if (b.battleContainsFighter(battleToCompareTo.getFighter1Red()) && b.battleContainsFighter(battleToCompareTo.getFighter2Blue())) {
                        b.setWinner(battleToCompareTo.getWinner());
                    }
                }
            }
        }
    }

}
