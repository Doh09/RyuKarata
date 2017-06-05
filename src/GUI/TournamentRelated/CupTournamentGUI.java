/*
 * This GUI class is used to display a CupTournament.
 * The class allows to click the battles displayed to open them up for a detailed viewing/editing.
 * The class also allows to save the tournament tree as a .PNG file and to edit the colors of the tournament displayed.
 */
package GUI.TournamentRelated;

import BE.Tournaments.Abstract_Tournament;
import BE.Tournaments.Battles.Battle;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxStylesheet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */

public class CupTournamentGUI extends Abstract_TournamentGUI {
    /**
     * Constructor, draws the tournament given to it using JGraph.
     *
     * @param parent
     * @param tournament
     */
    public CupTournamentGUI(java.awt.Frame parent, Abstract_Tournament tournament) {
        super(parent, tournament);
    }

    /**
     * Method that creats all the vertices according to the amount of battles in
     * the tournament, and puts them in their right location. To get the
     * tournament tree shape. The method also calls the private
     * "linkVerticesWithOneAnother()" method to draw lines between the connected
     * vertices.
     */
    @Override
    protected void drawTheTournament() {

        graph.getModel().beginUpdate();
        try {
            for (int i = 0; i < tournament.getBattleLayers().size(); i++) {
                TreeMap<Integer, Object> currentVerticeLayer = verticesLayers.get(i);
                double width = 110;
                double height = 70;
                double startFactor = (width / 2 + 30) * Math.pow(2, i) - (i * (width / tournament.getBattleLayers().size()));
                ArrayList<Battle> arrListBa = tournament.getBattleLayers().get(i); //Loop through the battles in each layer.
                for (int j = 0; j < arrListBa.size(); j++) {

                    //startfactor - (width/(TournamentHeight-i)
                    double spacing = 150 * Math.pow(2, i);
                    double y = startFactor + (spacing * j);
                    double x = 20 + i * (width + 110);

                    Battle b = arrListBa.get(j); //This is the Battle Object to put in instead of a random Object/String.
                    graph.insertVertex(graphParent, "Red:" + b.getId(), null, x, y, width + 10, 35, "fillColor=#ff0000"); //Red
                    graph.insertVertex(graphParent, "Blue:" + b.getId(), null, x, y + 35, width + 10, 35, "fillColor=#0000ff"); //Blue
                    if (b.getFighter2Blue() == null && b.getFighter1Red() == null) {
                        currentVerticeLayer.put(j, graph.insertVertex(graphParent, "ID:" + b.getId(), b, x, y, width, height, cellColorNoFighters));
                    } else if (b.getWinner() != null) {
                        currentVerticeLayer.put(j, graph.insertVertex(graphParent, "ID:" + b.getId(), b, x, y, width, height, cellColorWinnerFound));
                    } else {
                        currentVerticeLayer.put(j, graph.insertVertex(graphParent, "ID:" + b.getId(), b, x, y, width, height, cellColorNoWinnerFound));
                    }

                }
            }
            linkVerticesWithOneAnother();
            gatherAllCellsInOneCollection();
        } finally {
            graph.getModel().endUpdate();
        }
    }

    /**
     * Method used to create the edges/links between the vertices. This means
     * that it will draw lines between connected vertices, to indicate that they
     * are connected.
     */
    @Override
    protected void linkVerticesWithOneAnother() {
        //graph.insertEdge(parent, null, "Edge", v1, v2); //Seemingly used to make a line between 2 vertices.
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

        for (int i = 0; i < verticesLayers.size() - 1; i++) {
            if (i == 0) {//If initial layer
                TreeMap<Integer, Object> higherLayer = verticesLayers.get(1);
                TreeMap<Integer, Object> currentLayer = verticesLayers.get(0);
                for (int j = 0; j < higherLayer.size(); j++) //run through the higher layer.
                {
                    graph.insertEdge(graphParent, null, "", currentLayer.get(j * 2), higherLayer.get(j));
                    graph.insertEdge(graphParent, null, "", currentLayer.get(j * 2 + 1), higherLayer.get(j));
                }

            } else if (i == verticesLayers.size() - 1) { //If top layer
                TreeMap<Integer, Object> topLayer = verticesLayers.get(verticesLayers.size() - 1);
                TreeMap<Integer, Object> secondTopLayer = verticesLayers.get(verticesLayers.size() - 2);
                for (int j = 0; j < topLayer.size(); j++) //run through the higher layer.
                {
                    graph.insertEdge(graphParent, null, "", secondTopLayer.get(j * 2), topLayer.get(j));
                    graph.insertEdge(graphParent, null, "", secondTopLayer.get(j * 2 + 1), topLayer.get(j));
                }

            } else { //if not initial && not top layer.
                TreeMap<Integer, Object> higherLayer = verticesLayers.get(i + 1);
                TreeMap<Integer, Object> lowerLayer = verticesLayers.get(i);
                for (int j = 0; j < higherLayer.size(); j++) //run through the higher layer.
                {
                    graph.insertEdge(graphParent, null, "", lowerLayer.get(j * 2), higherLayer.get(j));
                    graph.insertEdge(graphParent, null, "", lowerLayer.get(j * 2 + 1), higherLayer.get(j));
                }
            }
        }
    }

}
