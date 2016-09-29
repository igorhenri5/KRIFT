/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.core.model.service.impl;

import krift.common.model.domain.Avaliacao;
import krift.common.model.domain.Denuncia;
import krift.common.model.services.IAvaliarReceita;
import krift.core.model.dao.IAvaliacaoDAO;
import krift.core.model.dao.impl.AvaliacaoDAO;
import util.db.exception.NegocioException;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Aluno
 */
public class AvaliarReceita implements IAvaliarReceita{

    @Override
    public boolean denunciar(Denuncia denuncia) throws PersistenciaException, NegocioException {
        if(denuncia.getDes_comentario().isEmpty()){
            throw new NegocioException("Não é permitido denunciar sem exclarecer o motivo");
        }
        IAvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
        return avaliacaoDAO.denunciar(denuncia);
    }
    
    @Override
    public boolean avaliar(Avaliacao avaliacao) throws PersistenciaException, NegocioException {
        IAvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
        return avaliacaoDAO.avaliar(avaliacao);
    }

    @Override
    public boolean alterarAvaliacao(Avaliacao avaliacao) throws PersistenciaException, NegocioException {
        IAvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
        return avaliacaoDAO.alterar(avaliacao);
    }
       
}
