/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meucomercio.dao;

import apoio.ConexaoBD;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import meucomercio.entidades.Comanda;
import meucomercio.entidades.Pedido;
import meucomercio.entidades.Produto;

/**
 *
 * @author Leandro Acosta <leandro.acosta292@hotmail.com>
 */
public class FazerPedidoDao {

    public int salvar(String idComanda, String idProduto) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "INSERT INTO produto_comanda VALUES"
                    + "(DEFAULT,"
                    + idProduto + ","
                    + idComanda + ","
                    + "'Aberto'"
                    + ") RETURNING id";
            System.out.println("sql: " + sql);

            ResultSet rs = st.executeQuery(sql);
            int id = 0;
            if (rs.next()) {
                id = rs.getInt("id");
            }
            return id;
        } catch (Exception e) {
            System.out.println("Erro ao salvar Pedido = " + e);
            return 0;
        }
    }

    public boolean excluir(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Object> consultarTodos() {
        ArrayList pedidos = new ArrayList();
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM produto_comanda ORDER BY 1";
            // System.out.println("sql: " + sql);

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Pedido tmpPedido = new Pedido();
                tmpPedido.setIdComanda(resultado.getInt("comanda_id"));
                Comanda tmpComanda = (Comanda) new ComandaDao().consultarId(Integer.valueOf(tmpPedido.getIdComanda()));
                tmpPedido.setNomeComanda(tmpComanda.getNome());
                tmpPedido.setIdPedido(resultado.getInt("id"));
                tmpPedido.setIdProduto(resultado.getInt("produto_id"));
                Produto tmpProduto = (Produto) new ProdutoDao().consultarId(Integer.valueOf(tmpPedido.getIdProduto()));
                tmpPedido.setNomeProduto(tmpProduto.getProduto());
                tmpPedido.setStatus(resultado.getString("status"));
                pedidos.add(tmpPedido);
            }
        } catch (Exception e) {
            System.out.println("Erro consultar Pedido= " + e);
            return null;
        }
        return pedidos;
    }

    public boolean alterarEstadoCancelado(int idPedido) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "UPDATE produto_comanda SET "
                    + "status = 'Cancelado'"
                    + " WHERE id = " + idPedido;
            //  System.out.println("sql: " + sql);
            st.executeUpdate(sql);;
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao alterar estado do Pedido = " + e);
            return false;
        }
    }

    public boolean alterarEstadoFechado(int idPedido) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "UPDATE produto_comanda SET "
                    + "status = 'Fechado'"
                    + " WHERE id = " + idPedido;
            //  System.out.println("sql: " + sql);
            st.executeUpdate(sql);;
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao alterar estado do Pedido = " + e);
            return false;
        }
    }

    public ArrayList<Object> consultarTodosAbertos() {
        ArrayList pedidos = new ArrayList();
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM produto_comanda WHERE status = 'Aberto' ORDER BY 1 ";
             System.out.println("sql: " + sql);

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Pedido tmpPedido = new Pedido();
                tmpPedido.setIdComanda(resultado.getInt("comanda_id"));
                Comanda tmpComanda = (Comanda) new ComandaDao().consultarId(Integer.valueOf(tmpPedido.getIdComanda()));
                tmpPedido.setNomeComanda(tmpComanda.getNome());
                tmpPedido.setIdPedido(resultado.getInt("id"));
                tmpPedido.setIdProduto(resultado.getInt("produto_id"));
                Produto tmpProduto = (Produto) new ProdutoDao().consultarId(Integer.valueOf(tmpPedido.getIdProduto()));
                tmpPedido.setNomeProduto(tmpProduto.getProduto());
                tmpPedido.setStatus(resultado.getString("status"));
                pedidos.add(tmpPedido);
            }
        } catch (Exception e) {
            System.out.println("Erro consultar Pedido= " + e);
            return null;
        }
        return pedidos;
    }

}
