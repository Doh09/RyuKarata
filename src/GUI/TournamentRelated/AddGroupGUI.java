/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.TournamentRelated;

import BE.Fighter;
import BE.Tournaments.Abstract_Tournament;
import BLL.TournamentTypes.SuperTournament;
import GUI.MainGUI;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class AddGroupGUI extends javax.swing.JDialog {

    private Abstract_Tournament tournament;
    private SuperTournament superTournament;
    private boolean edit;

    /**
     * Creates new form AddFighter
     *
     * @param tournament
     * @param parent
     * @param edit
     * @param superTournament
     */
    public AddGroupGUI(Abstract_Tournament tournament, MainGUI parent, boolean edit, SuperTournament superTournament) {
        super(parent, true);
        initComponents();
        this.edit = edit;
        this.tournament = tournament;
        this.superTournament = superTournament;
        if (edit) {
            if (tournament != null) {
                txtTournamentName.setText(tournament.getName());
            }
            setupGui();
        }
    }

    private void ifEdit() {
        ArrayList<Fighter> list = tournament.getFighterManager().getAllAsArrayList();
        for (Fighter fighter : list) 
        {
        fighter.setInGroup(false);
        tournament.getFighterManager().removeFighterFromMap(fighter.getFighterId());
        }
        fixGruop();
    }

    private void ifNotEdit() {
        fixGruop();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jTextField11 = new javax.swing.JTextField();
        Groups4 = new javax.swing.JTextField();
        jTextFieldAge = new javax.swing.JTextField();
        Groups5 = new javax.swing.JTextField();
        Groups6 = new javax.swing.JTextField();
        jTextFieldGrade = new javax.swing.JTextField();
        Groups7 = new javax.swing.JTextField();
        jTextFieldWeight = new javax.swing.JTextField();
        Groups8 = new javax.swing.JTextField();
        Groups9 = new javax.swing.JTextField();
        jTextFieldheight = new javax.swing.JTextField();
        Groups10 = new javax.swing.JTextField();
        jCheckumite = new javax.swing.JCheckBox();
        jCheckkata = new javax.swing.JCheckBox();
        jCheckBoxkobudo = new javax.swing.JCheckBox();
        jComboBoxGrade = new javax.swing.JComboBox();
        jComboBoxHeight = new javax.swing.JComboBox();
        jComboBoxWeight = new javax.swing.JComboBox();
        jComboBoxAge = new javax.swing.JComboBox();
        txtTournamentName = new javax.swing.JTextField();
        GroupsGender = new javax.swing.JTextField();
        jComboBoxGender = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTextField11.setEditable(false);
        jTextField11.setText("Turneringsnavn");

        Groups4.setEditable(false);
        Groups4.setText("Alder");

        jTextFieldAge.setText("1");

        Groups5.setEditable(false);
        Groups5.setText("kata");

        Groups6.setEditable(false);
        Groups6.setText("Grad");

        jTextFieldGrade.setText("1");

        Groups7.setEditable(false);
        Groups7.setText("kumite");

        jTextFieldWeight.setText("1");

        Groups8.setEditable(false);
        Groups8.setText("Vægt");

        Groups9.setEditable(false);
        Groups9.setText("kobudo");

        jTextFieldheight.setText("1");

        Groups10.setEditable(false);
        Groups10.setText("Højde");

        jComboBoxGrade.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Lige Meget", "Præcis", "Alt Andet", "Støre", "Mindre" }));

        jComboBoxHeight.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Lige Meget", "Præcis", "Alt Andet", "Støre", "Mindre" }));

        jComboBoxWeight.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Lige Meget", "Præcis", "Alt Andet", "Støre", "Mindre" }));

        jComboBoxAge.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Lige Meget", "Præcis", "Alt Andet", "Støre", "Mindre" }));

        txtTournamentName.setText("Ikke navngivet");

        GroupsGender.setEditable(false);
        GroupsGender.setText("Køn");

        jComboBoxGender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mand", "Begge", "Kvinde" }));

        jButton1.setText("gem");
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Luk");
        jButton2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Groups5)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckkata))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(Groups9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBoxkobudo))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(Groups7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckumite)))
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Groups4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBoxGrade, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(GroupsGender, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldAge)
                            .addComponent(jComboBoxGender, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jTextFieldGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(Groups6, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Groups8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jComboBoxWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextFieldWeight, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Groups10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldheight, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTournamentName, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTournamentName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Groups10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldheight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Groups8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Groups6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldGrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxGrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Groups4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GroupsGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Groups9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBoxkobudo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Groups7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckumite))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Groups5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckkata)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton2ActionPerformed
    {//GEN-HEADEREND:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed
        if (edit) {
            ifEdit();
        } else {
            ifNotEdit();
        }
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Groups10;
    private javax.swing.JTextField Groups4;
    private javax.swing.JTextField Groups5;
    private javax.swing.JTextField Groups6;
    private javax.swing.JTextField Groups7;
    private javax.swing.JTextField Groups8;
    private javax.swing.JTextField Groups9;
    private javax.swing.JTextField GroupsGender;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBoxkobudo;
    private javax.swing.JCheckBox jCheckkata;
    private javax.swing.JCheckBox jCheckumite;
    private javax.swing.JComboBox jComboBoxAge;
    private javax.swing.JComboBox jComboBoxGender;
    private javax.swing.JComboBox jComboBoxGrade;
    private javax.swing.JComboBox jComboBoxHeight;
    private javax.swing.JComboBox jComboBoxWeight;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextFieldAge;
    private javax.swing.JTextField jTextFieldGrade;
    private javax.swing.JTextField jTextFieldWeight;
    private javax.swing.JTextField jTextFieldheight;
    private javax.swing.JTextField txtTournamentName;
    // End of variables declaration//GEN-END:variables

    private void fixGruop() {
        tournament.setName(txtTournamentName.getText());
        //add fighters stats
        
         if (txtTournamentName.getText().trim().isEmpty()|| jTextFieldGrade.getText().trim().isEmpty()||
                 jTextFieldAge.getText().trim().isEmpty() || jTextFieldWeight.getText().trim().isEmpty() || jTextFieldheight.getText().trim().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"udfyld alle felterne");
        }
        else
        {
        double grade = Double.parseDouble(jTextFieldGrade.getText());
        double age = Double.parseDouble(jTextFieldAge.getText());
        double weight = Double.parseDouble(jTextFieldWeight.getText());
        double height = Double.parseDouble(jTextFieldheight.getText());
        boolean kata = jCheckkata.isSelected();
        boolean kumite = jCheckumite.isSelected();
        boolean kobudo = jCheckBoxkobudo.isSelected();
        //here we inter it in to a treeMap
        tournament.getRequirementsValue().put("grade", grade + 0.00);
        tournament.getRequirementsValue().put("age", age + 0.00);
        tournament.getRequirementsValue().put("weight", weight);
        tournament.getRequirementsValue().put("height", height);
        tournament.getRequirementsBooleans().put("kata", kata);
        tournament.getRequirementsBooleans().put("kumite", kumite);
        tournament.getRequirementsBooleans().put("kobudo", kobudo);
        tournament.getRequirementsValue().put("gradeT", jComboBoxGrade.getSelectedIndex() + 0.00);
        tournament.getRequirementsValue().put("ageT", jComboBoxAge.getSelectedIndex() + 0.00);
        tournament.getRequirementsValue().put("weightT", jComboBoxWeight.getSelectedIndex() + 0.00);
        tournament.getRequirementsValue().put("heightT", jComboBoxHeight.getSelectedIndex() + 0.00);
        tournament.getRequirementsValue().put("genderT", jComboBoxGender.getSelectedIndex() + 0.00);
        BLL.TournamentRequirementChecker trc = new BLL.TournamentRequirementChecker();
        trc.newGruop(tournament, superTournament);
        }
    }

    private void setupGui() {
        
        double grade = tournament.getRequirementsValue().get("grade");
        double age = tournament.getRequirementsValue().get("age");
        double weight = tournament.getRequirementsValue().get("weight");
        double height = tournament.getRequirementsValue().get("height");
        boolean kata = tournament.getRequirementsBooleans().get("kata");
        boolean kumite = tournament.getRequirementsBooleans().get("kumite");
        boolean kobudo = tournament.getRequirementsBooleans().get("kobudo");
        double gradeT = tournament.getRequirementsValue().get("gradeT");
        double ageT = tournament.getRequirementsValue().get("ageT");
        double weightT = tournament.getRequirementsValue().get("weightT");
        double heightT = tournament.getRequirementsValue().get("heightT");
        double genderT = tournament.getRequirementsValue().get("genderT");
       
        jTextFieldGrade.setText(Double.toString((int) grade));
        jTextFieldAge.setText(Double.toString((int) age));
        jTextFieldWeight.setText(Double.toString((int) weight));
        jTextFieldheight.setText(Double.toString((int) height));
        jCheckkata.setSelected(kata);
        jCheckumite.setSelected(kumite);
        jCheckBoxkobudo.setSelected(kobudo);

        jComboBoxGrade.setSelectedIndex((int) gradeT);
        jComboBoxAge.setSelectedIndex((int) ageT);
        jComboBoxWeight.setSelectedIndex((int) weightT);
        jComboBoxHeight.setSelectedIndex((int) heightT);
        jComboBoxGender.setSelectedIndex((int) genderT);
        
    }
}
