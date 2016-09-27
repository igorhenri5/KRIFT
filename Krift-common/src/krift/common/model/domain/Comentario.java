/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.common.model.domain;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Nome
 */
public class Comentario implements Serializable{
    private Date dat_publicacao;
    private long nro_seq_receita;
    private String nom_login;
    private String des_comentario;

    public Comentario() {
    }

    public Comentario(Date dat_publicacao, long nro_seq_receita, String nom_login, String des_comentario) {
        this.dat_publicacao = dat_publicacao;
        this.nro_seq_receita = nro_seq_receita;
        this.nom_login = nom_login;
        this.des_comentario = des_comentario;
    }

    public Date getDat_publicacao() {
        return dat_publicacao;
    }

    public void setDat_publicacao(Date dat_publicacao) {
        this.dat_publicacao = dat_publicacao;
    }

    public long getNro_seq_receita() {
        return nro_seq_receita;
    }

    public void setNro_seq_receita(long nro_seq_receita) {
        this.nro_seq_receita = nro_seq_receita;
    }

    public String getNom_login() {
        return nom_login;
    }

    public void setNom_login(String nom_login) {
        this.nom_login = nom_login;
    }

    public String getDes_comentario() {
        return des_comentario;
    }

    public void setDes_comentario(String des_comentario) {
        this.des_comentario = des_comentario;
    }

    
}
