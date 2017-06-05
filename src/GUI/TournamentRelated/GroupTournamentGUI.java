/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.TournamentRelated;

import BE.Fighter;
import BE.Tournaments.Abstract_Tournament;
import BE.Tournaments.Battles.Battle;
import BLL.TournamentTypes.GroupTournament;
import GUI.FighterLookerGUI;
import GUI.MainGUI;
import GUI.TableModels_And_Tables.ColorTableCellRenderer;
import GUI.TableModels_And_Tables.FighterTableModel;
import GUI.TableModels_And_Tables.GroupTournamentBattlesModel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;

/**
 *
 * @author skole
 */
public class GroupTournamentGUI extends javax.swing.JFrame {

    private FighterTableModel fighterModel;
    private GroupTournamentBattlesModel fighterBattlesModel;
    private GroupTournamentBattlesModel allBattlesModel;
    private GroupTournament tournament;
    private MainGUI parent;
    private ColorTableCellRenderer battleColorRenderer;
    private Fighter currentFighter;
    //Colors = hexadecimal numbers.
    private String cellColorNoWinnerFound;
    private String cellColorWinnerFound;

    /**
     * Creates new form GroupTurnement
     *
     * @param parent
     * @param tournament
     */
    public GroupTournamentGUI(MainGUI parent, Abstract_Tournament tournament) {
        initComponents();
        this.parent = parent;
        this.tournament = (GroupTournament) tournament;
        initializeFrameSettings();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void initializeFrameSettings() {
        fighterModel = new FighterTableModel(tournament.getFighterManager().getAllAsArrayList());
        tblFighters.setModel(fighterModel);
        fighterBattlesModel = new GroupTournamentBattlesModel(new ArrayList<>());
        tblFighterBattles.setModel(fighterBattlesModel);
        allBattlesModel = new GroupTournamentBattlesModel(new ArrayList<>());
        tblAllBattles.setModel(allBattlesModel);
        battleColorRenderer = new ColorTableCellRenderer();
        battleColorRenderer.setWinnerFoundColor(tournament.getColorManager().getCellColorWinnerFound());
        battleColorRenderer.setNoWinnerFoundColor(tournament.getColorManager().getCellColorNoWinnerFound());
        tblFighterBattles.setDefaultRenderer(String.class, battleColorRenderer);
        tblAllBattles.setDefaultRenderer(String.class, battleColorRenderer);
        addFighterTableMouseListener();
        addFighterBattlesTableMouseListener();
        addAllBattlesTableMouseListener();
        String title = "Turneringskampe";
        if (!tournament.getName().isEmpty()) {
            title = title + " - " + tournament.getName();
        }
        if (!tournament.getPartOfLargerTournament().isEmpty()) {
            title = title + " - " + tournament.getPartOfLargerTournament();
        }
        setTitle(title);
        addColourSelectionButtons();
        long seed = System.nanoTime();
        ArrayList<Battle> allBattles = (ArrayList<Battle>) tournament.getBattleManager().getAllAsArrayList().clone();
        Collections.shuffle(allBattles, new Random(seed));
        allBattlesModel.updateBattleList(allBattles);
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
                        fighterBattlesModel.updateBattleList(findFightersPrimaryLayer(currentFighter));
                    }
                }
            }
        });
    }

    private void addFighterBattlesTableMouseListener() {
        tblFighterBattles.addMouseListener(new MouseAdapter() {
            @Override
            public synchronized void mousePressed(MouseEvent me) {

                int index = tblFighterBattles.getSelectedRow();
                if (me.getClickCount() == 2) { //If table is double clicked.
                    if (index != -1) {
                        int battleId = (Integer) tblFighterBattles.getModel().getValueAt(tblFighterBattles.convertRowIndexToModel(index), 3);
                        Battle b = tournament.getBattleManager().getBattleByIndex(battleId);
                        new SingleBattleDetailsGUI(parent, true, b, tournament).setLocationRelativeTo(null);
                        tournament.setAllBattlesWithGivenFightersWon(b);
                        fighterBattlesModel.updateBattleList(findFightersPrimaryLayer(currentFighter));
                        repaint();
                        //tblBattles.setColumnSelectionInterval(index, index);
                        fighterModel.updateFighterList(tournament.getFighterManager().getAllAsArrayList());
                    }

                }
            }
        });
    }

    private void addAllBattlesTableMouseListener() {
        tblAllBattles.addMouseListener(new MouseAdapter() {
            @Override
            public synchronized void mousePressed(MouseEvent me) {

                int index = tblAllBattles.getSelectedRow();
                if (me.getClickCount() == 2) { //If table is double clicked.
                    if (index != -1) {
                        int battleId = (Integer) tblAllBattles.getModel().getValueAt(tblAllBattles.convertRowIndexToModel(index), 3);
                        Battle b = tournament.getBattleManager().getBattleByIndex(battleId);
                        new SingleBattleDetailsGUI(parent, true, b, tournament).setLocationRelativeTo(null);
                        tournament.setAllBattlesWithGivenFightersWon(b);
                        allBattlesModel.updateBattleList(findFightersPrimaryLayer(currentFighter));
                        repaint();
                        //tblBattles.setColumnSelectionInterval(index, index);
                        long seed = System.nanoTime();
                        ArrayList<Battle> allBattles = (ArrayList<Battle>) tournament.getBattleManager().getAllAsArrayList().clone();
                        Collections.shuffle(allBattles, new Random(seed));
                        allBattlesModel.updateBattleList(allBattles);
                    }

                }
            }
        });
    }

    private synchronized ArrayList<Battle> findFightersPrimaryLayer(Fighter f) {
        int counter = 0;
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

    protected void setColors() {
        cellColorNoWinnerFound = tournament.getColorManager().getCellColorNoWinnerFound();
        cellColorWinnerFound = tournament.getColorManager().getCellColorWinnerFound();
    }

    private void addColourSelectionButtons() {
        JButton btnWinnerFound = new JButton("Vælg farver for afgjorte kampe");
        btnWinnerFound.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e
            ) {
                Color winnerFoundColor = JColorChooser.showDialog(parent,
                        "Vælg farver for afgjorte kampe", Color.white);
                if (winnerFoundColor != null) {
                    cellColorWinnerFound = "fillColor=" + String.format("#%06x", winnerFoundColor.getRGB() & 0x00FFFFFF);
                    tournament.getColorManager().setCellColorWinnerFound(cellColorWinnerFound);
                    battleColorRenderer.setWinnerFoundColor(tournament.getColorManager().getCellColorWinnerFound());
                    repaint();
                }
            }
        }
        );
        JButton btnNoWinnerFound = new JButton("Vælg farver for kampe ikke kæmpet");

        btnNoWinnerFound.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e
            ) {
                Color noWinnerFoundColor = JColorChooser.showDialog(parent,
                        "Vælg farver for kampe ikke kæmpet", Color.white);
                if (noWinnerFoundColor != null) {
                    cellColorNoWinnerFound = "fillColor=" + String.format("#%06x", noWinnerFoundColor.getRGB() & 0x00FFFFFF);
                    tournament.getColorManager().setCellColorNoWinnerFound(cellColorNoWinnerFound);
                    battleColorRenderer.setNoWinnerFoundColor(tournament.getColorManager().getCellColorNoWinnerFound());
                    repaint();
                }
            }
        }
        );
        tlbarColours.add(btnNoWinnerFound);
        tlbarColours.add(new JPanel()); //add panel for  spacing.
        tlbarColours.add(btnWinnerFound);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jspFighters = new javax.swing.JScrollPane();
        tblFighters = new javax.swing.JTable();
        jspBattles = new javax.swing.JScrollPane();
        tblFighterBattles = new javax.swing.JTable();
        tlbarMenu = new javax.swing.JToolBar();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        jspBattles1 = new javax.swing.JScrollPane();
        tblAllBattles = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tlbarColours = new javax.swing.JToolBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        tblFighterBattles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1"
            }
        ));
        jspBattles.setViewportView(tblFighterBattles);

        tlbarMenu.setBorder(javax.swing.BorderFactory.createTitledBorder("Menu"));
        tlbarMenu.setRollover(true);

        jLabel4.setText("Hint: Dobbeltklik på kampe for at åbne dem op");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(408, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        tlbarMenu.add(jPanel7);

        btnSave.setText("Gem turnering");
        btnSave.setFocusable(false);
        btnSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        tlbarMenu.add(btnSave);

        jspBattles1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tblAllBattles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1"
            }
        ));
        jspBattles1.setViewportView(tblAllBattles);

        jLabel1.setText("Alle kæmpere");

        jLabel2.setText("Alle kampe i tilfældig orden");

        jLabel3.setText("Kampe for den valgte kæmper");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(tlbarMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspFighters, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspBattles, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jspBattles1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspBattles, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                    .addComponent(jspBattles1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jspFighters, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tlbarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tlbarColours.setBorder(javax.swing.BorderFactory.createTitledBorder("Farver"));
        tlbarColours.setFloatable(false);
        tlbarColours.setRollover(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tlbarColours, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(tlbarColours, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        dispose();
    }//GEN-LAST:event_btnSaveActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jspBattles;
    private javax.swing.JScrollPane jspBattles1;
    private javax.swing.JScrollPane jspFighters;
    private javax.swing.JTable tblAllBattles;
    private javax.swing.JTable tblFighterBattles;
    private javax.swing.JTable tblFighters;
    private javax.swing.JToolBar tlbarColours;
    private javax.swing.JToolBar tlbarMenu;
    // End of variables declaration//GEN-END:variables

}
