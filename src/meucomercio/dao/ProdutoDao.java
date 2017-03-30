package meucomercio.dao;

import apoio.ConexaoBD;
import com.sun.prism.paint.Gradient;
import meucomercio.entidades.Produto;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import meucomercio.entidades.Bloqueio;
import meucomercio.entidades.Categoria;
import meucomercio.entidades.Grupo;
import meucomercio.entidades.Produto;
import meucomercio.entidades.Subgrupo;
import meucomercio.entidades.Tipo;
import meucomercio.entidades.UnMedida;

/**
 * Created by leandro on 12/07/16.
 */
public class ProdutoDao implements daos.IDAO {

    @Override
    public int salvar(Object o) {
        System.out.println("prod dao salvar");
        Produto produto = (Produto) o;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "INSERT INTO produto VALUES"
                    + "(DEFAULT, "
                    + "'" + produto.getGrupoId() + "', "
                    + "'" + produto.getProduto() + "', "
                    + "'" + produto.getSubgrupoId() + "', "
                    + "'" + produto.getCategoriaId() + "', "
                    + "'" + produto.getBloqueioId() + "', "
                    + "'" + produto.getTipoId() + "', "
                    + "'" + produto.getUnidadeId() + "', "
                    + "'" + produto.getCusto() + "', "
                    + "'" + produto.getUltCusto() + "', "
                    + "'" + produto.getValor() + "', "
                    + "'" + produto.getEstMax() + "', "
                    + "'" + produto.getEstMin() + "') RETURNING id";
            System.out.println("sql: " + sql);

            ResultSet rs = st.executeQuery(sql);
            int id = 0;
            if (rs.next()) {
                id = rs.getInt("id");
            }
            return id;
        } catch (Exception e) {
            System.out.println("Erro ao salvar Produto = " + e);
            return 0;
        }
    }

    @Override
    public boolean atualizar(Object o) {
        Produto produto = (Produto) o;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "UPDATE produto SET "
                    + "grupo_id = '" + produto.getGrupoId() + "', "
                    + "produto = '" + produto.getProduto() + "', "
                    + "subgrupo_id = '" + produto.getSubgrupoId() + "', "
                    + "categoria_id = '" + produto.getCategoriaId() + "', "
                    + "bloqueio_id = '" + produto.getBloqueioId() + "', "
                    + "tipo_id = '" + produto.getTipoId() + "', "
                    + "unidade_id = '" + produto.getUnidadeId() + "', "
                    + "custo = '" + produto.getCusto() + "', "
                    + "ult_custo = '" + produto.getUltCusto() + "', "
                    + "valor = '" + produto.getValor() + "', "
                    + "est_max = '" + produto.getEstMax() + "', "
                    + "est_min = " + produto.getEstMin()
                    + " WHERE id = " + produto.getId();
            System.out.println("sql: " + sql);
            st.executeUpdate(sql);;
            return true;
        } catch (Exception e) {
            System.out.println("Erro atualizar produto = " + e);
            return false;
        }
    }

    @Override
    public boolean excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "DELETE FROM produto WHERE "
                    + "id  = " + id + "";
            //  System.out.println("sql: " + sql);
            st.execute(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao excluir produto = " + e);
            return false;
        }
    }

    public ArrayList<Produto> consultarTodosProdutos() {
        ArrayList produtos = new ArrayList();
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM Produto ORDER BY 1";
            System.out.println("sql: " + sql);

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Produto tmpProduto = new Produto();
                tmpProduto.setId(String.valueOf(resultado.getInt("id")));
                //grupoid
                tmpProduto.setGrupoId(String.valueOf(resultado.getInt("grupo_id")));
                Grupo tmpGrupo = (Grupo) new GrupoDao().consultarId(Integer.valueOf(tmpProduto.getGrupoId()));
                tmpProduto.setGrupoNome(tmpGrupo.getGrupo());
                //produto
                tmpProduto.setProduto(resultado.getString("produto"));
                //subgrupoid
                tmpProduto.setSubgrupoId(String.valueOf(resultado.getInt("subgrupo_id")));
                Subgrupo tmpSubgrupo = (Subgrupo) new SubgrupoDao().consultarId(Integer.valueOf(tmpProduto.getSubgrupoId()));
                tmpProduto.setSubgrupoNome(tmpSubgrupo.getSubgrupo());
                //categoriaid
                tmpProduto.setCategoriaId(String.valueOf(resultado.getInt("categoria_id")));
                Categoria tmpCategoria = (Categoria) new CategoriaDao().consultarId(Integer.valueOf(tmpProduto.getCategoriaId()));
                tmpProduto.setCategoriaNome(tmpCategoria.getCategoria());
                //bloqueioid
                tmpProduto.setBloqueioId(String.valueOf(resultado.getInt("bloqueio_id")));
                Bloqueio tmpBloqueio = (Bloqueio) new BloqueioDao().consultarId(Integer.valueOf(tmpProduto.getBloqueioId()));
                tmpProduto.setBloqueioNome(tmpBloqueio.getBloqueio());
                //tipoid
                tmpProduto.setTipoId(String.valueOf(resultado.getInt("tipo_id")));
                Tipo tmpTipo = (Tipo) new TipoDao().consultarId(Integer.valueOf(tmpProduto.getTipoId()));
                tmpProduto.setTipoNome(tmpTipo.getTipo());
                //unidadeid
                tmpProduto.setUnidadeId(String.valueOf(resultado.getInt("unidade_id")));
                UnMedida tmpUnMedida = (UnMedida) new UnMedidaDao().consultarId(Integer.valueOf(tmpProduto.getUnidadeId()));
                tmpProduto.setUnidadeNome(tmpUnMedida.getNome());
                tmpProduto.setCusto(resultado.getString("custo").replace(".", ""));
                tmpProduto.setUltCusto(resultado.getString("ult_custo").replace(".", ""));
                tmpProduto.setValor(resultado.getString("valor").replace(".", ""));
                tmpProduto.setEstMax(resultado.getString("est_max"));
                tmpProduto.setEstMin(resultado.getString("est_min"));
                produtos.add(tmpProduto);
            }
        } catch (Exception e) {
            System.out.println("Erro consultar Produto= " + e);
            return null;
        }
        return produtos;
    }

    @Override
    public ArrayList<Object> consultar(String produto) {
        ArrayList produtos = new ArrayList();
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM Produto WHERE "
                    + "produto iLIKE '%" + produto + "%' ORDER BY 1;";
            System.out.println("sql: " + sql);

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Produto tmpProduto = new Produto();
                tmpProduto.setId(String.valueOf(resultado.getInt("id")));
                tmpProduto.setProduto(resultado.getString("produto"));
                tmpProduto.setGrupoId(String.valueOf(resultado.getInt("grupo_id")));
                Grupo tmpGrupo = (Grupo) new GrupoDao().consultarId(Integer.valueOf(tmpProduto.getGrupoId()));
                tmpProduto.setGrupoNome(tmpGrupo.getGrupo());
                produtos.add(tmpProduto);
            }
        } catch (Exception e) {
            System.out.println("Erro consultar Produto= " + e);
            return null;
        }
        return produtos;
    }

    @Override
    public Object consultarId(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM Produto WHERE "
                    + "id = " + id + ";";

            //System.out.println("sql: " + sql);
            ResultSet resultado = st.executeQuery(sql);

            if (resultado.next()) {
                Produto tmpProduto = new Produto();
                tmpProduto.setId(String.valueOf(resultado.getInt("id")));
                //grupoid
                tmpProduto.setGrupoId(String.valueOf(resultado.getInt("grupo_id")));
                Grupo tmpGrupo = (Grupo) new GrupoDao().consultarId(Integer.valueOf(tmpProduto.getGrupoId()));
                tmpProduto.setGrupoNome(tmpGrupo.getGrupo());
                //produto
                tmpProduto.setProduto(resultado.getString("produto"));
                //subgrupoid
                tmpProduto.setSubgrupoId(String.valueOf(resultado.getInt("subgrupo_id")));
                Subgrupo tmpSubgrupo = (Subgrupo) new SubgrupoDao().consultarId(Integer.valueOf(tmpProduto.getSubgrupoId()));
                tmpProduto.setSubgrupoNome(tmpSubgrupo.getSubgrupo());
                //categoriaid
                tmpProduto.setCategoriaId(String.valueOf(resultado.getInt("categoria_id")));
                Categoria tmpCategoria = (Categoria) new CategoriaDao().consultarId(Integer.valueOf(tmpProduto.getCategoriaId()));
                tmpProduto.setCategoriaNome(tmpCategoria.getCategoria());
                //bloqueioid
                tmpProduto.setBloqueioId(String.valueOf(resultado.getInt("bloqueio_id")));
                Bloqueio tmpBloqueio = (Bloqueio) new BloqueioDao().consultarId(Integer.valueOf(tmpProduto.getBloqueioId()));
                tmpProduto.setBloqueioNome(tmpBloqueio.getBloqueio());
                //tipoid
                tmpProduto.setTipoId(String.valueOf(resultado.getInt("tipo_id")));
                Tipo tmpTipo = (Tipo) new TipoDao().consultarId(Integer.valueOf(tmpProduto.getTipoId()));
                tmpProduto.setTipoNome(tmpTipo.getTipo());
                //unidadeid
                tmpProduto.setUnidadeId(String.valueOf(resultado.getInt("unidade_id")));
                UnMedida tmpUnMedida = (UnMedida) new UnMedidaDao().consultarId(Integer.valueOf(tmpProduto.getUnidadeId()));
                tmpProduto.setUnidadeNome(tmpUnMedida.getNome());
                tmpProduto.setCusto(resultado.getString("custo").replace(".", ""));
                tmpProduto.setUltCusto(resultado.getString("ult_custo").replace(".", ""));
                tmpProduto.setValor(resultado.getString("valor").replace(".", ""));
                tmpProduto.setEstMax(resultado.getString("est_max"));
                tmpProduto.setEstMin(resultado.getString("est_min"));
                return tmpProduto;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro consultar Produto= " + e);
            return e.toString();
        }
    }

    @Override
    public Object consultarNome(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Object> consultarTodos(String sql) {

        ArrayList produtos = new ArrayList();
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            System.out.println("sql: " + sql);

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Produto tmpProduto = new Produto();
                tmpProduto.setId(String.valueOf(resultado.getInt("id")));
                //grupoid
                tmpProduto.setGrupoId(String.valueOf(resultado.getInt("grupo_id")));
                Grupo tmpGrupo = (Grupo) new GrupoDao().consultarId(Integer.valueOf(tmpProduto.getGrupoId()));
                tmpProduto.setGrupoNome(tmpGrupo.getGrupo());
                //produto
                tmpProduto.setProduto(resultado.getString("produto"));
                //subgrupoid
                tmpProduto.setSubgrupoId(String.valueOf(resultado.getInt("subgrupo_id")));
                Subgrupo tmpSubgrupo = (Subgrupo) new SubgrupoDao().consultarId(Integer.valueOf(tmpProduto.getSubgrupoId()));
                tmpProduto.setSubgrupoNome(tmpSubgrupo.getSubgrupo());
                //categoriaid
                tmpProduto.setCategoriaId(String.valueOf(resultado.getInt("categoria_id")));
                Categoria tmpCategoria = (Categoria) new CategoriaDao().consultarId(Integer.valueOf(tmpProduto.getCategoriaId()));
                tmpProduto.setCategoriaNome(tmpCategoria.getCategoria());
                //bloqueioid
                tmpProduto.setBloqueioId(String.valueOf(resultado.getInt("bloqueio_id")));
                Bloqueio tmpBloqueio = (Bloqueio) new BloqueioDao().consultarId(Integer.valueOf(tmpProduto.getBloqueioId()));
                tmpProduto.setBloqueioNome(tmpBloqueio.getBloqueio());
                //tipoid
                tmpProduto.setTipoId(String.valueOf(resultado.getInt("tipo_id")));
                Tipo tmpTipo = (Tipo) new TipoDao().consultarId(Integer.valueOf(tmpProduto.getTipoId()));
                tmpProduto.setTipoNome(tmpTipo.getTipo());
                //unidadeid
                tmpProduto.setUnidadeId(String.valueOf(resultado.getInt("unidade_id")));
                UnMedida tmpUnMedida = (UnMedida) new UnMedidaDao().consultarId(Integer.valueOf(tmpProduto.getUnidadeId()));
                tmpProduto.setUnidadeNome(tmpUnMedida.getNome());
                tmpProduto.setCusto(resultado.getString("custo").replace(".", ""));
                tmpProduto.setUltCusto(resultado.getString("ult_custo").replace(".", ""));
                tmpProduto.setValor(resultado.getString("valor").replace(".", ""));
                tmpProduto.setEstMax(resultado.getString("est_max"));
                tmpProduto.setEstMin(resultado.getString("est_min"));
                produtos.add(tmpProduto);
            }
        } catch (Exception e) {
            System.out.println("Erro consultar Produto= " + e);
            return null;
        }
        return produtos;
    }

    public ArrayList<Object> consultarIdCategoria(int idCategoria) {

        ArrayList produtos = new ArrayList();
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM Produto WHERE categoria_id = " + idCategoria + " ORDER BY 1";
            System.out.println("sql: " + sql);

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Produto tmpProduto = new Produto();
                tmpProduto.setId(String.valueOf(resultado.getInt("id")));
                //grupoid
                tmpProduto.setGrupoId(String.valueOf(resultado.getInt("grupo_id")));
                Grupo tmpGrupo = (Grupo) new GrupoDao().consultarId(Integer.valueOf(tmpProduto.getGrupoId()));
                tmpProduto.setGrupoNome(tmpGrupo.getGrupo());
                //produto
                tmpProduto.setProduto(resultado.getString("produto"));
                //subgrupoid
                tmpProduto.setSubgrupoId(String.valueOf(resultado.getInt("subgrupo_id")));
                Subgrupo tmpSubgrupo = (Subgrupo) new SubgrupoDao().consultarId(Integer.valueOf(tmpProduto.getSubgrupoId()));
                tmpProduto.setSubgrupoNome(tmpSubgrupo.getSubgrupo());
                //categoriaid
                tmpProduto.setCategoriaId(String.valueOf(resultado.getInt("categoria_id")));
                Categoria tmpCategoria = (Categoria) new CategoriaDao().consultarId(Integer.valueOf(tmpProduto.getCategoriaId()));
                tmpProduto.setCategoriaNome(tmpCategoria.getCategoria());
                //bloqueioid
                tmpProduto.setBloqueioId(String.valueOf(resultado.getInt("bloqueio_id")));
                Bloqueio tmpBloqueio = (Bloqueio) new BloqueioDao().consultarId(Integer.valueOf(tmpProduto.getBloqueioId()));
                tmpProduto.setBloqueioNome(tmpBloqueio.getBloqueio());
                //tipoid
                tmpProduto.setTipoId(String.valueOf(resultado.getInt("tipo_id")));
                Tipo tmpTipo = (Tipo) new TipoDao().consultarId(Integer.valueOf(tmpProduto.getTipoId()));
                tmpProduto.setTipoNome(tmpTipo.getTipo());
                //unidadeid
                tmpProduto.setUnidadeId(String.valueOf(resultado.getInt("unidade_id")));
                UnMedida tmpUnMedida = (UnMedida) new UnMedidaDao().consultarId(Integer.valueOf(tmpProduto.getUnidadeId()));
                tmpProduto.setUnidadeNome(tmpUnMedida.getNome());
                tmpProduto.setCusto(resultado.getString("custo").replace(".", ""));
                tmpProduto.setUltCusto(resultado.getString("ult_custo").replace(".", ""));
                tmpProduto.setValor(resultado.getString("valor").replace(".", ""));
                tmpProduto.setEstMax(resultado.getString("est_max"));
                tmpProduto.setEstMin(resultado.getString("est_min"));
                produtos.add(tmpProduto);
            }
        } catch (Exception e) {
            System.out.println("Erro consultar Produto= " + e);
            return null;
        }
        return produtos;
    }

    @Override
    public ArrayList<Object> consultarTodos() {

        ArrayList produtos = new ArrayList();
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM Produto ORDER BY 1";
            System.out.println("sql: " + sql);

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Produto tmpProduto = new Produto();
                tmpProduto.setId(String.valueOf(resultado.getInt("id")));
                //grupoid
                tmpProduto.setGrupoId(String.valueOf(resultado.getInt("grupo_id")));
                Grupo tmpGrupo = (Grupo) new GrupoDao().consultarId(Integer.valueOf(tmpProduto.getGrupoId()));
                tmpProduto.setGrupoNome(tmpGrupo.getGrupo());
                //produto
                tmpProduto.setProduto(resultado.getString("produto"));
                //subgrupoid
                tmpProduto.setSubgrupoId(String.valueOf(resultado.getInt("subgrupo_id")));
                Subgrupo tmpSubgrupo = (Subgrupo) new SubgrupoDao().consultarId(Integer.valueOf(tmpProduto.getSubgrupoId()));
                tmpProduto.setSubgrupoNome(tmpSubgrupo.getSubgrupo());
                //categoriaid
                tmpProduto.setCategoriaId(String.valueOf(resultado.getInt("categoria_id")));
                Categoria tmpCategoria = (Categoria) new CategoriaDao().consultarId(Integer.valueOf(tmpProduto.getCategoriaId()));
                tmpProduto.setCategoriaNome(tmpCategoria.getCategoria());
                //bloqueioid
                tmpProduto.setBloqueioId(String.valueOf(resultado.getInt("bloqueio_id")));
                Bloqueio tmpBloqueio = (Bloqueio) new BloqueioDao().consultarId(Integer.valueOf(tmpProduto.getBloqueioId()));
                tmpProduto.setBloqueioNome(tmpBloqueio.getBloqueio());
                //tipoid
                tmpProduto.setTipoId(String.valueOf(resultado.getInt("tipo_id")));
                Tipo tmpTipo = (Tipo) new TipoDao().consultarId(Integer.valueOf(tmpProduto.getTipoId()));
                tmpProduto.setTipoNome(tmpTipo.getTipo());
                //unidadeid
                tmpProduto.setUnidadeId(String.valueOf(resultado.getInt("unidade_id")));
                UnMedida tmpUnMedida = (UnMedida) new UnMedidaDao().consultarId(Integer.valueOf(tmpProduto.getUnidadeId()));
                tmpProduto.setUnidadeNome(tmpUnMedida.getNome());
                tmpProduto.setCusto(resultado.getString("custo").replace(".", ""));
                tmpProduto.setUltCusto(resultado.getString("ult_custo").replace(".", ""));
                tmpProduto.setValor(resultado.getString("valor").replace(".", ""));
                tmpProduto.setEstMax(resultado.getString("est_max"));
                tmpProduto.setEstMin(resultado.getString("est_min"));
                produtos.add(tmpProduto);
            }
        } catch (Exception e) {
            System.out.println("Erro consultar Produto= " + e);
            return null;
        }
        return produtos;
    }

}
