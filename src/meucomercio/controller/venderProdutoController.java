package meucomercio.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;

public class venderProdutoController implements Initializable {

    @FXML
    private TitledPane titledPane;

    @FXML
    private Button btnFechar;

    @FXML
    private TextField tfdInserirCod;

    @FXML
    private TextField tfdPesquisarItem;

    @FXML
    private ListView<?> listItens;

    @FXML
    private TableView<?> tblProdutos;

    @FXML
    private TextField tfdTotal;

    @FXML
    private Button btnF1Finalizar;

    @FXML
    private Button btnF2CancelarItem;

    @FXML
    private Button btnF3Cancelar;

    @FXML
    private Button btnF4ConsultarItem;

    @FXML
    private Button btnF5DescontoItem;

    @FXML
    private Button btnF6DescontoCupom;

    @FXML
    private TextField tfdDesconto;

    @FXML
    private void handleBtnF1Finalizar() {

    }

    @FXML
    private void handleBtnF2CancelarItem() {

    }

    @FXML
    private void handleBtnF3ConsultarItem() {

    }

    @FXML
    private void handleBtnF4DescontoItem() {

    }


    @FXML
    private void handleBtnFechar() {

    }

    @FXML
    private void handleF6Desconto() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
