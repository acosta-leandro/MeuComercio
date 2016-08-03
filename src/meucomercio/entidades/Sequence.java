package meucomercio.entidades;

import meucomercio.dao.SequenceDao;

/**
 * Created by leandro on 15/07/16.
 */
public class Sequence {


    private static int categoriaSeq;
    private static int grupoSeq;
    private static int subgrupoSeq;


    public static int getCategoriaSeq() {
        categoriaSeq = SequenceDao.atualizarSequence("categoriaSeq");
        return categoriaSeq;
    }

    public static int getGrupoSeq() {
        grupoSeq = SequenceDao.atualizarSequence("grupoSeq");
        return grupoSeq;
    }

    public static int getSubgrupoSeq() {
        subgrupoSeq = SequenceDao.atualizarSequence("subgrupoSeq");
        return subgrupoSeq;
    }

}
