package pl.gornik.javafx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu {

    @FXML
    private Button start;

    @FXML
    void endGame(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void startGame(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view.fxml"));
        Stage stage = (Stage) start.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
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
        stage.setScene(scene);
            stage.centerOnScreen();

    }

}
