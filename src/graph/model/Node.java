package graph.model;

import graph.Vector2D;
import graph.model.interfaces.Draggable;
import graph.view.NodeView;

import java.util.ArrayList;
import java.util.List;

public class Node extends Component implements Draggable {
    private final List<Edge> incomingEdges = new ArrayList<>();
    private final List<Edge> outgoingEdges = new ArrayList<>();

    private final Vector2D position;

    private final Graph graph;
    private String identifier;

    public Node(Vector2D position, Graph graph) {
        this.graph = graph;
        this.position = position;
        this.identifier = "";

        this.view = new NodeView(this);
    }

    public Node(Vector2D position, Graph graph, String identifier, int id) {
        super(id);

        this.graph = graph;
        this.position = position;
        this.identifier = identifier;

        this.view = new NodeView(this);
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
        ((NodeView) view).onIdentifierChanged();
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<Edge> getIncomingEdges() {
        return incomingEdges;
    }

    public List<Edge> getOutgoingEdges() {
        return outgoingEdges;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        setX(position.getX());
        setY(position.getY());
    }

    public double getX() {
        return position.getX();
    }

    public double getY() {
        return position.getY();
    }

    public void setX(double x) {
        position.setX(x);
        sendViewOnPositionUpdated();
    }

    public void setY(double y) {
        position.setY(y);
        sendViewOnPositionUpdated();
    }

    private void sendViewOnPositionUpdated() {
        view.onPositionUpdated();

        for (var edge : outgoingEdges) {
            edge.getView().onPositionUpdated();
        }

        for (var edge : incomingEdges) {
            edge.getView().onPositionUpdated();
        }
    }

    public void setPosition(double x, double y) {
        setX(x);
        setY(y);
    }

    @Override
    public NodeView getView() {
        return (NodeView) view;
    }

    @Override
    public void remove() {
        graph.remove(this);
    }
}
