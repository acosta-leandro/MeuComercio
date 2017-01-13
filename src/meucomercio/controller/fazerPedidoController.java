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
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
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
import meucomercio.dao.ComandaDao;
import meucomercio.entidades.Comanda;

public class fazerPedidoController implements Initializable {

    ComandaDao comandaDao = new ComandaDao();
    private static principalController princCont = principalController.getInstance();

    @FXML
    private TitledPane titledPane;

    @FXML
    private Button btnFechar;

    @FXML
    private Button btnF1NovaComanda;

    @FXML
    private Button btnF2AlterarComanda;

    @FXML
    private Button btnF5ListarTodos;

    @FXML
    private GridPane root;

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
        popularComandas();
    }

    @FXML
    private void handleBtnFechar() {
        princCont.fecharTittledPane("controlarComanda");
    }

    private void popularComandas() {
        ArrayList comandas = comandaDao.consultarComandasAbertas();

        for (int i = 0; i < comandas.size(); i++) {
            Comanda c = (Comanda) comandas.get(i);
            System.out.println("a:" + c.getNome());
        }
        int colunas = 8;
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
                    nome = c.getNome();
                } else {
                    break;
                }
                btn[i][j] = new Button("" + nome + "");
                //representa o tamanho de altura e largura de cada botao
                btn[i][j].setPrefSize(root.getWidth() / colunas, root.getHeight() / linhas);
                //adiciona todos os botoes a gridpane                
                root.add(btn[i][j], i, j);
                {
                    numero++;
                }
                //representa o evento relacionado com todos os botoes na gridpane
                btn[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent arg0) {
                        Button b = (Button) arg0.getSource();
                        System.out.println("b:" + b.getText());
                    }
                }
                );
            }
        }
        
    }

    private int verificarLinhas(int linhas, int colunas) {
        int li = (int) Math.ceil(linhas / colunas);
        if (li == 0) {
            li = 1;
        }
        return li;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
