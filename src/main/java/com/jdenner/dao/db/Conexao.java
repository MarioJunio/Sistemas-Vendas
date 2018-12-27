package com.jdenner.dao.db;

import java.sql.*;

public class Conexao {

    private final String SERVIDOR = "localhost";
    private final String PORTA = "3306";
    private final String BANCO_DE_DADOS = "dbsistemavenda";
    private final String USUARIO = "root";
    private final String SENHA = "bitlyuf";
    private final String URL = "jdbc:mysql://" + SERVIDOR + ":" + PORTA + "/" + BANCO_DE_DADOS + "?autoReconnect=true&useSSL=false";

    private Connection conexao;

    public Conexao() throws ClassNotFoundException, SQLException {
    	
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            conexao.setAutoCommit(false);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new ClassNotFoundException("O driver de conexão não foi adicionado ao projeto.\n" + e.getMessage());
        } catch (SQLException e) {
            throw new SQLException("Problemas nos parâmetros de conexão.\n" + e.getMessage());
        }
    }

    public Connection getConexao() {
        return conexao;
    }

    public void confirmar() throws SQLException {
    	
        try {
            conexao.commit();
        } catch (SQLException e) {
            throw new SQLException("Problemas na instrução SQL.\n" + e.getMessage());
        } finally {
            conexao.close();
        }
    }
}