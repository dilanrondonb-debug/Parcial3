package co.edu.uniquindio.poo.parcial3.viewController;

import co.edu.uniquindio.poo.parcial3.controller.PedidoController;
import co.edu.uniquindio.poo.parcial3.model.EstadoPedido;
import co.edu.uniquindio.poo.parcial3.model.Pedido;
import co.edu.uniquindio.poo.parcial3.model.TipoPan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PedidoViewController {

    private final PedidoController pedidoController = new PedidoController();
    private final ObservableList<Pedido> listaPedidos = FXCollections.observableArrayList();

    @FXML
    private Button btnAsignarPedido;

    @FXML
    private Button btnCancelarPedido;

    @FXML
    private Button btnLimpiarPedido;

    @FXML
    private Button btnRegistrarPedido;

    @FXML
    private ComboBox<String> cmbTipoPan;

    @FXML
    private TableColumn<Pedido, Integer> colCantidadPedido;

    @FXML
    private TableColumn<Pedido, String> colClientePedido;

    @FXML
    private TableColumn<Pedido, String> colCodigoPedido;

    @FXML
    private TableColumn<Pedido, String> colEmpleadoPedido;

    @FXML
    private TableColumn<Pedido, EstadoPedido> colEstadoPedido;

    @FXML
    private TableColumn<Pedido, Integer> colMinutosPedido;

    @FXML
    private TableColumn<Pedido, TipoPan> colTipoPanPedido;

    @FXML
    private Label lblMensajePedido;

    @FXML
    private Label lblMinutosReservados;

    @FXML
    private Label lblPanaderoAsignado;

    @FXML
    private Label lblResultadoOperacion;

    @FXML
    private TableView<Pedido> tblPedidos;

    @FXML
    private TextField txtCantidadUnidades;

    @FXML
    private TextField txtCodigoGestion;

    @FXML
    private TextField txtCodigoPedido;

    @FXML
    private TextField txtNombreCliente;

    @FXML
    void onRegistrarPedido(ActionEvent event) {
        try {
            String codigo = txtCodigoPedido.getText().trim();
            String cliente = txtNombreCliente.getText().trim();
            String tipoSeleccionado = cmbTipoPan.getValue();
            if (tipoSeleccionado == null) {
                throw new IllegalArgumentException("Seleccione el tipo de pan");
            }
            int unidades = Integer.parseInt(txtCantidadUnidades.getText().trim());

            pedidoController.registrarPedido(codigo, cliente, TipoPan.valueOf(tipoSeleccionado), unidades);

            actualizarTabla();
            limpiarFormulario();
            mostrarMensaje(lblMensajePedido, "Pedido " + codigo + " registrado correctamente", "mensaje-ok");
        } catch (NumberFormatException e) {
            mostrarMensaje(lblMensajePedido, "La cantidad de unidades debe ser un número entero", "mensaje-error");
        } catch (IllegalArgumentException | IllegalStateException e) {
            mostrarMensaje(lblMensajePedido, e.getMessage(), "mensaje-error");
        }
    }

    @FXML
    void onAsignarPedido(ActionEvent event) {
        String codigo = txtCodigoGestion.getText().trim();
        if (codigo.isEmpty()) {
            mostrarMensaje(lblResultadoOperacion, "Escriba o seleccione el código del pedido", "mensaje-error");
            return;
        }
        try {
            Pedido pedido = pedidoController.asignarPedido(codigo);
            actualizarTabla();
            lblPanaderoAsignado.setText("Panadero asignado: " + pedidoController.obtenerDescripcionPanadero(pedido));
            lblMinutosReservados.setText("Minutos reservados: " + pedido.getMinutosReservados());
            mostrarMensaje(lblResultadoOperacion, "Pedido " + codigo + " asignado a producción", "mensaje-ok");
        } catch (IllegalArgumentException | IllegalStateException e) {
            mostrarMensaje(lblResultadoOperacion, e.getMessage(), "mensaje-error");
        }
    }

    @FXML
    void onCancelarPedido(ActionEvent event) {
        String codigo = txtCodigoGestion.getText().trim();
        if (codigo.isEmpty()) {
            mostrarMensaje(lblResultadoOperacion, "Escriba o seleccione el código del pedido", "mensaje-error");
            return;
        }
        try {
            int minutosDevueltos = pedidoController.cancelarPedido(codigo);
            actualizarTabla();
            lblPanaderoAsignado.setText("Panadero asignado: sin asignar");
            lblMinutosReservados.setText("Minutos reservados: 0");
            mostrarMensaje(lblResultadoOperacion,
                    "Pedido " + codigo + " cancelado. Minutos devueltos al panadero: " + minutosDevueltos, "mensaje-ok");
        } catch (IllegalArgumentException | IllegalStateException e) {
            mostrarMensaje(lblResultadoOperacion, e.getMessage(), "mensaje-error");
        }
    }

    @FXML
    void onLimpiarPedido(ActionEvent event) {
        limpiarFormulario();
        mostrarMensaje(lblMensajePedido, "", "mensaje-neutro");
    }

    @FXML
    void initialize() {
        colCodigoPedido.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colClientePedido.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
        colTipoPanPedido.setCellValueFactory(new PropertyValueFactory<>("tipoPan"));
        colCantidadPedido.setCellValueFactory(new PropertyValueFactory<>("unidades"));
        colEstadoPedido.setCellValueFactory(new PropertyValueFactory<>("estadoPedido"));
        colEmpleadoPedido.setCellValueFactory(new PropertyValueFactory<>("codigoEmpleadoAsignado"));
        colMinutosPedido.setCellValueFactory(new PropertyValueFactory<>("minutosReservados"));

        colEstadoPedido.setCellFactory(columna -> new TableCell<Pedido, EstadoPedido>() {
            @Override
            protected void updateItem(EstadoPedido estado, boolean vacio) {
                super.updateItem(estado, vacio);
                getStyleClass().removeAll("estado-pendiente", "estado-asignado", "estado-cancelado");
                if (vacio || estado == null) {
                    setText(null);
                } else {
                    setText(estado.name());
                    if (estado == EstadoPedido.PENDIENTE) {
                        getStyleClass().add("estado-pendiente");
                    } else if (estado == EstadoPedido.ASIGNADO) {
                        getStyleClass().add("estado-asignado");
                    } else {
                        getStyleClass().add("estado-cancelado");
                    }
                }
            }
        });

        tblPedidos.setItems(listaPedidos);
        tblPedidos.getSelectionModel().selectedItemProperty().addListener((observable, anterior, seleccionado) -> {
            if (seleccionado != null) {
                txtCodigoGestion.setText(seleccionado.getCodigo());
            }
        });

        actualizarTabla();
    }

    public void actualizarTabla() {
        listaPedidos.setAll(pedidoController.obtenerPedidos());
        tblPedidos.refresh();
    }

    private void limpiarFormulario() {
        txtCodigoPedido.clear();
        txtNombreCliente.clear();
        cmbTipoPan.setValue(null);
        txtCantidadUnidades.clear();
    }

    private void mostrarMensaje(Label etiqueta, String texto, String estilo) {
        etiqueta.getStyleClass().removeAll("mensaje-neutro", "mensaje-ok", "mensaje-error");
        etiqueta.getStyleClass().add(estilo);
        etiqueta.setText(texto);
    }
}
