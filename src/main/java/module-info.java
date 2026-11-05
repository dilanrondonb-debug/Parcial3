module co.edu.uniquindio.poo.parcial3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens co.edu.uniquindio.poo.parcial3 to javafx.fxml;
    exports co.edu.uniquindio.poo.parcial3.model;
    exports co.edu.uniquindio.poo.parcial3.controller to javafx.fxml;
    exports co.edu.uniquindio.poo.parcial3.viewController to javafx.fxml;
    exports co.edu.uniquindio.poo.parcial3.app;
}