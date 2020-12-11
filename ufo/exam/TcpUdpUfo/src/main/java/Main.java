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
        Main.sendOverUdp(data);
//        Main.sendOverTcp(data);
    }

    private static void sendOverUdp(byte[] data) throws IOException {
        for (int i = 0; i < 100; i++) {

        UdpTransport udp = new UdpTransport("localhost", 3000, data);
        List<DatagramPacket> packetList = udp.createPackets(data);

        long start = System.currentTimeMillis();

        for (DatagramPacket packet : packetList) {
            udp.sendPackage(packet);
        }


        long end = System.currentTimeMillis();

        float sec = (end - start) ;
        System.out.println(sec + " milli seconds");
        }
    }

    private static void sendOverTcp(byte[] data) throws IOException {


        for (int i = 0; i < 100; i++) {
        TcpTransport tcp = new TcpTransport("localhost", 3000);

        long start = System.currentTimeMillis();

        tcp.sendPackage(data);

        long end = System.currentTimeMillis();

        float sec = (end - start) ;
        System.out.println(sec + " milli seconds");
        }
    }

    private static byte[] createRandByteArray(int size) {
        byte[] arr = new byte[size];
        new Random().nextBytes(arr);
        return arr;
    }


}
