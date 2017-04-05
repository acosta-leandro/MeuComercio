package meucomercio.controller;

import meucomercio.dao.GrupoDao;
import meucomercio.entidades.Grupo;
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
public class cadastrarGrupoProdutoController implements Initializable {

    GrupoDao grupoDao = new GrupoDao();
    Sequence sequence = new Sequence();
    private static principalController princCont = principalController.getInstance();
    private Grupo grupo = new Grupo();
    private List<Grupo> listGrupo;
    private ObservableList<Grupo> observableListGrupo;
    boolean atualizando = false;

    @FXML
    private TableView<Grupo> tblGrupo;
    @FXML
    private TableColumn<Grupo, Integer> tblColId;
    @FXML
    private TableColumn<Grupo, String> tblColGrupo;
    @FXML
    private Button btnNovo;
    @FXML
    private Button btnPesquisar;
    @FXML
    private Button btnRemover;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnAtualizar;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnFechar;
    @FXML
    private Label lblGrupoId;
    @FXML
    private Label lblId;
    @FXML
    private TextField tfdGrupo;
    @FXML
    private TextField tfdPesquisa;
    @FXML
    private TitledPane titledPane;
    @FXML
    private AnchorPane root;
    @FXML
    private AnchorPane anchor;
    @FXML
    private ComboBox cmbEstado;
    @FXML
    private TableColumn<Grupo, String> tblColEstado;

    @FXML
    private void handleBtnPesquisar() {
        ArrayList grupos = new ArrayList();
        if (tfdPesquisa.getText().equals("")) {
            grupos = grupoDao.consultarTodos();
        } else {
            grupos = grupoDao.consultar(tfdPesquisa.getText());
        }
        for (int i = 0; i < grupos.size(); i++) {
            Grupo tmpGrupo = (Grupo) grupos.get(i);
        }
        ObservableList<Grupo> listGrupos = FXCollections.observableArrayList(grupos);
        tblGrupo.setItems(listGrupos);
    }

    @FXML
    private void handleBtnRemover() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Excluir Grupo");
        alert.setHeaderText("Deseja excluir '" + grupo.getGrupo() + "' ?");
        alert.setContentText("Tem certeza?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            grupoDao.excluir(Integer.valueOf(grupo.getId()));
            handleBtnCancelar();
            handleBtnPesquisar();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    @FXML
    private void handleBtnConfirmar() {
        if (atualizando) {
            grupoDao.atualizar(grupo);
        } else {
            grupoDao.salvar(grupo);
        }
        atualizando = false;
        handleBtnCancelar();
    }

    @FXML
    private void handleBtnCancelar() {
        tblGrupo.getSelectionModel().clearSelection();
        lblId.setText("X");
        tfdGrupo.setText("");
        tblGrupo.setDisable(false);
        btnPesquisar.setDisable(false);
        handleBtnPesquisar();
    }

    @FXML
    private void handleBtnAtualizar() {
        tblGrupo.setDisable(false);
        btnPesquisar.setDisable(false);
        grupoDao.atualizar(grupo);
        handleBtnPesquisar();
    }

    @FXML
    private void handleBtnFechar() {
        princCont.fecharTittledPane("cadastrarGrupoProduto");
    }

    private void configuraColunas() {
        tblColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblColGrupo.setCellValueFactory(new PropertyValueFactory<>("grupo"));
        tblColEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        handleBtnPesquisar();
    }
    // configura a lógica da tela

    private void configuraBindings() {
        //bids de campos
        grupo.idProperty().bind(lblId.textProperty());
        grupo.grupoProperty().bind(tfdGrupo.textProperty());
        // quando algo é selecionado na tabela, preenchemos os campos de entrada com os valores para o 
        tblGrupo.getSelectionModel().selectedItemProperty().addListener(new javafx.beans.value.ChangeListener<Grupo>() {
            @Override
            public void changed(ObservableValue<? extends Grupo> observable, Grupo oldValue, Grupo newValue) {
                if (newValue != null) {
                    tfdGrupo.textProperty().bindBidirectional(newValue.grupoProperty());
                    lblId.textProperty().bind(Bindings.convert(newValue.idProperty()));
                    tblGrupo.setDisable(true);
                    btnPesquisar.setDisable(true);
                    atualizando = true;
                    cmbEstado.getSelectionModel().select(newValue.getEstado());
                } else {
                    lblId.textProperty().unbind();
                    tfdGrupo.textProperty().unbind();
                    tfdGrupo.setText("");
                    lblId.setText("X");
                }
            }
        });
        cmbEstado.getSelectionModel().selectedItemProperty().addListener(new javafx.beans.value.ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    grupo.setEstado((String) cmbEstado.getSelectionModel().getSelectedItem());
                }
            }
        });
    }

    private void validar() {
        DecorationPane decorationPane = new DecorationPane(anchor);
        root.getChildren().add(decorationPane);
        Validation.validate(tfdGrupo, Validation.VARCHAR25);
    }

    private void liberarBotoes() {
        btnConfirmar.disableProperty().bind(Validation.validGroup.not());
        //    btnRemover.disableProperty().bind(tblGrupo.getSelectionModel().selectedItemProperty().isNull());
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
