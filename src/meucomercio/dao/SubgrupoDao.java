package meucomercio.dao;

import apoio.ConexaoBD;
import com.sun.prism.paint.Gradient;
import meucomercio.entidades.Subgrupo;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import meucomercio.entidades.Grupo;

/**
 * Created by leandro on 12/07/16.
 */
public class SubgrupoDao implements daos.IDAO {

    @Override
    public int salvar(Object o) {
        Subgrupo subgrupo = (Subgrupo) o;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "INSERT INTO subgrupo VALUES"
                    + "(DEFAULT, "
                    + "'" + subgrupo.getSubgrupo() + "', "
                    + "'" + subgrupo.getGrupoId() + "',"
                    + "'" + subgrupo.getEstado()
                    + "') RETURNING id";
            System.out.println("sql: " + sql);

            ResultSet rs = st.executeQuery(sql);
            int id = 0;
            if (rs.next()) {
                id = rs.getInt("id");
            }
            return id;
        } catch (Exception e) {
            System.out.println("Erro ao salvar Subgrupo = " + e);
            return 0;
        }
    }

    @Override
    public boolean atualizar(Object o) {
        Subgrupo subgrupo = (Subgrupo) o;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "UPDATE subgrupo SET "
                    + "subgrupo = '" + subgrupo.getSubgrupo() + "', "
                    + "grupo_id = " + subgrupo.getGrupoId() + "',"
                    + "estado = '" + subgrupo.getEstado()
                    + " WHERE id = " + subgrupo.getId();
            System.out.println("sql: " + sql);
            st.executeUpdate(sql);;
            return true;
        } catch (Exception e) {
            System.out.println("Erro Atualizar Subgrupo = " + e);
            return false;
        }
    }

    @Override
    public boolean excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "DELETE FROM subgrupo WHERE "
                    + "id  = " + id + "";
            //  System.out.println("sql: " + sql);
            st.execute(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao excliuir subgrupo = " + e);
            return false;
        }
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        ArrayList subgrupos = new ArrayList();
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM Subgrupo ORDER BY 1";
            // System.out.println("sql: " + sql);

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Subgrupo tmpSubgrupo = new Subgrupo();
                tmpSubgrupo.setId(String.valueOf(resultado.getInt("id")));
                tmpSubgrupo.setSubgrupo(resultado.getString("subgrupo"));
                tmpSubgrupo.setGrupoId(String.valueOf(resultado.getInt("grupo_id")));
                Grupo tmpGrupo = (Grupo) new GrupoDao().consultarId(Integer.valueOf(tmpSubgrupo.getGrupoId()));
                tmpSubgrupo.setGrupoNome(tmpGrupo.getGrupo());
                tmpSubgrupo.setEstado(resultado.getString("estado"));
                subgrupos.add(tmpSubgrupo);
            }
        } catch (Exception e) {
            System.out.println("Erro consultar Subgrupo= " + e);
            return null;
        }
        return subgrupos;
    }

    @Override
    public ArrayList<Object> consultar(String subgrupo) {
        ArrayList subgrupos = new ArrayList();
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM Subgrupo WHERE "
                    + "subgrupo iLIKE '%" + subgrupo + "%' ORDER BY 1;";
            System.out.println("sql: " + sql);

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Subgrupo tmpSubgrupo = new Subgrupo();
                tmpSubgrupo.setId(String.valueOf(resultado.getInt("id")));
                tmpSubgrupo.setSubgrupo(resultado.getString("subgrupo"));
                tmpSubgrupo.setGrupoId(String.valueOf(resultado.getInt("grupo_id")));
                Grupo tmpGrupo = (Grupo) new GrupoDao().consultarId(Integer.valueOf(tmpSubgrupo.getGrupoId()));
                tmpSubgrupo.setGrupoNome(tmpGrupo.getGrupo());
                tmpSubgrupo.setEstado(resultado.getString("estado"));
                subgrupos.add(tmpSubgrupo);
            }
        } catch (Exception e) {
            System.out.println("Erro consultar Subgrupo= " + e);
            return null;
        }
        return subgrupos;
    }

    @Override
    public Object consultarId(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM Subgrupo WHERE "
                    + "id = " + id + " ORDER BY 1;";

            // System.out.println("sql: " + sql);
            ResultSet resultado = st.executeQuery(sql);

            if (resultado.next()) {
                Subgrupo tmpSubgrupo = new Subgrupo();
                tmpSubgrupo.setId(String.valueOf(resultado.getInt("id")));
                tmpSubgrupo.setSubgrupo(resultado.getString("subgrupo"));
                tmpSubgrupo.setGrupoId(String.valueOf(resultado.getInt("grupo_id")));
                Grupo tmpGrupo = (Grupo) new GrupoDao().consultarId(Integer.valueOf(tmpSubgrupo.getGrupoId()));
                tmpSubgrupo.setGrupoNome(tmpGrupo.getGrupo());
                tmpSubgrupo.setEstado(resultado.getString("estado"));
                return tmpSubgrupo;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro consultar Subgrupo= " + e);
            return e.toString();
        }
    }

    @Override
    public Object consultarNome(String nome) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM Subgrupo WHERE "
                    + "subgrupo = '" + nome + "';";

            System.out.println("sql: " + sql);
            ResultSet resultado = st.executeQuery(sql);

            if (resultado.next()) {
                Grupo tmpGrupo = new Grupo();
                tmpGrupo.setId(String.valueOf(resultado.getInt("id")));
                tmpGrupo.setGrupo(resultado.getString("subgrupo"));
                tmpGrupo.setEstado(resultado.getString("estado"));
                return tmpGrupo;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro consultar Grupo= " + e);
            return e.toString();
        }
    }

    public ArrayList<Object>  consultarIdGrupo(int idGrupo) {
        ArrayList subgrupos = new ArrayList();
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM Subgrupo WHERE "
                    + "grupo_id =" + idGrupo + " ORDER BY 1;";
            System.out.println("sql: " + sql);

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Subgrupo tmpSubgrupo = new Subgrupo();
                tmpSubgrupo.setId(String.valueOf(resultado.getInt("id")));
                tmpSubgrupo.setSubgrupo(resultado.getString("subgrupo"));
                tmpSubgrupo.setGrupoId(String.valueOf(resultado.getInt("grupo_id")));
                Grupo tmpGrupo = (Grupo) new GrupoDao().consultarId(Integer.valueOf(tmpSubgrupo.getGrupoId()));
                tmpSubgrupo.setGrupoNome(tmpGrupo.getGrupo());
                tmpSubgrupo.setEstado(resultado.getString("estado"));
                subgrupos.add(tmpSubgrupo);
            }
        } catch (Exception e) {
            System.out.println("Erro consultar Subgrupo= " + e);
            return null;
        }
        return subgrupos;
    }
}
