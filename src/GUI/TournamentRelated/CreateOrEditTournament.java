/*
 * This GUI class is used to inspect/edit a single subtournament (Abstract_Tournament).
 * It is also through this class that the GUI of the tournament can be displayed.
 */
package GUI.TournamentRelated;

import BE.Fighter;
import BE.StaffPerson;
import BE.Tournaments.Abstract_Tournament;
import BLL.TournamentTypes.CupTournament;
import BLL.Facades.FacadeToBLL;
import BLL.TournamentTypes.GroupTournament;
import BLL.TournamentTypes.SuperTournament;
import GUI.MainGUI;
import GUI.TableModels_And_Tables.FighterTableModel;
import GUI.TableModels_And_Tables.StaffTableModel;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.swing.JOptionPane;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class CreateOrEditTournament extends javax.swing.JDialog {

    /**
     * Variables
     */
    //Language strings
    private String txtTitle = "Rediger turnering";
    private String txtPnlDetails = "Turneringsdetaljer";
    private String txtLblName = "Turneringsnavn";
    private String txtLblNameToolTip = "Turneringens navn, hvis turneringen er en del af en større turnering, skal dette stå under 'Turneringen er en del af'.";
    private String txtLblPartOf = "Turneringen er en del af";
    private String txtLblPartOfToolTip = "Hvis turneringen er en del af en større turnering, skrives det her.";
    private String txtLblType = "Turneringstype";
    private String txtLblTypeToolTip = "Turneringstypen bestemmes når man opretter en ny turnering.";
    private String txtLblStartDate = "Startdato";
    private String txtLblEndDate = "Slutdato";
    private String txtDatePickerErrMsg = "Datoen du vælger, kan ikke ligge i fortiden.";
    private String txtDatePickerErrType = "Fejl";
    private String txtPnlPeople = "Personer";
    private String txtPnlFighters = "Turneringskæmpere";
    private String txtPnlStaff = "Stabsmedlemmer i turneringen";
    private String txtBtnEditStaff = "Rediger turneringsdeltagere- og stabsmedlemmer";
    private String txtBtnCancel = "Annuller";
    private String txtBtnShowTree = "Vis turneringstræ";
    private String txtBtnSave = "Gem turnering";

    //General
    private MainGUI parent;
    private FacadeToBLL facadeToBLL = FacadeToBLL.getInstance();
    private FighterTableModel fighterModel;
    private StaffTableModel staffModel;
    private Abstract_Tournament tournament;
    private String type;
    private boolean edit;
    private TreeMap<String, String> colors;

    /**
     * Creates new form CreateTournament
     *
     * @param parent
     * @param edit
     * @param tournament
     * @param type
     * @param colors
     * @param figherList
     * @param staffList
     */
    public CreateOrEditTournament(MainGUI parent, boolean edit, String type, Abstract_Tournament tournament, TreeMap<String, String> colors, ArrayList<Fighter> figherList, ArrayList<StaffPerson> staffList) {
        super(parent, false);
        this.type = type;
        this.edit = edit;
        this.parent = parent;
        this.tournament = tournament;
        this.colors = colors;
        initComponents();

        if (type.equalsIgnoreCase("Group")) {
            btnShowTree.setText("Vis grupper");
        }

        ifEdit();
        ifNotEdit(figherList, staffList);
        txtPartOf.setText(tournament.getPartOfLargerTournament());
        setColors();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setColors() {
        if (colors != null) {
            tournament.getColorManager().setCellColorNoFighters(colors.get("noFighters"));
            tournament.getColorManager().setCellColorNoWinnerFound(colors.get("noWinnerFound"));
            tournament.getColorManager().setCellColorWinnerFound(colors.get("winnerFound"));
        }
    }

    private void ifEdit() {
        if (edit) {
            txtTitle = "Rediger turnering";
            txtBtnCancel = "Annuller";
            txtDescription.setText(tournament.getDescription());
            btnCancel.setText(txtBtnCancel);
            if (!tournament.getName().isEmpty()) {
                txtTitle = txtTitle + " - " + tournament.getName();
            }
            if (!tournament.getPartOfLargerTournament().isEmpty()) {
                txtTitle = txtTitle + " - " + tournament.getPartOfLargerTournament();
            }
            txtType.setText(tournament.getType());
            txtName.setText(tournament.getName());
            jdpFrom.setDate(tournament.getStartTime());
            jdpTo.setDate(tournament.getEndTime());
            txtPartOf.setText(tournament.getPartOfLargerTournament());
            fighterModel = new FighterTableModel(tournament.getFighterManager().getAllAsArrayList());
            staffModel = new StaffTableModel(tournament.getStaffManager().getAllAsArrayList());
            tblFighters.setModel(fighterModel);
            tblStaff.setModel(staffModel);
        }
    }

    private void ifNotEdit(ArrayList<Fighter> fighterList, ArrayList<StaffPerson> staffList) {
        if (!edit) {
            setTitle("Opret ny turnering");
            txtBtnCancel = "Tilbage";
            txtType.setText(type);
            btnCancel.setText(txtBtnCancel);
            jdpFrom.setDate(jdpFrom.getLinkDay());
            jdpTo.setDate(jdpTo.getLinkDay());
            tournament = facadeToBLL.createTournament(type);
            fighterModel = new FighterTableModel(fighterList);
            staffModel = new StaffTableModel(staffList);
            tblFighters.setModel(fighterModel);
            tblStaff.setModel(staffModel);
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

        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        tlbarMenu = new javax.swing.JToolBar();
        btnCancel = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        btnShowTree = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        pnlDetails = new javax.swing.JPanel();
        txtName = new javax.swing.JTextField();
        lblName = new javax.swing.JLabel();
        txtPartOf = new javax.swing.JTextField();
        lblPartOf = new javax.swing.JLabel();
        lblStartDate = new javax.swing.JLabel();
        lblEndDate = new javax.swing.JLabel();
        jdpFrom = new org.jdesktop.swingx.JXDatePicker();
        jdpTo = new org.jdesktop.swingx.JXDatePicker();
        txtType = new javax.swing.JTextField();
        lblType = new javax.swing.JLabel();
        lblPartOfToolTip = new javax.swing.JLabel();
        lblTypeToolTip = new javax.swing.JLabel();
        lblNameToolTip = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        pnlPeople = new javax.swing.JPanel();
        pnlStaff = new javax.swing.JPanel();
        jspTournaments1 = new javax.swing.JScrollPane();
        tblStaff = new javax.swing.JTable();
        pnlFighters = new javax.swing.JPanel();
        jspTournaments = new javax.swing.JScrollPane();
        tblFighters = new javax.swing.JTable();
        btnEditStaff = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tlbarMenu.setBorder(javax.swing.BorderFactory.createTitledBorder("Menu"));
        tlbarMenu.setRollover(true);

        btnCancel.setText("Annuller");
        btnCancel.setFocusable(false);
        btnCancel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        tlbarMenu.add(btnCancel);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 243, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 17, Short.MAX_VALUE)
        );

        tlbarMenu.add(jPanel8);

        btnShowTree.setText("Vis turneringens kampe");
        btnShowTree.setFocusable(false);
        btnShowTree.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnShowTree.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnShowTree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowTreeActionPerformed(evt);
            }
        });
        tlbarMenu.add(btnShowTree);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 243, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 17, Short.MAX_VALUE)
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

        pnlDetails.setBorder(javax.swing.BorderFactory.createTitledBorder("Turneringsdetaljer"));

        lblName.setText("Turneringsnavn");
        lblName.setToolTipText("");

        txtPartOf.setEditable(false);
        txtPartOf.setEnabled(false);

        lblPartOf.setText("Turneringen er en del af");
        lblPartOf.setToolTipText("");

        lblStartDate.setText("Start dato");

        lblEndDate.setText("Slut dato");

        jdpFrom.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdpFromPropertyChange(evt);
            }
        });

        jdpTo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdpToPropertyChange(evt);
            }
        });

        txtType.setEditable(false);
        txtType.setText("Cup");
        txtType.setToolTipText("");
        txtType.setEnabled(false);

        lblType.setText("Turneringstype");
        lblType.setToolTipText("");

        lblPartOfToolTip.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/questionMark.png"))); // NOI18N
        lblPartOfToolTip.setToolTipText("Hvis turneringen er en del af en større turnering, skrives det her.");

        lblTypeToolTip.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/questionMark.png"))); // NOI18N
        lblTypeToolTip.setToolTipText("Turneringstypen bestemmes når man opretter en ny turnering.");

        lblNameToolTip.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/questionMark.png"))); // NOI18N
        lblNameToolTip.setToolTipText("Turneringens navn, hvis turneringen er en del af en større turnering, skal dette stå under \"Turneringen er en del af\".");

        txtDescription.setColumns(20);
        txtDescription.setLineWrap(true);
        txtDescription.setRows(5);
        jScrollPane1.setViewportView(txtDescription);

        jLabel1.setText("Turneringsbeskrivelse");

        javax.swing.GroupLayout pnlDetailsLayout = new javax.swing.GroupLayout(pnlDetails);
        pnlDetails.setLayout(pnlDetailsLayout);
        pnlDetailsLayout.setHorizontalGroup(
            pnlDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDetailsLayout.createSequentialGroup()
                        .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNameToolTip))
                    .addComponent(txtName)
                    .addComponent(txtPartOf, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetailsLayout.createSequentialGroup()
                        .addGroup(pnlDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDetailsLayout.createSequentialGroup()
                                .addComponent(jdpFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlDetailsLayout.createSequentialGroup()
                                .addGap(0, 1, Short.MAX_VALUE)
                                .addComponent(lblStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)))
                        .addGroup(pnlDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdpTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlDetailsLayout.createSequentialGroup()
                        .addComponent(lblType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTypeToolTip))
                    .addComponent(txtType)
                    .addGroup(pnlDetailsLayout.createSequentialGroup()
                        .addComponent(lblPartOf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPartOfToolTip))
                    .addGroup(pnlDetailsLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        pnlDetailsLayout.setVerticalGroup(
            pnlDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetailsLayout.createSequentialGroup()
                .addGroup(pnlDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblName)
                    .addComponent(lblNameToolTip))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPartOf)
                    .addComponent(lblPartOfToolTip))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPartOf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblType)
                    .addComponent(lblTypeToolTip))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStartDate)
                    .addComponent(lblEndDate))
                .addGap(8, 8, 8)
                .addGroup(pnlDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jdpFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdpTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlPeople.setBorder(javax.swing.BorderFactory.createTitledBorder("Personer"));

        pnlStaff.setBorder(javax.swing.BorderFactory.createTitledBorder("Stabsmedlemmer i turneringen"));

        jspTournaments1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tblStaff.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1"
            }
        ));
        jspTournaments1.setViewportView(tblStaff);

        javax.swing.GroupLayout pnlStaffLayout = new javax.swing.GroupLayout(pnlStaff);
        pnlStaff.setLayout(pnlStaffLayout);
        pnlStaffLayout.setHorizontalGroup(
            pnlStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(pnlStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jspTournaments1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
        );
        pnlStaffLayout.setVerticalGroup(
            pnlStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(pnlStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jspTournaments1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
        );

        pnlFighters.setBorder(javax.swing.BorderFactory.createTitledBorder("Turneringskæmpere"));

        jspTournaments.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tblFighters.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1"
            }
        ));
        jspTournaments.setViewportView(tblFighters);

        javax.swing.GroupLayout pnlFightersLayout = new javax.swing.GroupLayout(pnlFighters);
        pnlFighters.setLayout(pnlFightersLayout);
        pnlFightersLayout.setHorizontalGroup(
            pnlFightersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(pnlFightersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jspTournaments, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
        );
        pnlFightersLayout.setVerticalGroup(
            pnlFightersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 341, Short.MAX_VALUE)
            .addGroup(pnlFightersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jspTournaments, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
        );

        btnEditStaff.setText("Rediger turneringsdeltagere- og stabsmedlemmer");
        btnEditStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditStaffActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPeopleLayout = new javax.swing.GroupLayout(pnlPeople);
        pnlPeople.setLayout(pnlPeopleLayout);
        pnlPeopleLayout.setHorizontalGroup(
            pnlPeopleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPeopleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPeopleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPeopleLayout.createSequentialGroup()
                        .addComponent(pnlFighters, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlStaff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnEditStaff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );
        pnlPeopleLayout.setVerticalGroup(
            pnlPeopleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPeopleLayout.createSequentialGroup()
                .addGroup(pnlPeopleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlFighters, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlStaff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditStaff))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlPeople, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlPeople, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tlbarMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tlbarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditStaffActionPerformed
        tournament.setName(txtName.getText());
        new EditTournamentFightersAndStaff(parent, type, fighterModel.getFighterList(), staffModel.getStaffList(), null, tournament);
        fighterModel.updateFighterList(tournament.getFighterManager().getAllAsArrayList());
        staffModel.updateStaffMemberList(tournament.getStaffManager().getAllAsArrayList());
        SuperTournament st = (SuperTournament) tournament.getParentAsTournamentPart();
        for (Fighter f : tournament.getFighterManager().getAllAsArrayList()) {
            f.setInGroup(true);
        }
        if (st != null) {
            st.getFighterManager().mergeFighterCollections(tournament.getFighterManager().getAllAsArrayList());
            st.getStaffManager().mergeStaffCollections(tournament.getStaffManager().getAllAsArrayList());
        }
    }//GEN-LAST:event_btnEditStaffActionPerformed

    private void jdpToPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdpToPropertyChange
        if (jdpFrom.getDate() != null && jdpTo.getDate() != null) {
            if (!jdpFrom.getDate().before(jdpFrom.getLinkDay())) {
                if (jdpTo.getDate().before(jdpFrom.getDate())) {
                    jdpFrom.setDate(jdpTo.getDate());
                }
            } else {
                jdpTo.setDate(jdpFrom.getLinkDay());
                jdpFrom.setDate(jdpFrom.getLinkDay());
                JOptionPane.showMessageDialog(this, txtDatePickerErrMsg, txtDatePickerErrType, JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jdpToPropertyChange

    private void jdpFromPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdpFromPropertyChange
        if (jdpFrom.getDate() != null && jdpTo.getDate() != null) {
            if (!jdpFrom.getDate().before(jdpFrom.getLinkDay())) {
                if (jdpFrom.getDate().after(jdpTo.getDate())) //if 'from' date is after 'to' date, set them equal.
                {
                    jdpTo.setDate(jdpFrom.getDate());
                }
            } else {
                jdpFrom.setDate(jdpFrom.getLinkDay());
                JOptionPane.showMessageDialog(this, txtDatePickerErrMsg, txtDatePickerErrType, JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jdpFromPropertyChange

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        if (!edit) {
            dispose();
            new SelectTournamentType(parent, fighterModel.getFighterList(), staffModel.getStaffList());
        } else {
            dispose();
        }
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnShowTreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowTreeActionPerformed
        tournament.setName(txtName.getText());
        tournament.setPartOfLargerTournament(txtPartOf.getText());
        if (type.equalsIgnoreCase("Cup")) {
            CupTournament cupTournament = (CupTournament) tournament;
            cupTournament.setUpTournament(fighterModel.getFighterList());
            new CupTournamentGUI(parent, cupTournament);
        } else if (type.equalsIgnoreCase("Group")) {
            GroupTournament groupTournament = (GroupTournament) tournament;
            if (!groupTournament.hasInitialized()) { //if not initialized
                groupTournament.setUpTournament();
            } else { //if initialized
                groupTournament.afterInitializationUpdate(fighterModel.getFighterList());
            }
            new GroupTournamentGUI(parent, groupTournament);
        }
    }//GEN-LAST:event_btnShowTreeActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        tournament.setType(type);
        tournament.setName(txtName.getText());
        tournament.setDescription(txtDescription.getText());
        tournament.setPartOfLargerTournament(txtPartOf.getText());
        tournament.setStartTime(jdpFrom.getDate());
        tournament.setEndTime(jdpTo.getDate());
        tournament.getFighterManager().setFighters(fighterModel.getFighterList());
        tournament.getStaffManager().setPeople(staffModel.getStaffList());
        if (!edit) {
            facadeToBLL.addOrUpdateTournament(tournament);
        }

        parent.refreshTournamentList(true);
        dispose();
    }//GEN-LAST:event_btnSaveActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnEditStaff;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnShowTree;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private org.jdesktop.swingx.JXDatePicker jdpFrom;
    private org.jdesktop.swingx.JXDatePicker jdpTo;
    private javax.swing.JScrollPane jspTournaments;
    private javax.swing.JScrollPane jspTournaments1;
    private javax.swing.JLabel lblEndDate;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNameToolTip;
    private javax.swing.JLabel lblPartOf;
    private javax.swing.JLabel lblPartOfToolTip;
    private javax.swing.JLabel lblStartDate;
    private javax.swing.JLabel lblType;
    private javax.swing.JLabel lblTypeToolTip;
    private javax.swing.JPanel pnlDetails;
    private javax.swing.JPanel pnlFighters;
    private javax.swing.JPanel pnlPeople;
    private javax.swing.JPanel pnlStaff;
    private javax.swing.JTable tblFighters;
    private javax.swing.JTable tblStaff;
    private javax.swing.JToolBar tlbarMenu;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPartOf;
    private javax.swing.JTextField txtType;
    // End of variables declaration//GEN-END:variables
}
