/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meucomercio.entidades;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author leandro
 */
public class Produto {

    private StringProperty id = new SimpleStringProperty();
    private StringProperty produto = new SimpleStringProperty();
    private StringProperty grupoId = new SimpleStringProperty();
    private StringProperty grupoNome = new SimpleStringProperty();
    private StringProperty subgrupoId = new SimpleStringProperty();
    private StringProperty subgrupoNome = new SimpleStringProperty();
    private StringProperty categoriaId = new SimpleStringProperty();
    private StringProperty categoriaNome = new SimpleStringProperty();
    private StringProperty tipoId = new SimpleStringProperty();
    private StringProperty tipoNome = new SimpleStringProperty();
    private StringProperty bloqueioId = new SimpleStringProperty();
    private StringProperty bloqueioNome = new SimpleStringProperty();
    private StringProperty unidadeId = new SimpleStringProperty();
    private StringProperty unidadeNome = new SimpleStringProperty();
    private StringProperty custo = new SimpleStringProperty();
    private StringProperty ultCusto = new SimpleStringProperty();
    private StringProperty valor = new SimpleStringProperty();
    private StringProperty estMin = new SimpleStringProperty();
    private StringProperty estMax = new SimpleStringProperty();

    //id
    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }
    //produto

    public String getProduto() {
        return produto.get();
    }

    public StringProperty produtoProperty() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto.set(produto);
    }
    //grupoId

    public String getGrupoId() {
        return grupoId.get();
    }

    public StringProperty grupoIdProperty() {
        return grupoId;
    }

    public void setGrupoId(String grupoId) {
        this.grupoId.set(grupoId);
    }
    //grupoNome

    public String getGrupoNome() {
        return grupoNome.get();
    }

    public StringProperty GrupoNomeProperty() {
        return grupoNome;
    }

    public void setGrupoNome(String grupoNome) {
        this.grupoNome.set(grupoNome);
    }
    //subgrupoId

    public String getSubgrupoId() {
        return subgrupoId.get();
    }

    public StringProperty subgrupoIdProperty() {
        return subgrupoId;
    }

    public void setSubgrupoId(String subgrupoId) {
        this.subgrupoId.set(subgrupoId);
    }
    //subgrupoNome

    public String getSubgrupoNome() {
        return subgrupoNome.get();
    }

    public StringProperty SubgrupoNomeProperty() {
        return subgrupoNome;
    }

    public void setSubgrupoNome(String subgrupoNome) {
        this.subgrupoNome.set(subgrupoNome);
    }
    //categoriaId

    public String getCategoriaId() {
        return categoriaId.get();
    }

    public StringProperty categoriaIdProperty() {
        return categoriaId;
    }

    public void setCategoriaId(String categoriaId) {
        this.categoriaId.set(categoriaId);
    }
    //categoriaNome

    public String getCategoriaNome() {
        return categoriaNome.get();
    }

    public StringProperty categoriaNomeProperty() {
        return categoriaNome;
    }

    public void setCategoriaNome(String categoriaNome) {
        this.categoriaNome.set(categoriaNome);
    }
    //tipoId

    public String getTipoId() {
        return tipoId.get();
    }

    public StringProperty tipoIdProperty() {
        return tipoId;
    }

    public void setTipoId(String tipoId) {
        this.tipoId.set(tipoId);
    }
    //tipoNome

    public String getTipoNome() {
        return tipoNome.get();
    }

    public StringProperty tipoNomeProperty() {
        return tipoNome;
    }

    public void setTipoNome(String tipoNome) {
        this.tipoNome.set(tipoNome);
    }
    //bloqueioId

    public String getBloqueioId() {
        return bloqueioId.get();
    }

    public StringProperty bloqueioIdProperty() {
        return bloqueioId;
    }

    public void setBloqueioId(String bloqueioId) {
        this.bloqueioId.set(bloqueioId);
    }
    //bloqueioNome

    public String getBloqueioNome() {
        return bloqueioNome.get();
    }

    public StringProperty bloqueioNomeProperty() {
        return bloqueioNome;
    }

    public void setBloqueioNome(String bloqueioNome) {
        this.bloqueioNome.set(bloqueioNome);
    }

    //UnidadeId
    public String getUnidadeId() {
        return unidadeId.get();
    }

    public StringProperty unidadeIdProperty() {
        return unidadeId;
    }

    public void setUnidadeId(String unidadeId) {
        this.unidadeId.set(unidadeId);
    }

    //UnidadeNome
    public String getUnidadeNome() {
        return unidadeNome.get();
    }

    public StringProperty unidadeNomeProperty() {
        return unidadeNome;
    }

    public void setUnidadeNome(String unidadeNome) {
        this.unidadeNome.set(unidadeNome);
    }

    //custo
    public String getCusto() {
        return custo.get();
    }

    public StringProperty custoProperty() {
        return custo;
    }

    public void setCusto(String custo) {
        this.custo.set(custo);
    }

    //ultCusto
    public String getUltCusto() {
        return ultCusto.get();
    }

    public StringProperty ultCustoProperty() {
        return ultCusto;
    }

    public void setUltCusto(String ultCusto) {
        this.ultCusto.set(ultCusto);
    }

    //valor
    public String getValor() {
        return valor.get();
    }

    public StringProperty valorProperty() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor.set(valor);
    }

    //EstMin
    public String getEstMin() {
        return estMin.get();
    }

    public StringProperty estMinProperty() {
        return estMin;
    }

    public void setEstMin(String estMin) {
        this.estMin.set(estMin);
    }

    //EstMax
    public String getEstMax() {
        return estMax.get();
    }

    public StringProperty estMaxProperty() {
        return estMax;
    }

    public void setEstMax(String estMax) {
        this.estMax.set(estMax);
    }

}
