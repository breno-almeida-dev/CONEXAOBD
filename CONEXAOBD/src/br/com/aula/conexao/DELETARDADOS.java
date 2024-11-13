package br.com.aula.conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DELETARDADOS {

	/*
	 * Define o método como "public" para ser acessado de fora da classe, e
	 * "static" para que não seja necessário criar um objeto da classe para
	 * acessar o método. Além disso é passado como parâmetro um "scanner", que
	 * será utilizado para coletar dados do usuário.
	 */
	public static void deletarDados(Scanner scanner) {
		/*
		 * Para poder executar as funções do método, primeiro é realizada a chamada
		 * do método para garantir que a conexão com o banco de dados foi estabelecida.
		 * A condição "if" verifica se a conexão foi estabelecida e, sendo real, é declarada
		 * uma variável "string" que será utilizada para deletar os valores no comanda "DELETE"
		 * armazenado na "string".
		 */
        Connection conexao = ConexaoBD.conectar();
        
        if (conexao != null) {
            String sql = "DELETE FROM alunos WHERE id = ?";

            try {
            	/*
            	 * Para atribuir os valores no comando "DELETE" é criado um objeto
            	 * "PreparedStatement" que permite substituir os "?" pelos valores
            	 * informados pelo usuário através do "scanner" que aparecem logo em
            	 * seguida.
             	 */
                PreparedStatement stmt = conexao.prepareStatement(sql);

                System.out.print("Digite o ID do aluno que deseja deletar: ");
                int id = scanner.nextInt();

                /*
                 * Este bloco de código substitui o "?" pelo valor informado pelo
                 * usuário, e por fim faz a atualização do comando para deletar no 
                 * banco de dados.
                 */
                stmt.setInt(1, id);
                
                /*
                 * Este bloco de código realiza o "DELETE" no banco de dados, e com o "rowsUpdated"
                 * verifica se houve linhas afetadas no banco de dados, através da condição "if", para
                 * assim dar a mensagem de retorno ao usuário.
                 */
                int rowsDeleted = stmt.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Registro deletado com sucesso!");
                } else {
                    System.out.println("Nenhum registro encontrado com o ID especificado.");
                }

            // Caso haja erro ao deletar os dados, uma mensagem de erro é informada ao usuário.
            } catch (SQLException e) {
                System.err.println("Erro ao deletar dados: " + e.getMessage());
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
