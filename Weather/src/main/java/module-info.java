module a4.djorgensen.weather {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires java.net.http;


    opens a4.djorgensen.weather to javafx.fxml;
    exports a4.djorgensen.weather;
}