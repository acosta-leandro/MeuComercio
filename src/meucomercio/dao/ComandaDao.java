package meucomercio.dao;

import apoio.ConexaoBD;
import com.sun.prism.paint.Gradient;
import meucomercio.entidades.Comanda;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import meucomercio.entidades.Comanda;
import meucomercio.entidades.Grupo;

/**
 * Created by leandro on 12/07/16.
 */
public class ComandaDao implements daos.IDAO {

    @Override
    public int salvar(Object o) {
        Comanda comanda = (Comanda) o;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "INSERT INTO comanda VALUES"
                    + "(DEFAULT, "
                    + "'" + comanda.getNome() + "', "
                    + "'" + comanda.getDtAbertura() + "', "
                    + "null, "
                    + "'" + comanda.getEstado() + "', "
                    + "0 ) RETURNING id";
            System.out.println("sql: " + sql);

            ResultSet rs = st.executeQuery(sql);
            int id = 0;
            if (rs.next()) {
                id = rs.getInt("id");
            }
            return id;
        } catch (Exception e) {
            System.out.println("Erro ao salvar Comanda = " + e);
            return 0;
        }
    }

    @Override
    public boolean atualizar(Object o) {
        Comanda comanda = (Comanda) o;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "UPDATE comanda SET "
                    //         + "comanda = '" + comanda.getComanda() + "', "
                    //         + "grupo_id = " + comanda.getGrupoId()
                    + " WHERE id = " + comanda.getId();
            System.out.println("sql: " + sql);
            st.executeUpdate(sql);;
            return true;
        } catch (Exception e) {
            System.out.println("Erro Atualizar Comanda = " + e);
            return false;
        }
    }

    @Override
    public boolean excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "DELETE FROM comanda WHERE "
                    + "id  = " + id + "";
            //  System.out.println("sql: " + sql);
            st.execute(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao excliuir comanda = " + e);
            return false;
        }
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        ArrayList comandas = new ArrayList();
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM comanda ORDER BY 1";
            // System.out.println("sql: " + sql);

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Comanda tmpComanda = new Comanda();
                tmpComanda.setId(String.valueOf(resultado.getInt("id")));
                tmpComanda.setNome(resultado.getString("nome"));
                tmpComanda.setDtAbertura(String.valueOf(resultado.getTime("dt_abertura")));
                if (String.valueOf(resultado.getDate("dt_encerramento")).equals("null")) {
                    tmpComanda.setDtEncerramento("Aberto");
                } else {
                    tmpComanda.setDtEncerramento(String.valueOf(resultado.getDate("dt_encerramento")));
                }
                tmpComanda.setEstado(String.valueOf(resultado.getString("estado")));
                tmpComanda.setValor(String.valueOf(resultado.getString("valor")));
                comandas.add(tmpComanda);
                
            }
        } catch (Exception e) {
            System.out.println("Erro consultar Comanda= " + e);
            return null;
        }
        return comandas;
    }

    @Override
    public ArrayList<Object> consultar(String comanda) {
        ArrayList comandas = new ArrayList();
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM Comanda WHERE "
                    + "comanda iLIKE '%" + comanda + "%' ORDER BY 1;";
            System.out.println("sql: " + sql);

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Comanda tmpComanda = new Comanda();
                tmpComanda.setId(String.valueOf(resultado.getInt("id")));
                //   tmpComanda.setComanda(resultado.getString("comanda"));
                //   tmpComanda.setGrupoId(String.valueOf(resultado.getInt("grupo_id")));
                //   Grupo tmpGrupo = (Grupo) new GrupoDao().consultarId(Integer.valueOf(tmpComanda.getGrupoId()));
                //   tmpComanda.setGrupoNome(tmpGrupo.getGrupo());
                comandas.add(tmpComanda);
            }
        } catch (Exception e) {
            System.out.println("Erro consultar Comanda= " + e);
            return null;
        }
        return comandas;
    }

    @Override
    public Object consultarId(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM Comanda WHERE "
                    + "id = " + id + " ORDER BY 1;";

            // System.out.println("sql: " + sql);
            ResultSet resultado = st.executeQuery(sql);

            if (resultado.next()) {
                Comanda tmpComanda = new Comanda();
                tmpComanda.setId(String.valueOf(resultado.getInt("id")));
//                tmpComanda.setComanda(resultado.getString("comanda"));
//                tmpComanda.setGrupoId(String.valueOf(resultado.getInt("grupo_id")));
//                Grupo tmpGrupo = (Grupo) new GrupoDao().consultarId(Integer.valueOf(tmpComanda.getGrupoId()));
//                tmpComanda.setGrupoNome(tmpGrupo.getGrupo());
                return tmpComanda;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro consultar Comanda= " + e);
            return e.toString();
        }
    }

    @Override
    public Object consultarNome(String nome) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM Comanda WHERE "
                    + "comanda = '" + nome + "';";

            System.out.println("sql: " + sql);
            ResultSet resultado = st.executeQuery(sql);

            if (resultado.next()) {
                Grupo tmpGrupo = new Grupo();
                tmpGrupo.setId(String.valueOf(resultado.getInt("id")));
                tmpGrupo.setGrupo(resultado.getString("comanda"));
                return tmpGrupo;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro consultar Grupo= " + e);
            return e.toString();
        }
    }
    
    public ArrayList<Object> consultarComandasAbertas() {
        ArrayList comandas = new ArrayList();
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * "
                    + "FROM comanda "
                    + "WHERE estado != 'Fechado'"
                    + "ORDER BY 1";
            System.out.println("sql: " + sql);

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Comanda tmpComanda = new Comanda();
                tmpComanda.setId(String.valueOf(resultado.getInt("id")));
                tmpComanda.setNome(resultado.getString("nome"));
                tmpComanda.setDtAbertura(String.valueOf(resultado.getTime("dt_abertura")));
                if (String.valueOf(resultado.getDate("dt_encerramento")).equals("null")) {
                    tmpComanda.setDtEncerramento("Aberto");
                } else {
                    tmpComanda.setDtEncerramento(String.valueOf(resultado.getDate("dt_encerramento")));
                }
                tmpComanda.setEstado(String.valueOf(resultado.getString("estado")));
                tmpComanda.setValor(String.valueOf(resultado.getString("valor")));
                comandas.add(tmpComanda);
                
            }
        } catch (Exception e) {
            System.out.println("Erro consultar Comanda= " + e);
            return null;
        }
        return comandas;
    }
}
