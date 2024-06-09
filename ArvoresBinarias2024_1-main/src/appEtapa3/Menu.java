package appEtapa3;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args){
        criarMenu();
    }

    private static void criarMenu(){
        System.out.println("Menu:\n 1- Cadastrar Aluno:\n 2- Cadastrar Disciplina:\n 3- Informar Pré requisito\n"+
                " 4- Informar disciplina cursada\n 5- Consultar aluno por nome\n 6- Consultar aluno por matrícula\n"+
                " 7- Excluir aluno por matrícula");

        Scanner value = new Scanner(System.in);

        switch(value.nextInt()){
            case 1:
                System.out.println("Informar nome:");
                System.out.println("Informar matrícula:");
                
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
