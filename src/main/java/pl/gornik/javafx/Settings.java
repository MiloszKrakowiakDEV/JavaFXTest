package pl.gornik.javafx;


import javafx.scene.input.KeyCode;

public class Settings {
    public KeyCode up=KeyCode.W;
    public static KeyCode down=KeyCode.S;
    public static KeyCode left=KeyCode.A;
    public static KeyCode right=KeyCode.D;
    public static KeyCode restart=KeyCode.R;

    public static String setLength(int length, String text){
        return text.substring(0,length);
    }

}
