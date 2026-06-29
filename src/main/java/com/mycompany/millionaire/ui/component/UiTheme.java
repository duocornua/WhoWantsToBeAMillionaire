package com.mycompany.millionaire.ui.component;

import java.awt.Color;

/**
 * Shared colors used by the custom Swing components.
 */
public final class UiTheme {

    /**
     * Dark menu background color.
     */
    public static final Color MENU_BLUE = new Color(0, 0, 64);
    /**
     * Dark panel color for cards, controls, and tables.
     */
    public static final Color PANEL_BLUE = new Color(7, 18, 100);
    /**
     * Top color for answer and hex gradients.
     */
    public static final Color ANSWER_BLUE_TOP = new Color(43, 93, 190);
    /**
     * Bottom color for answer and hex gradients.
     */
    public static final Color ANSWER_BLUE_BOTTOM = new Color(12, 18, 116);
    /**
     * Main gold accent color.
     */
    public static final Color GOLD = new Color(245, 196, 52);
    /**
     * Brighter menu gold accent color.
     */
    public static final Color MENU_GOLD = new Color(255, 204, 0);
    /**
     * Shared white text color.
     */
    public static final Color WHITE = Color.WHITE;
    /**
     * Correct-answer feedback color.
     */
    public static final Color GREEN = new Color(43, 210, 89);
    /**
     * Wrong-answer and timer-warning color.
     */
    public static final Color RED = new Color(230, 55, 55);

    /**
     * Prevents creating theme instances.
     */
    private UiTheme() {
    }
}
