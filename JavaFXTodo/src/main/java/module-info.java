module a8.djorgensen.javafxtodo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.net.http;
    requires org.json;

    opens a8.djorgensen.javafxtodo to javafx.fxml;
    exports a8.djorgensen.javafxtodo;
}