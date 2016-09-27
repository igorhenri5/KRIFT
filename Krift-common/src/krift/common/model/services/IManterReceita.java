/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.common.model.services;

import java.util.ArrayList;
import krift.common.model.domain.Receita;
import util.db.exception.NegocioException;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Aluno
 */
public interface IManterReceita{
    public boolean criar(Receita receita) throws PersistenciaException, NegocioException;
    public boolean alterar(Receita receita) throws PersistenciaException, NegocioException;
    public boolean excluir(long id) throws PersistenciaException, NegocioException;
    public Receita visualizar(long id) throws PersistenciaException, NegocioException;
    public ArrayList<Receita> buscar(String sql) throws PersistenciaException, NegocioException;
    public ArrayList<Receita> listarReceitasRecomendadas(String nom_login) throws PersistenciaException, NegocioException;
    public ArrayList<Receita> listarReceitasPorUsuario(String nom_login) throws PersistenciaException, NegocioException;    
    
}

