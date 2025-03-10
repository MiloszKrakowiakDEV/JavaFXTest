package pl.gornik.javafx;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

public class Controller {

    @FXML
    private Button restart;

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
            grid.requestFocus();
        }
    }
    public void movePlayerDown(){
        if(GridPane.getRowIndex(player)==grid.getRowCount()-1){
            info.setText("Nie można iść w dół!");
        }else{
            player.toFront();
            GridPane.setRowIndex(player,GridPane.getRowIndex(player)+1);
        }
    }
    public void movePlayerRight(){
        if(GridPane.getColumnIndex(player)==grid.getColumnCount()-1){
            info.setText("Nie można iść w prawo!");
        }else{
            player.toFront();
            GridPane.setColumnIndex(player,GridPane.getColumnIndex(player)+1);
        }
    }
    public void movePlayerLeft(){
        if(GridPane.getColumnIndex(player)==0){
            info.setText("Nie można iść w lewo!");
        }else{
            player.toFront();
            GridPane.setColumnIndex(player,GridPane.getColumnIndex(player)-1);
        }
    }
    public void prepareGame(){
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
    public void endGame(){
        Platform.exit();
    }
    public void restartGame(){
        GridPane.setRowIndex(player,2);
        GridPane.setColumnIndex(player,3);
    }

}
