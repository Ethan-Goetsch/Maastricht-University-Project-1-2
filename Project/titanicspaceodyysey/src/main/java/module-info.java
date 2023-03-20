module com.titanicspaceodyysey {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.titanicspaceodyysey to javafx.fxml;
    exports com.titanicspaceodyysey;
}
