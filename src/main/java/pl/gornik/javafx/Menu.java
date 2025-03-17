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
    private Button score;

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
        controller.prepareGame();

        stage.setScene(scene);
            stage.centerOnScreen();

    }

    public void goSettings() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("settings.fxml"));
        Stage stage = (Stage) start.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.centerOnScreen();

    }

    @FXML
    void checkLeaderboard(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("scoreBoard.fxml"));
        Stage stage = (Stage)score.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.centerOnScreen();
    }

}
