/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meucomercio.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import meucomercio.dao.ComandaDao;
import meucomercio.entidades.Comanda;

/**
 * FXML Controller class
 *
 * @author Leandro
 */
public class editarComandaController implements Initializable {

    private boolean atualizando = false;
    private ComandaDao comandaDao = new ComandaDao();
    private Comanda comanda = new Comanda();

    @FXML
    private AnchorPane anchor;
    @FXML
    private AnchorPane root1;
    @FXML
    private Label lblId;
    @FXML
    private TextField tfdNome;
    @FXML
    private TextField tfdAbertura;
    @FXML
    private ComboBox cmbEstado;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnConfirmar;

    @FXML
    private void handleBtnCancelar() {
        // this.finalize();
    }

    @FXML
    private void handleBtnConfirmar() {
        if (atualizando) {
            comandaDao.atualizar(comanda);
        } else {
            comandaDao.salvar(comanda);
        }
        atualizando = false;
        //   handleBtnCancelar();
    }

//    private void configuraBindings() {
//        //bids de campos
//        comanda.idProperty().bind(lblId.textProperty());
//        comanda.dtAberturaProperty().bind(tfdAbertura.textProperty());
//        cmbEstado.getSelectionModel().selectedItemProperty().addListener(new javafx.beans.value.ChangeListener() {
//            @Override
//            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
//                if (newValue != null) {
//                    comanda.setEstado(cmbEstado.getSelectionModel().getSelectedItem().toString());
//                }
//            }
//        });
//    }

//    private void validar() {
//        DecorationPane decorationPane = new DecorationPane(anchor);
//        root.getChildren().add(decorationPane);
//        Validation.validate(tfdNome, Validation.VARCHAR25);
//        Validation.validate(tfdAbertura, Validation.VARCHAR25);
//        Validation.validate(cmbEstado);
//    }
//    private void liberarBotoes() {
//        btnConfirmar.disableProperty().bind(Validation.validGroup.not());
//    }
//
//    private void popularCmbEstado() {
//        ObservableList<String> estados = FXCollections.observableArrayList();
//        estados.add("Aguardando Pedido");
//        estados.add("Consumindo");
//        estados.add("Aguardando 15m");
//        estados.add("Aguardando 30m");
//        estados.add("Atender");
//        estados.add("Solicitou Conta");
//        estados.add("Fechado");
//        cmbEstado.getItems().addAll(estados);
//    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("peru");
        //  validar();
//       liberarBotoes();
//       configuraBindings();
    }

}
