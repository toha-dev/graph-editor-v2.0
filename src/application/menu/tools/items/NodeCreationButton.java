package application.menu.tools.items;

import application.ApplicationWindow;
import javafx.scene.control.Button;

public class NodeCreationButton extends Button {
    private static final String buttonText = "Create node";

    public NodeCreationButton() {
        super(buttonText);

        setOnAction(e -> {
            ApplicationWindow.getInstance()
                    .getEditor()
                    .getController()
                    .setNodeCreationOperation();
        });
    }
}
