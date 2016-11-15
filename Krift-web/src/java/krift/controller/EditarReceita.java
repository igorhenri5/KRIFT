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
public class EditarReceita {

    public static String execute(HttpServletRequest request) {

        String jsp = "index.jsp";
        
        try {
            IManterReceita manter = new stubManterReceita();
            Receita receita = manter.visualizar(Long.parseLong(request.getParameter("idReceita")));
            String nome = request.getParameter("nome");
            String descricao = request.getParameter("descricao");
            String rendimento = request.getParameter("rendimento");
            String tempo = request.getParameter("tempo");
            String tendencia = request.getParameter("tendencia");
            String imagem = request.getParameter("img64");
            ArrayList<Ingrediente> ingredientes = new ArrayList<>();
            ArrayList<Procedimento> procedimentos = new ArrayList<>();
            
            for(int i = 0; request.getParameterValues("ingrediente") != null && request.getParameterValues("quantidade") != null && i < request.getParameterValues("ingrediente").length &&
                    i < request.getParameterValues("quantidade").length; i++){
                if(!request.getParameterValues("ingrediente")[i].isEmpty() && !request.getParameterValues("quantidade")[i].isEmpty()){
                    Ingrediente atual = new Ingrediente();
                    atual.setNom_ingrediente(request.getParameterValues("ingrediente")[i]);
                    atual.setDes_quantidade(request.getParameterValues("quantidade")[i]);
                    ingredientes.add(atual);
                }
            }
            for(int i = 0; request.getParameterValues("passo") != null && i < request.getParameterValues("passo").length; i++){
                if(!request.getParameterValues("passo")[i].isEmpty()){
                    Procedimento atual = new Procedimento();
                    atual.setDes_procedimento(request.getParameterValues("passo")[i]);
                    procedimentos.add(atual);
                }
            }
            
            if(nome == null || nome.isEmpty() || rendimento == null 
                    || rendimento.isEmpty() || ingredientes.size() ==0 || 
                    procedimentos.size() == 0 || tendencia == null || 
                    tendencia.length() !=3){
                jsp = "/editarReceita.jsp";
                request.setAttribute("receita", receita);
            }else{
                receita.setNom_receita(nome);
                receita.setDes_receita(descricao);
                receita.setIdt_tendencia(tendencia);
                receita.setImagem(imagem.substring(22));
                receita.setIngredientes(ingredientes);
                receita.setQtd_rendimento(rendimento);
                receita.setProcedimentos(procedimentos);
                receita.setQtd_tempo(Integer.parseInt(tempo));
                
                if(manter.alterar(receita)){      
                    jsp = "/index.jsp";
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "/editarReceita.jsp";
        }

        return jsp;
    }
}
