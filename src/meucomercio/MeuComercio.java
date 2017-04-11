/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meucomercio;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import meucomercio.controller.cadastrarTipoProdutoController;

/**
 *
 * @author leandro
 */
public class MeuComercio extends Application {

    static BorderPane borde;
    public static Stage stage;
    private static MeuComercio instance;

    public MeuComercio() {
        instance = this;
    }

    public static MeuComercio getInstance() {
        return instance;
    }

    @Override
    public void start(Stage stage) throws IOException {

        this.stage = stage;
        iniciarLogin();
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void iniciarSistema() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MeuComercio.class.getResource("view/principal.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            stage.setScene(new Scene(pane));
            stage.setFullScreen(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
        public void iniciarLogin() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MeuComercio.class.getResource("view/login.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            stage.setScene(new Scene(pane));
            stage.setTitle("MeuComercio");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

}
