/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.core.model.service.impl;

import java.util.ArrayList;
import java.util.List;
import krift.common.model.domain.Comentario;
import krift.common.model.domain.Receita;
import krift.common.model.services.IManterComentario;
import krift.core.model.dao.IComentarioDAO;
import krift.core.model.dao.impl.ComentarioDAO;
import util.db.exception.NegocioException;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Nome
 */
public class ManterComentario implements IManterComentario{

    @Override
    public boolean comentar(Comentario comentario) throws PersistenciaException, NegocioException {
        IComentarioDAO comentarioDAO = new ComentarioDAO();
        return comentarioDAO.comentar(comentario);
    }

    @Override
    public boolean deletarComentario(Comentario comentario) throws PersistenciaException, NegocioException {
        IComentarioDAO comentarioDAO = new ComentarioDAO();
        return comentarioDAO.comentar(comentario);
    }

    @Override
    public ArrayList<Comentario> comentariosDaReceita(long nro_seq_receita) throws PersistenciaException, NegocioException {
        IComentarioDAO comentarioDAO = new ComentarioDAO();
        return comentarioDAO.comentariosDaReceita(nro_seq_receita);
    }
    
}
