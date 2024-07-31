package appEtapa3.services;

import app.ComparadorAlunoPorMatricula;
import app.ComparadorAlunoPorNome;
import appEtapa3.comparators.ComparadorDisciplinaPorNome;
import appEtapa3.models.Aluno;
import appEtapa3.models.Disciplina;
import lib.ArvoreBinaria;
import lib.No;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class AlunoService {

    public void cadastrar(ArvoreBinaria arvore, ArvoreBinaria arvoreNome){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informar nome:");
        String nome = scanner.nextLine();
        System.out.println("Informar matrícula:");
        int matricula = scanner.nextInt();

        Aluno aluno = new Aluno(matricula, nome);
        arvore.adicionar(aluno);
        arvoreNome.adicionar(aluno);
    }

    public void pesquisar(ArvoreBinaria arvore, Comparator comp){
        Scanner scanner = new Scanner(System.in);
        Aluno aluno = null;

        if(comp instanceof ComparadorAlunoPorMatricula){
            System.out.println("Informar matrícula:");
            int matricula = scanner.nextInt();

            aluno = (Aluno)arvore.pesquisar(new Aluno(matricula, ""), comp);
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

        List<Disciplina> disciplinasCursadas = aluno.getDisciplinasCursadas(aluno);
        if (disciplinasCursadas != null && !disciplinasCursadas.isEmpty()) {
            System.out.println("Disciplinas Cursadas:");
            for (Disciplina disciplina : disciplinasCursadas) {
                System.out.println("- Código: " + disciplina.getCodigo() + ", Nome: " + disciplina.getNome());
            }
        } else {
            System.out.println("O aluno não possui disciplinas cursadas.");
        }
    }

    public void remover(ArvoreBinaria arvore,ArvoreBinaria arvoreNome, ComparadorAlunoPorMatricula comp){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe a matrícula do aluno que deseja excluir:");
        int matricula = scanner.nextInt();

        Aluno aluno = (Aluno)arvore.pesquisar(new Aluno(matricula, ""), comp);
        arvore.remover(aluno);
        arvoreNome.remover(aluno);

        System.out.println("Aluno de matrícula:" + aluno.getMatricula() + " removido.");
    }

    public void informarDisciplinaCursada(ArvoreBinaria<Aluno> arvoreAlunos, ArvoreBinaria<Disciplina> arvoreDisciplinas) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe a matrícula do aluno:");
        int matricula = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        System.out.println("Informe o nome da disciplina:");
        String nomeDisciplina = scanner.nextLine();

        Aluno aluno = arvoreAlunos.pesquisar(new Aluno(matricula, ""), new ComparadorAlunoPorMatricula());
        Disciplina disciplina = arvoreDisciplinas.pesquisar(new Disciplina(nomeDisciplina, 0));

        if (aluno == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }

        if (disciplina == null) {
            System.out.println("Disciplina não encontrada.");
            return;
        }

        if (alunoPossuiPreRequisitos(aluno, disciplina, arvoreDisciplinas)) {
            // Adicionar a disciplina à lista de disciplinas cursadas do aluno (implementar na classe Aluno)
            aluno.getDisciplinasCursadas(aluno).add(disciplina);
            System.out.println("Disciplina cursada registrada com sucesso!");
        } else {
            List<Disciplina> preRequisitosFaltantes = obterPreRequisitosFaltantes(aluno, disciplina, arvoreDisciplinas);
            System.out.println("O aluno não possui os seguintes pré-requisitos:");
            for (Disciplina preRequisito : preRequisitosFaltantes) {
                System.out.println("- " + preRequisito.getNome());
            }
        }
    }

    private boolean alunoPossuiPreRequisitos(Aluno aluno, Disciplina disciplina, ArvoreBinaria<Disciplina> arvoreDisciplinas) {
        if (disciplina.getPreRequisitos() == null || disciplina.getPreRequisitos().isEmpty()) {
            System.out.println("Disciplina não tem pré-requisitos");
            return true; // Disciplina não tem pré-requisitos
        }

        List<Disciplina> disciplinasCursadas = aluno.getDisciplinasCursadas(aluno);
        for (Disciplina preRequisito : disciplina.getPreRequisitos()) {
            if (!disciplinasCursadas.contains(preRequisito) ||
                    arvoreDisciplinas.pesquisar(preRequisito, new ComparadorDisciplinaPorNome()) == null) {
                System.out.println("O aluno NÃO possui todos pré-requisitos");
                return false; // Pré-requisito não cursado e não encontrado na árvore
            }
        }
        System.out.println("O aluno possui todos pré-requisitos");
        return true; // Todos os pré-requisitos foram cursados ou encontrados na árvore
    }

    private List<Disciplina> obterPreRequisitosFaltantes(Aluno aluno, Disciplina disciplina, ArvoreBinaria<Disciplina> arvoreDisciplinas) {
        List<Disciplina> preRequisitosFaltantes = new ArrayList<>();
        if (disciplina.getPreRequisitos() == null || disciplina.getPreRequisitos().isEmpty()) {
            return preRequisitosFaltantes; // Disciplina não tem pré-requisitos
        }

        List<Disciplina> disciplinasCursadas = aluno.getDisciplinasCursadas(aluno);
        for (Disciplina preRequisito : disciplina.getPreRequisitos()) {
            if (!disciplinasCursadas.contains(preRequisito) ||
                    arvoreDisciplinas.pesquisar(preRequisito, new ComparadorDisciplinaPorNome()) == null) {
                preRequisitosFaltantes.add(preRequisito);
            }
        }
        return preRequisitosFaltantes;
    }

}
