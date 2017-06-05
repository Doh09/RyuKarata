/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.TournamentRelated;

import BE.Fighter;
import BE.Tournaments.Battles.Battle;
import BLL.TournamentTypes.GroupTournament;
import GUI.FighterLookerGUI;
import GUI.MainGUI;
import GUI.TableModels_And_Tables.ColorTableCellRenderer;
import GUI.TableModels_And_Tables.FighterTableModel;
import GUI.TableModels_And_Tables.GroupTournamentBattlesModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class pnlPreviewGroupTournament extends javax.swing.JPanel {

    private FighterTableModel fighterModel;
    private GroupTournamentBattlesModel battlesModel;
    private GroupTournament tournament = new GroupTournament(-1);
    private MainGUI parent;
    private ColorTableCellRenderer battleColorRenderer;
    private Fighter currentFighter;
    //Hexadecimal numbers = colours.
    private String cellColourNoWinnerFound = "fillColor=#cce6ff";
    private String cellColourWinnerFound = "fillColor=#66ff33";
    //Collections
    private ArrayList<ArrayList<Battle>> battleLayers = new ArrayList<>(); //list of the heights/layers in the tournament, where 0 is the initial layer.

    /**
     * Creates new form pnlPreviewGroupTournament
     */
    public pnlPreviewGroupTournament() {
        initComponents();
        createDataToDisplay();
        initializeFrameSettings();
        createBattleLayers();
        setUpBattles();
    }

    private void createDataToDisplay() {
        tournament.getFighterManager().addOrUpdateFighterToMap(new Fighter(0, "Peter Nielsen", Fighter.Gender.Male, 2, 19, 72, 1.9, false, true, false, true, 0, "Klubnavn"));
        tournament.getFighterManager().addOrUpdateFighterToMap(new Fighter(1, "Jens Møller", Fighter.Gender.Male, 2, 19, 72, 1.9, false, true, false, true, 0, "Klubnavn"));
        tournament.getFighterManager().addOrUpdateFighterToMap(new Fighter(2, "Hanne Nielsen", Fighter.Gender.Female, 2, 19, 72, 1.9, false, true, false, true, 0, "Klubnavn"));
        tournament.getFighterManager().addOrUpdateFighterToMap(new Fighter(3, "Torben Nikolajsen", Fighter.Gender.Male, 2, 19, 72, 1.9, false, true, false, true, 0, "Klubnavn"));
        tournament.getFighterManager().addOrUpdateFighterToMap(new Fighter(4, "Kristian Thomsen", Fighter.Gender.Male, 2, 19, 72, 1.9, false, true, false, true, 0, "Klubnavn"));
        tournament.getFighterManager().addOrUpdateFighterToMap(new Fighter(5, "Gurli Jensen", Fighter.Gender.Female, 2, 19, 72, 1.9, false, true, false, true, 0, "Klubnavn"));
        tournament.setUpTournament();
    }

    private void initializeFrameSettings() {
        fighterModel = new FighterTableModel(tournament.getFighterManager().getAllAsArrayList());
        tblFighters.setModel(fighterModel);
        battlesModel = new GroupTournamentBattlesModel(new ArrayList<>());
        tblBattles.setModel(battlesModel);
        battleColorRenderer = new ColorTableCellRenderer();
        battleColorRenderer.setWinnerFoundColor(cellColourWinnerFound);
        battleColorRenderer.setNoWinnerFoundColor(cellColourNoWinnerFound);
        tblBattles.setDefaultRenderer(String.class, battleColorRenderer);
        addFighterTableMouseListener();
        addBattlesTableMouseListener();
    }

    private void createBattleLayers() {
        for (Fighter f : tournament.getFighterManager().getAllAsArrayList()) { //add 1 battle layer for each person.  Each person = one battle layer.
            battleLayers.add(new ArrayList<Battle>());
        }

    }

    private void setUpBattles() {
        for (int i = 0; i < tournament.getFighterManager().getAllAsArrayList().size(); i++) { //add a fight against all other persons.
            //X-coordinate
            Fighter primaryFighter = tournament.getFighterManager().getAllAsArrayList().get(i);
            ArrayList<Battle> battleLayer = battleLayers.get(i);
            for (int j = 0; j < tournament.getFighterManager().getAllAsArrayList().size(); j++) {
                //Y-coordinate
                Fighter secondaryFighter = tournament.getFighterManager().getAllAsArrayList().get(j);
                if (primaryFighter.getFighterId() != secondaryFighter.getFighterId()) {
                    {
                        Battle b = tournament.getBattleManager().createBattle(primaryFighter, secondaryFighter);
                        battleLayer.add(b);
                    }
                }
            }
        }
    }

    private void addFighterTableMouseListener() {
        tblFighters.addMouseListener(new MouseAdapter() {
            @Override
            public synchronized void mousePressed(MouseEvent me) {

                int index = tblFighters.getSelectedRow();
                if (me.getClickCount() == 2) { //If table is double clicked.
                    if (index != -1 && currentFighter != null) {
                        FighterLookerGUI fighterLooker = new FighterLookerGUI(parent, true, currentFighter, null, tournament);
                        fighterLooker.setVisible(true);
                        fighterLooker.setLocationRelativeTo(null);
                    }

                } else if (me.getClickCount() == 1) { //If table is single clicked.
                    if (index != -1) {
                        int fighterId = (Integer) tblFighters.getModel().getValueAt(tblFighters.convertRowIndexToModel(index), 2);
                        currentFighter = tournament.getFighterManager().getFighterByIndex(fighterId);
                        battlesModel.updateBattleList(findFightersPrimaryLayer(currentFighter));
                    }
                }
            }
        });
    }

    private void addBattlesTableMouseListener() {
        tblBattles.addMouseListener(new MouseAdapter() {
            @Override
            public synchronized void mousePressed(MouseEvent me) {

                int index = tblBattles.getSelectedRow();
                if (me.getClickCount() == 2) { //If table is double clicked.
                    if (index != -1) {
                        int battleId = (Integer) tblBattles.getModel().getValueAt(tblBattles.convertRowIndexToModel(index), 3);
                        Battle b = tournament.getBattleManager().getBattleByIndex(battleId);
                        new SingleBattleDetailsGUI(parent, true, b, tournament).setLocationRelativeTo(null);
                        tournament.setAllBattlesWithGivenFightersWon(b);
                        battlesModel.updateBattleList(findFightersPrimaryLayer(currentFighter));
                        repaint();
                        //tblBattles.setColumnSelectionInterval(index, index);
                        fighterModel.updateFighterList(tournament.getFighterManager().getAllAsArrayList());
                    }

                }
            }
        });
    }

    private synchronized ArrayList<Battle> findFightersPrimaryLayer(Fighter f) {
        int counter = 0;
        System.out.println("Amount of battle layers: " + tournament.getBattleLayers().size());
        for (ArrayList<Battle> battleLayer : tournament.getBattleLayers()) {
            counter = 0;
            for (Battle b : battleLayer) {
                if (b.battleContainsFighter(f)) {
                    counter++;
                }
                if (counter > 1) {
                    return battleLayer;
                }
            }
        }
        return null;
    }

    public void updateWinnerFoundColour(String newColour) {
        cellColourWinnerFound = newColour;
        battleColorRenderer.setWinnerFoundColor(cellColourWinnerFound);
        repaint();

    }

    public void updateNoWinnerFoundColour(String newColour) {
        cellColourNoWinnerFound = newColour;
        battleColorRenderer.setNoWinnerFoundColor(cellColourNoWinnerFound);
        repaint();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jspFighters = new javax.swing.JScrollPane();
        tblFighters = new javax.swing.JTable();
        jspBattles = new javax.swing.JScrollPane();
        tblBattles = new javax.swing.JTable();

        jspFighters.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tblFighters.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1"
            }
        ));
        jspFighters.setViewportView(tblFighters);

        jspBattles.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tblBattles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1"
            }
        ));
        jspBattles.setViewportView(tblBattles);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jspFighters, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jspBattles, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jspBattles, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                        .addComponent(jspFighters, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jspBattles;
    private javax.swing.JScrollPane jspFighters;
    private javax.swing.JTable tblBattles;
    private javax.swing.JTable tblFighters;
    // End of variables declaration//GEN-END:variables
}
