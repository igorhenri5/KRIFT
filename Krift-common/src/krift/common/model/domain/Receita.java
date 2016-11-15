/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.common.model.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Aluno
 */
public class Receita implements Serializable{
    private long nro_seq_receita;
    private String num_login;
    private String nom_receita;
    private String des_receita;
    private Date dat_publicacao;
    private String idt_tendencia;
    private int qtd_tempo;
    private String qtd_rendimento;
    private String imagem;
    private long seq_imagem;
    private float nota;
    private Usuario autor;
    private ArrayList<Ingrediente> ingredientes;
    private ArrayList<Procedimento> procedimentos;

    public Receita() {
        ingredientes = new ArrayList<>();
        procedimentos = new ArrayList<>();
    }

    public Receita(long nro_seq_receita, String num_login, String nom_receita, String des_receita, Date dat_publicacao, String idt_tendencia, int qtd_tempo, String qtd_rendimento, String imagem, long seq_imagem, Usuario autor, ArrayList<Ingrediente> ingredientes, ArrayList<Procedimento> procedimentos, ArrayList<Denuncia> denuncias, Avaliacao avaliacoes, ArrayList<Comentario> comentarios, float nota) {
        this.nro_seq_receita = nro_seq_receita;
        this.num_login = num_login;
        this.nom_receita = nom_receita;
        this.des_receita = des_receita;
        this.dat_publicacao = dat_publicacao;
        this.idt_tendencia = idt_tendencia;
        this.qtd_tempo = qtd_tempo;
        this.qtd_rendimento = qtd_rendimento;
        this.imagem = imagem;
        this.seq_imagem = seq_imagem;
        this.autor = autor;
        this.ingredientes = ingredientes;
        this.procedimentos = procedimentos;
        this.nota = nota;
    }

    public long getNro_seq_receita() {
        return nro_seq_receita;
    }

    public void setNro_seq_receita(long nro_seq_receita) {
        this.nro_seq_receita = nro_seq_receita;
    }

    public String getNum_login() {
        return num_login;
    }

    public void setNum_login(String num_login) {
        this.num_login = num_login;
    }

    public String getNom_receita() {
        return nom_receita;
    }

    public void setNom_receita(String nom_receita) {
        this.nom_receita = nom_receita;
    }

    public String getDes_receita() {
        return des_receita;
    }

    public void setDes_receita(String des_receita) {
        this.des_receita = des_receita;
    }

    public Date getDat_publicacao() {
        return dat_publicacao;
    }

    public void setDat_publicacao(Date dat_publicacao) {
        this.dat_publicacao = dat_publicacao;
    }

    public String getIdt_tendencia() {
        return idt_tendencia;
    }

    public void setIdt_tendencia(String idt_tendencia) {
        this.idt_tendencia = idt_tendencia;
    }

    public int getQtd_tempo() {
        return qtd_tempo;
    }

    public void setQtd_tempo(int qtd_tempo) {
        this.qtd_tempo = qtd_tempo;
    }

    public String getQtd_rendimento() {
        return qtd_rendimento;
    }

    public void setQtd_rendimento(String qtd_rendimento) {
        this.qtd_rendimento = qtd_rendimento;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public long getSeq_imagem() {
        return seq_imagem;
    }

    public void setSeq_imagem(long seq_imagem) {
        this.seq_imagem = seq_imagem;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public ArrayList<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(ArrayList<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public ArrayList<Procedimento> getProcedimentos() {
        return procedimentos;
    }

    public void setProcedimentos(ArrayList<Procedimento> procedimentos) {
        this.procedimentos = procedimentos;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }
}
