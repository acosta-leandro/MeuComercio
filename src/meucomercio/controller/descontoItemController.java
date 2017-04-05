package meucomercio.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import meucomercio.apoio.Util;
import meucomercio.entidades.Produto;

public class descontoItemController implements Initializable {

    @FXML
    private Label lblProduto;

    @FXML
    private Button btnConfirmar;

    @FXML
    private Label lblValorNormal;

    @FXML
    private Label lblValorAtual;

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
        calcularPorcentagem();
    }

    private void configuraBindings() {       
        tfdDesconto.textProperty().addListener((observable, oldValue, newValue) -> {
            lblDescontoAtual.setText(newValue+"%");
            calcularPorcentagem();
        });
    }
    
  

    private void calcularPorcentagem() {
        Double valorNormal = Util.DinheiroParaDouble(lblValorNormal.getText());
        Double valorAtual = 0.0;
        Double desconto = Util.DinheiroParaDouble(tfdDesconto.getText());
        valorAtual = valorNormal - (valorNormal * (desconto / 100));
        lblValorAtual.setText(Util.DoubleParaDinheiro(valorAtual));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void descontoItem(Produto produto) {
        configuraBindings();
        lblProduto.setText(produto.getProduto());
        lblValorNormal.setText(produto.getValor());
    }
}
