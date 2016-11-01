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
public class DesfavoritarReceita {
    
     public static String execute(HttpServletRequest request) {
        
        String jsp = "index.jsp";
        
        
       
        
        try {
            
            Receita receita = null;   
            
            IManterFavoritos manter = new stubManterFavoritos();  
            IManterReceita manterR = new stubManterReceita();    
            
            Long id = Long.parseLong(request.getParameter("idReceita"));                   
            
            
            if(!(id.equals(""))&&id!=null){
                receita = manterR.visualizar(id);                
                manter.desfavoritar(((Usuario) request.getSession().getAttribute("logado")).getNom_login(), id);
                
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

