package meucomercio.controller;

import meucomercio.dao.SubgrupoDao;
import meucomercio.entidades.Subgrupo;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import jidefx.scene.control.decoration.DecorationPane;
import meucomercio.apoio.Validation;
import meucomercio.dao.GrupoDao;
import meucomercio.entidades.Grupo;

/**
 * Created by leandro on 04/07/16.
 */
public class cadastrarSubgrupoProdutoController implements Initializable {

    SubgrupoDao subgrupoDao = new SubgrupoDao();
    Sequence sequence = new Sequence();
    private static principalController princCont = principalController.getInstance();
    private Subgrupo subgrupo = new Subgrupo();
    private List<Subgrupo> listSubgrupo;
    private ObservableList<Subgrupo> observableListSubgrupo;
    private boolean atualizando = false;

    @FXML
    private TableView<Subgrupo> tblSubgrupo;
    @FXML
    private TableColumn<Subgrupo, Integer> tblColId;
    @FXML
    private TableColumn<Subgrupo, String> tblColSubgrupo;
    @FXML
    private TableColumn<Subgrupo, String> tblColGrupo;
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
    private Label lblSubgrupoId;
    @FXML
    private Label lblId;
    @FXML
    private TextField tfdSubgrupo;
    @FXML
    private TextField tfdPesquisa;
    @FXML
    private TitledPane titledPane;
    @FXML
    private ComboBox cmbGrupo;
    @FXML
    private AnchorPane root;
    @FXML
    private AnchorPane anchor;
    @FXML
    private ComboBox cmbEstado;
    @FXML
    private TableColumn<Subgrupo, String> tblColEstado;

    @FXML
    private void handleBtnPesquisar() {
        ArrayList subgrupos = new ArrayList();
        if (tfdPesquisa.getText().equals("")) {
            subgrupos = subgrupoDao.consultarTodos();
        } else {
            subgrupos = subgrupoDao.consultar(tfdPesquisa.getText());
        }
        for (int i = 0; i < subgrupos.size(); i++) {
            Subgrupo tmpSubgrupo = (Subgrupo) subgrupos.get(i);
        }
        ObservableList<Subgrupo> listSubgrupos = FXCollections.observableArrayList(subgrupos);
        tblSubgrupo.setItems(listSubgrupos);
    }

    @FXML
    private void handleBtnRemover() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Excluir Subgrupo");
        alert.setHeaderText("Deseja excluir '" + subgrupo.getSubgrupo() + "' ?");
        alert.setContentText("Tem certeza?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            subgrupoDao.excluir(Integer.valueOf(subgrupo.getId()));
            handleBtnCancelar();
            handleBtnPesquisar();
        }
    }

    @FXML
    private void handleBtnConfirmar() {
        if (atualizando) {
            subgrupoDao.atualizar(subgrupo);
        } else {
            Grupo grupo = (Grupo) new GrupoDao().consultarNome(cmbGrupo.getSelectionModel().getSelectedItem().toString());
            subgrupo.setGrupoId(grupo.getId());
            subgrupoDao.salvar(subgrupo);
        }
        atualizando = false;
        handleBtnCancelar();
    }

    @FXML
    private void handleBtnCancelar() {
        tblSubgrupo.getSelectionModel().clearSelection();
        lblId.setText("X");
        tfdSubgrupo.setText("");
        cmbGrupo.getSelectionModel().clearSelection();
        tblSubgrupo.setDisable(false);
        btnPesquisar.setDisable(false);
        handleBtnPesquisar();
    }

    @FXML
    private void handleBtnFechar() {
        princCont.fecharTittledPane("cadastrarSubgrupoProduto");
    }

    private void configuraColunas() {
        tblColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblColSubgrupo.setCellValueFactory(new PropertyValueFactory<>("subgrupo"));
        tblColGrupo.setCellValueFactory(new PropertyValueFactory<>("grupoNome"));
        handleBtnPesquisar();
    }

    // configura a lógica da tela
    private void configuraBindings() {
        //bids de campos
        subgrupo.idProperty().bind(lblId.textProperty());
        subgrupo.subgrupoProperty().bind(tfdSubgrupo.textProperty());
        // quando algo é selecionado na tabela, preenchemos os campos de entrada com os valores para o 
        tblSubgrupo.getSelectionModel().selectedItemProperty().addListener(new javafx.beans.value.ChangeListener<Subgrupo>() {
            @Override
            public void changed(ObservableValue<? extends Subgrupo> observable, Subgrupo oldValue, Subgrupo newValue) {
                if (newValue != null) {
                    tfdSubgrupo.textProperty().bindBidirectional(newValue.subgrupoProperty());
                    lblId.textProperty().bind(Bindings.convert(newValue.idProperty()));
                    cmbGrupo.getSelectionModel().select(newValue.getGrupoNome());
                    tblSubgrupo.setDisable(true);
                    btnPesquisar.setDisable(true);
                    atualizando = true;
                } else {
                    lblId.textProperty().unbind();
                    tfdSubgrupo.textProperty().unbind();
                    tfdSubgrupo.setText("");
                    lblId.setText("X");
                }
            }
        });
        cmbGrupo.getSelectionModel().selectedItemProperty().addListener(new javafx.beans.value.ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    Grupo grupo = (Grupo) new GrupoDao().consultarNome(cmbGrupo.getSelectionModel().getSelectedItem().toString());
                    subgrupo.setGrupoId(grupo.getId());
                }
            }
        });
    }

    private void validar() {
        DecorationPane decorationPane = new DecorationPane(anchor);
        root.getChildren().add(decorationPane);
        Validation.validate(tfdSubgrupo, Validation.VARCHAR25);
        Validation.validate(cmbGrupo);
    }

    private void liberarBotoes() {
        btnConfirmar.disableProperty().bind(Validation.validGroup.not());
        btnRemover.disableProperty().bind(tblSubgrupo.getSelectionModel().selectedItemProperty().isNull());
    }

    private void popularCmbGrupo() {
        ObservableList<String> nomesProdutos = FXCollections.observableArrayList();
        ArrayList<Object> listProduto = new GrupoDao().consultarTodos(); //Aqui você tem uma lista de todos seu produtos
        for (int i = 0; i < listProduto.size(); i++) {
            Grupo grupo = (Grupo) listProduto.get(i);
            nomesProdutos.add(grupo.getGrupo());
        }
        cmbGrupo.getItems().addAll(nomesProdutos);
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
        popularCmbGrupo();
        popularCmbEstado();
        cmbEstado.getSelectionModel().selectFirst();
    }
}
