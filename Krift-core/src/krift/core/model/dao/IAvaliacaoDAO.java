/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.core.model.dao;

import krift.common.model.domain.Avaliacao;
import krift.common.model.domain.Denuncia;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Nome
 */
public interface IAvaliacaoDAO {
    public boolean avaliar(Avaliacao avaliacao) throws PersistenciaException;
    public boolean alterar(Avaliacao avaliacao) throws PersistenciaException;
    public Avaliacao consultaAvaliacao(Avaliacao avaliacao) throws PersistenciaException;
    public Avaliacao avaliacaoReceita(Avaliacao avaliacao) throws PersistenciaException;
    public boolean denunciar(Denuncia denuncia) throws PersistenciaException;
}
