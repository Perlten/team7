package transport;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UdpTransport {

    private DatagramSocket clientSocket;
    private String ip;
    private int port;

    private final int BUFFER_SIZE = 64000;

    public UdpTransport(String ip, int port, byte[] data) throws IOException {
        this.clientSocket = new DatagramSocket();
        this.ip = ip;
        this.port = port;
    }

    public void sendPackage(DatagramPacket packet) throws IOException {
        this.clientSocket.send(packet);
    }

    public List<DatagramPacket> createPackets(byte[] source) throws UnknownHostException {

        byte[][] ret = new byte[(int) Math.ceil(source.length / (double) this.BUFFER_SIZE)][this.BUFFER_SIZE];

        int start = 0;

        for (int i = 0; i < ret.length; i++) {
            ret[i] = Arrays.copyOfRange(source, start, start + this.BUFFER_SIZE);
            start += this.BUFFER_SIZE;
        }

        InetAddress address = InetAddress.getByName(this.ip);

        List<DatagramPacket> packetList = new ArrayList<>();
        for (int i = 0; i < ret.length; i++) {
            byte[] data = ret[i];
            DatagramPacket packet = new DatagramPacket(data, data.length, address, this.port);
            packetList.add(packet);
        }
        return packetList;
    }

}
