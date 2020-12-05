package transport;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpTransport {

    private Socket clientSocket;

    public TcpTransport(String ip, int port) throws IOException {
        this.start(ip, port);
    }

    private void start(String ip, int port) throws IOException {
        this.clientSocket = new Socket(ip, port);
    }

    public void sendPackage(byte[] data) throws IOException {
        OutputStream out = this.clientSocket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(out);


        dos.writeInt(data.length);
        dos.write(data);

        out.close();
        dos.close();
        this.clientSocket.close();
    }

}
