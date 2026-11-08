package co.edu.uniquindio.poo.parcial3.viewController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class EmpleadoViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnEliminarEmpleado;

    @FXML
    private Button btnLimpiarEmpleado;

    @FXML
    private Button btnRegistrarEmpleado;

    @FXML
    private ComboBox<?> cmbTipoEmpleado;

    @FXML
    private TableColumn<?, ?> colCajaEmpleado;

    @FXML
    private TableColumn<?, ?> colCodigoEmpleado;

    @FXML
    private TableColumn<?, ?> colDocumentoEmpleado;

    @FXML
    private TableColumn<?, ?> colMinutosEmpleado;

    @FXML
    private TableColumn<?, ?> colNombreEmpleado;

    @FXML
    private TableColumn<?, ?> colTipoEmpleado;

    @FXML
    private Label lblCajaAsignada;

    @FXML
    private Label lblMensajeEmpleado;

    @FXML
    private TableView<?> tblEmpleados;

    @FXML
    private TextField txtCajaAsignada;

    @FXML
    private TextField txtCodigoEmpleado;

    @FXML
    private TextField txtDocumentoEmpleado;

    @FXML
    private TextField txtMinutosDisponibles;

    @FXML
    private TextField txtNombreEmpleado;

    @FXML
    void onEliminarEmpleado(ActionEvent event) {

    }

    @FXML
    void onLimpiarEmpleado(ActionEvent event) {

    }

    @FXML
    void onRegistrarEmpleado(ActionEvent event) {

    }

    @FXML
    void initialize() {


    }

}
