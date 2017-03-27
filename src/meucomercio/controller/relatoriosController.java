package meucomercio.controller;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.print.PrinterJob;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import meucomercio.apoio.Graficos;

public class relatoriosController {

    private static principalController princCont = principalController.getInstance();

    @FXML
    private Button btnTeste;

    @FXML
    private AnchorPane anchor;

    @FXML
    void handleBtnTeste() {
//        Graficos.nomes.add("Leandro");
//        Graficos.nomes.add("acosta");
//        Graficos.valores.add(30);
//        Graficos.valores.add(70);
        Graficos.nomes.add("meucu");
        Graficos.nomes.add("notebook");
        Graficos.valores.add(30);
        Graficos.valores.add(70);        
        Graficos.pizza(anchor);
    }

    @FXML
    void handleBtnF2CancelarItem() {

    }

    @FXML
    void handleBtnF3ConsultarItem() {

    }

    @FXML
    void handleBtnF4DescontoItem() {

    }

    @FXML
    void handleBtnFechar() {
        princCont.fecharTittledPane("relatorios");
    }

}
