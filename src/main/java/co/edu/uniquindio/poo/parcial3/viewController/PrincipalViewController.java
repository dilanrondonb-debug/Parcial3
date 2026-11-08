package co.edu.uniquindio.poo.parcial3.viewController;

import co.edu.uniquindio.poo.parcial3.app.App;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class PrincipalViewController {

    @FXML
    private Label lblTituloApp;

    @FXML
    private Label lblSubtituloApp;

    @FXML
    private TabPane tabPrincipal;

    @FXML
    private Tab tabPedidos;

    @FXML
    private Tab tabEmpleados;

    @FXML
    private PedidoViewController vistaPedidosController;

    @FXML
    private EmpleadoViewController vistaEmpleadosController;

    @FXML
    public void initialize() {
        lblTituloApp.setText(App.empresa.getNombre());

        tabPedidos.setOnSelectionChanged(evento -> {
            if (tabPedidos.isSelected()) {
                vistaPedidosController.actualizarTabla();
            }
        });

        tabEmpleados.setOnSelectionChanged(evento -> {
            if (tabEmpleados.isSelected()) {
                vistaEmpleadosController.actualizarTabla();
            }
        });
    }
}