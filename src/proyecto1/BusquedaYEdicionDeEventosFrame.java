/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package proyecto1;

import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class BusquedaYEdicionDeEventosFrame extends javax.swing.JInternalFrame {

    private DefaultTableModel tablaBusqueda = new DefaultTableModel();
    private DefaultTableModel tablaEdicion = new DefaultTableModel();

    public BusquedaYEdicionDeEventosFrame() {
        initComponents();

        //se utiliza para modificar los datos de la tabla
        tbl.setModel(tablaEdicion);

        //Metodo llamados para agregar columnas a la tabla
        cargarTablaBusqueda();
        cargarTablaEdicion();

        //Metodos llamados para agregar opciones a las DropDownList
        AgregarMeses();
        AgregarMes();
        AgregarHoras();
        AgregarDeportes();
    }

    private void buscarPorMesAnio() {

        try {

            String anioEventoText = txtAnioEvento.getText();

            // Se erifica si el campo está vacío
            if (anioEventoText.isEmpty()) {
                throw new IllegalArgumentException("El campo año del evento está vacío. Por favor, ingrese un número.");
            }

            //Se obtiene mes y Año para la busqueda de registros
            // Intenta convertir el texto a un entero
            int anioEvento = Integer.parseInt(anioEventoText);

            String mesEvento = (String) DDLMes.getSelectedItem();

            //Se eliminan las filas de la tabla
            tablaBusqueda.setRowCount(0);

            LinkedList<Evento> listaEvento = Evento.getListadoEventos();
            Iterator<Evento> iterator = listaEvento.iterator();

            while (iterator.hasNext()) {
                Evento evento = iterator.next();

                //Se el año y mes ingresado por el usuario coiuncide con los de la lista se agrega una fila
                if (anioEvento == evento.getAnioEvento() && (mesEvento.equals(evento.getMesEvento()))) {
                    //https://www.youtube.com/watch?v=6GMdqSO7OnQ&t=520s
                    tablaBusqueda.addRow(new Object[]{
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

        } catch (IllegalArgumentException e) {
            // Se captura la excepción si el campo está vacío
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void editarFila() {
        //Se obtiene el indice de la fila seleccionada 
        int fila = tbl.getSelectedRow();

        LinkedList<Evento> listaEventos = Evento.getListadoEventos();

        if (fila != -1) { // Verifica si se seleciono una fila

            //Se obtiene el id del evento seleccionado de la tabla
            int idEvento = Integer.parseInt(tbl.getValueAt(fila, 0).toString());

            for (Evento evento : listaEventos) {

                //Se busca el evento con el ID que coincida
                if (evento.getIdEvento() == idEvento) {

                    //Se actualiza los atributo con los valores de los campos de texto
                    evento.setAnioEvento(Integer.parseInt(txtAnio.getText()));
                    evento.setMesEvento((String) DDLMeses.getSelectedItem());
                    evento.setDiaEvento(Integer.parseInt(DDLDia.getText()));
                    evento.setHoraEvento((String) DDLHora.getSelectedItem());
                    evento.setDeporteEvento((String) DDLDeporte.getSelectedItem());
                    evento.setNombreEvento(txtNombreE.getText());
                    evento.setPaisEvento(txtPais.getText());
                    evento.setEquipoAEvento(txtEquipoA.getText());
                    evento.setEquipoBEvento(txtEquipoB.getText());

                    //Se actualizan los valores de la tabla
                    tablaEdicion.setValueAt(txtAnio.getText(), fila, 1);
                    tablaEdicion.setValueAt(DDLMeses.getSelectedItem(), fila, 2);
                    tablaEdicion.setValueAt(DDLDia.getText(), fila, 3);
                    tablaEdicion.setValueAt(DDLHora.getSelectedItem(), fila, 4);
                    tablaEdicion.setValueAt(DDLDeporte.getSelectedItem(), fila, 5);
                    tablaEdicion.setValueAt(txtNombreE.getText(), fila, 6);
                    tablaEdicion.setValueAt(txtPais.getText(), fila, 7);
                    tablaEdicion.setValueAt(txtEquipoA.getText(), fila, 8);
                    tablaEdicion.setValueAt(txtEquipoB.getText(), fila, 9);
                }
            }
        } else {
            // Si no se ha seleccionado ninguna fila se le indica al usuario
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para editar.",
                    "Selección requerida", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private void buscarFilaParaEditar() {

        try {
            String idEventoStr = txtIdEvento.getText();

            // Se erifica si el campo está vacío
            if (idEventoStr.isEmpty()) {
                throw new IllegalArgumentException("El campo ID parala búsqueda de registros está vacío. Por favor, ingrese un ID.");
            }

            int idEvento = Integer.parseInt(idEventoStr);

            tablaEdicion.setRowCount(0);

            LinkedList<Evento> listaEvento = Evento.getListadoEventos();
            Iterator<Evento> iterator = listaEvento.iterator();

            while (iterator.hasNext()) {
                Evento evento = iterator.next();

                //Si el ID ingresado por el usario coincide con algun id de la lista se agrega una fila
                if (idEvento == evento.getIdEvento()) {
                    //https://www.youtube.com/watch?v=6GMdqSO7OnQ&t=520s
                    tablaEdicion.addRow(new Object[]{
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

                    //Se actualizan los campos de entrada con los valores de la lista
                    txtAnio.setText(String.valueOf(evento.getAnioEvento()));
                    DDLMeses.setSelectedItem(evento.getMesEvento());
                    DDLDia.setText(String.valueOf(evento.getDiaEvento()));
                    DDLHora.setSelectedItem(evento.getHoraEvento());
                    DDLDeporte.setSelectedItem(evento.getDeporteEvento());
                    txtNombreE.setText(String.valueOf(evento.getNombreEvento()));
                    txtPais.setText(String.valueOf(evento.getPaisEvento()));
                    txtEquipoA.setText(String.valueOf(evento.getEquipoAEvento()));
                    txtEquipoB.setText(String.valueOf(evento.getEquipoBEvento()));
                }

            }
        } catch (IllegalArgumentException e) {
            // Se captura la excepción si el campo está vacío
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void cargarTablaBusqueda() {
        //Se agregan las columnas a la tabla de busqueda
        tablaBusqueda.addColumn("ID");
        tablaBusqueda.addColumn("Año");
        tablaBusqueda.addColumn("Mes");
        tablaBusqueda.addColumn("Día");
        tablaBusqueda.addColumn("Hora");
        tablaBusqueda.addColumn("Deporte");
        tablaBusqueda.addColumn("Nombre");
        tablaBusqueda.addColumn("País");
        tablaBusqueda.addColumn("Equipo A");
        tablaBusqueda.addColumn("Equipo B");

    }

    private void cargarTablaEdicion() {
        //Se agregan las columnas a la tabla de edicion
        tablaEdicion.addColumn("ID");
        tablaEdicion.addColumn("Año");
        tablaEdicion.addColumn("Mes");
        tablaEdicion.addColumn("Día");
        tablaEdicion.addColumn("Hora");
        tablaEdicion.addColumn("Deporte");
        tablaEdicion.addColumn("Nombre");
        tablaEdicion.addColumn("País");
        tablaEdicion.addColumn("Equipo A");
        tablaEdicion.addColumn("Equipo B");

    }

    private void AgregarMeses() {
        //Opciones del menu desplegable de meses de la zona de edicion
        DDLMes.addItem("Enero");
        DDLMes.addItem("Febrero");
        DDLMes.addItem("Marzo");
        DDLMes.addItem("Abril");
        DDLMes.addItem("Mayo");
        DDLMes.addItem("Junio");
        DDLMes.addItem("Julio");
        DDLMes.addItem("Agosto");
        DDLMes.addItem("Setiembre");
        DDLMes.addItem("Octubre");
        DDLMes.addItem("Noviembre");
        DDLMes.addItem("Diciembre");
    }

    private void AgregarMes() {
        //Opciones del menu desplegable de meses de la zona de busqueda
        DDLMeses.addItem("Enero");
        DDLMeses.addItem("Febrero");
        DDLMeses.addItem("Marzo");
        DDLMeses.addItem("Abril");
        DDLMeses.addItem("Mayo");
        DDLMeses.addItem("Junio");
        DDLMeses.addItem("Julio");
        DDLMeses.addItem("Agosto");
        DDLMeses.addItem("Setiembre");
        DDLMeses.addItem("Octubre");
        DDLMeses.addItem("Noviembre");
        DDLMeses.addItem("Diciembre");
    }

    private void AgregarHoras() {
        //Opciones del menu desplegable de horas
        for (int i = 0; i < 24; i++) {
            if (i >= 10) {
                DDLHora.addItem(i + ":00");
                DDLHora.addItem(i + ":30");
            } else {
                DDLHora.addItem("0" + i + ":00");
                DDLHora.addItem("0" + i + ":30");
            }
        }
    }

    private void AgregarDeportes() {
        //Opciones del menu desplegable de deportes
        DDLDeporte.addItem("Fútbol");
        DDLDeporte.addItem("Béisbol");
        DDLDeporte.addItem("Voleibol");
        DDLDeporte.addItem("Básquetbol");
        DDLDeporte.addItem("Fútbol Americano");
        DDLDeporte.addItem("Balonmano");
        DDLDeporte.addItem("Rugby");
        DDLDeporte.addItem("Hockey");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        DDLMes = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtAnioEvento = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtIdEvento = new javax.swing.JTextField();
        btnBuscarFila = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        btnGuardarEdicion = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtAnio = new javax.swing.JTextField();
        DDLDia = new javax.swing.JTextField();
        txtNombreE = new javax.swing.JTextField();
        txtPais = new javax.swing.JTextField();
        txtEquipoA = new javax.swing.JTextField();
        txtEquipoB = new javax.swing.JTextField();
        DDLDeporte = new javax.swing.JComboBox<>();
        DDLHora = new javax.swing.JComboBox<>();
        DDLMeses = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setTitle("Búsqueda y edición de un evento");

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Mes del evento");

        DDLMes.setBackground(new java.awt.Color(204, 204, 204));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Año del evento");

        txtAnioEvento.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel2)
                .addGap(36, 36, 36)
                .addComponent(DDLMes, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(188, 188, 188)
                .addComponent(jLabel3)
                .addGap(40, 40, 40)
                .addComponent(txtAnioEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(DDLMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtAnioEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setModel(tablaBusqueda);
        jScrollPane1.setViewportView(jTable1);

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ingresé el ID del evento que desea editar : ");

        txtIdEvento.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnBuscarFila.setBackground(new java.awt.Color(153, 153, 153));
        btnBuscarFila.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        btnBuscarFila.setForeground(new java.awt.Color(102, 102, 102));
        btnBuscarFila.setText("Buscar");
        btnBuscarFila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarFilaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(210, 210, 210)
                .addComponent(jLabel4)
                .addGap(34, 34, 34)
                .addComponent(txtIdEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(btnBuscarFila, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscarFila, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtIdEvento)
                        .addComponent(jLabel4)))
                .addContainerGap())
        );

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "anio", "mes", "dia", "hora", "deporte", "nom", "pais", "equipoA", "equipoB"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbl);

        btnBuscar.setBackground(new java.awt.Color(102, 102, 102));
        btnBuscar.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnGuardarEdicion.setBackground(new java.awt.Color(102, 102, 102));
        btnGuardarEdicion.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        btnGuardarEdicion.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarEdicion.setText("Guardar");
        btnGuardarEdicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarEdicionActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));

        txtAnio.setBackground(new java.awt.Color(204, 204, 204));

        DDLDia.setBackground(new java.awt.Color(204, 204, 204));

        txtNombreE.setBackground(new java.awt.Color(204, 204, 204));

        txtPais.setBackground(new java.awt.Color(204, 204, 204));

        txtEquipoA.setBackground(new java.awt.Color(204, 204, 204));

        txtEquipoB.setBackground(new java.awt.Color(204, 204, 204));

        DDLDeporte.setBackground(new java.awt.Color(204, 204, 204));

        DDLHora.setBackground(new java.awt.Color(204, 204, 204));

        DDLMeses.setBackground(new java.awt.Color(204, 204, 204));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Año");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Nombre");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("EquipoA");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Deporte");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Hora");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Mes");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Día");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Equipo B");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("País");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Editar : ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel11)
                .addGap(31, 31, 31)
                .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DDLMeses, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(DDLDia, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DDLHora, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DDLDeporte, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombreE, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPais)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEquipoA, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEquipoB, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(jLabel6)
                .addGap(76, 76, 76)
                .addComponent(jLabel12)
                .addGap(74, 74, 74)
                .addComponent(jLabel13)
                .addGap(67, 67, 67)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(47, 47, 47)
                .addComponent(jLabel7)
                .addGap(50, 50, 50)
                .addComponent(jLabel15)
                .addGap(62, 62, 62)
                .addComponent(jLabel8)
                .addGap(41, 41, 41)
                .addComponent(jLabel14)
                .addGap(27, 27, 27))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DDLDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEquipoA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEquipoB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DDLDeporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DDLHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DDLMeses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(340, 340, 340)
                .addComponent(btnGuardarEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(343, 343, 343)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(52, 52, 52)
                .addComponent(btnBuscar)
                .addGap(26, 26, 26)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGuardarEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarPorMesAnio();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnGuardarEdicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarEdicionActionPerformed
        editarFila();
    }//GEN-LAST:event_btnGuardarEdicionActionPerformed

    private void btnBuscarFilaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarFilaActionPerformed
        buscarFilaParaEditar();
    }//GEN-LAST:event_btnBuscarFilaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> DDLDeporte;
    private javax.swing.JTextField DDLDia;
    private javax.swing.JComboBox<String> DDLHora;
    private javax.swing.JComboBox<String> DDLMes;
    private javax.swing.JComboBox<String> DDLMeses;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarFila;
    private javax.swing.JButton btnGuardarEdicion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tbl;
    private javax.swing.JTextField txtAnio;
    private javax.swing.JTextField txtAnioEvento;
    private javax.swing.JTextField txtEquipoA;
    private javax.swing.JTextField txtEquipoB;
    private javax.swing.JTextField txtIdEvento;
    private javax.swing.JTextField txtNombreE;
    private javax.swing.JTextField txtPais;
    // End of variables declaration//GEN-END:variables
}
