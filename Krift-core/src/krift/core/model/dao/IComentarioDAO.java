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

    void comentar(Comentario comentario) throws PersistenciaException;

    ArrayList<Comentario> comentariosDaReceita(Comentario comentario) throws PersistenciaException;

    void detetarComentario(Comentario comentario) throws PersistenciaException;
    
}
