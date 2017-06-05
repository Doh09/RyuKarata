/*
 * This GUI class is used when importing fighters from an Excel file.
 * The class has a button to display advanced settings, if the user wants to
 * use a custom template different from the standard template.
 * Otherwise the GUI just asks for the club name of the fighters about to be imported.
 */
package GUI;

import BE.ColLooker;
import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class ColLookerFighterStaff extends javax.swing.JDialog {

    /**
     * Variables
     */
    private int fighterLine;
    private int staffLine;
    private int staffPage;
    private ColLooker staffColLooker;
    private ColLooker fighterColLooker;
    private String clubName;
    private Dimension fullSize;

    /**
     * Creates new form ColLookerFighterStaff
     */
    public ColLookerFighterStaff(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        pnlAdvanced.setVisible(false);
        fullSize = getSize();
        setSize(getMinimumSize());
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

        jLabel13 = new javax.swing.JLabel();
        pnlAdvanced = new javax.swing.JPanel();
        pnlStaff = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtStaffLineNumber = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        txtJudge = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtOfficial = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCoach = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtStaffEat = new javax.swing.JTextField();
        txtStaffName = new javax.swing.JTextField();
        pnlFighter = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtFighterLineNumber = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        txtGender = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtGrade = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtAge = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtHeight = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtKata = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtKumite = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtWeight = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtKobudo = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtFighterEat = new javax.swing.JTextField();
        txtFighterName = new javax.swing.JTextField();
        btnAdvanced = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        txtClubName = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel13.setText("Klubnavn:");

        pnlStaff.setBorder(javax.swing.BorderFactory.createTitledBorder("Personale"));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Skriv venligst hvilken linje informationen begynder med i hele tal:"));

        txtStaffLineNumber.setText("2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtStaffLineNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(225, 225, 225))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtStaffLineNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Hvis du bruger din egen template, udfyld venligst hvilken kolonne, de forskellige informationer optræder:"));

        txtJudge.setText("M");
        txtJudge.setPreferredSize(new java.awt.Dimension(20, 20));

        jLabel4.setText("Official");

        txtOfficial.setText("N");
        txtOfficial.setPreferredSize(new java.awt.Dimension(20, 20));

        jLabel5.setText("Coach");

        txtCoach.setText("O");
        txtCoach.setPreferredSize(new java.awt.Dimension(20, 20));

        jLabel6.setText("Navn");

        jLabel11.setText("Fællesspisning");

        jLabel2.setText("Dommer");

        txtStaffEat.setText("P");
        txtStaffEat.setPreferredSize(new java.awt.Dimension(20, 20));

        txtStaffName.setText("L");
        txtStaffName.setPreferredSize(new java.awt.Dimension(20, 20));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtStaffName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtJudge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtOfficial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtCoach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtStaffEat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(0, 264, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStaffName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJudge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOfficial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCoach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStaffEat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout pnlStaffLayout = new javax.swing.GroupLayout(pnlStaff);
        pnlStaff.setLayout(pnlStaffLayout);
        pnlStaffLayout.setHorizontalGroup(
            pnlStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 571, Short.MAX_VALUE)
            .addGroup(pnlStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlStaffLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(pnlStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pnlStaffLayout.setVerticalGroup(
            pnlStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
            .addGroup(pnlStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlStaffLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pnlFighter.setBorder(javax.swing.BorderFactory.createTitledBorder("Kæmpere"));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Skriv også venligst hvilken linje informationen begynder med i hele tal:"));

        txtFighterLineNumber.setText("2");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtFighterLineNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(251, 251, 251))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtFighterLineNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Hvis du bruger din egen template, udfyld venligst hvilken kolonne, de forskellige informationer optræder:"));

        txtGender.setText("B");
        txtGender.setPreferredSize(new java.awt.Dimension(20, 20));

        jLabel7.setText("Køn");

        txtGrade.setText("C");
        txtGrade.setPreferredSize(new java.awt.Dimension(20, 20));

        jLabel8.setText("Grad");

        txtAge.setText("D");
        txtAge.setPreferredSize(new java.awt.Dimension(20, 20));

        jLabel9.setText("Alder");

        txtHeight.setText("F");
        txtHeight.setPreferredSize(new java.awt.Dimension(20, 20));

        jLabel10.setText("Højde");

        txtKata.setText("G");
        txtKata.setPreferredSize(new java.awt.Dimension(20, 20));

        jLabel12.setText("Kata");

        txtKumite.setText("H");
        txtKumite.setPreferredSize(new java.awt.Dimension(20, 20));

        jLabel14.setText("Kumite");

        jLabel15.setText("Vægt");

        txtWeight.setText("E");
        txtWeight.setPreferredSize(new java.awt.Dimension(20, 20));

        jLabel16.setText("Fællesspisning");

        txtKobudo.setText("I");
        txtKobudo.setPreferredSize(new java.awt.Dimension(20, 20));

        jLabel17.setText("Kobudo");

        jLabel3.setText("Navn");

        txtFighterEat.setText("J");
        txtFighterEat.setPreferredSize(new java.awt.Dimension(20, 20));

        txtFighterName.setText("A");
        txtFighterName.setPreferredSize(new java.awt.Dimension(20, 20));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtFighterName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtGrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(txtWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(txtHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(txtKata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(txtKumite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(txtKobudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFighterEat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel15)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14)
                    .addComponent(jLabel17)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFighterName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKumite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKobudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFighterEat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlFighterLayout = new javax.swing.GroupLayout(pnlFighter);
        pnlFighter.setLayout(pnlFighterLayout);
        pnlFighterLayout.setHorizontalGroup(
            pnlFighterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFighterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFighterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlFighterLayout.setVerticalGroup(
            pnlFighterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFighterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlAdvancedLayout = new javax.swing.GroupLayout(pnlAdvanced);
        pnlAdvanced.setLayout(pnlAdvancedLayout);
        pnlAdvancedLayout.setHorizontalGroup(
            pnlAdvancedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnlFighter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlAdvancedLayout.setVerticalGroup(
            pnlAdvancedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAdvancedLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlFighter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnAdvanced.setText("Avanceret");
        btnAdvanced.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAdvancedActionPerformed(evt);
            }
        });

        jButton1.setText("Næste");
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnlAdvanced, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtClubName))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdvanced)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtClubName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btnAdvanced))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlAdvanced, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdvancedActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAdvancedActionPerformed
    {//GEN-HEADEREND:event_btnAdvancedActionPerformed
        pnlAdvanced.setVisible(!pnlAdvanced.isVisible());
        if (pnlAdvanced.isVisible()) {
            setSize(fullSize);
        } else {
            setSize(getMinimumSize());
        }
    }//GEN-LAST:event_btnAdvancedActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed
        if (!txtClubName.getText().trim().isEmpty())
        {
            clubName = txtClubName.getText();
        } else {
            JOptionPane.showMessageDialog(this, "Der kræves der er en klub!", "Advarsel", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (createStaffLookerFailure()) {
            return;
        }

        if (createFighterLookerFailure()) {
            return;
        }

        clubName = txtClubName.getText();
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private boolean createFighterLookerFailure() throws HeadlessException {
        String name = txtFighterName.getText();
        String gender = txtGender.getText();
        String grade = txtGrade.getText();
        String age = txtAge.getText();
        String weight = txtWeight.getText();
        String height = txtHeight.getText();
        String kata = txtKata.getText();
        String kumite = txtKumite.getText();
        String kobudo = txtKobudo.getText();
        String eat = txtFighterEat.getText();
        if (containNumber(name)) {
            return true;
        }
        if (containNumber(gender)) {
            return true;
        }
        if (containNumber(grade)) {
            return true;
        }
        if (containNumber(age)) {
            return true;
        }
        if (containNumber(weight)) {
            return true;
        }
        if (containNumber(height)) {
            return true;
        }
        if (containNumber(kata)) {
            return true;
        }
        if (containNumber(kumite)) {
            return true;
        }
        if (containNumber(kobudo)) {
            return true;
        }
        if (containNumber(eat)) {
            return true;
        }
        fighterColLooker = new ColLooker(name, gender, grade, age, weight, height, kata, kumite, kobudo, eat);
        try {
            fighterLine = Integer.parseInt(txtFighterLineNumber.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showConfirmDialog(this, "Put venligst kun tal ind som linjer", "Advarsel", JOptionPane.ERROR_MESSAGE);
            return true;
        }
        return false;
    }

    private boolean createStaffLookerFailure() throws HeadlessException {
        String name = txtStaffName.getText();
        String judge = txtJudge.getText();
        String official = txtOfficial.getText();
        String coach = txtCoach.getText();
        String eat = txtStaffEat.getText();
        if (containNumber(name)) {
            return true;
        }
        if (containNumber(judge)) {
            return true;
        }
        if (containNumber(official)) {
            return true;
        }
        if (containNumber(coach)) {
            return true;
        }
        if (containNumber(eat)) {
            return true;
        }
        staffColLooker = new ColLooker(name, judge, official, coach, eat);
        try {
            staffLine = Integer.parseInt(txtStaffLineNumber.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showConfirmDialog(this, "Put venligst kun tal ind som linjer");
            return true;
        }
        return false;
    }

    private boolean containNumber(String text) {
        for (int i = 0; i < 10; i++) {
            if (text.contains("" + i)) {
                JOptionPane.showMessageDialog(this, "Kolloner kan ikke være tal!", "Fejl!", JOptionPane.WARNING_MESSAGE);
                return true;
            }
        }
        return false;
    }

    public int getFighterLine() {
        return fighterLine;
    }

    public int getStaffLine() {
        return staffLine;
    }

    public int getStaffPage() {
        return staffPage;
    }

    public ColLooker getStaffColLooker() {
        return staffColLooker;
    }

    public ColLooker getFighterColLooker() {
        return fighterColLooker;
    }

    public String getClubName() {
        return clubName;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdvanced;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JPanel pnlAdvanced;
    private javax.swing.JPanel pnlFighter;
    private javax.swing.JPanel pnlStaff;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTextField txtClubName;
    private javax.swing.JTextField txtCoach;
    private javax.swing.JTextField txtFighterEat;
    private javax.swing.JTextField txtFighterLineNumber;
    private javax.swing.JTextField txtFighterName;
    private javax.swing.JTextField txtGender;
    private javax.swing.JTextField txtGrade;
    private javax.swing.JTextField txtHeight;
    private javax.swing.JTextField txtJudge;
    private javax.swing.JTextField txtKata;
    private javax.swing.JTextField txtKobudo;
    private javax.swing.JTextField txtKumite;
    private javax.swing.JTextField txtOfficial;
    private javax.swing.JTextField txtStaffEat;
    private javax.swing.JTextField txtStaffLineNumber;
    private javax.swing.JTextField txtStaffName;
    private javax.swing.JTextField txtWeight;
    // End of variables declaration//GEN-END:variables
}