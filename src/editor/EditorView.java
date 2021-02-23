package editor;

import graph.model.Edge;
import graph.model.Node;
import javafx.scene.layout.Pane;

public class EditorView {
    private final Editor editor;

    private final CanvasProxy canvasProxy = new CanvasProxy();

    public EditorView(Editor editor) {
        this.editor = editor;
    }

    public void onNodeCreated(Node node) {
        canvasProxy.append(node);
    }

    public void onNodeRemoved(Node node) {
        canvasProxy.remove(node);
    }

    public void onEdgeCreated(Edge edge) {
        canvasProxy.append(edge);
    }

    public void onEdgeRemoved(Edge edge) {
        canvasProxy.remove(edge);
    }

    public CanvasProxy getCanvasProxy() {
        return canvasProxy;
    }
}
