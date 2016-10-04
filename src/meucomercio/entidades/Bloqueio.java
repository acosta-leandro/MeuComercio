package meucomercio.entidades;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by leandro on 05/07/16.
 */
public class Bloqueio {

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getBloqueio() {
        return bloqueio.get();
    }

    public StringProperty bloqueioProperty() {
        return bloqueio;
    }

    public void setBloqueio(String bloqueio) {
        this.bloqueio.set(bloqueio);
    }

    StringProperty id = new SimpleStringProperty();
    StringProperty bloqueio = new SimpleStringProperty();



}
