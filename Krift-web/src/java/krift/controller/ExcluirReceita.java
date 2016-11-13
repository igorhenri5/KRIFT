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
public class ExcluirReceita {
    
    public static String execute(HttpServletRequest request) {
        
        String jsp = "index.jsp";
        
        
        try {          
            Long id = Long.parseLong(request.getParameter("idReceita")); 
            
            IManterReceita manter = new stubManterReceita();     
            
            boolean ok = manter.excluir(id);
            
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "/index.jsp";
        }
        
        return jsp;        
    }
}    

