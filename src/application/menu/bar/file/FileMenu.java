package application.menu.bar.file;

import application.menu.bar.file.items.OpenMenuItem;
import application.menu.bar.file.items.SaveAsMenuItem;
import application.menu.bar.file.items.SaveMenuItem;
import javafx.scene.control.Menu;

public class FileMenu extends Menu {
    private static final String menuText = "File";

    public FileMenu() {
        super(menuText);

        getItems().addAll(
                new OpenMenuItem(),
                new SaveAsMenuItem()
        );
    }
}
