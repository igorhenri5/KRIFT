package krift.core.model.dao;

import java.util.ArrayList;
import util.db.exception.PersistenciaException;

public interface GenericDAO<Entidade> {

    boolean inserir(Entidade obj) throws PersistenciaException;
    boolean atualizar(Entidade obj) throws PersistenciaException;
    boolean excluir(long id) throws PersistenciaException;
    Entidade consultarPorId(long id) throws PersistenciaException;
    ArrayList<Entidade> listarTodos() throws PersistenciaException;

}
