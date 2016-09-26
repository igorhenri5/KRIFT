package krift.core.model.dao;

import java.util.ArrayList;
import util.db.exception.PersistenciaException;

public interface GenericDAO<Entidade> {

    long inserir(Entidade obj) throws PersistenciaException;
    void atualizar(Entidade obj) throws PersistenciaException;
    void excluir(long id) throws PersistenciaException;
    Entidade consultarPorId(long id) throws PersistenciaException;
    ArrayList<Entidade> listarTodos() throws PersistenciaException;

}
