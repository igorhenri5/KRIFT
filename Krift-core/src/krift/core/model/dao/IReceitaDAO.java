/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.core.model.dao;

import java.util.ArrayList;
import krift.common.model.domain.Receita;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Nome
 */
public interface IReceitaDAO extends GenericDAO<Receita>{

    public ArrayList<Receita> buscar(String sql) throws PersistenciaException;

    public ArrayList<Receita> recomendados(String nom_login) throws PersistenciaException;

    public ArrayList<Receita> listarPorUsuario(String nom_login) throws PersistenciaException;
    
}
