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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Leandro Acosta <leandro.acosta292@hotmail.com>
 */
public class novaComandaController implements Initializable {

    /**
     * Initializes the controller class.
     *
     */
    @FXML
    private TextField tfd2;

    @FXML
    private TextField tfd1;
    
    private Stage dialogStage;

   public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
