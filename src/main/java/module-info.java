module pl.gornik.javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens pl.gornik.javafx to javafx.fxml;
    exports pl.gornik.javafx;
}