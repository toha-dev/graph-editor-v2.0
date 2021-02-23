package graph.view;

import graph.model.Edge;
import graph.model.Node;
import graph.view.menu.EdgeContextMenu;
import graph.view.menu.NodeContextMenu;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class EdgeView extends ComponentView {
    private static final double weightOffsetY = -10;

    private final Edge edge;

    private final Line renderer = new Line();

    private final EdgeContextMenu edgeContextMenu;
    private final Text weight;

    public EdgeView(Edge edge) {
        this.edge = edge;
        this.weight = new Text(edge.getWeight());

        setWeightStyle();
        setRenderedStyle();

        onWeightChanged();
        onPositionUpdated();

        this.edgeContextMenu = new EdgeContextMenu(edge);
        setContextMenuControllers();
    }

    private void setRenderedStyle() {
        this.renderer.setStroke(normalColor);
        this.renderer.setStrokeWidth(edgeStrokeWidth);
    }

    private void setContextMenuControllers() {
        renderer.setOnContextMenuRequested(e -> {
            if (edgeContextMenu.isShowing())
                return;

            if (edge.isHighlighted())
                edge.stopHighlighting();

            var sourceColor = renderer.getStroke();

            renderer.setStroke(contextMenuTargetColor);
            edgeContextMenu.show(renderer, e.getScreenX(), e.getScreenY());

            edgeContextMenu.setOnHidden(event -> {
                renderer.setStroke(sourceColor);
            });
        });
    }

    private void setWeightStyle() {
        weight.setFill(textColor);
        weight.setFont(textFont);
    }

    @Override
    public Line getRenderer() {
        return renderer;
    }

    public Text getWeight() {
        return weight;
    }

    public void onPositionUpdated() {
        renderer.setStartX(edge.getStart().getX());
        renderer.setStartY(edge.getStart().getY());

        renderer.setEndX(edge.getEnd().getX());
        renderer.setEndY(edge.getEnd().getY());

        updateWeightPosition();
    }

    @Override
    public void onSelectionChanged() {
        if (edge.isSelected()) {
            renderer.setStroke(selectedColor);
        } else {
            renderer.setStroke(normalColor);
        }
    }

    @Override
    public void onHighlightingChanged() {
        if (edgeContextMenu.isShowing())
            return;

        if (edge.isHighlighted()) {
            renderer.setStroke(highlightedColor);
        } else {
            renderer.setStroke(normalColor);
        }
    }

    public void onWeightChanged() {
        weight.setText(edge.getWeight());

        updateWeightPosition();
    }

    public void updateWeightPosition() {
        double centerX = (edge.getStart().getX() + edge.getEnd().getX()) / 2;
        double centerY = (edge.getStart().getY() + edge.getEnd().getY()) / 2;

        weight.setX(centerX - weight.getLayoutBounds().getWidth() / 2);
        weight.setY(centerY + weightOffsetY);
    }
}
