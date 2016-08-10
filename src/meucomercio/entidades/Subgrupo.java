package meucomercio.entidades;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by leandro on 05/07/16.
 */
public class Subgrupo {

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getSubgrupo() {
        return subgrupo.get();
    }

    public StringProperty subgrupoProperty() {
        return subgrupo;
    }

    public void setSubgrupo(String subgrupo) {
        this.subgrupo.set(subgrupo);
    }

    public String getGrupoId() {
        return grupoId.get();
    }

    public StringProperty GrupoIdProperty() {
        return grupoId;
    }

    public void setGrupoId(String grupoId) {
        this.grupoId.set(grupoId);
    }
    
        public String getGrupoNome() {
        return grupoNome.get();
    }

    public StringProperty GrupoNomeProperty() {
        return grupoNome;
    }

    public void setGrupoNome(String grupoNome) {
        this.grupoNome.set(grupoNome);
    }

    StringProperty id = new SimpleStringProperty();
    StringProperty subgrupo = new SimpleStringProperty();
    StringProperty grupoId = new SimpleStringProperty();
    StringProperty grupoNome = new SimpleStringProperty();

}
