package dk.dd.udpc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * @author Dora Di
 */
public class UDPClient {
    private static final int serverPort = 7777;

    // buffers for the messages
    public static String message;
    private static byte[] dataIn = new byte[5148];
    private static byte[] dataOut = new byte[5148];

    // In UDP messages are encapsulated in packages and sent over sockets
    private static DatagramPacket requestPacket;
    private static DatagramPacket responsePacket;
    private static DatagramSocket clientSocket;

    private static byte[] imageSend;

    public static void main(String[] args) throws IOException {
        //saveImage(loadImage("tinyImage.png"));
        // Enter server's IP address as a parameter from Run/Edit Configuration/Application/Program Arguments

        clientSocket = new DatagramSocket();
        InetAddress serverIP = InetAddress.getByName("localhost");
        System.out.println(serverIP);

        Scanner scan = new Scanner(System.in);
        System.out.println("Type message: ");

        while ((message = scan.nextLine()) != null) {
            sendRequest(serverIP);
            receiveResponse();
        }
        clientSocket.close();


    }

    public static void sendRequest(InetAddress serverIP) throws IOException {
        //clientSocket = new DatagramSocket();
        dataOut = loadImage("tinyImage.png");
        imageSend = dataOut;
        requestPacket = new DatagramPacket(dataOut, dataOut.length, serverIP, serverPort);
        clientSocket.send(requestPacket);
    }

    public static byte[] loadImage(String path) throws IOException {
        BufferedImage bImage = ImageIO.read(new File(path));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "png", bos);
        return bos.toByteArray();
    }

    public static void saveImage(byte[] image) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(image);
        BufferedImage bImage = ImageIO.read(bis);
        ImageIO.write(bImage, "png", new File("imageFromServer.png"));
        System.out.println("image created");
    }

    public static void receiveResponse() throws IOException {
        //clientSocket = new DatagramSocket();
        responsePacket = new DatagramPacket(dataIn, dataIn.length);
        clientSocket.receive(responsePacket);
        saveImage(responsePacket.getData());
        System.out.println("Byte from server: " + responsePacket.getLength());
    }
}
