package Modelo;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Aron
 */
public class Proyecto {
    private int idProyecto;
    private String nombre;
    private ArrayList<Task> tareas;

    public Proyecto() {
    }

    public Proyecto(int idProyecto, String nombre) {
        this.idProyecto = idProyecto;
        this.nombre = nombre;
        this.tareas = new ArrayList<>();
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Task> getTareas() {
        return tareas;
    }

    public void setTareas(ArrayList<Task> tareas) {
        this.tareas = tareas;
    }
    
    public void agregarTarea(Task tarea) {
        tareas.add(tarea);
    }
    
    public ArrayList<Task> obtenerTareas() {
        return tareas;
    }
    
    public ArrayList obtenerTareasPendientes(){
        ArrayList<Task> pendientes = new ArrayList<>();
        for (Task tarea : tareas) {
            if(!tarea.isCompletada()) {
                pendientes.add(tarea);
            }
        }
        return pendientes;
    }

    public ArrayList<Task> obtenerTareasCompletadas8() {
        ArrayList<Task> completadas = new ArrayList<>();
        for (Task tarea : tareas) {
            if(tarea.isCompletada()) {
                completadas.add(tarea);
            }
        }
        return completadas;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}


