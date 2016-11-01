/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.common.model.services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import krift.common.model.domain.Comentario;
import util.db.exception.NegocioException;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Nome
 */
public interface IManterComentario extends Remote{
    public boolean comentar(Comentario comentario) throws PersistenciaException, NegocioException, RemoteException;
    public boolean deletarComentario(Comentario comentario) throws PersistenciaException, NegocioException, RemoteException;
    public ArrayList<Comentario> comentariosDaReceita(long nro_seq_receita) throws PersistenciaException, NegocioException, RemoteException;
}
