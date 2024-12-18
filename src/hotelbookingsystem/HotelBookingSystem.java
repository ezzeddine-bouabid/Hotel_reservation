package hotelbookingsystem;

import java.sql.Connection;
import java.sql.SQLException;

public class HotelBookingSystem {
    public static void main(String[] args) {
        try {
            // Test de la connexion à la base de données
            Connection conn = connectionDB.connect(); // Make sure connect() handles exceptions
            if (conn != null) {
                System.out.println("Test de connexion réussi !");
                
                // Ouvrir la page de login ou directement la page de chambres si la connexion réussie
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        // Lancer la fenêtre de login ou chambres
                        new Loginframe().setVisible(true);  // Affiche la fenêtre des chambres
                    }
                });
            } else {
                System.err.println("Échec du test de connexion à la base de données.");
                // Vous pouvez afficher une fenêtre de message d'erreur ou tenter de reconnecter
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
            e.printStackTrace(); // Affiche l'erreur pour le débogage
        }
    }
}

