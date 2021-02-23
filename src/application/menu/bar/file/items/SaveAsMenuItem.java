package application.menu.bar.file.items;

import application.ApplicationWindow;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

import java.io.File;

public class SaveAsMenuItem extends MenuItem {
    private static final String menuItemText = "Save as";

    public SaveAsMenuItem() {
        super(menuItemText);

        setOnAction(this::onAction);
    }

    private void onAction(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser
                .ExtensionFilter("GRAPH files (*.graph)", "*.graph");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(ApplicationWindow
                .getInstance().getStage());

        if (file != null) {
            ApplicationWindow.getInstance().getFileSaver().save(file);
        }
    }
}
