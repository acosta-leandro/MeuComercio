/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meucomercio.controller;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import jidefx.scene.control.decoration.DecorationPane;
import meucomercio.dao.ComandaDao;
import meucomercio.entidades.Comanda;
import meucomercio.apoio.Validation;

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
    private AnchorPane root;
    @FXML
    private Label lblId;
    @FXML
    private TextField tfdNome;
    @FXML
    private TextField tfdAbertura;
    @FXML
    private ComboBox cmbEstado;
    @FXML
    private Button btnConfirmar;

    @FXML
    private void handleBtnConfirmar() {
        if (atualizando) {
            comandaDao.atualizar(comanda);
            controlarComandasController.getInstance().fecharPopup();
        } else {
            comandaDao.salvar(comanda);
            controlarComandasController.getInstance().fecharPopup();
        }
        atualizando = false;
        //   handleBtnCancelar();
    }

    private void configuraBindings() {
        //bids de campos
        comanda.idProperty().bind(lblId.textProperty());
        comanda.nomeProperty().bindBidirectional(tfdNome.textProperty());
        comanda.dtAberturaProperty().bindBidirectional(tfdAbertura.textProperty());
        cmbEstado.getSelectionModel().selectedItemProperty().addListener(new javafx.beans.value.ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue != null) {
                    comanda.setEstado(cmbEstado.getSelectionModel().getSelectedItem().toString());
                }
            }
        });
    }

    private void validar() {
        DecorationPane decorationPane = new DecorationPane(anchor);
        root.getChildren().add(decorationPane);
        Validation.validate(tfdNome, Validation.VARCHAR25);
        Validation.validateTrue(tfdAbertura);
        Validation.validate(cmbEstado);
    }

    private void liberarBotoes() {
        btnConfirmar.disableProperty().bind(Validation.validGroup.not());
    }

    private void popularCmbEstado() {
        ObservableList<String> estados = FXCollections.observableArrayList();
        estados.add("Aberto");
        cmbEstado.getItems().addAll(estados);
    }

    private void abertura() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = new Date();
        //System.out.println(dateFormat.format(date));
        tfdAbertura.setText(dateFormat.format(date));
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        popularCmbEstado();
        validar();
        liberarBotoes();
        configuraBindings();
        abertura();
        cmbEstado.getSelectionModel().selectFirst();
    }

    public void editarComanda(Comanda comanda) {
        this.comanda = comanda;
        tfdNome.setText(comanda.getNome());
        tfdAbertura.setText(comanda.getDtAbertura());
        lblId.setText(comanda.getId());
        cmbEstado.getSelectionModel().select(comanda.getEstado());
        Validation.validate(tfdNome, Validation.VARCHAR25);
    }

}
