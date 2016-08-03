package meucomercio.controller;

import meucomercio.dao.CategoriaDao;
import meucomercio.entidades.Categoria;
import meucomercio.entidades.Sequence;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.BooleanBinding;

/**
 * Created by leandro on 04/07/16.
 */
public class cadastrarCategoriaProdutoController implements Initializable {

    CategoriaDao categoriaDao = new CategoriaDao();
    Sequence sequence = new Sequence();
    private static principalController princCont = principalController.getInstance();

    @FXML
    private TableView<Categoria> tblCategoria;

    @FXML
    private TableColumn<Categoria, Integer> tblColId;

    @FXML
    private TableColumn<Categoria, String> tblColCategoria;

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
    private Label lblCategoriaId;

    @FXML
    private Label lblId;

    @FXML
    private TextField tfdCategoria;

    @FXML
    private TextField tfdPesquisa;

    @FXML
    private TitledPane titledPane;

    private List<Categoria> listCategoria;

    private ObservableList<Categoria> observableListCategoria;

    @FXML
    private void handleBtnNovo() {
    }

    @FXML
    private void handleBtnEditar() {
        tfdCategoria.setEditable(false);
        System.out.println("aaaaa");
    }

    @FXML
    private void handleBtnPesquisar() {

        // if (tfdPesquisa.getText().equals("null")) {
        ArrayList categorias = categoriaDao.consultarTodos();
        for (int i = 0; i < categorias.size(); i++) {
            Categoria tmpCategoria = (Categoria) categorias.get(i);
            System.out.println(tmpCategoria.getCategoria());
            System.out.println(tmpCategoria.getId());
        }
        ObservableList<Categoria> listCategorias = FXCollections.observableArrayList(categorias);
        tblCategoria.setItems(listCategorias);
        //      } else {
//
        //}
    }

    @FXML
    private void handleBtnRemover() {
    }

    @FXML
    private void handleBtnSalvar() {
        Categoria categoria = new Categoria();
        categoria.setCategoria(tfdCategoria.getText());
        categoriaDao.salvar(categoria);
        handleBtnPesquisar();
    }

    @FXML
    private void handleBtnCancelar() {
        tblCategoria.getSelectionModel().clearSelection();
        lblId.setText("X");
        tfdCategoria.setText("");
        tblCategoria.setDisable(false);
        handleBtnPesquisar();
    }

    @FXML
    private void handleBtnAtualizar() {
        tblCategoria.setDisable(false);
    }

    @FXML
    private void handleBtnFechar() {
        princCont.fecharTittledPane("cadastrarCategoriaProduto");
    }

    private void configuraColunas() {
        tblColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblColCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        handleBtnPesquisar();
    }

    // configura a lógica da tela
    private void configuraBindings() {
        // Verifica se os campos Obrigatorios estão vazios
        BooleanBinding camposObrigatorios = tfdCategoria.textProperty().isEmpty();
        // indica se há algo selecionado na tabela
        BooleanBinding algoSelecionado = tblCategoria.getSelectionModel().selectedItemProperty().isNull();
        // alguns botões só são habilitados se algo foi selecionado na tabela
        btnRemover.disableProperty().bind(algoSelecionado);
        btnAtualizar.disableProperty().bind(algoSelecionado);
        // o botão salvar/cancelar só é habilitado se as informações foram preenchidas e não tem nada na tela
        btnSalvar.disableProperty().bind(algoSelecionado.not().or(camposObrigatorios));
        btnCancelar.disableProperty().bind(camposObrigatorios);
        // quando algo é selecionado na tabela, preenchemos os campos de entrada com os valores para o 
        tblCategoria.getSelectionModel().selectedItemProperty().addListener(new javafx.beans.value.ChangeListener<Categoria>() {
            @Override
            public void changed(ObservableValue<? extends Categoria> observable, Categoria oldValue, Categoria newValue) {
                if (newValue != null) {
                    tfdCategoria.textProperty().bindBidirectional(newValue.categoriaProperty());
                    lblId.textProperty().bind(Bindings.convert(newValue.idProperty()));
                    tblCategoria.setDisable(true);
                } else {
                    lblId.textProperty().unbind();
                    tfdCategoria.textProperty().unbind();
                    tfdCategoria.setText("");
                    lblId.setText("X");
                }
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configuraColunas();
        configuraBindings();
        //pegando evento de click tabela

    }

}
