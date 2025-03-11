package pl.gornik.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.List;

public class ScoreBoard {
    @FXML
    VBox leaderboard;

    @FXML
    protected void initialize(){
        List<Integer> scores = App.scores;
        for (int i = 0; i < 10; i++) {
            StackPane pane = new StackPane();
            pane.setPrefHeight(35);
            Label label = new Label();
            label.setStyle("-fx-text-fill: white;-fx-font-size: 200%");
            if(i<scores.size()){
                label.setText(String.format("%3d. %d punkty",i+1,scores.get(i)));
            }else{
                label.setText(String.format("%3d. <puste>",i+1));
            }
            pane.getChildren().add(label);
            leaderboard.getChildren().add(pane);
        }
    }
    @FXML
    void exit(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
        Stage stage = (Stage)leaderboard.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.centerOnScreen();
    }
}
