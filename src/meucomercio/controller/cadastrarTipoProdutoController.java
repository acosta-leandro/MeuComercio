package meucomercio.controller;

import meucomercio.dao.TipoDao;
import meucomercio.entidades.Tipo;
import meucomercio.entidades.Sequence;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.binding.BooleanBinding;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import jidefx.scene.control.decoration.DecorationPane;
import meucomercio.apoio.Validation;

/**
 * Created by leandro on 04/07/16.
 */
public class cadastrarTipoProdutoController implements Initializable {

    TipoDao tipoDao = new TipoDao();
    Sequence sequence = new Sequence();
    private static principalController princCont = principalController.getInstance();
    private Tipo tipo = new Tipo();
    private List<Tipo> listTipo;
    private ObservableList<Tipo> observableListTipo;
    private boolean atualizando = false;

    @FXML
    private TableView<Tipo> tblTipo;
    @FXML
    private TableColumn<Tipo, Integer> tblColId;
    @FXML
    private TableColumn<Tipo, String> tblColTipo;
    @FXML
    private Button btnNovo;
    @FXML
    private Button btnPesquisar;
    @FXML
    private Button btnRemover;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnFechar;
    @FXML
    private Label lblTipoId;
    @FXML
    private Label lblId;
    @FXML
    private TextField tfdTipo;
    @FXML
    private TextField tfdPesquisa;
    @FXML
    private TitledPane titledPane;
    @FXML
    private AnchorPane anchor;
    @FXML
    private AnchorPane root;
    @FXML
    private ComboBox cmbEstado;
    @FXML
    private TableColumn<Tipo, String> tblColEstado;

    @FXML
    private void handleBtnPesquisar() {
        ArrayList tipos = new ArrayList();
        if (tfdPesquisa.getText().equals("")) {
            tipos = tipoDao.consultarTodos();
        } else {
            tipos = tipoDao.consultar(tfdPesquisa.getText());
        }
        for (int i = 0; i < tipos.size(); i++) {
            Tipo tmpTipo = (Tipo) tipos.get(i);
        }
        ObservableList<Tipo> listTipos = FXCollections.observableArrayList(tipos);
        tblTipo.setItems(listTipos);
    }

    @FXML
    private void handleBtnRemover() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Excluir Tipo");
        alert.setHeaderText("Deseja excluir '" + tipo.getTipo() + "' ?");
        alert.setContentText("Tem certeza?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            tipoDao.excluir(Integer.valueOf(tipo.getId()));
            handleBtnCancelar();
            handleBtnPesquisar();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    @FXML
    private void handleBtnConfirmar() {
        if (atualizando) {
            tipoDao.atualizar(tipo);
        } else {
            tipoDao.salvar(tipo);
        }
        handleBtnCancelar();
    }

    @FXML
    private void handleBtnCancelar() {
        tblTipo.getSelectionModel().clearSelection();
        lblId.setText("X");
        tfdTipo.setText("");
        tblTipo.setDisable(false);
        btnPesquisar.setDisable(false);
        handleBtnPesquisar();
    }

    @FXML
    private void handleBtnFechar() {
        princCont.fecharTittledPane("cadastrarTipoProduto");
    }

    private void configuraColunas() {
        tblColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblColTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        tblColEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        handleBtnPesquisar();
    }

    // configura a lógica da tela
    private void configuraBindings() {
        //bids de campos
        tipo.idProperty().bind(lblId.textProperty());
        tipo.tipoProperty().bind(tfdTipo.textProperty());
        // quando algo é selecionado na tabela, preenchemos os campos de entrada com os valores para o 
        tblTipo.getSelectionModel().selectedItemProperty().addListener(new javafx.beans.value.ChangeListener<Tipo>() {
            @Override
            public void changed(ObservableValue<? extends Tipo> observable, Tipo oldValue, Tipo newValue) {
                if (newValue != null) {
                    tfdTipo.textProperty().bindBidirectional(newValue.tipoProperty());
                    lblId.textProperty().bind(Bindings.convert(newValue.idProperty()));
                    tblTipo.setDisable(true);
                    btnPesquisar.setDisable(true);
                    atualizando = true;
                    cmbEstado.getSelectionModel().select(newValue.getEstado());
                } else {
                    lblId.textProperty().unbind();
                    tfdTipo.textProperty().unbind();
                    tfdTipo.setText("");
                    lblId.setText("X");
                    atualizando = false;
                }
            }
        });
        cmbEstado.getSelectionModel().selectedItemProperty().addListener(new javafx.beans.value.ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    tipo.setEstado((String) cmbEstado.getSelectionModel().getSelectedItem());
                }
            }
        });
    }

    private void validar() {
        DecorationPane decorationPane = new DecorationPane(anchor);
        root.getChildren().add(decorationPane);
        Validation.validate(tfdTipo, Validation.VARCHAR25);
    }

    private void liberarBotoes() {
        btnConfirmar.disableProperty().bind(Validation.validGroup.not());
        //   btnRemover.disableProperty().bind(tblTipo.getSelectionModel().selectedItemProperty().isNull());
        btnRemover.setDisable(true);
    }

    private void popularCmbEstado() {
        ObservableList<String> estados = FXCollections.observableArrayList();
        estados.add("Ativo");
        estados.add("Desativado");
        cmbEstado.getItems().addAll(estados);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configuraColunas();
        configuraBindings();
        validar();
        liberarBotoes();
        popularCmbEstado();
        cmbEstado.getSelectionModel().selectFirst();
    }
}
