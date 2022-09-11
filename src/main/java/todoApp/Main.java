/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todoApp;

import controller.ProjectController;
import controller.TaskController;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import model.Project;
import model.Task;
import util.ConnectionFactory;

/**
 *
 * @author vit
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
          // -> teste do model e do controller do projeto:
          
//        ProjectController projectController = new ProjectController();      
//        Project project = new Project();
//        project.setName("Projeto teste");
//        project.setDescription("description");
//        projectController.save(project);
        
   
//        ProjectController projectController = new ProjectController();   
//        Project project = new Project();
//        project.setId(1);
//        project.setName("Projeto teste atualizado");
//        project.setDescription("description");
//
//        projectController.update(project);
//
//        List<Project> projects = projectController.getAll();
//        System.out.println("Total de projetos" + projects.size());
//        
//        projectController.removeById(1);


            // -> teste do model e do controller do projeto:

//          TaskController taskController = new TaskController();
//          Task task = new Task();
//          
//          task.setIdProject(2);
//          task.setName("teste");
//          task.setDescription("teste desc");
//          task.setCompleted(false);
//          task.setDeadline(new Date());
//          
//          taskController.save(task);
          
//            TaskController taskController = new TaskController();
//            Task task = new Task();
//         
//            task.setId(1);
//            task.setIdProject(2);
//            task.setName("teste 2");
//            task.setDescription("teste desc");
//            task.setCompleted(true);
//            task.setDeadline(new Date());
//            
//            taskController.update(task);
//            List<Task> tasks = taskController.getAll(2);
//            System.out.println("Total de projetos" + tasks.size());

    }
    
}
