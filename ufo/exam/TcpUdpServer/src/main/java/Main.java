import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
//        Main.startUdpServer();
        Main.startTcpServer();
    }

    private static void startTcpServer() throws IOException {
        System.out.println("Start Tcp server");
        TcpServer tcpServer = new TcpServer(3000);
        tcpServer.start();
    }

    private static void startUdpServer() throws IOException {
        System.out.println("Start udp server");
        UdpServer udp = new UdpServer(3000);
        udp.start();
    }
}
