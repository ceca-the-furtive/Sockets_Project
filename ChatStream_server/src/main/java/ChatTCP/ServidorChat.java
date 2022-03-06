package ChatTCP;

import java.io.*;
import java.net.*;


public class ServidorChat  {
	static final int MAXIMO = 5;//MAXIMO DE CONEXIONES PERMITIDAS	
	
	public static void main(String args[]) throws IOException {
		int PUERTO = 44444;	
		
		ServerSocket servidor = new ServerSocket(PUERTO);
		System.out.println("Servidor iniciado...");
		
		Socket tabla[] = new Socket[MAXIMO];//para controlar las  distintas conexiones	
		ComunHilos comun = new ComunHilos(MAXIMO, 0, 0, tabla); //para distintos hilos
		//esta vez proceso servidor no es un ciclo sin fin
		while (comun.getCONEXIONES() < MAXIMO) {
			Socket socket = new Socket(); //para nueva conexión			
			socket = servidor.accept();// esperando cliente
			comun.addTabla(socket, comun.getCONEXIONES());
			comun.setACTUALES(comun.getACTUALES() + 1);
			comun.setCONEXIONES(comun.getCONEXIONES() + 1);			
			//lanzo un hilo para cada cliente conectado
			// socket distinto para cada hilo
			HiloServidorChat hilo = new HiloServidorChat(socket, comun);
			hilo.start();
		}	
		servidor.close();
	}//main
}//ServidorChat..  

