package graph.model.interfaces;

import graph.model.Edge;
import graph.model.Node;

public interface GraphOwner {
    void onNodeRemoved(Node node);
    void onEdgeRemoved(Edge edge);
}
