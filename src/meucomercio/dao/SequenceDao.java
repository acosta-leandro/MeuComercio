package meucomercio.dao;

import apoio.ConexaoBD;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by leandro on 15/07/16.
 */
public class SequenceDao {

    public static int atualizarSequence(String sequenceName){


        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select currval(sequenceName)";

            // System.out.println("sql: " + sql);

            ResultSet resultado = st.executeQuery(sql);

            if (resultado.next()) {

                int seq = resultado.getInt(0);
                return seq;
            } else {
                return 0;
            }
        } catch (Exception e) {
            System.out.println("Erro consultar sequence= " + e);
            return 0;
        }
    }

}
