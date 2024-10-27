module com.example.task2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.chein.task2 to javafx.fxml;
    exports com.chein.task2;
}