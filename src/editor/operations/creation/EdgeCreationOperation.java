package editor.operations.creation;

import editor.Editor;
import editor.operations.EditorOperation;
import graph.model.Edge;
import graph.model.Node;
import javafx.scene.input.MouseEvent;

public class EdgeCreationOperation extends EditorOperation {
    private final Editor editor;

    private Node startNode;

    public EdgeCreationOperation(Editor editor) {
        this.editor = editor;
    }

    @Override
    public void onSelected() {
        startNode = null;
    }

    @Override
    public void onUnselected() {
        removeStartNode();
    }

    @Override
    public void onMouseClicked(MouseEvent e, Node node) {
        if (!isLeftMouseButton(e) || node == startNode)
            return;

        if (startNode == null) {
            setStartNode(node);
            return;
        }

        Edge edge = new Edge(startNode, node, editor.getGraph());
        editor.onEdgeCreated(edge);

        removeStartNode();
    }

    private void setStartNode(Node node) {
        node.select();
        startNode = node;
    }

    private void removeStartNode() {
        if (startNode != null) {
            startNode.unselect();
            startNode = null;
        }
    }
}
