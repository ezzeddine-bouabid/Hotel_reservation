/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hotelbookingsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class connectionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/hotel_booking"; // Remplacez par votre URL
    private static final String USER = "root"; // Votre utilisateur MySQL
    private static final String PASSWORD = ""; // Votre mot de passe MySQL

    // Méthode pour établir la connexion
    public static Connection connect() {
        Connection conn = null;
        try {
            // Charger le driver JDBC (non nécessaire pour les versions récentes de Java)
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            // Établir la connexion
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion réussie !");
        } catch (SQLException e) {
            System.err.println("Erreur de connexion : " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC introuvable : " + e.getMessage());
        }
        return conn;
    }
    
}

