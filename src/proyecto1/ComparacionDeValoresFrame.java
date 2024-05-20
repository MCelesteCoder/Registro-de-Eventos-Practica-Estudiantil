package proyecto1;

import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;

public class ComparacionDeValoresFrame extends javax.swing.JInternalFrame {

    private DefaultTableModel tablaValores = new DefaultTableModel();

    public ComparacionDeValoresFrame() {
        initComponents();

        //Metodos llamados para agregar opciones a las DropDownList
        AgregarHoras1();
        AgregarHoras2();

        //Metodo llamados para agregar columnas a la tabla
        cargarModeladoTablaValores();
    }

    private int ContadorI() {
        //Se calcula el valor mas pequeño entre dos horas
        String hora1 = (String) DDLHora1.getSelectedItem();
        String hora2 = (String) DDLHora2.getSelectedItem();

        int contI;
        
        //Se eliminan los ":" para hacer la comparacion
        int hora1C = Integer.parseInt(hora1.replace(":", ""));
        int hora2C = Integer.parseInt(hora2.replace(":", ""));

        if (hora1C < hora2C) {
            contI = hora1C;

        } else {
            contI = hora2C;
        }
        return contI;
    }

    private int ContadorF() {
        //Se calcula el valor mas grande entre dos horas
        String hora1 = (String) DDLHora1.getSelectedItem();
        String hora2 = (String) DDLHora2.getSelectedItem();

        int contF;

         //Se eliminan los ":" para hacer la comparacion
        int hora1C = Integer.parseInt(hora1.replace(":", ""));
        int hora2C = Integer.parseInt(hora2.replace(":", ""));

        if (hora1C < hora2C) {
            contF = hora2C;
        } else {

            contF = hora1C;
        }
        return contF;
    }

    private void registroHoras(int contI, int contF, Iterator<Evento> iterator) {

        if (!iterator.hasNext()) {
            return; //Cuando no hay más eventos termina la recursión
        }

        Evento evento = iterator.next();
        String horaEventoStr = evento.getHoraEvento();

        //Se convierte la hora del evento a numero 
        int horasEvento = Integer.parseInt(horaEventoStr.replace(":", ""));

        //Se filtran las horas por un rango de horas
        //ContI y contF funcionan como limites y con el if se verifcia si horasEvento estan dentro del rango
        if (horasEvento >= contI && horasEvento <= contF) {
            tablaValores.addRow(new Object[]{
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

        //Llamada recursiva para el siguiente evento
        registroHoras(contI, contF, iterator);

    }

    private void cargarModeladoTablaValores() {
        //Se agregan las columnas a la tabla de Valores
        tablaValores.addColumn("ID");
        tablaValores.addColumn("Año");
        tablaValores.addColumn("Mes");
        tablaValores.addColumn("Día");
        tablaValores.addColumn("Hora");
        tablaValores.addColumn("Deporte");
        tablaValores.addColumn("Nombre");
        tablaValores.addColumn("País");
        tablaValores.addColumn("Equipo A");
        tablaValores.addColumn("Equipo B");
    }

    private void AgregarHoras1() {
        //Opciones del primer menu desplegable de horas
        for (int i = 0; i < 24; i++) {
            if (i >= 10) {
                DDLHora1.addItem(i + ":00");
                DDLHora1.addItem(i + ":30");
            } else {
                DDLHora1.addItem("0" + i + ":00");
                DDLHora1.addItem("0" + i + ":30");
            }
        }
    }

    private void AgregarHoras2() {
        //Opciones del segundo menu desplegable de horas
        for (int i = 0; i < 24; i++) {
            if (i >= 10) {
                DDLHora2.addItem(i + ":00");
                DDLHora2.addItem(i + ":30");
            } else {
                DDLHora2.addItem("0" + i + ":00");
                DDLHora2.addItem("0" + i + ":30");
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        DDLHora1 = new javax.swing.JComboBox<>();
        DDLHora2 = new javax.swing.JComboBox<>();
        btnBuscarHoras = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setTitle("Comparación de valores");

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Digite el rango de horas de los registros que desea ver:");

        btnBuscarHoras.setBackground(new java.awt.Color(153, 153, 153));
        btnBuscarHoras.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        btnBuscarHoras.setForeground(new java.awt.Color(102, 102, 102));
        btnBuscarHoras.setText("Buscar");
        btnBuscarHoras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarHorasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel1)
                .addGap(49, 49, 49)
                .addComponent(DDLHora1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(DDLHora2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(btnBuscarHoras)
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnBuscarHoras, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(DDLHora1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(DDLHora2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jTable1.setModel(tablaValores);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarHorasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarHorasActionPerformed
        int contI = ContadorI();
        int contF = ContadorF();

        tablaValores.setRowCount(0);

        LinkedList<Evento> listaEvento = Evento.getListadoEventos();
        Iterator<Evento> iterator = listaEvento.iterator();

        registroHoras(contI, contF, iterator);
    }//GEN-LAST:event_btnBuscarHorasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> DDLHora1;
    private javax.swing.JComboBox<String> DDLHora2;
    private javax.swing.JButton btnBuscarHoras;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
