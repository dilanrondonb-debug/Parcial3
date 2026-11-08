module co.edu.uniquindio.poo.parcial3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.junit.jupiter.api;

    opens co.edu.uniquindio.poo.parcial3 to javafx.fxml;
    exports co.edu.uniquindio.poo.parcial3.model;
    exports co.edu.uniquindio.poo.parcial3.controller to javafx.fxml;
    exports co.edu.uniquindio.poo.parcial3.viewController to javafx.fxml;
    exports co.edu.uniquindio.poo.parcial3.app;

    opens co.edu.uniquindio.poo.parcial3.model to org.junit.platform.commons;
    opens co.edu.uniquindio.poo.parcial3.app to org.junit.platform.commons;
    opens co.edu.uniquindio.poo.parcial3.controller to org.junit.platform.commons;
    opens co.edu.uniquindio.poo.parcial3.viewController to org.junit.platform.commons;
}