/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meucomercio.apoio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Leandro
 */
public class Graficos {
    
    public static List nomes = new ArrayList();
    public static List valores = new ArrayList();
   // public static List valores = null;
    
    

    public static void pizza(AnchorPane anchor) {
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data((String)nomes.get(0), (double) (Integer) valores.get(0)),
                        new PieChart.Data((String)nomes.get(1), (double) (Integer) valores.get(1)));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Fruits");
        anchor.getChildren().add(chart);
    }

    
}
