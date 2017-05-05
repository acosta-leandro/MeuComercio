package meucomercio.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
        testeImp2();
    }

    void testeImp1() {
        //PrinterJob printerJob = PrinterJob.createPrinterJob();
        //tezteeeeeeeeeeeeee
        Rectangle rect = new Rectangle(0, 0, anchorTbl.getWidth(), anchorTbl.getHeight());
        anchor.setClip(rect);
        WritableImage writableImage;
        writableImage = new WritableImage((int) anchorTbl.getPrefWidth(),
                (int) anchorTbl.getPrefHeight());
        anchorTbl.snapshot(null, writableImage);
        ////////////////// testeee
        ImageView imageView = new ImageView(writableImage);
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);
        double scaleX = pageLayout.getPrintableWidth() / imageView.getBoundsInParent().getWidth();
        double scaleY = pageLayout.getPrintableHeight() / imageView.getBoundsInParent().getHeight();
        imageView.getTransforms().add(new Scale(scaleX, scaleY));

        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            boolean successPrintDialog = job.showPrintDialog(meucomercio.MeuComercio.stage);
            if (successPrintDialog) {
                boolean success = job.printPage(pageLayout, anchor);
                if (success) {
                    job.endJob();
                }
            }
        }
//        System.out.println(" can I print?");
//        PrinterJob printerJob = PrinterJob.createPrinterJob();
//        if (printerJob.showPrintDialog(meucomercio.MeuComercio.stage) && printerJob.printPage(tblCategoria)) {
//            printerJob.endJob();
//            System.out.println("printed");
//        }
    }

    void testeImp2() {
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout
                = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);
        PrinterAttributes attr = printer.getPrinterAttributes();
        PrinterJob job = PrinterJob.createPrinterJob();
        double scaleX
                = pageLayout.getPrintableWidth() / anchorTbl.getBoundsInParent().getWidth();
        double scaleY
                = pageLayout.getPrintableHeight() / anchorTbl.getBoundsInParent().getHeight();
        Scale scale = new Scale(scaleX, scaleY);
        anchorTbl.getTransforms().add(scale);

        if (job != null && job.showPrintDialog(anchorTbl.getScene().getWindow())) {
            boolean success = job.printPage(pageLayout, anchorTbl);
            if (success) {
                job.endJob();

            }
        }
        anchorTbl.getTransforms().remove(scale);
    }

    @FXML
    void handleBtnTeste() {
//        Graficos.nomes.add("Leandro");
//        Graficos.nomes.add("acosta");
//        Graficos.valores.add(30);
//        Graficos.valores.add(70);
//        Graficos.nomes.add("meucu");
//        Graficos.nomes.add("notebook");
//        Graficos.valores.add(30);
//        Graficos.valores.add(70);        
//        Graficos.pizza(anchor);
        ArrayList categorias = new ArrayList();
        categorias = categoriaDao.consultarTodos();
        for (int i = 0; i < categorias.size(); i++) {
            Categoria tmpCategoria = (Categoria) categorias.get(i);
            System.out.println("teste:" + tmpCategoria.getCategoria());
        }
        ObservableList<Categoria> listCategorias = FXCollections.observableArrayList(categorias);
        tblCategoria.setItems(listCategorias);
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
