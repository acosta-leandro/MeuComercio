/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meucomercio.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableBooleanValue;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import meucomercio.dao.FazerPedidoDao;
import meucomercio.dao.ProdutoDao;
import meucomercio.entidades.Categoria;
import meucomercio.entidades.Pedido;
import meucomercio.entidades.Produto;

/**
 *
 * @author Leandro Acosta <leandro.acosta292@hotmail.com>
 */
public class cancelarPedidoController implements Initializable {

    private static fazerPedidoController fPedidoCont = fazerPedidoController.getInstance();
    ProdutoDao produtoDao = new ProdutoDao();
    FazerPedidoDao pedidoDao = new FazerPedidoDao();

    @FXML
    private TextField tfdProduto;

    @FXML
    private TextField tfdStatus;

    @FXML
    private TableView<Pedido> tblPedidos;

    @FXML
    private TableColumn<Pedido, String> tblColComanda;

    @FXML
    private TableColumn<Pedido, String> tblColProduto;

    @FXML
    private TableColumn<Pedido, String> tblColStatus;

    @FXML
    private TableColumn<Pedido, Integer> tblColId;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField tfdComanda;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField tfdId;

    @FXML
    void handleBtnCancelar() {
        System.out.println("alfa");
        if (tblPedidos.getSelectionModel().getSelectedItem().getStatus().equalsIgnoreCase("Aberto")) {
            System.out.println("alta");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Excluir Categoria");
            alert.setHeaderText("Cancelando Pedido " + tblPedidos.getSelectionModel().getSelectedItem().getIdPedido());
            alert.setContentText("Tem certeza?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                pedidoDao.alterarEstadoCancelado(tblPedidos.getSelectionModel().getSelectedItem().getIdPedido());
                popularTable();
                fPedidoCont.fecharPopUp();                
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        }
    }

    private void popularTable() {
        tblColComanda.setCellValueFactory(new PropertyValueFactory<>("nomeComanda"));
        tblColProduto.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
        tblColStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tblColId.setCellValueFactory(new PropertyValueFactory<>("idPedido"));
        ArrayList pedidos = new ArrayList();
        pedidos = pedidoDao.consultarTodos();
        ObservableList<Pedido> listPedidos = FXCollections.observableArrayList(pedidos);
        tblPedidos.setItems(listPedidos);
    }

    private void configurarSelecao() {
        tblPedidos.getSelectionModel().selectedItemProperty().addListener(new javafx.beans.value.ChangeListener<Pedido>() {
            @Override
            public void changed(ObservableValue<? extends Pedido> observable, Pedido oldValue, Pedido newValue) {
                if (newValue != null) {
                    tfdComanda.setText(newValue.getNomeComanda());
                    tfdProduto.setText(newValue.getNomeProduto());
                    tfdStatus.setText(newValue.getStatus());
                    tfdId.setText(String.valueOf(newValue.getIdPedido()));
                }
            }
        });
        btnCancelar.disableProperty().bind(tblPedidos.getSelectionModel().selectedItemProperty().isNull());

    }

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        popularTable();
        configurarSelecao();
    }
}
