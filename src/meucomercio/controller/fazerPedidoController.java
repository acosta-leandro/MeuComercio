package meucomercio.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import meucomercio.MeuComercio;
import static meucomercio.MeuComercio.stage;
import meucomercio.dao.CategoriaDao;
import meucomercio.dao.ComandaDao;
import meucomercio.dao.FazerPedidoDao;
import meucomercio.dao.GrupoDao;
import meucomercio.dao.ProdutoDao;
import meucomercio.dao.SubgrupoDao;
import meucomercio.entidades.Categoria;
import meucomercio.entidades.Comanda;
import meucomercio.entidades.Grupo;
import meucomercio.entidades.Produto;
import meucomercio.entidades.Subgrupo;

public class fazerPedidoController implements Initializable {

    private static fazerPedidoController instance;

    public fazerPedidoController() {
        instance = this;
    }

    public static fazerPedidoController getInstance() {
        return instance;
    }

    ComandaDao comandaDao = new ComandaDao();
    GrupoDao grupoDao = new GrupoDao();
    SubgrupoDao subgrupoDao = new SubgrupoDao();
    ProdutoDao produtoDao = new ProdutoDao();
    FazerPedidoDao fazerPedido = new FazerPedidoDao();
    CategoriaDao categoriaDao = new CategoriaDao();
    private static principalController princCont = principalController.getInstance();

    Stage stagePopUp = new Stage();

    @FXML
    private TitledPane titledPane;

    @FXML
    private Button btnFechar;

    @FXML
    private Button btnLiberarComanda;

    @FXML
    private Button btnF1NovaComanda;

    @FXML
    private Button btnF2AlterarComanda;

    @FXML
    private Button btnInserir;

    @FXML
    private Button btnCancelarProduto;

    @FXML
    private Button btnF5ListarTodos;

    @FXML
    private GridPane gridComandas;

    @FXML
    private GridPane gridProdutos;

    @FXML
    private AnchorPane anchorComandas;

    @FXML
    private AnchorPane anchorProdutos;

    @FXML
    private TextField tfdComanda;

    @FXML
    private TextField tfdProduto;

    @FXML
    private TextArea txtComanda;

    @FXML
    private TextArea txtProduto;

    @FXML
    private TextArea txtId;

    @FXML
    private TableView<Produto> tblProdutos;

    @FXML
    private TableColumn<Produto, String> tblColId;

    @FXML
    private TableColumn<Produto, String> tblColProduto;

    @FXML
    private void handleBtnF1NovaComanda() throws IOException {

    }

    @FXML
    private void handleBtnF2AlterarComanda() throws IOException {

//
//        FXMLLoader loader = new FXMLLoader(MeuComercio.class.getResource("view/editarComanda.fxml"));
//        AnchorPane anchorPane = loader.load();
//        // Get the Controller from the FXMLLoader
//        editarComandaController controller = loader.getController();
//        controller.editarComanda(tblComandas.getSelectionModel().getSelectedItem());
//        // Set data in the controller
//        Scene scene = new Scene(anchorPane);
//        Stage stage = new Stage();
//        stage.setScene(scene);
//        stage.show();
    }

    @FXML
    private void handleBtnF5ListarTodos() throws IOException {
        gridProdutos.getChildren().clear();
        gridComandas.getChildren().clear();
        popularComandas();
        popularCategorias();
    }

    @FXML
    private void handleBtnInserir() {
        String ComandaTxt = tfdComanda.getText() + "\n";
        String ProdutoTxt = tfdProduto.getText() + "\n";
        String idProduto = tfdProduto.getText(0, 1);
        String idComanda = tfdComanda.getText(0, 1);
        String IdTxt = String.valueOf(fazerPedido.salvar(idComanda, idProduto)) + "\n";
        txtComanda.setText(ComandaTxt + txtComanda.getText());
        txtProduto.setText(ProdutoTxt + txtProduto.getText());
        txtId.setText(IdTxt + txtId.getText());
        tfdProduto.clear();
        tfdProduto.setDisable(false);
        tfdProduto.requestFocus();
    }

    @FXML
    private void handleBtnLiberarComanda() throws IOException {
        anchorComandas.setDisable(false);
        anchorProdutos.setDisable(false);
        tfdProduto.setText("");
        tfdComanda.setText("");
        tfdComanda.setDisable(false);
        handleBtnF5ListarTodos();
    }

