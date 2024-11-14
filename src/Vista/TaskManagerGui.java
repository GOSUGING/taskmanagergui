/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Modelo.Proyecto;
import Modelo.Task;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Aron
 */
public class TaskManagerGui extends javax.swing.JFrame {
    private final DefaultListModel<Modelo.Task> modeloTarea; 
    private final ArrayList<Proyecto> proyectos = new ArrayList<>();
   
    
    public TaskManagerGui() {
        initComponents();
        jcbx_proyecto.setModel(new DefaultComboBoxModel<>());
        modeloTarea = new DefaultListModel<>();
        jlst_tarea.setModel(modeloTarea); 
        jlst_tarea.setCellRenderer(new TaskListRender());

        actualizarListaProyectos();
        jbtn_agregar_proyecto.addActionListener(e -> agregarProyecto());
        jbtn_agregar.addActionListener((ActionEvent e) -> {
            agregarTarea();            
        });

        jlst_tarea.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int index = jlst_tarea.locationToIndex(e.getPoint());
                if(index != -1) {
                    Task tarea = modeloTarea.elementAt(index);
                    tarea.setCompletada(!tarea.isCompletada());
                    jlst_tarea.repaint();
                }
            }
        });

        // Agregar ItemListener para los checkboxes de prioridad
        jchk_alta.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                jchk_media.setSelected(false);
                jchk_baja.setSelected(false);
            }
        });

        jchk_media.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                jchk_alta.setSelected(false);
                jchk_baja.setSelected(false);
            }
        });

        jchk_baja.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                jchk_alta.setSelected(false);
                jchk_media.setSelected(false);
            }
        });
    }
    
    

    private void agregarTarea() {
      String nombre = jtxt_nombre.getText();
      String fecha = jtxt_fecha.getText();
      int prioridad = getPrioridadSeleccionada();

      if (nombre.isEmpty() || fecha.isEmpty() || prioridad == -1) {
          JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos y seleccione una prioridad.");
          return; 
      }

      SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      Date fechaLimite;
      try {
          fechaLimite = dateFormat.parse(fecha);
      } catch (ParseException e) {
          JOptionPane.showMessageDialog(this, "Formato de fecha invalido, Use dd/MM/yyyy.");
          return;
      }

      Date fechaActual = new Date();
      if(fechaLimite.before(fechaActual)){
          JOptionPane.showMessageDialog(this, "Fecha Limite tiene que ser posterior a la fecha actual.");
          return;
      }

      Proyecto proyectoSeleccion = (Proyecto) jcbx_proyecto.getSelectedItem();
      if(proyectoSeleccion != null) {
          Task nuevaTarea = new Task(
              proyectoSeleccion.obtenerTareas().size() + 1,
              nombre,
              fechaLimite,
              prioridad,
              false,
              proyectoSeleccion // Pasar el proyecto aquí
          );
          proyectoSeleccion.agregarTarea(nuevaTarea); 
          modeloTarea.addElement(nuevaTarea);
      } else {
          JOptionPane.showMessageDialog(this, "Seleccione un proyecto válido.");
      }      
  }
    
    private void agregarProyecto() {
        String nombre = jtxt_nombre_proyecto.getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre del proyecto no puede estar vacío.");
            return;
        }

        // Crear el nuevo proyecto
        Proyecto nuevoProyecto = new Proyecto(PROPERTIES, nombre); // Asegúrate de pasar los parámetros correctos.
        proyectos.add(nuevoProyecto);

        // Limpiar el campo de texto
        jtxt_nombre_proyecto.setText("");

        // Actualizar el ComboBox
        actualizarListaProyectos();
    }

    

private void actualizarListaProyectos() {
    DefaultComboBoxModel<Proyecto> modeloProyecto = new DefaultComboBoxModel<>();
    for (Proyecto proyecto : proyectos) {
        modeloProyecto.addElement(proyecto);
    }
    jcbx_proyecto.setModel(modeloProyecto); // Asegúrate de que esto actualice el JComboBox que se muestra
}
    
    


    
    

    
    private int getPrioridadSeleccionada() {
       if (jchk_alta.isSelected()) return 3;
       if (jchk_media.isSelected()) return 2;
       if (jchk_baja.isSelected()) return 1;
       return -1;
    }

    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlb_nombre = new javax.swing.JLabel();
        jlbl_fecha = new javax.swing.JLabel();
        jtxt_nombre = new javax.swing.JTextField();
        jtxt_fecha = new javax.swing.JTextField();
        jlbl_prioridad = new javax.swing.JLabel();
        jchk_alta = new javax.swing.JCheckBox();
        jchk_media = new javax.swing.JCheckBox();
        jchk_baja = new javax.swing.JCheckBox();
        jbtn_agregar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlst_tarea = new javax.swing.JList<Task>();
        jcbx_proyecto = new javax.swing.JComboBox<>();
        jtxt_nombre_proyecto = new javax.swing.JTextField();
        jlbl_nombre_proyecto = new javax.swing.JLabel();
        jbtn_agregar_proyecto = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jlb_nombre.setText("Nombre Tarea");

        jlbl_fecha.setText("Fecha");

        jtxt_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxt_nombreActionPerformed(evt);
            }
        });

        jlbl_prioridad.setText("Prioridad");

        jchk_alta.setText("Alta");

        jchk_media.setText("Media");

        jchk_baja.setText("Baja");

        jbtn_agregar.setText("Agregar");

        jScrollPane1.setViewportView(jlst_tarea);

        jtxt_nombre_proyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxt_nombre_proyectoActionPerformed(evt);
            }
        });

        jlbl_nombre_proyecto.setText("Nombre Proyecto");

        jbtn_agregar_proyecto.setText("Agregar Proyecto");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbl_prioridad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jchk_alta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jchk_media)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jchk_baja))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbl_fecha)
                        .addGap(68, 68, 68)
                        .addComponent(jtxt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtn_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlbl_nombre_proyecto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtxt_nombre_proyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlb_nombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jtxt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jbtn_agregar_proyecto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jcbx_proyecto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 63, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxt_nombre_proyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbl_nombre_proyecto)
                    .addComponent(jbtn_agregar_proyecto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlb_nombre)
                    .addComponent(jtxt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbx_proyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbl_fecha)
                    .addComponent(jtxt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtn_agregar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbl_prioridad)
                    .addComponent(jchk_alta)
                    .addComponent(jchk_media)
                    .addComponent(jchk_baja))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxt_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxt_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxt_nombreActionPerformed

    private void jtxt_nombre_proyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxt_nombre_proyectoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxt_nombre_proyectoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TaskManagerGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TaskManagerGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TaskManagerGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TaskManagerGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new TaskManagerGui().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtn_agregar;
    private javax.swing.JButton jbtn_agregar_proyecto;
    private javax.swing.JComboBox<Proyecto> jcbx_proyecto;
    private javax.swing.JCheckBox jchk_alta;
    private javax.swing.JCheckBox jchk_baja;
    private javax.swing.JCheckBox jchk_media;
    private javax.swing.JLabel jlb_nombre;
    private javax.swing.JLabel jlbl_fecha;
    private javax.swing.JLabel jlbl_nombre_proyecto;
    private javax.swing.JLabel jlbl_prioridad;
    private javax.swing.JList<Task> jlst_tarea;
    private javax.swing.JTextField jtxt_fecha;
    private javax.swing.JTextField jtxt_nombre;
    private javax.swing.JTextField jtxt_nombre_proyecto;
    // End of variables declaration//GEN-END:variables

    

    
}
