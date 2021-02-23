package graph.view.menu;

import graph.model.Edge;
import graph.model.Node;
import javafx.event.ActionEvent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class EdgeContextMenu extends ContextMenu {
    private final Edge edge;

    public EdgeContextMenu(Edge edge) {
        this.edge = edge;

        MenuItem setWeightItem = new MenuItem("Set weight");
        setWeightItem.setOnAction(this::onSetIdentifierAction);

        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.setOnAction(this::onDeleteAction);

        getItems().addAll(
                setWeightItem,
                deleteItem
        );
    }

    private void onSetIdentifierAction(ActionEvent e) {
        TextInputDialog dialog = new TextInputDialog(edge.getWeight());
        dialog.setTitle("Change edge weight");
        dialog.setHeaderText("Enter weight below");
        dialog.setContentText("Weight: ");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(s -> edge.setWeight(s));
    }

    private void onDeleteAction(ActionEvent e) {
        edge.remove();
    }
}