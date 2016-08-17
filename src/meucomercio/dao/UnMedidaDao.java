package meucomercio.dao;

import apoio.ConexaoBD;
import com.sun.prism.paint.Gradient;
import meucomercio.entidades.UnMedida;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import meucomercio.entidades.Grupo;

/**
 * Created by leandro on 12/07/16.
 */
public class UnMedidaDao implements daos.IDAO {

    @Override
    public int salvar(Object o) {
        UnMedida unMedida = (UnMedida) o;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "INSERT INTO unidade_medida VALUES"
                    + "(DEFAULT, "
                    + "'" + unMedida.getNome() + "', "
                    + "'" + unMedida.getSigla()
                    + "') RETURNING id";
            System.out.println("sql: " + sql);

            ResultSet rs = st.executeQuery(sql);
            int id = 0;
            if (rs.next()) {
                id = rs.getInt("id");
            }
            return id;
        } catch (Exception e) {
            System.out.println("Erro ao salvar Unidade de Medida = " + e);
            return 0;
        }
    }

    @Override
    public boolean atualizar(Object o) {
        UnMedida unMedida = (UnMedida) o;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "UPDATE unidade_medida SET "
                    + "nome = '" + unMedida.getNome()+ "', "
                    + "sigla = '" + unMedida.getSigla()
                    + "' WHERE id = " + unMedida.getId();
            System.out.println("sql: " + sql);
            st.executeUpdate(sql);;
            return true;
        } catch (Exception e) {
            System.out.println("Erro Atualizar Unidade de Medida = " + e);
            return false;
        }
    }

    @Override
    public boolean excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "DELETE FROM unidade_medida WHERE "
                    + "id  = " + id + "";
            //  System.out.println("sql: " + sql);
            st.execute(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao excliuir unMedida = " + e);
            return false;
        }
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        ArrayList unMedidas = new ArrayList();
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM unidade_medida ORDER BY 1";
            // System.out.println("sql: " + sql);

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                UnMedida tmpUnMedida = new UnMedida();
                tmpUnMedida.setId(String.valueOf(resultado.getInt("id")));
                tmpUnMedida.setNome(resultado.getString("nome"));
                tmpUnMedida.setSigla(resultado.getString("sigla"));
                unMedidas.add(tmpUnMedida);
            }
        } catch (Exception e) {
            System.out.println("Erro consultar Unidade de Medida = " + e);
            return null;
        }
        return unMedidas;
    }

    @Override
    public ArrayList<Object> consultar(String unMedida) {
        ArrayList unMedidas = new ArrayList();
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM unidade_medida WHERE "
                    + "nome iLIKE '%" + unMedida + "%' ORDER BY 1;";
            System.out.println("sql: " + sql);

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                UnMedida tmpUnMedida = new UnMedida();
                tmpUnMedida.setId(String.valueOf(resultado.getInt("id")));
                tmpUnMedida.setNome(resultado.getString("nome"));
                tmpUnMedida.setSigla(resultado.getString("sigla"));
                unMedidas.add(tmpUnMedida);
            }
        } catch (Exception e) {
            System.out.println("Erro consultar Unidade de Medida = " + e);
            return null;
        }
        return unMedidas;
    }

    @Override
    public Object consultarId(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM unidade_medida WHERE "
                    + "id = " + id + " ORDER BY 1;";

            // System.out.println("sql: " + sql);
            ResultSet resultado = st.executeQuery(sql);

            if (resultado.next()) {
                UnMedida tmpUnMedida = new UnMedida();
                tmpUnMedida.setId(String.valueOf(resultado.getInt("id")));
                tmpUnMedida.setNome(resultado.getString("nome"));
                tmpUnMedida.setSigla(resultado.getString("sigla"));
                return tmpUnMedida;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro consultar UnMedida= " + e);
            return e.toString();
        }
    }

    @Override
    public Object consultarNome(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
