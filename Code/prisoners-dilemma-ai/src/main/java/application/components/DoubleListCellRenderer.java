package application.components;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * <p>
 *     List renderer that formats double values displayed in cells.
 * </p>
 */
public class DoubleListCellRenderer extends DefaultListCellRenderer {
    private final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        String formattedValue;
        if (value instanceof Double) {
            formattedValue = decimalFormat.format(value);
        }
        else {
            formattedValue = value.toString();
        }

        JLabel cell = (JLabel) super.getListCellRendererComponent(list, formattedValue, index, false, false);
        cell.setHorizontalAlignment(SwingConstants.CENTER);
        return cell;
    }
}
