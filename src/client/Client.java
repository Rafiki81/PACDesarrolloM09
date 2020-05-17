package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final int PUERTO = 9876;
    private final String HOST = "localhost";
    private Socket socket;

    public Client() throws IOException {
        System.out.println("Iniciando cliente...");
        socket = new Socket(HOST, PUERTO);
    }

    public void initClient() throws IOException {
        Scanner entradaTeclado = new Scanner(System.in);
        String msn = "";
        int tareas;
        DataInputStream entradaCliente = new DataInputStream(socket.getInputStream());
        DataOutputStream salidaCliente = new DataOutputStream(socket.getOutputStream());
        System.out.println(entradaCliente.readUTF());
        msn = entradaTeclado.nextLine();
        salidaCliente.writeUTF(msn);
        System.out.println(entradaCliente.readUTF());
        tareas = entradaTeclado.nextInt();
        salidaCliente.writeUTF(String.valueOf(tareas));
        for(int i=1; i<=tareas; i++){
            Scanner sn = new Scanner(System.in);
            System.out.println(entradaCliente.readUTF());
            System.out.println(entradaCliente.readUTF());
            salidaCliente.writeUTF(sn.nextLine());
            System.out.println(entradaCliente.readUTF());
            salidaCliente.writeUTF(sn.nextLine());
        }
        System.out.println(entradaCliente.readUTF());
        for(int i=1; i<=tareas; i++){
            System.out.println(entradaCliente.readUTF());
            System.out.println(entradaCliente.readUTF());
        }
        System.out.println(entradaCliente.readUTF());
    }

}
