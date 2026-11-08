package co.edu.uniquindio.poo.parcial3.app;

import co.edu.uniquindio.poo.parcial3.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.layout.BorderPane;

public class App extends Application {
    private Stage primaryStage;
    public static final Panaderia empresa = new Panaderia("Panadería El Trigal");

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(empresa.getNombre());
        openViewPrincipal();
    }

    private void openViewPrincipal() {
        inicializarDatos();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/co/edu/uniquindio/poo/parcial3/Principal.fxml"));
            BorderPane rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

    private void inicializarDatos() {
        // === REGISTRO DE EMPLEADOS ===
        Empleado panadero1 = new PanaderoIndustrial("1040", "Antonio", "E01", 300);
        Empleado panadero2 = new PanaderoArtesanal("1041", "Carlos", "E02", 300);
        Empleado panadero3 = new PanaderoIndustrial("1042", "Beatriz", "E03", 300);
        Empleado panadero4 = new PanaderoArtesanal("1043", "Elena", "E04", 300);
        Empleado cajero1 = new Cajero("1044", "David", "E05", 300, "Caja 1");

        empresa.agregarEmpleado(panadero1);
        empresa.agregarEmpleado(panadero2);
        empresa.agregarEmpleado(panadero3);
        empresa.agregarEmpleado(panadero4);
        empresa.agregarEmpleado(cajero1);

        // === REGISTRO DE PEDIDOS ===
        empresa.registrarPedido("P01", "Pepe", TipoPan.TRADICIONAL, 25);
        empresa.registrarPedido("P02", "Maria", TipoPan.INTEGRAL, 40);
        empresa.registrarPedido("P03", "Juan", TipoPan.TRADICIONAL, 15);
        empresa.registrarPedido("P04", "Lucia", TipoPan.INTEGRAL, 60);
        empresa.registrarPedido("P05", "Andres", TipoPan.TRADICIONAL, 30);
    }
}