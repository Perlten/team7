package dk.dd.udps;

import java.io.IOException;
import java.net.*;

/**
 * @author Dora
 */
public class UDPServer {
    private static final int serverPort = 7777;

    // buffers for the messages
    private static byte[] dataIn = new byte[5148];
    private static byte[] dataOut = new byte[5148];

    // In UDP messages are encapsulated in packages and sent over sockets
    private static DatagramPacket requestPacket;
    private static DatagramPacket responsePacket;
    private static DatagramSocket serverSocket;


    public static void main(String[] args) throws Exception {
        String messageIn, messageOut;
        try {
            String serverIP = InetAddress.getLocalHost().getHostAddress();
            // Opens socket for accepting requests
            serverSocket = new DatagramSocket(serverPort);
            while (true) {
                System.out.println("Server " + serverIP + " running ...");
                byte[] image = receiveRequest();

                sendResponse(image);
            }
        } catch (Exception e) {
            System.out.println(" Connection fails: " + e);
        } finally {
            serverSocket.close();
            System.out.println("Server port closed");
        }
    }

    public static byte[] receiveRequest() throws IOException {
        requestPacket = new DatagramPacket(dataIn, dataIn.length);
        serverSocket.receive(requestPacket);
        System.out.println("Request: " + requestPacket.getData());

        return requestPacket.getData();
    }

    public static String processRequest(String message) {
        return message.toUpperCase();
    }

    public static void sendResponse(byte[] image) throws IOException {
        InetAddress clientIP;
        int clientPort;

        clientIP = requestPacket.getAddress();
        clientPort = requestPacket.getPort();
        System.out.println("Client port: " + clientPort);
        dataOut = image;
        responsePacket = new DatagramPacket(dataOut, dataOut.length, clientIP, clientPort);
        serverSocket.send(responsePacket);
    }
}
