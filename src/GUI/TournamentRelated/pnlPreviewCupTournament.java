/*
 * This GUI class is a JPanel that displays a preview of the tournament type the user
 * has selected. It is used as a part of the JFrame 'SelectTournamentType'.
 */
package GUI.TournamentRelated;

import BE.Fighter;
import BE.Tournaments.Battles.Battle;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JScrollPane;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class pnlPreviewCupTournament extends javax.swing.JPanel {

    /**
     * Creates new form SelectTournamentType
     */
    /**
     * Variables
     */
    private mxGraph graph = new mxGraph();
    private final Object parent = graph.getDefaultParent();
    //Hexadecimal numbers = colours.
    private String cellColourNoFighters = "fillColor=#ffffcc";
    private String cellColourNoWinnerFound = "fillColor=#cce6ff";
    private String cellColourWinnerFound = "fillColor=#66ff33";
    //Collections
    private ArrayList<ArrayList<Battle>> battleLayers = new ArrayList<>(); //list of the heights/layers in the tournament, where 0 is the initial layer.
    private ArrayList<TreeMap<Integer, Object>> verticesLayers = new ArrayList<>();
    private TreeMap<String, mxCell> allCells = new TreeMap();

    /**
     * Creates new form SelectTournamentType
     */
    public pnlPreviewCupTournament() {
        initComponents();

        createPreviewBattleLayers();
        createPreviewVerticeLayers();

        drawTheTournament();

        mxGraphComponent graphComponent = new mxGraphComponent(graph); //Make the graph into a component.
        graphComponent.setConnectable(false);
        graphComponent.setDragEnabled(false);
        setUpOtherGUIComponents(graphComponent);
        add(graphComponent);
    }

    private void setUpOtherGUIComponents(mxGraphComponent graphComponent) {
        setLayout(new BorderLayout());
        JScrollPane jsp = new JScrollPane(graphComponent); //Add the tree to a JScrollPane
        pnlMainPanel.add(jsp, BorderLayout.CENTER);
    }

    private void createPreviewBattleLayers() { //Create test/preview battles.
        battleLayers.add(new ArrayList<>()); //add initial layer
        battleLayers.add(new ArrayList<>()); //add second layer

        //Set up layer 0.
        Battle b0 = new Battle(0);
        b0.setFighter1Red(new Fighter(0, "Peter Nielsen", Fighter.Gender.Male, 2, 19, 72, 1.9, false, true, false, true, 0, "Klubnavn"));
        b0.setFighter2Blue(new Fighter(1, "Jens Møller", Fighter.Gender.Male, 2, 19, 72, 1.9, false, true, false, true, 0, "Klubnavn"));
        b0.setWinner(b0.getFighter1Red());
        Battle b1 = new Battle(1);
        battleLayers.get(0).add(b0);
        battleLayers.get(0).add(b1);
        //Set up layer 1.
        Battle b2 = new Battle(2);
        b2.setFighter1Red(new Fighter(0, "Peter Nielsen", Fighter.Gender.Male, 2, 19, 72, 1.9, false, true, false, true, 0, "Klubnavn"));
        b2.setWinner(b2.getFighter2Blue());
        battleLayers.get(1).add(b2);
    }

    private void createPreviewVerticeLayers() {
        for (int layers = 0; layers < battleLayers.size(); layers++) {
            verticesLayers.add(new TreeMap<>()); //Add an amount of vertices layers equal to the amount of battle layers.
        }
    }

    private void drawTheTournament() {

        graph.setCellsDisconnectable(false);
        graph.setCellsCloneable(false);
        graph.setCellsEditable(false);
        graph.setCellsSelectable(false);

        graph.getModel().beginUpdate();

        try {
            for (int i = 0; i < battleLayers.size(); i++) { //Loop through the battle layers.
                TreeMap<Integer, Object> currentVerticeLayer = verticesLayers.get(i);
                double width = 110;
                double height = 70;
                double startFactor = (width / 2 + 30) * Math.pow(2, i);
                ArrayList<Battle> arrListBa = battleLayers.get(i); //Loop through the battles in each layer.
                for (int j = 0; j < arrListBa.size(); j++) {

                    double spacing = 150 * Math.pow(2, i);
                    double y = startFactor + (spacing * j);
                    double x = 20 + i * (height + 110);

                    Battle b = arrListBa.get(j); //This is the Battle Object to put in instead of a random Object/String.
                    graph.insertVertex(null, "IDRed:" + b.getId(), null, x, y, width + 10, 35, "fillColor=#ff0000"); //Red
                    graph.insertVertex(null, "IDBlue:" + b.getId(), null, x, y + 35, width + 10, 35, "fillColor=#0000ff"); //Blue
                    if (b.getFighter2Blue() == null && b.getFighter1Red() == null) {
                        currentVerticeLayer.put(j, graph.insertVertex(parent, "ID:" + b.getId(), b, x, y, width, height, cellColourNoFighters));
                    } else if (b.getWinner() != null) {
                        currentVerticeLayer.put(j, graph.insertVertex(parent, "ID:" + b.getId(), b, x, y, width, height, cellColourWinnerFound));
                    } else {
                        currentVerticeLayer.put(j, graph.insertVertex(parent, "ID:" + b.getId(), b, x, y, width, height, cellColourNoWinnerFound));
                    }
                }

            }
            linkVerticesWithOneAnother();
            gatherAllCellsInOneCollection();
            //graph.insertEdge(parent, null, "Edge", v1, v2); //Seemingly used to make a line between 2 vertices.
        } finally {
            graph.getModel().endUpdate();
        }
    }

    private void linkVerticesWithOneAnother() {
        //Set edge styling.
        Map<String, Object> edgeStyleLower = new HashMap<String, Object>();
        edgeStyleLower.put(mxConstants.STYLE_ROUNDED, false);
        edgeStyleLower.put(mxConstants.STYLE_ORTHOGONAL, false);
        edgeStyleLower.put(mxConstants.STYLE_EDGE, "elbowEdgeStyle");
        edgeStyleLower.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CONNECTOR);
        edgeStyleLower.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_CLASSIC);
        edgeStyleLower.put(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_MIDDLE);
        edgeStyleLower.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_CENTER);
        edgeStyleLower.put(mxConstants.STYLE_STROKECOLOR, "#000000"); // default is #6482B9
        edgeStyleLower.put(mxConstants.STYLE_FONTCOLOR, "#446299");
        mxStylesheet edgeStyles = new mxStylesheet();
        edgeStyles.setDefaultEdgeStyle(edgeStyleLower);
        graph.setStylesheet(edgeStyles);
        graph.setKeepEdgesInBackground(true);

        //Create edges
        TreeMap<Integer, Object> secondLayer = verticesLayers.get(1);
        TreeMap<Integer, Object> initialLayer = verticesLayers.get(0);
        graph.insertEdge(parent, null, "", initialLayer.get(0), secondLayer.get(0));
        graph.insertEdge(parent, null, "", initialLayer.get(1), secondLayer.get(0));
    }

    private void gatherAllCellsInOneCollection() {
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

    public void updateWinnerFoundColour(String newColour) {
        cellColourWinnerFound = newColour;
        Battle b = battleLayers.get(0).get(0);
        mxCell cellToUpdate = allCells.get("ID:" + b.getId());
        updateTheCellColour(b, cellToUpdate);
        graph.getModel().setValue(cellToUpdate, b);
    }

    public void updateNoWinnerFoundColour(String newColour) {
        cellColourNoWinnerFound = newColour;
        Battle b = battleLayers.get(1).get(0);
        mxCell cellToUpdate = allCells.get("ID:" + b.getId());
        updateTheCellColour(b, cellToUpdate);
        graph.getModel().setValue(cellToUpdate, b);
    }

    public void updateNoFightersColour(String newColour) {
        cellColourNoFighters = newColour;
        Battle b = battleLayers.get(0).get(1);
        mxCell cellToUpdate = allCells.get("ID:" + b.getId());
        updateTheCellColour(b, cellToUpdate);
        graph.getModel().setValue(cellToUpdate, b);
    }

    private void updateTheCellColour(Battle b, mxCell cellToUpdate) {
        if (b.getFighter2Blue() == null && b.getFighter1Red() == null) {
            cellToUpdate.setStyle(cellColourNoFighters);
        } else if (b.getWinner() != null) {
            cellToUpdate.setStyle(cellColourWinnerFound);
        } else if (b.getWinner() == null) {
            cellToUpdate.setStyle(cellColourNoWinnerFound);
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

        pnlMainPanel = new javax.swing.JPanel();

        javax.swing.GroupLayout pnlMainPanelLayout = new javax.swing.GroupLayout(pnlMainPanel);
        pnlMainPanel.setLayout(pnlMainPanelLayout);
        pnlMainPanelLayout.setHorizontalGroup(
            pnlMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        pnlMainPanelLayout.setVerticalGroup(
            pnlMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlMainPanel;
    // End of variables declaration//GEN-END:variables
}
