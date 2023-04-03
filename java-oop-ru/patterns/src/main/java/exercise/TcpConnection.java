package exercise;
import exercise.connections.Connection;
import exercise.connections.Disconnected;

// BEGIN
public class TcpConnection {
    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    private String ipAdress = "";
    private int port = 0;
    private Connection connection;

    public TcpConnection (String ipAdress, int port) {
        setIpAdress(ipAdress);
        setPort(port);
        this.connection = new Disconnected(this);
    }
    public String getCurrentState() {
//        System.out.println(this.connection.getCurrentState());
        return this.connection.getCurrentState();
    }
    public void setCurrentState(Connection connection) {
        this.connection = connection;
    }

    public void connect() {
        this.connection.connect();
    }
    public void write(String data) {
        this.connection.write(data);
    }

    public void disconnect() {
        this.connection.disconnect();
    }


}
// END
