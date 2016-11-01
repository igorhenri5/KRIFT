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
public class VisualizarReceita {
    
    public static String execute(HttpServletRequest request) {
        
        String jsp = "index.jsp";
        
        
       
        
        try {
            
            Receita receita = null;   
            
            Long idReceita = Long.parseLong(request.getParameter("idReceita"));
                      
            IManterReceita manterR = new stubManterReceita();    
            
            if(idReceita == null){
                jsp = "/index.jsp";
            }else{           
                receita = manterR.visualizar(idReceita);
                if(receita!=null){                         
                    jsp = "/receita.jsp";     
                    request.setAttribute("receita",receita);
                }                                       
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "/index.jsp";
        }
        
        return jsp;        
    }
}    

