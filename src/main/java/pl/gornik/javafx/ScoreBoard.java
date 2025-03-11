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
import java.util.Comparator;
import java.util.List;

public class ScoreBoard {
    @FXML
    VBox leaderboard;

    @FXML
    protected void initialize(){
        List<String[]> scores = App.scores;
        for (int i = 0; i < 10; i++) {
            StackPane pane = new StackPane();
            pane.setPrefHeight(35);
            Label label = new Label();
            label.setStyle("-fx-text-fill: white;-fx-font-size: 200%");
            scores.sort(new Comparator<String[]>() {
                @Override
                public int compare(String[] o1, String[] o2) {
                    return Integer.compare(Integer.parseInt(o2[1]), Integer.parseInt(o1[1]));
                }
            });

            if(i<scores.size()){
                label.setText(String.format("%3d. %s - %spkt",i+1,scores.get(i)[0],scores.get(i)[1]));
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
