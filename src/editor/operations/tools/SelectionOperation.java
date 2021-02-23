package editor.operations.tools;

import editor.operations.EditorOperation;
import graph.model.Component;
import graph.model.Edge;
import graph.model.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class SelectionOperation extends EditorOperation {
    private final List<Component> selectedComponents = new ArrayList<>();

    @Override
    public void onCanvasMouseClicked(MouseEvent e) {
        if (e.getButton() != MouseButton.PRIMARY)
            return;

        clear();
    }

    @Override
    public void onMouseClicked(MouseEvent e, Component component) {
        if (e.getButton() != MouseButton.PRIMARY)
            return;

        if (e.isControlDown()) {
            if (component.isSelected()) {
                remove(component);
            } else {
                add(component);
            }

            return;
        }

        if (component.isSelected()) {
            clear();
        } else {
            clear();
            add(component);
        }
    }

    private void add(Component component) {
        selectedComponents.add(component);
        component.select();
    }

    private void remove(Component component) {
        selectedComponents.remove(component);
        component.unselect();
    }

    public void clear() {
        for (var selected : selectedComponents) {
            selected.unselect();
        }

        selectedComponents.clear();
    }

    public List<Component> getSelectedComponents() {
        return selectedComponents;
    }
}
