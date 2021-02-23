package editor.operations.algorithm;

import editor.operations.EditorOperation;
import graph.model.Edge;
import graph.model.Graph;
import graph.model.Node;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GraphCycleFinderOperation extends EditorOperation {
    private final Graph graph;

    private final Set<Node> visitedNodes = new HashSet<>();
    private final Set<Edge> visitedEdges = new HashSet<>();

    private final List<Node> nodesInCycle = new ArrayList<>();

    private Node cycleFrom;
    private boolean needToAdd;

    public GraphCycleFinderOperation(Graph graph) {
        this.graph = graph;
    }

    @Override
    public void onSelected() {
        visitedNodes.clear();
        visitedEdges.clear();
        nodesInCycle.clear();

        cycleFrom = null;
        needToAdd = true;

        findCycle();
    }

    @Override
    public void onUnselected() {
        for (var edge : graph.getEdges()) {
            edge.unselect();
        }
    }

    private void findCycle() {
        boolean cycleFound = false;

        for (var node : graph.getNodes()) {
            if (visitedNodes.contains(node))
                continue;

            cycleFound = depthSearchForCycle(node);

            if (cycleFound) {
                break;
            }
        }

        if (cycleFound) {
            visualizeCycle();
        }

        System.out.println("Cycle found: " + cycleFound);
    }

    private void visualizeCycle() {
        nodesInCycle.add(nodesInCycle.get(0));

        for (int i = 0; i + 1 < nodesInCycle.size(); ++i) {
            Node start = nodesInCycle.get(i);
            Node end = nodesInCycle.get(i + 1);

            Edge edge = findEdgeBetween(start, end);
            if (edge == null)
                edge = findEdgeBetween(end, start);

            edge.select();
        }
    }

    private Edge findEdgeBetween(Node start, Node end) {
        for (var edge : start.getOutgoingEdges()) {
            if (edge.getStart() == start
            && edge.getEnd() == end) {
                return edge;
            }
        }

        return null;
    }

    private boolean depthSearchForCycle(Node node) {
        if (visitedNodes.contains(node)) {
            cycleFrom = node;

            nodesInCycle.add(node);
            return true;
        }

        visitedNodes.add(node);

        for (var edge : node.getOutgoingEdges()) {
            if (visitedEdges.contains(edge))
                continue;

            Node next = edge.getEnd();

            visitedEdges.add(edge);
            if (depthSearchForCycle(next)) {
                if (node == cycleFrom)
                    needToAdd = false;

                if (needToAdd)
                    nodesInCycle.add(node);

                return true;
            }
        }

        for (var edge : node.getIncomingEdges()) {
            if (visitedEdges.contains(edge))
                continue;

            Node next = edge.getStart();

            visitedEdges.add(edge);
            if (depthSearchForCycle(next)) {
                if (node == cycleFrom)
                    needToAdd = false;

                if (needToAdd)
                    nodesInCycle.add(node);

                return true;
            }
        }

        return false;
    }
}
