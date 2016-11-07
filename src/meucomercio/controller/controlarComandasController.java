package meucomercio.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import meucomercio.MeuComercio;
import static meucomercio.MeuComercio.stage;

public class controlarComandasController implements Initializable {

    @FXML
    private TitledPane titledPane;

    @FXML
    private Button btnFechar;

    @FXML
    private Button btnF1NovaComanda;

    @FXML
    private void handleBtnF1NovaComanda() throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(MeuComercio.class.getResource("view/editarComanda.fxml"));
//        Parent root1 = (Parent) fxmlLoader.load();
//        Stage stage = new Stage();
//        stage.setScene(new Scene(root1));
//        stage.setTitle("Nova Comanda"); 
//        stage.initModality(Modality.APPLICATION_MODAL);
//        stage.setResizable(false);
    }

    @FXML
    private void handleBtnFechar() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MeuComercio.class.getResource("view/cadastrarGrupoProduto.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("Nova Comanda"); 
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
