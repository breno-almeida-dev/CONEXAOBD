package br.com.aula.conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LERDADOS {

	/*
	 * Define o método como "public" para ser acessado de fora da classe, e
	 * "static" para que não seja necessário criar um objeto da classe para
	 * acessar o método.
	 */
	public static void lerDados() {
		/*
		 * Para poder executar as funções do método, primeiro é realizada a chamada
		 * do método para garantir que a conexão com o banco de dados foi estabelecida.
		 * A condição "if" verifica se a conexão foi estabelecida e, sendo real, é declarada
		 * uma variável "string" que será utilizada para buscar os valores no comanda "SELECT"
		 * armazenado na "string". 
		 */
        Connection conexao = ConexaoBD.conectar();

        if (conexao != null) {
            String sql = "SELECT id, nome, idade FROM alunos";
            
            try {
            	/*
            	 * Aqui é criado um objeto "PreparedStatement" que permite executar
            	 * a consulta no banco de dados de maneira segura e eficiente, pois controla
            	 * o processo e garante que dados indesejados não serão inseridos no banco de dados.
            	 * 
            	 * Logo em seguida é utilizado um "ResultSet" que executa a busca no banco de dados e
            	 * armazena os dados buscados dentro de si, de forma que estes podem ser apresentados
            	 * posteriormente através da saída do terminal.
             	 */
                PreparedStatement stmt = conexao.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();

                System.out.println("Registros da tabela 'alunos':");                
                /*
                 * Para selecionar os dados capturados na consulta, é inicializado um loop "while"
                 * que recebe como condição cada linha que o "ResultSet" capturou, assim o método
                 * "next()" avança essas linhas e vai armazenando cada valor em sua respectiva variável
                 * que aparecem logo em seguida do laço de repetição. Para apresentar os dados ao usuário
                 * um "System.out" apresenta o modelo de "String" que contém as variáveis que armazenam os
                 * dados. Quando o método "next()" retorna "false" o laço é concluído.
                 */
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    int idade = rs.getInt("idade");
                    System.out.println("ID: " + id + ", Nome: " + nome + ", Idade: " + idade);
                }
                
            // Caso haja erro ao deletar os dados, uma mensagem de erro é informada ao usuário.
            } catch (SQLException e) {
                System.err.println("Erro ao ler dados: " + e.getMessage());
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
