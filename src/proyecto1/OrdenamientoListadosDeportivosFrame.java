package proyecto1;

import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;

public class OrdenamientoListadosDeportivosFrame extends javax.swing.JInternalFrame {

    DefaultTableModel tablaEquipos = new DefaultTableModel();
    private String[] eventos;

    public OrdenamientoListadosDeportivosFrame() {
        initComponents();

        //Metodos llamados para agregar opciones a las DropDownList
        AgregarEquipos();
        AgregarMetodosOrdenamiento();

         //Metodo llamados para agregar columnas a la tabla
        cargarModeloTablaOrdenamiento();
    }

    private void AgregarEquipos() {
        //Opciones del menu desplegable de equipos
        DDLEquipos.addItem("Equipo A");
        DDLEquipos.addItem("Equipo B");
    }

    private void AgregarMetodosOrdenamiento() {
        //Opciones del menu desplegable de maneras de ordenar
        DDLOrdenar.addItem("Descendente");
        DDLOrdenar.addItem("Ascendente");
    }

    private void cargarModeloTablaOrdenamiento() {
         //Se agregan las columnas a la tabla de ordenamiento
        tablaEquipos.addColumn("ID");
        tablaEquipos.addColumn("Año");
        tablaEquipos.addColumn("Mes");
        tablaEquipos.addColumn("Día");
        tablaEquipos.addColumn("Hora");
        tablaEquipos.addColumn("Deporte");
        tablaEquipos.addColumn("Nombre");
        tablaEquipos.addColumn("País");
        tablaEquipos.addColumn("Equipo A");
        tablaEquipos.addColumn("Equipo B");
    }

