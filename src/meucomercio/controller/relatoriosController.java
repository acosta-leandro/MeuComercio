package meucomercio.controller;

import com.lowagie.text.ListItem;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import meucomercio.dao.CategoriaDao;
import meucomercio.entidades.Categoria;
import meucomercio.reports.PrintReport;

public class relatoriosController implements Initializable {

    PrintReport printReport = new PrintReport();
    String parametro1;
    String parametro2;

    @FXML
    private TitledPane titledPane;

    @FXML
    private Button btnFechar;

    @FXML
    private AnchorPane anchor;

    @FXML
    private AnchorPane anchorParametros;

    @FXML
    private AnchorPane anchorParametros2;

    @FXML
    private ListView listView;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabTabela;

    @FXML
    private AnchorPane anchorTabela;

    @FXML
    private Tab tabGrafico;

    @FXML
    private AnchorPane anchorGrafico;

    @FXML
    private Button btnImprimir;

    @FXML
    private AnchorPane anchorList;

    @FXML
    private ListView listListagens;

    @FXML
    private ListView listRelatorios;
    @FXML
    private Label lblRelAberto;

    DatePicker data1 = new DatePicker();
    DatePicker data2 = new DatePicker();

    @FXML
    void handleBtnFechar() {

    }

    @FXML
    void handleBtnImprimir() {
        if (lblRelAberto.getText().contains("Listar Comandas")) {
            printReport.showReportWithoutParameters("ListarComandas.jrxml");
        } else if (lblRelAberto.getText().contains("Produtos Mais Vendidos")) {
            printReport.showReportWithoutParameters("ProdutosMaisVendidos.jrxml");
        } else if (lblRelAberto.getText().contains("Produtos Por Categoria")) {
            Map parametros = new HashMap();
            Categoria tmpCat = (Categoria) new CategoriaDao().consultarNome(parametro1);
            parametros.put("id", Integer.valueOf(tmpCat.getId()));
            printReport.showReportWithParameters("ProdutosPorCategoria.jrxml", parametros);
        } else if (lblRelAberto.getText().contains("Vendas Por Data")) {
            Map parametros = new HashMap();
            parametros.put("data1", Timestamp.valueOf(data1.getValue().atStartOfDay()));
            parametros.put("data2", Timestamp.valueOf(data2.getValue().atStartOfDay()));
            printReport.showReportWithParameters("VendasPorData.jrxml", parametros);
        }
    }

    private void popularListagens() {
        ListItem listComandas = new ListItem("Listar Comandas");
        listListagens.getItems().add(listComandas);
        ListItem prodMaisVendidos = new ListItem("Produtos Mais Vendidos");
        listListagens.getItems().add(prodMaisVendidos);

        listListagens.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                lblRelAberto.setText(listListagens.getSelectionModel().getSelectedItem().toString());
            }
        });
    }

    private void popularRelatorios() {
        ListItem listProdCat = new ListItem("Produtos Por Categoria");
        listRelatorios.getItems().add(listProdCat);
        ListItem listVendasPorData = new ListItem("Vendas Por Data");
        listRelatorios.getItems().add(listVendasPorData);

        listRelatorios.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                lblRelAberto.setText(listRelatorios.getSelectionModel().getSelectedItem().toString());
                if (listRelatorios.getSelectionModel().getSelectedItem().toString().contains("Produtos Por Categoria")) {
                    parametroProdutoPorCategoria();
                } else if (listRelatorios.getSelectionModel().getSelectedItem().toString().contains("Vendas Por Data")) {
                    parametroVendasPorData();
                }
            }
        });
    }

    private void parametroProdutoPorCategoria() {
        ComboBox cmbCategorias = new ComboBox();
        ArrayList<Object> listCategorias = new CategoriaDao().consultarTodos();
        ObservableList<String> nomesCategorias = FXCollections.observableArrayList();
        for (int i = 0; i < listCategorias.size(); i++) {
            Categoria categoria = (Categoria) listCategorias.get(i);
            nomesCategorias.add(categoria.getCategoria());
        }
        cmbCategorias.getItems().addAll(nomesCategorias);
        anchorParametros.getChildren().add(cmbCategorias);
        cmbCategorias.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue)
                -> parametro1 = (String) newValue
        );
    }

    private void parametroVendasPorData() {
        anchorParametros.getChildren().add(data1);
        anchorParametros2.getChildren().add(data2);
    }

    // printReport.showReportWithoutParameters("ListarComandas.jrxml");
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("teste");
        popularListagens();
        popularRelatorios();
    }

}
