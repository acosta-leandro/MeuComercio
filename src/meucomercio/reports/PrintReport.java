
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meucomercio.reports;

import apoio.ConexaoBD;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Ram
 */
public class PrintReport extends JFrame {

    public void showReportWithoutParameters(String relatorioJrxml) {
        try {
            // Compila o relatorio
            JasperReport relatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream(relatorioJrxml));

            // Mapeia campos de parametros para o relatorio, mesmo que nao existam
            Map parametros = new HashMap();

            // Executa relatoio
            JasperPrint impressao = JasperFillManager.fillReport(relatorio, parametros, ConexaoBD.getInstance().getConnection());

            // Exibe resultado em video
            JasperViewer.viewReport(impressao, false);
        } catch (Exception e) {
            System.out.println("Erro ao gerar relatório: " + e);

        }
    }

    public void showReportWithParameters(String relatorioJrxml, Map parametros) {
        try {
            // Compila o relatorio
            JasperReport relatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream(relatorioJrxml));

            // Mapeia campos de parametros para o relatorio, mesmo que nao existam
           // Map parametros = new HashMap();

            // adiciona parametros
           // parametros.put("id", 2);
            // Executa relatoio
            JasperPrint impressao = JasperFillManager.fillReport(relatorio, parametros, ConexaoBD.getInstance().getConnection());

            // Exibe resultado em video
            JasperViewer.viewReport(impressao, false);
        } catch (Exception e) {
            System.out.println("Erro ao gerar relatório: " + e);
        }
    }
}
