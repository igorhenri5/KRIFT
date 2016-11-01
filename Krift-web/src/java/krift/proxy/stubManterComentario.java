/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.proxy;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import krift.common.model.domain.*;
import krift.common.model.services.*;
import util.db.exception.NegocioException;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Igor
 */
public class stubManterComentario implements IManterComentario {

    Registry registry;
    IManterComentario manterComentario;

    public stubManterComentario() {
        try{
            registry = LocateRegistry.getRegistry("localhost",2345);
            manterComentario = (IManterComentario) registry.lookup("comentario");
        }catch(RemoteException | NotBoundException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean comentar(Comentario comentario) throws PersistenciaException, NegocioException {
        try {
            return manterComentario.comentar(comentario);
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public boolean deletarComentario(Comentario comentario) throws PersistenciaException, NegocioException {
        try {
            return manterComentario.deletarComentario(comentario);
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public ArrayList<Comentario> comentariosDaReceita(long nro_seq_receita) throws PersistenciaException, NegocioException {
        try {
            return manterComentario.comentariosDaReceita(nro_seq_receita);
        } catch (IOException ex) {
            return null;
        }
    }

}
