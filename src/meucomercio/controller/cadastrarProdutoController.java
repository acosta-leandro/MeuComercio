package meucomercio.controller;

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
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import jidefx.scene.control.decoration.DecorationPane;
import meucomercio.apoio.Validation;
import meucomercio.dao.BloqueioDao;
import meucomercio.dao.CategoriaDao;
import meucomercio.dao.GrupoDao;
import meucomercio.dao.ProdutoDao;
import meucomercio.dao.SubgrupoDao;
import meucomercio.dao.TipoDao;
import meucomercio.dao.UnMedidaDao;
import meucomercio.entidades.Bloqueio;
import meucomercio.entidades.Categoria;
import meucomercio.entidades.Grupo;
import meucomercio.entidades.Produto;
import meucomercio.entidades.Subgrupo;
import meucomercio.entidades.Tipo;
import meucomercio.entidades.UnMedida;

/**
 * Created by leandro on 04/07/16.
 */
public class cadastrarProdutoController implements Initializable {

    ProdutoDao produtoDao = new ProdutoDao();
    Sequence sequence = new Sequence();
    private static principalController princCont = principalController.getInstance();
    private Produto produto = new Produto();
    private List<Produto> listProdutos;
    private ObservableList<Produto> observableListProduto;
    private ArrayList<Object> listGrupos = new GrupoDao().consultarTodos();
    private ArrayList<Object> listCategorias = new CategoriaDao().consultarTodos();
    private ArrayList<Object> listSubgrupos = new SubgrupoDao().consultarTodos();
    private ArrayList<Object> listTipos = new TipoDao().consultarTodos();
    private ArrayList<Object> listBloqueios = new BloqueioDao().consultarTodos();
    private ArrayList<Object> listUnMedidas = new UnMedidaDao().consultarTodos();
    private boolean atualizando = false;

    @FXML
    private TableView<Produto> tblProduto;
    @FXML
    private TableColumn<Produto, String> tblColId;
    @FXML
    private TableColumn<Produto, String> tblCId;
    @FXML
    private TableColumn<Produto, String> tblCProduto;
    @FXML
    private TableColumn<Produto, String> tblCValor;
    @FXML
    private TableColumn<Produto, String> tblCCusto;
    @FXML
    private TableColumn<Produto, String> tblCCategoria;
    @FXML
    private TableColumn<Produto, String> tblCGrupo;
    @FXML
    private TableColumn<Produto, String> tblCSubgrupo;
    @FXML
    private TableColumn<Produto, String> tblCTipo;
    @FXML
    private TableColumn<Produto, String> tblCBloqueio;
    @FXML
    private TableColumn<Produto, String> tblCUn;
    @FXML
    private TableColumn<Produto, String> tblCUltCusto;
    @FXML
    private TableColumn<Produto, String> tblCEstMin;
    @FXML
    private TableColumn<Produto, String> tblCEstMax;
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
    private Label lblId;
    @FXML
    private TextField tfdProduto;
    @FXML
    private TextField tfdCusto;
    @FXML
    private TextField tfdUltCusto;
    @FXML
    private TextField tfdValor;
    @FXML
    private TextField tfdEstMin;
    @FXML
    private TextField tfdEstMax;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tabProcurar;
    @FXML
    private Tab tabGeral;
    @FXML
    private ComboBox cmbGrupo;
    @FXML
    private ComboBox cmbSubgrupo;
    @FXML
    private ComboBox cmbCategoria;
    @FXML
    private ComboBox cmbTipo;
    @FXML
    private ComboBox cmbPGrupo;
    @FXML
    private ComboBox cmbPSubgrupo;
    @FXML
    private ComboBox cmbPCategoria;
    @FXML
    private ComboBox cmbPTipo;
    @FXML
    private ComboBox cmbPBloqueio;
    @FXML
    private ComboBox cmbBloqueio;
    @FXML
    private ComboBox cmbUnMedida;
    @FXML
    private TextField tfdPProduto;
    @FXML
    private AnchorPane root;
    @FXML
    private AnchorPane anchor;

