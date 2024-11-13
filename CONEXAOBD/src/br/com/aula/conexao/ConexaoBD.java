package br.com.aula.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {

    /*
     * Primeiramente, para estabelecer a conexão, é declarado
     * 3 atributos de forma privada, estática e final, garantindo assim
     * que estes não serão modificados e que serão visíveis apenas de
     * dentra da classe. 
     */
    private static final String URL = "jdbc:mysql://localhost:3306/aula_java_db";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    /*
     * Este método é definido como público para poder ser chamado de fora da classe,
     * além disso, também é definido como estático para que seja possível ser chamado
     * sem criar um objeto da classe. Retornando assim o status da conexão com o banco
     * de dados.
     */
    public static Connection conectar() {
    	/*
    	 * Primeiramente é declarada a variável "conexao" como nula para que,
    	 * caso a conexão com o banco de dados falhe, o status da conexão permaneça
    	 * como nulo.
    	 */
        Connection conexao = null;
        try {
            // Faz o carregamento do driver JDBC para estabelecer a conexão.
            Class.forName("com.mysql.cj.jdbc.Driver");

            /* Atribui a variável "conexao" o resultado obtido da chamada do método
             * que estabelece a conexão com o banco de dados, passando os atributos
             * necessários para execução do método.
             * 
             * Caso obtenha sucesso uma mensagem de confirmação é passada ao usuário.
             */
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conexão realizada com sucesso!");

        /*
         * Caso o driver JDBC não seja encontrado, ou a conexão com o banco de dados falhe
         * é informado ao usuário uma mensagem de falha.
         */
        } catch (ClassNotFoundException e) {
            System.err.println("Driver não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro ao conectar: " + e.getMessage());
        }
        // Por fim, o método retorna o status da conexão.
        return conexao;
    }
}
