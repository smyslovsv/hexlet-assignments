package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Disconnected implements Connection{

    private TcpConnection tcpConnection;

    public Disconnected(TcpConnection tcpConnection) {
        this.tcpConnection = tcpConnection;
    }

    @Override
    public String getCurrentState() {
//        System.out.println("disconnected");
        return "disconnected";
    }

    @Override
    public void connect() {
        TcpConnection con = this.tcpConnection;
        con.setCurrentState(new Connected(con));
        System.out.println("a connection has been established with the address - "
                + tcpConnection.getIpAdress() + ":" + tcpConnection.getPort());
    }

    @Override
    public void disconnect() {
        System.out.println("Error! Connection already disconnected");

    }

    @Override
    public void write(String data) {
        System.out.println("Error! missing TCP connection");
    }
}
// END
