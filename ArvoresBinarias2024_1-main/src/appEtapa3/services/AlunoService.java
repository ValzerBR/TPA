package appEtapa3.services;

import app.ComparadorAlunoPorMatricula;
import app.ComparadorAlunoPorNome;
import appEtapa3.models.Aluno;
import lib.ArvoreBinaria;
import lib.No;

import java.util.Comparator;
import java.util.Scanner;

public class AlunoService {

    public void cadastrar(ArvoreBinaria arvore){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informar nome:");
        String nome = scanner.nextLine();
        System.out.println("Informar matrícula:");
        int matricula = scanner.nextInt();

        Aluno aluno = new Aluno(matricula, nome);
        arvore.adicionar(aluno);
    }

    public void pesquisar(ArvoreBinaria arvore, Comparator comp){
        Scanner scanner = new Scanner(System.in);
        Aluno aluno = null;

        if(comp instanceof ComparadorAlunoPorMatricula){
            System.out.println("Informar matrícula:");
            int matricula = scanner.nextInt();

            aluno =  (Aluno)arvore.pesquisar(new Aluno(matricula, ""), comp);
        }
        else if(comp instanceof ComparadorAlunoPorNome){
            System.out.println("Informar nome:");
            String nome = scanner.nextLine();

            aluno = (Aluno)arvore.pesquisar(new Aluno(0, nome), comp);
        }

        if(aluno == null){
            System.out.println("Aluno não existe.");
            return;
        }
        System.out.println("Aluno: " + aluno.getNome() + "\nMatrícula: " + aluno.getMatricula());
    }

    public void remover(ArvoreBinaria arvore, ComparadorAlunoPorMatricula comp){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe a matrícula do aluno que deseja excluir:");
        int matricula = scanner.nextInt();

        Aluno aluno = (Aluno)arvore.pesquisar(new Aluno(matricula, ""), comp);
        arvore.remover(aluno);

        System.out.println("Aluno de matrícula:" + aluno.getMatricula() + " removido.");
    }
}
