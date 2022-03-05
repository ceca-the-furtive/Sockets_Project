package main;

import ctrl.Ctrl_Profesor;
import modl.Profesor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Data data = new Data();
        ServerSocket server = null;
        int identificador_clientes = 1;
        int puerto = 6000;
        try {
            server = new ServerSocket(puerto);
        } catch (IOException e) {
            System.out.println("Server line 25: Error al iniciar servidor " + server + " en el puerto " + puerto);
        }
        Ctrl_Profesor ctrl_profesor = new Ctrl_Profesor();
        List<Profesor> profesores = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            profesores.add(ctrl_profesor.instanciar_Profesor());
        }

        do {
            Socket socket_client = new Socket();
            try {
                socket_client = server.accept();
            } catch (IOException e) {
                System.out.println("Server line 36: Error al establecer cliente " + socket_client);
            }

            ObjectOutputStream outToClient = null;
            try {
                outToClient = new ObjectOutputStream(socket_client.getOutputStream());
            } catch (IOException ioE) {
                System.out.println("Server line 43: Error al preparar flujo de salida al cliente");
            }
            ObjectInputStream inFromCLient = null;
            try {
                inFromCLient = new ObjectInputStream(socket_client.getInputStream());
            } catch (IOException ioE) {
                System.out.println("Server line 49: Error al obtener flujo de lectura desde el cliente");
            }

            try {
                assert inFromCLient != null;
                data = (Data) inFromCLient.readObject();
            } catch (SocketException sckEx) {
                System.out.println("Server line 55:  Error al recibir datos del cliente");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Server line 57:  Error al recibir datos del cliente");
            }
            int idProfesorFromClient = 0;
            try {
                idProfesorFromClient = data.getIdProfesor();
            } catch (Exception ex) {
                System.out.println("Server line 62: Error al obtener id del profesor desde el cliente");
            }
            while (idProfesorFromClient > 0 && idProfesorFromClient <= 5) {
                data.setProfesorObject(profesores.get(idProfesorFromClient));
                try {
                    outToClient.writeObject(data);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } while (true);
    }
}
