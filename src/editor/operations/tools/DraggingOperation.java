package editor.operations.tools;

import editor.operations.EditorOperation;
import graph.Vector2D;
import graph.model.Node;
import graph.model.interfaces.Draggable;
import javafx.scene.input.MouseEvent;

import java.util.HashMap;
import java.util.Map;

public class DraggingOperation extends EditorOperation {
    private final Map<Draggable, Vector2D> draggingComponents = new HashMap<>();
    private final SelectionOperation selectionTool;

    private final Vector2D startedPosition = new Vector2D(0, 0);

    public DraggingOperation(SelectionOperation selectionTool) {
        this.selectionTool = selectionTool;
    }

    @Override
    public void onCanvasMouseDragged(MouseEvent e) {
        double offsetX = e.getX() - startedPosition.getX();
        double offsetY = e.getY() - startedPosition.getY();

        for (Map.Entry<Draggable, Vector2D>
                draggableEntry : draggingComponents.entrySet()) {

            Draggable draggable = draggableEntry.getKey();
            Vector2D position = draggableEntry.getValue();

            draggable.setX(position.getX() + offsetX);
            draggable.setY(position.getY() + offsetY);
        }
    }

    @Override
    public void onCanvasMousePressed(MouseEvent e) {
        draggingComponents.clear();
        startedPosition.setX(e.getX());
        startedPosition.setY(e.getY());

        for (var component : selectionTool.getSelectedComponents()) {
            if (component instanceof Draggable) {
                Draggable draggable = (Draggable) component;

                draggingComponents.put(draggable, new Vector2D(
                        draggable.getPosition()
                ));
            }
        }
    }
}
