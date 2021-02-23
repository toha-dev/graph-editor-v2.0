package application.menu.bar;

import application.menu.bar.file.FileMenu;
import javafx.scene.control.MenuBar;

public class ApplicationMenuBar extends MenuBar {
    public ApplicationMenuBar() {
        super.getMenus().add(
                new FileMenu()
        );
    }
}
