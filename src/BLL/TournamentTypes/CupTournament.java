/*
 * This class represents a cup tournament.
 * The class extends Abstract_Tournament to get its core functionality.
 * The part unique for this class, is that it sets up the battle layers and 
 * produces the battles based on the amount of fighters.
 * The cup tournament tree will always be binary.
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
public class CupTournament extends Abstract_Tournament {

    private double tournamentTreeHeight = 0;

    public CupTournament(int iD) {
        super(iD);
    }

    /**
     * Set up the tournament based on a list of fighters.
     *
     * @param fighterList
     */
    public synchronized void setUpTournament(List<Fighter> fighterList) {

        List<Fighter> tempList = fighterManager.mergeFighterCollections(fighterList);
//        merge fighters not already in the manager, into it and return a list of the newly added.
        if (!tournamentHasInitialized) {
            createTournamentLayers();
            if (tournamentTreeHeight > 0) {
                createInitialBattles();
                createBattlesInHigherLayers();
                linkBattlesWithOneAnother();
                tournamentHasInitialized = true;

            }
        } else {
            //If cup already has been initialized, fill in new battle/players as required.
            fillInNewPlayers(tempList);
        }
    }

    /**
     * If the tournament already was initialized, use this method to fill in new
     * players. The new players will be added to the cup tournament.
     *
     * @param tempList
     */
    private void fillInNewPlayers(List<Fighter> tempList) {
        //If cup already has been initialized, fill in new battle/players as required.
        for (Fighter f : tempList) {
            if (fighterManager.getAllAsArrayList().size() / 2 >= battleLayers.get(0).size()) {
                battleLayers.get(0).add(battleManager.createBattle(null, null)); //add a battle if no availlable battle.
                makeTreeBinary();
            }
            calculateHeightOfTree();
            while (!(battleLayers.size() >= tournamentTreeHeight)) {
                battleLayers.add(new ArrayList<>());
            }
            for (int i = 1; i < battleLayers.size(); i++) {
                while (battleLayers.get(i).size() < battleLayers.get(i - 1).size() / 2) { //måske <=
                    Battle b = battleManager.createBattle(null, null);
                    battleLayers.get(i).add(b);
                    linkBattlesWithOneAnother();
                }
            }
            for (Battle b : battleLayers.get(0)) {
                if (b.getFighter2Blue() == null) {
                    b.setFighter2Blue(f);
                    break;
                } else if (b.getFighter1Red() == null) {
                    b.setFighter1Red(f);
                    break;
                }
            }
        }
    }

    /**
     * This method creates the amount of tournament layers based on the height
     * calculated.
     */
    private void createTournamentLayers() {
        calculateHeightOfTree();
        battleLayers = new ArrayList<>();
        for (int i = 0; i < tournamentTreeHeight; i++) //add an ArrayList for each layer/height in the tree.
        {
            battleLayers.add(new ArrayList<>());
        }
    }

    /**
     * Calculates and sets the tournament tree height, based on the amount of
     * fighters.
     */
    private void calculateHeightOfTree() {
        //Math notes:
        //h = height.
        //total amount of battles = 2^h.
        //h = (Math.log(amount of battles)/Math.log(2))
        tournamentTreeHeight = Math.log(fighterManager.getAllAsArrayList().size()) / Math.log(2.0); //calculate height of tree
    }

    /**
     * Creates the initial layer of battles. This layer is the foundation on
     * which all the following layers are created.
     */
    private void createInitialBattles() {
        List<Fighter> tempList = fighterManager.getAllAsArrayList();
        Fighter tempFighter = null; //Variable used to hold 1 of 2 fighters in a battle.
        for (int i = 0; i < tempList.size(); i++) {
            if (tempFighter != null) {
                Battle b = battleManager.createBattle(tempFighter, tempList.get(i));
                battleLayers.get(0).add(b);//get initial layer and add 'b' to it.
                tempFighter = null;

            } else if (tempFighter == null && i == tempList.size() - 1) { 
                //If only 1 player is left, put him in a battle alone.
                Battle b = battleManager.createBattle(tempList.get(i), null);
                battleLayers.get(0).add(b);//get initial layer and add 'b' to it.
            } else if (tempFighter == null) {
                tempFighter = tempList.get(i);
            }
        }

        makeTreeBinary();
    }

    /**
     * This method fills in empty battles in the initial layer, until the layer
     * has a size, that will ensure the tree is binary.
     */
    private void makeTreeBinary() {
        //Added extra empty battles to ensure tree is binary.
        //(n & (n - 1)) == 0
        while (!((battleLayers.get(0).size() & (battleLayers.get(0).size() - 1)) == 0))
        //While size is not a power of 2, I.e. binary.
        {
            Battle b = battleManager.createBattle(null, null);
            battleLayers.get(0).add(b);
        }
    }

    /**
     * This method is used after the initial layer has been made, to create the
     * higher layers. Each layer is half the size of the previous one, until the
     * final battle is reached. All battles in higher layers are empty battles,
     * the players will be assigned to them when a winner is selected through
     * the GUI.
     */
    private void createBattlesInHigherLayers() {
        for (int i = 0; i < tournamentTreeHeight; i++) { 
            //For the battle layers above the initial.
            if (i != 0) { //Only do the following, if not the initial layer.
                for (int j = 0; j < (battleLayers.get(i - 1).size() / 2); j++) { 
                    //For half as much as the previous layer.
                    Battle b = battleManager.createBattle(null, null);
                    battleLayers.get(i).add(b);
                }
            }
        }
    }

    /**
     * This method makes sure that the following happens: - All battles in the
     * initial layer have a next battle. - The battle in the final layer has 2
     * previous battles. - All battles in between the initial and the final
     * layer, have 2 previous battles and 1 next battle. This ensures that all
     * battle paths eventually will lead to the final battle.
     */
    private void linkBattlesWithOneAnother() {
        for (int i = 0; i < battleLayers.size() - 1; i++) {
            if (i == 0) {//If initial layer
                ArrayList<Battle> higherLayer = battleLayers.get(1);
                ArrayList<Battle> currentLayer = battleLayers.get(0);
                for (int j = 0; j < higherLayer.size(); j++) 
                //run through the higher layer.
                {
                    higherLayer.get(j).setPreviousBattle1(currentLayer.get(j * 2)); 
                    //set the previousbattle1 in higher.
                    higherLayer.get(j).setPreviousBattle2(currentLayer.get(j * 2 + 1)); 
                    //set the previousbattle2 in higher.
                    currentLayer.get(j * 2).setNextBattle(higherLayer.get(j));
                    currentLayer.get(j * 2 + 1).setNextBattle(higherLayer.get(j));
                }

            } else if (i == battleLayers.size() - 1) { //If top layer
                ArrayList<Battle> topLayer = battleLayers.get(battleLayers.size() - 1);
                ArrayList<Battle> secondTopLayer = battleLayers.get(battleLayers.size() - 2);
                for (int j = 0; j < topLayer.size(); j++) //run through the higher layer.
                {
                    topLayer.get(j).setPreviousBattle1(secondTopLayer.get(j * 2)); 
                    //set the previousbattle1 in higher.
                    topLayer.get(j).setPreviousBattle2(secondTopLayer.get(j * 2 + 1)); 
                    //set the previousbattle2 in higher.
                    secondTopLayer.get(j * 2).setNextBattle(topLayer.get(j));
                    secondTopLayer.get(j * 2 + 1).setNextBattle(topLayer.get(j));
                }

            } else { //if not initial && not top layer.
                ArrayList<Battle> higherLayer = battleLayers.get(i + 1);
                ArrayList<Battle> lowerLayer = battleLayers.get(i);
                for (int j = 0; j < higherLayer.size(); j++) 
                //run through the higher layer.
                {
                    higherLayer.get(j).setPreviousBattle1(lowerLayer.get(j * 2)); 
                    //set the previousbattle1 in higher.
                    higherLayer.get(j).setPreviousBattle2(lowerLayer.get(j * 2 + 1)); 
                    //set the previousbattle2 in higher.
                    lowerLayer.get(j * 2).setNextBattle(higherLayer.get(j));
                    lowerLayer.get(j * 2 + 1).setNextBattle(higherLayer.get(j));
                }
            }
        }
    }

    /**
     * @return the height of the tournament tree. Will be 0 if the tournament
     * hasn't been set up.
     */
    public double getTournamentTreeHeight() {
        return tournamentTreeHeight;
    }

}
