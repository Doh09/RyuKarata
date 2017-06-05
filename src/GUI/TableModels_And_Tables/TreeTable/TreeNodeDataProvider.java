/*
 * This class represents the Tree/combination part of the TreeTable.
 */
package GUI.TableModels_And_Tables.TreeTable;

import BE.Tournaments.Abstract_TournamentPart;
import org.netbeans.swing.outline.RenderDataProvider;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class TreeNodeDataProvider implements RenderDataProvider {

    public TreeNodeDataProvider() {
    }

    @Override
    public java.awt.Color getBackground(Object o) {
        return null;
    }

    @Override
    public String getDisplayName(Object o) {
        Abstract_TournamentPart atp = (Abstract_TournamentPart) o;
        return atp.getName();
    }

    @Override
    public java.awt.Color getForeground(Object o) {
        //No colors in the TreeTable.
        return null;
    }

    @Override
    public javax.swing.Icon getIcon(Object o) {
        return null;
    }

    @Override
    public String getTooltipText(Object o) {
        Abstract_TournamentPart atp = (Abstract_TournamentPart) o;
        if (atp.getId() != -1) { //if not root tournament.
            return "Dobbeltklik for at inspicere/redigere";
        }
        return "Herunder kommer turneringer til at ligge når de tilføjes";
    }

    @Override
    public boolean isHtmlDisplayName(Object o) {
        return false;
    }
}
