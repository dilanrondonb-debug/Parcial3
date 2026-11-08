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

public class PedidoViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAsignarPedido;

    @FXML
    private Button btnCancelarPedido;

    @FXML
    private Button btnLimpiarPedido;

    @FXML
    private Button btnRegistrarPedido;

    @FXML
    private ComboBox<?> cmbTipoPan;

    @FXML
    private TableColumn<?, ?> colCantidadPedido;

    @FXML
    private TableColumn<?, ?> colClientePedido;

    @FXML
    private TableColumn<?, ?> colCodigoPedido;

    @FXML
    private TableColumn<?, ?> colEmpleadoPedido;

    @FXML
    private TableColumn<?, ?> colEstadoPedido;

    @FXML
    private TableColumn<?, ?> colMinutosPedido;

    @FXML
    private TableColumn<?, ?> colTipoPanPedido;

    @FXML
    private Label lblMensajePedido;

    @FXML
    private Label lblMinutosReservados;

    @FXML
    private Label lblPanaderoAsignado;

    @FXML
    private Label lblResultadoOperacion;

    @FXML
    private TableView<?> tblPedidos;

    @FXML
    private TextField txtCantidadUnidades;

    @FXML
    private TextField txtCodigoGestion;

    @FXML
    private TextField txtCodigoPedido;

    @FXML
    private TextField txtNombreCliente;

    @FXML
    void onAsignarPedido(ActionEvent event) {

    }

    @FXML
    void onCancelarPedido(ActionEvent event) {

    }

    @FXML
    void onLimpiarPedido(ActionEvent event) {

    }

    @FXML
    void onRegistrarPedido(ActionEvent event) {

    }

    @FXML
    void initialize() {


    }

}
