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
import java.util.ArrayList;
import java.util.List;
import krift.common.model.domain.*;
import krift.common.model.services.*;
import krift.common.util.AbstractInOut;
import krift.common.util.Request;
import util.db.exception.NegocioException;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Igor
 */
public class stubManterComentario implements IManterComentario {

    private Socket socket;
    private String host = "localhost";
    private int port = 2223;

    public stubManterComentario(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public boolean comentar(Comentario comentario) throws PersistenciaException, NegocioException {
        try {
            socket = new Socket(host, port);
            ObjectInputStream in = AbstractInOut.getObjectReader(socket);
            ObjectOutputStream out = AbstractInOut.getObjectWriter(socket);

            out.writeObject(Request.COMENTAR);
            out.writeObject(comentario);
            out.flush();

            boolean resposta = in.readBoolean();
            return resposta;
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public boolean deletarComentario(Comentario comentario) throws PersistenciaException, NegocioException {
        try {
            socket = new Socket(host, port);
            ObjectInputStream in = AbstractInOut.getObjectReader(socket);
            ObjectOutputStream out = AbstractInOut.getObjectWriter(socket);

            out.writeObject(Request.EXCLUIR_COMENTARIO);
            out.writeObject(comentario);
            out.flush();

            boolean resposta = in.readBoolean();
            return resposta;
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public ArrayList<Comentario> comentariosDaReceita(long nro_seq_receita) throws PersistenciaException, NegocioException {
        try {
            socket = new Socket(host, port);
            ObjectInputStream in = AbstractInOut.getObjectReader(socket);
            ObjectOutputStream out = AbstractInOut.getObjectWriter(socket);

            out.writeObject(Request.LISTAR_COMENTARIOS);
            out.writeObject(nro_seq_receita);
            out.flush();

            ArrayList<Comentario> resposta = (ArrayList) in.readObject();
            return resposta;
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }
    }

}
