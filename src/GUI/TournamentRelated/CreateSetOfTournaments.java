/*
 * This GUI class is used to create a SuperTournament and its subtournaments (Abstract_Tournament).
 * The class is used to create requirements for a subtournaments fighters and to administrate
 * which fighters/staff members go in which subtournament.
 */
package GUI.TournamentRelated;

import BE.Fighter;
import BE.StaffPerson;
import BE.Tournaments.Abstract_Tournament;
import BE.Tournaments.Abstract_TournamentPart;
import BLL.TournamentTypes.SuperTournament;
import BLL.Facades.FacadeToBLL;
import GUI.MainGUI;
import GUI.TableModels_And_Tables.FighterStatsInfoModel;
import GUI.TableModels_And_Tables.StaffTableModel;
import java.util.ArrayList;
import java.util.TreeMap;
import GUI.TableModels_And_Tables.GroupTournamentsTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class CreateSetOfTournaments extends javax.swing.JDialog {

    /**
     * Variables
     */
    //msg strings
    private String txtDatePickerErrMsg = "Datoen du vælger, kan ikke ligge i fortiden.";
    private String txtDatePickerErrType = "Fejl";

    //General
    private FacadeToBLL facadeToBLL = FacadeToBLL.getInstance();
    private MainGUI parent;
    private Abstract_Tournament selectedSubTournament;
    private SuperTournament superTournament;

    private String type;
    private boolean edit;
    private TreeMap<String, String> colors;

    private GroupTournamentsTableModel tournamentsTableModel;
    private FighterStatsInfoModel allFightersModel;
    private StaffTableModel allStaffModel;

    private FighterStatsInfoModel fightersInGroupModel;
    private StaffTableModel staffInGroupModel;

    private boolean movingFighters = false;

    /**
     * Creates new form CreateTournament
     *
     * @param parent
     * @param edit
     * @param superTournament
     * @param type
     * @param colors
     * @param figherList
     * @param staffList
     */
    public CreateSetOfTournaments(MainGUI parent, boolean edit, String type, SuperTournament superTournament, TreeMap<String, String> colors, ArrayList<Fighter> figherList, ArrayList<StaffPerson> staffList) {
        super(parent, false);
        this.type = type;
        this.edit = edit;
        this.parent = parent;
        this.superTournament = superTournament;
        this.colors = colors;
        initComponents();

        ifEdit();
        ifNotEdit(figherList, staffList);
        if (type.equalsIgnoreCase("Group")) {
            txtType.setText("Gruppe");
        } else {
            txtType.setText("Cup");
        }
        tblTournaments.setModel(tournamentsTableModel);
        tblTournaments.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblListOfAllFighters.setModel(allFightersModel);
        tblListOfAllStaff.setModel(allStaffModel);
        fightersInGroupModel = new FighterStatsInfoModel(new ArrayList<>());
        staffInGroupModel = new StaffTableModel(new ArrayList<>());
        tblFightersInGroup.setModel(fightersInGroupModel);
        tblStaffInGroup.setModel(staffInGroupModel);
        addGroupTableMouseListener();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setColors(Abstract_Tournament tournamentToColour) {
        if (colors != null) {
            tournamentToColour.getColorManager().setCellColorNoFighters(colors.get("noFighters"));
            tournamentToColour.getColorManager().setCellColorNoWinnerFound(colors.get("noWinnerFound"));
            tournamentToColour.getColorManager().setCellColorWinnerFound(colors.get("winnerFound"));
        }
    }

    private void ifEdit() {
        if (edit) {
            String txtTitle = "Rediger turnering";
            if (!superTournament.getName().isEmpty()) {
                txtTitle = txtTitle + " - " + superTournament.getName();
            }
            setTitle(txtTitle);
            btnSave.setText("Gem ændringer");
            btnCancel.setText("Annuller");
            txtDescription.setText(superTournament.getDescription());
            txtName.setText(superTournament.getName());
            jdpFrom.setDate(superTournament.getStartTime());
            jdpTo.setDate(superTournament.getEndTime());

            setUpTournamentModel();
            setUpAllFightersModel();
            setUpAllStaffModel();
        }
    }

    private void setUpTournamentModel() {
        //Load in tournament list.
        ArrayList<Abstract_Tournament> tournamentList = new ArrayList<>();
        for (Abstract_TournamentPart atp : this.superTournament.getSubTournaments()) {
            tournamentList.add((Abstract_Tournament) atp); //typecast list to Abstract_Tournament
        }
        tournamentsTableModel = new GroupTournamentsTableModel(tournamentList);
    }

    private void ifNotEdit(ArrayList<Fighter> fighterList, ArrayList<StaffPerson> staffList) {
        if (!edit) {
            setTitle("Opret ny turnering");
            btnCancel.setText("Tilbage");
            jdpFrom.setDate(jdpFrom.getLinkDay());
            jdpTo.setDate(jdpTo.getLinkDay());
            superTournament = facadeToBLL.createSuperTournament();
            setUpTournamentModel();
            superTournament.setType(type);
            allFightersModel = new FighterStatsInfoModel(fighterList);
            allStaffModel = new StaffTableModel(staffList);
        }
    }

    private void setUpAllFightersModel() {
        ArrayList<Abstract_Tournament> tempListTournament = new ArrayList<>();
        //Gather all tournaments
        for (Abstract_TournamentPart atp : superTournament.getSubTournaments()) //Typecast all to Abstract_Tournaments.
        {
            tempListTournament.add((Abstract_Tournament) atp);
        }
        //Gather all fighters in one list
        ArrayList<Fighter> tempListFighter = new ArrayList<>();
        for (Abstract_Tournament at : tempListTournament) {
            for (Fighter f : at.getFighterManager().getAllAsArrayList()) {
                tempListFighter.add(f);
            }
        }
        allFightersModel = new FighterStatsInfoModel(tempListFighter);
    }

    private void setUpAllStaffModel() {
        ArrayList<Abstract_Tournament> tempListTournament = new ArrayList<>();
        //Gather all tournaments
        for (Abstract_TournamentPart atp : superTournament.getSubTournaments()) //Typecast all to Abstract_Tournaments.
        {
            tempListTournament.add((Abstract_Tournament) atp);
        }
        //Gather all staff members in one list
        ArrayList<StaffPerson> tempListStaff = new ArrayList<>();
        for (Abstract_Tournament at : tempListTournament) {
            for (StaffPerson sp : at.getStaffManager().getAllAsArrayList()) {
                tempListStaff.add(sp);
            }
        }
        allStaffModel = new StaffTableModel(tempListStaff);
    }

    private void addGroupTableMouseListener() {
        tblTournaments.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {

                int index = tblTournaments.getSelectedRow();
                if (me.getClickCount() == 2) { //If table is double clicked.
                    if (index != -1) {
                        Integer tournamentID = (Integer) tblTournaments.getModel().getValueAt(index, 1);
                        Abstract_Tournament at = facadeToBLL.getTournament(tournamentID);
                        AddGroupGUI addGroupGUI = new AddGroupGUI(at, parent, true, superTournament);
                        addGroupGUI.setVisible(true);

                    }

                } else if (me.getClickCount() == 1) { //If table is single clicked.
                    if (index != -1) {
                        Integer tournamentID = (Integer) tblTournaments.getModel().getValueAt(index, 1);
                        Abstract_Tournament at = facadeToBLL.getTournament(tournamentID);
                        selectedSubTournament = at;
                        fightersInGroupModel.updateFighterList(at.getFighterManager().getAllAsArrayList());
                        staffInGroupModel.updateStaffMemberList(at.getStaffManager().getAllAsArrayList());
                    }
                }
            }
        });
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
        btnEditFightersAndStaff = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        pnlDetails = new javax.swing.JPanel();
        txtName = new javax.swing.JTextField();
        lblName = new javax.swing.JLabel();
        lblStartDate = new javax.swing.JLabel();
        lblEndDate = new javax.swing.JLabel();
        jdpFrom = new org.jdesktop.swingx.JXDatePicker();
        jdpTo = new org.jdesktop.swingx.JXDatePicker();
        txtType = new javax.swing.JTextField();
        lblType = new javax.swing.JLabel();
        lblTypeToolTip = new javax.swing.JLabel();
        lblNameToolTip = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        btnAddSubTournament = new javax.swing.JButton();
        pnlFighters1 = new javax.swing.JPanel();
        jspGroups = new javax.swing.JScrollPane();
        tblTournaments = new javax.swing.JTable();
        btnEditSubTournament = new javax.swing.JButton();
        btnRemoveSubTournament = new javax.swing.JButton();
        pnlPeople = new javax.swing.JPanel();
        pnlStaff = new javax.swing.JPanel();
        jspTournaments1 = new javax.swing.JScrollPane();
        tblStaffInGroup = new javax.swing.JTable();
        pnlFighters = new javax.swing.JPanel();
        jspTournaments = new javax.swing.JScrollPane();
        tblFightersInGroup = new javax.swing.JTable();
        pnlFighters2 = new javax.swing.JPanel();
        jspTournaments3 = new javax.swing.JScrollPane();
        tblListOfAllFighters = new javax.swing.JTable();
        pnlStaff3 = new javax.swing.JPanel();
        Scrollepanel6 = new javax.swing.JScrollPane();
        tblListOfAllStaff = new javax.swing.JTable();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 32767));
        btnAddToGroup = new javax.swing.JButton();
        btnRemoveFromGroup = new javax.swing.JButton();
        lblNameToolTip1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tlbarMenu.setBorder(javax.swing.BorderFactory.createTitledBorder("Menu"));
        tlbarMenu.setFloatable(false);
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
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tlbarMenu.add(jPanel8);

        btnEditFightersAndStaff.setText("Rediger turneringsdeltagere- og stabsmedlemmer");
        btnEditFightersAndStaff.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createTitledBorder("")));
        btnEditFightersAndStaff.setFocusable(false);
        btnEditFightersAndStaff.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEditFightersAndStaff.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEditFightersAndStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditFightersAndStaffActionPerformed(evt);
            }
        });
        tlbarMenu.add(btnEditFightersAndStaff);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tlbarMenu.add(jPanel7);

        btnSave.setText("Opret turneringssæt");
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

        lblTypeToolTip.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/questionMark.png"))); // NOI18N
        lblTypeToolTip.setToolTipText("Turneringstypen bestemmes når man opretter en ny turnering.");

        lblNameToolTip.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/questionMark.png"))); // NOI18N
        lblNameToolTip.setToolTipText("Turneringens navn, hvis turneringen er en del af en større turnering, vil det stå under \"Turneringen er en del af\".");

        txtDescription.setColumns(20);
        txtDescription.setLineWrap(true);
        txtDescription.setRows(5);
        jScrollPane1.setViewportView(txtDescription);

        jLabel1.setText("Turneringsbeskrivelse");

        btnAddSubTournament.setText("Tilføj underturnering");
        btnAddSubTournament.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSubTournamentActionPerformed(evt);
            }
        });

        pnlFighters1.setBorder(javax.swing.BorderFactory.createTitledBorder("Kategorier/Underturneringer"));

        jspGroups.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tblTournaments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1"
            }
        ));
        jspGroups.setViewportView(tblTournaments);

        javax.swing.GroupLayout pnlFighters1Layout = new javax.swing.GroupLayout(pnlFighters1);
        pnlFighters1.setLayout(pnlFighters1Layout);
        pnlFighters1Layout.setHorizontalGroup(
            pnlFighters1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFighters1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jspGroups, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlFighters1Layout.setVerticalGroup(
            pnlFighters1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jspGroups, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
        );

        btnEditSubTournament.setText("Rediger underturneringskrav");
        btnEditSubTournament.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditSubTournamentActionPerformed(evt);
            }
        });

        btnRemoveSubTournament.setText("Fjern underturnering");
        btnRemoveSubTournament.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveSubTournamentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDetailsLayout = new javax.swing.GroupLayout(pnlDetails);
        pnlDetails.setLayout(pnlDetailsLayout);
        pnlDetailsLayout.setHorizontalGroup(
            pnlDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlFighters1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlDetailsLayout.createSequentialGroup()
                        .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNameToolTip))
                    .addComponent(txtName)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetailsLayout.createSequentialGroup()
                        .addGroup(pnlDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdpFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                        .addGroup(pnlDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdpTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlDetailsLayout.createSequentialGroup()
                        .addComponent(lblType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTypeToolTip))
                    .addComponent(txtType)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetailsLayout.createSequentialGroup()
                        .addGroup(pnlDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnRemoveSubTournament, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlDetailsLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(btnAddSubTournament, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEditSubTournament, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(27, 27, 27)))
                .addContainerGap())
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
                .addGroup(pnlDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlFighters1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddSubTournament)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditSubTournament)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemoveSubTournament)
                .addContainerGap())
        );

        pnlPeople.setBorder(javax.swing.BorderFactory.createTitledBorder("Personer"));

        pnlStaff.setBorder(javax.swing.BorderFactory.createTitledBorder("Stabsmedlemmer i valgt underturnering"));

        jspTournaments1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tblStaffInGroup.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1"
            }
        ));
        tblStaffInGroup.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tblStaffInGroupFocusGained(evt);
            }
        });
        jspTournaments1.setViewportView(tblStaffInGroup);

        javax.swing.GroupLayout pnlStaffLayout = new javax.swing.GroupLayout(pnlStaff);
        pnlStaff.setLayout(pnlStaffLayout);
        pnlStaffLayout.setHorizontalGroup(
            pnlStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 478, Short.MAX_VALUE)
            .addGroup(pnlStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jspTournaments1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
        );
        pnlStaffLayout.setVerticalGroup(
            pnlStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 225, Short.MAX_VALUE)
            .addGroup(pnlStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jspTournaments1, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
        );

        pnlFighters.setBorder(javax.swing.BorderFactory.createTitledBorder("Kæmpere i valgt underturnering"));

        jspTournaments.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tblFightersInGroup.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1"
            }
        ));
        tblFightersInGroup.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tblFightersInGroupFocusGained(evt);
            }
        });
        jspTournaments.setViewportView(tblFightersInGroup);

        javax.swing.GroupLayout pnlFightersLayout = new javax.swing.GroupLayout(pnlFighters);
        pnlFighters.setLayout(pnlFightersLayout);
        pnlFightersLayout.setHorizontalGroup(
            pnlFightersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 478, Short.MAX_VALUE)
            .addGroup(pnlFightersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlFightersLayout.createSequentialGroup()
                    .addComponent(jspTournaments, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        pnlFightersLayout.setVerticalGroup(
            pnlFightersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 223, Short.MAX_VALUE)
            .addGroup(pnlFightersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jspTournaments, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE))
        );

        pnlFighters2.setBorder(javax.swing.BorderFactory.createTitledBorder("Alle kæmpere"));

        jspTournaments3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tblListOfAllFighters.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1"
            }
        ));
        tblListOfAllFighters.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tblListOfAllFightersFocusGained(evt);
            }
        });
        jspTournaments3.setViewportView(tblListOfAllFighters);

        javax.swing.GroupLayout pnlFighters2Layout = new javax.swing.GroupLayout(pnlFighters2);
        pnlFighters2.setLayout(pnlFighters2Layout);
        pnlFighters2Layout.setHorizontalGroup(
            pnlFighters2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(pnlFighters2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlFighters2Layout.createSequentialGroup()
                    .addComponent(jspTournaments3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        pnlFighters2Layout.setVerticalGroup(
            pnlFighters2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 223, Short.MAX_VALUE)
            .addGroup(pnlFighters2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jspTournaments3, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE))
        );

        pnlStaff3.setBorder(javax.swing.BorderFactory.createTitledBorder("Alle stabsmedlemmer"));

        Scrollepanel6.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tblListOfAllStaff.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1"
            }
        ));
        tblListOfAllStaff.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tblListOfAllStaffFocusGained(evt);
            }
        });
        Scrollepanel6.setViewportView(tblListOfAllStaff);

        javax.swing.GroupLayout pnlStaff3Layout = new javax.swing.GroupLayout(pnlStaff3);
        pnlStaff3.setLayout(pnlStaff3Layout);
        pnlStaff3Layout.setHorizontalGroup(
            pnlStaff3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Scrollepanel6, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        pnlStaff3Layout.setVerticalGroup(
            pnlStaff3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Scrollepanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
        );

        filler1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnAddToGroup.setText("Tilføj");
        btnAddToGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToGroupActionPerformed(evt);
            }
        });

        btnRemoveFromGroup.setText("Fjern");
        btnRemoveFromGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveFromGroupActionPerformed(evt);
            }
        });

        lblNameToolTip1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/questionMark.png"))); // NOI18N
        lblNameToolTip1.setToolTipText("Det er muligt at vælge mere end 1 person af gangen, når man tilføjer eller fjerner.");

        javax.swing.GroupLayout pnlPeopleLayout = new javax.swing.GroupLayout(pnlPeople);
        pnlPeople.setLayout(pnlPeopleLayout);
        pnlPeopleLayout.setHorizontalGroup(
            pnlPeopleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPeopleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPeopleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filler1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPeopleLayout.createSequentialGroup()
                        .addGroup(pnlPeopleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPeopleLayout.createSequentialGroup()
                                .addGroup(pnlPeopleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(pnlFighters2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(pnlStaff3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 1, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPeopleLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblNameToolTip1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAddToGroup)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlPeopleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRemoveFromGroup)
                            .addGroup(pnlPeopleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(pnlStaff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pnlFighters, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        pnlPeopleLayout.setVerticalGroup(
            pnlPeopleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPeopleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPeopleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlPeopleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAddToGroup)
                        .addComponent(btnRemoveFromGroup))
                    .addComponent(lblNameToolTip1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPeopleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlFighters2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlFighters, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPeopleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlStaff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlStaff3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlPeople, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(tlbarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            facadeToBLL.removeSuperTournament(superTournament.getId());
            new SelectTournamentType(parent, allFightersModel.getFighterList(), allStaffModel.getStaffList());
        } else {
            dispose();
        }
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        superTournament.setName(txtName.getText());
        superTournament.setDescription(txtDescription.getText());
        superTournament.setStartTime(jdpFrom.getDate());
        superTournament.setEndTime(jdpTo.getDate());
        for (Abstract_Tournament at : tournamentsTableModel.getTournamentList()) {
            if (!superTournament.getSubTournaments().contains(at)) {
                at.setStartTime(jdpFrom.getDate());
                at.setEndTime(jdpTo.getDate());
                superTournament.getSubTournaments().add(at);
            }
            at.setPartOfLargerTournament(superTournament.getName());
        }
        if (!edit) {
            facadeToBLL.addOrUpdateSuperTournament(superTournament);
        }
        parent.getRootTournament().getSubTournaments().add(superTournament);
        parent.refreshTournamentList(true);
        dispose();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnAddSubTournamentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSubTournamentActionPerformed

        Abstract_Tournament at = facadeToBLL.createTournament(type);
        setColors(at);
        superTournament.getSubTournaments().add(at);
        at.setParentTournamentPart(superTournament);
        AddGroupGUI addGroupGUI = new AddGroupGUI(at, parent, false, superTournament);
        addGroupGUI.setVisible(true);
        allFightersModel.updateFighterList(superTournament.getFighterManager().getAllAsArrayList());
        allStaffModel.updateStaffMemberList(superTournament.getStaffManager().getAllAsArrayList());
        tournamentsTableModel.updateTournamentList(superTournament.getAllAsAbstract_TournamentList());
    }//GEN-LAST:event_btnAddSubTournamentActionPerformed

    private void btnEditFightersAndStaffActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnEditFightersAndStaffActionPerformed
    {//GEN-HEADEREND:event_btnEditFightersAndStaffActionPerformed
        superTournament.setName(txtName.getText());
        new EditTournamentFightersAndStaff(parent, type, allFightersModel.getFighterList(), allStaffModel.getStaffList(), superTournament, null);
        allFightersModel.updateFighterList(superTournament.getFighterManager().getAllAsArrayList());
        allStaffModel.updateStaffMemberList(superTournament.getStaffManager().getAllAsArrayList());

    }//GEN-LAST:event_btnEditFightersAndStaffActionPerformed

    private void btnAddToGroupActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAddToGroupActionPerformed
    {//GEN-HEADEREND:event_btnAddToGroupActionPerformed
        int index = tblTournaments.getSelectedRow();
        if (index != -1 && selectedSubTournament != null) {
            if (movingFighters) { //move fighters
                int[] selection = tblListOfAllFighters.getSelectedRows();
                for (int fighterIndex : selection) {
                    if (fighterIndex != -1 && !(fighterIndex >= superTournament.getFighterManager().getAllAsArrayList().size())) {
                        Fighter fighter = superTournament.getFighterManager().getAllAsArrayList().get(tblListOfAllFighters.convertRowIndexToModel(fighterIndex));
                        if (!fighter.isInGroup()) {
                            selectedSubTournament.getFighterManager().addOrUpdateFighterToMap(fighter);
                            fighter.setInGroup(true);
                        }
                    }
                }
            } else if (!movingFighters) { //move staff
                int[] selection = tblListOfAllStaff.getSelectedRows();
                for (int staffIndex : selection) {
                    if (staffIndex != -1 && !(staffIndex >= superTournament.getStaffManager().getAllAsArrayList().size())) {
                        StaffPerson staffPerson = superTournament.getStaffManager().getAllAsArrayList().get(tblListOfAllStaff.convertRowIndexToModel(staffIndex));
                        selectedSubTournament.getStaffManager().addOrUpdatePersonToMap(staffPerson);
                    }
                }
            }
            //Update table models.
            allFightersModel.updateFighterList(superTournament.getFighterManager().getAllAsArrayList());
            allStaffModel.updateStaffMemberList(superTournament.getStaffManager().getAllAsArrayList());
            tournamentsTableModel.updateTournamentList(superTournament.getAllAsAbstract_TournamentList());
            fightersInGroupModel.updateFighterList(selectedSubTournament.getFighterManager().getAllAsArrayList());
            staffInGroupModel.updateStaffMemberList(selectedSubTournament.getStaffManager().getAllAsArrayList());
            tblTournaments.setRowSelectionInterval(index, index);
        }
    }//GEN-LAST:event_btnAddToGroupActionPerformed

    private void btnRemoveFromGroupActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnRemoveFromGroupActionPerformed
    {//GEN-HEADEREND:event_btnRemoveFromGroupActionPerformed
        int index = tblTournaments.getSelectedRow();
        if (index != -1 && selectedSubTournament != null) {
            if (movingFighters) { //move fighters
                int fighterIndex = -1;
                int[] selection = tblFightersInGroup.getSelectedRows();
                for (int i = selection.length - 1; i >= 0; i--) { //reverse loop to ensure indexes remain valid.
                    fighterIndex = selection[i];
                    if (fighterIndex != -1 && !(fighterIndex >= selectedSubTournament.getFighterManager().getAllAsArrayList().size())) {
                        Fighter fighter = selectedSubTournament.getFighterManager().getAllAsArrayList().get(tblFightersInGroup.convertRowIndexToModel(fighterIndex));
                        fighter.setInGroup(false);
                        selectedSubTournament.getFighterManager().removeFighterFromMap(fighter.getFighterId());
                    }
                }
            } else if (!movingFighters) { //move staff
                {
                    int staffIndex = -1;
                    int[] selection = tblStaffInGroup.getSelectedRows();
                    for (int i = selection.length - 1; i >= 0; i--) { //reverse loop to ensure indexes remain valid.
                        staffIndex = selection[i];
                        if (staffIndex != -1 && !(staffIndex >= selectedSubTournament.getStaffManager().getAllAsArrayList().size())) {
                            StaffPerson staff = selectedSubTournament.getStaffManager().getAllAsArrayList().get(tblStaffInGroup.convertRowIndexToModel(staffIndex));
                            selectedSubTournament.getStaffManager().removePersonFromMap(staff.getId());
                        }
                    }
                }
            }
            //Update table models
            allFightersModel.updateFighterList(superTournament.getFighterManager().getAllAsArrayList());
            allStaffModel.updateStaffMemberList(superTournament.getStaffManager().getAllAsArrayList());
            tournamentsTableModel.updateTournamentList(superTournament.getAllAsAbstract_TournamentList());
            fightersInGroupModel.updateFighterList(selectedSubTournament.getFighterManager().getAllAsArrayList());
            staffInGroupModel.updateStaffMemberList(selectedSubTournament.getStaffManager().getAllAsArrayList());
            tblTournaments.setRowSelectionInterval(index, index);
        }
    }//GEN-LAST:event_btnRemoveFromGroupActionPerformed

    private void btnRemoveSubTournamentActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnRemoveSubTournamentActionPerformed
    {//GEN-HEADEREND:event_btnRemoveSubTournamentActionPerformed
        int index = tblTournaments.getSelectedRow();
        if (index != -1) {
            Integer tournamentID = (Integer) tblTournaments.getModel().getValueAt(index, 1);

            Abstract_Tournament at = facadeToBLL.getTournament(tournamentID);
            ArrayList<Fighter> list = at.getFighterManager().getAllAsArrayList();
            for (Fighter fighter : list) {
                fighter.setInGroup(false);
            }
            superTournament.removeFromList(at);
            facadeToBLL.removeTournament(at.getId());
        }
        tournamentsTableModel.updateTournamentList(superTournament.getAllAsAbstract_TournamentList());
    }//GEN-LAST:event_btnRemoveSubTournamentActionPerformed

    private void btnEditSubTournamentActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnEditSubTournamentActionPerformed
    {//GEN-HEADEREND:event_btnEditSubTournamentActionPerformed
        int index = tblTournaments.getSelectedRow();
        if (index != -1) {
            Integer tournamentID = (Integer) tblTournaments.getModel().getValueAt(index, 1);

            Abstract_Tournament at = facadeToBLL.getTournament(tournamentID);
            AddGroupGUI addGroupGUI = new AddGroupGUI(at, parent, true, superTournament);
            addGroupGUI.setVisible(true);
        }
        tournamentsTableModel.updateTournamentList(superTournament.getAllAsAbstract_TournamentList());
    }//GEN-LAST:event_btnEditSubTournamentActionPerformed

    private void tblListOfAllFightersFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblListOfAllFightersFocusGained
        movingFighters = true;
    }//GEN-LAST:event_tblListOfAllFightersFocusGained

    private void tblFightersInGroupFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblFightersInGroupFocusGained
        movingFighters = true;
    }//GEN-LAST:event_tblFightersInGroupFocusGained

    private void tblStaffInGroupFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblStaffInGroupFocusGained
        movingFighters = false;
    }//GEN-LAST:event_tblStaffInGroupFocusGained

    private void tblListOfAllStaffFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblListOfAllStaffFocusGained
        movingFighters = false;
    }//GEN-LAST:event_tblListOfAllStaffFocusGained

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane Scrollepanel6;
    private javax.swing.JButton btnAddSubTournament;
    private javax.swing.JButton btnAddToGroup;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnEditFightersAndStaff;
    private javax.swing.JButton btnEditSubTournament;
    private javax.swing.JButton btnRemoveFromGroup;
    private javax.swing.JButton btnRemoveSubTournament;
    private javax.swing.JButton btnSave;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private org.jdesktop.swingx.JXDatePicker jdpFrom;
    private org.jdesktop.swingx.JXDatePicker jdpTo;
    private javax.swing.JScrollPane jspGroups;
    private javax.swing.JScrollPane jspTournaments;
    private javax.swing.JScrollPane jspTournaments1;
    private javax.swing.JScrollPane jspTournaments3;
    private javax.swing.JLabel lblEndDate;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNameToolTip;
    private javax.swing.JLabel lblNameToolTip1;
    private javax.swing.JLabel lblStartDate;
    private javax.swing.JLabel lblType;
    private javax.swing.JLabel lblTypeToolTip;
    private javax.swing.JPanel pnlDetails;
    private javax.swing.JPanel pnlFighters;
    private javax.swing.JPanel pnlFighters1;
    private javax.swing.JPanel pnlFighters2;
    private javax.swing.JPanel pnlPeople;
    private javax.swing.JPanel pnlStaff;
    private javax.swing.JPanel pnlStaff3;
    private javax.swing.JTable tblFightersInGroup;
    private javax.swing.JTable tblListOfAllFighters;
    private javax.swing.JTable tblListOfAllStaff;
    private javax.swing.JTable tblStaffInGroup;
    private javax.swing.JTable tblTournaments;
    private javax.swing.JToolBar tlbarMenu;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtType;
    // End of variables declaration//GEN-END:variables
}
