package graph.model.interfaces;

import graph.Vector2D;

public interface Draggable {
    void setX(double x);
    void setY(double y);
    double getX();
    double getY();

    Vector2D getPosition();
}
