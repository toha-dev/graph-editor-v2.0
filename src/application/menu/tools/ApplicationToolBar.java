package application.menu.tools;

import application.menu.tools.items.*;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class ApplicationToolBar extends ToolBar {
    public ApplicationToolBar() {
        super.getItems().addAll(
                new SelectionButton(),
                new DraggingButton(),
                new NodeCreationButton(),
                new EdgeCreationButton(),
                new CycleFinderButton()
        );
    }
}
