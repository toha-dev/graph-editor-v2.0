package graph.model;

import graph.view.ComponentView;

public abstract class Component {
    private static int lastUniqueId = 0;

    protected final int id;

    private boolean selected = false;
    private boolean highlighted = false;

    protected ComponentView view;

    public Component() {
        id = lastUniqueId++;
    }

    public Component(int id) {
        this.id = id;
        lastUniqueId = Math.max(lastUniqueId, id) + 1;
    }

    public int getId() {
        return id;
    }

    public void select() {
        selected = true;
        view.onSelectionChanged();
    }

    public void unselect() {
        selected = false;
        view.onSelectionChanged();
    }

    public boolean isSelected() {
        return selected;
    }

    public void highlight() {
        if (selected)
            return;

        highlighted = true;
        view.onHighlightingChanged();
    }

    public void stopHighlighting() {
        if (selected)
            return;

        highlighted = false;
        view.onHighlightingChanged();
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public abstract ComponentView getView();

    public abstract void remove();
}
