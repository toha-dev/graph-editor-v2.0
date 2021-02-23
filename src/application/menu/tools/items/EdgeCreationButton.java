package application.menu.tools.items;

import application.ApplicationWindow;
import javafx.scene.control.Button;

public class EdgeCreationButton extends Button {
    private static final String buttonText = "Create edge";

    public EdgeCreationButton() {
        super(buttonText);

        setOnAction(e -> {
            ApplicationWindow.getInstance()
                    .getEditor()
                    .getController()
                    .setEdgeCreationOperation();
        });
    }
}
