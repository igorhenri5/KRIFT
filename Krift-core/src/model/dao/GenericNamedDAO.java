
package util.db.persistence;

import util.db.exception.PersistenciaException;

public interface GenericNamedDAO<Entidade> extends GenericDAO<Entidade> {
    
    Entidade consultarPorNome(String nome) throws PersistenciaException;
}
