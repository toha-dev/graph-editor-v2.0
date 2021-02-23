package application.menu.tools.items;

import application.ApplicationWindow;
import javafx.scene.control.Button;

public class DraggingButton extends Button {
    private static final String buttonText = "Dragging tool";

    public DraggingButton() {
        super(buttonText);

        setOnAction(e -> {
            ApplicationWindow.getInstance()
                    .getEditor()
                    .getController()
                    .setDraggingOperation();
        });
    }
}
