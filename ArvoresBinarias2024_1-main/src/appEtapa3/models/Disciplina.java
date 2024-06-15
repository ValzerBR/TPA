package appEtapa3.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Disciplina {

    private static int ultimoCodigo;

    private int codigo = 0;
    private String nome;
    private  int cargaHoraria;
    private ArrayList<Disciplina> preRequisitos;

    public Disciplina(String nome, int cargaHoraria){
        this.codigo = ++ultimoCodigo;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.preRequisitos = new ArrayList<Disciplina>();
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

    public void setPreRequisitos(Disciplina disciplina){
        this.preRequisitos.add(disciplina);
    }

    public List<Disciplina> getPreRequisitos() {
        if (preRequisitos == null) {
            return new ArrayList<>(); // Retorna uma lista vazia se não houver pré-requisitos
        }
        return preRequisitos; // Retorna a lista de pré-requisitos
    }
}