    @FXML
    private void handleBtnPesquisar() {

        ArrayList produtos = new ArrayList();

        if (cmbPCategoria.getSelectionModel().getSelectedIndex() == -1 && cmbPGrupo.getSelectionModel().getSelectedIndex() == -1
                && cmbPSubgrupo.getSelectionModel().getSelectedIndex() == -1 && cmbPTipo.getSelectionModel().getSelectedIndex() == -1
                && tfdPProduto.getText().equals("")) {
            produtos = produtoDao.consultarTodos();
        } else {
            String sql = "SELECT * FROM produto";
            if (cmbPCategoria.getSelectionModel().getSelectedIndex() != -1 || cmbPGrupo.getSelectionModel().getSelectedIndex() != -1
                    || cmbPSubgrupo.getSelectionModel().getSelectedIndex() != -1 || cmbPTipo.getSelectionModel().getSelectedIndex() != -1 || !tfdPProduto.getText().equals("")) {
                sql = sql + " WHERE ";
            }
            if (cmbPCategoria.getSelectionModel().getSelectedIndex() != -1) {
                Categoria tmpCategoria = (Categoria) listCategorias.get(cmbPCategoria.getSelectionModel().getSelectedIndex());
                int categoriaId = Integer.parseInt(tmpCategoria.getId());
                sql = sql + "categoria_id = " + categoriaId;
            }
            if (cmbPGrupo.getSelectionModel().getSelectedIndex() != -1) {
                Grupo tmpGrupo = (Grupo) listGrupos.get(cmbPGrupo.getSelectionModel().getSelectedIndex());
                int grupoId = Integer.parseInt(tmpGrupo.getId());
                if (sql.equals("SELECT * FROM produto WHERE ")) {
                    sql = sql + "grupo_id = " + grupoId;
                } else {
                    sql = sql + " AND grupo_id = " + grupoId;
                }
            }
            if (cmbPSubgrupo.getSelectionModel().getSelectedIndex() != -1) {
                Subgrupo tmpSubgrupo = (Subgrupo) listSubgrupos.get(cmbPSubgrupo.getSelectionModel().getSelectedIndex());
                int subgrupoId = Integer.parseInt(tmpSubgrupo.getId());
                if (sql.equals("SELECT * FROM produto WHERE ")) {
                    sql = sql + "subgrupo_id = " + subgrupoId;
                } else {
                    sql = sql + " AND subgrupo_id = " + subgrupoId;
                }
            }
            if (cmbPTipo.getSelectionModel().getSelectedIndex() != -1) {
                Tipo tmpTipo = (Tipo) listTipos.get(cmbPTipo.getSelectionModel().getSelectedIndex());
                int tipoId = Integer.parseInt(tmpTipo.getId());
                if (sql.equals("SELECT * FROM produto WHERE ")) {
                    sql = sql + "tipo_id = " + tipoId;
                } else {
                    sql = sql + " AND tipo_id = " + tipoId;
                }
            }
            if (!tfdPProduto.getText().equals("")) {
                System.out.println("sql" + sql);
                if (sql.equals("SELECT * FROM produto WHERE ")) {
                    sql = sql + "produto ilike '%" + tfdPProduto.getText() + "%';";
                } else {
                    sql = sql + " AND produto ilike '%" + tfdPProduto.getText() + "%';";
                }
            }
            System.out.println("sql: " + sql);
            produtos = produtoDao.consultarTodos(sql);
        }
        ObservableList<Produto> tmplistProdutos = FXCollections.observableArrayList(produtos);
        tblProduto.setItems(tmplistProdutos);
    }

