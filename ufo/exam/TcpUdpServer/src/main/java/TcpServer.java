

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {

    private int port;
    private ServerSocket server;

    private final int BUFFER_SIZE = 8000;

    public TcpServer(int port) throws IOException {
        this.port = port;
        this.server = new ServerSocket(this.port);
    }

    public void start() throws IOException {
        while (true) {
            Socket client = this.server.accept();

            InputStream in = client.getInputStream();
            DataInputStream dis = new DataInputStream(in);

            int len = dis.readInt();
            byte[] data = new byte[len];
            if (len > 0) {
                dis.readFully(data);
            }

            System.out.println(len);

            in.close();
            client.close();
            dis.close();

            System.out.println("DONE");
            System.out.println("Data send: " + data.length);
        }

    }

}
