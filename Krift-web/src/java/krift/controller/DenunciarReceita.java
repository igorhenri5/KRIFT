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
public class DenunciarReceita {
    
    public static String execute(HttpServletRequest request) {
        
        String jsp = "index.jsp";
        
        
       
        
        try {
            
            Receita receita = null;   
            
            IAvaliarReceita manter = new stubAvaliarReceita();  
            IManterReceita manterR = new stubManterReceita();    
            
            Long id = Long.parseLong(request.getParameter("idReceita"));
            
            Denuncia den = new Denuncia();
            
            if(!(id.equals(""))&&id!=null){
                receita = manterR.visualizar(id);
                manter.denunciar(den);
                den.setNro_seq_receita(id);
                den.setDes_comentario("D E N U N C I A D O");
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

