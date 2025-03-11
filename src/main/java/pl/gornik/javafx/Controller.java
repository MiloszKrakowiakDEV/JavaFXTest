package pl.gornik.javafx;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;
import java.util.Stack;
import java.util.zip.GZIPInputStream;

public class Controller {
    private int score=0;
    @FXML
    private Button restart;

    @FXML
    private StackPane point;

    @FXML
    private Label info;

    @FXML
    private VBox vbox1;

    @FXML
    private Button space;

    @FXML
    private TextField userField;

    @FXML
    private Button exit;

    @FXML
    private GridPane grid;

    @FXML
    private StackPane player;

    public void movePlayerUp(){
        if(GridPane.getRowIndex(player)==0){
            info.setText("Nie można iść w górę!");
        }else{
            player.toFront();
            GridPane.setRowIndex(player,GridPane.getRowIndex(player)-1);
            if(checkIfTouchingPoint(player,point)){
                randomizePointPlacement(point, grid.getColumnCount(), grid.getRowCount());
                score++;
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
            }
        }
    }
    public void prepareGame(){
        System.out.println(player.getPrefWidth());
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
                System.out.println(row);
                System.out.println(col);
                GridPane.setColumnIndex(player,col);
                GridPane.setRowIndex(player,row);
                info.setText("Poprawnie przeniesiono na pole "+input);
                player.toFront();

            }else{
                info.setText("Podana liczba nie mieści się w siatce!");
            }
        }else{
            info.setText("Nie podano liczby!");
        }

    }
    public void endGame() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
        Stage stage = (Stage)player.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.centerOnScreen();
    }
    public void restartGame(){
        GridPane.setRowIndex(player,2);
        GridPane.setColumnIndex(player,3);
    }
    public static boolean checkIfTouchingPoint(StackPane player, StackPane point){
        return point.get,,,,,,,,,,,,,,,,,,,,,,,,,,,,,BoundsInParent().intersects(player.getBoundsInParent());
    }
    public static void randomizePointPlacement(StackPane point, int colSize, int rowSize){
        Random random = new Random();
        GridPane.setRowIndex(point,random.nextInt(0,rowSize));
        GridPane.setColumnIndex(point, random.nextInt(0, colSize));
    }


}
