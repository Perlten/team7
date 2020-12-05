import transport.TcpTransport;
import transport.UdpTransport;
import utils.TimeMeasure;

import java.io.*;
import java.net.DatagramPacket;
import java.util.List;
import java.util.Random;

public class Main {


    public static void main(String[] args) throws IOException, InterruptedException {

        byte[] data = Main.createRandByteArray(1000 * 1000 * 1000);

//        Main.sendOverUdp(data);
        Main.sendOverTcp(data);
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

    private static byte[] createRandByteArray(int size) {
        byte[] arr = new byte[size];
        new Random().nextBytes(arr);
        return arr;
    }


}
