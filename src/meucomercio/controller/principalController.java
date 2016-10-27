package meucomercio.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Accordion;
import meucomercio.MeuComercio;

/**
 * Created by leandro on 05/07/16.
 */
public class principalController implements Initializable {

    private static principalController instance;

    public principalController() {
        instance = this;
    }

    public static principalController getInstance() {
        return instance;
    }

    private boolean cadastrarCategoriaIsAberto = false;
    private boolean cadastrarGrupoIsAberto = false;
    private boolean cadastrarSubgrupoIsAberto = false;
    private boolean cadastrarUnMedidaIsAberto = false;
    private boolean cadastrarTipoIsAberto = false;
    private boolean cadastrarProdutoIsAberto = false;
    private boolean cadastrarBloqueioIsAberto = false;
    private boolean venderProdutoIsAberto = false;
    private boolean controlarComandasIsAberto = false;

    @FXML
    private Accordion accordion;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private MenuItem MenuItemProduto;

    @FXML
    private MenuItem MenuItemCategoria;

    @FXML
    private MenuItem MenuItemGrupo;

    @FXML
    private MenuItem MenuItemSubgrupo;

    @FXML
    private MenuItem MenuItemTipo;

    @FXML
    private MenuItem MenuItemBloqueio;

    @FXML
    private MenuItem MenuItemVenderProduto;

    @FXML
    private MenuItem MenuItemControlarComandas;

    @FXML
    public void handleMenuItemCadastrarCategoria() throws IOException {
        if (!cadastrarCategoriaIsAberto) {
            TitledPane cadastrarCategoria = FXMLLoader.load(MeuComercio.class.getResource("view/cadastrarCategoriaProduto.fxml"));
            accordion.getPanes().add(cadastrarCategoria);
            accordion.setExpandedPane(cadastrarCategoria);
            cadastrarCategoriaIsAberto = true;
        }
    }

    @FXML
    public void handleMenuItemCadastrarGrupo() throws IOException {
        if (!cadastrarGrupoIsAberto) {
            TitledPane cadastrarGrupo = FXMLLoader.load(MeuComercio.class.getResource("view/cadastrarGrupoProduto.fxml"));
            accordion.getPanes().add(cadastrarGrupo);
            accordion.setExpandedPane(cadastrarGrupo);
            cadastrarGrupoIsAberto = true;
        }
    }

    @FXML
    public void handleMenuItemCadastrarSubgrupo() throws IOException {
        if (!cadastrarSubgrupoIsAberto) {
            TitledPane cadastrarSubgrupo = FXMLLoader.load(MeuComercio.class.getResource("view/cadastrarSubgrupoProduto.fxml"));
            accordion.getPanes().add(cadastrarSubgrupo);
            accordion.setExpandedPane(cadastrarSubgrupo);
            cadastrarSubgrupoIsAberto = true;
        }
    }

    @FXML
    public void handleMenuItemCadastrarUnMedida() throws IOException {
        if (!cadastrarUnMedidaIsAberto) {
            TitledPane cadastrarUnMedida = FXMLLoader.load(MeuComercio.class.getResource("view/cadastrarUnMedida.fxml"));
            accordion.getPanes().add(cadastrarUnMedida);
            accordion.setExpandedPane(cadastrarUnMedida);
            cadastrarUnMedidaIsAberto = true;
        }
    }

    @FXML
    public void handleMenuItemCadastrarTipo() throws IOException {
        if (!cadastrarTipoIsAberto) {
            TitledPane cadastrarTipo = FXMLLoader.load(MeuComercio.class.getResource("view/cadastrarTipoProduto.fxml"));
            accordion.getPanes().add(cadastrarTipo);
            accordion.setExpandedPane(cadastrarTipo);
            cadastrarTipoIsAberto = true;
        }
    }

    @FXML
    public void handleMenuItemCadastrarProduto() throws IOException {
        if (!cadastrarProdutoIsAberto) {
            TitledPane cadastrarProduto = FXMLLoader.load(MeuComercio.class.getResource("view/cadastrarProduto.fxml"));
            accordion.getPanes().add(cadastrarProduto);
            accordion.setExpandedPane(cadastrarProduto);
            cadastrarProdutoIsAberto = true;
        }
    }

    @FXML
    public void handleMenuItemCadastrarBloqueio() throws IOException {
        if (!cadastrarBloqueioIsAberto) {
            TitledPane cadastrarBloqueio = FXMLLoader.load(MeuComercio.class.getResource("view/cadastrarBloqueioProduto.fxml"));
            accordion.getPanes().add(cadastrarBloqueio);
            accordion.setExpandedPane(cadastrarBloqueio);
            cadastrarBloqueioIsAberto = true;
        }
    }

    @FXML
    public void handleMenuItemControlarComandas() throws IOException {
        if (!controlarComandasIsAberto) {
            TitledPane controlarComandas = FXMLLoader.load(MeuComercio.class.getResource("view/controlarComandas.fxml"));
            accordion.getPanes().add(controlarComandas);
            accordion.setExpandedPane(controlarComandas);
            controlarComandasIsAberto = true;
        }
    }

    @FXML
    public void handleMenuItemVenderProduto() throws IOException {
        if (!venderProdutoIsAberto) {
            TitledPane venderProduto = FXMLLoader.load(MeuComercio.class.getResource("view/venderProduto.fxml"));
            accordion.getPanes().add(venderProduto);
            accordion.setExpandedPane(venderProduto);
            venderProdutoIsAberto = true;
        }
    }

    public void fecharTittledPane(String tela) {
        if (tela.equals("cadastrarCategoriaProduto")) {
            cadastrarCategoriaIsAberto = false;
        }
        if (tela.equals("cadastrarGrupoProduto")) {
            cadastrarGrupoIsAberto = false;
        }
        if (tela.equals("cadastrarSubgrupoProduto")) {
            cadastrarSubgrupoIsAberto = false;
        }
        if (tela.equals("cadastrarUnMedida")) {
            cadastrarSubgrupoIsAberto = false;
        }
        if (tela.equals("cadastrarTipoProduto")) {
            cadastrarTipoIsAberto = false;
        }
        if (tela.equals("cadastrarProduto")) {
            cadastrarProdutoIsAberto = false;
        }
        if (tela.equals("cadastrarBloqueioProduto")) {
            cadastrarBloqueioIsAberto = false;
        }
        if (tela.equals("cadastrarVenderProdutos")) {
            venderProdutoIsAberto = false;
        }
        if (tela.equals("controlarComandas")) {
            controlarComandasIsAberto = false;
        }
        accordion.getPanes().remove(accordion.getExpandedPane());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
