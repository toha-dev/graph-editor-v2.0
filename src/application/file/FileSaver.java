package application.file;

import graph.model.Edge;
import graph.model.Graph;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import graph.model.Node;
import org.json.*;

import static application.file.JSONKey.*;

public class FileSaver {
    private final Graph graph;

    public FileSaver(Graph graph) {
        this.graph = graph;
    }

    public void save(File file) {
        try {
            FileOutputStream fileOutputStream =
                    new FileOutputStream(file);

            fileOutputStream.flush();

            JSONObject result = new JSONObject();

            List<JSONObject> nodesArray = packNodesToJSONList(graph.getNodes());
            List<JSONObject> edgesArray = packEdgesToJSONList(graph.getEdges());

            result.put(NODES_ARRAY_KEY, nodesArray);
            result.put(EDGES_ARRAY_KEY, edgesArray);

            try (PrintStream out = new PrintStream(fileOutputStream)) {
                out.print(result);
            }

            System.out.println("Correctly saved to file");
        } catch (Exception e) {
            System.out.println("ERROR SAVING TO FILE: " + e.getMessage());
        }
    }

    private ArrayList<JSONObject> packNodesToJSONList(List<Node> nodes) {
        ArrayList<JSONObject> result = new ArrayList<>();

        for (var node : nodes) {
            result.add(getJSON(node));
        }

        return result;
    }

    private ArrayList<JSONObject> packEdgesToJSONList(List<Edge> edges) {
        ArrayList<JSONObject> result = new ArrayList<>();

        for (var edge : edges) {
            result.add(getJSON(edge));
        }

        return result;
    }

    private JSONObject getJSON(Node node) {
        JSONObject result = new JSONObject();

        result.put(NODE_ID_KEY, node.getId());
        result.put(NODE_IDENTIFIER_KEY, node.getIdentifier());
        result.put(NODE_POSITION_X_KEY, node.getX());
        result.put(NODE_POSITION_Y_KEY, node.getY());

        return result;
    }

    private JSONObject getJSON(Edge edge) {
        JSONObject result = new JSONObject();

        result.put(EDGE_START_ID_KEY, edge.getStart().getId());
        result.put(EDGE_END_ID_KEY, edge.getEnd().getId());
        result.put(EDGE_WEIGHT_KEY, edge.getWeight());

        return result;
    }
}
