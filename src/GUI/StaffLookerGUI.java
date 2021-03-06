/*
 * This GUI class is used to create/edit/inspect a single StaffPerson
 */
package GUI;

import BE.StaffPerson;
import BE.StaffPosition;
import BE.Tournaments.Abstract_Tournament;
import BLL.TournamentTypes.SuperTournament;
import javax.swing.JOptionPane;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class StaffLookerGUI extends javax.swing.JDialog {

    /**
     * Variables
     */
    private StaffPerson staffperson;

    private SuperTournament st; //used it editing from a super-tournament.
    private Abstract_Tournament at; //used if editing from a sub-tournament.

    /**
     * Creates new form StaffLookerGUI
     */
    public StaffLookerGUI(java.awt.Frame parent, boolean modal, SuperTournament st, Abstract_Tournament at) {
        super(parent, modal);
        this.st = st;
        this.at = at;
        initComponents();

        txtClub.setText("");

        rdgPosition.setSelected(rbtnJudge.getModel(), true);
        rdgPosition.setSelected(rbtnOfficial.getModel(), false);
        rdgPosition.setSelected(rbtnCoach.getModel(), false);

    }

    public StaffLookerGUI(java.awt.Frame parent, boolean modal, StaffPerson staffperson, SuperTournament st, Abstract_Tournament at) {
        super(parent, modal);
        this.st = st;
        this.at = at;
        initComponents();

        this.staffperson = staffperson;

        ckbEat.setSelected(staffperson.isCommunalMeal());
        txtClub.setText(staffperson.getClubName());
        txtName.setText(staffperson.getName());

        StaffPosition position = staffperson.getPosition();

        if (position.equals(StaffPosition.Judge)) {
            rdgPosition.setSelected(rbtnJudge.getModel(), true);
            rdgPosition.setSelected(rbtnOfficial.getModel(), false);
            rdgPosition.setSelected(rbtnCoach.getModel(), false);
        }
        if (position.equals(StaffPosition.Official)) {
            rdgPosition.setSelected(rbtnJudge.getModel(), false);
            rdgPosition.setSelected(rbtnOfficial.getModel(), true);
            rdgPosition.setSelected(rbtnCoach.getModel(), false);
        }
        if (position.equals(StaffPosition.Coach)) {
            rdgPosition.setSelected(rbtnJudge.getModel(), false);
            rdgPosition.setSelected(rbtnOfficial.getModel(), false);
            rdgPosition.setSelected(rbtnCoach.getModel(), true);
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

        rdgPosition = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        rbtnJudge = new javax.swing.JRadioButton();
        rbtnOfficial = new javax.swing.JRadioButton();
        rbtnCoach = new javax.swing.JRadioButton();
        btnOk = new javax.swing.JButton();
        ckbEat = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        txtClub = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Personlige Oplysninger"));

        jLabel6.setText("Navn:");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Position"));

        rdgPosition.add(rbtnJudge);
        rbtnJudge.setText("Dommer");

        rdgPosition.add(rbtnOfficial);
        rbtnOfficial.setText("Official");

        rdgPosition.add(rbtnCoach);
        rbtnCoach.setText("Træner");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtnJudge)
                    .addComponent(rbtnOfficial)
                    .addComponent(rbtnCoach))
                .addContainerGap(105, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbtnJudge)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbtnOfficial)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbtnCoach)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnOk.setText("Gem");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        ckbEat.setText("Skal til spisning");

        jLabel7.setText("Klub:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ckbEat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnOk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                                    .addComponent(txtClub))))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtClub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ckbEat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOk)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnOkActionPerformed
    {//GEN-HEADEREND:event_btnOkActionPerformed

        if (!txtName.getText().isEmpty()) {
            String name = txtName.getText();
            StaffPosition position = rdgPosition.isSelected(rbtnJudge.getModel()) ? StaffPosition.Judge
                    : rdgPosition.isSelected(rbtnOfficial.getModel()) ? StaffPosition.Official
                    : rdgPosition.isSelected(rbtnCoach.getModel()) ? StaffPosition.Coach : null;
            boolean eat = ckbEat.isSelected();
            System.out.println(eat);
            if (staffperson != null) {
                staffperson.setClubName(txtClub.getText());
                staffperson.setName(name);
                staffperson.setPosition(position);
                staffperson.setCommunalMeal(eat);
            } else {
                String clubName = txtClub.getText();

                if (st != null) {
                    staffperson = st.getStaffManager().createPerson(
                            name,
                            position,
                            eat,
                            clubName);
                } else {
                    staffperson = at.getStaffManager().createPerson(
                            name,
                            position,
                            eat,
                            clubName);
                }

            }

            dispose();
        } else {
            JOptionPane.showConfirmDialog(this, "Udfyld venligst alle tekst felter", "Fejl", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_btnOkActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOk;
    private javax.swing.JCheckBox ckbEat;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton rbtnCoach;
    private javax.swing.JRadioButton rbtnJudge;
    private javax.swing.JRadioButton rbtnOfficial;
    private javax.swing.ButtonGroup rdgPosition;
    private javax.swing.JTextField txtClub;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
