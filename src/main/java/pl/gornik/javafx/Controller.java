package pl.gornik.javafx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.IOException;
import java.util.Random;
import java.util.Stack;
import java.util.zip.GZIPInputStream;

public class Controller {
    private int score;
    @FXML
    private Button restart;

    @FXML
    private StackPane point;

    @FXML
    private Label info;

    @FXML
    private TextField nickname;

    @FXML
    private Button submit;

    @FXML
    private VBox vbox1;

    @FXML
    private Label scoreDisplay;

    private Timeline gameTimer;

    @FXML
    private Button space;

    @FXML
    private Label timerDisplay;

    @FXML
    private TextField userField;

    @FXML
    private Button exit;

    @FXML
    private GridPane grid;

    @FXML
    private StackPane player;

    private int timer;

    public void movePlayerUp(){
        if(GridPane.getRowIndex(player)==0){
            info.setText("Nie można iść w górę!");
        }else{
            player.toFront();
            GridPane.setRowIndex(player,GridPane.getRowIndex(player)-1);
            if(checkIfTouchingPoint(player,point)){
                randomizePointPlacement(point, grid.getColumnCount(), grid.getRowCount());
                score++;
                updateScoreDisplay(scoreDisplay,score);

            }
        }
    }

    public void movePlayerDown(){
        if(GridPane.getRowIndex(player)==grid.getRowCount()-1){
            info.setText("Nie można iść w dół!");
        }else{
            player.toFront();
            GridPane.setRowIndex(player,GridPane.getRowIndex(player)+1);
            if(checkIfTouchingPoint(player,point)){
                randomizePointPlacement(point, grid.getColumnCount(), grid.getRowCount());
                score++;
                updateScoreDisplay(scoreDisplay,score);

            }
        }
    }
    public void movePlayerRight(){
        if(GridPane.getColumnIndex(player)==grid.getColumnCount()-1){
            info.setText("Nie można iść w prawo!");
        }else{
            player.toFront();
            GridPane.setColumnIndex(player,GridPane.getColumnIndex(player)+1);
            if(checkIfTouchingPoint(player,point)){
                randomizePointPlacement(point, grid.getColumnCount(), grid.getRowCount());
                score++;
                updateScoreDisplay(scoreDisplay,score);

            }
        }
    }
    public void movePlayerLeft(){
        if(GridPane.getColumnIndex(player)==0){
            info.setText("Nie można iść w lewo!");
        }else{
            player.toFront();
            GridPane.setColumnIndex(player,GridPane.getColumnIndex(player)-1);
            if(checkIfTouchingPoint(player,point)){
                randomizePointPlacement(point, grid.getColumnCount(), grid.getRowCount());
                score++;
                updateScoreDisplay(scoreDisplay,score);

            }
        }
    }
    public void prepareGame(){
        nickname.getParent().setVisible(false);
        nickname.setText("");
        randomizePointPlacement(player, grid.getColumnCount(), grid.getRowCount());
        score=0;
        timer=15;
        player.getScene().setOnKeyPressed(e -> {
            switch (e.getCode()){
                case W -> this.movePlayerUp();
                case S -> this.movePlayerDown();
                case A -> this.movePlayerLeft();
                case D -> this.movePlayerRight();
                case TAB -> Platform.exit();
            }
        });
        updateScoreDisplay(scoreDisplay,score);
        timerDisplay.setText("Pozostały czas: "+timer+" sekund.");
        gameTimer = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                timerDisplay.setText("Pozostały czas: "+timer+" sekund.");
                                if(timer==0){
                                    scoreDisplay.getScene().setOnKeyPressed(null);
                                    gameTimer.stop();
                                    Platform.runLater(()->
                                    {
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setTitle("Koniec");
                                        alert.setHeaderText("Uzyskany wynik to "+score);
                                        alert.setContentText("Skończył ci się czas!\nPo zamknięciu okienka podejmij decyzję.");
                                        alert.showAndWait();
                                        submit.setVisible(true);
                                        nickname.getParent().setVisible(true);
                                    });
                                }
                                timer--;
                            }
                        }));
        gameTimer.setCycleCount(Timeline.INDEFINITE);
        gameTimer.play();

        Random random = new Random();
        int pointColPos = random.nextInt(0,grid.getColumnCount());
        int pointRowPos = random.nextInt(0, grid.getRowCount());
        GridPane.setColumnIndex(point,pointColPos);
        GridPane.setRowIndex(point,pointRowPos);
        point.setVisible(true);

        for (int i = 0; i < grid.getRowCount(); i++) {
            for (int j = 0; j < grid.getColumnCount(); j++) {
                Pane pane = new Pane();
                Label label = new Label();
                label.setText(String.valueOf(j+1+grid.getColumnCount()*i));
                label.setStyle("-fx-font-size:20px;");
                label.setPadding(new Insets(0,0,0,2));
                pane.getChildren().add(label);
                pane.setStyle("-fx-border-color: black;\n" +
                        "    -fx-border-width: 2px;\n" +
                        "    -fx-padding: 10px;-fx-background-color: red;");
                grid.add(pane,j,i);

            }
        }
        point.toFront();
        player.toFront();

    }
    public void moveToSpace(){
        String input = userField.getText();
        if(input.matches("^[0-9]+$")){
            int inputInt = Integer.valueOf(input);
            if(inputInt <= grid.getColumnCount()* grid.getRowCount()){
                int col = (inputInt-1)%grid.getColumnCount();
                int row = (inputInt-1-col)% grid.getRowCount();
                GridPane.setColumnIndex(player,col);
                GridPane.setRowIndex(player,row);
                info.setText("Poprawnie przeniesiono na pole "+input);
                player.toFront();
                if(checkIfTouchingPoint(player,point)){
                    randomizePointPlacement(point, grid.getColumnCount(), grid.getRowCount());
                    score++;
                    updateScoreDisplay(scoreDisplay,score);

                }

            }else{
                info.setText("Podana liczba nie mieści się w siatce!");
            }
        }else{
            info.setText("Nie podano liczby!");
        }

    }
    public void endGame() throws IOException {
        gameTimer.stop();
        gameTimer=null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
        Stage stage = (Stage)player.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.centerOnScreen();
    }



    public void restartGame(){
        submit.setVisible(false);
        submit.setVisible(false);
        gameTimer.stop();
        gameTimer=null;
        prepareGame();
        scoreDisplay.setText("Wynik: "+score);
    }
    public static boolean checkIfTouchingPoint(StackPane player, StackPane point){
        return GridPane.getColumnIndex(player)==GridPane.getColumnIndex(point) && GridPane.getRowIndex(point)==GridPane.getRowIndex(player);
    }
    public static void randomizePointPlacement(StackPane point, int colSize, int rowSize){
        Random random = new Random();
        GridPane.setRowIndex(point,random.nextInt(0,rowSize));
        GridPane.setColumnIndex(point, random.nextInt(0, colSize));

    }

    public static void updateScoreDisplay(Label scoreDisplay, int score){
        scoreDisplay.setText("Wynik: "+score);
    }

    public void submitScore(){
        if(score>0){
            if(nickname.getText().matches("^\\s*$")){
                Platform.runLater(()->
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Błąd");
                    alert.setContentText("Nie podano nazwy użytkownika.");
                    alert.setHeaderText("Podaj nazwę użytkownika w polu obok");
                    alert.showAndWait();
                });
            }else{
                App.scores.add(new String[]{nickname.getText(),String.valueOf(score)});
                submit.setVisible(false);
                nickname.getParent().setVisible(false);
            }
        }else{
            Platform.runLater(()->
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Błąd");
                alert.setContentText("Nie można dodać wyniku 0.");
                alert.setHeaderText("Nie dodano do tabeli wyników.");
                alert.showAndWait();
                submit.setVisible(false);
            });
        }
    }

}
