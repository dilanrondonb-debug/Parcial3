package co.edu.uniquindio.poo.parcial3.viewController;

import co.edu.uniquindio.poo.parcial3.controller.EmpleadoController;
import co.edu.uniquindio.poo.parcial3.model.Empleado;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class EmpleadoViewController {

    private final EmpleadoController empleadoController = new EmpleadoController();
    private final ObservableList<Empleado> listaEmpleados = FXCollections.observableArrayList();

    @FXML
    private Button btnEliminarEmpleado;

    @FXML
    private Button btnLimpiarEmpleado;

    @FXML
    private Button btnRegistrarEmpleado;

    @FXML
    private ComboBox<String> cmbTipoEmpleado;

    @FXML
    private TableColumn<Empleado, String> colCajaEmpleado;

    @FXML
    private TableColumn<Empleado, String> colCodigoEmpleado;

    @FXML
    private TableColumn<Empleado, String> colDocumentoEmpleado;

    @FXML
    private TableColumn<Empleado, Integer> colMinutosEmpleado;

    @FXML
    private TableColumn<Empleado, String> colNombreEmpleado;

    @FXML
    private TableColumn<Empleado, String> colTipoEmpleado;

    @FXML
    private Label lblCajaAsignada;

    @FXML
    private Label lblMensajeEmpleado;

    @FXML
    private TableView<Empleado> tblEmpleados;

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
    void onRegistrarEmpleado(ActionEvent event) {
        try {
            String tipo = cmbTipoEmpleado.getValue();
            String documento = txtDocumentoEmpleado.getText().trim();
            String nombre = txtNombreEmpleado.getText().trim();
            String codigo = txtCodigoEmpleado.getText().trim();
            int minutos = Integer.parseInt(txtMinutosDisponibles.getText().trim());
            String caja = txtCajaAsignada.getText().trim();

            empleadoController.registrarEmpleado(tipo, documento, nombre, codigo, minutos, caja);

            actualizarTabla();
            limpiarFormulario();
            mostrarMensaje("Empleado " + codigo + " registrado correctamente", "mensaje-ok");
        } catch (NumberFormatException e) {
            mostrarMensaje("Los minutos disponibles deben ser un número entero", "mensaje-error");
        } catch (IllegalArgumentException | IllegalStateException e) {
            mostrarMensaje(e.getMessage(), "mensaje-error");
        }
    }

    @FXML
    void onEliminarEmpleado(ActionEvent event) {
        Empleado seleccionado = tblEmpleados.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarMensaje("Seleccione un empleado de la tabla", "mensaje-error");
            return;
        }
        try {
            empleadoController.eliminarEmpleado(seleccionado.getCodigo());
            actualizarTabla();
            mostrarMensaje("Empleado " + seleccionado.getCodigo() + " eliminado", "mensaje-ok");
        } catch (IllegalArgumentException e) {
            mostrarMensaje(e.getMessage(), "mensaje-error");
        }
    }

    @FXML
    void onLimpiarEmpleado(ActionEvent event) {
        limpiarFormulario();
        mostrarMensaje("", "mensaje-neutro");
    }

    @FXML
    void initialize() {
        colCodigoEmpleado.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombreEmpleado.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDocumentoEmpleado.setCellValueFactory(new PropertyValueFactory<>("documento"));
        colMinutosEmpleado.setCellValueFactory(new PropertyValueFactory<>("minutosDisponible"));
        colTipoEmpleado.setCellValueFactory(datos -> new SimpleStringProperty(empleadoController.obtenerTipo(datos.getValue())));
        colCajaEmpleado.setCellValueFactory(datos -> new SimpleStringProperty(empleadoController.obtenerCaja(datos.getValue())));

        tblEmpleados.setItems(listaEmpleados);

        cmbTipoEmpleado.setOnAction(evento -> actualizarCampoCaja());
        actualizarCampoCaja();
        actualizarTabla();
    }

    public void actualizarTabla() {
        listaEmpleados.setAll(empleadoController.obtenerEmpleados());
        tblEmpleados.refresh();
    }

    private void actualizarCampoCaja() {
        boolean esCajero = "Cajero".equals(cmbTipoEmpleado.getValue());
        lblCajaAsignada.setDisable(!esCajero);
        txtCajaAsignada.setDisable(!esCajero);
        if (!esCajero) {
            txtCajaAsignada.clear();
        }
    }

    private void limpiarFormulario() {
        cmbTipoEmpleado.setValue(null);
        txtDocumentoEmpleado.clear();
        txtNombreEmpleado.clear();
        txtCodigoEmpleado.clear();
        txtMinutosDisponibles.clear();
        txtCajaAsignada.clear();
        actualizarCampoCaja();
    }

    private void mostrarMensaje(String texto, String estilo) {
        lblMensajeEmpleado.getStyleClass().removeAll("mensaje-neutro", "mensaje-ok", "mensaje-error");
        lblMensajeEmpleado.getStyleClass().add(estilo);
        lblMensajeEmpleado.setText(texto);
    }
}