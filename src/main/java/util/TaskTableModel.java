/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Task;

/**
 *
 * @author vit
 */
public class TaskTableModel extends AbstractTableModel {
    String[] columns = {"Nome", "Descrição", "Prazo", "Tarefa Concluída", "Editar", "Excluir"};
    List<Task> tasks = new ArrayList();
    
    // Métodos Abstratos (obrigatório declará-los por causa da classe pai): 
    // o decorador @Override serve para sobrescrever o método da classe pai.
    @Override
    public int getRowCount() {
        return tasks.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }
    
    @Override 
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        //essa classe sempre seta as colunas como uma string, por isso estamos a sobrescrevendo, para que ela defina colunas como boolean tmb.
        if(tasks.isEmpty()) {
            //caso a var tasks estiver vazio, retorna-se Object.class, pois é indiferente definir o tipo da coluna, já que não há dados.
            return Object.class;
        }
        
        //define-se o tipo da coluna com o tipo de dado retornado no método getValueAt(), o qual chama os métodos para obter os valores da coluna. Caso esse valor for boolean, seta-se a classe/tipo da coluna como boolena, se for date...
        return this.getValueAt(0, columnIndex).getClass();
    }
    
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        //retorna true apenas caso column index for igual a 3.
        return columnIndex == 3;
    } 

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //Basicamente, com o switch abaixo, estamos fazendo um grid. De acordo com a coluna, exibe-se o registro de tasks na posição rowIndex. 
        switch (columnIndex) {
            case 0:
                return tasks.get(rowIndex).getName();
            case 1: 
                return tasks.get(rowIndex).getDescription();
            case 2: 
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                return dateFormat.format(tasks.get(rowIndex).getDeadline());
            case 3: 
                return tasks.get(rowIndex).isCompleted();
            case 4: 
                return "";
            case 5:
                return "";
            default: 
                return "Dados não encontrado";
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // Este método é sempre chamado quando ocorrer uma alteração nas colunas da jTable.
        
        /* Nesse método, recebe-se aValue como um Object, mesmo sabendo que queremos receber um boolean. Isso é necessário, porque aValue 
        pode ser do tipo String, date e boolean, já que há diferentes colunas. Então, converte-se esse tipo para um dado genérico 
        compatível com todos os tipos de dados (Object no caso). Depois disso, basta fazer o casting do eValue para boolean. */
        tasks.get(rowIndex).setCompleted((boolean) aValue);
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Object get(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
