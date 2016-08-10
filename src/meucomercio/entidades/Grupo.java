package meucomercio.entidades;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by leandro on 05/07/16.
 */
public class Grupo {

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getGrupo() {
        return grupo.get();
    }

    public StringProperty grupoProperty() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo.set(grupo);
    }

    StringProperty id = new SimpleStringProperty();
    StringProperty grupo = new SimpleStringProperty();



}

