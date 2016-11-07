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
 * @author Leandro
 */
public class Comanda {
    
    
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
    
    //dt_abertura
    public String getDtAbertura() {
        return dtAbertura.get();
    }
    public StringProperty dtAberturaProperty() {
        return dtAbertura;
    }
    public void setDtAbertura(String dtAbertura) {
        this.dtAbertura.set(dtAbertura);
    }
    
    //dt_encerramento
    public String getDtEncerramento() {
        return dtEncerramento.get();
    }
    public StringProperty dtEncerramento() {
        return dtEncerramento;
    }
    public void setDtEncerramento(String dtEncerramento) {
        this.dtEncerramento.set(dtEncerramento);
    }
    
     //estado
    public String getEstado() {
        return estado.get();
    }
    public StringProperty estado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado.set(estado);
    }  
    

    StringProperty id = new SimpleStringProperty();
    StringProperty valor = new SimpleStringProperty();
    StringProperty dtAbertura = new SimpleStringProperty();
    StringProperty dtEncerramento = new SimpleStringProperty();
    StringProperty estado = new SimpleStringProperty();
    
    
}
