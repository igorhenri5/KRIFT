/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.core.model.dao;

import java.util.ArrayList;
import krift.common.model.domain.Procedimento;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Nome
 */
public interface IProcedimentoDAO {
    
    public long inserir(Procedimento procedimento) throws PersistenciaException ;
    public void excluir(Procedimento procedimento) throws PersistenciaException ;
    public ArrayList<Procedimento> listarPorReceita(long nro_seq_receita) throws PersistenciaException ;

}
