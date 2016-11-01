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
public class AlterarAvaliacao {
    
    public static String execute(HttpServletRequest request) {
        
        String jsp = "index.jsp";
        
        try {
            
            Receita receita = null;   
            
            IAvaliarReceita manter = new stubAvaliarReceita();  
            IManterReceita manterR = new stubManterReceita();    
            
            Long id = Long.parseLong(request.getParameter("idReceita"));            
            
            Avaliacao aval = new Avaliacao();
            
            aval.setNom_login(((Usuario) request.getSession().getAttribute("logado")).getNom_login());
            aval.setNro_seq_receita(id);
            aval.setVlr_avaliacao(Integer.parseInt(request.getParameter("valorAvalicacao")));
            
            if(!(id.equals(""))&&id!=null){
                receita = manterR.visualizar(id);                
                manter.alterarAvaliacao(aval);
                
                if(receita!=null){                         
                    jsp = "/receita.jsp";     
                    request.setAttribute("receita",receita);
                } 
            }else{
                jsp = "/index.jsp";
            }    
            
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "/index.jsp";
        }
        
        return jsp;        
    }
}    

