package meucomercio.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import meucomercio.apoio.Util;
import meucomercio.dao.LoginDao;
import meucomercio.entidades.Produto;
import meucomercio.entidades.Usuario;

public class descontoItemController implements Initializable {

    Produto produto = new Produto();

    @FXML
    private Label lblProduto;

    @FXML
    private Button btnConfirmar;

    @FXML
    private Label lblValorNormal;

    @FXML
    private Label lblValorAtual;

    @FXML
    private Label lblSenha;

    @FXML
    private TextField tfdDesconto;

    @FXML
    private Label lblDescontoMax;

    @FXML
    private Label lblDescontoAtual;

    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane anchor;

    @FXML
    private Label lblResponsavel;

    @FXML
    private PasswordField tfdSenha;

    @FXML
    private ToggleGroup tipoDesconto;

    @FXML
    void handleBtnConfirmar() {
        System.out.println("ValorCOnf" + lblValorAtual);
        produto.setValor(lblValorAtual.getText());
        PDVController.setProduto(produto);
        PDVController.getInstance().fecharPopup();
        
    }

    private void configuraBindings() {
        tfdDesconto.textProperty().addListener((observable, oldValue, newValue) -> {
            lblDescontoAtual.setText(newValue + "%");
            calcularPorcentagem();
            tfdSenha.setText("");
        });
        tfdSenha.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (tfdSenha.getText().equalsIgnoreCase(principalController.getUsuarioLogado().getSenha())) {
                btnConfirmar.setDisable(false);
            } else {
                btnConfirmar.setDisable(true);
            }
        });
    }

    private void calcularPorcentagem() {

        Double descontoMax = Double.valueOf(lblDescontoMax.getText().replace("%", ""));
        Double descontoAtual = 0.0;
        if (!tfdDesconto.getText().isEmpty()) {
            descontoAtual = Util.DinheiroParaDouble(tfdDesconto.getText());
        }

        Double valorNormal = Util.DinheiroParaDouble(lblValorNormal.getText());
        Double valorAtual = valorAtual = valorNormal - (valorNormal * (descontoAtual / 100));;

        lblValorAtual.setText(Util.DoubleParaDinheiro(valorAtual));

        if (descontoAtual <= descontoMax) {
            btnConfirmar.setDisable(false);
            lblSenha.setDisable(true);
            tfdSenha.setDisable(true);
        } else {
            lblSenha.setDisable(false);
            tfdSenha.setDisable(false);
            btnConfirmar.setDisable(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void descontoItem(Produto produto) {
        configuraBindings();
        System.out.println("produtodocoooooooooooo" + produto.getProduto());
        lblProduto.setText(produto.getProduto());
        lblValorNormal.setText(produto.getValor());
        lblResponsavel.setText(principalController.getUsuarioLogado().getNome());
        this.produto = produto;
    }
}
