package br.com.aula.conexao;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        /*
         * Faz a chamada da classe de conexão do banco de dados, e executa
         * seu método que realiza a conexão.
         */
        Connection conexao = ConexaoBD.conectar();
        
        /*
         * Para que funcione como um menu, é declarada uma condição "if" para
         * que seja verificado primeiramente se a conexão foi estabelecida,
         * caso seja real é declarada um laço de repetição while, para que a
         * tela de menu sempre retorne, até que o usuário escolha sair.
         */
        if(conexao!=null) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Inserir Aluno");
            System.out.println("2. Atualizar Aluno");
            System.out.println("3. Deletar Aluno");
            System.out.println("4. Ler Registro de Alunos");
            System.out.println("0. Sair");
            System.out.print("Escolha um opção: ");
            
            try {
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer.

                /*
                 * Este bloco "switch" faz a chamada do método presente em
                 * outras classes de acordo com a opção escolhida pelo usuário.
                 * Caso as opções "inserirDados", "atualizarDados", "deletarDados"
                 * sejam escolhidas, o "scanner" para entrada de dados é passado
                 * como argumento para ser utilizado nos métodos da classe.
                 */
                switch (opcao) {
                    case 1: // Inserir um aluno.
                        INSERIRDADOS.inserirDados(scanner);
                        break;
                    case 2: // Atualizar um aluno.
                        ATUALIZARDADOS.atualizarDados(scanner);
                        break;
                    case 3: // Deletar um aluno.
                        DELETARDADOS.deletarDados(scanner);
                    	break;
                    case 4: // Ler todos os alunos.
                        LERDADOS.lerDados();
                        break;
                    case 0: // Sair.
                        System.out.println("Saindo...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.nextLine(); // Limpar o buffer.
            }
        }
        }
    }
}