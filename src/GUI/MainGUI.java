/*
 * This GUI class acts as the main window for the tournament administration.
 * It is displayed after logging into the system.
 * The window has 2 tabs and a log out button.
 * The first tab has a button to create an Excel template for importing fighters
 * along with a brief explanation of its use.
 * The second tab has a TreeTable displaying all tournaments in the system,
 * along with search functionality to search through them and CRUD to control them.
 */
package GUI;

import BE.Tournaments.Abstract_Tournament;
import BLL.TournamentTypes.SuperTournament;
import BLL.Managers.Singleton_Managers.ExcelManager;
import BLL.Facades.FacadeToBLL;
import GUI.TableModels_And_Tables.TreeTable.TreeNodeDataProvider;
import GUI.TableModels_And_Tables.TreeTable.TreeNodeRowModel;
import GUI.TournamentRelated.CreateOrEditTournament;
import GUI.TournamentRelated.CreateSetOfTournaments;
import GUI.TournamentRelated.SelectTournamentType;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import org.netbeans.swing.outline.DefaultOutlineModel;
import org.netbeans.swing.outline.OutlineModel;
import org.netbeans.swing.outline.Outline;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class MainGUI extends javax.swing.JFrame {

    /**
     * Variables
     */
    private FacadeToBLL facadeToBLL = FacadeToBLL.getInstance();
    private Outline treeTable;
    private MainGUI thisGUI = this;
    private SuperTournament rootTournament = new SuperTournament(-1);
    private OutlineModel mdl;

    /**
     * Creates new form MainGUI
     */
    public MainGUI() {
        initComponents();
        jdpStart.setDate(jdpStart.getLinkDay());
        jdpEnd.setDate(jdpEnd.getLinkDay());

        createTournamentTreeTable();

    }

    private void createTournamentTreeTable() {
        pnlTurneringsDisplay.setLayout(new BorderLayout());
        rootTournament.setName("Turneringer");
        //Set up tree table
        TreeModel treeMdl = new DefaultTreeModel(rootTournament);
        mdl = DefaultOutlineModel.createOutlineModel(treeMdl, new TreeNodeRowModel(), true);
        //Outline treeTable;
        treeTable = new Outline();
        treeTable.setColumnHidingAllowed(true);
        treeTable.setColumnSelectionAllowed(true);
        treeTable.setPopupUsedFromTheCorner(true);
        treeTable.setRenderDataProvider(new TreeNodeDataProvider());
        treeTable.setRootVisible(true);
        treeTable.setModel(mdl);
        //Add mouse listener
        addTreeTableMouseListener();

        //Add header
        JScrollPane scrollPane = new JScrollPane(treeTable);
        scrollPane.setRowHeaderView(treeTable.getTableHeader());
        //Set scroll bar policy
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        //Add Tree Table to GUI in a scrollpane
        pnlTurneringsDisplay.add(scrollPane, BorderLayout.CENTER);
    }

    private void addTreeTableMouseListener() {
        treeTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {

                int index = treeTable.getSelectedRow();
                if (me.getClickCount() == 2) { //If table is double clicked.
                    if (index != -1) {
                        Integer tournamentID = (Integer) treeTable.getModel().getValueAt(index, 4);
                        String type = (String) treeTable.getModel().getValueAt(index, 1);
                        if (tournamentID != null && type != null) //if the ID found was an int and selected is not a superTournament.
                        {
                            if (!type.equalsIgnoreCase("Overturnering")) {
                                Abstract_Tournament at = facadeToBLL.getTournament(tournamentID);
                                new CreateOrEditTournament(thisGUI, true, at.getType(), at, null, null, null); //Edit tournament
                            } else {
                                SuperTournament st = facadeToBLL.getSuperTournament(tournamentID);
                                new CreateSetOfTournaments(thisGUI, true, st.getType(), st, null, null, null);
                            }
                        }
                    }

                } else if (me.getClickCount() == 1) { //If table is single clicked.

                    if (index != -1) {
                        Integer tournamentID = (Integer) treeTable.getModel().getValueAt(index, 4);
                        String type = (String) treeTable.getModel().getValueAt(index, 1);
                        if (tournamentID != null && type != null) //if the ID found was an int and selected is not a superTournament.
                        {
                            if (!type.equalsIgnoreCase("Overturnering")) {
                                Abstract_Tournament at = facadeToBLL.getTournament(tournamentID);
                                if (at.getDescription() != null) {
                                    txtAreaTournamentDetails.setText(at.getDescription());
                                }
                            } else {
                                SuperTournament st = facadeToBLL.getSuperTournament(tournamentID);
                                if (st.getDescription() != null) {
                                    txtAreaTournamentDetails.setText(st.getDescription());
                                }
                            }
                        }
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
    private void initComponents()
    {

        pnlMainTabbedPane = new javax.swing.JTabbedPane();
        pnlMain = new javax.swing.JPanel();
        btnCreateTemplate = new javax.swing.JButton();
        ckbTemplateExcelPre2007 = new javax.swing.JCheckBox();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        AddAdministrator = new javax.swing.JButton();
        pnlTournaments = new javax.swing.JPanel();
        pnlTurneringsDisplay = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        btnCreateTournament = new javax.swing.JButton();
        btnViewEditTournament = new javax.swing.JButton();
        btnDeleteTournament = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaTournamentDetails = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jdpEnd = new org.jdesktop.swingx.JXDatePicker();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jdpStart = new org.jdesktop.swingx.JXDatePicker();
        jPanel3 = new javax.swing.JPanel();
        chboxSearchByDate = new javax.swing.JCheckBox();
        pnlSearchPanel = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        cmbBoxFind = new javax.swing.JComboBox<String>();
        txtSearch = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        tlbMenu = new javax.swing.JToolBar();
        jPanel9 = new javax.swing.JPanel();
        btnLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Turneringssystem");

        btnCreateTemplate.setText("Lav skabelon...");
        btnCreateTemplate.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnCreateTemplateActionPerformed(evt);
            }
        });

        ckbTemplateExcelPre2007.setText("Brug Excel fra før 2007");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Når du henter en skabelon, bliver der lavet en skabelon til både \nkæmpere og andre personer.\nKæmpere og andre personer findes i hver deres faner.\n\nHvis du ønsker at benytte din egen Excel fil, bedes du følge \ndisse rækkefølger: \nFor kæmpere:\nNavn, køn, grad, alder, vægt(kg), \nhøjde(m), Kata, Kumite, Kobudo.\nKata, Kumite og Kobudo skal makeres med 1 hvis de har.\n\nFor andre:\nNavn, Dommer, Official, Coach.\nDommer, Official og Coach skal markeres med 1 \nalt efter hvilke titler, de har.");
        jScrollPane3.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
                .addContainerGap())
        );

        AddAdministrator.setText("Tilføj Administrator");
        AddAdministrator.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                AddAdministratorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCreateTemplate)
                    .addComponent(ckbTemplateExcelPre2007)
                    .addComponent(AddAdministrator))
                .addGap(44, 44, 44)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(btnCreateTemplate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ckbTemplateExcelPre2007)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AddAdministrator)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pnlMainTabbedPane.addTab("Hovedside", pnlMain);

        pnlTurneringsDisplay.setBorder(javax.swing.BorderFactory.createTitledBorder("Turneringer"));

        javax.swing.GroupLayout pnlTurneringsDisplayLayout = new javax.swing.GroupLayout(pnlTurneringsDisplay);
        pnlTurneringsDisplay.setLayout(pnlTurneringsDisplayLayout);
        pnlTurneringsDisplayLayout.setHorizontalGroup(
            pnlTurneringsDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 735, Short.MAX_VALUE)
        );
        pnlTurneringsDisplayLayout.setVerticalGroup(
            pnlTurneringsDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 265, Short.MAX_VALUE)
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("Turneringsmenu"));

        btnCreateTournament.setText("Opret ny turnering");
        btnCreateTournament.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnCreateTournamentActionPerformed(evt);
            }
        });

        btnViewEditTournament.setText("Se/rediger turneringsdetaljer");
        btnViewEditTournament.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnViewEditTournamentActionPerformed(evt);
            }
        });

        btnDeleteTournament.setText("Slet den valgte underturnering");
        btnDeleteTournament.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnDeleteTournamentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnViewEditTournament, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                    .addComponent(btnCreateTournament, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDeleteTournament, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnViewEditTournament)
                .addGap(12, 12, 12)
                .addComponent(btnCreateTournament)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDeleteTournament)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Detaljer om den valgte turnering"));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 53, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 77, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        txtAreaTournamentDetails.setColumns(20);
        txtAreaTournamentDetails.setLineWrap(true);
        txtAreaTournamentDetails.setRows(5);
        jScrollPane1.setViewportView(txtAreaTournamentDetails);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Søgning"));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jdpEnd.addPropertyChangeListener(new java.beans.PropertyChangeListener()
        {
            public void propertyChange(java.beans.PropertyChangeEvent evt)
            {
                jdpEndPropertyChange(evt);
            }
        });

        jLabel12.setText("Til dato");

        jLabel11.setText("Fra dato");

        jdpStart.addPropertyChangeListener(new java.beans.PropertyChangeListener()
        {
            public void propertyChange(java.beans.PropertyChangeEvent evt)
            {
                jdpStartPropertyChange(evt);
            }
        });

        chboxSearchByDate.setText("Søg på dato");
        chboxSearchByDate.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                chboxSearchByDateMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chboxSearchByDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(88, 88, 88))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(chboxSearchByDate)
                .addGap(0, 7, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jdpStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jdpEnd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(jdpStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(jdpEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pnlSearchPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setText("Find");

        cmbBoxFind.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Indeholder", "Passer med", "Starter med", "Slutter med" }));
        cmbBoxFind.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                cmbBoxFindItemStateChanged(evt);
            }
        });

        txtSearch.setText("Klik her for at søge...");
        txtSearch.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusGained(java.awt.event.FocusEvent evt)
            {
                txtSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                txtSearchFocusLost(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                txtSearchKeyReleased(evt);
            }
        });

        jButton1.setText("Søg");

        javax.swing.GroupLayout pnlSearchPanelLayout = new javax.swing.GroupLayout(pnlSearchPanel);
        pnlSearchPanel.setLayout(pnlSearchPanelLayout);
        pnlSearchPanelLayout.setHorizontalGroup(
            pnlSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbBoxFind, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );
        pnlSearchPanelLayout.setVerticalGroup(
            pnlSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbBoxFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1))
                    .addComponent(jLabel13))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlSearchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlSearchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout pnlTournamentsLayout = new javax.swing.GroupLayout(pnlTournaments);
        pnlTournaments.setLayout(pnlTournamentsLayout);
        pnlTournamentsLayout.setHorizontalGroup(
            pnlTournamentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTournamentsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTournamentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlTournamentsLayout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlTurneringsDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlTournamentsLayout.setVerticalGroup(
            pnlTournamentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTournamentsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTurneringsDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTournamentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnlMainTabbedPane.addTab("Turneringer", pnlTournaments);

        tlbMenu.setBorder(javax.swing.BorderFactory.createTitledBorder("Menu"));
        tlbMenu.setFloatable(false);
        tlbMenu.setRollover(true);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 731, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 27, Short.MAX_VALUE)
        );

        tlbMenu.add(jPanel9);

        btnLogout.setText("Log ud");
        btnLogout.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnLogout.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnLogoutActionPerformed(evt);
            }
        });
        tlbMenu.add(btnLogout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tlbMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlMainTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tlbMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlMainTabbedPane))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnLogoutActionPerformed
    {//GEN-HEADEREND:event_btnLogoutActionPerformed
        LoginUI login = new LoginUI();
        login.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnCreateTemplateActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnCreateTemplateActionPerformed
    {//GEN-HEADEREND:event_btnCreateTemplateActionPerformed
        // TODO add your handling code here:
        boolean oldExcel = ckbTemplateExcelPre2007.isSelected();
        JFileChooser fc = new JFileChooser();

        fc.setDialogTitle("Vælg en mappe hvor filen skal gemmes");
        int returnVal = fc.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            String name = file.getAbsolutePath()+(oldExcel?".lsx":".xlsx");
            try {
                ExcelManager.instance().createTemplate(name);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            System.err.println("NO FILE CHOSEN");
        }
    }//GEN-LAST:event_btnCreateTemplateActionPerformed

    private void btnCreateTournamentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateTournamentActionPerformed

        new SelectTournamentType(this, null, null);

    }//GEN-LAST:event_btnCreateTournamentActionPerformed

    public void refreshTournamentList(boolean amountOfTournamentsChanged) {
        //Less pretty way of updating the TreeTable, but tested with a looot of tournaments and no speed issues appeared.
        //This way we avoid any potential errors with updating the wrong tree paths or table model parts.
        if (treeTable != null) {
            if (amountOfTournamentsChanged) {
                rootTournament.setSubTournamentsUsingSuperTournaments(facadeToBLL.getAllSuperTournaments());
                txtSearch.setText("Klik her for at søge...");
                chboxSearchByDate.setSelected(false);
            }
            TreeModel treeMdl = new DefaultTreeModel(rootTournament);
            mdl = DefaultOutlineModel.createOutlineModel(treeMdl, new TreeNodeRowModel(), true);
            treeTable.setModel(mdl);
            treeTable.createDefaultColumnsFromModel();

        }
    }

    public SuperTournament getRootTournament() {
        return rootTournament;
    }


    private void btnViewEditTournamentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewEditTournamentActionPerformed
        int index = treeTable.getSelectedRow();
        if (index != -1) {
            Integer tournamentID = (Integer) treeTable.getModel().getValueAt(index, 4);
            if (tournamentID != null) //if the ID found was an int.
            {
                Abstract_Tournament at = facadeToBLL.getTournament(tournamentID);
                new CreateOrEditTournament(thisGUI, true, at.getType(), at, null, null, null); //Edit tournament
            }
        }
    }//GEN-LAST:event_btnViewEditTournamentActionPerformed

    private void btnDeleteTournamentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteTournamentActionPerformed
        int index = treeTable.getSelectedRow();
        if (index != -1) {
            Integer tournamentID = (Integer) treeTable.getModel().getValueAt(index, 4);
            String type = (String) treeTable.getModel().getValueAt(index, 1);
            if (tournamentID != null && type != null) //if the ID found was an int and selected is not a superTournament.
            {
                if (!type.equalsIgnoreCase("Overturnering")) {
                    deleteSubTournament(tournamentID);
                } else if (type.equalsIgnoreCase("Overturnering")) {
                    SuperTournament st = facadeToBLL.getSuperTournament(tournamentID);
                    String tournamentName = "Navn ikke angivet";
                    if (st.getName() != null) {
                        tournamentName = st.getName();
                    }
                    Object[] choices
                            = {
                                "Slet", "Annuller"
                            };
                    Object defaultChoice = choices[0];
                    int selectedOption = JOptionPane.showOptionDialog(null,
                            "Er du helt sikker på at du vil slette overturneringen '" + st.getName() + "'?",
                            "Bekræft sletning af turnering",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            choices,
                            defaultChoice);
                    if (selectedOption == JOptionPane.YES_OPTION) {
                        st.removeAllSubtournaments();
                        rootTournament.getSubTournaments().remove(st);
                        facadeToBLL.removeSuperTournament(st.getId());
                        refreshTournamentList(true);
                    }
                }
            }
        }
    }//GEN-LAST:event_btnDeleteTournamentActionPerformed

    private void deleteSubTournament(Integer tournamentID) throws HeadlessException {
        Abstract_Tournament at = facadeToBLL.getTournament(tournamentID);
        String tournamentName = "Navn ikke angivet";
        if (at.getName() != null) {
            tournamentName = at.getName();
        }
        Object[] choices
                = {
                    "Slet", "Annuller"
                };
        Object defaultChoice = choices[0];
        int selectedOption = JOptionPane.showOptionDialog(null,
                "Er du helt sikker på at du vil slette turneringen '" + at.getName() + "'?",
                "Bekræft sletning af turnering",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                choices,
                defaultChoice);
        if (selectedOption == JOptionPane.YES_OPTION) {
            int parentId = at.getParentAsTournamentPart().getId();
            facadeToBLL.getSuperTournament(parentId).getSubTournaments().remove(at);
            facadeToBLL.removeTournament(at.getId());
            if (facadeToBLL.getSuperTournament(parentId).getSubTournaments().isEmpty()) {
                facadeToBLL.removeSuperTournament(parentId); //if parent has no subTournaments, delete it.
            }
            refreshTournamentList(true);
        }
    }

    private void txtSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchFocusGained
        if (txtSearch.getText().equalsIgnoreCase("Klik her for at søge...")) {
            txtSearch.setText("");
        }
    }//GEN-LAST:event_txtSearchFocusGained

    private void txtSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchFocusLost
        if (txtSearch.getText().isEmpty()) {
            txtSearch.setText("Klik her for at søge...");
        }
        txtSearchKeyReleased(null);
    }//GEN-LAST:event_txtSearchFocusLost

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased

        String text = txtSearch.getText();
        int searchMode = 0;
        switch (cmbBoxFind.getSelectedIndex()) {
            case 0: //Contains
                searchMode = 0;
                break;
            case 1: //Matches
                searchMode = 1;
                break;
            case 2: //Begins with
                searchMode = 2;
                break;
            case 3: //Ends with
                searchMode = 3;
                break;
        }

        if (text.trim().length() == 0 || text.equalsIgnoreCase("Klik her for at søge...")) {
            if (!chboxSearchByDate.isSelected()) {
                rootTournament.setSubTournaments(new ArrayList(facadeToBLL.getAllSuperTournaments()));
            } else {
                rootTournament.setSubTournaments(new ArrayList(facadeToBLL.searchForTournamentsByDateOnly(jdpStart.getDate(), jdpEnd.getDate())));
            }
        } else {
            rootTournament.setSubTournaments(new ArrayList(facadeToBLL.searchForTournamentsByName(text, searchMode, chboxSearchByDate.isSelected(), jdpStart.getDate(), jdpEnd.getDate())));
        }
        refreshTournamentList(false);
    }//GEN-LAST:event_txtSearchKeyReleased

    private void jdpStartPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdpStartPropertyChange
        if (jdpEnd.getDate() != null && jdpStart.getDate() != null) {
            if (jdpStart.getDate().after(jdpEnd.getDate())) //if 'from' date is after 'to' date, set them equal.
            {
                jdpEnd.setDate(jdpStart.getDate());
            }
        }
        txtSearchKeyReleased(null);
    }//GEN-LAST:event_jdpStartPropertyChange

    private void jdpEndPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdpEndPropertyChange
        if (jdpEnd.getDate() != null && jdpStart.getDate() != null) {
            if (jdpEnd.getDate().before(jdpStart.getDate())) //if 'to' date is before 'from' date, set them equal.
            {
                jdpStart.setDate(jdpEnd.getDate());
            }
        }
        txtSearchKeyReleased(null);
    }//GEN-LAST:event_jdpEndPropertyChange

    private void cmbBoxFindItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbBoxFindItemStateChanged
        txtSearchKeyReleased(null);
    }//GEN-LAST:event_cmbBoxFindItemStateChanged

    private void chboxSearchByDateMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chboxSearchByDateMouseReleased
        txtSearchKeyReleased(null);
    }//GEN-LAST:event_chboxSearchByDateMouseReleased

    private void AddAdministratorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_AddAdministratorActionPerformed
    {//GEN-HEADEREND:event_AddAdministratorActionPerformed
    new AddAdministrator();
            
    }//GEN-LAST:event_AddAdministratorActionPerformed
    private void adjustLanguage() {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddAdministrator;
    private javax.swing.JButton btnCreateTemplate;
    private javax.swing.JButton btnCreateTournament;
    private javax.swing.JButton btnDeleteTournament;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnViewEditTournament;
    private javax.swing.JCheckBox chboxSearchByDate;
    private javax.swing.JCheckBox ckbTemplateExcelPre2007;
    private javax.swing.JComboBox<String> cmbBoxFind;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private org.jdesktop.swingx.JXDatePicker jdpEnd;
    private org.jdesktop.swingx.JXDatePicker jdpStart;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JTabbedPane pnlMainTabbedPane;
    private javax.swing.JPanel pnlSearchPanel;
    private javax.swing.JPanel pnlTournaments;
    private javax.swing.JPanel pnlTurneringsDisplay;
    private javax.swing.JToolBar tlbMenu;
    private javax.swing.JTextArea txtAreaTournamentDetails;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
