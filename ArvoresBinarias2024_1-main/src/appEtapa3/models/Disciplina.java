package appEtapa3.models;

import java.util.ArrayList;
public class Disciplina {
    private int codigo;
    private String nome;
    private  int cargaHoraria;
    private ArrayList<Disciplina> preRequisitos;

    public Disciplina(int codigo, String nome, int cargaHoraria, ArrayList<Disciplina> preRequisitos){
        this.codigo = codigo;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.preRequisitos = preRequisitos;
    }
    public int getCodigo() {
        return codigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
}
