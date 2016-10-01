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
            IManterUsuario manter = new stubManterUsuario(host,port);            
            IManterReceita manterR = new stubManterReceita(host, port);    
            
            if(nomeUsuario==null||nomeUsuario.equals("")){
                jsp = "/index.jsp";
            }else{           
                user = manter.buscar(nomeUsuario);          
                
                request.setAttribute("nome", nomeUsuario);
                request.setAttribute("perfilNome", user.getNom_perfil_usuario());
                request.setAttribute("perfilRank", user.getPos_ranking());
                request.setAttribute("perfilPontos", user.getNro_pontos());                
                request.setAttribute("perfilSobre", user.getDes_usuario());
                request.setAttribute("perfilReceitas", manterR.listarReceitasPorUsuario(nomeUsuario));
                
                String td = user.getIdt_tendencia();
                
                if(td.equals("SEM")){
                    td = "SEM TENDÊNCIA";
                }
                
                if(td.equals("VGN")){
                    td = "VEGANO";
                }
                
                if(td.equals("VGT")){
                    td = "VEGETARIANO";
                }
                
                if(td.equals("ONV")){
                    td = "ONÍVORO";
                }
                
                if(td.equals("CRN")){
                    td = "CARNÍVORO";
                }
                
                request.setAttribute("perfilTendencia", td);
                
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

