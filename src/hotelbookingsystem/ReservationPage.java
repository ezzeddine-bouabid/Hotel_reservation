/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hotelbookingsystem;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author ezzeddine
 */
public class ReservationPage extends javax.swing.JFrame {

    /**
     * Creates new form ReservationPage
     */
    public ReservationPage() {
        initComponents();
        loadAvailableRooms();
        setupGenderComboBox();
    }
    private void setupGenderComboBox() {
    // Créez un modèle pour le JComboBox
    DefaultComboBoxModel<String> genderModel = new DefaultComboBoxModel<>();
    
    // Ajoutez les options "Homme" et "Femme"
    genderModel.addElement("Homme");
    genderModel.addElement("Femme");
    
    // Affectez ce modèle au JComboBox
    jComboBox1.setModel(genderModel);
}

     private void loadAvailableRooms() {
    try {
        // Connexion à la base de données
        Connection conn = connectionDB.connect();
        
        // Requête pour obtenir les chambres disponibles
        String query = "SELECT room_number FROM rooms WHERE status = 'available'";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        
        // Modèle pour le JComboBox
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        
        // Ajout des chambres disponibles au modèle
        while (rs.next()) {
            String roomNumber = rs.getString("room_number");
            model.addElement(roomNumber);
        }
        
        // Affecter le modèle au JComboBox
        jComboBox2.setModel(model);
        
        // Fermeture des ressources
        rs.close();
        stmt.close();
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Erreur lors du chargement des chambres disponibles : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}


private void enregistrerReservation() {
    String nomClient = jTextField1.getText();
    String numTelephone = jTextField2.getText();
    String email = jTextField3.getText();
    String genre = (String) jComboBox1.getSelectedItem();
    String chambreNumero = (String) jComboBox2.getSelectedItem();

    // Afficher les valeurs pour debug
    System.out.println("Nom: " + nomClient);
    System.out.println("Téléphone: " + numTelephone);
    System.out.println("Email: " + email);
    System.out.println("Genre: " + genre);
    System.out.println("Chambre: " + chambreNumero);

    try {
        Connection conn = connectionDB.connect();

        // Obtenez l'ID de la chambre à partir de son numéro
        String queryChambre = "SELECT id FROM rooms WHERE room_number = ?";
        PreparedStatement stmtChambre = conn.prepareStatement(queryChambre);
        stmtChambre.setString(1, chambreNumero);
        ResultSet rsChambre = stmtChambre.executeQuery();
        int chambreId = -1;
        if (rsChambre.next()) {
            chambreId = rsChambre.getInt("id");
        }

        if (chambreId == -1) {
            JOptionPane.showMessageDialog(this, "Chambre non trouvée.");
            return;
        }

        // Insérez la réservation
        String queryReservation = "INSERT INTO reservation (nom_client, num_telephone, email, genre, chambre_id) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmtReservation = conn.prepareStatement(queryReservation);
        stmtReservation.setString(1, nomClient);
        stmtReservation.setString(2, numTelephone);
        stmtReservation.setString(3, email);
        stmtReservation.setString(4, genre);
        stmtReservation.setInt(5, chambreId);

        int rowsInserted = stmtReservation.executeUpdate();
        System.out.println("Lignes insérées dans la table reservation: " + rowsInserted);

        // Mettez à jour le statut de la chambre à "occupée"
        String updateChambre = "UPDATE rooms SET status = 'occupied' WHERE id = ?";
        PreparedStatement stmtUpdateChambre = conn.prepareStatement(updateChambre);
        stmtUpdateChambre.setInt(1, chambreId);
        int rowsUpdated = stmtUpdateChambre.executeUpdate();
        System.out.println("Lignes mises à jour dans la table rooms: " + rowsUpdated);

        if (rowsInserted > 0 && rowsUpdated > 0) {
            JOptionPane.showMessageDialog(this, "Réservation enregistrée et chambre marquée comme occupée.");
        } else {
            JOptionPane.showMessageDialog(this, "Erreur : réservation ou mise à jour de chambre échouée.");
        }

        // Fermeture des ressources
        stmtChambre.close();
        stmtReservation.close();
        stmtUpdateChambre.close();
        conn.close();

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Erreur lors de l'enregistrement de la réservation : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Andalus", 0, 18)); // NOI18N
        jLabel1.setText("Nom :");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 79, 25));

        jTextField1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 203, 46));

        jLabel2.setFont(new java.awt.Font("Andalus", 0, 18)); // NOI18N
        jLabel2.setText("N° de telephone:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 145, 36));

        jLabel3.setFont(new java.awt.Font("Andalus", 0, 18)); // NOI18N
        jLabel3.setText("e-mail :");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 89, 43));

        jTextField3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, 203, 43));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 203, 45));

        jLabel4.setFont(new java.awt.Font("Andalus", 0, 18)); // NOI18N
        jLabel4.setText("Genre :");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 76, 45));

        jLabel5.setFont(new java.awt.Font("Andalus", 0, 18)); // NOI18N
        jLabel5.setText("Chambre :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, 98, 39));

        jButton1.setBackground(new java.awt.Color(204, 204, 255));
        jButton1.setFont(new java.awt.Font("Andalus", 0, 18)); // NOI18N
        jButton1.setText("confirmer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 350, 200, 43));

        jTextField2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 203, 40));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 280, 203, 45));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotelbookingsystem/images (2).png"))); // NOI18N
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, 250, 330));

        jButton2.setBackground(new java.awt.Color(204, 204, 255));
        jButton2.setFont(new java.awt.Font("Andalus", 0, 18)); // NOI18N
        jButton2.setText("retour");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 405, 200, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, -3, 800, 520));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        enregistrerReservation();
        this.dispose();
    
    // Créer et afficher la page d'accueil
    new ReservationPage().setVisible(true); 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         this.dispose();
    
    // Créer et afficher la page d'accueil
    new Home().setVisible(true); 
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
      public static void main(String args[]) {
}

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}

class connectionDB {
    public static Connection connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/hotel_booking"; // Replace with your database URL
        String user = "root"; // Replace with your database username
        String password = ""; // Replace with your database password
        return DriverManager.getConnection(url, user, password);
    }
}