    @FXML
    private void handleBtnRemover() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Excluir Produto");
        alert.setHeaderText("Deseja excluir '" + produto.getProduto() + "' ?");
        alert.setContentText("Tem certeza?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            produtoDao.excluir(Integer.valueOf(produto.getId()));
            handleBtnCancelar();
            handleBtnPesquisar();
            tabPane.getSelectionModel().select(tabProcurar);
        }
    }

    @FXML
    private void handleBtnConfirmar() {
        if (atualizando) {
            produtoDao.atualizar(produto);
            atualizando = false;
        } else {
            produtoDao.salvar(produto);
        }
        handleBtnPesquisar();
        handleBtnCancelar();
        tabPane.getSelectionModel().select(tabProcurar);
    }

    @FXML
    private void handleBtnCancelar() {
        tblProduto.getSelectionModel().clearSelection();
        tblProduto.setDisable(false);
        btnPesquisar.setDisable(false);
        handleBtnPesquisar();
        limparTabGeral();
        atualizando = false;
    }

    @FXML
    private void handleBtnFechar() {
        princCont.fecharTittledPane("cadastrarProduto");
    }

    @FXML
    private void handleBtnLimpar() {
        tblProduto.getItems().clear();
        cmbBloqueio.getSelectionModel().clearSelection();
        cmbCategoria.getSelectionModel().clearSelection();
        cmbGrupo.getSelectionModel().clearSelection();
        cmbPBloqueio.getSelectionModel().clearSelection();
        cmbPCategoria.getSelectionModel().clearSelection();
        cmbPGrupo.getSelectionModel().clearSelection();
        cmbPSubgrupo.getSelectionModel().clearSelection();
        cmbPTipo.getSelectionModel().clearSelection();
        cmbSubgrupo.getSelectionModel().clearSelection();
        cmbTipo.getSelectionModel().clearSelection();
        cmbUnMedida.getSelectionModel().clearSelection();
        tfdPProduto.clear();
        tfdCusto.clear();
        tfdEstMax.clear();
        tfdEstMin.clear();
        tfdProduto.clear();
        tfdUltCusto.clear();
        tfdValor.clear();
    }

    private void configuraColunas() {
        tblCId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCProduto.setCellValueFactory(new PropertyValueFactory<>("produto"));
        tblCValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        tblCCusto.setCellValueFactory(new PropertyValueFactory<>("custo"));
        tblCCategoria.setCellValueFactory(new PropertyValueFactory<>("categoriaNome"));
        tblCGrupo.setCellValueFactory(new PropertyValueFactory<>("grupoNome"));
        tblCSubgrupo.setCellValueFactory(new PropertyValueFactory<>("subgrupoNome"));
        tblCTipo.setCellValueFactory(new PropertyValueFactory<>("tipoNome"));
        tblCBloqueio.setCellValueFactory(new PropertyValueFactory<>("bloqueioNome"));
        tblCUn.setCellValueFactory(new PropertyValueFactory<>("unidadeNome"));
        tblCUltCusto.setCellValueFactory(new PropertyValueFactory<>("ultCusto"));
        tblCEstMin.setCellValueFactory(new PropertyValueFactory<>("estMin"));
        tblCEstMax.setCellValueFactory(new PropertyValueFactory<>("estMax"));
    }

    // configura a lógica da tela
    private void configuraBindings() {
        //binds de campos
        produto.idProperty().bind(lblId.textProperty());
        produto.produtoProperty().bind(tfdProduto.textProperty());
        produto.custoProperty().bind(tfdCusto.textProperty());
        produto.ultCustoProperty().bind(tfdUltCusto.textProperty());
        produto.valorProperty().bind(tfdValor.textProperty());
        produto.estMaxProperty().bind(tfdEstMax.textProperty());
        produto.estMinProperty().bind(tfdEstMin.textProperty());

//bind combos 
        cmbGrupo.getSelectionModel().selectedItemProperty().addListener(new javafx.beans.value.ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    for (int i = 0; i <= listGrupos.size(); i++) {
                        Grupo grupo = (Grupo) listGrupos.get(i);
                        if (grupo.getGrupo().equals(cmbGrupo.getSelectionModel().getSelectedItem().toString())) {
                            produto.setGrupoId(grupo.getId());
                            break;
                        }
                    }
                }
            }
        });
        cmbSubgrupo.getSelectionModel().selectedItemProperty().addListener(new javafx.beans.value.ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    for (int i = 0; i <= listSubgrupos.size(); i++) {
                        Subgrupo subgrupo = (Subgrupo) listSubgrupos.get(i);
                        if (subgrupo.getSubgrupo().equals(cmbSubgrupo.getSelectionModel().getSelectedItem().toString())) {
                            produto.setSubgrupoId(subgrupo.getId());
                            break;
                        }
                    }
                }
            }
        });
        cmbCategoria.getSelectionModel().selectedItemProperty().addListener(new javafx.beans.value.ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    for (int i = 0; i <= listCategorias.size(); i++) {
                        Categoria categoria = (Categoria) listCategorias.get(i);
                        if (categoria.getCategoria().equals(cmbCategoria.getSelectionModel().getSelectedItem().toString())) {
                            produto.setCategoriaId(categoria.getId());
                            break;
                        }
                    }
                }
            }
        });
        cmbTipo.getSelectionModel().selectedItemProperty().addListener(new javafx.beans.value.ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    for (int i = 0; i <= listTipos.size(); i++) {
                        Tipo tipo = (Tipo) listTipos.get(i);
                        if (tipo.getTipo().equals(cmbTipo.getSelectionModel().getSelectedItem().toString())) {
                            produto.setTipoId(tipo.getId());
                            break;
                        }
                    }
                }
            }
        });
        cmbBloqueio.getSelectionModel().selectedItemProperty().addListener(new javafx.beans.value.ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    for (int i = 0; i <= listBloqueios.size(); i++) {
                        Bloqueio bloqueio = (Bloqueio) listBloqueios.get(i);
                        if (bloqueio.getBloqueio().equals(cmbBloqueio.getSelectionModel().getSelectedItem().toString())) {
                            produto.setBloqueioId(bloqueio.getId());
                            break;
                        }
                    }
                }
            }
        });
        cmbUnMedida.getSelectionModel().selectedItemProperty().addListener(new javafx.beans.value.ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    for (int i = 0; i <= listUnMedidas.size(); i++) {
                        UnMedida unMedida = (UnMedida) listUnMedidas.get(i);
                        if (unMedida.getNome().equals(cmbUnMedida.getSelectionModel().getSelectedItem().toString())) {
                            produto.setUnidadeId(unMedida.getId());
                            break;
                        }
                    }
                }
            }
        });
