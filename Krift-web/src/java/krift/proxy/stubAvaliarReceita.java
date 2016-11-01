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
import krift.common.model.domain.Avaliacao;
import krift.common.model.domain.Denuncia;
import krift.common.model.services.IAvaliarReceita;
import util.db.exception.NegocioException;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Igor
 */
public class stubAvaliarReceita implements IAvaliarReceita {

    Registry registry;
    IAvaliarReceita avaliar;
    
    public stubAvaliarReceita() {
        try{
            registry = LocateRegistry.getRegistry("localhost",2345);
            avaliar = (IAvaliarReceita) registry.lookup("avaliar");
        }catch(RemoteException | NotBoundException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean denunciar(Denuncia denuncia) throws PersistenciaException, NegocioException {
        try {         
            return avaliar.denunciar(denuncia);
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public boolean avaliar(Avaliacao avaliacao) throws PersistenciaException, NegocioException {
        try {          
            return avaliar.avaliar(avaliacao);
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public boolean alterarAvaliacao(Avaliacao avaliacao) throws PersistenciaException, NegocioException {
        try {
            return avaliar.alterarAvaliacao(avaliacao);
        } catch (IOException ex) {
            return false;
        }
    }


}
