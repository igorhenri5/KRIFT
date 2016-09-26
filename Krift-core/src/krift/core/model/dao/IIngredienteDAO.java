/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.core.model.dao;

import java.util.ArrayList;
import krift.common.model.domain.Ingrediente;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Nome
 */
public interface IIngredienteDAO {
    public long inserir(Ingrediente ingrediente) throws PersistenciaException ;
    public void excluir(Ingrediente ingrediente) throws PersistenciaException ;
    public ArrayList<Ingrediente> listarPorReceita(long nro_seq_receita) throws PersistenciaException;
}
