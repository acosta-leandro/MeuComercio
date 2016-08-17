package meucomercio.entidades;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by leandro on 05/07/16.
 */
public class UnMedida {

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getNome() {
        return nome.get();
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public String getSigla() {
        return sigla.get();
    }

    public StringProperty siglaProperty() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla.set(sigla);
    }
    

    StringProperty id = new SimpleStringProperty();
    StringProperty nome = new SimpleStringProperty();
    StringProperty sigla = new SimpleStringProperty();


}
