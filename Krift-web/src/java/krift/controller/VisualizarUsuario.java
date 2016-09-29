/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import krift.common.model.domain.*;
import krift.common.model.services.*;
import krift.proxy.*;

/**
 *
 * @author Igor
 */
public class VisualizarUsuario {
    
    public static String execute(HttpServletRequest request) {
        
        String jsp = "index.jsp";
        String host = "localhost";
        
        int port = 2223;
        
        try {
            
            Usuario user = null;   
            ArrayList<Receita> receitas = null;
            
            String nomeUsuario = request.getParameter("nome");
            System.out.println("GOD -> "+nomeUsuario);
            IManterUsuario manter = new stubManterUsuario(host,port);            
            IManterReceita manterR = new stubManterReceita(host, port);    
            
            if(nomeUsuario==null||nomeUsuario.equals("")){
                jsp = "/index.jsp";
            }else{           
                System.out.println("G");
                user = manter.buscar(nomeUsuario);
                
                request.setAttribute("perfilNome", user.getNom_perfil_usuario());
                request.setAttribute("perfilRank", user.getPos_ranking());
                request.setAttribute("perfilPontos", user.getNro_pontos());
                request.setAttribute("perfilTendencia", user.getIdt_tendencia());
                request.setAttribute("perfilSobre", user.getDes_usuario());
                
                if(user!=null){    
                    System.out.println("B");
                    jsp = "/perfil.jsp";                     
                }                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "/index.jsp";
        }
        
        return jsp;        
    }
}    

