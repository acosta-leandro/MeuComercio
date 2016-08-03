package controller;

import entidades.Grupo;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

/**
 * Created by leandro on 04/07/16.
 */
public class cadastrarGrupoProdutoController {

    @FXML
    private TableView<Grupo> tblGrupo;

    @FXML
    private TableColumn<Grupo, String> tblColId;

    @FXML
    private TableColumn<Grupo, String> tblColGrupo;

    @FXML
    private Button btnNovo;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnPesquisar;

    @FXML
    private Button btnLimpar;

    @FXML
    private Button btnRemover;

    @FXML
    private Button btnConfirmar;

    @FXML
    private Button btnCancelar;

    @FXML
    private Label lblGrupoId;

    @FXML
    private TextField tfdId;

    @FXML
    private TextField tfdGrupo;

    @FXML
    private TextField tfdPesquisa;

    @FXML
    private TitledPane titledPane;

    private List<Grupo> listGrupo;

    private ObservableList<Grupo> observableListGrupo;



}