    @FXML
    private void handleEnterTfdComanda() {
        Comanda c = (Comanda) comandaDao.consultarId(Integer.valueOf(tfdComanda.getText()));
        tfdComanda.setText(c.getId() + " - " + c.getNome());
//        anchorComandas.setDisable(true);
        tfdProduto.requestFocus();
        tfdComanda.setDisable(true);
    }

    @FXML
    private void handleEnterTfdProduto() {
        Produto p = (Produto) produtoDao.consultarId(Integer.valueOf(tfdProduto.getText()));
        tfdProduto.setText(p.getId() + " - " + p.getProduto());
//        anchorProdutos.setDisable(true);
        btnInserir.requestFocus();
        tfdProduto.setDisable(true);
    }

    @FXML
    private void handleBtnCancelarProduto() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MeuComercio.class.getResource("view/cancelarPedido.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        stagePopUp.setScene(new Scene(root1));
        stagePopUp.setTitle("Cancelar Pedido");
        stagePopUp.setResizable(false);
        stagePopUp.show();
    }

    @FXML
    private void handleBtnTodosItens() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MeuComercio.class.getResource("view/listarProdutos.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        stagePopUp.setScene(new Scene(root1));
        stagePopUp.setTitle("Listar Produtos");
        stagePopUp.setResizable(false);
        stagePopUp.show();
    }

    @FXML
    private void handleBtnFechar() {
        princCont.fecharTittledPane("fazerPedido");
    }

