package meucomercio.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterAttributes;
import javafx.print.PrinterJob;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import meucomercio.apoio.Graficos;
import meucomercio.dao.CategoriaDao;
import meucomercio.entidades.Categoria;
import meucomercio.reports.PrintReport;
import net.sf.jasperreports.engine.JRException;

public class relatoriosController implements Initializable {

    private static principalController princCont = principalController.getInstance();
    CategoriaDao categoriaDao = new CategoriaDao();

    @FXML
    private Button btnTeste;

    @FXML
    private Button btnImprimir;

    @FXML
    private AnchorPane anchor;
    @FXML
    private AnchorPane anchorTbl;


    @FXML
    private TableView<Categoria> tblCategoria;
    @FXML
    private TableColumn<Categoria, Integer> tblColId;
    @FXML
    private TableColumn<Categoria, String> tblColCategoria;
    @FXML
    private TableColumn<Categoria, String> tblColEstado;

    @FXML
    void handleBtnImprimir() {
       testeImp1();
    }

    void testeImp1() {
        try {
            new PrintReport().showReport();
        } catch (JRException ex) {
            Logger.getLogger(relatoriosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(relatoriosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(relatoriosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void handleBtnTeste() {
        System.out.println(getClass().getResource("ListarComandas.jrxml"));
    }

    private void configuraColunas() {
        tblColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblColCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        tblColEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
    }

    @FXML
    void handleBtnFechar() {
        princCont.fecharTittledPane("relatorios");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configuraColunas();
    }

}
