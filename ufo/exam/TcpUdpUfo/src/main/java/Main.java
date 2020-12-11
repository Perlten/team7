import transport.TcpTransport;
import transport.UdpTransport;
import utils.TimeMeasure;

import java.io.*;
import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private static final String DATA_PATH = "data";
    private static final int SAMPLE_SIZE = 1000;

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
        List<Long> tcpResList = new ArrayList<>();
        for (int i = 0; i < Main.SAMPLE_SIZE; i++) {
            long time = Main.sendOverTcp(data);
            tcpResList.add(time);
        }
        Main.saveResToFile(tcpResList, "./tcpRes.txt");

        System.out.println("Send UDP");
        List<Long> udpResList = new ArrayList<>();
        for (int i = 0; i < Main.SAMPLE_SIZE; i++) {
            long time = Main.sendOverUdp(data);
            udpResList.add(time);
        }
        Main.saveResToFile(udpResList, "./udpRes.txt");
    }

<<<<<<< HEAD
        byte[] data = Main.createRandByteArray(1000 * 1000 * 1000);
        Main.sendOverUdp(data);
//        Main.sendOverTcp(data);
    }

    private static void sendOverUdp(byte[] data) throws IOException {
        for (int i = 0; i < 100; i++) {

        UdpTransport udp = new UdpTransport("localhost", 3000, data);
=======
    private static void saveResToFile(List<Long> dataList, String path) {
        dataList.sort(Long::compareTo);
        File file = new File(path);
        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            for (long data : dataList) {
                writer.write(Long.toString(data) + "\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static long sendOverUdp(byte[] data) throws IOException {
        TimeMeasure timeMeasure = new TimeMeasure();
        UdpTransport udp = new UdpTransport("localhost", 3001, data);
>>>>>>> 1989a31fcbc97abc1c735e5142473c281672bc6e
        List<DatagramPacket> packetList = udp.createPackets(data);

        long start = System.currentTimeMillis();

        for (DatagramPacket packet : packetList) {
            udp.sendPackage(packet);
        }


<<<<<<< HEAD
        long end = System.currentTimeMillis();

        float sec = (end - start) ;
        System.out.println(sec + " milli seconds");
        }
    }

    private static void sendOverTcp(byte[] data) throws IOException {


        for (int i = 0; i < 100; i++) {
        TcpTransport tcp = new TcpTransport("localhost", 3000);
=======
        System.out.println(time);
        return time;
    }

    private static long sendOverTcp(byte[] data) throws IOException {
        TimeMeasure timeMeasure = new TimeMeasure();
        TcpTransport tcp = new TcpTransport("localhost", 3000);

        timeMeasure.startTimer();
>>>>>>> 1989a31fcbc97abc1c735e5142473c281672bc6e

        long start = System.currentTimeMillis();

        tcp.sendPackage(data);

<<<<<<< HEAD
        long end = System.currentTimeMillis();

        float sec = (end - start) ;
        System.out.println(sec + " milli seconds");
        }
=======
        System.out.println(time);
        return time;
>>>>>>> 1989a31fcbc97abc1c735e5142473c281672bc6e
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
