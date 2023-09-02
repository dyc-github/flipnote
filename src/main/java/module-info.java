module com.dyc.flipnote {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.dyc.flipnote to javafx.fxml;
    exports com.dyc.flipnote;
    exports com.dyc.flipnote.components;
    opens com.dyc.flipnote.components to javafx.fxml;
    exports com.dyc.flipnote.models;
    opens com.dyc.flipnote.models to javafx.fxml;
}