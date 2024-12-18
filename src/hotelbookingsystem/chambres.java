/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hotelbookingsystem;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class chambres extends javax.swing.JFrame {

    public chambres() {
        initComponents(); // Appel de la méthode générée automatiquement
        loadRoomData("");   // Charge les données dès le lancement
        jButton2.addActionListener(evt -> updateRoomStatus());
        jComboBox1.addActionListener(evt -> filterRooms());
    }
private void filterRooms() {
        String selectedStatus = (String) jComboBox1.getSelectedItem();
        
        // Update the table with filtered data based on the selected status
        if (selectedStatus != null) {
            loadRoomData(selectedStatus);
        }
    }
    /**
     * Cette méthode récupère les données de la base de données et les affiche dans le tableau.
     */
   private void loadRoomData(String statusFilter) {
    String sql = "SELECT * FROM rooms"; 
    
    // If a status filter is applied, modify the SQL query
    if (statusFilter != null && !statusFilter.isEmpty()) {
        sql += " WHERE status = '" + statusFilter + "'";
    }

    try (Connection conn = connectionDB.connect();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear existing rows

        while (rs.next()) {
            Object[] row = {
                rs.getInt("id"),
                rs.getString("room_number"),
                rs.getString("type"),
                rs.getDouble("price"),
                rs.getString("status")
            };
            model.addRow(row);
        }
    } catch (SQLException e) {
        System.err.println("Erreur lors de la récupération des données : " + e.getMessage());
    }
}

    
private void updateRoomStatus() {
    int selectedRow = jTable1.getSelectedRow(); // Récupère la ligne sélectionnée

    if (selectedRow != -1) {
        // Récupérer l'ID de la chambre
        int chambre_id = (int) jTable1.getValueAt(selectedRow, 0);
        String roomNumber = (String) jTable1.getValueAt(selectedRow, 1); // Numéro de chambre

        // SQL pour mettre à jour le statut dans la table rooms
        String updateRoomSql = "UPDATE rooms SET status = 'Available' WHERE id = " + chambre_id;

        // SQL pour supprimer la réservation liée dans la table reservation
        String deleteReservationSql = "DELETE FROM reservation WHERE chambre_id = " + chambre_id;

        try (Connection conn = connectionDB.connect();
             Statement stmt = conn.createStatement()) {

            // Exécuter la mise à jour du statut
            int roomUpdated = stmt.executeUpdate(updateRoomSql);

            // Exécuter la suppression de la réservation
            int reservationDeleted = stmt.executeUpdate(deleteReservationSql);

            if (roomUpdated > 0) {
                System.out.println("Statut de la chambre " + roomNumber + " mis à jour avec succès.");
                if (reservationDeleted > 0) {
                    System.out.println("Réservation liée à la chambre " + roomNumber + " supprimée.");
                } else {
                    System.out.println("Aucune réservation trouvée pour cette chambre.");
                }
                // Recharger les données après la mise à jour
                loadRoomData("");
            } else {
                System.out.println("Impossible de mettre à jour le statut de la chambre.");
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour ou de la suppression : " + e.getMessage());
        }
    } else {
        System.out.println("Veuillez sélectionner une chambre.");
    }
}


    @SuppressWarnings("unchecked")                        
   

    



    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jInternalFrame1.setTitle("les chambres");
        jInternalFrame1.setVisible(true);
        jInternalFrame1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 204, 204));
        jScrollPane1.setForeground(new java.awt.Color(204, 204, 255));
        jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable1.setBackground(new java.awt.Color(204, 204, 204));
        jTable1.setFont(new java.awt.Font("Calibri Light", 2, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "N° de chambre", "Type", "Prix", "Status"
            }
        ));
        jTable1.setAlignmentX(1.0F);
        jTable1.setAlignmentY(1.0F);
        jTable1.setIntercellSpacing(new java.awt.Dimension(1, 1));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
        }

        jInternalFrame1.getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 779, 291));

        jButton2.setBackground(new java.awt.Color(204, 204, 255));
        jButton2.setFont(new java.awt.Font("Andalus", 0, 18)); // NOI18N
        jButton2.setText("Enregistrer les modification");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jInternalFrame1.getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(295, 370, 240, -1));

        jButton1.setBackground(new java.awt.Color(204, 204, 255));
        jButton1.setFont(new java.awt.Font("Andalus", 0, 18)); // NOI18N
        jButton1.setText("retour");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jInternalFrame1.getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 420, 230, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "available", "occupied" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jInternalFrame1.getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 80, 30));

        jLabel1.setFont(new java.awt.Font("Andalus", 1, 18)); // NOI18N
        jLabel1.setText("Filtrer :");
        jInternalFrame1.getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 80, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jInternalFrame1, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    
    // Créer et afficher la page d'accueil
    new chambres().setVisible(true); 
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    // Add listener to JComboBox
    
    


    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         this.dispose();

    // Create and display the Home window
    new Home().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
