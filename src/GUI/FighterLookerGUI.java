/*
 * This GUI class is used to create/edit/inspect a single fighter.
 */
package GUI;

import BE.Fighter;
import BE.Tournaments.Abstract_Tournament;
import BLL.TournamentTypes.SuperTournament;
import javax.swing.JOptionPane;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class FighterLookerGUI extends javax.swing.JDialog {

    /**
     * Variables
     */
    private Fighter fighter;
    private SuperTournament st; //used it editing from a super-tournament.
    private Abstract_Tournament at; //used if editing from a sub-tournament.

    /**
     * Creates new form FighterGUI
     */
    public FighterLookerGUI(java.awt.Frame parent, boolean modal, SuperTournament st, Abstract_Tournament at) {
        super(parent, modal);
        this.at = at;
        this.st = st;
        initComponents();
        for (Fighter.Statue item : Fighter.Statue.values()) {
            String string = item.equals(item.Sprawny) ? "Spinkel" : item.equals(item.Averge) ? "Gennemsnitlig" : item.equals(item.Heavy) ? "Kraftig" : "";

            cmbStatue.addItem(string);
        }

        for (Fighter.Speed item : Fighter.Speed.values()) {
            String string = item.equals(item.Slow) ? "Langsom" : item.equals(item.Averge) ? "Gennemsnitlig" : item.equals(item.Fast) ? "Hurtig" : "";

            cmbSpeed.addItem(string);
        }

        for (Fighter.Toughness item : Fighter.Toughness.values()) {
            String string = item.equals(item.Soft) ? "Ømskindet" : item.equals(item.Averge) ? "Gennemsnitlig" : item.equals(item.Hard) ? "Hårdfør" : "";

            cmbToughness.addItem(string);
        }

        for (Fighter.BattleAbility item : Fighter.BattleAbility.values()) {
            String string = item.equals(item.Bad) ? "Knapt så god" : item.equals(item.Averge) ? "Gennemsnitlig" : item.equals(item.Good) ? "God" : "";

            cmbBattleAbility.addItem(string);
        }

        for (Fighter.RuleUnderstanding item : Fighter.RuleUnderstanding.values()) {
            String string = item.equals(item.Bad) ? "Knapt så god" : item.equals(item.Average) ? "Gennemsnitlig" : item.equals(item.Good) ? "God" : "";

            cmbRuleUnderstanding.addItem(string);
        }

        for (Fighter.Gender item : Fighter.Gender.values()) {
            String string = item.equals(item.Male) ? "M" : item.equals(item.Female) ? "K" : "";

            cmbGender.addItem(string);
        }

        cmbBattleAbility.setSelectedIndex(0);
        cmbGender.setSelectedIndex(0);
        cmbRuleUnderstanding.setSelectedIndex(0);
        cmbSpeed.setSelectedIndex(0);
        cmbStatue.setSelectedIndex(0);
        cmbToughness.setSelectedIndex(0);

    }

    /**
     * Constructor
     *
     * @param parent
     * @param modal
     * @param fighter
     * @param st
     * @param at
     */
    public FighterLookerGUI(java.awt.Frame parent, boolean modal, Fighter fighter, SuperTournament st, Abstract_Tournament at) {
        super(parent, modal);
        this.st = st;
        initComponents();
        this.fighter = fighter;
        for (Fighter.Statue item : Fighter.Statue.values()) {
            String string = item.equals(item.Sprawny) ? "Spinkel" : item.equals(item.Averge) ? "Gennemsnitlig" : item.equals(item.Heavy) ? "Kraftig" : "";

            cmbStatue.addItem(string);
        }

        for (Fighter.Speed item : Fighter.Speed.values()) {
            String string = item.equals(item.Slow) ? "Langsom" : item.equals(item.Averge) ? "Gennemsnitlig" : item.equals(item.Fast) ? "Hurtig" : "";

            cmbSpeed.addItem(string);
        }

        for (Fighter.Toughness item : Fighter.Toughness.values()) {
            String string = item.equals(item.Soft) ? "Ømskindet" : item.equals(item.Averge) ? "Gennemsnitlig" : item.equals(item.Hard) ? "Hårdfør" : "";

            cmbToughness.addItem(string);
        }

        for (Fighter.BattleAbility item : Fighter.BattleAbility.values()) {
            String string = item.equals(item.Bad) ? "Knapt så god" : item.equals(item.Averge) ? "Gennemsnitlig" : item.equals(item.Good) ? "God" : "";

            cmbBattleAbility.addItem(string);
        }

        for (Fighter.RuleUnderstanding item : Fighter.RuleUnderstanding.values()) {
            String string = item.equals(item.Bad) ? "Knapt så god" : item.equals(item.Average) ? "Gennemsnitlig" : item.equals(item.Good) ? "God" : "";

            cmbRuleUnderstanding.addItem(string);
        }

        for (Fighter.Gender item : Fighter.Gender.values()) {
            String string = item.equals(item.Male) ? "M" : item.equals(item.Female) ? "K" : "";

            cmbGender.addItem(string);
        }

        ckbEat.setSelected(fighter.isCommunalMeal());
        txtClub.setText(fighter.getClubName());

        txtName.setText(fighter.getName());
        txtAge.setText("" + fighter.getAge());
        txtHeight.setText("" + fighter.getHeight());
        txtWeight.setText("" + fighter.getWeight());

        ckbKata.setSelected(fighter.isKata());
        ckbKumite.setSelected(fighter.isKumite());
        ckbKobudo.setSelected(fighter.isKobudo());

        if (fighter.getBattleAbility() != null) {
            cmbBattleAbility.setSelectedIndex(fighter.getBattleAbility().ordinal());
            cmbGender.setSelectedIndex(fighter.getGender().ordinal());
            cmbRuleUnderstanding.setSelectedIndex(fighter.getRuleUnderstanding().ordinal());
            cmbSpeed.setSelectedIndex(fighter.getSpeed().ordinal());
            cmbStatue.setSelectedIndex(fighter.getStatue().ordinal());
            cmbToughness.setSelectedIndex(fighter.getToughness().ordinal());
        } else {
            cmbBattleAbility.setSelectedIndex(0);
            cmbGender.setSelectedIndex(0);
            cmbRuleUnderstanding.setSelectedIndex(0);
            cmbSpeed.setSelectedIndex(0);
            cmbStatue.setSelectedIndex(0);
            cmbToughness.setSelectedIndex(0);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmbStatue = new javax.swing.JComboBox();
        cmbSpeed = new javax.swing.JComboBox();
        cmbToughness = new javax.swing.JComboBox();
        cmbBattleAbility = new javax.swing.JComboBox();
        cmbRuleUnderstanding = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        ckbKumite = new javax.swing.JCheckBox();
        ckbKata = new javax.swing.JCheckBox();
        ckbKobudo = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtAge = new javax.swing.JTextField();
        cmbGender = new javax.swing.JComboBox();
        txtWeight = new javax.swing.JTextField();
        txtHeight = new javax.swing.JTextField();
        txtClub = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnOk = new javax.swing.JButton();
        ckbEat = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Kamp Oplysninger"));

        jLabel1.setText("Statur:");

        jLabel2.setText("Hurtighed:");

        jLabel3.setText("Ømskindethed:");

        jLabel4.setText("Kampevne:");

        jLabel5.setText("Regelforståelse:");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Kamptype(r)"));

        ckbKumite.setText("Kumite");

        ckbKata.setText("Kata");

        ckbKobudo.setText("Kobudo");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ckbKata)
                    .addComponent(ckbKumite)
                    .addComponent(ckbKobudo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(ckbKata)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ckbKumite)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ckbKobudo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbStatue, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbSpeed, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbToughness, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbBattleAbility, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbRuleUnderstanding, 0, 101, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbStatue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbToughness, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbBattleAbility, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbRuleUnderstanding, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Personlige Oplysninger"));

        jLabel6.setText("Navn:");

        jLabel7.setText("Køn:");

        jLabel8.setText("Alder:");

        jLabel9.setText("Vægt (kg):");

        jLabel10.setText("Højde(m):");

        jLabel11.setText("Klub:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHeight)
                            .addComponent(txtWeight)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtName, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtAge, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmbGender, 0, 170, Short.MAX_VALUE)
                            .addComponent(txtClub))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtClub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cmbGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Afslut"));

        btnOk.setText("Gem");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        ckbEat.setText("Skal til spisning");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ckbEat)
                    .addComponent(btnOk))
                .addContainerGap(146, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(ckbEat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 3, Short.MAX_VALUE)
                .addComponent(btnOk))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnOkActionPerformed
    {//GEN-HEADEREND:event_btnOkActionPerformed

        try {
            if (!txtName.getText().isEmpty() && !txtAge.getText().isEmpty() && !txtHeight.getText().isEmpty() && !txtWeight.getText().isEmpty()) {
                String name = txtName.getText();
                int age = Integer.parseInt(txtAge.getText());
                double height = Double.parseDouble(txtHeight.getText());
                double weight = Double.parseDouble(txtWeight.getText());
                boolean eat = ckbEat.isSelected();
                boolean kata = ckbKata.isSelected();
                boolean kobudo = ckbKobudo.isSelected();
                boolean kumite = ckbKumite.isSelected();

                Fighter.BattleAbility battleAbility = Fighter.BattleAbility.values()[cmbBattleAbility.getSelectedIndex()];
                Fighter.Gender gender = Fighter.Gender.values()[cmbGender.getSelectedIndex()];
                Fighter.RuleUnderstanding ruleUnderstanding = Fighter.RuleUnderstanding.values()[cmbRuleUnderstanding.getSelectedIndex()];
                Fighter.Speed speed = Fighter.Speed.values()[cmbSpeed.getSelectedIndex()];
                Fighter.Statue statue = Fighter.Statue.values()[cmbStatue.getSelectedIndex()];
                Fighter.Toughness toughness = Fighter.Toughness.values()[cmbToughness.getSelectedIndex()];

                if (fighter != null) {
                    fighter.setClubName(txtClub.getText());
                    fighter.setName(name);
                    fighter.setAge(age);
                    fighter.setHeight(height);
                    fighter.setWeight(weight);
                    fighter.setCommunalMeal(eat);

                    fighter.setKata(kata);
                    fighter.setKumite(kumite);
                    fighter.setKobudo(kobudo);

                    fighter.setBattleAbility(battleAbility);
                    fighter.setGender(gender);
                    fighter.setRuleUnderstanding(ruleUnderstanding);
                    fighter.setSpeed(speed);
                    fighter.setStatue(statue);
                    fighter.setToughness(toughness);
                } else {
                    String clubName = txtClub.getText();
                    if (st != null) {
                        fighter = st.getFighterManager().createFighter(name, eat, gender, age, age, weight, height, kata, kobudo, kumite, clubName);
                    } else {
                        fighter = at.getFighterManager().createFighter(name, eat, gender, age, age, weight, height, kata, kobudo, kumite, clubName);
                    }
                    fighter.setBattleAbility(battleAbility);
                    fighter.setGender(gender);
                    fighter.setRuleUnderstanding(ruleUnderstanding);
                    fighter.setSpeed(speed);
                    fighter.setStatue(statue);
                    fighter.setToughness(toughness);
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(getParent(), "Udfyld venligst alle felter.", "Fejl", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(getParent(), "Skriv venligst kun tal ved 'Alder', 'Vægt' og 'Højde'.", "Fejl", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnOkActionPerformed

    public Fighter getFighter() {
        return fighter;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOk;
    private javax.swing.JCheckBox ckbEat;
    private javax.swing.JCheckBox ckbKata;
    private javax.swing.JCheckBox ckbKobudo;
    private javax.swing.JCheckBox ckbKumite;
    private javax.swing.JComboBox cmbBattleAbility;
    private javax.swing.JComboBox cmbGender;
    private javax.swing.JComboBox cmbRuleUnderstanding;
    private javax.swing.JComboBox cmbSpeed;
    private javax.swing.JComboBox cmbStatue;
    private javax.swing.JComboBox cmbToughness;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTextField txtClub;
    private javax.swing.JTextField txtHeight;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtWeight;
    // End of variables declaration//GEN-END:variables
}
