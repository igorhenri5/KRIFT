/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.controller;

import javax.servlet.http.HttpServletRequest;
import krift.common.model.domain.*;
import krift.common.model.services.*;
import krift.proxy.*;

/**
 *
 * @author Igor
 */
public class CriarReceita {
    
    public static String execute(HttpServletRequest request) {
        
        String jsp = "index.jsp";
        String host = "localhost";
        
        int port = 2223;
        
        try {
            
            Receita receita = new Receita();            
            
            String nome = request.getParameter("nome");
            String descricao = request.getParameter("descricao");
            String rendimento = request.getParameter("rendimento");
            String tempo = request.getParameter("tempo");
            String tendencia = request.getParameter("tendencia");
            String imagem = request.getParameter("imagem");
            //receita.set
            //lidar com ingredientes e passos
            
            IManterReceita manter = new stubManterReceita(host,port);
            
            if(false){ // trocar pra validação de campos
                jsp = "/cadastrarReceita.jsp";
            }else{
                
                receita.setNom_receita(nome);            
                        
                if(manter.criar(receita)){      
                    jsp = "/index.jsp";
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "/cadastrarReceita.jsp";
        }
        
        return jsp;        
    }
}    