//        Remover R$ quando for alterar valor
//        tfdCusto.focusedProperty().addListener(new ChangeListener<Boolean>() {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
//                if (newPropertyValue) { //ganhou foco
//                    if (!tfdCusto.getText().isEmpty() && tfdCusto.getText(0, 2).equals("R$")) {
//                        tfdCusto.setText(tfdCusto.getText(2, tfdCusto.getLength()));
//                    }
//                }
//            }
//        });
//        tfdUltCusto.focusedProperty().addListener(new ChangeListener<Boolean>() {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
//                if (newPropertyValue) { //ganhou foco
//                    if (!tfdUltCusto.getText().isEmpty() && tfdUltCusto.getText(0, 2).equals("R$")) {
//                        tfdUltCusto.setText(tfdUltCusto.getText(2, tfdUltCusto.getLength()));
//                    }
//                }
//            }
//        });
//        tfdValor.focusedProperty().addListener(new ChangeListener<Boolean>() {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
//                if (newPropertyValue) { //ganhou foco
//                    if (!tfdValor.getText().isEmpty() && tfdValor.getText(0, 2).equals("R$")) {
//                        tfdValor.setText(tfdValor.getText(2, tfdValor.getLength()));
//                    }
//                }
//            }
//        });

        // indica se há algo selecionado na tabela
        BooleanBinding algoSelecionado = tblProduto.getSelectionModel().selectedItemProperty().isNull();
        // alguns botões só são habilitados se algo foi selecionado na tabela
        // btnRemover.disableProperty().bind(algoSelecionado);

        // quando algo é selecionado na tabela, preenchemos os campos de entrada com os valores para o 
        tblProduto.getSelectionModel().selectedItemProperty().addListener(new javafx.beans.value.ChangeListener<Produto>() {
            @Override
            public void changed(ObservableValue<? extends Produto> observable, Produto oldValue, Produto newValue) {
                if (newValue != null) {
                    atualizando = true;
                    tabPane.getSelectionModel().select(tabGeral);
                    lblId.textProperty().bind(Bindings.convert(newValue.idProperty()));
                    tfdProduto.textProperty().bindBidirectional(newValue.produtoProperty());
                    tfdCusto.textProperty().bindBidirectional(newValue.custoProperty());
                    tfdUltCusto.textProperty().bindBidirectional(newValue.ultCustoProperty());
                    tfdValor.textProperty().bindBidirectional(newValue.valorProperty());
                    tfdEstMax.textProperty().bindBidirectional(newValue.estMaxProperty());
                    tfdEstMin.textProperty().bindBidirectional(newValue.estMinProperty());
                    cmbGrupo.getSelectionModel().select(newValue.getGrupoNome());
                    cmbSubgrupo.getSelectionModel().select(newValue.getSubgrupoNome());
                    cmbCategoria.getSelectionModel().select(newValue.getCategoriaNome());
                    cmbTipo.getSelectionModel().select(newValue.getTipoNome());
                    cmbBloqueio.getSelectionModel().select(newValue.getBloqueioNome());
                    cmbUnMedida.getSelectionModel().select(newValue.getUnidadeNome());
                    tblProduto.setDisable(true);
                    btnPesquisar.setDisable(true);
                } else {
                    atualizando = false;
                    lblId.textProperty().unbind();
                    tfdProduto.textProperty().unbind();
                    tfdCusto.textProperty().unbind();
                    tfdUltCusto.textProperty().unbind();
                    tfdValor.textProperty().unbind();
                    tfdEstMax.textProperty().unbind();
                    tfdEstMin.textProperty().unbind();
                    tfdProduto.setText("");
                    tfdCusto.setText("");
                    tfdUltCusto.setText("");
                    tfdValor.setText("");
                    tfdEstMax.setText("");
                    tfdEstMin.setText("");
                    lblId.setText("X");
                }
            }
        });
        cmbGrupo.getSelectionModel().selectedItemProperty().addListener(new javafx.beans.value.ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                cmbSubgrupo.getItems().clear();
                popularCmbSubgrupo((String) cmbGrupo.getSelectionModel().getSelectedItem());
            }
        });

    }

    private void popularCmbGrupo() {
        ObservableList<String> nomesGrupos = FXCollections.observableArrayList();

        for (int i = 0; i < listGrupos.size(); i++) {
            Grupo grupo = (Grupo) listGrupos.get(i);
            nomesGrupos.add(grupo.getGrupo());
        }
        cmbPGrupo.getItems().addAll(nomesGrupos);
        cmbGrupo.getItems().addAll(nomesGrupos);
    }

    private void popularCmbCategoria() {
        ObservableList<String> nomesCategorias = FXCollections.observableArrayList();
        for (int i = 0; i < listCategorias.size(); i++) {
            Categoria categoria = (Categoria) listCategorias.get(i);
            nomesCategorias.add(categoria.getCategoria());
        }
        cmbPCategoria.getItems().addAll(nomesCategorias);
        cmbCategoria.getItems().addAll(nomesCategorias);
    }

    private void popularCmbSubgrupo(String grupo) {
        ObservableList<String> nomesSubgrupos = FXCollections.observableArrayList();
        for (int i = 0; i < listSubgrupos.size(); i++) {
            Subgrupo subGrupo = (Subgrupo) listSubgrupos.get(i);
            if (subGrupo.getGrupoNome().equalsIgnoreCase(grupo)) {
                nomesSubgrupos.add(subGrupo.getSubgrupo());
            }
        }
        cmbPSubgrupo.getItems().addAll(nomesSubgrupos);
        cmbSubgrupo.getItems().addAll(nomesSubgrupos);
    }

    private void popularCmbTipo() {
        ObservableList<String> nomesTipos = FXCollections.observableArrayList();
        for (int i = 0; i < listTipos.size(); i++) {
            Tipo tipo = (Tipo) listTipos.get(i);
            nomesTipos.add(tipo.getTipo());
        }
        cmbPTipo.getItems().addAll(nomesTipos);
        cmbTipo.getItems().addAll(nomesTipos);
    }

    private void popularCmbBloqueio() {
        ObservableList<String> nomesBloqueios = FXCollections.observableArrayList();
        for (int i = 0; i < listBloqueios.size(); i++) {
            Bloqueio bloqueio = (Bloqueio) listBloqueios.get(i);
            nomesBloqueios.add(bloqueio.getBloqueio());
        }
        cmbPBloqueio.getItems().addAll(nomesBloqueios);
        cmbBloqueio.getItems().addAll(nomesBloqueios);
    }

    private void popularCmbUnMedida() {
        ObservableList<String> nomesUnMedida = FXCollections.observableArrayList();
        for (int i = 0; i < listUnMedidas.size(); i++) {
            UnMedida unMedida = (UnMedida) listUnMedidas.get(i);
            nomesUnMedida.add(unMedida.getNome());
        }
        cmbUnMedida.getItems().addAll(nomesUnMedida);
    }

    private void limparTabGeral() {
        lblId.setText("X");
        tfdProduto.setText("");
        tfdCusto.setText("");
        tfdUltCusto.setText("");
        tfdValor.setText("");
        tfdEstMax.setText("");
        tfdEstMin.setText("");
        cmbGrupo.getSelectionModel().clearSelection();
        cmbSubgrupo.getSelectionModel().clearSelection();
        cmbCategoria.getSelectionModel().clearSelection();
        cmbTipo.getSelectionModel().clearSelection();
        cmbBloqueio.getSelectionModel().clearSelection();
        cmbUnMedida.getSelectionModel().clearSelection();
    }

    private void validar() {
        DecorationPane decorationPane = new DecorationPane(anchor);
        root.getChildren().add(decorationPane);
        Validation.validate(cmbBloqueio);
        Validation.validate(cmbCategoria);
        Validation.validate(cmbGrupo);
        Validation.validate(cmbSubgrupo);
        Validation.validate(cmbTipo);
        Validation.validate(cmbUnMedida);
        Validation.validate(tfdProduto, Validation.VARCHAR25);
        Validation.validate(tfdCusto, Validation.MONEY);
        Validation.validate(tfdEstMax, Validation.VARCHAR25);
        Validation.validate(tfdEstMin, Validation.VARCHAR25);
        Validation.validate(tfdUltCusto, Validation.MONEY);
        Validation.validateOr(tfdValor, Validation.MONEY, Validation.DOUBLE);

    }

    private void liberarBotoes() {
        btnConfirmar.disableProperty().bind(Validation.validGroup.not());
        // btnRemover.disableProperty().bind(tblProduto.getSelectionModel().selectedItemProperty().isNull());
        btnRemover.setDisable(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configuraColunas();
        configuraBindings();
        popularCmbGrupo();
        popularCmbCategoria();
//        popularCmbSubgrupo();
        popularCmbTipo();
        popularCmbBloqueio();
        popularCmbUnMedida();
        validar();
        liberarBotoes();
    }
}
