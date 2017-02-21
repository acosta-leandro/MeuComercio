/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meucomercio.entidades;

/**
 *
 * @author Leandro Acosta <leandro.acosta292@hotmail.com>
 */
public class Pedido {
    
    private int idComanda;
    private int idProduto;
    private int idPedido;
    private String status;
    private String nomeComanda;
    private String nomeProduto;
    
    public int getIdComanda() {
        return idComanda;
    }

    public void setIdComanda(int idComanda) {
        this.idComanda = idComanda;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNomeComanda() {
        return nomeComanda;
    }

    public void setNomeComanda(String nomeComanda) {
        this.nomeComanda = nomeComanda;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
    
    
}
