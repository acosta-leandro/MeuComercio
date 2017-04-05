package meucomercio.controller;

import meucomercio.dao.UnMedidaDao;
import meucomercio.entidades.UnMedida;
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
import meucomercio.dao.GrupoDao;
import meucomercio.entidades.Grupo;

/**
 * Created by leandro on 04/07/16.
 */
public class cadastrarUnMedidaController implements Initializable {

    UnMedidaDao unMedidaDao = new UnMedidaDao();
    Sequence sequence = new Sequence();
    private static principalController princCont = principalController.getInstance();
    private UnMedida unMedida = new UnMedida();
    private List<UnMedida> listUnMedida;
    private ObservableList<UnMedida> observableListUnMedida;
    private boolean atualizando = false;

    @FXML
    private TableView<UnMedida> tblUnMedida;
    @FXML
    private TableColumn<UnMedida, Integer> tblColId;
    @FXML
    private TableColumn<UnMedida, String> tblColNome;
    @FXML
    private TableColumn<UnMedida, String> tblColSigla;
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
    private Label lblUnMedidaId;
    @FXML
    private Label lblId;
    @FXML
    private TextField tfdNome;
    @FXML
    private TextField tfdSigla;
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
    private TableColumn<UnMedida, String> tblColEstado;

    @FXML
    private void handleBtnPesquisar() {
        ArrayList unMedidas = new ArrayList();
        if (tfdPesquisa.getText().equals("")) {
            unMedidas = unMedidaDao.consultarTodos();
        } else {
            unMedidas = unMedidaDao.consultar(tfdPesquisa.getText());
        }
        for (int i = 0; i < unMedidas.size(); i++) {
            UnMedida tmpUnMedida = (UnMedida) unMedidas.get(i);
        }
        ObservableList<UnMedida> listUnMedidas = FXCollections.observableArrayList(unMedidas);
        tblUnMedida.setItems(listUnMedidas);
    }

    @FXML
    private void handleBtnRemover() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Excluir UnMedida");
        alert.setHeaderText("Deseja excluir '" + unMedida.getNome() + "' ?");
        alert.setContentText("Tem certeza?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            unMedidaDao.excluir(Integer.valueOf(unMedida.getId()));
            handleBtnCancelar();
            handleBtnPesquisar();
        }
    }

    @FXML
    private void handleBtnConfirmar() {
        if (atualizando) {
            unMedidaDao.atualizar(unMedida);
        } else {
            unMedidaDao.salvar(unMedida);
        }
        atualizando = false;
        handleBtnCancelar();
    }

    @FXML
    private void handleBtnCancelar() {
        tblUnMedida.getSelectionModel().clearSelection();
        lblId.setText("X");
        tfdNome.setText("");
        tfdSigla.setText("");
        tblUnMedida.setDisable(false);
        btnPesquisar.setDisable(false);
        handleBtnPesquisar();
    }

    @FXML
    private void handleBtnFechar() {
        princCont.fecharTittledPane("cadastrarUnMedidaProduto");
    }

    private void configuraColunas() {
        tblColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblColNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tblColSigla.setCellValueFactory(new PropertyValueFactory<>("sigla"));
        tblColEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        handleBtnPesquisar();
    }

    // configura a lógica da tela
    private void configuraBindings() {
        //binds de campos
        unMedida.idProperty().bind(lblId.textProperty());
        unMedida.nomeProperty().bind(tfdNome.textProperty());
        unMedida.siglaProperty().bind(tfdSigla.textProperty());
        // quando algo é selecionado na tabela, preenchemos os campos de entrada com os valores para o 
        tblUnMedida.getSelectionModel().selectedItemProperty().addListener(new javafx.beans.value.ChangeListener<UnMedida>() {
            @Override
            public void changed(ObservableValue<? extends UnMedida> observable, UnMedida oldValue, UnMedida newValue) {
                if (newValue != null) {
                    tfdNome.textProperty().bindBidirectional(newValue.nomeProperty());
                    lblId.textProperty().bind(Bindings.convert(newValue.idProperty()));
                    tfdSigla.textProperty().bindBidirectional(newValue.siglaProperty());
                    atualizando = true;
                    tblUnMedida.setDisable(true);
                    btnPesquisar.setDisable(true);
                    atualizando = true;
                    cmbEstado.getSelectionModel().select(newValue.getEstado());
                } else {
                    lblId.textProperty().unbind();
                    tfdNome.textProperty().unbind();
                    tfdSigla.textProperty().unbind();
                    tfdSigla.setText("");
                    lblId.setText("X");
                    tfdNome.setText("");
                }
            }
        });
        cmbEstado.getSelectionModel().selectedItemProperty().addListener(new javafx.beans.value.ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    unMedida.setEstado((String) cmbEstado.getSelectionModel().getSelectedItem());
                }
            }
        });
    }

    private void validar() {
        DecorationPane decorationPane = new DecorationPane(anchor);
        root.getChildren().add(decorationPane);
        Validation.validate(tfdNome, Validation.VARCHAR25);
        Validation.validate(tfdSigla, Validation.SIGLA);
    }

    private void liberarBotoes() {
        btnConfirmar.disableProperty().bind(Validation.validGroup.not());
       // btnRemover.disableProperty().bind(tblUnMedida.getSelectionModel().selectedItemProperty().isNull());
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
        validar();
        liberarBotoes();
        configuraColunas();
        configuraBindings();
        popularCmbEstado();
        cmbEstado.getSelectionModel().selectFirst();
    }
}
