package graph.model;

import editor.Editor;
import graph.view.EdgeView;

public class Edge extends Component {
    private final Node start;
    private final Node end;

    private final Graph graph;
    private String weight;

    public Edge(Node start, Node end, Graph graph) {
        this.graph = graph;
        this.start = start;
        this.end = end;
        this.weight = "";

        this.view = new EdgeView(this);
    }

    public Edge(Node start, Node end, Graph graph, String weight) {
        this.graph = graph;
        this.start = start;
        this.end = end;
        this.weight = weight;

        this.view = new EdgeView(this);
    }

    public void setWeight(String weight) {
        this.weight = weight;
        ((EdgeView) view).onWeightChanged();
    }

    public String getWeight() {
        return weight;
    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }

    @Override
    public EdgeView getView() {
        return (EdgeView) view;
    }

    @Override
    public void remove() {
        graph.remove(this);
    }
}