    private void popularComandas() {
        ArrayList comandas = comandaDao.consultarComandasAbertas();

        for (int i = 0; i < comandas.size(); i++) {
            Comanda c = (Comanda) comandas.get(i);
            System.out.println("a:" + c.getNome());
        }
        int colunas = 7;
        int linhas = verificarLinhas(comandas.size(), colunas);
        System.out.println("linas" + linhas);

        Button[][] btn = new Button[colunas][linhas];
        //para testar numero de casas
        int numero = 0;
        //loops para adicionar os buttons ao layout nas linhas do array
        for (int i = 0; i < colunas; i++) {
            //loops para adicionar os buttons ao layout nas colunas do array
            for (int j = 0; j < linhas; j++) {
                //Criar todos os botoes
                //preenche imagem nos restantes botoes para peças brancas
                String nome = "";
                if (numero < comandas.size()) {
                    Comanda c = (Comanda) comandas.get(numero);
                    nome = c.getId() + "\n" + c.getNome();
                } else {
                    break;
                }
                btn[i][j] = new Button("" + nome + "");
                btn[i][j].setDisable(false);
                //representa o tamanho de altura e largura de cada botao
                btn[i][j].setPrefSize(anchorComandas.getWidth() / colunas, anchorComandas.getHeight() / linhas);
                //adiciona todos os botoes a gridpane                
                gridComandas.add(btn[i][j], i, j);
                {
                    numero++;
                }
                //representa o evento relacionado com todos os botoes na gridpane
                btn[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent arg0) {
                        Button b = (Button) arg0.getSource();
                        tfdComanda.setText(b.getText());
                        tfdComanda.setDisable(true);
                    }
                }
                );
            }
        }

    }

    private void popularCategorias() {
        ArrayList categorias = categoriaDao.consultarTodosAtivos();

        for (int i = 0; i < categorias.size(); i++) {
            Categoria categoria = (Categoria) categorias.get(i);
            System.out.println("a:" + categoria.getCategoria());
        }
        int colunas = 4;
        int linhas = verificarLinhas(categorias.size(), colunas);
        System.out.println("linas" + linhas);

        Button[][] btn = new Button[colunas][linhas];
        //para testar numero de casas
        int numero = 0;
        //loops para adicionar os buttons ao layout nas linhas do array
        for (int i = 0; i < colunas; i++) {
            //loops para adicionar os buttons ao layout nas colunas do array
            for (int j = 0; j < linhas; j++) {
                //Criar todos os botoes
                //preenche imagem nos restantes botoes para peças brancas
                String nome = "";
                if (numero < categorias.size()) {
                    Categoria categoria = (Categoria) categorias.get(numero);
                    nome = categoria.getId() + "\n" + categoria.getCategoria();
                } else {
                    break;
                }
                btn[i][j] = new Button("" + nome + "");
                //representa o tamanho de altura e largura de cada botao
                btn[i][j].setPrefSize(anchorProdutos.getWidth() / colunas, anchorProdutos.getHeight() / linhas);

                //adiciona todos os botoes a gridpane                
                gridProdutos.add(btn[i][j], i, j);
                {
                    numero++;
                }
                //representa o evento relacionado com todos os botoes na gridpane
                btn[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent arg0) {
                        Button b = (Button) arg0.getSource();
                        int idCategoria = Integer.valueOf(String.valueOf(b.getText().charAt(0)));
                        gridProdutos.getChildren().clear();
                        popularProdutos(idCategoria);
                    }
                }
                );
            }
        }

    }

    private void popularProdutos(int idCategoria) {
        ArrayList produtos = produtoDao.consultarIdCategoria(idCategoria);

        for (int i = 0; i < produtos.size(); i++) {
            Produto p = (Produto) produtos.get(i);
            System.out.println("a:" + p.getProduto());
        }
        int colunas = 4;
        int linhas = verificarLinhas(produtos.size(), colunas);
        System.out.println("linas" + linhas);

        Button[][] btn = new Button[colunas][linhas];
        //para testar numero de casas
        int numero = 0;
        //loops para adicionar os buttons ao layout nas linhas do array
        for (int i = 0; i < colunas; i++) {
            //loops para adicionar os buttons ao layout nas colunas do array
            for (int j = 0; j < linhas; j++) {
                //Criar todos os botoes
                //preenche imagem nos restantes botoes para peças brancas
                String nome = "";
                if (numero < produtos.size()) {
                    Produto p = (Produto) produtos.get(numero);
                    nome = p.getId() + "\n" + p.getProduto() + "\n" + p.getValor();
                } else {
                    break;
                }
                btn[i][j] = new Button("" + nome + "");
                //representa o tamanho de altura e largura de cada botao
                btn[i][j].setPrefSize(anchorProdutos.getWidth() / colunas, anchorProdutos.getHeight() / linhas);
                //adiciona todos os botoes a gridpane                
                gridProdutos.add(btn[i][j], i, j);
                {
                    numero++;
                }
                //representa o evento relacionado com todos os botoes na gridpane
                btn[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent arg0) {
                        Button b = (Button) arg0.getSource();
                        tfdProduto.setText(b.getText());
                        gridProdutos.getChildren().clear();
                        popularCategorias();
                        btnInserir.requestFocus();
                    }
                }
                );
            }
        }

    }

    private void configurarSelecaoTabela() {
        tblProdutos.getSelectionModel().selectedItemProperty().addListener(new javafx.beans.value.ChangeListener<Produto>() {
            @Override
            public void changed(ObservableValue<? extends Produto> observable, Produto oldValue, Produto newValue) {
                if (newValue != null) {
                    Produto p = tblProdutos.getSelectionModel().getSelectedItem();
                    String strProd = p.getId() + " - " + p.getProduto();
                    tfdProduto.setText(strProd);
                }
            }
        });
    }

    public void selecionarProduto(String produto) {
        tfdProduto.setText(produto);
        anchorProdutos.setDisable(true);
        stagePopUp.close();
    }

    public void fecharPopUp() {
        stagePopUp.close();
    }

    private int verificarLinhas(int linhas, int colunas) {
        int li = (int) Math.ceil(linhas / colunas);
        if (li == 0) {
            li = 1;
        }
        return li;
    }

    private void configuraColunas() {
        tblColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblColProduto.setCellValueFactory(new PropertyValueFactory<>("produto"));
    }

    private void popularTblProdutos() {
        ArrayList produtos = new ArrayList();
        produtos = produtoDao.consultarTodos();
        ObservableList<Produto> list = FXCollections.observableArrayList(produtos);
        tblProdutos.setItems(list);
    }

    private void configurarAcoesBtnEnter() {
        btnInserir.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    handleBtnInserir();
                }
            }
        });
    }

    private void configurarBtnInserir() {
        BooleanBinding booleanBind = Bindings.or(tfdComanda.textProperty().isEmpty(),
                tfdProduto.textProperty().isEmpty());
        btnInserir.disableProperty().bind(booleanBind);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> popularComandas());
        Platform.runLater(() -> popularCategorias());
        configuraColunas();
        configurarSelecaoTabela();
        popularTblProdutos();
        configurarBtnInserir();
        configurarAcoesBtnEnter();
        Platform.runLater(() -> tfdComanda.requestFocus());
    }

}
