package pl.gornik.javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view.fxml"));
        Parent loader = fxmlLoader.load();
        Scene scene = new Scene(loader);
        stage.setTitle("Poruszanie kozy");
        Controller controller = fxmlLoader.getController();
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()){
                case W -> controller.movePlayerUp();
                case S -> controller.movePlayerDown();
                case A -> controller.movePlayerLeft();
                case D -> controller.movePlayerRight();
                case TAB -> Platform.exit();
            }
        });
        controller.prepareGame();
        stage.setFullScreenExitHint("");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}