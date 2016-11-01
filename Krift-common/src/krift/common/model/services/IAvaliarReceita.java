/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.common.model.services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import krift.common.model.domain.Avaliacao;
import krift.common.model.domain.Denuncia;
import util.db.exception.NegocioException;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Aluno
 */
public interface IAvaliarReceita extends Remote{
    public boolean denunciar(Denuncia denuncia) throws PersistenciaException, NegocioException, RemoteException;
    public boolean avaliar(Avaliacao avaliacao) throws PersistenciaException, NegocioException, RemoteException;
    public boolean alterarAvaliacao(Avaliacao avaliacao) throws PersistenciaException, NegocioException, RemoteException;
}

