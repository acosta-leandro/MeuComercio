package meucomercio.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import meucomercio.dao.LoginDao;
import meucomercio.entsenhaades.Usuario;

public class loginController implements Initializable{

    LoginDao loginDao = new LoginDao();
    Usuario usuario = new Usuario();
    
    @FXML
    private Button btnLogin;

    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane anchor;

    @FXML
    private PasswordField tfdSenha;

    @FXML
    private TextField tfdLogin;
    
    @FXML
    private Label lblStatus;

    @FXML
    void handleBtnLogin() {
//        if (loginDao.verificarUsuario(usuario)) {
//           meucomercio.MeuComercio.getInstance().iniciarSistema();
//        }else{
//            lblStatus.setText("Usuário ou senha incorreto");
//            lblStatus.setTextFill(Color.rgb(210, 39, 30));
//        }
         meucomercio.MeuComercio.getInstance().iniciarSistema();
        
    }
    
    private void configuraBindings() {
        //bids de campos
        usuario.loginProperty().bind(tfdLogin.textProperty());
        usuario.senhaProperty().bind(tfdSenha.textProperty());        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configuraBindings();
    }

}
