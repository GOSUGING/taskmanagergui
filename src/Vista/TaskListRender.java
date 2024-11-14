/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;


import Modelo.Task;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Aron
 */
public class TaskListRender extends JCheckBox implements ListCellRenderer<Task> {

    @Override
    public Component getListCellRendererComponent(JList<? extends Task> list, Task value, int index, boolean isSelected, boolean cellHasFocus) {
        setText(value.getNombre() + " | Prioridad: " + value.mostrarPrioridad() + " | Fecha: " + value.mostrarFechaLimite());
        
        setSelected(value.isCompletada());
        
        if(isSelected) {
            setBackground(Color.CYAN);
        } else {
            setBackground(Color.WHITE);
        }
        setFocusable(false);
        
        addItemListener((ItemEvent e) -> {
            Task tarea = (Task) list.getModel().getElementAt(index);
            
            tarea.setCompletada(isSelected());
        }); {
        
    }
        
        return this;
    }
    
}
