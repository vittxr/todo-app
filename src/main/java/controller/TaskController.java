/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Task;
import java.util.List;
import util.ConnectionFactory;

/**
 *
 * @author vit
 */
public class TaskController {
    public void save(Task task) {
        //quando ainda n�o se tem os par�metros para fazer a query, coloca-se um "?".
        String sql = "INSERT INTO tasks (idProject," +
                "name," +
                "description," +
                "completed," +
                "notes," +
                "deadline," +
                "createdAt," +
                "updatedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            
            //prepareStatement preparara uma query (um c�digo sql) para ser executado. Isso evitado sql ejection.
            statement = connection.prepareStatement(sql);
            
            //setantdo os par�metross:
            //setInt, setString... est�o setando os par�metros da query. Neste caso, o primeiro par�metro "?" vai ser substitu�do pelo taskId.
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isCompleted());
            statement.setString(5, task.getNotes());
            //� preciso converter o tipo date do java para o date do sql. Ambos s�o diferentes. Esse date abaixo � o java.sql.Date
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            
            statement.execute();
                        
        } catch (Exception ex) { 
            throw new RuntimeException("Erro ao salvar a tarefa" + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    public void update(Task task) {
        String sql = "UPDATE tasks SET "
                     + "idProject = ?, "
                     + "name = ?, "
                     + "description = ?, "
                     + "notes = ?, "
                     + "completed = ?, "
                     + "deadline = ?, "
                     + "createdAt = ?, "
                     + "updatedAt = ? "
                     + "WHERE id = ?";
        
        Connection connection = null; 
        PreparedStatement statement = null; 
        
        try {
            //Estabelecendo a conex�o com o db
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
            // preparando a query
            statement.setInt(1, task.getIdProject());
            
            // setando os valores do statement
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getNotes());
            statement.setBoolean(5, task.isCompleted());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.setInt(9, task.getId());
            
            //executando a query
            statement.execute();
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao atualizar a tarefa", ex);
        }
    }
    
    public void removeById(int taskId) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        
        try { 
            //estabelecendo a conexao com o db
            connection = ConnectionFactory.getConnection();
            
            // preparando a query
            statement = connection.prepareStatement(sql);
            
            
            // setando os valores do statement
            statement.setInt(1, taskId);
            
            // executando a query
            statement.execute();
        } catch (Exception ex) { 
            throw new RuntimeException("Erro ao deletar a tarefa", ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    public List<Task> getAll(int idProject) {
        String sql = "SELECT * FROM tasks WHERE idProject = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        //ResultSet � um dado para guarda retorno de um comando SQL. Neste caso, guardar� os dados retornados pelo SELECT.
        ResultSet resultSet = null;
        
        //lista de tarefas que ser� devolvida quando esse m�todo for chamado
        List<Task> tasks = new ArrayList<Task>();
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idProject);
            
            //executeQuery executa o select e retorna os dados.
            resultSet = statement.executeQuery();
            
            //esse while � executado enquanto houver um pr�ximo valor dentro de resultSet.
            while(resultSet.next()) {
                Task task = new Task();
                
                // o par�metro dentro de getInt(), getString(), � o nome da coluna que est� no db.
                task.setId(resultSet.getInt("id"));
                task.setIdProject(resultSet.getInt("idProject"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setNotes(resultSet.getString("notes"));
                task.setCompleted(resultSet.getBoolean("completed"));
                task.setDeadline(resultSet.getDate("deadline"));
                task.setCreatedAt(resultSet.getDate("createdAt"));
                task.setUpdatedAt(resultSet.getDate("updatedAt"));
                
                tasks.add(task);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao carregar as tarefas" + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
        
        //retornando todas tarefas do projeto especificado.
        return tasks;
    }
}
