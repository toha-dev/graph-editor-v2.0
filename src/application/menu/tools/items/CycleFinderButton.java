package application.menu.tools.items;

import application.ApplicationWindow;
import javafx.scene.control.Button;


public class CycleFinderButton extends Button {
    private static final String buttonText = "Find cycle";

    public CycleFinderButton() {
        super(buttonText);

        setOnAction(e -> {
            ApplicationWindow.getInstance()
                    .getEditor()
                    .getController()
                    .setGraphCycleFinderOperation();
        });
    }
}
