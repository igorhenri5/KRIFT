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
import krift.common.model.services.IManterFavoritos;
import util.db.exception.NegocioException;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Igor
 */
public class stubManterFavoritos implements IManterFavoritos{
    
    Registry registry;
    IManterFavoritos manterFavoritos;
    
    public stubManterFavoritos() {
        try{
            registry = LocateRegistry.getRegistry("localhost",2345);
            manterFavoritos = (IManterFavoritos) registry.lookup("favoritos");
        }catch(RemoteException | NotBoundException ex){
            throw new RuntimeException(ex);
        }
    }
    
    @Override
    public boolean favoritar(String nom_login, long nro_seq_receita) throws PersistenciaException, NegocioException {
        try {      
            return manterFavoritos.favoritar(nom_login, nro_seq_receita);
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public ArrayList<Receita> listarFavoritos(String nom_login, long nro_seq_receita) throws PersistenciaException, NegocioException {
        try {           
            return manterFavoritos.listarFavoritos(nom_login, nro_seq_receita);
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public boolean desfavoritar(String nom_login, long nro_seq_receita) throws PersistenciaException, NegocioException {
        try {
            return manterFavoritos.desfavoritar(nom_login, nro_seq_receita);
        } catch (IOException ex) {
            return false;
        }
    }
    
}
