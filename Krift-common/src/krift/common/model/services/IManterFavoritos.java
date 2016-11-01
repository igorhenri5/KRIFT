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
public interface IManterFavoritos extends Remote{
    public boolean favoritar(String nom_login, long nro_seq_receita) throws PersistenciaException, NegocioException, RemoteException;
    public ArrayList<Receita> listarFavoritos(String nom_login, long nro_seq_receita) throws PersistenciaException, NegocioException, RemoteException;
    public boolean desfavoritar(String nom_login, long nro_seq_receita) throws PersistenciaException, NegocioException, RemoteException;
}
