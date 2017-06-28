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
import javafx.application.Platform;
import javafx.scene.control.Accordion;
import javafx.scene.control.Menu;
import meucomercio.MeuComercio;
import meucomercio.entidades.Usuario;

/**
 * Created by leandro on 05/07/16.
 */
public class principalController implements Initializable {

    private static principalController instance;
    private static Usuario user;

    public principalController() {
        instance = this;
    }

    public static principalController getInstance() {
        return instance;
    }

    public static void setUsuarioLogado(Usuario usuario) {
        user = usuario;
    }

    public static Usuario getUsuarioLogado() {
        return user;
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
    private boolean fazerPedidoIsAberto = false;
    private boolean relatoriosIsAberto = false;

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
    private MenuItem MenuItemFazerPedido;

    @FXML
    private MenuItem MenuItemRelatorios;

    @FXML
    private MenuItem MenuItemSair;

    @FXML
    private Menu menuUsuario;

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
            TitledPane venderProduto = FXMLLoader.load(MeuComercio.class.getResource("view/PDV.fxml"));
            accordion.getPanes().add(venderProduto);
            accordion.setExpandedPane(venderProduto);
            venderProdutoIsAberto = true;
        }
    }

    @FXML
    public void handleMenuItemFazerPedido() throws IOException {
        if (!fazerPedidoIsAberto) {
            TitledPane fazerPedido = FXMLLoader.load(MeuComercio.class.getResource("view/fazerPedido.fxml"));
            accordion.getPanes().add(fazerPedido);
            accordion.setExpandedPane(fazerPedido);
            fazerPedidoIsAberto = true;
        }
    }

    @FXML
    public void handleMenuItemRelatorios() throws IOException {
        if (!relatoriosIsAberto) {
            TitledPane relatorios = FXMLLoader.load(MeuComercio.class.getResource("view/relatorios.fxml"));
            accordion.getPanes().add(relatorios);
            accordion.setExpandedPane(relatorios);
            relatoriosIsAberto = true;
        }
    }

    @FXML
    public void handleMenuItemSair() throws IOException {
        System.exit(0);
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
        if (tela.equals("venderProdutos")) {
            venderProdutoIsAberto = false;
        }
        if (tela.equals("controlarComandas")) {
            controlarComandasIsAberto = false;
        }
        if (tela.equals("fazerPedido")) {
            fazerPedidoIsAberto = false;
        }
        if (tela.equals("relatorios")) {
            relatoriosIsAberto = false;
        }
        accordion.getPanes().remove(accordion.getExpandedPane());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuUsuario.setText(getUsuarioLogado().getNome());
    }
}
