/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meucomercio.dao;

import apoio.ConexaoBD;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import meucomercio.entidades.Comanda;
import meucomercio.entidades.Pedido;
import meucomercio.entidades.Produto;

/**
 *
 * @author Leandro Acosta <leandro.acosta292@hotmail.com>
 */
public class VendaDao {

    public void venderDinheiro(List<Produto> produtos, String idComanda) {
        String tipoPagamento = "dinheiro";
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("time?" + timestamp);

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            for (int i = 0; i < produtos.size(); i++) {

                String sql = "INSERT INTO venda VALUES"
                        + "(DEFAULT,'"
                        + produtos.get(i).getValor() + "',"
                        + "(SELECT valor FROM produto WHERE id =" + produtos.get(i).getId() + "),"
                        + produtos.get(i).getId() + ",'"
                        + idComanda + "','"
                        + timestamp + "','"
                        + tipoPagamento + "')";
                System.out.println("sql: " + sql);
                st.execute(sql);
            }
        } catch (Exception e) {
            System.out.println("Erro ao salvar Venda = " + e);
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

}
