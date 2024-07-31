package appEtapa3.services;

import appEtapa3.models.Disciplina;
import lib.ArvoreBinaria;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;

public class DisciplinaService {
    public void cadastrar(ArvoreBinaria arvore){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informar nome:");
        String nome = scanner.nextLine();

        System.out.println("Informar carga horária:");
        int cargaHoraria = scanner.nextInt();

        arvore.adicionar(new Disciplina(nome, cargaHoraria));

    }

    public void informarPreDisciplina(ArvoreBinaria arvore, Comparator comparator){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informar primeira disciplina: ");
        String primeiraDisciplina = scanner.nextLine();

        System.out.println("Informar segunda disciplina: ");
        String segundaDisciplina = scanner.nextLine();

        Disciplina disciplina1 = (Disciplina)arvore.pesquisar(new Disciplina(primeiraDisciplina, 0));
        Disciplina disciplina2 = (Disciplina)arvore.pesquisar(new Disciplina(segundaDisciplina, 0));

        disciplina2.setPreRequisitos(disciplina1);
    }
}
