package meucomercio.dao;

import apoio.ConexaoBD;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import meucomercio.entidades.Grupo;

/**
 * Created by leandro on 12/07/16.
 */
public class GrupoDao implements daos.IDAO {

    @Override
    public int salvar(Object o) {
        Grupo grupo = (Grupo) o;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "INSERT INTO grupo VALUES"
                    + "(DEFAULT, "
                    + "'" + grupo.getGrupo()
                    + "') RETURNING id";
            //System.out.println("sql: " + sql);

            ResultSet rs = st.executeQuery(sql);
            int id = 0;
            if (rs.next()) {
                id = rs.getInt("id");
            }
            return id;
        } catch (Exception e) {
            System.out.println("Erro ao salvar Grupo = " + e);
            return 0;
        }
    }

    @Override
    public boolean atualizar(Object o) {
        Grupo grupo = (Grupo) o;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "UPDATE grupo SET "
                    + "grupo = '" + grupo.getGrupo()
                    + "' WHERE id = " + grupo.getId();
            System.out.println("sql: " + sql);
            st.executeUpdate(sql);;
            return true;
        } catch (Exception e) {
            System.out.println("Erro Atualizar Grupo = " + e);
            return false;
        }
    }

    @Override
    public boolean excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "DELETE FROM grupo WHERE "
                    + "id  = " + id + "";
            //  System.out.println("sql: " + sql);
            st.execute(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao excliuir grupo = " + e);
            return false;
        }
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        ArrayList grupos = new ArrayList();
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM Grupo ORDER BY 1";
            // System.out.println("sql: " + sql);

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Grupo tmpGrupo = new Grupo();
                tmpGrupo.setId(String.valueOf(resultado.getInt("id")));
                tmpGrupo.setGrupo(resultado.getString("grupo"));
                grupos.add(tmpGrupo);
            }
        } catch (Exception e) {
            System.out.println("Erro consultar Grupo= " + e);
            return null;
        }
        return grupos;
    }

    @Override
    public ArrayList<Object> consultar(String grupo) {
        ArrayList grupos = new ArrayList();
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM Grupo WHERE "
                    + "grupo iLIKE '%" + grupo + "%' ORDER BY 1;";
            System.out.println("sql: " + sql);

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Grupo tmpGrupo = new Grupo();
                tmpGrupo.setId(String.valueOf(resultado.getInt("id")));
                tmpGrupo.setGrupo(resultado.getString("grupo"));
                grupos.add(tmpGrupo);
            }
        } catch (Exception e) {
            System.out.println("Erro consultar Grupo= " + e);
            return null;
        }
        return grupos;
    }

    @Override
    public Object consultarId(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM Grupo WHERE "
                    + "id = " + id + " ORDER BY 1;";

            // System.out.println("sql: " + sql);
            ResultSet resultado = st.executeQuery(sql);

            if (resultado.next()) {
                Grupo tmpGrupo = new Grupo();
                tmpGrupo.setId(String.valueOf(resultado.getInt("id")));
                tmpGrupo.setGrupo(resultado.getString("grupo"));
                return tmpGrupo;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro consultar Grupo= " + e);
            return e.toString();
        }
    }

    @Override
    public Object consultarNome(String nome) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM Grupo WHERE "
                    + "grupo = '" + nome + "';";

            System.out.println("sql: " + sql);
            ResultSet resultado = st.executeQuery(sql);

            if (resultado.next()) {
                Grupo tmpGrupo = new Grupo();
                tmpGrupo.setId(String.valueOf(resultado.getInt("id")));
                tmpGrupo.setGrupo(resultado.getString("grupo"));
                return tmpGrupo;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro consultar Grupo= " + e);
            return e.toString();
        }
    }
}
