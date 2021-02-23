package graph.view;

import graph.model.Node;
import graph.view.menu.NodeContextMenu;
import javafx.event.ActionEvent;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Optional;

public class NodeView extends ComponentView {
    private static final Color innerColor = Color.WHITE;
    private static final double identifierOffset = 15;

    private final Node node;

    private final Circle renderer = new Circle();

    private final NodeContextMenu nodeContextMenu;
    private final Text identifier;

    public NodeView(Node node) {
        this.node = node;

        this.renderer.setRadius(10);
        setRendererStyle();
        setNormalRenderer();

        this.nodeContextMenu = new NodeContextMenu(node);
        setContextMenuControllers();

        this.identifier = new Text(node.getIdentifier());
        setIdentifierStyle();
        onPositionUpdated();
    }

    private void setRendererStyle() {
        renderer.setStrokeWidth(nodeStrokeWidth);
    }

    private void setNormalRenderer() {
        renderer.setFill(innerColor);
        renderer.setStroke(normalColor);
    }

    private void setContextMenuControllers() {
        renderer.setOnContextMenuRequested(e -> {
            if (nodeContextMenu.isShowing())
                return;

            if (node.isHighlighted()) {
                node.stopHighlighting();
            }

            var sourceColor = renderer.getStroke();

            renderer.setStroke(contextMenuTargetColor);
            nodeContextMenu.show(renderer, e.getScreenX(), e.getScreenY());

            nodeContextMenu.setOnHidden(event -> {
                renderer.setStroke(sourceColor);
            });
        });
    }

    private void setIdentifierStyle() {
        identifier.setFill(textColor);
        identifier.setFont(textFont);
    }

    @Override
    public Circle getRenderer() {
        return renderer;
    }

    public Text getIdentifier() {
        return identifier;
    }

    public void onPositionUpdated() {
        renderer.setCenterX(node.getX());
        renderer.setCenterY(node.getY());

        updateIdentifierPosition();
    }

    private void updateIdentifierPosition() {
        identifier.setX(node.getX() + identifierOffset);
        identifier.setY(node.getY() + identifierOffset);
    }

    @Override
    public void onSelectionChanged() {
        if (node.isSelected()) {
            renderer.setStroke(selectedColor);
        } else {
            renderer.setStroke(normalColor);
        }
    }

    @Override
    public void onHighlightingChanged() {
        if (nodeContextMenu.isShowing())
            return;

        if (node.isHighlighted()) {
            renderer.setStroke(highlightedColor);
        } else {
            renderer.setStroke(normalColor);
        }
    }

    public void onIdentifierChanged() {
        identifier.setText(node.getIdentifier());
        System.out.println("On identifier changed");
    }
}
