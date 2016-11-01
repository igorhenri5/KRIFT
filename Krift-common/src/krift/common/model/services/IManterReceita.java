/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.common.model.services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import krift.common.model.domain.Receita;
import util.db.exception.NegocioException;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Aluno
 */
public interface IManterReceita extends Remote{
    public boolean criar(Receita receita) throws PersistenciaException, NegocioException, RemoteException;
    public boolean alterar(Receita receita) throws PersistenciaException, NegocioException, RemoteException;
    public boolean excluir(long id) throws PersistenciaException, NegocioException, RemoteException;
    public Receita visualizar(long id) throws PersistenciaException, NegocioException, RemoteException;
    public ArrayList<Receita> buscar(String search, String idt_tendencia) throws PersistenciaException, NegocioException, RemoteException;
    public ArrayList<Receita> listarReceitasRecomendadas(String nom_login) throws PersistenciaException, NegocioException, RemoteException;
    public ArrayList<Receita> listarReceitasPorUsuario(String nom_login) throws PersistenciaException, NegocioException, RemoteException;    
    
}

