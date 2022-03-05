package main;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.DataTruncation;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Data data = new Data();
        int identificador_clientes;
        int idProfesor;
        String host = "localhost";
        int puertoServer = 6000;
        Scanner scanner = new Scanner(System.in);
        Socket socket_server = null;
        try {
            socket_server = new Socket(host, puertoServer);
            System.out.println("Cliente: Conectando con el servidor...");
        } catch (ConnectException coEx) {
            System.out.println("Cliente: Error al establecer conexion con el servidor");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectInputStream inFromServer= null;
        try {
            inFromServer = new ObjectInputStream(socket_server.getInputStream());
        } catch (IOException e) {
            System.out.println("Cliente: Error al establecer flujo de entrada desde el servidor");
        }
        ObjectOutputStream outToServer= null;
        try {
            outToServer = new ObjectOutputStream(socket_server.getOutputStream());
        } catch (IOException e) {
            System.out.println("Cliente: Error al establecer flujo de salida al servidor");
        }
        int idProfesorValor;
        do {
            System.out.println("Introduce Id del profesor");
            try {
                idProfesorValor = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException imEx) {
                scanner.nextLine();
                idProfesorValor = 1;
                System.out.println("id incorrecto");
                continue;
            }
            Data dat = new Data();
            dat.setIdProfesor(idProfesorValor);
            try {
                outToServer.writeObject(dat);
            } catch (IOException e) {
                System.out.println("Client: Error al enviar informacion al servidor: Mensaje:"+e.getMessage()+" Causa: "+e.getCause());
            }
            if (idProfesorValor > 0) {
                try {
                    Data dat2 = (Data) inFromServer.readObject();
                    System.out.println(dat2);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } while (idProfesorValor > 0);
    }
}
