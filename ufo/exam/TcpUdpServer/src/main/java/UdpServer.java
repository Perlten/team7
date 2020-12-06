import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServer {

    private int port;
    private DatagramSocket serverSocket;
    private byte[] buf = new byte[64000];


    public UdpServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        serverSocket = new DatagramSocket(this.port);

        while (true) {
            DatagramPacket packet
                    = new DatagramPacket(buf, buf.length);
            serverSocket.receive(packet);
        }
    }
}
