package meucomercio.controller;

import meucomercio.dao.BloqueioDao;
import meucomercio.entidades.Bloqueio;
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

/**
 * Created by leandro on 04/07/16.
 */
public class cadastrarBloqueioProdutoController implements Initializable {

    BloqueioDao bloqueioDao = new BloqueioDao();
    Sequence sequence = new Sequence();
    private static principalController princCont = principalController.getInstance();
    private Bloqueio bloqueio = new Bloqueio();
    private List<Bloqueio> listBloqueio;
    private ObservableList<Bloqueio> observableListBloqueio;

    @FXML
    private TableView<Bloqueio> tblBloqueio;
    @FXML
    private TableColumn<Bloqueio, Integer> tblColId;
    @FXML
    private TableColumn<Bloqueio, String> tblColBloqueio;
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
    private Label lblBloqueioId;
    @FXML
    private Label lblId;
    @FXML
    private TextField tfdBloqueio;
    @FXML
    private TextField tfdPesquisa;
    @FXML
    private TitledPane titledPane;


    @FXML
    private void handleBtnPesquisar() {
        ArrayList bloqueios = new ArrayList();
        if (tfdPesquisa.getText().equals("")) {
            bloqueios = bloqueioDao.consultarTodos();
        } else {
            bloqueios = bloqueioDao.consultar(tfdPesquisa.getText());
        }
        for (int i = 0; i < bloqueios.size(); i++) {
            Bloqueio tmpBloqueio = (Bloqueio) bloqueios.get(i);
        }
        ObservableList<Bloqueio> listBloqueios = FXCollections.observableArrayList(bloqueios);
        tblBloqueio.setItems(listBloqueios);
    }

    @FXML
    private void handleBtnRemover() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Excluir Bloqueio");
        alert.setHeaderText("Deseja excluir '" + bloqueio.getBloqueio() + "' ?");
        alert.setContentText("Tem certeza?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            bloqueioDao.excluir(Integer.valueOf(bloqueio.getId()));
            handleBtnCancelar();
            handleBtnPesquisar();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    @FXML
    private void handleBtnSalvar() {
        Bloqueio bloqueio = new Bloqueio();
        bloqueio.setBloqueio(tfdBloqueio.getText());
        bloqueioDao.salvar(bloqueio);
        handleBtnPesquisar();
        handleBtnCancelar();
    }

    @FXML
    private void handleBtnCancelar() {
        tblBloqueio.getSelectionModel().clearSelection();
        lblId.setText("X");
        tfdBloqueio.setText("");
        tblBloqueio.setDisable(false);
        btnPesquisar.setDisable(false);
        handleBtnPesquisar();
    }

    @FXML
    private void handleBtnAtualizar() {
        tblBloqueio.setDisable(false);
        btnPesquisar.setDisable(false);
        bloqueioDao.atualizar(bloqueio);
        handleBtnPesquisar();
    }

    @FXML
    private void handleBtnFechar() {
        princCont.fecharTittledPane("cadastrarBloqueioProduto");
    }

    private void configuraColunas() {
        tblColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblColBloqueio.setCellValueFactory(new PropertyValueFactory<>("bloqueio"));
        handleBtnPesquisar();
    }

    // configura a lógica da tela
    private void configuraBindings() {
        //bids de campos
        bloqueio.idProperty().bind(lblId.textProperty());
        bloqueio.bloqueioProperty().bind(tfdBloqueio.textProperty());
        // Verifica se os campos Obrigatorios estão vazios
        BooleanBinding camposObrigatorios = tfdBloqueio.textProperty().isEmpty();
        // indica se há algo selecionado na tabela
        BooleanBinding algoSelecionado = tblBloqueio.getSelectionModel().selectedItemProperty().isNull();
        // alguns botões só são habilitados se algo foi selecionado na tabela
        btnRemover.disableProperty().bind(algoSelecionado);
        btnAtualizar.disableProperty().bind(algoSelecionado);
        // o botão salvar/cancelar só é habilitado se as informações foram preenchidas e não tem nada na tela
        btnSalvar.disableProperty().bind(algoSelecionado.not().or(camposObrigatorios));
        btnCancelar.disableProperty().bind(camposObrigatorios);
        // quando algo é selecionado na tabela, preenchemos os campos de entrada com os valores para o 
        tblBloqueio.getSelectionModel().selectedItemProperty().addListener(new javafx.beans.value.ChangeListener<Bloqueio>() {
            @Override
            public void changed(ObservableValue<? extends Bloqueio> observable, Bloqueio oldValue, Bloqueio newValue) {
                if (newValue != null) {
                    tfdBloqueio.textProperty().bindBidirectional(newValue.bloqueioProperty());
                    lblId.textProperty().bind(Bindings.convert(newValue.idProperty()));
                    tblBloqueio.setDisable(true);
                    btnPesquisar.setDisable(true);
                } else {
                    lblId.textProperty().unbind();
                    tfdBloqueio.textProperty().unbind();
                    tfdBloqueio.setText("");
                    lblId.setText("X");
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
