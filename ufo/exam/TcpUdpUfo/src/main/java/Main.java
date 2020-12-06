import transport.TcpTransport;
import transport.UdpTransport;
import utils.TimeMeasure;

import java.io.*;
import java.net.DatagramPacket;
import java.util.List;
import java.util.Random;

public class Main {

    private static final String DATA_PATH = "data";

    public static void main(String[] args) throws IOException, InterruptedException {
        File file = new File(Main.DATA_PATH);
        if (!file.exists()) {
            System.out.println("Creating data file");
            Main.saveDataToFile(1000 * 1000 * 1000);
            System.out.println("Created data file");
        }

        System.out.println("Reading data file");
        byte[] data = Main.loadDataFromFile();
        System.out.println("Sending data");

        System.out.println("Send TCP");
        for (int i = 0; i < 10; i++) {
            Main.sendOverTcp(data);
        }

        System.out.println("Send UDP");
        for (int i = 0; i < 10; i++) {
            Main.sendOverUdp(data);
        }
    }

    private static void sendOverUdp(byte[] data) throws IOException {
        TimeMeasure timeMeasure = new TimeMeasure();
        UdpTransport udp = new UdpTransport("localhost", 3000, data);
        List<DatagramPacket> packetList = udp.createPackets(data);


        timeMeasure.startTimer();

        for (DatagramPacket packet : packetList) {
            udp.sendPackage(packet);
        }

        long time = timeMeasure.endTimer();

        System.out.println(time);
    }

    private static void sendOverTcp(byte[] data) throws IOException {
        TimeMeasure timeMeasure = new TimeMeasure();
        TcpTransport tcp = new TcpTransport("localhost", 3000);

        timeMeasure.startTimer();

        tcp.sendPackage(data);

        long time = timeMeasure.endTimer();

        System.out.println(time);
    }

    private static void saveDataToFile(int size) throws IOException {
        byte[] arr = new byte[size];
        new Random().nextBytes(arr);
        FileOutputStream fos = new FileOutputStream(Main.DATA_PATH);
        fos.write(arr);
    }

    private static byte[] loadDataFromFile() throws IOException {
        FileInputStream in = new FileInputStream(Main.DATA_PATH);
        byte[] data = in.readAllBytes();
        return data;
    }
}
