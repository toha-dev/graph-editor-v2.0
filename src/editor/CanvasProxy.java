package editor;

import graph.model.Edge;
import graph.model.Node;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class CanvasProxy {
    private boolean innerComponentClicked = false;

    private final Pane canvas = new Pane();

    public CanvasProxy() {
        canvas.setPrefSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
        canvas.setStyle("-fx-background-color: #0f1123;"); // TODO
    }

    public Pane getCanvas() {
        return canvas;
    }

    public void onInnerComponentClicked() {
        innerComponentClicked = true;
    }

    public void append(Node node) {
        canvas.getChildren().addAll(
                node.getView().getRenderer(),
                node.getView().getIdentifier()
        );
    }

    public void append(Edge edge) {
        canvas.getChildren().addAll(
                edge.getView().getRenderer(),
                edge.getView().getWeight()
        );

        updateRenderPriority(edge.getStart());
        updateRenderPriority(edge.getEnd());
    }

    public void remove(Node node) {
        canvas.getChildren().removeAll(
                node.getView().getRenderer(),
                node.getView().getIdentifier()
        );
    }

    public void remove(Edge edge) {
        canvas.getChildren().removeAll(
                edge.getView().getRenderer(),
                edge.getView().getWeight()
        );
    }

    public void updateRenderPriority(Node node) {
        remove(node);
        append(node);
    }

    public void updateRenderPriority(Edge edge) {
        remove(edge);
        append(edge);
    }

    public void setOnMouseClicked(EventHandler<? super MouseEvent> eventHandler) {
        canvas.setOnMouseClicked(e -> {
            if (innerComponentClicked) {
                innerComponentClicked = false;
                return;
            }

            eventHandler.handle(e);
        });
    }
}
