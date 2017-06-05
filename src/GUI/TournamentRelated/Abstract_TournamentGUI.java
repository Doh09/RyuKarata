/*
 * This class is used as the core for displaying tournaments using the JGraph library.
 * The class creates listeners, buttons, and core functionality for the programs CupTournament
 * and potentially other tournaments that would use the JGraph library.
 */
package GUI.TournamentRelated;

import BE.Tournaments.Abstract_Tournament;
import BE.Tournaments.Battles.Battle;
import BLL.Facades.FacadeToBLL;
import BLL.ScreenImage;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public abstract class Abstract_TournamentGUI extends javax.swing.JFrame {

    /**
     * Variables
     */
    //Language strings
    protected String title = "Turneringstræ";
    protected String tournamentName = "Turneringstræ";
    protected String txtBtnSaveAsImage = "Gem som billede";
    protected String txtChooserDialogTitle = "Vælg en fildestination";
    protected String txtChooserApproveButton = "Gem";
    protected String txtApproveButtonToolTip = "Husk at skrive et filnavn!";
    protected String txtChooserToolTip = "Gå til den ønskede mappe, angiv et filnavn og tryk så 'gem'.";
    protected String txtBtnColorWinner = "Vælg 'vinder fundet' farve";
    protected String txtChooserColorWinner = "Vælg farve for 'Hvis en vinder er fundet'";
    protected String txtBtnColorNoWinner = "Vælg 'ingen vinder' fundet farve";
    protected String txtChooserColorNoWinner = "Vælg farve for 'Hvis ingen vinder er fundet'";
    protected String txtBtnColorNoFighters = "Vælg 'ingen kæmpere' farve";
    protected String txtChooserColorNoFighters = "Vælg farve for 'Hvis kampen ingen kæmpere har'";

    //General
    protected final FacadeToBLL facadeToBLL = FacadeToBLL.getInstance();
    protected java.awt.Frame parent;
    protected Abstract_Tournament tournament;
    protected mxGraph graph = new mxGraph();

    protected mxGraphComponent graphComponent;
    protected JScrollPane jsp;
    protected int vScrollSpeed = 35;
    protected final Object graphParent;
    protected ArrayList<TreeMap<Integer, Object>> verticesLayers;
    protected TreeMap<String, mxCell> allCells;
    protected boolean aftermathCreated = false;

    //Colors = hexadecimal numbers.
    protected String cellColorNoFighters;
    protected String cellColorNoWinnerFound;
    protected String cellColorWinnerFound;

    //JComponents
    protected JButton btnWinnerFound;
    protected JButton btnNoWinnerFound;
    protected JButton btnNoFighters;
    protected JButton btnSaveAsImage;
    protected JLabel lblTournamentName;

    //
    public Abstract_TournamentGUI(java.awt.Frame parent, Abstract_Tournament tournament) {
        this.parent = parent;
        this.tournament = tournament;
        graphParent = graph.getDefaultParent();
        initializeFrameSettings();
        setColors();

        initializeVerticeVariables();

        drawTheTournament();
        graph.setCellsDisconnectable(false);
        graph.setCellsCloneable(false);
        graph.setCellsEditable(false);
        graph.setCellsSelectable(false);
        graphComponent = new mxGraphComponent(graph); //Make the graph into a component.
        graphComponent.setConnectable(false);
        graphComponent.setDragEnabled(false);
        graphComponent.setKeepSelectionVisibleOnZoom(true);
        setUpOtherGUIComponents();
        refreshCellColours();
    }

    private void initializeVerticeVariables() {
        verticesLayers = new ArrayList<>();
        allCells = new TreeMap();
        for (int layers = 0; layers < tournament.getBattleLayers().size(); layers++) {
            verticesLayers.add(new TreeMap<>()); 
            //Add an amount of vertices layers equal to the amount of battle layers.
        }
    }

    protected void initializeFrameSettings() {

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 320);

        if (!tournament.getName().isEmpty()) {
            title = title + " - " + tournament.getName();
        }
        if (!tournament.getPartOfLargerTournament().isEmpty()) {
            title = title + " - " + tournament.getPartOfLargerTournament();
        }
        setTitle(title);
        setResizable(true);
        setVisible(true);
    }

    protected void setColors() {
        cellColorNoFighters = tournament.getColorManager().getCellColorNoFighters();
        cellColorNoWinnerFound = tournament.getColorManager().getCellColorNoWinnerFound();
        cellColorWinnerFound = tournament.getColorManager().getCellColorWinnerFound();
    }

    protected void refreshCellColours() {
        for (Battle b : tournament.getBattleManager().getAllAsArrayList()) {
            mxCell cellToUpdate = allCells.get("ID:" + b.getId());
            updateTheCellColour(b, cellToUpdate);
            if (cellToUpdate != null) {
                graph.getModel().setValue(cellToUpdate, b);
            }
        }
    }

    protected void setUpOtherGUIComponents() {
        setButtonsAndListeners();
        getContentPane().setLayout(new BorderLayout());
        jsp = new JScrollPane(graphComponent); //Add the tree to a JScrollPane
        jsp.setWheelScrollingEnabled(true);
        getContentPane().add(jsp, BorderLayout.CENTER);
        JToolBar jtb = new JToolBar("Menu");
        jtb.setBorder(new TitledBorder("Menu"));
        jtb.setFloatable(false);
        jtb.add(btnSaveAsImage);
        JPanel fillerPanel = new JPanel();

        if (tournament.getName() != null && !tournament.getName().isEmpty()) {
                tournamentName = "- Turneringstræ for: " + tournament.getName() + " - ";
            
        }
        if (tournament.getPartOfLargerTournament() != null && !tournament.getPartOfLargerTournament().isEmpty()) {
       tournamentName = tournamentName + " en del af " + tournament.getPartOfLargerTournament();
            

        }
        lblTournamentName = new JLabel(tournamentName);
        fillerPanel.add(lblTournamentName);
        jtb.add(fillerPanel); //Add panel for spacing and tournament name.
        jtb.add(btnWinnerFound);
        jtb.add(btnNoWinnerFound);
        jtb.add(btnNoFighters);
        getContentPane().add(jtb, BorderLayout.NORTH);
    }

    abstract void drawTheTournament();

    abstract void linkVerticesWithOneAnother();

    protected void addScrollWheelListener() {
        graphComponent.getGraphControl().addMouseWheelListener(new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent mwe) {
                jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getValue() + mwe.getWheelRotation() * vScrollSpeed);
            }
        });
    }

    protected void addNodeClickedListener() {
        graphComponent.getGraphControl().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Object cell = graphComponent.getCellAt(e.getX(), e.getY());
                if (cell != null) {
                    mxCell downCastedCell = (mxCell) cell;
                    if (downCastedCell.getId().length() > 1) {
                        //if cell Id has a string length higher than 1 (meaning its not an edge).

                        String sToAnalyze = graph.getLabel(cell); //get the toString label.
                        int indexOfDot = sToAnalyze.indexOf("."); //Find the index of the dot sign.
                        if (!sToAnalyze.startsWith("Red:") && !sToAnalyze.startsWith("Blue:") && !sToAnalyze.isEmpty()) {
                            String sBattleId = String.valueOf(sToAnalyze.toCharArray(), 0, indexOfDot); //Split off the id.
                            int iBattleId = -1;
                            try {
                                iBattleId = Integer.parseInt(sBattleId); //Retrieve the id from the string.
                            } catch (Exception ex) {
                                System.err.println("ERROR - BattleId is not an integer - CupTournamentGUI");
                                throw ex;
                            }
                            if (iBattleId != -1) {
                                if (!tournament.getBattleManager().getBattleByIndex(iBattleId).isBeingEdited()) { //if not being edited by someone else.
                                    Battle b = tournament.getBattleManager().getBattleByIndex(iBattleId);
                                    b.setBeingEdited(true); //editing starts
                                    new SingleBattleDetailsGUI(parent, true, b, tournament).setLocationRelativeTo(null);
                                    //Update the managers and GUI according to the changes.
                                    tournament.getBattleManager().addWinnerIfNextBattle(b); //add the winner to the next battle.
                                    //add the winner to the next battle.
                                    tournament.getBattleManager().addOrUpdateBattleToMap(b); //update the battle
                                    //update the battle
                                    tournament.getFighterManager().addOrUpdateFighterToMap(b.getFighter1Red()); //update the fighters in the battle
                                    //update the fighters in the battle
                                    tournament.getFighterManager().addOrUpdateFighterToMap(b.getFighter2Blue()); //
                                    //
                                    mxCell cellToUpdate = allCells.get("ID:" + b.getId());
                                    updateTheCellColour(b, cellToUpdate);
                                    graph.getModel().setValue(cellToUpdate, b); //Update the selected GUI cell. NOTE: needs to check if the updated fighters are in other battles, 
                                    //and update them as well, in the Manager as well as GUI.
                                    updateTheGUIOfTheNextBattle(b);
                                    b.setBeingEdited(false); //editing stops
                                }
                            } else {
                                System.err.println("ERROR - No valid BattleId selected - CupTournamentGUI");
                            }
                        }
                    }
                }
            }
        }
        );
    }

    protected void updateTheGUIOfTheNextBattle(Battle b) {
        if (b.getNextBattle() != null) {
            //Downcast to find cell by id given to it when creating it.
            mxCell nextCell = allCells.get("ID:" + b.getNextBattle().getId());
            updateTheCellColour(b.getNextBattle(), nextCell);
            graph.getModel().setValue(nextCell, b.getNextBattle()); //Update the next GUI cell.
//                    updateTheGUIOfTheNextBattle(b.getNextBattle()); //Make recursive calls to reflect the changes throughout the tournament tree.
        } else if (b.getNextBattle() == null) {
            //setUpAftermath(b);
            
        }
    }

    protected void updateTheCellColour(Battle b, mxCell cellToUpdate) {
        //Update cell colour to green'ish if winner is found, set it blue'ish if winner is not found.
        //Change the colour according if no fighters present and whether or not winner is found..
        if (b != null && cellToUpdate != null) {
            if (b.getFighter2Blue() == null && b.getFighter1Red() == null) {
                cellToUpdate.setStyle(cellColorNoFighters);
            } else if (b.getWinner() != null) {
                cellToUpdate.setStyle(cellColorWinnerFound);
            } else if (b.getWinner() == null) {
                cellToUpdate.setStyle(cellColorNoWinnerFound);
            }
        }
    }

    protected void gatherAllCellsInOneCollection() {
        //Add each cell to a list of all cells, for easier access.
        //This collection is used when updating the cells.
        for (int i = 0; i < verticesLayers.size(); i++) {
            for (Object o : verticesLayers.get(i).values().toArray()) {
                mxCell nextCell = (mxCell) o;
                allCells.put(nextCell.getId(), nextCell);
            }
        }
        //graph.insertEdge(parent, null, "Edge", v1, v2); //Seemingly used to make a line between 2 vertices.
    }

    protected void setButtonsAndListeners() {
        setUpSaveAsImageButton();
        addColourSelectionButtons();
        addNodeClickedListener();
        addScrollWheelListener();
    }

    protected JButton setUpSaveAsImageButton() { //can use all JComponents
        btnSaveAsImage = new JButton(txtBtnSaveAsImage);
        btnSaveAsImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new java.io.File("."));
                chooser.setDialogTitle(txtChooserDialogTitle);
                chooser.setApproveButtonText(txtChooserApproveButton);
                chooser.setApproveButtonToolTipText(txtApproveButtonToolTip);
                chooser.setToolTipText(txtChooserToolTip);
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                //
                // disable the "All files" option.
                //
                chooser.setAcceptAllFileFilterUsed(false);

                //    
                chooser.approveSelection();
                int returnVal = chooser.showOpenDialog(getParent());
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    java.io.File file = chooser.getSelectedFile();
                    int scrollAmount = jsp.getVerticalScrollBar().getModel().getValue(); //needed to adjust for amount scrolled down.
                    jsp.getVerticalScrollBar().getModel().setValue(0); //Set scroll to 0 to avoid dark areas appearing in image.
                    facadeToBLL.savePanelAsImage(ScreenImage.createImage(graphComponent, graphComponent.getBounds()), file.getAbsolutePath()); //save the image on the given path.
                    jsp.getVerticalScrollBar().getModel().setValue(scrollAmount); //set scroll back to where it was.
                } else {
                    System.err.println("Image file destination selection - cancelled by user.");
                }
            }
        }
        );
        return btnSaveAsImage;
    }

    private void addColourSelectionButtons() {
        btnWinnerFound = new JButton(txtBtnColorWinner);
        btnWinnerFound.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e
            ) {
                Color winnerFoundColor = JColorChooser.showDialog(parent,
                        txtChooserColorWinner, Color.white);
                if (winnerFoundColor != null) {
                    cellColorWinnerFound = "fillColor=" + String.format("#%06x", winnerFoundColor.getRGB() & 0x00FFFFFF);
                    tournament.getColorManager().setCellColorWinnerFound(cellColorWinnerFound);
                    refreshCellColours();
                }
            }
        }
        );
        btnNoWinnerFound = new JButton(txtBtnColorNoWinner);

        btnNoWinnerFound.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e
            ) {
                Color noWinnerFoundColor = JColorChooser.showDialog(parent,
                        txtChooserColorNoWinner, Color.white);
                if (noWinnerFoundColor != null) {
                    cellColorNoWinnerFound = "fillColor=" + String.format("#%06x", noWinnerFoundColor.getRGB() & 0x00FFFFFF);
                    tournament.getColorManager().setCellColorNoWinnerFound(cellColorNoWinnerFound);
                    refreshCellColours();
                }
            }
        }
        );
        btnNoFighters = new JButton(txtBtnColorNoFighters);

        btnNoFighters.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e
            ) {
                Color noFightersColor = JColorChooser.showDialog(parent,
                        txtChooserColorNoFighters, Color.white);
                if (noFightersColor != null) {
                    cellColorNoFighters = "fillColor=" + String.format("#%06x", noFightersColor.getRGB() & 0x00FFFFFF);
                    tournament.getColorManager().setCellColorNoFighters(cellColorNoFighters);
                    refreshCellColours();
                }
            }
        }
        );
    }
}
