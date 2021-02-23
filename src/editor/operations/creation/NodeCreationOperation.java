package editor.operations.creation;

import editor.Editor;
import editor.operations.EditorOperation;
import graph.Vector2D;
import graph.model.Component;
import graph.model.Node;
import javafx.scene.input.MouseEvent;

public class NodeCreationOperation extends EditorOperation {
    private final Editor editor;

    public NodeCreationOperation(Editor editor) {
        this.editor = editor;
    }

    @Override
    public void onCanvasMouseClicked(MouseEvent e) {
        if (!isLeftMouseButton(e))
            return;

        Node node = new Node(new Vector2D(
                e.getX(),
                e.getY()
        ), editor.getGraph());

        editor.onNodeCreated(node);
    }
}
