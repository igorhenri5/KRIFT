/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.core.model.dao;

import java.util.ArrayList;
import krift.common.model.domain.Comentario;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Nome
 */
public interface IComentarioDAO {

    boolean comentar(Comentario comentario) throws PersistenciaException;

    ArrayList<Comentario> comentariosDaReceita(long nro_seq_receita) throws PersistenciaException;

    boolean detetarComentario(Comentario comentario) throws PersistenciaException;
    
}
