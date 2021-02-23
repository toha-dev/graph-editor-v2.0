package application.file;

import application.ApplicationWindow;
import graph.Vector2D;
import graph.model.Edge;
import graph.model.Graph;
import graph.model.Node;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static application.file.JSONKey.*;

public class FileLoader {
    private final Graph graph;

    public FileLoader(Graph graph) {
        this.graph = graph;
    }

    public Graph load(File file) {
        try {
            StringBuilder fileRawData = new StringBuilder((int) file.length());
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    fileRawData.append(scanner.nextLine());
                }
            }

            JSONObject fileJSON = new JSONObject(fileRawData.toString());

            graph.clear();

            var parsedNodes = parseNodesJSONToList(fileJSON);
            for (var node : parsedNodes)
                ApplicationWindow.getInstance().getEditor().onNodeCreated(node);

            var parsedEdges = parseEdgesJSONToList(fileJSON);
            for (var edge : parsedEdges)
                ApplicationWindow.getInstance().getEditor().onEdgeCreated(edge);

            System.out.println("Loaded from file successfully");

            return graph;
        } catch (Exception e) {
            System.out.println("ERROR LOADING FROM FILE: " + e.getMessage());
        }

        return null;
    }

    private List<Node> parseNodesJSONToList(JSONObject fileJSON) {
        List<Node> result = new ArrayList<>();

        var nodesJSON = fileJSON.getJSONArray(NODES_ARRAY_KEY);
        for (int i = 0; i < nodesJSON.length(); ++i) {
            JSONObject nodeJSON = (JSONObject) nodesJSON.get(i);

            result.add(parseNodeJSON(nodeJSON));
        }

        return result;
    }

    private List<Edge> parseEdgesJSONToList(JSONObject fileJSON) {
        List<Edge> result = new ArrayList<>();

        var edgesJSON = fileJSON.getJSONArray(EDGES_ARRAY_KEY);
        for (int i = 0; i < edgesJSON.length(); ++i) {
            JSONObject edgeJSON = (JSONObject) edgesJSON.get(i);

            result.add(parseEdgeJSON(edgeJSON));
        }

        return result;
    }

    private Node findNodeById(int id) {
        var nodes = graph.getNodes();
        for (var node : nodes) {
            if (node.getId() == id) {
                return node;
            }
        }

        return null;
    }

    private Node parseNodeJSON(JSONObject nodeJSON) {
        Vector2D position = new Vector2D(
                nodeJSON.getDouble(NODE_POSITION_X_KEY),
                nodeJSON.getDouble(NODE_POSITION_Y_KEY)
        );

        String identifier = nodeJSON.getString(NODE_IDENTIFIER_KEY);
        int id = nodeJSON.getInt(NODE_ID_KEY);

        Node result = new Node(position, graph, identifier, id);
        return result;
    }

    private Edge parseEdgeJSON(JSONObject edgeJSON) {
        int startId = edgeJSON.getInt(EDGE_START_ID_KEY);
        int endId = edgeJSON.getInt(EDGE_END_ID_KEY);
        String weight = edgeJSON.getString(EDGE_WEIGHT_KEY);

        Node start = findNodeById(startId);
        Node end = findNodeById(endId);

        Edge result = new Edge(start, end, graph, weight);
        return result;
    }
}
