package util.db.persistence;

import util.db.exception.PersistenciaException;
import java.util.List;

public interface GenericDAO<Entidade> {

    Integer inserir(Entidade obj) throws PersistenciaException;
    void atualizar(Entidade obj) throws PersistenciaException;
    void excluir(Integer id) throws PersistenciaException;
    Entidade consultarPorId(Integer id) throws PersistenciaException;
    List<Entidade> listarTodos() throws PersistenciaException;

}
