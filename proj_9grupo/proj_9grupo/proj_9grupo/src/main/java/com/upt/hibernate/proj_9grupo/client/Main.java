package com.upt.hibernate.proj_9grupo.client;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        AlunoClient alunoClient = new AlunoClient();
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("Menu:");
            System.out.println("1. Obter um aluno através do ID");
            System.out.println("2. Obter Todos os Alunos");
            System.out.println("3. Criar Novo Aluno");
            System.out.println("4. Atualizar Aluno");
            System.out.println("5. Eliminar Aluno");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Escreva o ID do aluno: ");
                    Long id = scanner.nextLong();
                    alunoClient.getAlunoById(id);
                    break;
                case 2:
                    alunoClient.getAllAlunos();
                    break;
                case 3:
                    alunoClient.criarAluno();
                    break;
                case 4:
                    System.out.print("Escreva o ID do aluno a ser atualizado: ");
                    Long updateId = scanner.nextLong();
                    alunoClient.updateAluno(updateId);
                    break;
                case 5:
                    System.out.print("Escreva o ID do aluno a ser eliminado: ");
                    Long deleteId = scanner.nextLong();
                    alunoClient.eliminarAluno(deleteId);
                    break;
                case 0:
                    System.out.println("A sair...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (option != 6);

        scanner.close();
    }
}
