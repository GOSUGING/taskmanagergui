package Modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Aron
 */
public class Task {
    private int idTask;
    private String nombre;
    private Date fechaLimite;
    private int prioridad;
    private boolean completada;
    private Proyecto proyecto;

    public Task() {
    }

    public Task(int idTask, String nombre, Date fechaLimite, int prioridad, boolean completada, Proyecto proyecto) {
        this.idTask = idTask;
        this.nombre = nombre;
        this.fechaLimite = fechaLimite;
        this.prioridad = prioridad;
        this.completada = completada;
        this.proyecto = proyecto;
    }

    public Task(int size) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }
    
    public String mostrarPrioridad() {
        return switch (prioridad) {
            case 1 -> "Baja";
            case 2 -> "Media";
            case 3 -> "Alta";
            default -> "Desconocida";
        };
    }
    
    public String mostrarFechaLimite() {
         if (fechaLimite == null) {
        return "Fecha no asignada"; 
    }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(fechaLimite);
    }
    
    @Override
    public String toString() {
        return  nombre + "Proyecto: " + (proyecto != null ? proyecto.getNombre(): "Sin Proyecto") +  " | " + " Prioridad: "   + this.mostrarPrioridad() + " | " + this.mostrarFechaLimite();
    }
}
