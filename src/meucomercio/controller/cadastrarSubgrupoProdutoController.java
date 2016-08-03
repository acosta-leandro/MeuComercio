package meucomercio.controller;

import entidades.Subgrupo;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;
import javafx.application.Application;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by leandro on 04/07/16.
 */
public class cadastrarSubgrupoProdutoController extends Application {

    @FXML
    private TableView<Subgrupo> tblGrupo;

    @FXML
    private TableColumn<Subgrupo, String> tblColId;

    @FXML
    private TableColumn<Subgrupo, String> tblColSubgrupo;

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
    private Label lblSubgrupoId;

    @FXML
    private TextField tfdId;

    @FXML
    private TextField tfdSubGrupo;

    @FXML
    private TextField tfdPesquisa;

    @FXML
    private TitledPane titledPane;

    private List<Subgrupo> listSubgrupo;

    private ObservableList<Subgrupo> observableListSubgrupo;

    @Override
    public void start(Stage primaryStage) throws Exception {
        
    }

}
