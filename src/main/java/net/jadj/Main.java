package net.jadj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        int SERVER_PORT = 5001;

        try {
            // Création du serveur
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Le serveur de capitalisation est démarré sur le port : " + SERVER_PORT);

            // Accepter la connexion d'un client
            Socket clientSocket = serverSocket.accept();
            System.out.println("Un client s'est connecté : " + clientSocket.getInetAddress());

            // Préparation pour lire et envoyer des messages
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

            String message;
            System.out.println("En attente des messages du client...");

            // Boucle pour traiter les messages
            while ((message = input.readLine()) != null) {
                if (message.equalsIgnoreCase("bye")) {
                    System.out.println("Le client s'est déconnecté.");
                    break;
                }

                // Capitalisation du message
                String capitalizedMessage = message.toUpperCase();
                System.out.println("Message reçu : " + message + " | Transformé : " + capitalizedMessage);

                // Renvoi du message capitalisé au client
                output.println(capitalizedMessage);
            }

            // Fermeture des ressources
            input.close();
            output.close();
            clientSocket.close();
            serverSocket.close();
            System.out.println("Le serveur a été arrêté.");

        } catch (IOException exception) {
            System.err.println("Erreur serveur : " + exception.getMessage());
        }
    }
}