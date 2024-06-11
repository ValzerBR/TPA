package appEtapa3;

import app.ComparadorAlunoPorMatricula;
import appEtapa3.models.Aluno;
import appEtapa3.models.Disciplina;

import appEtapa3.services.AlunoService;
import appEtapa3.services.DisciplinaService;
import lib.ArvoreBinaria;

import java.util.Scanner;

import app.ComparadorAlunoPorNome;


public class Menu {
    ArvoreBinaria<Aluno> arvoreAluno;
    ArvoreBinaria<Disciplina> arvoreDisciplina;
    ComparadorAlunoPorMatricula compMatricula;

    public Menu(){
        this.compMatricula = new ComparadorAlunoPorMatricula();
        this.arvoreAluno = new ArvoreBinaria<>(compMatricula);
    }

    public static void main(String[] args){
        Menu menu = new Menu();
        menu.criarMenu();
    }

    private void criarMenu(){

        AlunoService alunoSvc = new AlunoService();

        System.out.println("Menu:\n 1- Cadastrar Aluno:\n 2- Cadastrar Disciplina:\n 3- Informar Pré requisito\n"+
                " 4- Informar disciplina cursada\n 5- Consultar aluno por nome\n 6- Consultar aluno por matrícula\n"+
                " 7- Excluir aluno por matrícula");

        Scanner value = new Scanner(System.in);

        switch(value.nextInt()){
            case 1:
                alunoSvc.cadastrar(this.arvoreAluno);
                criarMenu();
            case 2:
                System.out.println("Informar nome:");

                System.out.println("Informar carga horária:");



                criarMenu();

            case 3:
                System.out.println("");

                criarMenu();

            case 4:
                System.out.println("Informar nome do aluno que deseja consultar");

                criarMenu();

            case 5:
                System.out.println("Informar matrícula do aluno que deseja consultar");

                criarMenu();

            case 6:
                System.out.println("Informar matrícula do aluno que deseja excluir");

                criarMenu();
        }
    }
}
