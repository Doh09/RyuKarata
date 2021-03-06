/*
 * This GUI class is used to select the type of tournament you wish to create.
 * A small visual preview of the class will be displayed.
 * The class also allows for the user to select the standard colors of the tournament(s) created.
 */
package GUI.TournamentRelated;

import BE.Fighter;
import BE.StaffPerson;
import GUI.MainGUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.swing.JColorChooser;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class SelectTournamentType extends javax.swing.JDialog {

    /**
     * Variables
     */
    //Language strings
    private String txtTitle = "Vælg turneringstype";
    private String txtPnlSelectType = "Vælg turneringstype";
    private String txtJrdBtnCupTournament = "Cupturnering";
    private String txtJrdBtnGroupTournament = "Gruppeturnering";
    private String txtPnlSelectColors = "Vælg farver";
    private String txtBtnColorSelect = "Vælg farve";
    private String txtLblWinnerFound = "Hvis kampen har en vinder";
    private String txtLblNoWinnerFound = "Hvis kampen ikke har en vinder";
    private String txtLblNoFighters = "Hvis kampen ingen kæmpere har";
    private String txtPnlPreview = "Forhåndsvisning";
    private String txtBtnCancel = "Annuller";
    private String txtBtnNext = "Næste";

    //General
    private MainGUI parent;
    private TreeMap<String, String> colors = new TreeMap();
    private pnlPreviewCupTournament previewCupGUI = new pnlPreviewCupTournament();
    private pnlPreviewGroupTournament previewGroupGUI = new pnlPreviewGroupTournament();
    private ArrayList<Fighter> fighterList; //Used to remember the fighter info in case user clicks "back" to change tournament type.
    private ArrayList<StaffPerson> staffList; //Same as comment above, just for staff.

    //Default colors
    private String cellColorNoFighters = "fillColor=#ffffcc";
    private String cellColorNoWinnerFound = "fillColor=#cce6ff";
    private String cellColorWinnerFound = "fillColor=#66ff33";

    /**
     * Creates new form SelectTournamentType
     *
     * @param parent
     * @param fighterList
     * @param staffList
     */
    public SelectTournamentType(MainGUI parent, ArrayList<Fighter> fighterList, ArrayList<StaffPerson> staffList) {
        super(parent, false);
        if (fighterList == null) {
            this.fighterList = new ArrayList<>();
        } else {
            this.fighterList = fighterList;
        }
        if (staffList == null) {
            this.staffList = new ArrayList<>();
        } else {
            this.staffList = staffList;
        }
        initComponents();

        setDefaultColors();
        this.parent = parent;

        btnGroupSelectTournament.setSelected(jrdBtnCupTournament.getModel(), true);
        pnlPreview.setLayout(new BorderLayout());
        previewCupGUI.setVisible(true);
        pnlPreview.add(previewCupGUI, BorderLayout.CENTER);
        previewGroupGUI.setVisible(false);
        previewCupGUI.setVisible(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setDefaultColors() {
        colors.put("winnerFound", cellColorWinnerFound);
        colors.put("noWinnerFound", cellColorNoWinnerFound);
        colors.put("noFighters", cellColorNoFighters);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupSelectTournament = new javax.swing.ButtonGroup();
        pnlSelectType = new javax.swing.JPanel();
        jrdBtnCupTournament = new javax.swing.JRadioButton();
        jrdBtnGroupTournament = new javax.swing.JRadioButton();
        pnlSelectColors = new javax.swing.JPanel();
        btnColourWinnerFound = new javax.swing.JButton();
        lblWinnerFound = new javax.swing.JLabel();
        btnColourNoWinnerFound = new javax.swing.JButton();
        lblNoWinnerFound = new javax.swing.JLabel();
        lblNoFighters = new javax.swing.JLabel();
        btnColourNoFighters = new javax.swing.JButton();
        jtbMenu = new javax.swing.JToolBar();
        btnCancel = new javax.swing.JButton();
        pnlMenuSpacing = new javax.swing.JPanel();
        btnNext = new javax.swing.JButton();
        pnlPreview = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Vælg turneringstype");

        pnlSelectType.setBorder(javax.swing.BorderFactory.createTitledBorder("Vælg turneringstype"));

        btnGroupSelectTournament.add(jrdBtnCupTournament);
        jrdBtnCupTournament.setSelected(true);
        jrdBtnCupTournament.setText("Cupturnering");
        jrdBtnCupTournament.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cup_Seleted.png"))); // NOI18N
        jrdBtnCupTournament.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cup.png"))); // NOI18N
        jrdBtnCupTournament.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cup_Seleted.png"))); // NOI18N
        jrdBtnCupTournament.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jrdBtnCupTournamentMouseReleased(evt);
            }
        });
        jrdBtnCupTournament.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrdBtnCupTournamentActionPerformed(evt);
            }
        });

        btnGroupSelectTournament.add(jrdBtnGroupTournament);
        jrdBtnGroupTournament.setText("Gruppeturnering");
        jrdBtnGroupTournament.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/group_Selected.png"))); // NOI18N
        jrdBtnGroupTournament.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/group.png"))); // NOI18N
        jrdBtnGroupTournament.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/group_Selected.png"))); // NOI18N
        jrdBtnGroupTournament.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jrdBtnGroupTournamentMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlSelectTypeLayout = new javax.swing.GroupLayout(pnlSelectType);
        pnlSelectType.setLayout(pnlSelectTypeLayout);
        pnlSelectTypeLayout.setHorizontalGroup(
            pnlSelectTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSelectTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSelectTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jrdBtnCupTournament)
                    .addComponent(jrdBtnGroupTournament))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        pnlSelectTypeLayout.setVerticalGroup(
            pnlSelectTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSelectTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jrdBtnCupTournament)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrdBtnGroupTournament)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlSelectColors.setBorder(javax.swing.BorderFactory.createTitledBorder("Vælg farver"));

        btnColourWinnerFound.setText("Vælg farve");
        btnColourWinnerFound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColourWinnerFoundActionPerformed(evt);
            }
        });

        lblWinnerFound.setText("Hvis kampen har en vinder");

        btnColourNoWinnerFound.setText("Vælg farve");
        btnColourNoWinnerFound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColourNoWinnerFoundActionPerformed(evt);
            }
        });

        lblNoWinnerFound.setText("Hvis kampen ikke har en vinder");

        lblNoFighters.setText("Hvis kampen ingen kæmpere har");

        btnColourNoFighters.setText("Vælg farve");
        btnColourNoFighters.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColourNoFightersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSelectColorsLayout = new javax.swing.GroupLayout(pnlSelectColors);
        pnlSelectColors.setLayout(pnlSelectColorsLayout);
        pnlSelectColorsLayout.setHorizontalGroup(
            pnlSelectColorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSelectColorsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSelectColorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnColourNoFighters, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnColourNoWinnerFound, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblWinnerFound, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNoWinnerFound, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNoFighters, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnColourWinnerFound, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlSelectColorsLayout.setVerticalGroup(
            pnlSelectColorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSelectColorsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblWinnerFound)
                .addGap(5, 5, 5)
                .addComponent(btnColourWinnerFound)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNoWinnerFound)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnColourNoWinnerFound)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNoFighters)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnColourNoFighters)
                .addContainerGap(93, Short.MAX_VALUE))
        );

        jtbMenu.setBorder(javax.swing.BorderFactory.createTitledBorder("Menu"));
        jtbMenu.setFloatable(false);
        jtbMenu.setRollover(true);

        btnCancel.setText("Annuller");
        btnCancel.setFocusable(false);
        btnCancel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        jtbMenu.add(btnCancel);

        javax.swing.GroupLayout pnlMenuSpacingLayout = new javax.swing.GroupLayout(pnlMenuSpacing);
        pnlMenuSpacing.setLayout(pnlMenuSpacingLayout);
        pnlMenuSpacingLayout.setHorizontalGroup(
            pnlMenuSpacingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 495, Short.MAX_VALUE)
        );
        pnlMenuSpacingLayout.setVerticalGroup(
            pnlMenuSpacingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 19, Short.MAX_VALUE)
        );

        jtbMenu.add(pnlMenuSpacing);

        btnNext.setText("Næste");
        btnNext.setFocusable(false);
        btnNext.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNext.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        jtbMenu.add(btnNext);

        pnlPreview.setBorder(javax.swing.BorderFactory.createTitledBorder("Forhåndsvisning"));

        javax.swing.GroupLayout pnlPreviewLayout = new javax.swing.GroupLayout(pnlPreview);
        pnlPreview.setLayout(pnlPreviewLayout);
        pnlPreviewLayout.setHorizontalGroup(
            pnlPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlPreviewLayout.setVerticalGroup(
            pnlPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnlSelectColors, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlSelectType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlPreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jtbMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlSelectType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlSelectColors, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pnlPreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtbMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed

        if (jrdBtnCupTournament.isSelected()) {
            new CreateSetOfTournaments(parent, false, "Cup", null, colors, fighterList, staffList); //Create cup tournament
            dispose();
        } else if (jrdBtnGroupTournament.isSelected()) {
            new CreateSetOfTournaments(parent, false, "Group", null, colors, fighterList, staffList); //Create group tournament
            dispose();
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnColourWinnerFoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColourWinnerFoundActionPerformed
        Color winnerFoundColor = JColorChooser.showDialog(parent,
                "Vælg farve for 'Hvis en vinder er fundet'", Color.white);
        if (winnerFoundColor != null) {
            colors.put("winnerFound", "fillColor=" + String.format("#%06x", winnerFoundColor.getRGB() & 0x00FFFFFF));
            previewCupGUI.updateWinnerFoundColour(colors.get("winnerFound"));
            previewGroupGUI.updateWinnerFoundColour(colors.get("winnerFound"));
        }

    }//GEN-LAST:event_btnColourWinnerFoundActionPerformed

    private void btnColourNoWinnerFoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColourNoWinnerFoundActionPerformed
        Color noWinnerFoundColor = JColorChooser.showDialog(parent,
                "Vælg farve for 'Hvis en vinder ikke er fundet'", Color.white);
        if (noWinnerFoundColor != null) {
            colors.put("noWinnerFound", "fillColor=" + String.format("#%06x", noWinnerFoundColor.getRGB() & 0x00FFFFFF));
            previewCupGUI.updateNoWinnerFoundColour(colors.get("noWinnerFound"));
            previewGroupGUI.updateNoWinnerFoundColour(colors.get("noWinnerFound"));
        }
    }//GEN-LAST:event_btnColourNoWinnerFoundActionPerformed

    private void btnColourNoFightersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColourNoFightersActionPerformed
        Color winnerFoundColor = JColorChooser.showDialog(parent,
                "Vælg farve for 'Hvis kampen ingen kæmpere har'", Color.white);
        if (winnerFoundColor != null) {
            colors.put("noFighters", "fillColor=" + String.format("#%06x", winnerFoundColor.getRGB() & 0x00FFFFFF));
            previewCupGUI.updateNoFightersColour(colors.get("noFighters"));
        }
    }//GEN-LAST:event_btnColourNoFightersActionPerformed

    private void jrdBtnCupTournamentActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jrdBtnCupTournamentActionPerformed
    {//GEN-HEADEREND:event_jrdBtnCupTournamentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jrdBtnCupTournamentActionPerformed

    private void jrdBtnCupTournamentMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jrdBtnCupTournamentMouseReleased
        if (jrdBtnCupTournament.isSelected()) {
            btnColourNoFighters.setEnabled(true);
            pnlPreview.add(previewCupGUI, BorderLayout.CENTER);
            previewCupGUI.setVisible(true);
            previewGroupGUI.setVisible(false);
            previewCupGUI.updateWinnerFoundColour(colors.get("winnerFound"));
            previewCupGUI.updateNoWinnerFoundColour(colors.get("noWinnerFound"));
            previewCupGUI.updateNoFightersColour(colors.get("noFighters"));
        }
    }//GEN-LAST:event_jrdBtnCupTournamentMouseReleased

    private void jrdBtnGroupTournamentMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jrdBtnGroupTournamentMouseReleased
        if (jrdBtnGroupTournament.isSelected()) {
            btnColourNoFighters.setEnabled(false);
            pnlPreview.add(previewGroupGUI, BorderLayout.CENTER);
            previewGroupGUI.setVisible(true);
            previewCupGUI.setVisible(false);
            previewCupGUI.updateWinnerFoundColour(colors.get("winnerFound"));
            previewCupGUI.updateNoWinnerFoundColour(colors.get("noWinnerFound"));
            previewCupGUI.updateNoFightersColour(colors.get("noFighters"));
        }
    }//GEN-LAST:event_jrdBtnGroupTournamentMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnColourNoFighters;
    private javax.swing.JButton btnColourNoWinnerFound;
    private javax.swing.JButton btnColourWinnerFound;
    private javax.swing.ButtonGroup btnGroupSelectTournament;
    private javax.swing.JButton btnNext;
    private javax.swing.JRadioButton jrdBtnCupTournament;
    private javax.swing.JRadioButton jrdBtnGroupTournament;
    private javax.swing.JToolBar jtbMenu;
    private javax.swing.JLabel lblNoFighters;
    private javax.swing.JLabel lblNoWinnerFound;
    private javax.swing.JLabel lblWinnerFound;
    private javax.swing.JPanel pnlMenuSpacing;
    private javax.swing.JPanel pnlPreview;
    private javax.swing.JPanel pnlSelectColors;
    private javax.swing.JPanel pnlSelectType;
    // End of variables declaration//GEN-END:variables
}
