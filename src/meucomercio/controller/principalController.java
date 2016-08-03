package meucomercio.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Accordion;
import meucomercio.MeuComercio;

/**
 * Created by leandro on 05/07/16.
 */
public class principalController {

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

    @FXML
    private Accordion accordion;

    @FXML
    private MenuItem MenuItemProduto;

    @FXML
    private MenuItem MenuItemCategoria;

    @FXML
    private MenuItem MenuItemGrupo;

    @FXML
    private MenuItem MenuItemSubgrupo;

    @FXML
    private MenuItem MenuItemTeste;

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
        if (!cadastrarGrupoIsAberto) {
            TitledPane cadastrarSubgrupo = FXMLLoader.load(MeuComercio.class.getResource("view/cadastrarSubgrupoProduto.fxml"));
            accordion.getPanes().add(cadastrarSubgrupo);
            accordion.setExpandedPane(cadastrarSubgrupo);
            cadastrarSubgrupoIsAberto = true;
        }
    }

    @FXML
    public void handleMenuItemTeste() throws IOException {
        System.out.println("teste:" + accordion.getExpandedPane());
        System.out.println("teste:" + accordion.getPanes().toString());
        // System.out.println("teste:"+accordion.getPanes().remove(accordion.getExpandedPane()));

    }

    public void fecharTittledPane(String tela) {
        if (tela.equals("cadastrarCategoriaProduto")) {
            cadastrarCategoriaIsAberto = false;
        }
        if (tela.equals("cadastrarGrupoProduto")) {
            cadastrarGrupoIsAberto = false;
        }
        if (tela.equals("cadastrarSubgruProduto")) {
            cadastrarSubgrupoIsAberto = false;
        }
        accordion.getPanes().remove(accordion.getExpandedPane());
    }
}
