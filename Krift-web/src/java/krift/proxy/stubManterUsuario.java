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
import krift.common.model.domain.Usuario;
import krift.common.model.services.IManterUsuario;
import util.db.exception.NegocioException;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Igor
 */
public class stubManterUsuario implements IManterUsuario{

    Registry registry;
    IManterUsuario manterUsuario;

    public stubManterUsuario() {
        try{
            registry = LocateRegistry.getRegistry("localhost",2345);
            manterUsuario = (IManterUsuario) registry.lookup("usuario");
        }catch(RemoteException | NotBoundException ex){
            throw new RuntimeException(ex);
        }
    }
    
    @Override
    public boolean logar(String nom_login, String Senha) throws PersistenciaException, NegocioException {
        
        try {
            return manterUsuario.logar(nom_login, Senha);
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;            
        }
    }

    @Override
    public boolean cadastrar(Usuario usuario) throws PersistenciaException, NegocioException {      
        
        try {           
            return manterUsuario.cadastrar(usuario);            
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;            
        }
    }

    @Override
    public boolean alterar(Usuario usuario) throws PersistenciaException, NegocioException {
        try {           
            return manterUsuario.alterar(usuario);
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public Usuario buscar(String nom_login) throws PersistenciaException, NegocioException {
        
        try {
            return manterUsuario.buscar(nom_login);            
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public ArrayList<Usuario> listar() throws PersistenciaException, NegocioException {
        try {
            return manterUsuario.listar();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;            
        }
    }
    
}
