module a6.djorgensen.fxchat.fxchat {
    requires javafx.controls;
    requires javafx.fxml;


    opens a6.djorgensen.fxchat.fxchat to javafx.fxml;
    exports a6.djorgensen.fxchat.fxchat;
}