package graph.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;

public abstract class ComponentView {
    protected static final Color normalColor =
            new Color(0.663, 0.545, 0.596, 1);

    protected static final Color selectedColor = Color.GREEN;
    protected static final Color contextMenuTargetColor = Color.RED;
    protected static final Color highlightedColor = Color.ORANGE;

    protected static final Color textColor = Color.WHITE;
    protected static final Font textFont = new Font("Verdana", 18);

    protected static final double edgeStrokeWidth = 7;
    protected static final double nodeStrokeWidth = 5;

    public abstract Shape getRenderer();

    public abstract void onSelectionChanged();
    public abstract void onHighlightingChanged();
    public abstract void onPositionUpdated();
}
