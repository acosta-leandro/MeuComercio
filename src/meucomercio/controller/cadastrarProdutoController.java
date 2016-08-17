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
import javafx.beans.binding.BooleanBinding;
import javafx.scene.control.Alert.AlertType;
import meucomercio.dao.GrupoDao;
import meucomercio.entidades.Grupo;

/**
 * Created by leandro on 04/07/16.
 */
public class cadastrarProdutoController implements Initializable {

    SubgrupoDao subgrupoDao = new SubgrupoDao();
    Sequence sequence = new Sequence();
    private static principalController princCont = principalController.getInstance();
    private Subgrupo subgrupo = new Subgrupo();
    private List<Subgrupo> listSubgrupo;
    private ObservableList<Subgrupo> observableListSubgrupo;

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
    private Button btnAtualizar;
    @FXML
    private Button btnSalvar;
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
    private void handleBtnSalvar() {
        Subgrupo subgrupo = new Subgrupo();
        subgrupo.setSubgrupo(tfdSubgrupo.getText());
        Grupo grupo = (Grupo) new GrupoDao().consultarNome(cmbGrupo.getSelectionModel().getSelectedItem().toString());
        subgrupo.setGrupoId(grupo.getId());
        subgrupoDao.salvar(subgrupo);
        handleBtnPesquisar();
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
    private void handleBtnAtualizar() {
        tblSubgrupo.setDisable(false);
        btnPesquisar.setDisable(false);
        subgrupoDao.atualizar(subgrupo);
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
        // Verifica se os campos Obrigatorios estão vazios
        BooleanBinding camposObrigatorios = tfdSubgrupo.textProperty().isEmpty();
        // indica se há algo selecionado na tabela
        BooleanBinding algoSelecionado = tblSubgrupo.getSelectionModel().selectedItemProperty().isNull();
        // alguns botões só são habilitados se algo foi selecionado na tabela
        btnRemover.disableProperty().bind(algoSelecionado);
        btnAtualizar.disableProperty().bind(algoSelecionado);
        // o botão salvar/cancelar só é habilitado se as informações foram preenchidas e não tem nada na tela
        btnSalvar.disableProperty().bind(algoSelecionado.not().or(camposObrigatorios));
        btnCancelar.disableProperty().bind(camposObrigatorios);
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

    private void popularCmbGrupo() {
        ObservableList<String> nomesProdutos = FXCollections.observableArrayList();
        ArrayList<Object> listProduto = new GrupoDao().consultarTodos(); //Aqui você tem uma lista de todos seu produtos
        for (int i = 0; i < listProduto.size(); i++) {
            Grupo grupo = (Grupo) listProduto.get(i);
            nomesProdutos.add(grupo.getGrupo());
        }
        cmbGrupo.getItems().addAll(nomesProdutos);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configuraColunas();
        configuraBindings();
        popularCmbGrupo();
    }
}
