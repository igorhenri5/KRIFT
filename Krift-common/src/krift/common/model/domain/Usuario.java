/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.common.model.domain;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Aluno
 */
public class Usuario {
    private String nom_login;
    private long seq_imagem;
    private String nom_perfil_usuario;
    private Date dat_cadastro;
    private String email;
    private String senha;
    private String des_usuario;
    private String idt_tendencia;
    private long nro_pontos;
    private int pos_ranking;
    private ArrayList<Receita> historico;
    private ArrayList<Receita> favoritos;
    private byte[] imagem;

    public Usuario() {
    }

    public Usuario(String nom_login, long seq_imagem, String nom_perfil_usuario, Date dat_cadastro, String email, String senha, String des_usuario, String idt_tendencia, long nro_pontos, int pos_ranking, ArrayList<Receita> historico, ArrayList<Receita> favoritos, byte[] imagem) {
        this.nom_login = nom_login;
        this.seq_imagem = seq_imagem;
        this.nom_perfil_usuario = nom_perfil_usuario;
        this.dat_cadastro = dat_cadastro;
        this.email = email;
        this.senha = senha;
        this.des_usuario = des_usuario;
        this.idt_tendencia = idt_tendencia;
        this.nro_pontos = nro_pontos;
        this.pos_ranking = pos_ranking;
        this.historico = historico;
        this.favoritos = favoritos;
        this.imagem = imagem;
    }

    public String getNom_login() {
        return nom_login;
    }

    public void setNom_login(String nom_login) {
        this.nom_login = nom_login;
    }

    public long getSeq_imagem() {
        return seq_imagem;
    }

    public void setSeq_imagem(long seq_imagem) {
        this.seq_imagem = seq_imagem;
    }

    public String getNom_perfil_usuario() {
        return nom_perfil_usuario;
    }

    public void setNom_perfil_usuario(String nom_perfil_usuario) {
        this.nom_perfil_usuario = nom_perfil_usuario;
    }

    public Date getDat_cadastro() {
        return dat_cadastro;
    }

    public void setDat_cadastro(Date dat_cadastro) {
        this.dat_cadastro = dat_cadastro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDes_usuario() {
        return des_usuario;
    }

    public void setDes_usuario(String des_usuario) {
        this.des_usuario = des_usuario;
    }

    public String getIdt_tendencia() {
        return idt_tendencia;
    }

    public void setIdt_tendencia(String idt_tendencia) {
        this.idt_tendencia = idt_tendencia;
    }

    public long getNro_pontos() {
        return nro_pontos;
    }

    public void setNro_pontos(long nro_pontos) {
        this.nro_pontos = nro_pontos;
    }

    public int getPos_ranking() {
        return pos_ranking;
    }

    public void setPos_ranking(int pos_ranking) {
        this.pos_ranking = pos_ranking;
    }

    public ArrayList<Receita> getHistorico() {
        return historico;
    }

    public void setHistorico(ArrayList<Receita> historico) {
        this.historico = historico;
    }

    public ArrayList<Receita> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(ArrayList<Receita> favoritos) {
        this.favoritos = favoritos;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
    
}
