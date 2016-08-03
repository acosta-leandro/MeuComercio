package meucomercio.dao;

import apoio.ConexaoBD;
import meucomercio.entidades.Categoria;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by leandro on 12/07/16.
 */
public class CategoriaDao implements daos.IDAO {

    @Override
    public String salvar(Object o) {
        Categoria categoria = (Categoria) o;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "INSERT INTO categoria VALUES"
                    + "(DEFAULT, "
                    + "'" + categoria.getCategoria()
                    + "') RETURNING id";
            //System.out.println("sql: " + sql);

            ResultSet rs = st.executeQuery(sql);
            int id = 0;
            if (rs.next()) {
                id = rs.getInt("id");
            }
            return String.valueOf(id);
        } catch (Exception e) {
            System.out.println("Erro ao salvar Categoria = " + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(Object o) {
        return null;
    }

    @Override
    public String excluir(int id) {
        return null;
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        ArrayList categorias = new ArrayList();
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM Categoria";

            // System.out.println("sql: " + sql);
            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Categoria tmpCategoria = new Categoria();
                tmpCategoria.setId(resultado.getInt("id"));
                tmpCategoria.setCategoria(resultado.getString("categoria"));
                categorias.add(tmpCategoria);
            }
        } catch (Exception e) {
            System.out.println("Erro consultar Categoria= " + e);
            return null;
        }
        return categorias;
    }

    @Override
    public ArrayList<Object> consultar(String criterio) {
        return null;
    }

    @Override
    public Object consultarId(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM Categoria WHERE "
                    + "id = " + id + "";

            // System.out.println("sql: " + sql);
            ResultSet resultado = st.executeQuery(sql);

            if (resultado.next()) {
                Categoria tmpCategoria = new Categoria();
                tmpCategoria.setId(resultado.getInt("id"));
                tmpCategoria.setCategoria(resultado.getString("categoria"));
                return tmpCategoria;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro consultar Categoria= " + e);
            return e.toString();
        }
    }
}
