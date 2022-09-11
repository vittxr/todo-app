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
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.ConnectionFactory;

/**
 *
 * @author vit
 */
public class ProjectController {
   public static void save(Project project) { 
      String sql = "INSERT INTO projects (" 
                    + "name, "
                    + "description, "
                    + "createdAt, "
                    + "updatedAt) VALUES (?, ?, ?, ?)";
      
      Connection connection = null;
      PreparedStatement statement = null;
      
       try {
           connection = ConnectionFactory.getConnection();
           
           statement = connection.prepareStatement(sql);
           
           statement.setString(1, project.getName());           
           statement.setString(2, project.getDescription());  
           statement.setDate(3, new Date(project.getCreatedAt().getTime()));
           statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
           
           statement.execute();
       } catch (Exception ex) {
           throw new RuntimeException("Erro em criar o projeto" + ex.getMessage(), ex);
       } finally {
           ConnectionFactory.closeConnection(connection, statement);
       }
   }
   
   public static void update(Project project) {
        String sql = "UPDATE projects SET "
                     + "name = ?, "
                     + "description = ?, "
                     + "createdAt = ?, "
                     + "updatedAt = ? "
                     + "WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
           connection = ConnectionFactory.getConnection();
           statement = connection.prepareStatement(sql);
           
           statement.setString(1, project.getName());
           statement.setString(2, project.getDescription());
           statement.setDate(3, new Date(project.getCreatedAt().getTime()));
           statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
           statement.setInt(5, project.getId());
           
           statement.execute();
       } catch (Exception ex) {
           throw new RuntimeException("Erro em atualizar o projeto" + ex.getMessage(), ex);
       } finally {
           ConnectionFactory.closeConnection(connection, statement);     
       }
   }
   
   public List<Project> getAll() {
        String sql = "SELECT * FROM projects";
       
        Connection connection = null;
        PreparedStatement statement = null;
        
        //ResultSet � um dado para guarda retorno de um comando SQL. Neste caso, guardar� os dados retornados pelo SELECT.
        ResultSet resultSet = null;
        
        //lista de tarefas que ser� devolvida quando esse m�todo for chamado
        List<Project> projects = new ArrayList<Project>();
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
            //executeQuery executa o select e retorna os dados.
            resultSet = statement.executeQuery();
            
            //esse while � executado enquanto houver um pr�ximo valor dentro de resultSet.
            while(resultSet.next()) {
                Project project = new Project();
                
                // o par�metro dentro de getInt(), getString(), � o nome da coluna que est� no db.
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setCreatedAt(resultSet.getDate("createdAt"));
                project.setUpdatedAt(resultSet.getDate("updatedAt"));
  
                projects.add(project);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao carregar os projetos" + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
        
        return projects;
   }
   
   public static void removeById(int idProject) { 
      String sql = "DELETE FROM projects WHERE id = ?";
      
      Connection connection = null;
      PreparedStatement statement = null;
      
       try {
         connection = ConnectionFactory.getConnection();
         statement = connection.prepareStatement(sql);
         
         statement.setInt(1, idProject);
         statement.execute();
       } catch (Exception ex) {
           throw new RuntimeException("Erro em deletar o projeto" + ex.getMessage(), ex);
       } finally {
           ConnectionFactory.closeConnection(connection, statement);
       }
   }
}
