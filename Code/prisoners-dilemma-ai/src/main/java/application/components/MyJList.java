package application.components;

import javax.swing.*;
import javax.swing.border.Border;

/**
 * <p>
 *     Extension of JList that can specify font size, border and horizontal alignment in constructor.
 * </p>
 * @param <T> type stored in list model
 */
public class MyJList<T> extends JList<T> {
    public MyJList(ListModel<T> dataModel) {
        super(dataModel);
    }

    public MyJList(ListModel<T> dataModel, float fontSize) {
        this(dataModel, SwingConstants.LEADING, fontSize);
    }

    public MyJList(ListModel<T> dataModel, float fontSize, Border border) {
        this(dataModel, SwingConstants.LEADING, fontSize, border);
    }

    public MyJList(ListModel<T> dataModel, int horizontalAlignment, float fontSize) {
        super(dataModel);
        this.setAlignmentX(horizontalAlignment);
        ((DefaultListCellRenderer) this.getCellRenderer()).setHorizontalAlignment(horizontalAlignment);
        this.setFont(this.getFont().deriveFont(fontSize));
        this.setCellRenderer(new DoubleListCellRenderer());
    }

    public MyJList(ListModel<T> dataModel, int horizontalAlignment, float fontSize, Border border) {
        this(dataModel, horizontalAlignment, fontSize);
        this.setBorder(border);
    }
}
