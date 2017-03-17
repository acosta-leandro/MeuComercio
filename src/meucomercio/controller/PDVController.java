/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meucomercio.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import meucomercio.dao.ComandaDao;
import meucomercio.dao.ProdutoDao;
import meucomercio.entidades.Comanda;
import meucomercio.entidades.Produto;

/**
 *
 * @author Leandro Acosta <leandro.acosta292@hotmail.com>
 */
public class PDVController implements Initializable {

    ProdutoDao produtoDao = new ProdutoDao();
    ComandaDao comandaDao = new ComandaDao();

    @FXML
    private Button btnFechar;

    @FXML
    private Button btnConsProduto;

    @FXML
    private Button btnDescontoItem;

    @FXML
    private TextField tfdTotalProdutos;

    @FXML
    private TableView<Comanda> tblComandas;

    @FXML
    private TableColumn<Comanda, ?> tblColIdComanda;

    @FXML
    private TableColumn<Comanda, ?> tblColValorComanda;

    @FXML
    private Button btnFinalizarVenda;

    @FXML
    private TextField tfdDesconto;

    @FXML
    private Button btnFecharComanda;

    @FXML
    private TitledPane titledPane;

    @FXML
    private TableView<Produto> tblProdutos;

    @FXML
    private TableColumn<Comanda, ?> tblColNome;

    @FXML
    private TableColumn<Produto, ?> tblColIdProduto;

    @FXML
    private TableColumn<Produto, String> tblColValorProduto;

    @FXML
    private TextField tfdTotalFinal;

    @FXML
    private RadioButton rbtTodos;

    @FXML
    private RadioButton rbtFechados;

    @FXML
    private RadioButton rbtFaturados;

    @FXML
    private RadioButton rbtAbertos;

    @FXML
    private TableView<Produto> tblVenda;
    @FXML
    private TableColumn<Produto, ?> tblColItem;
    @FXML
    private TableColumn<Produto, ?> tblColId;
    @FXML
    private TableColumn<Produto, ?> tblColProduto;
    @FXML
    private TableColumn<Produto, ?> tblColQuant;
    @FXML
    private TableColumn<Produto, ?> tblColValorUn;
    @FXML
    private TableColumn<Produto, ?> tblColValorTotalItem;

    @FXML
    private void handleBtnFecharComanda() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Fechar Comanda");
        alert.setHeaderText("Fechando Comanda: " + tblComandas.getSelectionModel().getSelectedItem().getId()
                + " - " + tblComandas.getSelectionModel().getSelectedItem().getNome());
        alert.setContentText("Não é possível reabrir.\nTem Certeza?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            comandaDao.fecharComanda(Integer.valueOf(tblComandas.getSelectionModel().getSelectedItem().getId()));
            rbtFechados.setSelected(true);
            actRbtFechados();
        }
    }

    @FXML
    private void handleBtnFinalizarVenda() {
        System.out.println("aaaa");
    }

    @FXML
    private void handleBtnDescontoItem() {

    }

    @FXML
    private void handleBtnConsProduto() {

    }

    @FXML
    private void handleBtnFechar() {

    }

    @FXML
    private void actRbtFechados() {
        popularTblComandas("Fechado");
    }

    @FXML
    private void actRbtAbertos() {
        popularTblComandas("Aberto");
    }

    @FXML
    private void actRbtFaturados() {
        popularTblComandas("Faturados");
    }

    @FXML
    private void actRbtTodos() {
        popularTblComandas("Todos");
    }

    private void configurarColunas() {
        tblColIdProduto.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblColProduto.setCellValueFactory(new PropertyValueFactory<>("produto"));
        tblColValorProduto.setCellValueFactory(new PropertyValueFactory<>("valor"));

        tblColIdComanda.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblColNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tblColValorComanda.setCellValueFactory(new PropertyValueFactory<>("valor"));

        tblColProduto.setCellValueFactory(new PropertyValueFactory<>("produto"));
        tblColQuant.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblColValorUn.setCellValueFactory(new PropertyValueFactory<>("valor"));
        tblColValorTotalItem.setCellValueFactory(new PropertyValueFactory<>("valor"));
    }

    private void popularTblProdutos() {
        ArrayList produtos = new ArrayList();
        produtos = produtoDao.consultarTodos();
        ObservableList<Produto> list = FXCollections.observableArrayList(produtos);
        tblProdutos.setItems(list);
    }

    private void popularTblComandas(String comandaStatus) {
        ArrayList comandas = new ArrayList();
        if (comandaStatus.equalsIgnoreCase("Todos")) {
            comandas = comandaDao.consultarTodos();
        } else {
            comandas = comandaDao.consultarStatus(comandaStatus);
        }
        ObservableList<Comanda> list = FXCollections.observableArrayList(comandas);
        tblComandas.setItems(list);
    }

    private void liberarBotoes() {
        BooleanBinding booleanBind = Bindings.not(rbtAbertos.selectedProperty())
                .or(Bindings.isEmpty(tblComandas.getSelectionModel().getSelectedItems()));
        btnFecharComanda.disableProperty().bind(booleanBind);
    }

    private void configuraBindings() {
        tblProdutos.setRowFactory(tv -> {
            TableRow<Produto> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    tblVenda.getItems().add(row.getItem());                    
                }
            });
            return row;
        });

    }

    private void inserirProduto(Produto produto) {
        tblProdutos.getItems().add(produto);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarColunas();
        popularTblProdutos();
        actRbtAbertos();
        liberarBotoes();
        configuraBindings();
    }
}
