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
import krift.common.model.domain.Receita;
import krift.common.model.services.IManterReceita;
import krift.common.util.AbstractInOut;
import krift.common.util.Request;
import util.db.exception.NegocioException;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Igor
 */
public class stubManterReceita implements IManterReceita{
    
    private Socket socket;
    private String host = "localhost";
    private int port = 2223;

    public stubManterReceita(String host, int port) {
        this.host = host;
        this.port = port;
    }
    
    @Override
    public boolean criar(Receita receita) throws PersistenciaException, NegocioException {
        try {
            socket = new Socket(host, port);
            ObjectInputStream in = AbstractInOut.getObjectReader(socket);
            ObjectOutputStream out = AbstractInOut.getObjectWriter(socket);
            
            out.writeObject(Request.CRIAR_RECEITA);
            out.writeObject(receita);
            out.flush();
            
            boolean resposta = in.readBoolean();            
            return resposta;
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public boolean alterar(Receita receita) throws PersistenciaException, NegocioException {
        try {
            socket = new Socket(host, port);
            ObjectInputStream in = AbstractInOut.getObjectReader(socket);
            ObjectOutputStream out = AbstractInOut.getObjectWriter(socket);
            
            out.writeObject(Request.ALTERAR_RECEITA);
            out.writeObject(receita);
            out.flush();
            
            boolean resposta = in.readBoolean();            
            return resposta;
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public boolean excluir(long id) throws PersistenciaException, NegocioException {
        try {
            socket = new Socket(host, port);
            ObjectInputStream in = AbstractInOut.getObjectReader(socket);
            ObjectOutputStream out = AbstractInOut.getObjectWriter(socket);
            
            out.writeObject(Request.EXCLUIR_RECEITA);
            out.writeObject(id);
            out.flush();
            
            boolean resposta = in.readBoolean();            
            return resposta;
        } catch (IOException ex) {
            return false;
        }
    }

     @Override
    public ArrayList<Receita> listarReceitasRecomendadas(String nom_login) throws PersistenciaException, NegocioException {
        try {
            socket = new Socket(host, port);
            ObjectInputStream in = AbstractInOut.getObjectReader(socket);
            ObjectOutputStream out = AbstractInOut.getObjectWriter(socket);
            out.writeObject(Request.LISTAR_RECOMENDADAS);
            out.flush();
            ArrayList<Receita> resposta = (ArrayList<Receita>) in.readObject();
            return resposta;
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }
    }

    @Override
    public ArrayList<Receita> listarReceitasPorUsuario(String nom_login) throws PersistenciaException, NegocioException {
        try {
            socket = new Socket(host, port);
            ObjectInputStream in = AbstractInOut.getObjectReader(socket);
            ObjectOutputStream out = AbstractInOut.getObjectWriter(socket);
            out.writeObject(Request.LISTAR_RECEITAS);
            out.flush();
            ArrayList<Receita> resposta = (ArrayList<Receita>) in.readObject();
            return resposta;
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }
    }

    @Override
    public Receita visualizar(long id) throws PersistenciaException, NegocioException {
        try {
            socket = new Socket(host, port);
            ObjectInputStream in = AbstractInOut.getObjectReader(socket);
            ObjectOutputStream out = AbstractInOut.getObjectWriter(socket);
            out.writeObject(Request.VISITAR_RECEITA);
            out.writeObject(id);
            out.flush();
            Receita resposta = (Receita) in.readObject();
            return resposta;
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }
    }

    @Override
    public ArrayList<Receita> buscar(String search, String idt_tendencia) throws PersistenciaException, NegocioException {
        try {
            socket = new Socket(host, port);
            ObjectInputStream in = AbstractInOut.getObjectReader(socket);
            ObjectOutputStream out = AbstractInOut.getObjectWriter(socket);
            out.writeObject(Request.BUSCAR_RECEITAS);
            out.writeObject(search);
            out.writeObject(idt_tendencia);
            out.flush();
            ArrayList<Receita> resposta = (ArrayList<Receita>) in.readObject();
            return resposta;
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }
    }
    
}
