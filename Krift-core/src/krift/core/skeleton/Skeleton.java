/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.core.skeleton;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import krift.common.model.domain.Avaliacao;
import krift.common.model.domain.Comentario;
import krift.common.model.domain.Denuncia;
import krift.common.model.domain.Receita;
import krift.common.model.domain.Usuario;
import krift.common.model.services.IAvaliarReceita;
import krift.common.model.services.IManterComentario;
import krift.common.model.services.IManterFavoritos;
import krift.common.model.services.IManterReceita;
import krift.common.model.services.IManterUsuario;
import krift.common.util.AbstractInOut;
import krift.common.util.Request;
import krift.core.model.service.impl.AvaliarReceita;
import krift.core.model.service.impl.ManterComentario;
import krift.core.model.service.impl.ManterFavoritos;
import krift.core.model.service.impl.ManterReceita;
import krift.core.model.service.impl.ManterUsuario;
import util.db.exception.NegocioException;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Nome
 */
public class Skeleton implements Runnable{

    private Socket socket;

    public Skeleton(Socket socket) {
        this.socket = socket;
    }

    private Socket getSocket() {
        return this.socket;
    }
    
    
    public void process() throws ClassNotFoundException, PersistenciaException, NegocioException {
        ObjectOutputStream writer;
        ObjectInputStream reader;

        try {

            writer = AbstractInOut.getObjectWriter(this.getSocket());
            reader = AbstractInOut.getObjectReader(this.getSocket());

            Request command;
            command = (Request) reader.readObject();
            
            IManterUsuario manterUsuario = new ManterUsuario();
            IManterFavoritos manterFavoritos = new ManterFavoritos();
            IManterReceita manterReceita = new ManterReceita();
            IAvaliarReceita avaliarReceita = new AvaliarReceita();
            IManterComentario manterComentario = new ManterComentario();
            
            Usuario usuario;
            Receita receita;
            Comentario comentario;
            String nome, senha, query;
            long id;
            Avaliacao avaliacao;
            Denuncia denuncia;
            
            System.out.println("TRAB");
            
            switch (command) {                
                case CADASTRAR:
                    usuario = (Usuario)reader.readObject();
                    writer.writeObject(manterUsuario.cadastrar(usuario));
                    break;
                case LOGAR:
                    nome = (String)reader.readObject();
                    senha = (String)reader.readObject();
                    writer.writeObject(manterUsuario.logar(nome, senha));
                    break;
                case ALTERAR_PERFIL:
                    usuario = (Usuario)reader.readObject();
                    writer.writeObject(manterUsuario.alterar(usuario));
                    break;
                case VISITAR_PERFIL:
                    nome = (String)reader.readObject();
                    writer.writeObject(manterUsuario.buscar(nome));
                    break;
                case CRIAR_RECEITA:
                    receita = (Receita)reader.readObject();
                    writer.writeObject(manterReceita.criar(receita));
                    break;
                case ALTERAR_RECEITA:
                    receita = (Receita)reader.readObject();
                    writer.writeObject(manterReceita.alterar(receita));
                    break;
                case EXCLUIR_RECEITA:
                    id = reader.readLong();
                    writer.writeObject(manterReceita.excluir(id));
                    break;
                case BUSCAR_RECEITAS:
                    query = (String)reader.readObject();
                    writer.writeObject(manterReceita.buscar(query));
                    break;
                case LISTAR_RECEITAS:
                    nome = (String)reader.readObject();
                    writer.writeObject(manterReceita.listarReceitasPorUsuario(nome));
                    break;
                case LISTAR_RECOMENDADAS:
                    nome = (String)reader.readObject();
                    writer.writeObject(manterReceita.listarReceitasRecomendadas(nome));
                    break;
                case AVALIAR:
                    avaliacao = (Avaliacao)reader.readObject();
                    writer.writeObject(avaliarReceita.avaliar(avaliacao));
                    break;
                case ALTERAR_AVALIACAO:
                    avaliacao = (Avaliacao)reader.readObject();
                    writer.writeObject(avaliarReceita.alterarAvaliacao(avaliacao));
                    break;
                case COMENTAR:
                    comentario = (Comentario)reader.readObject();
                    writer.writeObject(manterComentario.comentar(comentario));
                    break;
                case EXCLUIR_COMENTARIO:
                    comentario = (Comentario)reader.readObject();
                    writer.writeObject(manterComentario.deletarComentario(comentario));
                    break;
                case DENUNCIAR:
                    denuncia = (Denuncia)reader.readObject();
                    writer.writeObject(avaliarReceita.denunciar(denuncia));
                    break;
                case FAVORITAR:
                    nome = (String)reader.readObject();
                    id = reader.readLong();
                    writer.writeObject(manterFavoritos.favoritar(nome, id));
                    break;
                case REMOVER_FAVORITO:
                    nome = (String)reader.readObject();
                    id = reader.readLong();
                    writer.writeObject(manterFavoritos.favoritar(nome, id));
                    break;
                case VISITAR_RECEITA:
                    query = (String)reader.readObject();
                    writer.writeObject(manterReceita.buscar(query));
                    break;
                case LISTAR_FAVORITOS:
                    nome = (String)reader.readObject();
                    id = reader.readLong();
                    writer.writeObject(manterFavoritos.listarFavoritos(nome, id));
                    break;
                case LISTAR_HISTORICO:
                    break;
                case LISTAR_COMENTARIOS:
                    nome = (String)reader.readObject();
                    id = reader.readLong();
                    writer.writeObject(manterFavoritos.listarFavoritos(nome, id));
                    break;
                case LISTAR_RANKING:
                    writer.writeObject(manterUsuario.listar());
                    break;
                    
            }            
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Skeleton.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    @Override
    public void run() {
        try {
            this.process();
            this.getSocket().close();
            
        } catch ( PersistenciaException | NegocioException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(Skeleton.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }
}
