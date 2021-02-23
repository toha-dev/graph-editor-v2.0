package editor;

import editor.operations.creation.EdgeCreationOperation;
import editor.operations.EditorOperation;
import editor.operations.creation.NodeCreationOperation;
import editor.operations.tools.DraggingOperation;
import editor.operations.tools.SelectionOperation;
import graph.model.Component;
import graph.model.Edge;
import graph.model.Graph;
import graph.model.Node;
import graph.model.interfaces.GraphOwner;
import javafx.scene.layout.Pane;

public class Editor implements GraphOwner {
    private final EditorView editorView;
    private final EditorController editorController;

    private final Graph graph;

    public Editor() {
        this.graph = new Graph(this);

        this.editorView = new EditorView(this);
        this.editorController = new EditorController(this);
    }

    public void onNodeCreated(Node node) {
        graph.add(node);

        editorView.onNodeCreated(node);
        editorController.onNodeCreated(node);

        System.out.println("On node created");
    }

    public void onEdgeCreated(Edge edge) {
        graph.add(edge);

        editorView.onEdgeCreated(edge);
        editorController.onEdgeCreated(edge);

        System.out.println("On edge created");
    }

    public void onNodeRemoved(Node node) {
        editorView.onNodeRemoved(node);
    }

    public void onEdgeRemoved(Edge edge) {
        editorView.onEdgeRemoved(edge);
    }

    public EditorView getView() {
        return editorView;
    }

    public EditorController getController() {
        return editorController;
    }

    public Graph getGraph() {
        return graph;
    }
}
