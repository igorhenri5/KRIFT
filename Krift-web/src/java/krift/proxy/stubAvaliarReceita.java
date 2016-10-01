/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.proxy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import krift.common.model.domain.Avaliacao;
import krift.common.model.domain.Comentario;
import krift.common.model.domain.Denuncia;
import krift.common.model.domain.Receita;
import krift.common.model.services.IAvaliarReceita;
import krift.common.util.AbstractInOut;
import krift.common.util.Request;
import util.db.exception.NegocioException;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Igor
 */
public class stubAvaliarReceita implements IAvaliarReceita {

    private Socket socket;
    private String host = "localhost";
    private int port = 2223;

    public stubAvaliarReceita(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public boolean denunciar(Denuncia denuncia) throws PersistenciaException, NegocioException {
        try {
            socket = new Socket(host, port);
            ObjectInputStream in = AbstractInOut.getObjectReader(socket);
            ObjectOutputStream out = AbstractInOut.getObjectWriter(socket);
            
            out.writeObject(Request.DENUNCIAR); 
            out.writeObject(denuncia);
            out.flush();
            
            boolean resposta = in.readBoolean();            
            return resposta;
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public boolean avaliar(Avaliacao avaliacao) throws PersistenciaException, NegocioException {
        try {
            socket = new Socket(host, port);
            ObjectInputStream in = AbstractInOut.getObjectReader(socket);
            ObjectOutputStream out = AbstractInOut.getObjectWriter(socket);
            
            out.writeObject(Request.AVALIAR);
            out.writeObject(avaliacao);
            out.flush();
            
            boolean resposta = in.readBoolean();            
            return resposta;
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public boolean alterarAvaliacao(Avaliacao avaliacao) throws PersistenciaException, NegocioException {
        try {
            socket = new Socket(host, port);
            ObjectInputStream in = AbstractInOut.getObjectReader(socket);
            ObjectOutputStream out = AbstractInOut.getObjectWriter(socket);
            
            out.writeObject(Request.ALTERAR_AVALIACAO);
            out.writeObject(avaliacao);
            out.flush();
            
            boolean resposta = in.readBoolean();            
            return resposta;
        } catch (IOException ex) {
            return false;
        }
    }


}
