package uk.ac.gla.util;

import javax.swing.*;
import java.awt.*;

/**
 * @author Shangzhen Wei
 * @version 1.1
 */

/*
 * Get the display size of the user's computer screen
 * The size of the rectangle returned is used as the size of the SearchView page
 */

public class ScreenDimensionUtil {

    public static Rectangle getBounds() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(new JFrame().getGraphicsConfiguration());

        Rectangle rectangle = new Rectangle(screenInsets.left, screenInsets.top,
                screenSize.width - screenInsets.left - screenInsets.right,
                screenSize.height - screenInsets.top - screenInsets.bottom);
        return rectangle;
    }
}
