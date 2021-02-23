package graph.view.menu;

import graph.model.Node;
import graph.view.NodeView;
import javafx.event.ActionEvent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class NodeContextMenu extends ContextMenu {
    private final Node node;

    public NodeContextMenu(Node node) {
        this.node = node;

        MenuItem setIdentifierItem = new MenuItem("Set identifier");
        setIdentifierItem.setOnAction(this::onSetIdentifierAction);

        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.setOnAction(this::onDeleteAction);

        getItems().addAll(
                setIdentifierItem,
                deleteItem
        );
    }

    private void onSetIdentifierAction(ActionEvent e) {
        TextInputDialog dialog = new TextInputDialog(node.getIdentifier());
        dialog.setTitle("Change node identifier");
        dialog.setHeaderText("Enter identifier below");
        dialog.setContentText("Identifier: ");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(s -> node.setIdentifier(s));
    }

    private void onDeleteAction(ActionEvent e) {
        node.remove();
    }
}
