/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meucomercio.controller;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.DoubleStream;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import meucomercio.MeuComercio;
import meucomercio.apoio.Util;
import meucomercio.dao.ComandaDao;
import meucomercio.dao.ProdutoDao;
import meucomercio.entidades.Comanda;
import meucomercio.entidades.Produto;

/**
 *
 * @author Leandro Acosta <leandro.acosta292@hotmail.com>
 */
public class PDVController implements Initializable {

    private ProdutoDao produtoDao = new ProdutoDao();
    private ComandaDao comandaDao = new ComandaDao();
    private static Produto produto;
    private Stage popup = new Stage();
    private static PDVController instance;

    public PDVController() {
        instance = this;
    }

    public static PDVController getInstance() {
        return instance;
    }

    public static Produto getProduto() {
        return produto;
    }

    public static void setProduto(Produto tmpProduto) {
        produto = tmpProduto;
    }

    @FXML
    private Button btnFechar;

    @FXML
    private Button btnConsProduto;

    @FXML
    private Button btnDescontoItem;

    @FXML
    private TableView<Comanda> tblComandas;

    @FXML
    private TableColumn<Comanda, ?> tblColIdComanda;

    @FXML
    private TableColumn<Comanda, ?> tblColValorComanda;

    @FXML
    private Button btnFinalizarVenda;

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
    private TableColumn<Produto, ?> tblColProduto;

    @FXML
    private TableColumn<Produto, String> tblColValorProduto;

    @FXML
    private TextField tfdTotal;

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
    private TableColumn<Produto, ?> tblVendaColProduto;
    @FXML
    private TableColumn<Produto, ?> tblVendaColValorUn;

    @FXML
    private void handleBtnFecharComanda() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Fechar Comanda");
        alert.setHeaderText("Fechando Comanda: " + tblComandas.getSelectionModel().getSelectedItem().getId()
                + " - " + tblComandas.getSelectionModel().getSelectedItem().getNome());
        alert.setContentText("Não será possível reabrir.\nOk?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            comandaDao.fecharComanda(Integer.valueOf(tblComandas.getSelectionModel().getSelectedItem().getId()));
            rbtFechados.setSelected(true);
            actRbtFechados();
        }
    }

    @FXML
    private void handleBtnFinalizarVenda() throws IOException {
        String valorVenda = tfdTotal.getText();
        String comandaIdSelecionada = "1";
        if (!tblComandas.getSelectionModel().isEmpty()) {
            comandaIdSelecionada = tblComandas.getSelectionModel().getSelectedItem().getId();
        }

        FXMLLoader loader = new FXMLLoader(MeuComercio.class.getResource("view/pagamentoDinheiro.fxml"));
        AnchorPane anchorPane = loader.load();
        // Get the Controller from the FXMLLoader
        pagamentoDinheiroController controller = loader.getController();
        List<Produto> consultations = tblVenda.getItems();
        controller.efetuarPagamento(tblVenda.getItems(), comandaIdSelecionada, valorVenda);
        // Set data in the controller
        Scene scene = new Scene(anchorPane);

        popup.setScene(scene);
        popup.setTitle("Finalizar Venda");
        popup.showAndWait();
        System.out.println("fechouuuu");
    }

    @FXML
    private void handleBtnDescontoItem() throws IOException {

        FXMLLoader loader = new FXMLLoader(MeuComercio.class.getResource("view/descontoItem.fxml"));
        AnchorPane anchorPane = loader.load();
        // Get the Controller from the FXMLLoader
        descontoItemController controller = loader.getController();
        controller.descontoItem(tblVenda.getSelectionModel().getSelectedItem());
        // Set data in the controller
        Scene scene = new Scene(anchorPane);

        popup.setScene(scene);
        popup.setTitle("Desconto Item");
        popup.showAndWait();
        System.out.println("fechouuuu");

    }

    public void fecharPopup() {
        popup.close();
    }

    public void prepararFinalizarVenda() {

    }

    @FXML
    private void handleBtnConsProduto() {
        calcularTotal();
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
        popularTblComandas("Faturado");
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

        tblVendaColProduto.setCellValueFactory(new PropertyValueFactory<>("produto"));
        tblVendaColValorUn.setCellValueFactory(new PropertyValueFactory<>("valor"));
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
        //liberar botão de fechar comanda - apenas se estiverem sendo exibidas as comandas aberta
        BooleanBinding booleanBind = Bindings.not(rbtAbertos.selectedProperty())
                .or(Bindings.isEmpty(tblComandas.getSelectionModel().getSelectedItems()));
        btnFecharComanda.disableProperty().bind(booleanBind);
        //liberar botão desconto item apenas se uma linha da tblVenda estiver selecionada
        btnDescontoItem.disableProperty().bind(Bindings.isEmpty(tblVenda.getSelectionModel().getSelectedItems()));
    }

    private void configuraBindings() {
        //inserir produto para venda
        tblProdutos.setRowFactory(tv -> {
            TableRow<Produto> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    tblVenda.getItems().add(row.getItem());
                }
            });
            return row;
        });
        tblComandas.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    System.out.println(rbtFechados.isSelected());
                    if (rbtFechados.isSelected()) {
                        tblVenda.getItems().addAll(comandaDao.produtosComandas(Integer.valueOf(tblComandas.getSelectionModel().getSelectedItem().getId())));
                        tblComandas.setDisable(true);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Informação");
                        alert.setHeaderText("Somente comandas FECHADAS podem ser faturadas!");
                        alert.show();
                    }
                }
            }
        });
        tblVenda.getItems().addListener(new ListChangeListener<Produto>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Produto> arg0) {
                calcularTotal();
            }
        });
    }

    private void inserirProduto(Produto produto) {
        tblProdutos.getItems().add(produto);
    }

    private void calcularTotal() {
        ArrayList<String> valores = new ArrayList<String>();
        for (Produto value : tblVenda.getItems()) {
            //System.out.println(value.getValor());
            valores.add(value.getValor());
        }
        ArrayList<Double> arrayDouble = Util.DinheiroParaDouble(valores);
        //System.out.println("total:" + Util.somarDouble(arrayDouble));
        tfdTotal.setText("R$ " + String.valueOf(Util.somarDouble(arrayDouble)));
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