    public void llenarArray() {

        LinkedList<Evento> listaEvento = Evento.getListadoEventos();

        eventos = new String[listaEvento.size() * 10];//Debido a que son 10 campos por evento

        int indice = 0;

        Iterator<Evento> iterator = listaEvento.iterator();

        while (iterator.hasNext()) {
            Evento evento = iterator.next();

            //https://www.youtube.com/watch?v=6GMdqSO7OnQ&t=520s
            tablaEquipos.addRow(new Object[]{
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

            //Se llena el array con los datos de cada evento
            eventos[indice++] = (String.valueOf(evento.getIdEvento()));
            eventos[indice++] = (String.valueOf(evento.getAnioEvento()));
            eventos[indice++] = evento.getMesEvento();
            eventos[indice++] = (String.valueOf(evento.getDiaEvento()));
            eventos[indice++] = evento.getHoraEvento();
            eventos[indice++] = evento.getDeporteEvento();
            eventos[indice++] = (String.valueOf(evento.getNombreEvento()));
            eventos[indice++] = (String.valueOf(evento.getPaisEvento()));
            eventos[indice++] = (String.valueOf(evento.getEquipoAEvento()));
            eventos[indice++] = (String.valueOf(evento.getEquipoBEvento()));
        }
    }

    private void OrdenamientoPorEquipos() {

        //Se obtiene el indice del equipo seleccionado
        int equipo = DDLEquipos.getSelectedIndex();

        //Se llama al metodo para llenar el array con los datos del linkedList
        llenarArray();

        //Se actualiza la tabal para evitar que s eacumulen los datos
        actualizarTabla();

        switch (equipo) {
            case 0:
                //Se ordenan los eventos de acuerdo con la columna 8 del array que seria el equipo A
                OrdenamientoDescendenteAscendente(eventos, 8);
                break;

            case 1:
                //Se ordenan los eventos de acuerdo con la columna 9 del array que seria el equipo B
                OrdenamientoDescendenteAscendente(eventos, 9);
                break;
        }
    }

    private void OrdenamientoDescendenteAscendente(String[] eventos, int indiceEquipo) {

        int ordenar = DDLOrdenar.getSelectedIndex();
        
        //La longitud del array es dividida en 10 porque cada evento represenat 1o elementos del array
        int elmnt = eventos.length / 10;//Numero de eventos

        switch (ordenar) {

            case 0:
                //Se recorren todos los elementos del array
                for (int i = 0; i < elmnt; i++) {
                    //Se busca el menor elemento del array antes de ser ordenado
                    int indiceMin = i;
                    for (int e = i + 1; e < elmnt; e++) {

                        //Se comparan los valores de los dos elementos
                        if (eventos[e * 10 + indiceEquipo].compareTo(eventos[indiceMin * 10 + indiceEquipo]) > 0) {

                            indiceMin = e;
                        }
                    }
                    //Aqui se intercambia el elemento menor con el primer elemento del array antes del ordenamiento
                    for (int k = 0; k < 10; k++) {
                        String aux = eventos[i * 10 + k];
                        eventos[i * 10 + k] = eventos[indiceMin * 10 + k];
                        eventos[indiceMin * 10 + k] = aux;
                    }
                }

                break;
            case 1:

                //Dependiendo del indice del equipo se ordena
                if(indiceEquipo == 8 ){
                    quickSort(eventos, 8, 0, elmnt -1);
                }else{
                    quickSort(eventos, 9, 0, elmnt -1);
                }
               
                break;
        }
        actualizarTabla();
    }

    private void quickSort(String[] eventos, int indiceEquipo, int bajo, int alto) {
        //Si alto es menor que bajo se debe ordenar el array
        if (alto > bajo) {
            //Se llama al metodo separacion para dividir el array en dos subarrays
            int p = separacion(eventos, indiceEquipo, bajo, alto);

            //Se ordena a la izquierda los elementos menores a p
            quickSort(eventos, indiceEquipo, bajo, p - 1);
            
            //Se ordena a la derecha los elementos mayores que p
            quickSort(eventos, indiceEquipo, p + 1, alto);
        }
    }

    private int separacion(String[] eventos, int indiceEquipo, int bajo, int alto) {
       // p es selecionado como el ultimo elemento edel array.
        String p = eventos[alto * 10 + indiceEquipo];

        int b = bajo - 1; //Se utiliza para seguir la posicion del ultimo elmento que sea menor que p
        
        //Se recorre el subarray de bajo a alto  y se compara el elementp actual con p
        for (int i = bajo; i < alto; i++) {
            //Si el elemento actual es menor o igual que p se intercambian con el elemento en la posicion b y se incrementa b
            if (eventos[i * 10 + indiceEquipo].compareTo(p) <= 0) {
                b++;
                for (int e = 0; e < 10; e++) {
                    String aux = eventos[b * 10 + e];
                    eventos[b * 10 + e] = eventos[i * 10 + e];
                    eventos[i * 10 + e] = aux;
                }
            }
        }
        
        //Intercambiar el p con el elemento en la posicion (b+1)
        for (int k = 0; k < 10; k++) {
            String aux = eventos[(b+1) * 10 + k];
            eventos[(b+1) * 10 + k] = eventos[alto * 10 + k];
            eventos[alto * 10 + k] = aux;
        }
        
        //Se devuelve la posicion de p
        return b + 1;

    }

    private void actualizarTabla() {

        //Se borran todas las filas que hay en la tabla
        tablaEquipos.setRowCount(0);

        int eventosNum = eventos.length / 10;
        for (int i = 0; i < eventosNum; i++) {
            Object[] filasDatos = new Object[10];

            for (int e = 0; e < 10; e++) {
                filasDatos[e] = eventos[i * 10 + e];

            }
            tablaEquipos.addRow(filasDatos);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        DDLEquipos = new javax.swing.JComboBox<>();
        DDLOrdenar = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        btnOrdenar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setTitle("Ordenamiento de la lista de eventos deportivos");

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Ordenar por :");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Ordenar de manera  :");

        btnOrdenar.setBackground(new java.awt.Color(153, 153, 153));
        btnOrdenar.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        btnOrdenar.setForeground(new java.awt.Color(102, 102, 102));
        btnOrdenar.setText("Ordenar");
        btnOrdenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel1)
                .addGap(53, 53, 53)
                .addComponent(DDLEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(28, 28, 28)
                .addComponent(DDLOrdenar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89)
                .addComponent(btnOrdenar)
                .addGap(53, 53, 53))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(DDLOrdenar, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(btnOrdenar))
                    .addComponent(DDLEquipos)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        jTable1.setModel(tablaEquipos);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenarActionPerformed
        OrdenamientoPorEquipos();
    }//GEN-LAST:event_btnOrdenarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> DDLEquipos;
    private javax.swing.JComboBox<String> DDLOrdenar;
    private javax.swing.JButton btnOrdenar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
