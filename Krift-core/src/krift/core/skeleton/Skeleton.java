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
import krift.common.model.services.IAvaliarReceita;
import krift.common.model.services.IManterFavoritos;
import krift.common.model.services.IManterPreferencias;
import krift.common.model.services.IManterReceita;
import krift.common.model.services.IManterUsuario;
import krift.common.util.AbstractInOut;
import krift.common.util.Request;
import krift.core.model.service.impl.AvaliarReceita;
import krift.core.model.service.impl.ManterFavoritos;
import krift.core.model.service.impl.ManterPreferencias;
import krift.core.model.service.impl.ManterReceita;
import krift.core.model.service.impl.ManterUsuario;

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
    
    
    public void process() throws ClassNotFoundException {
        ObjectOutputStream writer;
        ObjectInputStream reader;

        try {

            writer = AbstractInOut.getObjectWriter(this.getSocket());
            reader = AbstractInOut.getObjectReader(this.getSocket());

            Request command;
            command = (Request) reader.readObject();
            
            IManterUsuario manterUsuario = new ManterUsuario();
            IManterFavoritos manterFavoritos = new ManterFavoritos();
            IManterPreferencias manterPreferencias = new ManterPreferencias();
            IManterReceita manterReceita = new ManterReceita();
            IAvaliarReceita avaliarReceita = new AvaliarReceita();
            
            switch (command) {
                
                case CADASTRAR:
                    break;
                case LOGAR:
                    break;
                case ALTERAR_PERFIL:
                    break;
                case VISITAR_PERFIL:
                    break;
                case CRIAR_RECEITA:
                    break;
                case ALTERAR_RECEITA:
                    break;
                case EXCLUIR_RECEITA:
                    break;
                case BUSCAR_RECEITAS:
                    break;
                case LISTAR_RECEITAS:
                    break;
                case LISTAR_RECOMENDADAS:
                    break;
                case AVALIAR:
                    break;
                case ALTERAR_AVALIACAO:
                    break;
                case COMENTAR:
                    break;
                case EXCLUIR_COMENTARIO:
                    break;
                case DENUNCIAR:
                    break;
                case FAVORITAR:
                    break;
                case REMOVER_FAVORITO:
                    break;
                case ADICIONAR_HISTORICO:
                    break;
                case VISITAR_RECEITA:
                    break;
                case LISTAR_FAVORITOS:
                    break;
                case LISTAR_HISTORICO:
                    break;
                case LISTAR_COMENTARIOS:
                    break;
                case LISTAR_RANKING:
                    break;
                    
            }
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
            
        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(Skeleton.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }
}
