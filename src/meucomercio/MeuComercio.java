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
    private String primaria = "view/principal.fxml";

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
        System.out.println("1");
        iniciaTelas(primaria);
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void iniciaTelas(String tela) {
        System.out.println("2");
        try {
            FXMLLoader loader = new FXMLLoader();
            // System.out.println("3: "+MeuComercio.class.getResource(tela));
            loader.setLocation(MeuComercio.class.getResource(tela));
            // System.out.println("4");
            AnchorPane pane = (AnchorPane) loader.load();
            // System.out.println("5");

            stage.setScene(new Scene(pane));
            //stage.setResizable(false);
            stage.setFullScreen(true);
            stage.show();

            // borde.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void gotoCategoria() {
//        try {
//            new cadastrarTipoProdutoController();
//            Parent page = (Parent) FXMLLoader.load(MeuComercio.class.getResource("view/cadastrarCategoriaProduto.fxml"), null, new JavaFXBuilderFactory());
//            Scene scene = new Scene(page, 725, 384);
//            stage.setScene(scene);
//            stage.setResizable(false);
//            stage.setTitle("BHGerVendas -- Busca Produtos");
//            stage.centerOnScreen();
//        } catch (Exception ex) {
//            Logger.getLogger(MeuComercio.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

}
