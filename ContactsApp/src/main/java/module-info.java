module a3.djorgensen.contactsapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens a3.djorgensen.contactsapp to javafx.fxml;
    exports a3.djorgensen.contactsapp;
}