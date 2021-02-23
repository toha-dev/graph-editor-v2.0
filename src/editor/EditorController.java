package editor;

import editor.operations.EditorOperation;
import editor.operations.algorithm.GraphCycleFinderOperation;
import editor.operations.creation.EdgeCreationOperation;
import editor.operations.creation.NodeCreationOperation;
import editor.operations.tools.DraggingOperation;
import editor.operations.tools.SelectionOperation;
import graph.model.Component;
import graph.model.Edge;
import graph.model.Node;

public class EditorController {
    private final Editor editor;

    private final SelectionOperation selectionOperation;
    private final DraggingOperation draggingOperation;
    private final NodeCreationOperation nodeCreationOperation;
    private final EdgeCreationOperation edgeCreationOperation;
    private final GraphCycleFinderOperation graphCycleFinderOperation;

    private EditorOperation editorActiveOperation;

    public EditorController(Editor editor) {
        this.editor = editor;

        this.selectionOperation = new SelectionOperation();
        this.draggingOperation = new DraggingOperation(selectionOperation);

        this.nodeCreationOperation = new NodeCreationOperation(editor);
        this.edgeCreationOperation = new EdgeCreationOperation(editor);

        this.graphCycleFinderOperation = new GraphCycleFinderOperation(editor.getGraph());

        setSelectionOperation();
        setCanvasControllers();
    }

    private void setCanvasControllers() {
        CanvasProxy proxy = editor.getView().getCanvasProxy();

        proxy.setOnMouseClicked(e ->
        { editorActiveOperation.onCanvasMouseClicked(e); });

        proxy.getCanvas().setOnMouseDragged(e ->
        { editorActiveOperation.onCanvasMouseDragged(e); });

        proxy.getCanvas().setOnMousePressed(e ->
        { editorActiveOperation.onCanvasMousePressed(e); });
    }

    public void setSelectionOperation() {
        changeActiveOperation(selectionOperation);

        System.out.println("You switched to SELECTION OPERATION");
    }

    public void setDraggingOperation() {
        changeActiveOperation(draggingOperation);

        System.out.println("You switched to DRAGGING OPERATION");
    }

    public void setNodeCreationOperation() {
        selectionOperation.clear();

        changeActiveOperation(nodeCreationOperation);

        System.out.println("You switched to NODE CREATION OPERATION");
    }

    public void setEdgeCreationOperation() {
        selectionOperation.clear();

        changeActiveOperation(edgeCreationOperation);

        System.out.println("You switched to EDGE CREATION OPERATION");
    }

    public void setGraphCycleFinderOperation() {
        selectionOperation.clear();

        changeActiveOperation(graphCycleFinderOperation);

        System.out.println("You switched to GRAPH CYCLE FINDER OPERATION");
    }

    private void changeActiveOperation(EditorOperation target) {
        if (editorActiveOperation != null)
            editorActiveOperation.onUnselected();

        editorActiveOperation = target;
        editorActiveOperation.onSelected();
    }

    public void removeSelectedComponents() {
        for (var component : selectionOperation.getSelectedComponents()) {
            component.remove();
        }
    }

    public void onNodeCreated(Node node) {
        node.getView().getRenderer().setOnMouseClicked(e -> {
            editor.getView().getCanvasProxy().onInnerComponentClicked();

            editorActiveOperation.onMouseClicked(e, (Component) node);
            editorActiveOperation.onMouseClicked(e, node);
        });

        node.getView().getRenderer().setOnMousePressed(e ->
        { editorActiveOperation.onMousePressed(e, node); });

        node.getView().getRenderer().setOnMouseDragged(e ->
        { editorActiveOperation.onMouseDragged(e, node); });

        node.getView().getRenderer().setOnMouseEntered(e ->
        { node.highlight(); });

        node.getView().getRenderer().setOnMouseExited(e ->
        { node.stopHighlighting(); });
    }

    public void onEdgeCreated(Edge edge) {
        edge.getView().getRenderer().setOnMouseClicked(e -> {
            editor.getView().getCanvasProxy().onInnerComponentClicked();

            editorActiveOperation.onMouseClicked(e, (Component) edge);
            editorActiveOperation.onMouseClicked(e, edge);
        });

        edge.getView().getRenderer().setOnMouseEntered(e ->
        { edge.highlight(); });

        edge.getView().getRenderer().setOnMouseExited(e ->
        { edge.stopHighlighting(); });
    }

    public void move(double offsetX, double offsetY) {
        for (var node : editor.getGraph().getNodes()) {
            node.setX(node.getX() + offsetX);
            node.setY(node.getY() + offsetY);
        }

        System.out.println("Move objects");
    }
}
