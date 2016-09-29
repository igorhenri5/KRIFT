/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.impl;

import model.dao.IProcedimentoDAO;
import model.dao.impl.ProcedimentoDAO;
import model.domain.Procedimento;
import model.service.IManterProcedimento;
import util.db.exception.NegocioException;
import util.db.exception.PersistenciaException;
import java.util.List;
import model.service.IManterProcedimento;

/**
 *
 * @author Administrador
 */
public class ManterProcedimento implements IManterProcedimento {

    @Override
    public void cadastrar(Procedimento e) throws PersistenciaException, NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterar(Procedimento e) throws PersistenciaException, NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Procedimento buscarPorId(Integer id) throws PersistenciaException, NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir(Procedimento e) throws PersistenciaException, NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Procedimento> listarTodos() throws PersistenciaException, NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
