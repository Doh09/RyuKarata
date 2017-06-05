/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.TableModels_And_Tables;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class ColorTableCellRenderer extends DefaultTableCellRenderer {

    Color winnerFound;
    Color noWinnerFound;

    public ColorTableCellRenderer() {
    }

    public void setWinnerFoundColor(String hexcolor) {
        winnerFound = (convertHexToRGBColor(hexcolor));
    }

    public void setNoWinnerFoundColor(String hexcolor) {
        noWinnerFound = (convertHexToRGBColor(hexcolor));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        String status = (String) table.getModel().getValueAt(row, 2); //Check if winner found.
        if (status.equalsIgnoreCase("Ikke afgjort")) {
            setBackground(noWinnerFound);
        } else {
            setBackground(winnerFound);
        }
        return cellComponent;
    }

    private Color convertHexToRGBColor(String hexColor) {
        hexColor = hexColor.substring(hexColor.indexOf("=") + 1, hexColor.length());
        return new Color(
                Integer.valueOf(hexColor.substring(1, 3), 16),
                Integer.valueOf(hexColor.substring(3, 5), 16),
                Integer.valueOf(hexColor.substring(5, 7), 16));
    }
}
