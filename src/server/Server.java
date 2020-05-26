package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private final int PUERTO = 9876;
    private ServerSocket serverSocket;
    private Socket socket;

    public Server() throws IOException {
        System.out.println("Iniciando el servidor...");
        serverSocket = new ServerSocket(PUERTO);
        socket = new Socket();
    }

    public void initServer() throws IOException {

        while(true){
            System.out.println("Esperando la conexion de un Cliente...");
            socket = serverSocket.accept();
            System.out.println("Cliente conectado.");
            DataOutputStream salidaServidor = new DataOutputStream(socket.getOutputStream());
            DataInputStream entradaServidor = new DataInputStream(socket.getInputStream());
            String numeroTareas;
            int numero;
            String nombre = "";
            salidaServidor.writeUTF("Hola, ¿Como te llamas?");
            nombre = entradaServidor.readUTF();
            salidaServidor.writeUTF("¿Cuantas Tareas hay que realizar?");
            numeroTareas = entradaServidor.readUTF();
            numero = Integer.parseInt(numeroTareas);
            ArrayList<Tarea> tareas = new ArrayList<>();
            for (int i = 1; i <= numero; i++) {
                Tarea tarea = new Tarea();
                salidaServidor.writeUTF("Tarea numero " + i);
                salidaServidor.writeUTF("Introduce la descripcion de la Tarea");
                tarea.setDescripcion(entradaServidor.readUTF());
                salidaServidor.writeUTF("Introduce el estado de la Tarea");
                tarea.setEstado(entradaServidor.readUTF());
                tareas.add(tarea);
            }
            salidaServidor.writeUTF("Las tareas de " + nombre + " son:");
            for(Tarea tarea:tareas){
                salidaServidor.writeUTF(tarea.toString());
                salidaServidor.writeUTF("\n");
            }
            salidaServidor.writeUTF("Se finaliza la Conexión");

        }

    }

}
