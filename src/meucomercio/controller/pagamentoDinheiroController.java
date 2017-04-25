package meucomercio.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import jidefx.scene.control.decoration.DecorationPane;
import meucomercio.apoio.Util;
import meucomercio.apoio.Validation;
import meucomercio.dao.ComandaDao;
import meucomercio.dao.VendaDao;
import meucomercio.entidades.Produto;

public class pagamentoDinheiroController implements Initializable {

    VendaDao vendaDao = new VendaDao();
    ComandaDao comandaDao = new ComandaDao();
    List<Produto> produtos = new ArrayList<>();
    String idComanda = "1";
    String valorVenda;

    @FXML
    private Button btnConfirmar;

    @FXML
    private TextField tfdTroco;

    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane anchor;

    @FXML
    private TextField tfdValorTotal;

    @FXML
    private TextField tfdValorPago;

    @FXML
    private void handleBtnConfirmar() {
        vendaDao.venderDinheiro(produtos, idComanda);
        comandaDao.faturarComanda(Integer.valueOf(idComanda));
    }

    private void configuraBindings() {
        tfdValorPago.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null || newValue.equals("")) {
                calcularTroco();
            }
        });
    }

    private void calcularTroco() {
        Double valorTotal = Util.DinheiroParaDouble(tfdValorTotal.getText());
        Double valorPago;
        if (!tfdValorPago.getText().isEmpty() && tfdValorPago.getText().matches(Validation.DOUBLE)) {
            valorPago = Double.valueOf(tfdValorPago.getText());
        } else {
            valorPago = 0.0;
        }

        Double troco = valorPago - valorTotal;

        tfdTroco.setText(Util.DoubleParaDinheiro(troco));

        if (valorTotal <= valorPago) {
            btnConfirmar.setDisable(false);
        } else {
            btnConfirmar.setDisable(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void efetuarPagamento(List<Produto> produtos, String idComanda, String valorVenda) {
        configuraBindings();
        this.produtos = produtos;
        this.valorVenda = valorVenda;
        this.idComanda = idComanda;
        tfdValorTotal.setText(valorVenda);
    }

}
