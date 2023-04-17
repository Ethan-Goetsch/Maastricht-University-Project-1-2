module group9.project
{
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens group9.project to javafx.fxml;
    exports group9.project;
}