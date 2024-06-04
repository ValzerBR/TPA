package appEtapa3;

import java.util.ArrayList;
public class Disciplina {
    private int codigo;
    private String nome;
    private  int cargaHoraria;
    private ArrayList<Disciplina> preRequisitos;
    private ArrayList<Aluno> alunos;

    public Disciplina(int codigo, String nome, int cargaHoraria, ArrayList<Disciplina> preRequisitos, ArrayList<Aluno> alunos){
        this.codigo = codigo;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.preRequisitos = preRequisitos;
        this.alunos = alunos;
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
