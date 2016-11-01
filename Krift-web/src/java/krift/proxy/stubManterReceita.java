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
import krift.common.model.domain.Receita;
import krift.common.model.services.IManterReceita;
import util.db.exception.NegocioException;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Igor
 */
public class stubManterReceita implements IManterReceita{
    
    Registry registry;
    IManterReceita manterReceita;

    public stubManterReceita() {
        try{
            registry = LocateRegistry.getRegistry("localhost",2345);
            manterReceita = (IManterReceita) registry.lookup("receita");
        }catch(RemoteException | NotBoundException ex){
            throw new RuntimeException(ex);
        }
    }
    
    @Override
    public boolean criar(Receita receita) throws PersistenciaException, NegocioException {
        try {          
            return manterReceita.criar(receita);
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public boolean alterar(Receita receita) throws PersistenciaException, NegocioException {
        try {           
            return manterReceita.alterar(receita);
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public boolean excluir(long id) throws PersistenciaException, NegocioException {
        try {
            return manterReceita.excluir(id);
        } catch (IOException ex) {
            return false;
        }
    }

     @Override
    public ArrayList<Receita> listarReceitasRecomendadas(String nom_login) throws PersistenciaException, NegocioException {
        try {
            return manterReceita.listarReceitasRecomendadas(nom_login);
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public ArrayList<Receita> listarReceitasPorUsuario(String nom_login) throws PersistenciaException, NegocioException {
        try {
            return manterReceita.listarReceitasPorUsuario(nom_login);
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public Receita visualizar(long id) throws PersistenciaException, NegocioException {
        try {
            return manterReceita.visualizar(id);
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public ArrayList<Receita> buscar(String search, String idt_tendencia) throws PersistenciaException, NegocioException {
        try {
            return manterReceita.buscar(search, idt_tendencia);
        } catch (IOException ex) {
            return null;
        }
    }
    
}
