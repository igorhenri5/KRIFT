/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.common.model.services;

import krift.common.model.domain.Avaliacao;
import krift.common.model.domain.Comentario;
import krift.common.model.domain.Denuncia;
import krift.common.model.domain.Receita;
import util.db.exception.NegocioException;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Aluno
 */
public interface IAvaliarReceita{
    public boolean denunciar(Denuncia denuncia) throws PersistenciaException, NegocioException;
    public boolean comentar(Comentario comentario) throws PersistenciaException, NegocioException;
    public boolean excluirComentario(Comentario comentario) throws PersistenciaException, NegocioException;
    public boolean avaliar(Avaliacao avaliacao) throws PersistenciaException, NegocioException;
    public boolean alterarAvaliacao(Avaliacao avaliacao) throws PersistenciaException, NegocioException;
    public boolean listarComentarios(Receita receita) throws PersistenciaException, NegocioException;
}

