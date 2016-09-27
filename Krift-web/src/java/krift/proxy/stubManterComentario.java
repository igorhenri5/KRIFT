/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.proxy;

import java.util.List;
import krift.common.model.domain.Comentario;
import krift.common.model.services.IManterComentario;
import util.db.exception.NegocioException;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Igor
 */
public class stubManterComentario implements IManterComentario{

    @Override
    public void cadastrar(Comentario e) throws PersistenciaException, NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterar(Comentario e) throws PersistenciaException, NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Comentario buscarPorId(long id) throws PersistenciaException, NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir(long e) throws PersistenciaException, NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Comentario> listarTodos() throws PersistenciaException, NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
