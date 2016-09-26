package krift.core.model.dao;

import util.db.exception.PersistenciaException;

public interface GenericNamedDAO<Entidade> extends GenericDAO<Entidade> {
    
    Entidade consultarPorNome(String nome) throws PersistenciaException;
}
