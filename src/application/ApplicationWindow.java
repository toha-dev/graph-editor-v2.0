package application;

import application.file.FileLoader;
import application.file.FileSaver;
import application.menu.bar.ApplicationMenuBar;
import application.menu.tools.ApplicationToolBar;
import editor.Editor;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ApplicationWindow extends Application {
    private static final double moveOffset = 50;
    private static ApplicationWindow instance;

    private static final String title = "Graph editor";
    private static final double standardWidth = 960;
    private static final double standardHeight = 600;

    private FileSaver fileSaver;
    private FileLoader fileLoader;

    private Stage stage;
    private Editor editor;

    public static ApplicationWindow getInstance() {
        return instance;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(title);
        this.stage = stage;

        ApplicationMenuBar mainMenuBar = new ApplicationMenuBar();
        ApplicationToolBar mainToolBar = new ApplicationToolBar();

        editor = new Editor();
        fileSaver = new FileSaver(editor.getGraph());
        fileLoader = new FileLoader(editor.getGraph());

        VBox root = new VBox(
                mainMenuBar,
                mainToolBar,
                editor.getView().getCanvasProxy().getCanvas()
        );

        Scene scene = new Scene(root, standardWidth, standardHeight);

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case DIGIT1 -> editor.getController().setSelectionOperation();
                case DIGIT2 -> editor.getController().setDraggingOperation();
                case DIGIT3 -> editor.getController().setNodeCreationOperation();
                case DIGIT4 -> editor.getController().setEdgeCreationOperation();
                case DIGIT5 -> editor.getController().setGraphCycleFinderOperation();

                case DELETE -> editor.getController().removeSelectedComponents();

                case A -> editor.getController().move(moveOffset, 0);
                case D -> editor.getController().move(-moveOffset, 0);
                case W -> editor.getController().move(0, moveOffset);
                case S -> editor.getController().move(0, -moveOffset);
            }
        });

        stage.setScene(scene);
        stage.show();

        instance = this;
    }

    public Editor getEditor() {
        return editor;
    }

    public Stage getStage() {
        return stage;
    }

    public FileSaver getFileSaver() {
        return fileSaver;
    }

    public FileLoader getFileLoader() {
        return fileLoader;
    }
}
