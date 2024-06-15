package appEtapa3;

import app.ComparadorAlunoPorMatricula;
import appEtapa3.models.Aluno;
import appEtapa3.models.Disciplina;
import appEtapa3.comparators.ComparadorDisciplinaPorNome;

import appEtapa3.services.AlunoService;
import appEtapa3.services.DisciplinaService;
import lib.ArvoreBinaria;

import java.util.Scanner;

import app.ComparadorAlunoPorNome;


public class Menu {
    ArvoreBinaria<Aluno> arvoreAluno;
    ArvoreBinaria<Aluno> arvoreAlunoNome;
    ArvoreBinaria<Disciplina> arvoreDisciplina;
    ComparadorAlunoPorMatricula compMatricula;
    ComparadorAlunoPorNome compNome;
    ComparadorDisciplinaPorNome compDisciplinaNome;

    public Menu(){
        this.compMatricula = new ComparadorAlunoPorMatricula();
        this.compNome = new ComparadorAlunoPorNome();
        this.arvoreAluno = new ArvoreBinaria<>(compMatricula);
        this.arvoreAlunoNome = new ArvoreBinaria<>(compNome);
        this.compDisciplinaNome = new ComparadorDisciplinaPorNome();
        this.arvoreDisciplina = new ArvoreBinaria<>(compDisciplinaNome);
    }

    public static void main(String[] args){
        Menu menu = new Menu();
        menu.criarMenu();
    }

    private void criarMenu(){
        AlunoService alunoSvc = new AlunoService();
        DisciplinaService disciplinaService = new DisciplinaService();

        System.out.println("Menu:\n 1- Cadastrar Aluno\n 2- Cadastrar Disciplina\n 3- Informar Pré requisito\n"+
                " 4- Informar disciplina cursada\n 5- Consultar aluno por nome\n 6- Consultar aluno por matrícula\n"+
                " 7- Excluir aluno por matrícula");

        Scanner value = new Scanner(System.in);

        switch(value.nextInt()){
            case 1:
                limpar();
                alunoSvc.cadastrar(this.arvoreAluno, this.arvoreAlunoNome);
                criarMenu();
            case 2:
                limpar();
                disciplinaService.cadastrar(this.arvoreDisciplina);
                criarMenu();

            case 3:
                limpar();
                disciplinaService.informarPreDisciplina(this.arvoreDisciplina, this.compDisciplinaNome);
                criarMenu();

            case 4:
                limpar();
                System.out.println("Informar nome do aluno que deseja consultar");
                alunoSvc.informarDisciplinaCursada(this.arvoreAluno, this.arvoreDisciplina);
                criarMenu();

            case 5:
                limpar();
                alunoSvc.pesquisar(this.arvoreAlunoNome, this.compNome);

                criarMenu();

            case 6:
                limpar();
                alunoSvc.pesquisar(this.arvoreAluno, this.compMatricula);
                criarMenu();

            case 7:
                limpar();
                alunoSvc.remover(this.arvoreAluno, this.arvoreAlunoNome, this.compMatricula);
                criarMenu();
        }
    }

    private static void limpar(){
        for (int i = 0; i < 50; ++i) {
            System.out.println();
        }
    }

}
