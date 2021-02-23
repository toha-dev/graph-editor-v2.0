package application.menu.tools.items;

import application.ApplicationWindow;
import javafx.application.Application;
import javafx.scene.control.Button;

public class SelectionButton extends Button {
    private static final String buttonText = "Selection tool";

    public SelectionButton() {
        super(buttonText);

        setOnAction(e -> {
            ApplicationWindow.getInstance()
                    .getEditor()
                    .getController()
                    .setSelectionOperation();
        });
    }
}
