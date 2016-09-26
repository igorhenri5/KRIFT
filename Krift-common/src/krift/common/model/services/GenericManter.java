package krift.common.model.services;

import util.db.exception.NegocioException;
import util.db.exception.PersistenciaException;
import java.util.List;

public interface GenericManter<Entidade> {
    
    public void cadastrar(Entidade e) throws PersistenciaException, NegocioException;
    public void alterar(Entidade e) throws PersistenciaException, NegocioException;
    public Entidade buscarPorId(Integer id) throws PersistenciaException, NegocioException;
    public void excluir(Entidade e) throws PersistenciaException, NegocioException;
    public List<Entidade> listarTodos() throws PersistenciaException, NegocioException;

}
