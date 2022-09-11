/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author vit
 */
public class ConnectionFactoryModel {
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/database_name";
    public static final String USER = "user";
    public static final String PASS = "password";
    
    public static Connection getConnection() {
        try {
            //Para fazer a conexão, é preciso especificar o drive (faz-se isso na próxima linha) e também passar as credenciais (URL, USER, PASS)
            Class.forName(DRIVER);
            
            //Observer que getConnection é um método estático, já que não foi preciso instanciar a classe DriverManager.
            return DriverManager.getConnection(URL, USER, PASS);
        } catch(Exception ex) {
            throw new RuntimeException("Erro na conexão com o banco de dados", ex);
        }
    }
    
    //é possível ter dois métodos de classe com o mesmo nome, porém seus parâmetros tem que mudar. Quando se usar o closeConnection, poderá ser o primeiro ou o segundo, dependerá da quantidade de parâmetros (1 parãmetro irá ser usado primeiro, 2 parâmetros será usado o segundo método).
    public static void closeConnection(Connection connection) {
        try { 
            if(connection != null) {
               connection.close(); 
            } 
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao fechar a conexão com o banco de dados", ex);
        } 
    }
    
    public static void closeConnection(Connection connection, PreparedStatement statement) {
        try { 
            if(connection != null) {
               connection.close(); 
            } 
            
            if (statement != null) { 
                statement.close();
            }
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao fechar a conexão com o banco de dados", ex);
        } 
    }
    
    public static void closeConnection(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        try { 
            if(connection != null) {
               connection.close(); 
            } 
            
            if (statement != null) { 
                statement.close();
            }
            
            if(resultSet != null) {
                resultSet.close();
            }
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao 0 a conexão com o banco de dados", ex);
        } 
    }
}
