package appEtapa3.services;

import appEtapa3.models.Aluno;
import lib.ArvoreBinaria;

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
}
