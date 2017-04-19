package meucomercio.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import meucomercio.MeuComercio;
import static meucomercio.MeuComercio.stage;
import meucomercio.dao.ComandaDao;
import meucomercio.entidades.Comanda;

public class controlarComandasController implements Initializable {

    ComandaDao comandaDao = new ComandaDao();
    private static principalController princCont = principalController.getInstance();
    private Stage popup = new Stage();
    private static controlarComandasController instance;

    public controlarComandasController() {
        instance = this;
    }

    public static controlarComandasController getInstance() {
        return instance;
    }

    @FXML
    private TitledPane titledPane;

    @FXML
    private Button btnFechar;

    @FXML
    private Button btnF1NovaComanda;

    @FXML
    private Button btnTodosItens;

    @FXML
    private Button btnF2AlterarComanda;

    @FXML
    private Button btnF5ListarTodos;

    @FXML
    private TableView<Comanda> tblComandas;

    @FXML
    private TableColumn<Comanda, String> tblColId;

    @FXML
    private TableColumn<Comanda, String> tblColNome;

    @FXML
    private TableColumn<Comanda, String> tblColEntrada;

    @FXML
    private TableColumn<Comanda, String> tblColEncerramento;

    @FXML
    private TableColumn<Comanda, String> tblColValor;

    @FXML
    private TableColumn<Comanda, String> tblColStatus;

    private void configuraColunas() {
        tblColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblColNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tblColEntrada.setCellValueFactory(new PropertyValueFactory<>("dtAbertura"));
        tblColEncerramento.setCellValueFactory(new PropertyValueFactory<>("dtEncerramento"));
        tblColValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        tblColStatus.setCellValueFactory(new PropertyValueFactory<>("estado"));
    }

    private void configuraBindings() {
        tblComandas.setRowFactory(tv -> {
            TableRow<Comanda> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    try {
                        handleBtnF2AlterarComanda();
                    } catch (IOException ex) {
                        Logger.getLogger(controlarComandasController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            return row;
        });
        btnF2AlterarComanda.disableProperty().bind(Bindings.isEmpty(tblComandas.getSelectionModel().getSelectedItems()));
    }

    @FXML
    private void handleBtnTodosItens() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MeuComercio.class.getResource("view/listarProdutos.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("Listar Produtos");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void handleBtnF1NovaComanda() throws IOException {

        FXMLLoader loader = new FXMLLoader(MeuComercio.class.getResource("view/editarComanda.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);
        popup.setTitle("Nova Comanda");
        popup.setResizable(false);
        popup.setScene(scene);
        popup.showAndWait();
        System.out.println("fechou");
        popularTblComandas();
    }

    @FXML
    private void handleBtnF2AlterarComanda() throws IOException {

        FXMLLoader loader = new FXMLLoader(MeuComercio.class.getResource("view/editarComanda.fxml"));
        AnchorPane anchorPane = loader.load();
        // Get the Controller from the FXMLLoader
        editarComandaController controller = loader.getController();
        controller.editarComanda(tblComandas.getSelectionModel().getSelectedItem());
        // Set data in the controller
        Scene scene = new Scene(anchorPane);
        popup.setTitle("Editar Comanda");
        popup.setResizable(false);
        popup.setScene(scene);
        popup.showAndWait();
        System.out.println("fechou");
        popularTblComandas();
    }

    @FXML
    private void handleBtnF5ListarTodos() throws IOException {
        popularTblComandas();
    }

    private void popularTblComandas() {
        ArrayList comandas = comandaDao.consultarTodos();
        ObservableList<Comanda> tmplistProdutos = FXCollections.observableArrayList(comandas);
        tblComandas.setItems(tmplistProdutos);
    }

    @FXML
    private void handleBtnFechar() {
        princCont.fecharTittledPane("controlarComanda");
    }

    public void fecharPopup() {
        popup.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configuraColunas();
        configuraBindings();
        Platform.runLater(() -> popularTblComandas());
    }

}
