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
 * @author Leandro Acosta <leandro.acosta292@hotmail.com>
 */
public class Usuario {

    public String getSenha() {
        return senha.get();
    }

    public StringProperty senhaProperty() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha.set(senha);
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

    public String getPerfil() {
        return perfil.get();
    }

    public StringProperty perfilProperty() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil.set(perfil);
    }

    public String getLogin() {
        return login.get();
    }

    public StringProperty loginProperty() {
        return login;
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    StringProperty login = new SimpleStringProperty();
    StringProperty senha = new SimpleStringProperty();
    StringProperty nome = new SimpleStringProperty();
    StringProperty perfil = new SimpleStringProperty();
    StringProperty id = new SimpleStringProperty();
}
