package editor.operations;

import graph.model.Component;
import graph.model.Edge;
import graph.model.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public abstract class EditorOperation {
    public void onSelected() { }
    public void onUnselected() { }

    public void onMouseClicked(MouseEvent e, Component component) { }

    public void onMousePressed(MouseEvent e, Node node) { }
    public void onMouseClicked(MouseEvent e, Node node) { }
    public void onMouseDragged(MouseEvent e, Node node) { }

    public void onMouseClicked(MouseEvent e, Edge edge) { }

    public void onCanvasMouseClicked(MouseEvent e) { }
    public void onCanvasMouseDragged(MouseEvent e) { }
    public void onCanvasMousePressed(MouseEvent e) { }

    protected boolean isLeftMouseButton(MouseEvent e) {
        return e.getButton() == MouseButton.PRIMARY;
    }
}
