/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author godof
 */
public class SMTPService {
    private Socket sckt;
    private final String HOST = "mail.tecnoweb.org.bo";
    private final int PORT = 25;
    private final String EMISOR = "grupo06sc@tecnoweb.org.bo";
    private BufferedReader entrada;
    private DataOutputStream salida;

    public SMTPService() throws IOException {
        try {
            sckt = new Socket(HOST, PORT);
            entrada = new BufferedReader(new InputStreamReader(sckt.getInputStream()));
            salida = new DataOutputStream(sckt.getOutputStream());
        } catch (IOException e) {
            System.out.println("Excepcion: " + e);
        }
    }

    public void sendMessage(String sbjct, String receptor, String message) {
        try {
            System.out.println(entrada.readLine());
            salida.writeBytes("HELO mail.tecnoweb.org.bo\r\n");
            //entrada.readLine();
            getMultiline(entrada);
            salida.writeBytes("MAIL FROM: <" + EMISOR + "> \r\n");
            entrada.readLine();
            salida.writeBytes("RCPT TO: <" + receptor + "> \r\n");
            entrada.readLine();
            salida.writeBytes("DATA\r\n");
            entrada.readLine();
            String comando = "Subject:" + sbjct + "\r\n" + message + "\n" + ".\r\n";
            salida.writeBytes(comando);
            entrada.readLine();
            System.out.println("Respuesta de "+ EMISOR + " enviada a " + receptor);
        } catch (IOException e) {
            System.out.println("Excepcion al enviar respuesta: " + e);
        }
    }

    public List<String> getMultiline(BufferedReader in) {
        List<String> lines = new ArrayList<String>();
        try {
            while (true) {
                String line = in.readLine();
                if (line == null) {
                }
                if (line.charAt(3) == ' ') {
                    lines.add(line);
                    break;
                }
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Excepcion en getMultiline SMTPService: " + e);
        }
        return lines;
    }

    public void close() {
        try {
            salida.writeBytes("QUIT\r\n");
            entrada.readLine();
            entrada.close();
            salida.close();
            sckt.close();
        } catch (IOException e) {
            System.out.println("Excepcion al cerrar SMTPService: " + e);
        }
    }
}
