/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.common.model.domain;

import java.io.Serializable;

/**
 *
 * @author Aluno
 */
public class Denuncia implements Serializable{
    private String nom_login;
    private long nro_seq_receita;
    private String des_comentario;

    public Denuncia() {
    }

    public Denuncia(String nom_login, long seq_receita, String des_comentario) {
        this.nom_login = nom_login;
        this.nro_seq_receita = seq_receita;
        this.des_comentario = des_comentario;
    }

    public String getNom_login() {
        return nom_login;
    }

    public void setNom_login(String nom_login) {
        this.nom_login = nom_login;
    }

    public long getNro_seq_receita() {
        return nro_seq_receita;
    }

    public void setNro_seq_receita(long nro_seq_receita) {
        this.nro_seq_receita = nro_seq_receita;
    }

    public String getDes_comentario() {
        return des_comentario;
    }

    public void setDes_comentario(String des_comentario) {
        this.des_comentario = des_comentario;
    }

    
    
    
}
