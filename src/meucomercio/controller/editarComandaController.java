/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meucomercio.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Leandro
 */
public class editarComandaController implements Initializable {

    @FXML
    private AnchorPane anchor;
    @FXML
    private TextField tfdBloqueio;
    @FXML
    private Label lblId;
    @FXML
    private TextField tfdBloqueio1;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnConfirmar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleBtnCancelar(ActionEvent event) {
    }

    @FXML
    private void handleBtnConfirmar(ActionEvent event) {
    }
    
}
