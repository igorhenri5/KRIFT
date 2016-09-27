/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.core.model.service.impl;

import java.util.ArrayList;
import krift.common.model.domain.Receita;
import krift.common.model.services.IManterReceita;
import krift.core.model.dao.IReceitaDAO;
import krift.core.model.dao.impl.ReceitaDAO;
import util.db.exception.NegocioException;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Aluno
 */
public class ManterReceita implements IManterReceita{

    @Override
    public boolean criar(Receita receita) throws PersistenciaException, NegocioException {
        IReceitaDAO receitaDAO = new ReceitaDAO();
        return receitaDAO.inserir(receita);
    }

    @Override
    public boolean alterar(Receita receita) throws PersistenciaException, NegocioException {
        IReceitaDAO receitaDAO = new ReceitaDAO();
        return receitaDAO.atualizar(receita);
    }

    @Override
    public boolean excluir(long id) throws PersistenciaException, NegocioException {
        IReceitaDAO receitaDAO = new ReceitaDAO();
        return receitaDAO.excluir(id);
    }

    @Override
    public ArrayList<Receita> buscar(String sql) throws PersistenciaException, NegocioException {
        IReceitaDAO receitaDAO = new ReceitaDAO();
        return receitaDAO.buscar(sql);
    }

    @Override
    public ArrayList<Receita> listarReceitasRecomendadas(String nom_login) throws PersistenciaException, NegocioException {
        IReceitaDAO receitaDAO = new ReceitaDAO();
        return receitaDAO.recomendados(nom_login);
    }

    @Override
    public ArrayList<Receita> listarReceitasPorUsuario(String nom_login) throws PersistenciaException, NegocioException {
        IReceitaDAO receitaDAO = new ReceitaDAO();
        return receitaDAO.listarPorUsuario(nom_login);
    }

    @Override
    public Receita visualizar(long id) throws PersistenciaException, NegocioException {
        IReceitaDAO receitaDAO = new ReceitaDAO();
        return receitaDAO.consultarPorId(id);
    }
}
