package proyecto1;

import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;

public class ListadoEventosFrame extends javax.swing.JInternalFrame {

    //Se define la tabla tablaListado
    private DefaultTableModel tablaListado = new DefaultTableModel();
    ;
    
    public ListadoEventosFrame() {
        initComponents();

        //Metodo llamados para agregar columnas a la tabla
        cargarModeloTablas();

    }

    private void cargarModeloTablas() {
        
        //Se agregan las columnas a la tabla
        tablaListado.addColumn("ID");
        tablaListado.addColumn("Año");
        tablaListado.addColumn("Mes");
        tablaListado.addColumn("Día");
        tablaListado.addColumn("Hora");
        tablaListado.addColumn("Deporte");
        tablaListado.addColumn("Nombre");
        tablaListado.addColumn("País");
        tablaListado.addColumn("Equipo A");
        tablaListado.addColumn("Equipo B");

        //Se obtiene la la lista de eventos 
        LinkedList<Evento> listaEvento = Evento.getListadoEventos();

        //Se crea un iterador para recorrer listaEventos
        Iterator<Evento> iterator = listaEvento.iterator();

        while (iterator.hasNext()) {
            Evento evento = iterator.next();

            //Referencia 1. pedro yuyarima paredes( 5 de marzo del 2015).Manejo de Tabla en Java. https://www.youtube.com/watch?v=6GMdqSO7OnQ&t=520s
            //Por cada evento se agrega una fila
            tablaListado.addRow(new Object[]{
                evento.getIdEvento(),
                evento.getAnioEvento(),
                evento.getMesEvento(),
                evento.getDiaEvento(),
                evento.getHoraEvento(),
                evento.getDeporteEvento(),
                evento.getNombreEvento(),
                evento.getPaisEvento(),
                evento.getEquipoAEvento(),
                evento.getEquipoBEvento()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        modeloTabla = new javax.swing.JTable();

        setTitle("Listado de eventos");

        modeloTabla.setBackground(new java.awt.Color(204, 204, 204));
        modeloTabla.setModel(tablaListado);
        jScrollPane1.setViewportView(modeloTabla);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 909, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable modeloTabla;
    // End of variables declaration//GEN-END:variables
}
