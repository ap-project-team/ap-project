package src.ApProject.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static src.ApProject.network.ConnectionMode.HOST;

public class NetworkParticipant {
    private ServerSocket serverSocket;
    private Socket socket;

    private int port;
    private String ip;

    private ObjectOutputStream out;
    private ObjectInputStream in;

    ConnectionMode connectionMode;

    public NetworkParticipant(ConnectionMode mode, int port, String ip) {
        if (mode == null)
            mode = HOST;
        this.connectionMode = mode;
        this.port = port;
    }

    public void startConnection() {
        try {
            if (isHost()) {
                serverSocket = new ServerSocket(port);
                this.port = serverSocket.getLocalPort();
                serverSocket.setSoTimeout(20);
                socket = serverSocket.accept();

            } else {
                socket = new Socket(ip, port);
            }

            if (socket != null) {
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());

                ConnectionThread connectionThread = new ConnectionThread();
                connectionThread.start();
            }
            else {
                //todo alert "connection timed out"
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isHost() {
        return connectionMode == HOST;
    }


    class ConnectionThread extends Thread{//mainly this class is receiving thread

        @Override
        public void run() {
            try {
                Message message = (Message)in.readObject();
                message.run();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}



