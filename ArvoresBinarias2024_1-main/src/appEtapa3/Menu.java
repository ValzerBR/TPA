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
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
        }
    }
}
