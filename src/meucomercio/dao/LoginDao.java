/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meucomercio.dao;

import apoio.ConexaoBD;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import meucomercio.controller.principalController;
import meucomercio.entidades.Usuario;

/**
 *
 * @author Leandro Acosta <leandro.acosta292@hotmail.com>
 */
public class LoginDao {

    public boolean verificarUsuario(Usuario user) {
        ArrayList<Usuario> usuarios = buscartodosUsuarios();
        for (int i = 0; i < usuarios.size(); i++) {
            if (user.getLogin().equals(usuarios.get(i).getLogin())) {
                System.out.println("ACHOU USER");
                if (user.getSenha().equals(usuarios.get(i).getSenha())) {
                    System.out.println("Senha OK");
                    principalController.setUsuarioLogado(usuarios.get(i));
                    return true;
                }
            }
        }
        return false;
    }

    private ArrayList<Usuario> buscartodosUsuarios() {
        ArrayList usuarios = new ArrayList();
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM Usuario ORDER BY 1";
            // System.out.println("sql: " + sql);

            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                Usuario tmpUsuario = new Usuario();
                tmpUsuario.setId(String.valueOf(resultado.getInt("id")));
                tmpUsuario.setNome(resultado.getString("nome"));
                tmpUsuario.setLogin(resultado.getString("login"));
                tmpUsuario.setPerfil(resultado.getString("funcao"));
                tmpUsuario.setSenha(resultado.getString("senha"));
                usuarios.add(tmpUsuario);
            }
        } catch (Exception e) {
            System.out.println("Erro consultar Usuario= " + e);
            return null;
        }
        return usuarios;
    }

}
