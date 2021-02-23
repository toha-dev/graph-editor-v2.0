package graph.model;

import graph.model.interfaces.GraphOwner;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private final List<Node> nodes = new ArrayList<>();
    private final List<Edge> edges = new ArrayList<>();

    private final GraphOwner graphOwner;

    public Graph(GraphOwner graphOwner) {
        this.graphOwner = graphOwner;
    }

    public void add(Edge edge) {
        edges.add(edge);

        edge.getStart().getOutgoingEdges().add(edge);
        edge.getEnd().getIncomingEdges().add(edge);
    }

    public void add(Node node) {
        nodes.add(node);
    }

    public void remove(Edge edge) {
        if (edges.contains(edge)) {
            edges.remove(edge);

            edge.getEnd().getIncomingEdges().remove(edge);
            edge.getStart().getOutgoingEdges().remove(edge);

            graphOwner.onEdgeRemoved(edge);
            System.out.println("On edge removed (" + edges.size() + ")");
        }
    }

    public void remove(Node node) {
        if (nodes.contains(node)) {
            nodes.remove(node);

            while (!node.getOutgoingEdges().isEmpty())
                remove(node.getOutgoingEdges().get(0));

            while (!node.getIncomingEdges().isEmpty())
                remove(node.getIncomingEdges().get(0));

            graphOwner.onNodeRemoved(node);
            System.out.println("On node removed (" + nodes.size() + ")");
        }
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void clear() {
        while (!nodes.isEmpty())
            remove(nodes.get(0));
    }
}
