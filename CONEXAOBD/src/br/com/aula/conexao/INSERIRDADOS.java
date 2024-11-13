package br.com.aula.conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class INSERIRDADOS {

	/*
	 * Define o método como "public" para ser acessado de fora da classe, e
	 * "static" para que não seja necessário criar um objeto da classe para
	 * acessar o método. Além disso é passado como parâmetro um "scanner", que
	 * será utilizado para coletar dados do usuário.
	 */
	public static void inserirDados(Scanner scanner) {
		/*
		 * Para poder executar as funções do método, primeiro é realizada a chamada
		 * do método para garantir que a conexão com o banco de dados foi estabelecida.
		 * A condição "if" verifica se a conexão foi estabelecida e, sendo real, é declarada
		 * uma variável "string" que será utilizada para inserir os valores no comanda "INSERT"
		 * armazenado na "string".
		 */
        Connection conexao = ConexaoBD.conectar();
        if (conexao != null) {
            String sql = "INSERT INTO alunos (nome, idade) VALUES (?, ?)";
            try {
            	/*
            	 * Para atribuir os valores no comando "INSERT" é criado um objeto
            	 * "PreparedStatement" que permite substituir os "?" pelos valores
            	 * informados pelo usuário através do "scanner" que aparecem logo em
            	 * seguida.
             	 */
                PreparedStatement stmt = conexao.prepareStatement(sql);

                System.out.print("Digite o nome do aluno: ");
                String nome = scanner.nextLine();

                System.out.print("Digite a idade do aluno: ");
                int idade = scanner.nextInt();
                
                /*
                 * Este bloco de código substitui os "?" pelos valores informados pelo
                 * usuário, de acordo com a ordem correta no comando "insert", e por fim
                 * faz o "update" do comando para armazenar no banco de dados.
                 */
                stmt.setString(1, nome);
                stmt.setInt(2, idade);
                stmt.executeUpdate();

                // Apresenta uma mensagem de sucesso ao usuário.
                System.out.println("Dados inseridos com sucesso!");

            // Caso haja erro ao inserir os dados, uma mensagem de erro é informada ao usuário.
            } catch (SQLException e) {
                System.err.println("Erro ao inserir dados: " + e.getMessage());
            /*
             * Como boa prática, é definido um bloco "finally" para encerrar a conexão com
             * o banco de dados, ao fim da execução do método. Assim garantindo que o comando
             * "PreparedStatement" foi fechado de forma correta, evitando ataques de injeção de
             * dados indesejados.
             * 
             * Caso aconteça algum erro ao fechar a conexão, uma mensagem de erro é enviada ao
             * usuário.
             */
            } finally {
                try {
                    if (conexao != null) {
                        conexao.close();
                    }
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar conexão: " + e.getMessage());
                }
            }
        }
    }
}
