/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meucomercio.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import meucomercio.dao.ProdutoDao;
import meucomercio.entidades.Categoria;
import meucomercio.entidades.Produto;

/**
 *
 * @author Leandro Acosta <leandro.acosta292@hotmail.com>
 */
public class listarProdutosController implements Initializable {

    private static fazerPedidoController fPedidoCont = fazerPedidoController.getInstance();
    ProdutoDao produtoDao = new ProdutoDao();

    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane anchor;

    @FXML
    private TableView<Produto> tblProdutos;

    @FXML
    private TableColumn<Produto, String> tblColId;

    @FXML
    private TableColumn<Produto, String> tblColProduto;

    @FXML
    private TextField tfdBuscar;

    private void configurarBusca() {
        ArrayList produtos = produtoDao.consultarTodos();
        ObservableList<Produto> masterData = FXCollections.observableArrayList(produtos);

        // 0. Initialize the columns.
        tblColProduto.setCellValueFactory(cellData -> cellData.getValue().produtoProperty());
        tblColId.setCellValueFactory(cellData -> cellData.getValue().idProperty());

        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Produto> filteredData = new FilteredList<>(masterData, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        tfdBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(produto -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (produto.getProduto().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (produto.getId().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Produto> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tblProdutos.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tblProdutos.setItems(sortedData);
    }

    private void configurarSelecao() {
        tblProdutos.getSelectionModel().selectedItemProperty().addListener(new javafx.beans.value.ChangeListener<Produto>() {
            @Override
            public void changed(ObservableValue<? extends Produto> observable, Produto oldValue, Produto newValue) {
                if (newValue != null) {
                    Produto p = tblProdutos.getSelectionModel().getSelectedItem();
                    String strProd = p.getId() + " - " + p.getProduto();
                    fPedidoCont.selecionarProduto(strProd);
                }
            }
        });
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarBusca();
        configurarSelecao();
        Platform.runLater(() -> tfdBuscar.requestFocus());
    }
}
