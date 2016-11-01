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
public class EditarReceita {

    public static String execute(HttpServletRequest request) {

        String jsp = "index.jsp";
        

       
        try {
            
            Receita receita = (Receita) request.getAttribute("receita");    
            
            String nome = request.getParameter("nome");
            String descricao = request.getParameter("descricao");
            String rendimento = request.getParameter("rendimento");
            String tempo = request.getParameter("tempo");
            String tendencia = request.getParameter("tendencia");
            String imagem = request.getParameter("imagem");
            
            IManterReceita manter = new stubManterReceita();
            
            if(false){ // trocar pra validação de campos
                jsp = "/editarReceita.jsp";
            }else{                          
                        
                if(manter.alterar(receita)){      
                    jsp = "/index.jsp";
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "/editarPerfil.jsp";
        }

        return jsp;
    }
}
