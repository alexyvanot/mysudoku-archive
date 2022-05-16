module com.intech.mysudoku {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.intech.mysudoku to javafx.fxml;
    exports com.intech.mysudoku;
}
