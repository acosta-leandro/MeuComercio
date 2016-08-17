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
    private Button btnAtualizar;
    @FXML
    private Button btnSalvar;
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
    private void handleBtnSalvar() {
        UnMedida unMedida = new UnMedida();
        unMedida.setNome(tfdNome.getText());
        unMedida.setSigla(tfdSigla.getText());
        unMedidaDao.salvar(unMedida);
        handleBtnPesquisar();
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
    private void handleBtnAtualizar() {
        tblUnMedida.setDisable(false);
        btnPesquisar.setDisable(false);
        unMedidaDao.atualizar(unMedida);
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
        handleBtnPesquisar();
    }

    // configura a lógica da tela
    private void configuraBindings() {
        //bids de campos
        unMedida.idProperty().bind(lblId.textProperty());
        unMedida.nomeProperty().bind(tfdNome.textProperty());
         unMedida.siglaProperty().bind(tfdSigla.textProperty());
        // Verifica se os campos Obrigatorios estão vazios
        BooleanBinding camposObrigatorios = tfdNome.textProperty().isEmpty().or(tfdSigla.textProperty().isEmpty());
        // indica se há algo selecionado na tabela
        BooleanBinding algoSelecionado = tblUnMedida.getSelectionModel().selectedItemProperty().isNull();
        // alguns botões só são habilitados se algo foi selecionado na tabela
        btnRemover.disableProperty().bind(algoSelecionado);
        btnAtualizar.disableProperty().bind(algoSelecionado);
        // o botão salvar/cancelar só é habilitado se as informações foram preenchidas e não tem nada na tela
        btnSalvar.disableProperty().bind(algoSelecionado.not().or(camposObrigatorios));
        btnCancelar.disableProperty().bind(camposObrigatorios);
        // quando algo é selecionado na tabela, preenchemos os campos de entrada com os valores para o 
        tblUnMedida.getSelectionModel().selectedItemProperty().addListener(new javafx.beans.value.ChangeListener<UnMedida>() {
            @Override
            public void changed(ObservableValue<? extends UnMedida> observable, UnMedida oldValue, UnMedida newValue) {
                if (newValue != null) {
                    tfdNome.textProperty().bindBidirectional(newValue.nomeProperty());
                    lblId.textProperty().bind(Bindings.convert(newValue.idProperty()));
                    tfdSigla.textProperty().bindBidirectional(newValue.siglaProperty());
                    tblUnMedida.setDisable(true);
                    btnPesquisar.setDisable(true);
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
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configuraColunas();
        configuraBindings();
    }
}
