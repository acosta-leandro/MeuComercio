package meucomercio.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import meucomercio.apoio.Criptografa;
import meucomercio.dao.LoginDao;
import meucomercio.entidades.Usuario;
import meucomercio.reports.PrintReport;
import net.sf.jasperreports.engine.JRException;

public class loginController implements Initializable {

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
        Usuario user = new Usuario();
        user.setLogin(tfdLogin.getText());
        user.setSenha(Criptografa.Cripto(tfdSenha.getText()));
        if (loginDao.verificarUsuario(user)) {
            meucomercio.MeuComercio.getInstance().iniciarSistema();
        } else {
            lblStatus.setText("Usuário ou senha incorreto");
            lblStatus.setTextFill(Color.rgb(210, 39, 30));
        }
//////////        Usuario user = new Usuario();
//////////        user.setLogin("adm");
//////////        user.setSenha("adm");
//////////        loginDao.verificarUsuario(user);
//////////        meucomercio.MeuComercio.getInstance().iniciarSistema();
//////////        System.out.println("adm:"+Criptografa.Cripto("adm"));
//////////        System.out.println("user:"+Criptografa.Cripto("user"));

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
