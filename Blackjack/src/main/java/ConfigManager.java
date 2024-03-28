/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */

import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConfigManager {
    private Properties config;
    private final String configFilePath = "param.txt";
    
    public ConfigManager() {
        config = new Properties();
        loadConfig();
    }
    
// Lit le fichier param.txt en utilisant un FileInputStream, les paramètres sont chargés dans l'objet Properties
// try mot clés en Java pour démarrer un bloc de code qui pourrait poser problème.   
    private void loadConfig() {
        try (FileInputStream fis = new FileInputStream(configFilePath)) {
            config.load(fis);
            }
        
// catch mot clés pour gérer les erreurs de leccture 
        catch(IOException e) {
            System.out.println("Erreurs lors de la lecture du fichier");
			
		}
        }
    
 // Garde en mémoire la valeur minBal   
    public void setMinBal(int minBal){
        config.setProperty("minBal", String.valueOf(minBal));
        saveConfig();
    }
    
 // Garde en mémoire la valeur maxBal
    public void setMaxBal(int maxBal) {
        config.setProperty("maxBal", String.valueOf(maxBal));
        saveConfig();
    }
    
// Cette méthode écrit les paramètres de configuration actuels dans le fichier param.txt en utilisant un FileOutputStream 
    private void saveConfig() {
        try (FileOutputStream fos = new FileOutputStream(configFilePath)){
                config.store(fos,"param.txt");
        }
        
        catch(IOException e) {
            System.out.println("Erreur lors de la sauvegarde du fichier");
            
        }
        
        }
    
 // Méthode pour vérifier l'Id du joueur et l'Id du fichier    
    public boolean isAuthorized(String userId) {
        String authorizedId = config.getProperty("authorizedId");
        return authorizedId != null && authorizedId.equals(userId);       
    }
    
// Connexion à la base de donnée si l'Id est juste     
    public Connection connectedtoBDD() {
        if (isAuthorized(Player.getName()) ) {
            try { 
                String bddURL=config.getProperty("bddURL");
                DriverManager.getConnection(bddURL);
            }
            catch(SQLException e) {
                System.out.println("Impossible de se connecter a la base de données");
            }
        }
        else{
                System.out.println("Identifiant incorrect. Accés refusé");
                    
            }
            }
        
        
        }
    
    
    
    
  
    
    
            
    

