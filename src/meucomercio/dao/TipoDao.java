package meucomercio.dao;

import apoio.ConexaoBD;
import meucomercio.entidades.Tipo;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by leandro on 12/07/16.
 */
public class TipoDao implements daos.IDAO {

    @Override
    public int salvar(Object o) {
        Tipo tipo = (Tipo) o;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "INSERT INTO tipo VALUES"
                    + "(DEFAULT, "
                    + "'" + tipo.getTipo()
                    + "') RETURNING id";
            //System.out.println("sql: " + sql);

            ResultSet rs = st.executeQuery(sql);
            int id = 0;
            if (rs.next()) {
                id = rs.getInt("id");
            }
            return id;
        } catch (Exception e) {
            System.out.println("Erro ao salvar Tipo = " + e);
            return 0;
        }
    }

    @Override
    public boolean atualizar(Object o) {
        Tipo tipo = (Tipo) o;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "UPDATE tipo SET "
                    + "tipo = '" + tipo.getTipo()
                    + "' WHERE id = " + tipo.getId();
            System.out.println("sql: " + sql);
            st.executeUpdate(sql);;
            return true;
        } catch (Exception e) {
            System.out.println("Erro Atualizar Tipo = " + e);
            return false;
        }
    }

    @Override
    public boolean excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "DELETE FROM tipo WHERE "
                    + "id  = " + id + "";
            //  System.out.println("sql: " + sql);
            st.execute(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao excliuir tipo = " + e);
            return false;
        }
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        ArrayList tipos = new ArrayList();
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM Tipo ORDER BY 1";
            // System.out.println("sql: " + sql);

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Tipo tmpTipo = new Tipo();
                tmpTipo.setId(String.valueOf(resultado.getInt("id")));
                tmpTipo.setTipo(resultado.getString("tipo"));
                tipos.add(tmpTipo);
            }
        } catch (Exception e) {
            System.out.println("Erro consultar Tipo= " + e);
            return null;
        }
        return tipos;
    }

    @Override
    public ArrayList<Object> consultar(String tipo) {
        ArrayList tipos = new ArrayList();
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM Tipo WHERE "
                    + "tipo iLIKE '%" + tipo + "%' ORDER BY 1;";
            System.out.println("sql: " + sql);

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Tipo tmpTipo = new Tipo();
                tmpTipo.setId(String.valueOf(resultado.getInt("id")));
                tmpTipo.setTipo(resultado.getString("tipo"));
                tipos.add(tmpTipo);
            }
        } catch (Exception e) {
            System.out.println("Erro consultar Tipo= " + e);
            return null;
        }
        return tipos;
    }

    @Override
    public Object consultarId(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM Tipo WHERE "
                    + "id = " + id + " ORDER BY 1;";

            // System.out.println("sql: " + sql);
            ResultSet resultado = st.executeQuery(sql);

            if (resultado.next()) {
                Tipo tmpTipo = new Tipo();
                tmpTipo.setId(String.valueOf(resultado.getInt("id")));
                tmpTipo.setTipo(resultado.getString("tipo"));
                return tmpTipo;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro consultar Tipo= " + e);
            return e.toString();
        }
    }

    @Override
    public Object consultarNome(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
